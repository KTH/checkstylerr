/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.s1asdev.admin.ee.synchronization.api.deployment;

import jakarta.ejb.SessionBean;
import jakarta.ejb.SessionContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.*;
import javax.rmi.PortableRemoteObject;
import java.io.File;
import java.io.IOException;

import java.rmi.RemoteException;

import com.sun.enterprise.ee.synchronization.api.SynchronizationClient;
import com.sun.enterprise.ee.synchronization.api.SynchronizationFactory;
import com.sun.enterprise.ee.synchronization.SynchronizationException;
import com.sun.enterprise.ee.synchronization.api.ApplicationsMgr;
import com.sun.enterprise.server.ApplicationServer;
import com.sun.enterprise.config.ConfigContext;

public class SynchronizationEJB
    implements SessionBean
{
    private SessionContext context;
    private Context initialCtx;

    public void ejbCreate() {
    }

    public boolean getFile(String instanceName, String sourceFile,
    String destLoc) {
        try {
            ConfigContext ctx = ApplicationServer.getServerContext().
                    getConfigContext();
            ApplicationsMgr appSynchMgr = SynchronizationFactory.
                createSynchronizationContext(ctx).getApplicationsMgr();

            appSynchMgr.synchronize(sourceFile);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean get(String instanceName, String name, String type,
        String destLoc) {
        try {
            ConfigContext ctx = ApplicationServer.getServerContext().
                    getConfigContext();
            ApplicationsMgr appSynchMgr = SynchronizationFactory.
                createSynchronizationContext(ctx).getApplicationsMgr();

            if (type == null) {
                return false;
            }

            if ( "J2EEApplication".equals(type) ) {
                appSynchMgr.synchronizeJ2EEApplication(name);
                return true;
            }

            if ( "WebModule".equals(type) ) {
                appSynchMgr.synchronizeWebModule(name);
                return true;
            }

            if ( "EJBModule".equals(type) ) {
                appSynchMgr.synchronizeEJBModule(name);
                return true;
            }

            if ( "ConnectorModule".equals(type) ) {
                appSynchMgr.synchronizeConnectorModule(name);
                return true;
            }

            if ( "AppclientModule".equals(type) ) {
                appSynchMgr.synchronizeAppclientModule(name);
                return true;
            }

            if ( "LifecycleModule".equals(type) ) {
                appSynchMgr.synchronizeLifecycleModule(name);
                return true;
            }

            return false;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean putFile(String instanceName, String sourceFile,
    String destDir)  {
        try {
            SynchronizationClient sc =
              SynchronizationFactory.createSynchronizationClient( instanceName);
            sc.connect();
            String s = sc.put(sourceFile, destDir);
            sc.disconnect();
            System.out.println("Upload file at " + s);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setSessionContext(SessionContext sc) {
        this.context = sc;
        try {
            this.initialCtx = new InitialContext();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void ejbRemove() {}

    public void ejbActivate() {
        System.out.println ("In SFSB.ejbActivate() " );
    }

    public void ejbPassivate() {
        System.out.println ("In SFSB.ejbPassivate() ");
    }
}
