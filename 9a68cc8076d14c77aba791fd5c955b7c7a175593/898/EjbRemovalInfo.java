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

package org.glassfish.ejb.deployment.descriptor;

import java.io.Serializable;

import com.sun.enterprise.deployment.MethodDescriptor;

/**
 * Contains information about a stateful session bean remove method.
 */

public class EjbRemovalInfo implements Serializable
{
    private MethodDescriptor removeMethod;
    private boolean retainIfException = false;
    private boolean retainIfExceptionSet = false;

    public MethodDescriptor getRemoveMethod() {
        return removeMethod;
    }

    public void setRemoveMethod(MethodDescriptor method) {
        removeMethod = method;
    }

    public boolean getRetainIfException() {
        return retainIfException;
    }

    public void setRetainIfException(boolean flag) {
        retainIfException = flag;
        retainIfExceptionSet = true;
    }

    public boolean isRetainIfExceptionSet() {
        return retainIfExceptionSet;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("remove method = " + removeMethod + "\t");
        sb.append("retainIfException = " + retainIfException);
        return sb.toString();
    }
}
