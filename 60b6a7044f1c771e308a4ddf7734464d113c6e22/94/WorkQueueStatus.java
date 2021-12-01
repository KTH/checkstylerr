/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// Generated by http://code.google.com/p/protostuff/ ... DO NOT EDIT!
// Generated from BitControl.Proto

package org.apache.drill.exec.proto;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import com.dyuproject.protostuff.GraphIOUtil;
import com.dyuproject.protostuff.Input;
import com.dyuproject.protostuff.Message;
import com.dyuproject.protostuff.Output;
import com.dyuproject.protostuff.Schema;

public final class WorkQueueStatus implements Externalizable, Message<WorkQueueStatus>, Schema<WorkQueueStatus>
{

    public static Schema<WorkQueueStatus> getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static WorkQueueStatus getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final WorkQueueStatus DEFAULT_INSTANCE = new WorkQueueStatus();

    
    private DrillbitEndpoint endpoint;
    private Integer queueLength;
    private Long reportTime;

    public WorkQueueStatus()
    {
        
    }

    // getters and setters

    // endpoint

    public DrillbitEndpoint getEndpoint()
    {
        return endpoint;
    }

    public void setEndpoint(DrillbitEndpoint endpoint)
    {
        this.endpoint = endpoint;
    }

    // queueLength

    public Integer getQueueLength()
    {
        return queueLength;
    }

    public void setQueueLength(Integer queueLength)
    {
        this.queueLength = queueLength;
    }

    // reportTime

    public Long getReportTime()
    {
        return reportTime;
    }

    public void setReportTime(Long reportTime)
    {
        this.reportTime = reportTime;
    }

    // java serialization

    public void readExternal(ObjectInput in) throws IOException
    {
        GraphIOUtil.mergeDelimitedFrom(in, this, this);
    }

    public void writeExternal(ObjectOutput out) throws IOException
    {
        GraphIOUtil.writeDelimitedTo(out, this, this);
    }

    // message method

    public Schema<WorkQueueStatus> cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public WorkQueueStatus newMessage()
    {
        return new WorkQueueStatus();
    }

    public Class<WorkQueueStatus> typeClass()
    {
        return WorkQueueStatus.class;
    }

    public String messageName()
    {
        return WorkQueueStatus.class.getSimpleName();
    }

    public String messageFullName()
    {
        return WorkQueueStatus.class.getName();
    }

    public boolean isInitialized(WorkQueueStatus message)
    {
        return true;
    }

    public void mergeFrom(Input input, WorkQueueStatus message) throws IOException
    {
        for(int number = input.readFieldNumber(this);; number = input.readFieldNumber(this))
        {
            switch(number)
            {
                case 0:
                    return;
                case 1:
                    message.endpoint = input.mergeObject(message.endpoint, DrillbitEndpoint.getSchema());
                    break;

                case 2:
                    message.queueLength = input.readInt32();
                    break;
                case 3:
                    message.reportTime = input.readInt64();
                    break;
                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }


    public void writeTo(Output output, WorkQueueStatus message) throws IOException
    {
        if(message.endpoint != null)
             output.writeObject(1, message.endpoint, DrillbitEndpoint.getSchema(), false);


        if(message.queueLength != null)
            output.writeInt32(2, message.queueLength, false);

        if(message.reportTime != null)
            output.writeInt64(3, message.reportTime, false);
    }

    public String getFieldName(int number)
    {
        switch(number)
        {
            case 1: return "endpoint";
            case 2: return "queueLength";
            case 3: return "reportTime";
            default: return null;
        }
    }

    public int getFieldNumber(String name)
    {
        final Integer number = __fieldMap.get(name);
        return number == null ? 0 : number.intValue();
    }

    private static final java.util.HashMap<String,Integer> __fieldMap = new java.util.HashMap<String,Integer>();
    static
    {
        __fieldMap.put("endpoint", 1);
        __fieldMap.put("queueLength", 2);
        __fieldMap.put("reportTime", 3);
    }
    
}
