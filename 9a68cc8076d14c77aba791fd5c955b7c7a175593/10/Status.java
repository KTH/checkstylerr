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
 * Status.java
 *
 * Created on March 27, 2004, 10:40 PM
 */

package com.sun.enterprise.backup;

import com.sun.enterprise.util.io.FileUtils;
import java.io.*;
import java.util.*;
import java.util.zip.*;

import com.sun.appserv.server.util.Version;

/**
 *
 * @author  Byron Nevins
 */
class Status {

    String write(BackupRequest request) {
        props = new Properties();
        File backupFileDir = null;
        if (request.configOnly) {
            backupFileDir = new File(request.domainDir, Constants.CONFIG_DIR) ;
        } else {
            backupFileDir = request.domainDir;
        }
        statusFile = new File(backupFileDir, Constants.PROPS_FILENAME);

        FileOutputStream out = null;
        try {
            setProps(request);

            out = new FileOutputStream(statusFile);
            props.store(out, Constants.PROPS_HEADER);
            return propsToString(false);
        } catch(Exception e) {
            return StringHelper.get("backup-res.CantWriteStatus", statusFile);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch(IOException ex) {}
            }
        }
    }

    /**
     * @param file Either a zip file that contains backup.properties -- or
     * backup.properties itself.  terse is automatically set to true.
     * @return a String summary of the backup
     */
    String read(File file) {
        return read(file, true);
    }

    /**
     * @param file Either a zip file that contains backup.properties -- or
     * backup.properties itself.
     * @param terse if true, give a short summary
     * @return a String summary of the backup
     */
    String read(File file, boolean terse) {
        props = null;

        setPropsFromFile(file);
        if(props == null) {
            return badStatusFileMessage(file);
        }

        return propsToString(terse);
    }

    public boolean loadProps(File file) {

        // props is a class variable.
        props = null;
        setPropsFromFile(file);

        if (props == null)
            return false;

        return true;
    }

    /**
     * open the zip file, parse the status file and return the timestamp
     * of when it was created.
     */
    long getInternalTimestamp(File f) {
        props = null;
        setPropsFromFile(f);

        try {
            String s = props.getProperty(Constants.PROPS_TIMESTAMP_MSEC);
            return Long.parseLong(s);
        }
        catch(Exception e) {
            LoggerHelper.warning(badStatusFileMessage(f));
            return 0;
        }
    }

    void delete() {
        if(statusFile != null && !statusFile.delete()) {
            // TBD warning message
            statusFile.deleteOnExit();
        }
    }

    String getDomainName() {
        if(props == null)
            return null;

        return props.getProperty(Constants.PROPS_DOMAIN_NAME);
    }

    String getTimeStamp() {
        if(props == null)
            return null;

        return props.getProperty(Constants.PROPS_TIMESTAMP_HUMAN);
    }

    String getUserName() {
        if(props == null)
            return null;

        return props.getProperty(Constants.PROPS_USER_NAME);
    }

    // Return the full path to the backup file.
    String getBackupPath() {
        if(props == null)
            return null;

        try {
            File f = new File(props.getProperty(Constants.PROPS_BACKUP_FILE));

            return f.getAbsolutePath();
        } catch (NullPointerException e) {
            return null;
        }
    }

    // Return the filename portion of the path.
    String getFileName() {
        if(props == null)
            return null;

        try {
            File f = new File(props.getProperty(Constants.PROPS_BACKUP_FILE));

            return f.getName();
        } catch (NullPointerException e) {
            return null;
        }
    }

    String getBackupConfigName(){
       if(props == null)
            return "";

        return props.getProperty(Constants.BACKUP_CONFIG, "");
    }

    String getBackupType(){
       if(props == null)
            return "";

        return props.getProperty(Constants.PROPS_TYPE, "");
    }

    ///////////////////////////////////////////////////////////////////////////
    //////  PRIVATE METHODS AND DATA    ///////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    /**
     * @param file Either a zip file that contains backup.properties -- or
     * backup.properties itself.
     * @param terse if true, give a short summary
     * @return a String summary of the backup
     */
    private void setPropsFromFile(File file) {
        props = null;
        ZipInputStream zis = null;

        if(file.getName().toLowerCase(Locale.ENGLISH).endsWith(".properties")) {
            readPropertiesFile(file);
            // props is now set...
            return;
        }

        try {
            zis = new ZipInputStream(new FileInputStream(file));
            ZipEntry ze;

            while( (ze = zis.getNextEntry()) != null ) {
                if(ze.getName().equals(Constants.PROPS_FILENAME)) {
                    props = new Properties();
                    props.load(zis);
                    break;
                }
            }
            // props may be null
        }
        catch(Exception e) {
            // overkill...
            props = null;
        }
        finally {
            if(zis != null) {
                try {
                    zis.close();
                }
                catch(Exception e) {
                }
            }
        }
    }

    private void readPropertiesFile(File propsFile) {

        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(propsFile));
            props = new Properties();
            props.load(in);
        } catch(IOException ioe) {
            props = null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch(IOException ex) {}
            }
        }
    }

    private void setProps(BackupRequest request) {
        props.setProperty(Constants.PROPS_USER_NAME,
                          System.getProperty(Constants.PROPS_USER_NAME));
        props.setProperty(Constants.PROPS_TIMESTAMP_MSEC,
                          "" + request.timestamp);
        props.setProperty(Constants.PROPS_DOMAINS_DIR,
                          FileUtils.safeGetCanonicalPath(request.domainsDir));
        props.setProperty(Constants.PROPS_DOMAIN_DIR,
                          FileUtils.safeGetCanonicalPath(request.domainDir));
        props.setProperty(Constants.PROPS_BACKUP_FILE,
                          FileUtils.safeGetCanonicalPath(request.backupFile));
        props.setProperty(Constants.PROPS_DOMAIN_NAME,
                          request.domainName);
        props.setProperty(Constants.PROPS_DESCRIPTION,
                          request.description);
        props.setProperty(Constants.PROPS_TIMESTAMP_HUMAN,
                          new Date(request.timestamp).toString());

        props.setProperty(Constants.PROPS_VERSION,
                          Version.getFullVersion());

        String type = request.configOnly ? Constants.CONFIG_ONLY :
                Constants.FULL;
        props.setProperty(Constants.PROPS_TYPE, type);
        String bc = (request.backupConfig == null) ? Constants.NO_CONFIG : request.backupConfig;
        props.setProperty(Constants.BACKUP_CONFIG,bc);
    }

    private String propsToString(boolean terse) {
        final String pre = "backup-res.Props.";
        StringBuffer sb = new StringBuffer();


        if(terse) {
            sb.append(props.getProperty(Constants.PROPS_BACKUP_FILE));
        } else {
            sb.append(StringHelper.get(pre + Constants.PROPS_DESCRIPTION,
                props.getProperty(Constants.PROPS_DESCRIPTION)));
            sb.append("\n");
            sb.append(StringHelper.get(pre + Constants.PROPS_VERSION,
                props.getProperty(Constants.PROPS_VERSION)));
            sb.append("\n");
            sb.append(StringHelper.get(pre + Constants.PROPS_USER_NAME,
                props.getProperty(Constants.PROPS_USER_NAME)));
            sb.append("\n");
            sb.append(StringHelper.get(pre + Constants.PROPS_TIMESTAMP_HUMAN,
                props.getProperty(Constants.PROPS_TIMESTAMP_HUMAN)));
            sb.append("\n");
            sb.append(StringHelper.get(pre + Constants.PROPS_DOMAIN_NAME,
                props.getProperty(Constants.PROPS_DOMAIN_NAME)));
            sb.append("\n");
            sb.append(StringHelper.get(pre + Constants.PROPS_TYPE,
                props.getProperty(Constants.PROPS_TYPE)));
            sb.append("\n");
            sb.append(StringHelper.get(pre + Constants.BACKUP_CONFIG,
                props.getProperty(Constants.BACKUP_CONFIG)));
            sb.append("\n");
            sb.append(StringHelper.get(pre + Constants.PROPS_BACKUP_FILE,
                props.getProperty(Constants.PROPS_BACKUP_FILE)));
            sb.append("\n");
            sb.append(StringHelper.get(pre + Constants.PROPS_DOMAIN_DIR,
                props.getProperty(Constants.PROPS_DOMAIN_DIR)));
        }

        return sb.toString();
    }

    private String badStatusFileMessage(File file) {
        String msg = StringHelper.get("backup-res.Props.backup.file", file);
        msg += "\n";
        msg += StringHelper.get("backup-res.CorruptBackupFile.NoStatusFile");
        return msg;
    }

    private File             statusFile = null;
    private Properties       props;
}
