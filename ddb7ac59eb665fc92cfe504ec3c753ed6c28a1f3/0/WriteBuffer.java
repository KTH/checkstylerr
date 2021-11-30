/*******************************************************************************
 * Copyright (c) 2017-2019, org.smartboot. All rights reserved.
 * project name: smart-socket
 * file name: WriteBuffer.java
 * Date: 2019-12-31
 * Author: sandao (zhengjunweimail@163.com)
 *
 ******************************************************************************/

package org.smartboot.socket.transport;

import org.smartboot.socket.buffer.BufferPage;
import org.smartboot.socket.buffer.VirtualBuffer;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

/**
 * 包装当前会话分配到的虚拟Buffer,提供流式操作方式
 *
 * @author 三刀
 * @version V1.0 , 2018/11/8
 */

public final class WriteBuffer extends OutputStream {
    /**
     * 存储已就绪待输出的数据
     */
    private final VirtualBuffer[] items;
    /**
     * 同步锁
     */
    private final ReentrantLock lock = new ReentrantLock();
    private final ReentrantLock bufferLock = new ReentrantLock();
    /**
     * Condition for waiting puts
     */
    private final Condition notFull = lock.newCondition();

    /**
     * 为当前 WriteBuffer 提供数据存放功能的缓存页
     */
    private final BufferPage bufferPage;
    /**
     * 缓冲区数据刷新Function
     */
    private final Consumer<WriteBuffer> consumer;
    /**
     * 默认内存块大小
     */
    private final int chunkSize;
    /**
     * items 读索引位
     */
    private int takeIndex;
    /**
     * items 写索引位
     */
    private int putIndex;
    /**
     * items 中存放的缓冲数据数量
     */
    private int count;
    /**
     * 暂存当前业务正在输出的数据,输出完毕后会存放到items中
     */
    private VirtualBuffer writeInBuf;
    /**
     * 当前WriteBuffer是否已关闭
     */
    private boolean closed = false;
    /**
     * 辅助8字节以内输出的缓存组数
     */
    private byte[] cacheByte;


    WriteBuffer(BufferPage bufferPage, Consumer<WriteBuffer> consumer, int chunkSize, int capacity) {
        this.bufferPage = bufferPage;
        this.consumer = consumer;
        this.items = new VirtualBuffer[capacity];
        this.chunkSize = chunkSize;
    }

    /**
     * 按照{@link OutputStream#write(int)}规范：要写入的字节是参数 b 的八个低位。 b 的 24 个高位将被忽略。
     * <br/>
     * 而使用该接口时容易传入非byte范围内的数据，接口定义与实际使用出现歧义的可能性较大，故建议废弃该方法，选用{@link WriteBuffer#writeByte(byte)}。
     *
     * @param b 输出字节
     * @deprecated
     */
    @Override
    public void write(int b) {
        writeByte((byte) b);
    }


    /**
     * 输出一个short类型的数据
     *
     * @param v short数值
     * @throws IOException IO异常
     */
    public void writeShort(short v) throws IOException {
        initCacheBytes();
        cacheByte[0] = (byte) ((v >>> 8) & 0xFF);
        cacheByte[1] = (byte) (v & 0xFF);
        write(cacheByte, 0, 2);
    }

    /**
     * @param b 待输出数值
     * @see #write(int)
     */
    public void writeByte(byte b) {
        bufferLock.lock();
        try {
            if (writeInBuf == null) {
                writeInBuf = bufferPage.allocate(chunkSize);
            }
            writeInBuf.buffer().put(b);
            flushWriteBuffer(false);
        } finally {
            bufferLock.unlock();
        }
    }

    private void flushWriteBuffer(boolean forceFlush) {
        if (!forceFlush && writeInBuf.buffer().hasRemaining()) {
            return;
        }
        consumer.accept(this);
        if (writeInBuf == null) {
            return;
        }
        writeInBuf.buffer().flip();
        VirtualBuffer virtualBuffer = writeInBuf;
        writeInBuf = null;
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
                //防止因close诱发内存泄露
                if (closed) {
                    virtualBuffer.clean();
                    return;
                }
            }

            items[putIndex] = virtualBuffer;
            if (++putIndex == items.length) {
                putIndex = 0;
            }
            count++;
        } catch (InterruptedException e1) {
            throw new RuntimeException(e1);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 输出int数值,占用4个字节
     *
     * @param v int数值
     * @throws IOException IO异常
     */
    public void writeInt(int v) throws IOException {
        initCacheBytes();
        cacheByte[0] = (byte) ((v >>> 24) & 0xFF);
        cacheByte[1] = (byte) ((v >>> 16) & 0xFF);
        cacheByte[2] = (byte) ((v >>> 8) & 0xFF);
        cacheByte[3] = (byte) (v & 0xFF);
        write(cacheByte, 0, 4);
    }


    /**
     * 输出long数值,占用8个字节
     *
     * @param v long数值
     * @throws IOException IO异常
     */
    public void writeLong(long v) throws IOException {
        initCacheBytes();
        cacheByte[0] = (byte) ((v >>> 56) & 0xFF);
        cacheByte[1] = (byte) ((v >>> 48) & 0xFF);
        cacheByte[2] = (byte) ((v >>> 40) & 0xFF);
        cacheByte[3] = (byte) ((v >>> 32) & 0xFF);
        cacheByte[4] = (byte) ((v >>> 24) & 0xFF);
        cacheByte[5] = (byte) ((v >>> 16) & 0xFF);
        cacheByte[6] = (byte) ((v >>> 8) & 0xFF);
        cacheByte[7] = (byte) (v & 0xFF);
        write(cacheByte, 0, 8);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        if (writeInBuf == null) {
            int bufferSize = Math.max(chunkSize, len);
            VirtualBuffer virtualBuffer = bufferPage.allocate(bufferSize);
            int writeSize = writeToBuffer(virtualBuffer, b, off, len);
            writeInBuf = virtualBuffer;
            if (bufferSize > writeSize) {
                return;
            }
            off += writeSize;
            len -= writeSize;
        }

        bufferLock.lock();
        try {
            if (writeInBuf != null) {
                flushWriteBuffer(false);
            }
            while (len > 0) {
                if (writeInBuf == null) {
                    writeInBuf = bufferPage.allocate(Math.max(chunkSize, len));
                }
                int writeSize = writeToBuffer(writeInBuf, b, off, len);
                off += writeSize;
                len -= writeSize;
                flushWriteBuffer(false);
            }
        } finally {
            bufferLock.unlock();
        }
    }

    private int writeToBuffer(VirtualBuffer writeInBuf, byte[] b, int off, int len) throws IOException {
        ByteBuffer writeBuffer = writeInBuf.buffer();
        if (closed) {
            writeInBuf.clean();
            throw new IOException("writeBuffer has closed");
        }
        int writeSize = Math.min(writeBuffer.remaining(), len);
        writeBuffer.put(b, off, writeSize);
        return writeSize;
    }

    public void write(ByteBuffer buffer) throws IOException {
        write(VirtualBuffer.wrap(buffer));
    }

    public void write(VirtualBuffer virtualBuffer) throws IOException {
        bufferLock.lock();
        try {
            if (writeInBuf != null && !virtualBuffer.buffer().isDirect() && writeInBuf.buffer().remaining() > virtualBuffer.buffer().remaining()) {
                writeInBuf.buffer().put(virtualBuffer.buffer());
                virtualBuffer.clean();
            } else {
                if (writeInBuf != null) {
                    flushWriteBuffer(true);
                }
                virtualBuffer.buffer().compact();
                writeInBuf = virtualBuffer;
            }
            flushWriteBuffer(false);
        } finally {
            bufferLock.unlock();
        }
    }

    /**
     * 初始化8字节的缓存数值
     */
    private void initCacheBytes() {
        if (cacheByte == null) {
            cacheByte = new byte[8];
        }
    }

    /**
     * 写入内容并刷新缓冲区。在{@link org.smartboot.socket.MessageProcessor#process(AioSession, Object)}执行的write操作可无需调用该方法，业务执行完毕后框架本身会自动触发flush。
     * 调用该方法后数据会及时的输出到对端，如果再循环体中通过该方法往某个通道中写入数据将无法获得最佳性能表现，
     *
     * @param b 待输出数据
     * @throws IOException 如果发生 I/O 错误
     */
    public void writeAndFlush(byte[] b) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        }
        writeAndFlush(b, 0, b.length);
    }

    /**
     * @param b   待输出数据
     * @param off b的起始位点
     * @param len 从b中输出的数据长度
     * @throws IOException 如果发生 I/O 错误
     * @see WriteBuffer#writeAndFlush(byte[])
     */
    public void writeAndFlush(byte[] b, int off, int len) throws IOException {
        write(b, off, len);
        flush();
    }

    @Override
    public void flush() {
        if (closed) {
            throw new RuntimeException("OutputStream has closed");
        }
        if (this.count > 0 || writeInBuf != null) {
            consumer.accept(this);
        }
    }

    @Override
    public void close() {
        if (closed) {
            return;
        }
        lock.lock();
        try {
            flush();
            closed = true;
            VirtualBuffer byteBuf;
            while ((byteBuf = poll()) != null) {
                byteBuf.clean();
            }
        } finally {
            lock.unlock();
        }
        bufferLock.lock();
        try {
            if (writeInBuf != null) {
                writeInBuf.clean();
                writeInBuf = null;
            }
        } finally {
            bufferLock.unlock();
        }
    }


    /**
     * 是否存在待输出的数据
     *
     * @return true:有,false:无
     */
    boolean isEmpty() {
        return count == 0 && writeInBuf == null;
    }


    /**
     * 获取并移除当前缓冲队列中头部的VirtualBuffer
     *
     * @return 待输出的VirtualBuffer
     */
    VirtualBuffer pollQueue() {
        if (count == 0) {
            return null;
        }
        lock.lock();
        try {
            VirtualBuffer x = items[takeIndex];
            items[takeIndex] = null;
            if (++takeIndex == items.length) {
                takeIndex = 0;
            }
            if (count-- == items.length) {
                notFull.signal();
            }
            return x;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取并移除当前缓冲队列中头部的VirtualBuffer
     *
     * @return 待输出的VirtualBuffer
     */
    VirtualBuffer poll() {
        if (count > 0) {
            return pollQueue();
        }
        if (writeInBuf == null || !bufferLock.tryLock()) {
            return null;
        }
        try {
            if (writeInBuf != null) {
                writeInBuf.buffer().flip();
                VirtualBuffer buffer = writeInBuf;
                writeInBuf = null;
                return buffer;
            } else {
                return null;
            }
        } finally {
            bufferLock.unlock();
        }
    }

}