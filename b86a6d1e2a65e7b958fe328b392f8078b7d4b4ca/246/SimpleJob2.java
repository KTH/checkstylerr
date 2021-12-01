/*
 * Copyright 2005 - 2009 Terracotta, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 */

package quartz.examples.example9;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * <p>
 * This is just a simple job that gets fired off many times by example 1
 * </p>
 *
 * @author Bill Kratzer
 */
public class SimpleJob2 implements Job {

    private static Logger _log = LoggerFactory.getLogger(SimpleJob2.class);

    /**
     * Empty constructor for job initialization
     */
    public SimpleJob2() {
    }

    /**
     * <p>
     * Called by the <code>{@link org.quartz.Scheduler}</code> when a
     * <code>{@link org.quartz.Trigger}</code> fires that is associated with
     * the <code>Job</code>.
     * </p>
     *
     * @throws org.quartz.JobExecutionException if there is an exception while executing the job.
     */
    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        // This job simply prints out its job name and the
        // com.gourd.erwa.date and time that it is running
        JobKey jobKey = context.getJobDetail().getKey();
        _log.info("SimpleJob2 says: " + jobKey + "执行于 " + new Date());
    }

}
