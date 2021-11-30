/*
 * Copyright 2014 - 2019 Rafael Winterhalter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.bytebuddy.agent;

import com.sun.jna.*;
import com.sun.jna.platform.win32.*;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.W32APIOptions;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileLock;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * An implementation for attachment on a virtual machine. This interface mimics the tooling API's virtual
 * machine interface to allow for similar usage by {@link ByteBuddyAgent} where all calls are made via
 * reflection such that this structural typing suffices for interoperability.
 * </p>
 * <p>
 * <b>Note</b>: Implementations are required to declare a static method {@code attach(String)} returning an
 * instance of a class that declares the methods defined by {@link VirtualMachine}.
 * </p>
 */
public interface VirtualMachine {

    /**
     * Loads an agent into the represented virtual machine.
     *
     * @param jarFile  The jar file to attach.
     * @param argument The argument to provide or {@code null} if no argument should be provided.
     * @throws IOException If an I/O exception occurs.
     */
    void loadAgent(String jarFile, String argument) throws IOException;

    /**
     * Loads a native agent into the represented virtual machine.
     *
     * @param library  The agent library.
     * @param argument The argument to provide or {@code null} if no argument should be provided.
     * @throws IOException If an I/O exception occurs.
     */
    void loadAgentPath(String library, String argument) throws IOException;

    /**
     * Detaches this virtual machine representation.
     *
     * @throws IOException If an I/O exception occurs.
     */
    void detach() throws IOException;

    /**
     * A resolver for the current VM's virtual machine attachment emulation.
     */
    enum Resolver implements PrivilegedAction<Class<? extends VirtualMachine>> {

        /**
         * The singleton instance.
         */
        INSTANCE;

        /**
         * {@inheritDoc}
         */
        public Class<? extends VirtualMachine> run() {
            try {
                Class<?> platform = Class.forName("com.sun.jna.Platform");
                if (System.getProperty("java.vm.vendor").toUpperCase(Locale.US).contains("J9")) {
                    return ForOpenJ9.class;
                } else if ((Boolean) platform.getMethod("isWindows").invoke(null) || (Boolean) platform.getMethod("isWindowsCE").invoke(null)) {
                    throw new UnsupportedOperationException("Attachment emulation is not currently available for HotSpot on Windows");
                } else {
                    return ForHotSpot.class;
                }
            } catch (ClassNotFoundException exception) {
                throw new IllegalStateException("Optional JNA dependency is not available", exception);
            } catch (NoSuchMethodException exception) {
                throw new IllegalStateException("The signature of the included JNA distribution is incompatible", exception);
            } catch (IllegalAccessException exception) {
                throw new UnsupportedOperationException("Could not access JNA", exception);
            } catch (InvocationTargetException exception) {
                throw new UnsupportedOperationException("Could not invoke JNA method", exception);
            }
        }
    }

    /**
     * A virtual machine attachment implementation for a HotSpot VM or any compatible JVM.
     */
    class ForHotSpot implements VirtualMachine {

        /**
         * The UTF-8 charset name.
         */
        private static final String UTF_8 = "UTF-8";

        /**
         * The protocol version to use for communication.
         */
        private static final String PROTOCOL_VERSION = "1";

        /**
         * The {@code load} command.
         */
        private static final String LOAD_COMMAND = "load";

        /**
         * The {@code instrument} command.
         */
        private static final String INSTRUMENT_COMMAND = "instrument";

        /**
         * A delimiter to be used for attachment.
         */
        private static final String ARGUMENT_DELIMITER = "=";

        /**
         * A blank line argument.
         */
        private static final byte[] BLANK = new byte[]{0};

        /**
         * The virtual machine connection.
         */
        private final Connection connection;

        /**
         * Creates a new virtual machine connection for HotSpot.
         *
         * @param connection The virtual machine connection.
         */
        protected ForHotSpot(Connection connection) {
            this.connection = connection;
        }

        /**
         * Attaches to the supplied process id using the default JNA implementation.
         *
         * @param processId The process id.
         * @return A suitable virtual machine implementation.
         * @throws IOException If an IO exception occurs during establishing the connection.
         */
        public static VirtualMachine attach(String processId) throws IOException {
            return attach(processId, new Connection.ForJnaPosixSocket.Factory(15, 100, TimeUnit.MILLISECONDS));
        }

        /**
         * Attaches to the supplied process id using the supplied connection factory.
         *
         * @param processId         The process id.
         * @param connectionFactory The connection factory to use.
         * @return A suitable virtual machine implementation.
         * @throws IOException If an IO exception occurs during establishing the connection.
         */
        public static VirtualMachine attach(String processId, Connection.Factory connectionFactory) throws IOException {
            return new ForHotSpot(connectionFactory.connect(processId));
        }

        /**
         * {@inheritDoc}
         */
        public void loadAgent(String jarFile, String argument) throws IOException {
            load(jarFile, false, argument);
        }

        /**
         * {@inheritDoc}
         */
        public void loadAgentPath(String library, String argument) throws IOException {
            load(library, true, argument);
        }

        /**
         * Loads an agent by the given command.
         *
         * @param file     The Java agent or library to be loaded.
         * @param isNative {@code true} if the agent is native.
         * @param argument The argument to the agent or {@code null} if no argument is given.
         * @throws IOException If an I/O exception occurs.
         */
        protected void load(String file, boolean isNative, String argument) throws IOException {
            connection.write(PROTOCOL_VERSION.getBytes(UTF_8));
            connection.write(BLANK);
            connection.write(LOAD_COMMAND.getBytes(UTF_8));
            connection.write(BLANK);
            connection.write(INSTRUMENT_COMMAND.getBytes(UTF_8));
            connection.write(BLANK);
            connection.write(Boolean.toString(isNative).getBytes(UTF_8));
            connection.write(BLANK);
            connection.write((argument == null
                    ? file
                    : file + ARGUMENT_DELIMITER + argument).getBytes(UTF_8));
            connection.write(BLANK);
            byte[] buffer = new byte[1];
            StringBuilder stringBuilder = new StringBuilder();
            int length;
            while ((length = connection.read(buffer)) != -1) {
                if (length > 0) {
                    if (buffer[0] == 10) {
                        break;
                    }
                    stringBuilder.append((char) buffer[0]);
                }
            }
            switch (Integer.parseInt(stringBuilder.toString())) {
                case 0:
                    return;
                case 101:
                    throw new IOException("Protocol mismatch with target VM");
                default:
                    buffer = new byte[1024];
                    stringBuilder = new StringBuilder();
                    while ((length = connection.read(buffer)) != -1) {
                        stringBuilder.append(new String(buffer, 0, length, UTF_8));
                    }
                    throw new IllegalStateException(stringBuilder.toString());
            }
        }

        /**
         * {@inheritDoc}
         */
        public void detach() throws IOException {
            connection.close();
        }

        /**
         * Represents a connection to a virtual machine.
         */
        public interface Connection extends Closeable {

            /**
             * Reads from the connected virtual machine.
             *
             * @param buffer The buffer to read from.
             * @return The amount of bytes that were read.
             * @throws IOException If an I/O exception occurs during reading.
             */
            int read(byte[] buffer) throws IOException;

            /**
             * Writes to the connected virtual machine.
             *
             * @param buffer The buffer to write to.
             * @throws IOException If an I/O exception occurs during writing.
             */
            void write(byte[] buffer) throws IOException;

            /**
             * A factory for creating connections to virtual machines.
             */
            interface Factory {

                /**
                 * Connects to the supplied process.
                 *
                 * @param processId The process id.
                 * @return The connection to the virtual machine with the supplied process id.
                 * @throws IOException If an I/O exception occurs during connecting to the targeted VM.
                 */
                Connection connect(String processId) throws IOException;

                /**
                 * A factory for attaching on a POSIX-compatible VM.
                 */
                abstract class ForPosixSocket implements Factory {

                    /**
                     * The temporary directory on Unix systems.
                     */
                    private static final String TEMPORARY_DIRECTORY = "/tmp";

                    /**
                     * The name prefix for a socket.
                     */
                    private static final String SOCKET_FILE_PREFIX = ".java_pid";

                    /**
                     * The name prefix for an attachment file indicator.
                     */
                    private static final String ATTACH_FILE_PREFIX = ".attach_pid";

                    /**
                     * The maximum amount of attempts for checking the establishment of a socket connection.
                     */
                    private final int attempts;

                    /**
                     * The pause between two checks for an established socket connection.
                     */
                    private final long pause;

                    /**
                     * The time unit of the pause time.
                     */
                    private final TimeUnit timeUnit;

                    /**
                     * Creates a connection factory for creating a POSIX socket connection.
                     *
                     * @param attempts The maximum amount of attempts for checking the establishment of a socket connection.
                     * @param pause    The pause between two checks for an established socket connection.
                     * @param timeUnit The time unit of the pause time.
                     */
                    protected ForPosixSocket(int attempts, long pause, TimeUnit timeUnit) {
                        this.attempts = attempts;
                        this.pause = pause;
                        this.timeUnit = timeUnit;
                    }

                    /**
                     * {@inheritDoc}
                     */
                    @SuppressFBWarnings(value = "DMI_HARDCODED_ABSOLUTE_FILENAME", justification = "File name convention is specified.")
                    public Connection connect(String processId) throws IOException {
                        File socket = new File(TEMPORARY_DIRECTORY, SOCKET_FILE_PREFIX + processId);
                        if (!socket.exists()) {
                            String target = ATTACH_FILE_PREFIX + processId, path = "/proc/" + processId + "/cwd/" + target;
                            File attachFile = new File(path);
                            try {
                                if (!attachFile.createNewFile() && !attachFile.isFile()) {
                                    throw new IllegalStateException("Could not create attach file: " + attachFile);
                                }
                            } catch (IOException ignored) {
                                attachFile = new File(TEMPORARY_DIRECTORY, target);
                                if (!attachFile.createNewFile() && !attachFile.isFile()) {
                                    throw new IllegalStateException("Could not create attach file: " + attachFile);
                                }
                            }
                            try {
                                kill(processId, 3, socket);
                                int attempts = this.attempts;
                                while (!socket.exists() && attempts-- > 0) {
                                    timeUnit.sleep(pause);
                                }
                                if (!socket.exists()) {
                                    throw new IllegalStateException("Target VM did not respond: " + processId);
                                }
                            } catch (InterruptedException e) {
                                throw new UnsupportedOperationException("Not yet implemented");
                            } finally {
                                if (!attachFile.delete()) {
                                    attachFile.deleteOnExit();
                                }
                            }
                        }
                        return doConnect(socket);
                    }

                    /**
                     * Sends a kill signal to the target process.
                     *
                     * @param processId The process id.
                     * @param signal    The signal to send.
                     * @param socket    The socket to read from.
                     */
                    protected abstract void kill(String processId, int signal, File socket);

                    /**
                     * Connects to the supplied POSIX socket.
                     *
                     * @param socket The socket to connect to.
                     * @return An active connection to the supplied socket.
                     * @throws IOException If an error occurs during connection.
                     */
                    protected abstract Connection doConnect(File socket) throws IOException;
                }
            }

            /**
             * Implements a connection for a Posix socket in JNA.
             */
            class ForJnaPosixSocket implements Connection {

                /**
                 * The JNA library to use.
                 */
                private final PosixLibrary library;

                /**
                 * The socket's handle.
                 */
                private final int handle;

                /**
                 * Creates a new connection for a Posix socket.
                 *
                 * @param library The JNA library to use.
                 * @param handle  The socket's handle.
                 */
                protected ForJnaPosixSocket(PosixLibrary library, int handle) {
                    this.library = library;
                    this.handle = handle;
                }

                /**
                 * {@inheritDoc}
                 */
                public int read(byte[] buffer) {
                    return library.read(handle, ByteBuffer.wrap(buffer), buffer.length);
                }

                /**
                 * {@inheritDoc}
                 */
                public void write(byte[] buffer) {
                    library.write(handle, ByteBuffer.wrap(buffer), buffer.length);
                }

                /**
                 * {@inheritDoc}
                 */
                public void close() {
                    library.close(handle);
                }

                /**
                 * A JNA library binding for Posix sockets.
                 */
                protected interface PosixLibrary extends Library {

                    /**
                     * Sends a kill command.
                     *
                     * @param processId The process id to kill.
                     * @param signal    The signal to send.
                     * @throws LastErrorException If an error occurs.
                     */
                    void kill(int processId, int signal) throws LastErrorException;

                    /**
                     * Creates a POSIX socket connection.
                     *
                     * @param domain   The socket's domain.
                     * @param type     The socket's type.
                     * @param protocol The protocol version.
                     * @return A handle to the socket that was created or {@code 0} if no socket could be created.
                     * @throws LastErrorException If an error occurs.
                     */
                    int socket(int domain, int type, int protocol) throws LastErrorException;

                    /**
                     * Connects a socket connection.
                     *
                     * @param handle  The socket's handle.
                     * @param address The address of the POSIX socket.
                     * @param length  The length of the socket value.
                     * @throws LastErrorException If an error occurs.
                     */
                    void connect(int handle, SocketAddress address, int length) throws LastErrorException;

                    /**
                     * Reads from a POSIX socket.
                     *
                     * @param handle The socket's handle.
                     * @param buffer The buffer to read from.
                     * @param count  The bytes being read.
                     * @return The amount of bytes that could be read.
                     * @throws LastErrorException If an error occurs.
                     */
                    int read(int handle, ByteBuffer buffer, int count) throws LastErrorException;

                    /**
                     * Writes to a POSIX socket.
                     *
                     * @param handle The socket's handle.
                     * @param buffer The buffer to write to.
                     * @param count  The bytes being written.
                     * @throws LastErrorException If an error occurs.
                     */
                    void write(int handle, ByteBuffer buffer, int count) throws LastErrorException;

                    /**
                     * Closes the socket connection.
                     *
                     * @param handle The handle of the connection.
                     * @throws LastErrorException If an error occurs.
                     */
                    void close(int handle) throws LastErrorException;

                    /**
                     * Represents an address for a POSIX socket.
                     */
                    class SocketAddress extends Structure {

                        /**
                         * The socket family.
                         */
                        @SuppressWarnings("unused")
                        @SuppressFBWarnings(value = "URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD", justification = "Field required by native implementation.")
                        public short family = 1;

                        /**
                         * The socket path.
                         */
                        public byte[] path = new byte[100];

                        /**
                         * Sets the socket path.
                         *
                         * @param path The socket path.
                         */
                        protected void setPath(String path) {
                            try {
                                System.arraycopy(path.getBytes("UTF-8"), 0, this.path, 0, path.length());
                                System.arraycopy(new byte[]{0}, 0, this.path, path.length(), 1);
                            } catch (UnsupportedEncodingException exception) {
                                throw new IllegalStateException(exception);
                            }
                        }

                        @Override
                        protected List<String> getFieldOrder() {
                            return Arrays.asList("family", "path");
                        }
                    }
                }

                /**
                 * A factory for a POSIX socket connection to a JVM using JNA.
                 */
                public static class Factory extends Connection.Factory.ForPosixSocket {

                    /**
                     * The socket library API.
                     */
                    private final PosixLibrary library;

                    /**
                     * Creates a connection factory for a POSIX socket using JNA.
                     *
                     * @param attempts The maximum amount of attempts for checking the establishment of a socket connection.
                     * @param pause    The pause between two checks for an established socket connection.
                     * @param timeUnit The time unit of the pause time.
                     */
                    public Factory(int attempts, long pause, TimeUnit timeUnit) {
                        super(attempts, pause, timeUnit);
                        library = Native.load("c", PosixLibrary.class);
                    }

                    @Override
                    protected void kill(String processId, int signal, File socket) {
                        library.kill(Integer.parseInt(processId), signal);
                    }

                    @Override
                    public Connection doConnect(File socket) {
                        int handle = library.socket(1, 1, 0);
                        PosixLibrary.SocketAddress address = new PosixLibrary.SocketAddress();
                        try {
                            address.setPath(socket.getAbsolutePath());
                            library.connect(handle, address, address.size());
                            return new Connection.ForJnaPosixSocket(library, handle);
                        } finally {
                            address.clear();
                        }
                    }
                }
            }
        }
    }

    /**
     * A virtual machine attachment implementation for OpenJ9 or any compatible JVM.
     */
    class ForOpenJ9 implements VirtualMachine {

        /**
         * The temporary folder for attachment files for OpenJ9 VMs.
         */
        private static final String IBM_TEMPORARY_FOLDER = "com.ibm.tools.attach.directory";

        /**
         * The socket on which this VM and the target VM communicate.
         */
        private final Socket socket;

        /**
         * Creates a new virtual machine connection for OpenJ9.
         *
         * @param socket The socket on which this VM and the target VM communicate.
         */
        protected ForOpenJ9(Socket socket) {
            this.socket = socket;
        }

        /**
         * Attaches to the supplied process id using the default JNA implementation.
         *
         * @param processId The process id.
         * @return A suitable virtual machine implementation.
         * @throws IOException If an IO exception occurs during establishing the connection.
         */
        public static VirtualMachine attach(String processId) throws IOException {
            return attach(processId, 5000, Platform.isWindows() || Platform.isWindowsCE()
                    ? new Connector.ForJnaWindowsEnvironment()
                    : new Connector.ForJnaPosixEnvironment(15, 100, TimeUnit.MILLISECONDS));
        }

        /**
         * Attaches to the supplied process id.
         *
         * @param processId The process id.
         * @param timeout   The timeout for establishing the socket connection.
         * @param connector The connector to use to communicate with the target VM.
         * @return A suitable virtual machine implementation.
         * @throws IOException If an IO exception occurs during establishing the connection.
         */
        public static VirtualMachine attach(String processId, int timeout, Connector connector) throws IOException {
            File directory = new File(System.getProperty(IBM_TEMPORARY_FOLDER, connector.getTemporaryFolder()), ".com_ibm_tools_attach");
            RandomAccessFile attachLock = new RandomAccessFile(new File(directory, "_attachlock"), "rw");
            try {
                FileLock attachLockLock = attachLock.getChannel().lock();
                try {
                    List<Properties> virtualMachines;
                    RandomAccessFile master = new RandomAccessFile(new File(directory, "_master"), "rw");
                    try {
                        FileLock masterLock = master.getChannel().lock();
                        try {
                            File[] vmFolder = directory.listFiles();
                            if (vmFolder == null) {
                                throw new IllegalStateException("No descriptor files found in " + directory);
                            }
                            long userId = connector.userId();
                            virtualMachines = new ArrayList<Properties>();
                            for (File aVmFolder : vmFolder) {
                                if (aVmFolder.isDirectory() && connector.getOwnerIdOf(aVmFolder) == userId) {
                                    File attachInfo = new File(aVmFolder, "attachInfo");
                                    if (attachInfo.isFile()) {
                                        Properties virtualMachine = new Properties();
                                        FileInputStream inputStream = new FileInputStream(attachInfo);
                                        try {
                                            virtualMachine.load(inputStream);
                                        } finally {
                                            inputStream.close();
                                        }
                                        long targetProcessId = Long.parseLong(virtualMachine.getProperty("processId"));
                                        long targetUserId;
                                        try {
                                            targetUserId = Long.parseLong(virtualMachine.getProperty("userUid"));
                                        } catch (NumberFormatException ignored) {
                                            targetUserId = 0L;
                                        }
                                        if (userId != 0L && targetUserId == 0L) {
                                            targetUserId = connector.getOwnerIdOf(attachInfo);
                                        }
                                        if (targetProcessId == 0L || connector.isExistingProcess(targetProcessId)) {
                                            virtualMachines.add(virtualMachine);
                                        } else if (userId == 0L || targetUserId == userId) {
                                            File[] vmFile = aVmFolder.listFiles();
                                            if (vmFile != null) {
                                                for (File aVmFile : vmFile) {
                                                    if (!aVmFile.delete()) {
                                                        aVmFile.deleteOnExit();
                                                    }
                                                }
                                            }
                                            if (!aVmFolder.delete()) {
                                                aVmFolder.deleteOnExit();
                                            }
                                        }
                                    }
                                }
                            }
                        } finally {
                            masterLock.release();
                        }
                    } finally {
                        master.close();
                    }
                    Properties target = null;
                    for (Properties virtualMachine : virtualMachines) {
                        if (virtualMachine.getProperty("processId").equalsIgnoreCase(processId)) {
                            target = virtualMachine;
                            break;
                        }
                    }
                    if (target == null) {
                        throw new IllegalStateException("Could not locate target process info in " + directory);
                    }
                    ServerSocket serverSocket = new ServerSocket(0);
                    try {
                        serverSocket.setSoTimeout(timeout);
                        File receiver = new File(directory, target.getProperty("vmId"));
                        String key = Long.toHexString(new SecureRandom().nextLong());
                        File reply = new File(receiver, "replyInfo");
                        try {
                            if (reply.createNewFile()) {
                                connector.setPermissions(reply, 0600);
                            }
                            FileOutputStream outputStream = new FileOutputStream(reply);
                            try {
                                outputStream.write(key.getBytes("UTF-8"));
                                outputStream.write("\n".getBytes("UTF-8"));
                                outputStream.write(Long.toString(serverSocket.getLocalPort()).getBytes("UTF-8"));
                                outputStream.write("\n".getBytes("UTF-8"));
                            } finally {
                                outputStream.close();
                            }
                            Map<RandomAccessFile, FileLock> locks = new HashMap<RandomAccessFile, FileLock>();
                            try {
                                String pid = Long.toString(connector.pid());
                                for (Properties virtualMachine : virtualMachines) {
                                    if (!virtualMachine.getProperty("processId").equalsIgnoreCase(pid)) {
                                        String attachNotificationSync = virtualMachine.getProperty("attachNotificationSync");
                                        RandomAccessFile syncFile = new RandomAccessFile(attachNotificationSync == null
                                                ? new File(directory, "attachNotificationSync")
                                                : new File(attachNotificationSync), "rw");
                                        try {
                                            locks.put(syncFile, syncFile.getChannel().lock());
                                        } catch (IOException ignored) {
                                            syncFile.close();
                                        }
                                    }
                                }
                                int notifications = 0;
                                File[] item = directory.listFiles();
                                if (item != null) {
                                    for (File anItem : item) {
                                        String name = anItem.getName();
                                        if (!name.startsWith(".trash_")
                                                && !name.equalsIgnoreCase("_attachlock")
                                                && !name.equalsIgnoreCase("_master")
                                                && !name.equalsIgnoreCase("_notifier")) {
                                            notifications += 1;
                                        }
                                    }
                                }
                                connector.incrementSemaphore(directory, "_notifier", notifications);
                                try {
                                    Socket socket = serverSocket.accept();
                                    String answer = read(socket);
                                    if (answer.contains(' ' + key + ' ')) {
                                        return new ForOpenJ9(socket);
                                    } else {
                                        throw new IllegalStateException("Unexpected answered to attachment: " + answer);
                                    }
                                } finally {
                                    connector.decrementSemaphore(directory, "_notifier", notifications);
                                }
                            } finally {
                                for (Map.Entry<RandomAccessFile, FileLock> entry : locks.entrySet()) {
                                    try {
                                        try {
                                            entry.getValue().release();
                                        } finally {
                                            entry.getKey().close();
                                        }
                                    } catch (Throwable ignored) {
                                        /* do nothing */
                                    }
                                }
                            }
                        } finally {
                            if (!reply.delete()) {
                                reply.deleteOnExit();
                            }
                        }
                    } finally {
                        serverSocket.close();
                    }
                } finally {
                    attachLockLock.release();
                }
            } finally {
                attachLock.close();
            }
        }

        /**
         * {@inheritDoc}
         */
        public void loadAgent(String jarFile, String argument) throws IOException {
            write(socket, "ATTACH_LOADAGENT(instrument," + jarFile + '=' + (argument == null ? "" : argument) + ')');
            String answer = read(socket);
            if (answer.startsWith("ATTACH_ERR")) {
                throw new IllegalStateException("Target agent failed loading agent: " + answer);
            } else if (!answer.startsWith("ATTACH_ACK") && !answer.startsWith("ATTACH_RESULT=")) {
                throw new IllegalStateException("Unexpected response: " + answer);
            }
        }

        /**
         * {@inheritDoc}
         */
        public void loadAgentPath(String library, String argument) throws IOException {
            write(socket, "ATTACH_LOADAGENTPATH(" + library + (argument == null ? "" : (',' + argument)) + ')');
            String answer = read(socket);
            if (answer.startsWith("ATTACH_ERR")) {
                throw new IllegalStateException("Target agent failed loading library: " + answer);
            } else if (!answer.startsWith("ATTACH_ACK") && !answer.startsWith("ATTACH_RESULT=")) {
                throw new IllegalStateException("Unexpected response: " + answer);
            }
        }

        /**
         * {@inheritDoc}
         */
        public void detach() throws IOException {
            try {
                write(socket, "ATTACH_DETACH");
                read(socket); // The answer is intentionally ignored.
            } finally {
                socket.close();
            }
        }

        /**
         * Writes the supplied value to the target socket.
         *
         * @param socket The socket to write to.
         * @param value  The value being written.
         * @throws IOException If an I/O exception occurs.
         */
        private static void write(Socket socket, String value) throws IOException {
            socket.getOutputStream().write(value.getBytes("UTF-8"));
            socket.getOutputStream().write(0);
            socket.getOutputStream().flush();
        }

        /**
         * Reads a {©code 0}-terminated value from the target socket.
         *
         * @param socket The socket to read from.
         * @return The value that was read.
         * @throws IOException If an I/O exception occurs.
         */
        private static String read(Socket socket) throws IOException {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = socket.getInputStream().read(buffer)) != -1) {
                if (length > 0 && buffer[length - 1] == 0) {
                    outputStream.write(buffer, 0, length - 1);
                    break;
                } else {
                    outputStream.write(buffer, 0, length);
                }
            }
            return outputStream.toString("UTF-8");
        }

        /**
         * A connector for native operations being used for communication with an OpenJ9 virtual machine.
         */
        public interface Connector {

            /**
             * Returns this machine's temporary folder.
             *
             * @return The temporary folder.
             */
            String getTemporaryFolder();

            /**
             * Returns the process id of this process.
             *
             * @return The process id of this process.
             */
            long pid();

            /**
             * Returns the user id of this process.
             *
             * @return The user id of this process
             */
            long userId();

            /**
             * Returns {@code true} if the supplied process id is a running process.
             *
             * @param processId The process id to evaluate.
             * @return {@code true} if the supplied process id is currently running.
             */
            boolean isExistingProcess(long processId);

            /**
             * Returns the user id of the owner of the supplied file.
             *
             * @param file The file for which to locate the owner.
             * @return The owner id of the supplied file.
             */
            long getOwnerIdOf(File file);

            /**
             * Sets permissions for the supplied file.
             *
             * @param file        The file for which to set the permissions.
             * @param permissions The permission bits to set.
             */
            void setPermissions(File file, int permissions);

            /**
             * Increments a semaphore.
             *
             * @param directory The sempahore's control directory.
             * @param name      The semaphore's name.
             * @param count     The amount of increments.
             */
            void incrementSemaphore(File directory, String name, int count);

            /**
             * Decrements a semaphore.
             *
             * @param directory The sempahore's control directory.
             * @param name      The semaphore's name.
             * @param count     The amount of decrements.
             */
            void decrementSemaphore(File directory, String name, int count);

            /**
             * A connector implementation for a POSIX environment using JNA.
             */
            class ForJnaPosixEnvironment implements Connector {

                /**
                 * The JNA library to use.
                 */
                private final PosixLibrary library;

                /**
                 * The maximum amount of attempts for checking the result of a foreign process.
                 */
                private final int attempts;

                /**
                 * The pause between two checks for another process to return.
                 */
                private final long pause;

                /**
                 * The time unit of the pause time.
                 */
                private final TimeUnit timeUnit;

                /**
                 * Creates a new connector for a POSIX enviornment using JNA.
                 *
                 * @param attempts The maximum amount of attempts for checking the result of a foreign process.
                 * @param pause    The pause between two checks for another process to return.
                 * @param timeUnit The time unit of the pause time.
                 */
                public ForJnaPosixEnvironment(int attempts, long pause, TimeUnit timeUnit) {
                    this.attempts = attempts;
                    this.pause = pause;
                    this.timeUnit = timeUnit;
                    library = Native.load("c", PosixLibrary.class);
                }

                /**
                 * {@inheritDoc}
                 */
                public String getTemporaryFolder() {
                    return "/tmp";
                }

                /**
                 * {@inheritDoc}
                 */
                public long pid() {
                    return library.getpid();
                }

                /**
                 * {@inheritDoc}
                 */
                public long userId() {
                    return library.getuid();
                }

                /**
                 * {@inheritDoc}
                 */
                public boolean isExistingProcess(long processId) {
                    return library.kill(processId, PosixLibrary.NULL_SIGNAL) != PosixLibrary.ESRCH;
                }

                /**
                 * {@inheritDoc}
                 */
                @SuppressFBWarnings(value = "OS_OPEN_STREAM", justification = "The stream life-cycle is bound to its process.")
                public long getOwnerIdOf(File file) {
                    try {
                        // The binding for 'stat' is very platform dependant. To avoid the complexity of binding the correct method,
                        // stat is called as a separate command. This is less efficient but more portable.
                        Process process = Runtime.getRuntime().exec("stat -c=%u " + file.getAbsolutePath());
                        int attempts = this.attempts;
                        boolean exited = false;
                        String line = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8")).readLine();
                        do {
                            try {
                                if (process.exitValue() != 0) {
                                    throw new IllegalStateException("Error while executing stat");
                                }
                                exited = true;
                                break;
                            } catch (IllegalThreadStateException ignored) {
                                try {
                                    Thread.sleep(timeUnit.toMillis(pause));
                                } catch (InterruptedException exception) {
                                    Thread.currentThread().interrupt();
                                    throw new IllegalStateException("Interrupted while waiting for stat", exception);
                                }
                            }
                        } while (--attempts > 0);
                        if (!exited) {
                            process.destroy();
                            throw new IllegalStateException("Command for stat did not exit in time");
                        }
                        return Long.parseLong(line.substring(1));
                    } catch (IOException exception) {
                        throw new IllegalStateException("Unable to execute stat command", exception);
                    }
                }

                /**
                 * {@inheritDoc}
                 */
                public void setPermissions(File file, int permissions) {
                    library.chmod(file.getAbsolutePath(), permissions);
                }

                /**
                 * {@inheritDoc}
                 */
                public void incrementSemaphore(File directory, String name, int count) {
                    notifySemaphore(directory, name, count, (short) 1, (short) 0, false);
                }

                /**
                 * {@inheritDoc}
                 */
                public void decrementSemaphore(File directory, String name, int count) {
                    notifySemaphore(directory, name, count, (short) -1, (short) (PosixLibrary.SEM_UNDO | PosixLibrary.IPC_NOWAIT), true);
                }

                /**
                 * Notifies a POSIX semaphore.
                 *
                 * @param directory         The semaphore's directory.
                 * @param name              The semaphore's name.
                 * @param count             The amount of notifications to send.
                 * @param operation         The operation to apply.
                 * @param flags             The flags to set.
                 * @param acceptUnavailable {@code true} if a {@code EAGAIN} code should be accepted.
                 */
                @SuppressFBWarnings(value = {"URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD", "UUF_UNUSED_PUBLIC_OR_PROTECTED_FIELD"}, justification = "Modifier is required by JNA.")
                private void notifySemaphore(File directory, String name, int count, short operation, short flags, boolean acceptUnavailable) {
                    int semaphore = library.semget(library.ftok(new File(directory, name).getAbsolutePath(), 0xA1), 2, 0666);
                    PosixLibrary.SemaphoreOperation buffer = new PosixLibrary.SemaphoreOperation();
                    buffer.semOp = operation;
                    buffer.semFlg = flags;
                    try {
                        while (count-- > 0) {
                            try {
                                library.semop(semaphore, buffer, 1);
                            } catch (LastErrorException exception) {
                                if (acceptUnavailable && Native.getLastError() == PosixLibrary.EAGAIN) {
                                    break;
                                } else {
                                    throw exception;
                                }
                            }
                        }
                    } finally {
                        buffer.clear();
                    }
                }

                /**
                 * An API for interaction with POSIX systems.
                 */
                protected interface PosixLibrary extends Library {

                    /**
                     * A null signal.
                     */
                    int NULL_SIGNAL = 0;

                    /**
                     * Indicates that a process does not exist.
                     */
                    int ESRCH = 3;

                    /**
                     * Indicates that a request timed out.
                     */
                    int EAGAIN = 11;

                    /**
                     * Indicates that a semaphore's operations should be undone at process shutdown.
                     */
                    short SEM_UNDO = 0x1000;

                    /**
                     * Indicates that one should not wait for the release of a semaphore if it is not currently available.
                     */
                    short IPC_NOWAIT = 04000;

                    /**
                     * Runs the {@code getpid} command.
                     *
                     * @return The command's return value.
                     * @throws LastErrorException If an error occurred.
                     */
                    int getpid() throws LastErrorException;

                    /**
                     * Runs the {@code getuid} command.
                     *
                     * @return The command's return value.
                     * @throws LastErrorException If an error occurred.
                     */
                    int getuid() throws LastErrorException;

                    /**
                     * Runs the {@code kill} command.
                     *
                     * @param processId The target process id.
                     * @param signal    The signal to send.
                     * @return The command's return value.
                     * @throws LastErrorException If an error occurred.
                     */
                    int kill(long processId, int signal) throws LastErrorException;

                    /**
                     * Runs the {@code chmod} command.
                     *
                     * @param path The file path.
                     * @param mode The mode to set.
                     * @throws LastErrorException If an error occurred.
                     */
                    void chmod(String path, int mode) throws LastErrorException;

                    /**
                     * Runs the {@code ftok} command.
                     *
                     * @param path The file path.
                     * @param id   The id being used for creating the generated key.
                     * @return The generated key.
                     * @throws LastErrorException If an error occurred.
                     */
                    int ftok(String path, int id) throws LastErrorException;

                    /**
                     * Runs the {@code semget} command.
                     *
                     * @param key   The key of the semaphore.
                     * @param count The initial count of the semaphore.
                     * @param flags The flags to set.
                     * @return The id of the semaphore.
                     * @throws LastErrorException If an error occurred.
                     */
                    int semget(int key, int count, int flags) throws LastErrorException;

                    /**
                     * Runs the {@code semop} command.
                     *
                     * @param id        The id of the semaphore.
                     * @param operation The initial count of the semaphore.
                     * @param flags     The flags to set.
                     * @throws LastErrorException If the operation was not successful.
                     */
                    void semop(int id, SemaphoreOperation operation, int flags) throws LastErrorException;

                    /**
                     * A structure to represent a semaphore operation for {@code semop}.
                     */
                    class SemaphoreOperation extends Structure {

                        /**
                         * The semaphore number.
                         */
                        @SuppressWarnings("unused")
                        public short semNum;

                        /**
                         * The operation to execute.
                         */
                        public short semOp;

                        /**
                         * The flags being set for the operation.
                         */
                        public short semFlg;

                        @Override
                        protected List<String> getFieldOrder() {
                            return Arrays.asList("semNum", "semOp", "semFlg");
                        }
                    }
                }
            }

            /**
             * A connector implementation for a Windows environment using JNA.
             */
            class ForJnaWindowsEnvironment implements Connector {

                /**
                 * Indicates a missing user id what is not supported on Windows.
                 */
                private static final long NO_USER_ID = 0;

                /**
                 * The name of the creation mutex.
                 */
                private static final String CREATION_MUTEX_NAME = "j9shsemcreationMutex";

                /**
                 * A library to use for interacting with Windows.
                 */
                private final WindowsLibrary library;

                /**
                 * Creates a new connector for a Windows environment using JNA.
                 */
                public ForJnaWindowsEnvironment() {
                    library = Native.load("kernel32", WindowsLibrary.class, W32APIOptions.DEFAULT_OPTIONS);
                }

                /**
                 * {@inheritDoc}
                 */
                public String getTemporaryFolder() {
                    WinDef.DWORD length = new WinDef.DWORD(WinDef.MAX_PATH);
                    char[] path = new char[length.intValue()];
                    if (Kernel32.INSTANCE.GetTempPath(length, path).intValue() == 0) {
                        throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
                    }
                    return Native.toString(path);
                }

                /**
                 * {@inheritDoc}
                 */
                public long pid() {
                    return Kernel32.INSTANCE.GetCurrentProcessId();
                }

                /**
                 * {@inheritDoc}
                 */
                public long userId() {
                    return NO_USER_ID;
                }

                /**
                 * {@inheritDoc}
                 */
                public boolean isExistingProcess(long processId) {
                    WinNT.HANDLE handle = Kernel32.INSTANCE.OpenProcess(WinNT.PROCESS_QUERY_INFORMATION, false, (int) processId);
                    if (handle == null) {
                        throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
                    }
                    IntByReference exists = new IntByReference();
                    if (!Kernel32.INSTANCE.GetExitCodeProcess(handle, exists)) {
                        throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
                    }
                    return exists.getValue() == WinBase.STILL_ACTIVE;
                }

                /**
                 * {@inheritDoc}
                 */
                public long getOwnerIdOf(File file) {
                    return NO_USER_ID;
                }

                /**
                 * {@inheritDoc}
                 */
                public void setPermissions(File file, int permissions) {
                    /* do nothing */
                }

                /**
                 * {@inheritDoc}
                 */
                public void incrementSemaphore(File directory, String name, int count) {
                    AttachmentHandle handle = openSemaphore(directory, name);
                    try {
                        while (count-- > 0) {
                            if (!library.ReleaseSemaphore(handle.getHandle(), 1, null)) {
                                throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
                            }
                        }
                    } finally {
                        handle.close();
                    }
                }

                /**
                 * {@inheritDoc}
                 */
                public void decrementSemaphore(File directory, String name, int count) {
                    AttachmentHandle handle = openSemaphore(directory, name);
                    try {
                        while (count-- > 0) {
                            int result = Kernel32.INSTANCE.WaitForSingleObject(handle.getHandle(), 0);
                            switch (result) {
                                case WinBase.WAIT_ABANDONED:
                                case WinBase.WAIT_OBJECT_0:
                                    break;
                                case WinError.WAIT_TIMEOUT:
                                    return;
                                default:
                                    throw new Win32Exception(result);
                            }
                        }
                    } finally {
                        handle.close();
                    }
                }

                /**
                 * Opens a semaphore for signaling another process that an attachment is performed.
                 *
                 * @param directory The control directory.
                 * @param name      The semaphore's name.
                 * @return A handle for signaling an attachment to the target process.
                 */
                private AttachmentHandle openSemaphore(File directory, String name) {
                    WinNT.SECURITY_DESCRIPTOR securityDescriptor = new WinNT.SECURITY_DESCRIPTOR(64 * 1024);
                    try {
                        if (!Advapi32.INSTANCE.InitializeSecurityDescriptor(securityDescriptor, WinNT.SECURITY_DESCRIPTOR_REVISION)) {
                            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
                        }
                        if (!Advapi32.INSTANCE.SetSecurityDescriptorDacl(securityDescriptor, true, null, true)) {
                            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
                        }
                        WinBase.SECURITY_ATTRIBUTES securityAttributes = new WinBase.SECURITY_ATTRIBUTES();
                        try {
                            securityAttributes.dwLength = new WinDef.DWORD(securityAttributes.size());
                            securityAttributes.lpSecurityDescriptor = securityDescriptor.getPointer();
                            securityAttributes.bInheritHandle = false;
                            WinNT.HANDLE mutex = Kernel32.INSTANCE.CreateMutex(securityAttributes, false, CREATION_MUTEX_NAME);
                            if (mutex == null) {
                                int lastError = Kernel32.INSTANCE.GetLastError();
                                if (lastError == WinError.ERROR_ALREADY_EXISTS) {
                                    mutex = Kernel32.INSTANCE.OpenMutex(WinBase.MUTEX_ALL_ACCESS, false, CREATION_MUTEX_NAME);
                                    if (mutex == null) {
                                        throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
                                    }
                                } else {
                                    throw new Win32Exception(lastError);
                                }
                            }
                            int result = Kernel32.INSTANCE.WaitForSingleObject(mutex, 2000);
                            switch (result) {
                                case WinBase.WAIT_FAILED:
                                case WinError.WAIT_TIMEOUT:
                                    throw new Win32Exception(result);
                                default:
                                    try {
                                        // TODO: Not using global for now due to testing with 'old' JVM 8.
                                        String target = (directory.getAbsolutePath() + '_' + name).replaceAll("[^a-zA-Z0-9_]", "") + "_semaphore";
                                        WinNT.HANDLE parent = library.OpenSemaphoreW(WindowsLibrary.SEMAPHORE_ALL_ACCESS, false, target);
                                        if (parent == null) {
                                            parent = library.CreateSemaphoreW(null, 0, Integer.MAX_VALUE, target);
                                            if (parent == null) {
                                                throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
                                            }
                                            WinNT.HANDLE child = library.CreateSemaphoreW(null, 0, Integer.MAX_VALUE, target + "_set0");
                                            if (child == null) {
                                                throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
                                            }
                                            return new AttachmentHandle(parent, child);
                                        } else {
                                            WinNT.HANDLE child = library.OpenSemaphoreW(WindowsLibrary.SEMAPHORE_ALL_ACCESS, false, target + "_set0");
                                            if (child == null) {
                                                throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
                                            }
                                            return new AttachmentHandle(parent, child);
                                        }
                                    } finally {
                                        Kernel32.INSTANCE.ReleaseMutex(mutex);
                                    }
                            }
                        } finally {
                            securityAttributes.clear();
                        }
                    } finally {
                        securityDescriptor.clear();
                    }
                }

                /**
                 * A library for interacting with Windows.
                 */
                protected interface WindowsLibrary extends Library {

                    /**
                     * Indicates that a semaphore requires all access rights.
                     */
                    int SEMAPHORE_ALL_ACCESS = 0x1F0003;

                    /**
                     * Opens an existing semaphore.
                     *
                     * @param access        The access rights.
                     * @param inheritHandle {@code true} if the handle is inherited.
                     * @param name          The semaphore's name.
                     * @return The handle or {@code null} if the handle could not be created.
                     */
                    @SuppressWarnings("checkstyle:methodname")
                    WinNT.HANDLE OpenSemaphoreW(int access, boolean inheritHandle, String name);

                    /**
                     * Creates a new semaphore.
                     *
                     * @param securityAttributes The security attributes for the created semaphore.
                     * @param count              The initial count for the semaphore.
                     * @param maximumCount       The maximum count for the semaphore.
                     * @param name               The semaphore's name.
                     * @return The handle or {@code null} if the handle could not be created.
                     */
                    @SuppressWarnings("checkstyle:methodname")
                    WinNT.HANDLE CreateSemaphoreW(WinBase.SECURITY_ATTRIBUTES securityAttributes, long count, long maximumCount, String name);

                    /**
                     * Releases the semaphore.
                     *
                     * @param handle        The semaphore's handle.
                     * @param count         The amount with which to increase the semaphore.
                     * @param previousCount The previous count of the semaphore or {@code null}.
                     * @return {@code true} if the semaphore was successfully released.
                     */
                    @SuppressWarnings("checkstyle:methodname")
                    boolean ReleaseSemaphore(WinNT.HANDLE handle, long count, Long previousCount);
                }

                /**
                 * A handle for an attachment which is represented by a pair of handles.
                 */
                protected static class AttachmentHandle implements Closeable {

                    /**
                     * The parent handle.
                     */
                    private final WinNT.HANDLE parent;

                    /**
                     * The child handle.
                     */
                    private final WinNT.HANDLE child;

                    /**
                     * Creates a new attachment handle.
                     *
                     * @param parent The parent handle.
                     * @param child  The child handle.
                     */
                    protected AttachmentHandle(WinNT.HANDLE parent, WinNT.HANDLE child) {
                        this.parent = parent;
                        this.child = child;
                    }

                    /**
                     * Returns the handle on which signals are to be sent.
                     *
                     * @return The handle on which signals are to be sent.
                     */
                    protected WinNT.HANDLE getHandle() {
                        return child;
                    }

                    /**
                     * {@inheritDoc}
                     */
                    public void close() {
                        boolean closed;
                        try {
                            if (!Kernel32.INSTANCE.CloseHandle(child)) {
                                throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
                            }
                        } finally {
                            closed = Kernel32.INSTANCE.CloseHandle(parent);
                        }
                        if (!closed) {
                            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
                        }
                    }
                }
            }
        }
    }
}