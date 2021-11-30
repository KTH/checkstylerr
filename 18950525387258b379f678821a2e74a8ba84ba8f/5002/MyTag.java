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

package taglib;

import java.io.IOException;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

public class MyTag extends TagSupport {

    private String[] args;

    public int doEndTag() throws JspException {

        JspWriter jsw = pageContext.getOut();

        try {
            jsw.print("ARGS:");
            for (int i=0; args!=null && i<args.length; i++) {
                jsw.print(args[i]);
            }
        } catch (IOException ioe) {
            throw new JspException(ioe.toString(), ioe);
        }

        return EVAL_PAGE;
    }

    public void setArray(String[] args) {
        this.args = args;
    }
}
