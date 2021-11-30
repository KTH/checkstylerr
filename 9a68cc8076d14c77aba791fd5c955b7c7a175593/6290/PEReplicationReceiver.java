/*
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
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

/*
 * PEReplicationReceiver.java
 *
 * Created on January 30, 2006, 11:33 AM
 *
 */

package com.sun.enterprise.web;

import org.apache.catalina.LifecycleException;

/**
 *
 * @author Larry White
 */
public class PEReplicationReceiver implements ReplicationReceiver {

    /** Creates a new instance of PEReplicationReceiver */
    public PEReplicationReceiver(EmbeddedWebContainer embedded) {
    }

    public void init() {
        //no-op
    }

    public void stop() throws LifecycleException {
        //no-op
    }

}
