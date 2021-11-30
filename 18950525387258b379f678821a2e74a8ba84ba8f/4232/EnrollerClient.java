/*
 * Copyright (c) 2001, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.s1peqe.loadbalancing.client;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.util.*;
import com.sun.s1peqe.ejb.bmp.enroller.ejb.*;
import com.sun.ejte.ccl.reporter.SimpleReporterAdapter;
import java.util.LinkedList;
import java.util.Vector;
import java.net.InetAddress;

import java.net.MalformedURLException;
import java.net.UnknownHostException;

import org.glassfish.jndi.cosnaming.IiopUrl;

public class EnrollerClient {

    private SimpleReporterAdapter stat =
        new SimpleReporterAdapter("appserv-tests");

    public static void main(String[] args) {
        EnrollerClient client = new EnrollerClient();

        // run the tests
        client.runTestClient(args);
    }

    public void runTestClient(String[] args) {
        try{
            Hashtable env = new Hashtable();
            stat.addDescription("Testing loadbalancing app ...");

            test01(env, args);
            stat.printSummary("loadbalancingAppID");
        } catch (Exception ex) {
            System.out.println("Exception in runTestClient: " + ex.toString());
            ex.printStackTrace();
        }
    }


    private void test01(Hashtable env, String[] args) {

        try {
              env.put("java.naming.factory.initial",
                  "com.sun.appserv.naming.S1ASCtxFactory");
            Context initial = new InitialContext(env);
            System.out.println("Looking up MEJB...");
            Object objref = initial.lookup("ejb/mgmt/MEJB");
            System.out.println("Looked up ejb/mgmt/MEJB with context 0");

            String jndi = "";
            if (args.length == 3 && args[2].equals("standalone")) {
                jndi = "ejb/MyStudent";
            } else jndi = "java:comp/env/ejb/SimpleStudent";
            Context initial1 = new InitialContext(env);
            objref = initial1.lookup(jndi);
            System.out.println("Looked up " + jndi + " with context 1");
            StudentHome sHome =
              (StudentHome) PortableRemoteObject.narrow(objref,
                                                        StudentHome.class);
            Student denise = sHome.create("823", "Denise Smith");

            Context initial2 = new InitialContext(env);
            objref= initial2.lookup(jndi);
            System.out.println("Looked up " + jndi + " with context 2");
            sHome =
                (StudentHome) PortableRemoteObject.narrow(objref,
                                                          StudentHome.class);
            Student jim = sHome.create("828", "Jim Goodyear");


            Context initial3 = new InitialContext(env);
            objref= initial3.lookup("ejb/MyStudent");
            System.out.println("Looked up ejb/MyStudent with context 3");
            sHome =
              (StudentHome) PortableRemoteObject.narrow(objref,
                                                        StudentHome.class);
            Student scott = sHome.create("826", "Scott White");

            Context initial4 = new InitialContext(env);
            objref= initial4.lookup("ejb/MyStudent");
            System.out.println("Looked up ejb/MyStudent with context 4");
            sHome =
              (StudentHome) PortableRemoteObject.narrow(objref,
                                                        StudentHome.class);
            Student john = sHome.create("844", "John Wayne");

            Context initial5 = new InitialContext(env);
            objref = initial5.lookup("corbaname:iiop:" + args[0] + ":" + args[1] + "#ejb/MyStudent");
            System.out.println("Looked up corbaname:iiop:" + args[0] + ":" + args[1] + "#ejb/MyStudent");
            sHome =
              (StudentHome) PortableRemoteObject.narrow(objref,
                                                        StudentHome.class);
            Student tim = sHome.create("555", "Tim Duncan");

            System.out.println("**************************************");
            System.out.println("Loadbalancing for java:comp names :");
            String[] deniseEndpoint = denise.getServerHostPort();
            System.out.println("Denise Smith created on " +
                               deniseEndpoint[0] + ":" + deniseEndpoint[1]);
            String[] jimEndpoint = jim.getServerHostPort();
            System.out.println("Jim Goodyear created on " +
                               jimEndpoint[0] + ":" + jimEndpoint[1]);
            System.out.println("**************************************");

            System.out.println("**************************************");
            System.out.println("Loadbalancing for global jndi names :");
            String[] scottEndpoint = scott.getServerHostPort();
            System.out.println("Scott White created on " +
                               scottEndpoint[0] + ":" + scottEndpoint[1]);
            String[] johnEndpoint = john.getServerHostPort();
            System.out.println("John Wayne created on " +
                               johnEndpoint[0] + ":" + johnEndpoint[1]);
            System.out.println("**************************************");


            System.out.println("**************************************");
            String[] timEndpoint = tim.getServerHostPort();
            System.out.println("Tim Duncan created on " +
                               timEndpoint[0] + ":" + timEndpoint[1]);
            System.out.println("**************************************");
            boolean pass = false;
            String[] addr = getAddressPortList(args[0] + ":" + args[1]);
            for (int j = 0; j < addr.length; j++) {
System.out.println("addr[j]..." + addr[j]);
              if ((addr[j].trim()).equals((timEndpoint[0] + ":" + timEndpoint[1]).trim())) {
              System.out.println("found right address..." + addr[j]);
              pass = true;
              break;
              }
            }
            if (!deniseEndpoint[0].equals(jimEndpoint[0]) &&
                !johnEndpoint[0].equals(scottEndpoint[0]) && pass == true) {
              System.out.println("timEndpoint[0] =" + timEndpoint[0]);
                System.out.println("LOADBALANCING SUCCESSFUL!!");
                stat.addStatus("load balancing", stat.PASS);
            } else if (!deniseEndpoint[1].equals(jimEndpoint[1]) &&
                       !johnEndpoint[1].equals(scottEndpoint[1]) && pass == true) {
                System.out.println("LOADBALANCING SUCCESSFUL!!");
                stat.addStatus("load balancing", stat.PASS);
            }

        } catch (Exception ex) {
            stat.addStatus("load balancing", stat.FAIL);
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
    }

  /**
   * following methods (over-loaded) for getting all IP addresses corresponding
   * to a particular host.
   * (multi-homed hosts).
   */
    private String [] getAddressPortList(String host) {
        Vector addressPortVector = new Vector();
        try {
            IiopUrl url = new IiopUrl("iiop://"+host);
            String [] apList = getAddressPortList(url);
            for (int j=0; j<apList.length; j++) {
              addressPortVector.addElement(apList[j]);
            }
        } catch (MalformedURLException me) {
          me.printStackTrace();
        }

        String [] ret = new String[addressPortVector.size()];
        for (int i=0; i<ret.length; i++) {
            ret[i] = (String)addressPortVector.elementAt(i);
        }
        // We return a list of <IP ADDRESS>:<PORT> values
        return ret;
    }

    private String [] getAddressPortList(IiopUrl iiopUrl) {
        // Pull out the host name and port
        IiopUrl.Address iiopUrlAddress =
                (IiopUrl.Address)(iiopUrl.getAddresses().elementAt(0));
        String host = iiopUrlAddress.host;
        int portNumber = iiopUrlAddress.port;
        String port = Integer.toString(portNumber);
        // We return a list of <IP ADDRESS>:<PORT> values
        return getAddressPortList(host, port);
    }

    private String [] getAddressPortList(String host, String port) {
        // Get the ip addresses corresponding to the host
        try {
            InetAddress [] addresses = InetAddress.getAllByName(host);
            String[] ret = new String[addresses.length];
            for (int i = 0; i < addresses.length; i++) {
                ret[i] = addresses[i].getHostAddress() + ":" + port;
            }
            // We return a list of <IP ADDRESS>:<PORT> values
            return ret;
        } catch (UnknownHostException ukhe) {
          ukhe.printStackTrace();
            return null;
        }
    }
}
