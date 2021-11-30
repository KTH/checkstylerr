/*
 * Copyright (c) 2010, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package admin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author tmueller
 */
public class SyncTest extends AdminBaseDevTest {

    SyncTest() {
        instancesHome = TestEnv.getInstancesHome("localhost-domain1");

    }
    @Override
    protected String getTestDescription() {
        return "Tests Start-up Synchronization between the DAS and Instances.";
    }

    public static void main(String[] args) {
        new SyncTest().runTests();
    }

    private void runTests() {
        startDomain();
        testCleanupofStaleFiles();
        testStartWithDASDown();
        testConfigDirSync();
        testFullSync();
        stopDomain();
        stat.printSummary();
    }

    /*
     * This is a test for requirement SYNC-002
     */
    void testCleanupofStaleFiles() {
        final String tn = "stalefiles";
        final String cname = "syncc1";
        final String i1url = "http://localhost:18080/";
        final String i1murl = "http://localhost:14848/management/domain/";
        final String i1name = "synci1";

        // create a cluster and an instance
        report(tn + "create-cluster", asadmin("create-cluster", cname));
        report(tn + "create-local-instance1", asadmin("create-local-instance",
                "--cluster", cname, "--systemproperties",
                "HTTP_LISTENER_PORT=18080:HTTP_SSL_LISTENER_PORT=18181:IIOP_SSL_LISTENER_PORT=13800:" +
                "IIOP_LISTENER_PORT=13700:JMX_SYSTEM_CONNECTOR_PORT=17676:IIOP_SSL_MUTUALAUTH_PORT=13801:" +
                "JMS_PROVIDER_PORT=18686:ASADMIN_LISTENER_PORT=14848", i1name));

        // deploy an application to the cluster (before the instance is started)
        /*
         File webapp = new File("resources", "helloworld.war");
        report(tn + "deploy", asadmin("deploy", "--target", cname,
                "--name", "helloworld1", webapp.getAbsolutePath()));
        */
        // create a file in docroot
        File foo = new File(TestEnv.getDomainDocRoot(), "foo.html");
        try {
            FileWriter fw = new FileWriter(foo);
            fw.write("<html><body>Foo file</body></html>");
            fw.close();
        }
        catch (IOException ioe) {
            report(tn + "file-create", false);
            ioe.printStackTrace();
        }

        // start the instance
        report(tn + "start-local-instance1", asadmin("start-local-instance", i1name));

        // check that the instance, the app, and the file are there
        report(tn + "list-instances", asadmin("list-instances"));
        report(tn + "getindex1", matchString("GlassFish Server", getURL(i1url)));
        report(tn + "getfoo", matchString("Foo file", getURL(i1url + "foo.html")));
        //report(tn + "getapp1", matchString("Hello", getURL(i1url + "helloworld1/hi.jsp")));

        // stop the instance
        report(tn + "stop-local-instance1", asadmin("stop-local-instance", i1name));

        // undeploy
        //report(tn + "undeploy", asadmin("undeploy", "--target", cname, "helloworld1"));
        foo.delete();
        // touch the DAS domain.xml file so that synchronization still occurs
        this.getDASDomainXML().setLastModified(System.currentTimeMillis());

         // start the instance
        report(tn + "start-local-instance1a", asadmin("start-local-instance", i1name));

        // make sure the app and file are gone
        //report(tn + "get-del-app1", !matchString("Hello", getURL(i1url + "helloworld1/hi.jsp")));
        report(tn + "get-del-foo", !matchString("Foo file", getURL(i1url + "foo.html")));

        // stop the instance
        report(tn + "stop-local-instance1a", asadmin("stop-local-instance", i1name));

        // delete the instances and the cluster
        report(tn + "delete-local-instance1", asadmin("delete-local-instance", i1name));
        report(tn + "delete-cluster", asadmin("delete-cluster", cname));
    }

    /*
     * This is a test for requirement SYNC-003
     */
    void testStartWithDASDown() {
        final String tn = "dasdown";
        final String cname = "syncc2";
        final String i1url = "http://localhost:18080/";
        final String i1murl = "http://localhost:14848/management/domain/";
        final String i1name = "synci2";

        // create a cluster an instance
        report(tn + "create-cluster", asadmin("create-cluster", cname));
        report(tn + "create-local-instance1", asadmin("create-local-instance",
                "--cluster", cname, "--systemproperties",
                "HTTP_LISTENER_PORT=18080:HTTP_SSL_LISTENER_PORT=18181:IIOP_SSL_LISTENER_PORT=13800:" +
                "IIOP_LISTENER_PORT=13700:JMX_SYSTEM_CONNECTOR_PORT=17676:IIOP_SSL_MUTUALAUTH_PORT=13801:" +
                "JMS_PROVIDER_PORT=18686:ASADMIN_LISTENER_PORT=14848", i1name));

        // deploy an application to the cluster (before instance is started)
        /*
        File webapp = new File("resources", "helloworld.war");
         report(tn + "deploy", asadmin("deploy", "--target", cname,
                "--name", "helloworld2", webapp.getAbsolutePath()));
        */

        // start the instance
        report(tn + "start-local-instance1", asadmin("start-local-instance", i1name));

        // check that the instance and the app are there
        report(tn + "list-instances", asadmin("list-instances"));
        report(tn + "getindex1", matchString("GlassFish Server", getURL(i1url)));
        //report(tn + "getapp1", matchString("Hello", getURL(i1url + "helloworld2/hi.jsp")));

        // stop the instance
        report(tn + "stop-local-instance1", asadmin("stop-local-instance", i1name));

        stopDomain();

         // start the instance
        report(tn + "start-local-instance1a", asadmin("start-local-instance", i1name));

        // make sure the instance and app are still there




        String s = getURL(i1url);
        boolean b = matchString("GlassFish Server", s);
        String testname = tn + "getindex1a";

        if(!b)
            TestUtils.writeCommandToDebugLog(testname + ": getURL returned: \n" + s);

        report(testname, b);





        //report(tn + "getapp1a", matchString("Hello", getURL(i1url + "helloworld2/hi.jsp")));

        // stop the instance
        report(tn + "stop-local-instance1a", asadmin("stop-local-instance", i1name));

        startDomain();

        // delete the instances and the cluster
        //report(tn + "undeploy", asadmin("undeploy", "--target", cname, "helloworld2"));
        report(tn + "delete-local-instance1", asadmin("delete-local-instance", i1name));
        report(tn + "delete-cluster", asadmin("delete-cluster", cname));
    }

    void testFullSync() {
        final String tn = "fullsync-";
        final String cname = "syncc3";
        final String i1url = "http://localhost:18080/";
        final String i1murl = "http://localhost:14848/management/domain/";
        final String i1name = "synci3";

        // create a cluster and an instance
        report(tn + "create-cluster", asadmin("create-cluster", cname));
        report(tn + "create-local-instance1", asadmin("create-local-instance",
                "--cluster", cname, "--systemproperties",
                "HTTP_LISTENER_PORT=18080:HTTP_SSL_LISTENER_PORT=18181:IIOP_SSL_LISTENER_PORT=13800:" +
                "IIOP_LISTENER_PORT=13700:JMX_SYSTEM_CONNECTOR_PORT=17676:IIOP_SSL_MUTUALAUTH_PORT=13801:" +
                "JMS_PROVIDER_PORT=18686:ASADMIN_LISTENER_PORT=14848", i1name));

        // create a file in docroot
        File foo = new File(TestEnv.getDomainDocRoot(), "foo.html");

        try {
            FileWriter fw = new FileWriter(foo);
            fw.write("<html><body>Foo file</body></html>");
            fw.close();
        }
        catch (IOException ioe) {
            report(tn + "file-create", false);
            ioe.printStackTrace();
        }

        // start the instance
        report(tn + "start-local-instance1", asadmin("start-local-instance", i1name));

        // check that the instance and the file are there
        report(tn + "list-instances", asadmin("list-instances"));
        report(tn + "getindex1", matchString("GlassFish Server", getURL(i1url)));
        report(tn + "getfoo", matchString("Foo file", getURL(i1url + "foo.html")));

        // stop the instance
        report(tn + "stop-local-instance1", asadmin("stop-local-instance", i1name));

        // delete the file from the instance
        File fooOnInstance = new File(instancesHome, i1name + "/docroot/foo.html");
        report(tn + "instance-file-exists", fooOnInstance.exists());
        fooOnInstance.delete();
        report(tn + "del-instance-file", !fooOnInstance.exists());

         // start the instance with --fullsync
        report(tn + "start-local-instance1a", asadmin("start-local-instance", "--sync=full", i1name));

        // make sure the file is back
        report(tn + "getfoo1", matchString("Foo file", getURL(i1url + "foo.html")));
        report(tn + "instance-file-exists2", fooOnInstance.exists());

        // stop the instance
        report(tn + "stop-local-instance1b", asadmin("stop-local-instance", i1name));

        // delete the instances and the cluster
        report(tn + "delete-local-instance1", asadmin("delete-local-instance", i1name));
        report(tn + "delete-cluster", asadmin("delete-cluster", cname));
        foo.delete();
    }

    void testConfigDirSync() {
        final String tn = "config-dir-sync";
        final String i1name = "synci4";

        // create a cluster and an instance
        report(tn + "create-instance1", asadmin("create-instance",
                "--node", "localhost-domain1", i1name));

        // synchronize the instance without starting it
        report(tn + "_synchronize-instance1",
            asadmin("_synchronize-instance", i1name));

        // create a file in the config-specific docroot directory
        File foo = new File(TestEnv.getConfigSpecificDocRoot(i1name), "foo.html");
        try {
            FileWriter fw = new FileWriter(foo, true);
            fw.write("<html><body>Foo file</body></html>");
            fw.close();
        } catch (IOException ioe) {
            report(tn + "file-create", false);
            ioe.printStackTrace();
        }

        // touch domain.xml
        File dxml = TestEnv.getDomainXml();
        touch(dxml);

        // synchronize the instance without starting it
        report(tn + "_synchronize-instance1-1",
            asadmin("_synchronize-instance", i1name));

        // is the file on the instance?
        File fooOnInstance = new File(TestEnv.getInstanceDir("localhost-domain1", i1name),
                "config/" + i1name + "-config/docroot/foo.html");
        report(tn + "instance-file-exists", fooOnInstance.exists());

        // now remove the file and make sure it disappears from the instance
        foo.delete();
        touch(dxml);

        // synchronize the instance without starting it
        report(tn + "_synchronize-instance1-2",
            asadmin("_synchronize-instance", i1name));

        // is the file on the instance?
        report(tn + "instance-file-does-not-exist", !fooOnInstance.exists());

        // delete the instance
        report(tn + "delete-instance1", asadmin("delete-instance", i1name));
    }

    /**
     * Touch the file, making sure the last modified time changes.
     */
    private static void touch(File f) {
        long mod = f.lastModified();
        long time = System.currentTimeMillis();
        f.setLastModified(time);
        if (f.lastModified() != mod)        // did it change?
            return;
        try {
            Thread.sleep(1000);             // wait for a second
        } catch (InterruptedException ex) {
        }
        time = System.currentTimeMillis();
        f.setLastModified(time);
        if (f.lastModified() != mod)        // did it change?
            return;
        throw new RuntimeException("Can't touch file: " + f);
    }

    private final File instancesHome;

}
