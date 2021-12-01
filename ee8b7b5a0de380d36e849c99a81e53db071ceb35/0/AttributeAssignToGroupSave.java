package edu.internet2.middleware.grouper.attr.assign;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import edu.internet2.middleware.grouper.Group;
import edu.internet2.middleware.grouper.GroupFinder;
import edu.internet2.middleware.grouper.GrouperSession;
import edu.internet2.middleware.grouper.attr.AttributeDefName;
import edu.internet2.middleware.grouper.attr.finder.AttributeDefNameFinder;
import edu.internet2.middleware.grouper.exception.GroupNotFoundException;
import edu.internet2.middleware.grouper.exception.InsufficientPrivilegeException;
import edu.internet2.middleware.grouper.hibernate.GrouperTransaction;
import edu.internet2.middleware.grouper.hibernate.GrouperTransactionHandler;
import edu.internet2.middleware.grouper.internal.dao.GrouperDAOException;
import edu.internet2.middleware.grouper.misc.SaveMode;
import edu.internet2.middleware.grouper.misc.SaveResultType;
import edu.internet2.middleware.grouper.util.GrouperUtil;

public class AttributeAssignToGroupSave {
  
  /**
   * attributeDefName
   */
  private AttributeDefName attributeDefName;

  /**
   * 
   * @param theAttributeDefName
   * @return this for chaining
   */
  public AttributeAssignToGroupSave assignAttributeDefName(AttributeDefName theAttributeDefName) {
    this.attributeDefName = theAttributeDefName;
    return this;
  }

  private String nameOfAttributeDefName;

  /**
   * 
   * @param theNameOfAttributeDefName
   * @return
   */
  public AttributeAssignToGroupSave assignNameOrAttributeDefName(String theNameOfAttributeDefName) {
    this.nameOfAttributeDefName = theNameOfAttributeDefName;
    return this;
  }
  
  private Group group;
  
  private String groupId;
  
  private String groupName;
  
  /** save mode */
  private SaveMode saveMode;
  
  /** save type after the save */
  private SaveResultType saveResultType = null;

  public AttributeAssignToGroupSave() {
    
  }
  
  /**
   * assign a group
   * @param theGroup
   * @return this for chaining
   */
  public AttributeAssignToGroupSave assignGroup(Group theGroup) {
    this.group = theGroup;
    return this;
  }

  /**
   * group id to add to, mutually exclusive with group name and group
   * @param theGroupId
   * @return this for chaining
   */
  public AttributeAssignToGroupSave assignGroupId(String theGroupId) {
    this.groupId = theGroupId;
    return this;
  }

  /**
   * group name to add to, mutually exclusive with group id and group
   * @param theGroupName
   * @return this for chaining
   */
  public AttributeAssignToGroupSave assignGroupName(String theGroupName) {
    this.groupName = theGroupName;
    return this;
  }

  /**
   * asssign save mode
   * @param theSaveMode
   * @return this for chaining
   */
  public AttributeAssignToGroupSave assignSaveMode(SaveMode theSaveMode) {
    this.saveMode = theSaveMode;
    return this;
  }

  /**
   * get the save type
   * @return save type
   */
  public SaveResultType getSaveResultType() {
    return this.saveResultType;
  }
  
  /**
   * <pre>
   * create or update or delete attribute assign on a group
   * </pre>
   * @return the composite that was updated or created or deleted
   */
  public AttributeAssign save() throws InsufficientPrivilegeException, GroupNotFoundException {

    //default to insert or update
    saveMode = (SaveMode)ObjectUtils.defaultIfNull(saveMode, SaveMode.INSERT_OR_UPDATE);
    
    AttributeAssign attributeAssign = (AttributeAssign)GrouperTransaction.callbackGrouperTransaction(new GrouperTransactionHandler() {
    
        public Object callback(GrouperTransaction grouperTransaction)
            throws GrouperDAOException {
          
          grouperTransaction.setCachingEnabled(false);
          
          if (group == null && !StringUtils.isBlank(AttributeAssignToGroupSave.this.groupId)) {
            group = GroupFinder.findByUuid(GrouperSession.staticGrouperSession(), AttributeAssignToGroupSave.this.groupId, false);
          } 
          if (group == null && !StringUtils.isBlank(AttributeAssignToGroupSave.this.groupName)) {
            group = GroupFinder.findByName(GrouperSession.staticGrouperSession(), AttributeAssignToGroupSave.this.groupName, false);
          }
          
          GrouperUtil.assertion(group!=null,  "Group not found");
          
          if (attributeDefName == null && !StringUtils.isBlank(AttributeAssignToGroupSave.this.nameOfAttributeDefName)) {
            attributeDefName = AttributeDefNameFinder.findByName(AttributeAssignToGroupSave.this.nameOfAttributeDefName, false);
          }
          
          GrouperUtil.assertion(attributeDefName!=null,  "AttributeDefName not found");

          
          // handle deletes
          if (saveMode == SaveMode.DELETE) {
            
            AttributeAssignResult attributeAssignResult = group.getAttributeDelegate().removeAttribute(attributeDefName);
            boolean changed = attributeAssignResult.isChanged();
            
            AttributeAssignToGroupSave.this.saveResultType = changed ? SaveResultType.DELETE : SaveResultType.NO_CHANGE;

            return attributeAssignResult.getAttributeAssign();
          }
          
          AttributeAssign attributeAssign = 
              group.getAttributeDelegate().retrieveAssignment(null, attributeDefName, true, false);
          
          AttributeAssignResult attributeAssignResult = group.getAttributeDelegate().assignAttribute(attributeDefName);
          boolean changed = attributeAssignResult.isChanged();
          
          if (saveMode == SaveMode.INSERT && !changed) {
            throw new RuntimeException("Inserting attribute to group but it already exists!");
          }
          if (saveMode == SaveMode.UPDATE && attributeAssign == null) {
            throw new RuntimeException("Updating attribute assign but it doesnt exist!");
          }
          
          if (attributeAssign == null) {
            AttributeAssignToGroupSave.this.saveResultType = SaveResultType.INSERT;
          } else {
            AttributeAssignToGroupSave.this.saveResultType = attributeAssignResult.isChanged() ? SaveResultType.UPDATE : SaveResultType.NO_CHANGE;
          }
          
          return attributeAssignResult.getAttributeAssign();
        }
    });
    
    
    return attributeAssign;
    
  }
  

}
