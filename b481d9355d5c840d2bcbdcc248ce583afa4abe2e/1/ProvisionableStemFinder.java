package edu.internet2.middleware.grouper.app.provisioning;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import edu.internet2.middleware.grouper.GrouperSession;
import edu.internet2.middleware.grouper.Stem;
import edu.internet2.middleware.grouper.StemFinder;
import edu.internet2.middleware.grouper.exception.GrouperSessionException;
import edu.internet2.middleware.grouper.internal.dao.QueryOptions;
import edu.internet2.middleware.grouper.misc.GrouperSessionHandler;
import edu.internet2.middleware.grouper.privs.NamingPrivilege;
import edu.internet2.middleware.grouper.privs.PrivilegeHelper;
import edu.internet2.middleware.grouper.util.GrouperUtil;
import edu.internet2.middleware.grouperClientExt.org.apache.commons.lang3.StringUtils;
import edu.internet2.middleware.subject.Subject;
import edu.internet2.middleware.subject.SubjectUtils;

public class ProvisionableStemFinder {
  
  private Stem stem;
  
  private String stemId;
  
  private String stemName;
  
  private String targetName;
  
  private boolean runAsRoot;
  
  /**
   * if null (default) retrieve direct and indirect assignments, if true then only retrieve direct assignments,
   * if false only retrieve indirect assignments
   */
  private Boolean directAssignment;
  
  public ProvisionableStemFinder assignStem(Stem stem) {
    this.stem = stem;
    return this;
  }
  
  public ProvisionableStemFinder assignStemId(String stemId) {
    this.stemId = stemId;
    return this;
  } 
  
  public ProvisionableStemFinder assignStemName(String stemName) {
    this.stemName = stemName;
    return this;
  }
  
  public ProvisionableStemFinder assignTargetName(String targetName) {
    this.targetName = targetName;
    return this;
  }
  
  public ProvisionableStemFinder assignRunAsRoot(boolean runAsRoot) {
    this.runAsRoot = runAsRoot;
    return this;
  }
  
  public ProvisionableStemFinder assignDirectAssignment(Boolean directAssignment) {
    this.directAssignment = directAssignment;
    return this;
  }

  
  public GrouperProvisioningAttributeValue findProvisionableStemAttributeValue() {
    
    Set<GrouperProvisioningAttributeValue> grouperProvisioningAttributeValues = this.findProvisionableStemAttributeValues();

    return GrouperUtil.setPopOne(grouperProvisioningAttributeValues);
  }
  
  @SuppressWarnings("unchecked")
  public Set<GrouperProvisioningAttributeValue> findProvisionableStemAttributeValues() {
    
    Subject SUBJECT_IN_SESSION = GrouperSession.staticGrouperSession().getSubject();
    
    Set<GrouperProvisioningAttributeValue> grouperProvisioningAttributeValues = (Set<GrouperProvisioningAttributeValue>) GrouperSession.internal_callbackRootGrouperSession(new GrouperSessionHandler() {

      @Override
      public Object callback(GrouperSession grouperSession) throws GrouperSessionException {
        
        if (stem == null && !StringUtils.isBlank(stemId)) {
          stem = StemFinder.findByUuid(GrouperSession.staticGrouperSession(), stemId, false, new QueryOptions().secondLevelCache(false));
        }
        
        if (stem == null && !StringUtils.isBlank(stemName)) {
          stem = StemFinder.findByName(GrouperSession.staticGrouperSession(), stemName, false, new QueryOptions().secondLevelCache(false));
        }
        
        GrouperUtil.assertion(stem != null,  "Stem not found");
        
        if (!runAsRoot) {
          if (!PrivilegeHelper.isWheelOrRoot(SUBJECT_IN_SESSION)) {
            throw new RuntimeException("Subject '" + SubjectUtils.subjectToString(SUBJECT_IN_SESSION)+ "' is not wheel or root user.");
          }
        }
        
        if (StringUtils.isNotBlank(targetName) &&  !GrouperProvisioningSettings.getTargets(true).containsKey(targetName)) {
          throw new RuntimeException("target must be one of the valid targets ["+GrouperUtil.collectionToString(GrouperProvisioningSettings.getTargets(true).keySet()) + "]");
        }
        
        Set<GrouperProvisioningAttributeValue> result = new HashSet<GrouperProvisioningAttributeValue>();
        
        if (StringUtils.isNotBlank(targetName)) {
          GrouperProvisioningAttributeValue provisioningAttributeValue = GrouperProvisioningService.getProvisioningAttributeValue(stem, targetName);
          if (provisioningAttributeValue != null) {            
            result.add(provisioningAttributeValue);
          }
        } else {
          List<GrouperProvisioningAttributeValue> provisioningAttributeValues = GrouperProvisioningService.getProvisioningAttributeValues(stem);
          result.addAll(GrouperUtil.nonNull(provisioningAttributeValues));
        }
        
        if (directAssignment == null) {
          return result;
        } else if (directAssignment) {
          return result.stream().filter(attributeValue -> attributeValue.isDirectAssignment()).collect(Collectors.toSet());
        } else {
          return result.stream().filter(attributeValue -> !attributeValue.isDirectAssignment()).collect(Collectors.toSet());
        }
        
      }});
    
    return grouperProvisioningAttributeValues;
    
    
  }
  

}
