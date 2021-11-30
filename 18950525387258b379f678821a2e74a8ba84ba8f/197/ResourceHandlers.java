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

package org.glassfish.admingui.common.handlers;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.List;

import com.sun.jsftemplating.annotation.Handler;
import com.sun.jsftemplating.annotation.HandlerInput;
import com.sun.jsftemplating.annotation.HandlerOutput;
import com.sun.jsftemplating.layout.descriptors.handler.HandlerContext;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import org.glassfish.admingui.common.util.DeployUtil;
import org.glassfish.admingui.common.util.GuiUtil;
import org.glassfish.admingui.common.util.TargetUtil;


/**
 *
 * @author Anissa Lam
 */
public class ResourceHandlers {

    /*
     * This handler takes in a list of rows, there should be 'Enabled' attribute in each row.
     * Get the resource-ref of this resource and do a logical And with this Enabled attribute
     * to get the real status
     */
    @Handler(id = "gf.getResourceRealStatus",
        input = {
            @HandlerInput(name = "endpoint", type = String.class),
            @HandlerInput(name = "rows", type = java.util.List.class, required = true)},
        output = {
            @HandlerOutput(name = "result", type = List.class)})
    public static void getResourceRealStatus(HandlerContext handlerCtx) {
        List<Map> rows = (List) handlerCtx.getInputValue("rows");
        Map<String, String> targetsMap = new HashMap<String, String>();
        for (Map oneRow : rows) {
            try {
                String name = (String) oneRow.get("name");
                String encodedName = URLEncoder.encode(name, "UTF-8");
                List<String> targets = DeployUtil.getApplicationTarget(name, "resource-ref");
                if (targets.size() == 0) {
                    continue; //The resource is only created on domain, no source-ref exists.
                }
                String enabledStr = DeployUtil.getTargetEnableInfo(encodedName, false, false);
                List<String> targetUrls = new ArrayList<String>();
                for (String target : targets) {
                    if (TargetUtil.isCluster(target)) {
                        targetsMap.put(GuiUtil.getSessionValue("REST_URL") + "/clusters/cluster/" + target, target);
                        targetUrls.add(GuiUtil.getSessionValue("REST_URL") + "/clusters/cluster/" + target);
                    } else {
                        targetsMap.put(GuiUtil.getSessionValue("REST_URL") + "/servers/server/" + target, target);
                        targetUrls.add(GuiUtil.getSessionValue("REST_URL") + "/servers/server/" + target);
                    }
                }
                oneRow.put("targetUrls", targetUrls);
                oneRow.put("targetsMap", targetsMap);
                oneRow.put("enabled", enabledStr);
            } catch (Exception ex) {
                GuiUtil.handleException(handlerCtx, ex);
            }
        }
        handlerCtx.setOutputValue("result", rows);
    }


    /**
     * <p> This handler looks into the config properties, and confidential list, and returns a List of Map for populating the properties table. </p>
     *   This is called for creating new objects.
     */
    @Handler(id="gf.getConfigPropsInfo",
            input={
        @HandlerInput(name="extraProps", type=java.util.Map.class),
        @HandlerInput(name="key", type=String.class),
        @HandlerInput(name="confidentialKey", type=String.class, defaultValue="confidentialConfigProps")},
        output={
        @HandlerOutput(name="result", type=java.util.List.class),
        @HandlerOutput(name="hasConfidentialProps", type=java.lang.Boolean.class)})
    public static void getConfigPropsInfo(HandlerContext handlerCtx) {

        Map<String,Object> extraProps = (Map) handlerCtx.getInputValue("extraProps");
        String key = (String) handlerCtx.getInputValue("key");
        String confidentialKey = (String) handlerCtx.getInputValue("confidentialKey");
        Boolean hasConfidential = true;
        Map<String, String> allProps = (Map<String, String>) extraProps.get(key);
        List<String> confidentialPropsNames = (List<String>) extraProps.get(confidentialKey);
        if (confidentialPropsNames == null || confidentialPropsNames.isEmpty()){
            hasConfidential = false;
        }
        List<Map> result = new ArrayList();

        for(Map.Entry<String, String> e : allProps.entrySet()){
            Map<String, Object> oneRow = new HashMap();
            String name = e.getKey();
            String value = (e.getValue() == null) ? "" : e.getValue();
            oneRow.put("selected", false);
            oneRow.put("name", name);
            if ( hasConfidential && confidentialPropsNames.contains(name)){
                oneRow.put("value", "");
                oneRow.put("confValue", value);
                oneRow.put("confValue2", value);
                oneRow.put("isConfidential", true);
            }else{
                oneRow.put("value", value);
                oneRow.put("confValue", "");
                oneRow.put("confValue2", "");
                oneRow.put("isConfidential", false);
            }
            result.add(oneRow);
        }
        handlerCtx.setOutputValue("result", result);
        handlerCtx.setOutputValue("hasConfidentialProps", hasConfidential);
    }

   /* This method goes through the table list,  if there is confidential properties, will ensure that the masked value1 and value2 is the same.
    * And will copy this to the property value column to continue processing.
    * This method is called just before saving the properties.
    */
    @Handler(id="gf.combineProperties",
            input={
        @HandlerInput(name="tableList", type=java.util.List.class)},
        output={
        @HandlerOutput(name="combined", type=java.util.List.class)})
    public static void combineProperties(HandlerContext handlerCtx) {

        List<Map> tableList = (List) handlerCtx.getInputValue("tableList");
        List<Map> combined = new ArrayList();
        for(Map oneRow: tableList){
            Map newRow = new HashMap();
            boolean isC = (Boolean) oneRow.get("isConfidential");
            String name = (String)oneRow.get("name");
            newRow.put("name", name);
            if (GuiUtil.isEmpty(name)){
                continue;
            }
            if(isC){
                String v1 = (String) oneRow.get("confValue");
                String v2 = (String) oneRow.get("confValue2");
                if(v1 == null){
                    if (v2 != null){
                        GuiUtil.handleError(handlerCtx, "Confidential property '" + name + "' does not match.");
                        return;
                    }
                    continue;
                }
                if(! v1.equals(v2)){
                    GuiUtil.handleError(handlerCtx, "Confidential property '" + name + "' does not match.");
                    return;
                }
                newRow.put("value",v1);
            }else{
                newRow.put("value", oneRow.get("value"));
            }
            combined.add(newRow);
        }
        handlerCtx.setOutputValue("combined", combined);
    }


    @Handler(id="gf.buildConfidentialPropsTable",
            input={
        @HandlerInput(name="propsMaps", type=List.class),
        @HandlerInput(name="confidentialList", type=java.util.List.class)},
        output={
        @HandlerOutput(name="result", type=java.util.List.class),
        @HandlerOutput(name="hasConfidentialProps", type=Boolean.class) })
    public static void buildConfidentialPropsTable(HandlerContext handlerCtx) {

        List<String> confidentialList = (List<String>) handlerCtx.getInputValue("confidentialList");
        List<Map<String, String>> propsMaps = (List<Map<String, String>>) handlerCtx.getInputValue("propsMaps");
        Boolean hasConfidential = true;
        if (confidentialList == null || confidentialList.isEmpty()){
            hasConfidential = false;
        }
        Boolean hasConf = false;
        List<Map> result = new ArrayList();
        for( Map<String, String> oneProp : propsMaps){
            Map<String, Object> oneRow = new HashMap();
            String name = oneProp.get("name");
            String value = oneProp.get("value");
            if (value == null) value="";
            oneRow.put("selected", false);
            oneRow.put("name", name);
            if ( hasConfidential && confidentialList.contains(name)){
                oneRow.put("value", "");
                oneRow.put("confValue", value);
                oneRow.put("confValue2", value);
                oneRow.put("isConfidential", true);
                hasConf = true;
            }else{
                oneRow.put("value", value);
                oneRow.put("confValue", "");
                oneRow.put("confValue2", "");
                oneRow.put("isConfidential", false);
            }
            result.add(oneRow);
        }
        handlerCtx.setOutputValue("result", result);
        handlerCtx.setOutputValue("hasConfidentialProps", hasConf);
    }

    //getLogicalJndiName(logicalMap="#{requestScope.tmpJMap.data.extraProperties.#{pageSession.logicalJndiMapKey}}", listRow="#{pageSession.listOfRows}");
    @Handler(id="gf.getLogicalJndiName",
            input={
                    @HandlerInput(name="logicalMapList", type=List.class, required=true),
                    @HandlerInput(name="listRow", type=java.util.List.class)},
            output={
                    @HandlerOutput(name="result", type=java.util.List.class) })
    public static void getLogicalJndiName(HandlerContext handlerCtx)  {
        List<Map<String, String>> logicalMapList = (List<Map<String, String>>) handlerCtx.getInputValue("logicalMapList");
        List<Map<String, Object>> listRow = (List<Map<String, Object>>) handlerCtx.getInputValue("listRow");
        if (listRow==null || listRow.isEmpty()){
            handlerCtx.setOutputValue("result", listRow);
            return;
        }
        //listRow is the row for each resource table, need to extract its logical jndi name for logicalMapList and add that to the row.
        try{
            for(Map<String, Object> onerow: listRow){
                String name= (String) onerow.get("name");
                onerow.put("logicalJndiName", "");
                onerow.put("encodedLogicalJndiName", "");
                for(Map<String, String> logicalMap : logicalMapList){
                    if(name.equals(logicalMap.get("name"))){
                        String lname = logicalMap.get("logical-jndi-name");
                        if (!GuiUtil.isEmpty(lname)){
                            onerow.put("logicalJndiName", lname);
                            onerow.put("encodedLogicalJndiName", URLEncoder.encode(lname, "UTF-8"));
                        }
                        break;
                    }
                }
            }
            handlerCtx.setOutputValue("result", listRow);
        } catch (Exception ex) {
            GuiUtil.handleException(handlerCtx, ex);
        }
    }
}
