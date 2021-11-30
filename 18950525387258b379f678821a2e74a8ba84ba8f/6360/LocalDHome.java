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

package cascadeDelete;

import jakarta.ejb.*;

/**
 * Created Dec 23, 2002 12:43:03 PM
 * Code generated by the Forte For Java EJB Builder
 * @author mvatkina
 */

public interface LocalDHome extends jakarta.ejb.EJBLocalHome {

    public cascadeDelete.LocalD findByPrimaryKey(java.lang.Integer aKey)
    throws jakarta.ejb.FinderException;

    public java.util.Collection findAll() throws jakarta.ejb.FinderException;

    public java.util.Collection findInRange(java.lang.Integer p0, java.lang.Integer p1) throws jakarta.ejb.FinderException;

}
