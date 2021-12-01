package edu.internet2.middleware.grouper.app.serviceLifecycle;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobKey;
import org.quartz.Scheduler;

import edu.internet2.middleware.grouper.Group;
import edu.internet2.middleware.grouper.GrouperSession;
import edu.internet2.middleware.grouper.app.loader.GrouperLoader;
import edu.internet2.middleware.grouper.app.loader.GrouperLoaderConfig;
import edu.internet2.middleware.grouper.app.loader.GrouperLoaderIncrementalJob;
import edu.internet2.middleware.grouper.app.loader.GrouperLoaderIncrementalJob.Row;
import edu.internet2.middleware.grouper.app.loader.GrouperLoaderType;
import edu.internet2.middleware.grouper.app.loader.db.GrouperLoaderDb;
import edu.internet2.middleware.grouper.app.provisioning.GrouperProvisioningProcessingResult;
import edu.internet2.middleware.grouper.changeLog.esb.consumer.EsbEvent;
import edu.internet2.middleware.grouper.changeLog.esb.consumer.EsbEventContainer;
import edu.internet2.middleware.grouper.changeLog.esb.consumer.EsbEventType;
import edu.internet2.middleware.grouper.esb.listener.EsbListenerBase;
import edu.internet2.middleware.grouper.esb.listener.ProvisioningSyncConsumerResult;
import edu.internet2.middleware.grouper.group.TypeOfGroup;
import edu.internet2.middleware.grouper.internal.dao.QueryOptions;
import edu.internet2.middleware.grouper.misc.GrouperDAOFactory;
import edu.internet2.middleware.grouper.util.GrouperUtil;
import edu.internet2.middleware.grouperClient.collections.MultiKey;
import edu.internet2.middleware.grouperClient.jdbc.GcDbAccess;
import edu.internet2.middleware.grouperClient.util.ExpirableCache;

public class GrouperGracePeriodChangeLogConsumer extends EsbListenerBase {

  public static int test_fullSyncCount = 0;
  
  public static int test_incrementalSyncCount = 0;
  
  @Override
  public boolean dispatchEvent(String eventJsonString, String consumerName) {
    throw new RuntimeException("Not implemented");
  }

  @Override
  public void disconnect() {
    // TODO Auto-generated method stub
    
  }

  /**
   * group ids which have grace periods
   */
  private static ExpirableCache<Boolean, List<String>> gracePeriodGroupIds = new ExpirableCache<Boolean, List<String>>(1);

  public List<String> gracePeriodGroupIds() {
    // TODO check the 10 second cache clear table in future and change to five minute cache
    List<String> result = gracePeriodGroupIds.get(Boolean.TRUE);
    if (result == null) {
      synchronized (GrouperGracePeriodChangeLogConsumer.class) {
        result = gracePeriodGroupIds.get(Boolean.TRUE);
        if (result == null) {
          
          result = new ArrayList<String>();
          // gaaagv_gracePeriod.group_id owner_group_id, gaaagv_gracePeriod.group_name owner_group_name, "
          // + "gaaagv_gracePeriod.value_string grace_period_days, gaaagv_groupName.value_string group_name "
          String groupQuery = GrouperGracePeriod.groupQuery();
          
          List<Object[]> rows = new GcDbAccess().sql(groupQuery).selectList(Object[].class);
          for (Object[] row : GrouperUtil.nonNull(rows)) {
            result.add((String)row[0]);
          }
          
          gracePeriodGroupIds.put(Boolean.TRUE, result);
          
        }
      }
    }
    return result;
  }
  
  @Override
  public ProvisioningSyncConsumerResult dispatchEventList(
      List<EsbEventContainer> esbEventContainers,
      GrouperProvisioningProcessingResult grouperProvisioningProcessingResult) {

    Set<String> groupIds = new HashSet<String>(gracePeriodGroupIds());
    
    
    ProvisioningSyncConsumerResult provisioningSyncConsumerResult = new ProvisioningSyncConsumerResult();


    Set<MultiKey> subjectSourceIdAndSubjectIds = new LinkedHashSet<MultiKey>();
    Map<MultiKey, Long> subjectSourceIdAndSubjectIdToCreatedOn = new HashMap<MultiKey, Long>();

    boolean needsFullSync = false;
    boolean attributeChange = false;
    
    String gracePeriodStem = GrouperGracePeriod.gracePeriodStemName();
    
    int maxUntilFullSync = GrouperLoaderConfig.retrieveConfig().propertyValueInt("changeLog.consumer.gracePeriods.maxUntilFullSync", 100);
    
    String groupName = GrouperGracePeriod.gracePeriodStemName() + ":" + GrouperGracePeriod.GROUPER_GRACE_PERIOD_LOADER_GROUP_NAME;

    Group group = GrouperDAOFactory.getFactory().getGroup().findByNameSecure(
        groupName, true, new QueryOptions().secondLevelCache(false), GrouperUtil.toSet(TypeOfGroup.group));

    Timestamp lastSuccess = new GcDbAccess().sql("select max(ended_time) from grouper_loader_log where job_name = ? and status = 'SUCCESS'")
      //'SQL_GROUP_LIST__etc:attribute:gracePeriod:grouperGracePeriodLoader__b3708fc0a5c347ff8dad78f2b0f7d50e'
      .addBindVar("SQL_GROUP_LIST__" + groupName + "__" + group.getId()).select(Timestamp.class);
    
    OUTER: for (EsbEventContainer esbEventContainer : esbEventContainers) {
      
      EsbEventType esbEventType = esbEventContainer.getEsbEventType();
      EsbEvent esbEvent = esbEventContainer.getEsbEvent();

      // skip any events that happened before the last full sync
      if (lastSuccess != null && esbEvent.getCreatedOnMicros() / 1000 < lastSuccess.getTime()) {
        continue;
      }
      
      switch(esbEventType) {
        case MEMBERSHIP_ADD:
        case MEMBERSHIP_DELETE:
        case MEMBER_UPDATE:
          if (groupIds.contains(esbEvent.getGroupId()) && !StringUtils.equals("g:gsa", esbEvent.getSourceId())) {
            
            MultiKey sourceIdSubjectId = new MultiKey(esbEvent.getSourceId(), esbEvent.getSubjectId());
            subjectSourceIdAndSubjectIds.add(sourceIdSubjectId);
            subjectSourceIdAndSubjectIdToCreatedOn.put(sourceIdSubjectId, esbEvent.getCreatedOnMicros()/1000);
          }
          break;
        case ATTRIBUTE_ASSIGN_DELETE:
        case ATTRIBUTE_ASSIGN_ADD:
        case ATTRIBUTE_ASSIGN_VALUE_ADD:
        case ATTRIBUTE_ASSIGN_VALUE_DELETE:
          if (esbEvent.getAttributeDefNameName() != null && esbEvent.getAttributeDefNameName().startsWith(gracePeriodStem)) {
            needsFullSync = true;
            attributeChange = true;
            // clear cache
            gracePeriodGroupIds.clear();
            break OUTER;
          }
          break;
      }
    }
    
    if (subjectSourceIdAndSubjectIds.size() > maxUntilFullSync) {
      needsFullSync = true;
    }

    if (needsFullSync) {
      test_fullSyncCount++;
      // schedule one in 5 minutes after caches clear in case running on another loader
      scheduleGraceLoaderNow(attributeChange, group);
    } else if (GrouperUtil.length(subjectSourceIdAndSubjectIds) > 0) {
       
      GrouperLoaderDb grouperLoaderDb = GrouperLoaderConfig.retrieveDbProfile("grouper");
      
      // send sourceIds and subjectIds to incremental loader
      for (MultiKey subjectSourceIdAndSubjectId : subjectSourceIdAndSubjectIds) {
        test_incrementalSyncCount++;
        String sourceId = (String)subjectSourceIdAndSubjectId.getKey(0);
        String subjectId = (String)subjectSourceIdAndSubjectId.getKey(1);
        Long createdOn = subjectSourceIdAndSubjectIdToCreatedOn.get(subjectSourceIdAndSubjectId);
        Row row = new Row(1, createdOn, groupName, subjectId, null, null, sourceId, "subject_id", subjectId);
        
        Map<String, Set<Group>> groupsRequiringLoaderMetadataUpdates = new HashMap<String, Set<Group>>();
        
        GrouperLoaderIncrementalJob.processOneSQLRow(GrouperSession.staticGrouperSession(), grouperLoaderDb, 
            row, null, group, GrouperLoaderType.SQL_GROUP_LIST.name(), 
            this.getEsbConsumer().getChangeLogProcessorMetadata().getHib3GrouperLoaderLog(), groupsRequiringLoaderMetadataUpdates, 
            null, null, GrouperGracePeriod.groupQuery(),
            GrouperGracePeriod.query(), "grouper", false, false);
        
      }
    }
    
    provisioningSyncConsumerResult.setLastProcessedSequenceNumber(esbEventContainers.get(esbEventContainers.size()-1).getSequenceNumber());
    return provisioningSyncConsumerResult;
  }

  /**
   * run job now and wait for cache to clear
   * @param now
   */
  private static void scheduleGraceLoaderNow(boolean waitForCache, Group group) {
  
    String jobName =  GrouperLoaderType.SQL_GROUP_LIST.name() + "__" + group.getName() + "__" + group.getUuid();
     
    GrouperLoader.runOnceByJobName(GrouperSession.staticGrouperSession(true), jobName);
    
    if (waitForCache) {
      GrouperUtil.sleep(60*1000);
    }
  }

  
//  /**
//   * run job now or in 5 minutes
//   * @param now
//   */
//  private static void scheduleGraceLoaderNow(boolean now) {
//
//    try {
//      String groupName = GrouperGracePeriod.gracePeriodStemName() + ":" + GrouperGracePeriod.GROUPER_GRACE_PERIOD_LOADER_GROUP_NAME;
//  
//      Group group = GrouperDAOFactory.getFactory().getGroup().findByNameSecure(
//          groupName, false, new QueryOptions().secondLevelCache(false), GrouperUtil.toSet(TypeOfGroup.group));
//  
//      Scheduler scheduler = GrouperLoader.schedulerFactory().getScheduler();
//  
//      String jobName =  GrouperLoaderType.SQL_GROUP_LIST.name() + "__" + group.getName() + "__" + group.getUuid();
//      
//      if (now) {
//        JobKey jobKey = new JobKey(jobName);
//        scheduler.triggerJob(jobKey);
//      } else {
//        
//        Thread thread = new Thread(new Runnable() {
//
//          @Override
//          public void run() {
//            GrouperUtil.sleep(1000*60*5);
//            scheduleGraceLoaderNow(true);
//          }
//          
//        });
//        thread.setDaemon(true);
//        thread.start();
////        JobKey jobKey = new JobKey(jobName);
////        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
////        
//////        JobBuilder.newJob(GrouperLoaderJob.class)
//////            .withIdentity(jobName)
//////            .build();
////        Trigger trigger = TriggerBuilder.newTrigger()
////            .startAt(new Date(Calendar.getInstance().getTimeInMillis()+ 1000*60*5)).build();
//////        scheduler.
//////        scheduler.deleteJob();
////        scheduler.deleteJob(jobKey);
////        scheduleJob(jobDetail, trigger);
//      }
//    
//    } catch (Exception e) {
//      throw new RuntimeException("Problem running job now", e);
//    }
//
//  }
}
