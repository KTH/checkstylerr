/**
 * @author mchyzer
 * $Id$
 */
package edu.internet2.middleware.grouperClient.jdbc.tableSync;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import edu.internet2.middleware.grouperClient.jdbc.GcDbAccess;
import edu.internet2.middleware.grouperClient.jdbc.GcDbVersionable;
import edu.internet2.middleware.grouperClient.jdbc.GcPersist;
import edu.internet2.middleware.grouperClient.jdbc.GcPersistableClass;
import edu.internet2.middleware.grouperClient.jdbc.GcPersistableField;
import edu.internet2.middleware.grouperClient.jdbc.GcPersistableHelper;
import edu.internet2.middleware.grouperClient.jdbc.GcSqlAssignPrimaryKey;
import edu.internet2.middleware.grouperClient.util.GrouperClientUtils;
import edu.internet2.middleware.grouperClientExt.org.apache.commons.lang3.builder.EqualsBuilder;
import edu.internet2.middleware.grouperClientExt.org.apache.commons.logging.Log;


/**
 * Status of all jobs for the sync.  one record for full, one for incremental, etc
 */
@GcPersistableClass(tableName="grouper_sync_job", defaultFieldPersist=GcPersist.doPersist)
public class GcGrouperSyncJob implements GcSqlAssignPrimaryKey, GcDbVersionable {
  
  
  //########## START GENERATED BY GcDbVersionableGenerate.java ###########
  /** save the state when retrieving from DB */
  @GcPersistableField(persist = GcPersist.dontPersist)
  private GcGrouperSyncJob dbVersion = null;

  /**
   * take a snapshot of the data since this is what is in the db
   */
  @Override
  public void dbVersionReset() {
    //lets get the state from the db so we know what has changed
    this.dbVersion = this.clone();
  }

  /**
   * if we need to update this object
   * @return if needs to update this object
   */
  @Override
  public boolean dbVersionDifferent() {
    return !this.equalsDeep(this.dbVersion);
  }

  /**
   * db version
   */
  @Override
  public void dbVersionDelete() {
    this.dbVersion = null;
  }

  /**
   * deep clone the fields in this object
   */
  @Override
  public GcGrouperSyncJob clone() {

    GcGrouperSyncJob gcGrouperSyncJob = new GcGrouperSyncJob();
    //connectionName  DONT CLONE
  
    //dbVersion  DONT CLONE
  
    gcGrouperSyncJob.errorMessage = this.errorMessage;
    gcGrouperSyncJob.errorTimestamp = this.errorTimestamp;
    //grouperSync  DONT CLONE
  
    gcGrouperSyncJob.grouperSyncId = this.grouperSyncId;
    //grouperSyncLog  DONT CLONE
  
    gcGrouperSyncJob.heartbeat = this.heartbeat;
    gcGrouperSyncJob.id = this.id;
    gcGrouperSyncJob.jobStateDb = this.jobStateDb;
    gcGrouperSyncJob.lastSyncIndex = this.lastSyncIndex;
    gcGrouperSyncJob.lastSyncTimestamp = this.lastSyncTimestamp;
    gcGrouperSyncJob.lastSyncStart = this.lastSyncStart;
    gcGrouperSyncJob.lastTimeWorkWasDone = this.lastTimeWorkWasDone;
    //lastUpdated  DONT CLONE
  
    gcGrouperSyncJob.percentComplete = this.percentComplete;
    gcGrouperSyncJob.quartzJobName = this.quartzJobName;
    gcGrouperSyncJob.syncType = this.syncType;

    return gcGrouperSyncJob;
  }

  /**
   *
   */
  public boolean equalsDeep(Object obj) {
    if (this==obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof GcGrouperSyncJob)) {
      return false;
    }
    GcGrouperSyncJob other = (GcGrouperSyncJob) obj;

    return new EqualsBuilder()


      //connectionName  DONT EQUALS

      //dbVersion  DONT EQUALS
      .append(this.errorMessage, other.errorMessage)
      .append(this.errorTimestamp, other.errorTimestamp)

      //grouperSync  DONT EQUALS

      .append(this.grouperSyncId, other.grouperSyncId)
      //grouperSyncLog  DONT EQUALS

      .append(this.heartbeat, other.heartbeat)
      .append(this.id, other.id)
      .append(this.jobStateDb, other.jobStateDb)
      .append(this.lastSyncIndex, other.lastSyncIndex)
      .append(this.lastSyncStart, other.lastSyncStart)
      .append(this.lastSyncTimestamp, other.lastSyncTimestamp)
      .append(this.lastTimeWorkWasDone, other.lastTimeWorkWasDone)
      //lastUpdated  DONT EQUALS

      .append(this.percentComplete, other.percentComplete)
      .append(this.quartzJobName, other.quartzJobName)
      .append(this.syncType, other.syncType)
        .isEquals();

  }
  //########## END GENERATED BY GcDbVersionableGenerate.java ###########

  /**
   * if heartbeat is alive and well
   * @return true if heartbeat is valid (heartbeat in last 1.5 minutes)
   */
  public boolean isHeartBeatAlive() {
    return (this.getHeartbeat() != null) && (System.currentTimeMillis() - this.getHeartbeat().getTime() < 90000);
  }
  
  /**
   * 0-100 percent complete of the job
   */
  private Integer percentComplete;
  
  /**
   * 0-100 percent complete of the job
   * @return percent complete
   */
  public Integer getPercentComplete() {
    return this.percentComplete;
  }

  /**
   * 0-100 percent complete of the job
   * @param percentComplete1
   */
  public void setPercentComplete(Integer percentComplete1) {
    this.percentComplete = percentComplete1;
  }

  /**
   * quartz job name if applicable
   */
  private String quartzJobName;
  
  /**
   * quartz job name if applicable
   * @return quartz job name
   */
  public String getQuartzJobName() {
    return this.quartzJobName;
  }

  /**
   * quartz job name if applicable
   * @param quartzJobName1
   */
  public void setQuartzJobName(String quartzJobName1) {
    this.quartzJobName = quartzJobName1;
  }

  /**
   * delete all data if table is here
   */
  public static void reset() {
    
    try {
      // if its not there forget about it... TODO remove this in 2.5+
      new GcDbAccess().connectionName("grouper").sql("select * from " + GcPersistableHelper.tableName(GcGrouperSyncJob.class) + " where 1 != 1").select(Integer.class);
    } catch (Exception e) {
      return;
    }

    new GcDbAccess().connectionName("grouper").sql("delete from " + GcPersistableHelper.tableName(GcGrouperSyncJob.class)).executeSql();
  }

  /**
   * run this before storing
   */
  public void storePrepare() {
    this.lastUpdated = new Timestamp(System.currentTimeMillis());
    this.connectionName = GcGrouperSync.defaultConnectionName(this.connectionName);
    this.errorMessage = GrouperClientUtils.abbreviate(this.errorMessage, 3700);
  }
  
  /**
   * if the last sync had an error, this is the error message
   */
  private String errorMessage; 

  /**
   * this the last sync had an error, this was the error timestamp
   */
  private Timestamp errorTimestamp;
  
  /**
   * if the last sync had an error, this is the error message
   * @return error message
   */
  public String getErrorMessage() {
    return this.errorMessage;
  }

  /**
   * if the last sync had an error, this is the error message
   * @param errorMessage1
   */
  public void setErrorMessage(String errorMessage1) {
    this.errorMessage = errorMessage1;
  }

  /**
   * this the last sync had an error, this was the error timestamp
   * @return error timestamp
   */
  public Timestamp getErrorTimestamp() {
    return this.errorTimestamp;
  }

  /**
   * this the last sync had an error, this was the error timestamp
   * @param errorTimestamp1
   */
  public void setErrorTimestamp(Timestamp errorTimestamp1) {
    this.errorTimestamp = errorTimestamp1;
  }

  /**
   * 
   */
  @GcPersistableField(persist=GcPersist.dontPersist)
  private GcGrouperSync grouperSync;
  
  /**
   * 
   * @return gc grouper sync
   */
  public GcGrouperSync getGrouperSync() {
    return this.grouperSync;
  }
  
  /**
   * 
   * @param gcGrouperSync
   */
  public void setGrouperSync(GcGrouperSync gcGrouperSync) {
    this.grouperSync = gcGrouperSync;
    this.grouperSyncId = gcGrouperSync == null ? null : gcGrouperSync.getId();
    this.connectionName = gcGrouperSync == null ? this.connectionName : gcGrouperSync.getConnectionName();
  }
  
  /**
   * connection name or null for default
   */
  @GcPersistableField(persist=GcPersist.dontPersist)
  private String connectionName;

  /**
   * connection name or null for default
   * @return connection name
   */
  public String getConnectionName() {
    return this.connectionName;
  }

  /**
   * connection name or null for default
   * @param connectionName1
   */
  public void setConnectionName(String connectionName1) {
    this.connectionName = connectionName1;
  }

  /**
   * 
   * @param args
   */
  public static void main(String[] args) {
    
    System.out.println("none");
    
    for (GcGrouperSyncJob theGcGrouperSyncJob : new GcDbAccess().connectionName("grouper").selectList(GcGrouperSyncJob.class)) {
      System.out.println(theGcGrouperSyncJob.toString());
    }
    
    // foreign key
    GcGrouperSync gcGrouperSync = new GcGrouperSync();
    gcGrouperSync.setSyncEngine("temp");
    gcGrouperSync.setProvisionerName("myJob");
    gcGrouperSync.getGcGrouperSyncDao().store();

    
    GcGrouperSyncJob gcGrouperSyncJob = new GcGrouperSyncJob();
    gcGrouperSyncJob.setGrouperSync(gcGrouperSync);
    gcGrouperSyncJob.setJobState(GcGrouperSyncJobState.running);
    gcGrouperSyncJob.setLastSyncIndex(135L);
    gcGrouperSyncJob.setLastTimeWorkWasDone(new Timestamp(System.currentTimeMillis() + 2000));
    gcGrouperSyncJob.setSyncType("testSyncType");
    gcGrouperSync.getGcGrouperSyncJobDao().internal_jobStore(gcGrouperSyncJob);
    
    System.out.println("stored");
    
    gcGrouperSyncJob = gcGrouperSync.getGcGrouperSyncJobDao().jobRetrieveBySyncType("testSyncType");
    System.out.println(gcGrouperSyncJob);
    
    gcGrouperSyncJob.setJobState(GcGrouperSyncJobState.notRunning);
    gcGrouperSync.getGcGrouperSyncJobDao().internal_jobStore(gcGrouperSyncJob);

    System.out.println("updated");

    for (GcGrouperSyncJob theGcGrouperSyncStatus : new GcDbAccess().connectionName("grouper").selectList(GcGrouperSyncJob.class)) {
      System.out.println(theGcGrouperSyncStatus.toString());
    }

    gcGrouperSync.getGcGrouperSyncJobDao().jobDelete(gcGrouperSyncJob, false);
    
    System.out.println("deleted");

    for (GcGrouperSyncJob theGcGrouperSyncStatus : new GcDbAccess().connectionName("grouper").selectList(GcGrouperSyncJob.class)) {
      System.out.println(theGcGrouperSyncStatus.toString());
    }
    
    System.out.println("retrieveOrCreate");
    gcGrouperSyncJob = gcGrouperSync.getGcGrouperSyncJobDao().jobRetrieveOrCreateBySyncType("testSyncType");    
    System.out.println(gcGrouperSyncJob);

    System.out.println("retrieve");
    gcGrouperSyncJob = gcGrouperSync.getGcGrouperSyncJobDao().jobRetrieveBySyncType("testSyncType");
    System.out.println(gcGrouperSyncJob);

    System.out.println("retrieveOrCreate");
    gcGrouperSyncJob = gcGrouperSync.getGcGrouperSyncJobDao().jobRetrieveOrCreateBySyncType("testSyncType");    
    System.out.println(gcGrouperSyncJob);

    System.out.println("deleted");
    gcGrouperSync.getGcGrouperSyncJobDao().jobDelete(gcGrouperSyncJob, false);
    gcGrouperSync.getGcGrouperSyncDao().delete();

    for (GcGrouperSyncJob theGcGrouperSyncStatus : new GcDbAccess().connectionName("grouper").selectList(GcGrouperSyncJob.class)) {
      System.out.println(theGcGrouperSyncStatus.toString());
    }

  }
  
  /**
   * get the job from a list
   * @param gcGrouperSyncJobs
   * @param syncType
   * @return job or null
   */
  private static GcGrouperSyncJob retrieveJobBySyncType(List<GcGrouperSyncJob> gcGrouperSyncJobs, String syncType) {
    for (GcGrouperSyncJob gcGrouperSyncJob : GrouperClientUtils.nonNull(gcGrouperSyncJobs)) {
      if (GrouperClientUtils.equals(syncType, gcGrouperSyncJob.getSyncType())) {
        return gcGrouperSyncJob;
      }
    }
    return null;
  }
  
  /**
   * 
   */
  @Override
  public String toString() {
    return GrouperClientUtils.toStringReflection(this);
  }

  /**
   * heartbeat updated every minute
   */
  private Timestamp heartbeat;
  
  
  
  /**
   * heartbeat updated every minute
   * @return heartbeat
   */
  public Timestamp getHeartbeat() {
    return this.heartbeat;
  }

  /**
   * heatbeat updated every minute
   * @param heartbeat1
   */
  public void setHeartbeat(Timestamp heartbeat1) {
    this.heartbeat = heartbeat1;
  }

  /**
   * 
   */
  public GcGrouperSyncJob() {
  }

  /**
   * uuid of this record in this table
   */
  @GcPersistableField(primaryKey=true, primaryKeyManuallyAssigned=false)
  private String id;

  
  /**
   * uuid of this record in this table
   * @return the id
   */
  public String getId() {
    return this.id;
  }

  
  /**
   * uuid of this record in this table
   * @param id1 the id to set
   */
  public void setId(String id1) {
    this.id = id1;
  }

  /**
   * type of sync, e.g. for sql sync this is the job subtype
   */
  private String syncType;
  
  /**
   * type of sync, e.g. for sql sync this is the job subtype
   * @return sync type
   */
  public String getSyncType() {
    return this.syncType;
  }

  /**
   * type of sync, e.g. for sql sync this is the job subtype
   * @param syncType
   */
  public void setSyncType(String syncType) {
    this.syncType = syncType;
  }

  /**
   * uuid of the job in grouper_sync
   */
  private String grouperSyncId;
  
  /**
   * uuid of the job in grouper_sync
   * @return uuid of the job in grouper_sync
   */ 
  public String getGrouperSyncId() {
    return this.grouperSyncId;
  }

  /**
   * uuid of the job in grouper_sync
   * @param grouperSyncId1
   */
  public void setGrouperSyncId(String grouperSyncId1) {
    this.grouperSyncId = grouperSyncId1;
    if (this.grouperSync == null || !GrouperClientUtils.equals(this.grouperSync.getId(), grouperSyncId1)) {
      this.grouperSync = null;
    }
  }
  
  /**
   * running, waitingForAnotherJobToFinish (if waiting for another job to finish), notRunning
   */
  @GcPersistableField(columnName="job_state")
  private String jobStateDb;
  
  
  /**
   * running, waitingForAnotherJobToFinish (if waiting for another job to finish), notRunning
   * @return the jobState
   */
  public String getJobStateDb() {
    return this.jobStateDb;
  }

  /**
   * 
   * @return the state or null if not there
   */
  public GcGrouperSyncJobState getJobState() {
    return GcGrouperSyncJobState.valueOfIgnoreCase(this.jobStateDb);
  }
  
  /**
   * 
   * @param gcGrouperSyncJobState
   */
  public void setJobState(GcGrouperSyncJobState gcGrouperSyncJobState) {
    this.jobStateDb = gcGrouperSyncJobState == null ? null : gcGrouperSyncJobState.name();
  }
  
  /**
   * running, waitingForAnotherJobToFinish (if waiting for another job to finish), notRunning
   * @param jobState1 the jobState to set
   */
  public void setJobStateDb(String jobState1) {
    this.jobStateDb = jobState1;
  }

  /**
   * when last sync started
   */
  private Timestamp lastSyncStart;

  /**
   * when last sync started
   * @return
   */
  public Timestamp getLastSyncStart() {
    return lastSyncStart;
  }

  /**
   * when last sync started
   * @param lastSyncStart
   */
  public void setLastSyncStart(Timestamp lastSyncStart) {
    this.lastSyncStart = lastSyncStart;
  }

  /**
   * when last record processed if timestamp and not integer, or when last sync ended
   */
  private Timestamp lastSyncTimestamp;
  
  /**
   * when last record processed if timestamp and not integer
   * @return when processed
   */
  public Timestamp getLastSyncTimestamp() {
    return this.lastSyncTimestamp;
  }

  /**
   * when last record processed if timestamp and not integer
   * @param lastSyncTimestamp1
   */
  public void setLastSyncTimestamp(Timestamp lastSyncTimestamp1) {
    this.lastSyncTimestamp = lastSyncTimestamp1;
  }

  /**
   * int of last record checked
   */
  private Long lastSyncIndex;
  
  
  /**
   * int of last record checked
   * @return the lastSyncIndexOrMillis
   */
  public Long getLastSyncIndex() {
    return this.lastSyncIndex;
  }

  
  /**
   * int of last record checked
   * @param lastSyncIndexOrMillis1 the lastSyncIndexOrMillis to set
   */
  public void setLastSyncIndex(Long lastSyncIndexOrMillis1) {
    this.lastSyncIndex = lastSyncIndexOrMillis1;
  }

  /**
   * last time a record was processed
   */
  private Timestamp lastTimeWorkWasDone;
  

  /**
   * last time a record was processed
   * @return the lastTimeWorkWasDone
   */
  public Timestamp getLastTimeWorkWasDone() {
    return this.lastTimeWorkWasDone;
  }

  
  /**
   * last time a record was processed
   * @param lastTimeWorkWasDone1 the lastTimeWorkWasDone to set
   */
  public void setLastTimeWorkWasDone(Timestamp lastTimeWorkWasDone1) {
    this.lastTimeWorkWasDone = lastTimeWorkWasDone1;
  }

  /**
   * when this record was last updated
   */
  private Timestamp lastUpdated;
  
  /**
   * when this record was last updated
   * @return the lastUpdated
   */
  public Timestamp getLastUpdated() {
    return this.lastUpdated;
  }

  
  /**
   * when this record was last updated
   * @param lastUpdated1 the lastUpdated to set
   */
  public void setLastUpdated(Timestamp lastUpdated1) {
    this.lastUpdated = lastUpdated1;
  }

  /**
   * 
   */
  @Override
  public boolean gcSqlAssignNewPrimaryKeyForInsert() {
    if (this.id != null) {
      return false;
    }
    this.id = GrouperClientUtils.uuid();
    return true;
  }

  /**
   * assign heartbeat and see if other jobs are pending or running
   * @param provisionerName
   * @param isLargeJob is if this is a  big job and has precendence
   * @return false if should stop and true if should run
   */
  public boolean assignHeartbeatAndCheckForPendingJobs(boolean isLargeJob) {
    
    List<GcGrouperSyncJob> allGcGrouperSyncJobs = this.getGrouperSync().getGcGrouperSyncJobDao().internal_jobRetrieveFromDbAll();
  
    GcGrouperSyncJob gcGrouperSyncJob = GcGrouperSyncJob.retrieveJobBySyncType(allGcGrouperSyncJobs, syncType);
    
    // if doesnt exist, 
    if (gcGrouperSyncJob == null) {
      throw new RuntimeException("Why is this job not found????");
    }
    
    gcGrouperSyncJob.setHeartbeat(new Timestamp(System.currentTimeMillis()));
  
    // should already be running but just in case
    gcGrouperSyncJob.setJobState(GcGrouperSyncJobState.running);
  
    this.grouperSync.getGcGrouperSyncJobDao().internal_jobStore(gcGrouperSyncJob);
    
    for (GcGrouperSyncJob currentGrouperSyncJob : GrouperClientUtils.nonNull(allGcGrouperSyncJobs)) {
      if (GrouperClientUtils.equals(currentGrouperSyncJob.getSyncType(), syncType)) {
        continue;
      }
      
      //if the heartbeat is bad dontw worry about it
      if (currentGrouperSyncJob.getHeartbeat() == null || System.currentTimeMillis() - currentGrouperSyncJob.getHeartbeat().getTime() > 90000) {
        // dont worry about it
        continue;
      }
      
      if (GcGrouperSyncJobState.running == currentGrouperSyncJob.getJobState() && !isLargeJob) {
        return false;
      }
      
      // dont run if we are a small job
      if (GcGrouperSyncJobState.pending == currentGrouperSyncJob.getJobState() && !isLargeJob) {
        return false;
      }
    }
  
    // large jobs always keep going
    return true;
  }

  /**
   * assign heartbeat and end job
   */
  public void assignHeartbeatAndEndJob() {
    
    this.setHeartbeat(new Timestamp(System.currentTimeMillis()));
  
    // should already be running but just in case
    this.setJobState(GcGrouperSyncJobState.notRunning);
  
    this.grouperSync.getGcGrouperSyncJobDao().internal_jobStore(this);
    
  }

  /**
   * wait for related jobs to finish running, then run.  note you should get the job again afterwards so its up to date
   * @param provisionerName
   * @param goToPendingFirstAkaLargeJob is if this is a  big job and needs to register as pending first so 
   * it knows it should run now.  false if quick job and doesnt matter
   */
  public void waitForRelatedJobsToFinishThenRun(boolean goToPendingFirstAkaLargeJob) {
  
    List<GcGrouperSyncJob> allGcGrouperSyncJobs = this.grouperSync.getGcGrouperSyncJobDao().internal_jobRetrieveFromDbAll();
  
    for (int i=0;i<allGcGrouperSyncJobs.size();i++) {
      GcGrouperSyncJob current = allGcGrouperSyncJobs.get(i);
      if (GrouperClientUtils.equals(this.getId(), current.getId())) {
        allGcGrouperSyncJobs.set(i, this);
      }
    }
    
    waitForRelatedJobsToFinishThenRunHelper(allGcGrouperSyncJobs, goToPendingFirstAkaLargeJob);
  }

  /**
   * wait for related jobs to finish running, then run
   * @param provisionerName
   * @param goToPendingFirstAkaLargeJob is if this is a  big job and needs to register as pending first so 
   * it knows it should run now.  falso if quick job and doesnt matter
   */
  private void waitForRelatedJobsToFinishThenRunHelper(List<GcGrouperSyncJob> allGcGrouperSyncJobs, boolean goToPendingFirstAkaLargeJob) {
    
    String syncType = this.getSyncType();
    
    // if we are already running then we good
    if (this.getJobState() == GcGrouperSyncJobState.running) {
  
      // if heartbeat is expired
      if (this.isHeartBeatAlive()) {
        return;
      }
    }
  
    
    //go to pending first?
    if (goToPendingFirstAkaLargeJob) {
      this.setJobState(GcGrouperSyncJobState.pending);
      this.setHeartbeat(new Timestamp(System.currentTimeMillis()));
      this.grouperSync.getGcGrouperSyncJobDao().internal_jobStore(this);
      
      // sleep between half and full second
      GrouperClientUtils.sleep(500 + new Random().nextInt(500));
      
      allGcGrouperSyncJobs = this.grouperSync.getGcGrouperSyncJobDao().internal_jobRetrieveFromDbAll();
      
    }
  
    // back this off so we arent overchecking
    int sleepSeconds = 15;
    long started = System.currentTimeMillis();
    
    while(true) {
  
      // see how many other jobs running
      int runningOrPendingCount = 0;
      for (GcGrouperSyncJob currentGrouperSyncJob : GrouperClientUtils.nonNull(allGcGrouperSyncJobs)) {
        if (GrouperClientUtils.equals(currentGrouperSyncJob.getSyncType(), syncType)) {
          continue;
        }
        
        //if the heartbeat is bad dontw worry about it
        if (currentGrouperSyncJob.getHeartbeat() == null || System.currentTimeMillis() - currentGrouperSyncJob.getHeartbeat().getTime() > 90000) {
          // dont worry about it
          continue;
        }
        
        if (GcGrouperSyncJobState.running == currentGrouperSyncJob.getJobState()) {
          runningOrPendingCount++;
          continue;
        }
        
        // dont run if we are a small job
        if (GcGrouperSyncJobState.pending == currentGrouperSyncJob.getJobState() && !goToPendingFirstAkaLargeJob) {
          runningOrPendingCount++;
          continue;
        }
      }
      
      //if nothing there or only one there, we done
      if (runningOrPendingCount == 0) {
        this.setJobState(GcGrouperSyncJobState.running);
        this.setHeartbeat(new Timestamp(System.currentTimeMillis()));
        this.grouperSync.getGcGrouperSyncJobDao().internal_jobStore(this);
        return;
      }
  
      // lets go to pending and set the heartbeat
      if (sleepSeconds > 60) {
        this.setJobState(GcGrouperSyncJobState.notRunning);
      }
      this.setHeartbeat(new Timestamp(System.currentTimeMillis()));
      this.grouperSync.getGcGrouperSyncJobDao().internal_jobStore(this);
      
      // sleep a little at first then ramp it up
      GrouperClientUtils.sleep((sleepSeconds*1000) + new Random().nextInt(5000));
  
      if (sleepSeconds > 60 || goToPendingFirstAkaLargeJob) {
        this.setJobState(GcGrouperSyncJobState.pending);
        
      }
      this.setHeartbeat(new Timestamp(System.currentTimeMillis()));
      this.grouperSync.getGcGrouperSyncJobDao().internal_jobStore(this);
  
      if (goToPendingFirstAkaLargeJob) {
        
        // sleep between half and full second
        GrouperClientUtils.sleep(500 + new Random().nextInt(500));
        
      }
      if (sleepSeconds < 120) {
        sleepSeconds *= 2;
      }
      if (sleepSeconds > 120) {
        sleepSeconds = 120;
      }
      
      allGcGrouperSyncJobs = this.grouperSync.getGcGrouperSyncJobDao().internal_jobRetrieveFromDbAll();
  
      if (System.currentTimeMillis() - started > 1000 * 60 * 60 * 24) {
        throw new RuntimeException("Job cannot start for a day! " 
            + this.connectionName + ", " + this.grouperSync.getSyncEngine() + ", " + this.grouperSync.getProvisionerName() + ", " + syncType);
      }
    }    
  }

}
