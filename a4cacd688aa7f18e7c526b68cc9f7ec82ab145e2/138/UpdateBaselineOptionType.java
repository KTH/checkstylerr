//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.3
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2021.01.12 at 08:36:06 AM GMT
//

package net.sf.mpxj.primavera.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * &lt;p&gt;Java class for UpdateBaselineOptionType complex type.
 *
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 *
 * &lt;pre&gt;
 * &amp;lt;complexType name="UpdateBaselineOptionType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="ActivitiesFilter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ActivitiesFilterLogic" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="255"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="ActivityCodeAssignments" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ActivityFilterId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ActivityFilterName" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="255"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="ActivityInformation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ActivityNotebooks" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ActivityRsrcAssignmentCodes" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ActivityRsrcAssignmentUdfs" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ActivityUdfs" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ActualUnitsCostWoRsrcAssignmnt" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="AddNewActivitiesData" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="AddNewRsrcRole" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="AllActivities" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="BatchModeEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="BudgetUnitsCost" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="BudgetUnitsCostWoRsrcAssignmnt" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Constraints" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DatesDurationDatadates" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DeleteNonExistingActivities" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ExpenseUdfs" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Expenses" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FilteredActivities" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="GeneralActivitiInfo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="IgnoreLastUpdateDate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="IssueUDFs" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="NewActivityInformation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="NewBudgetUnitsCost" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ObjectId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ProjectDetails" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ProjectRisksIssuesAndThresholds" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ProjectUDFs" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Relationships" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RiskAssignments" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RiskUDFs" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Steps" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="StepsUdf" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="UpdateExistRsrcRoleAssignment" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="UpdateExistingActivities" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="UserName" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="255"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="WPDocumentUDFs" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="WbsAssignments" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="WbsUDFs" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="WorkProductsAndDocuments" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD) @XmlType(name = "UpdateBaselineOptionType", propOrder =
{
   "activitiesFilter",
   "activitiesFilterLogic",
   "activityCodeAssignments",
   "activityFilterId",
   "activityFilterName",
   "activityInformation",
   "activityNotebooks",
   "activityRsrcAssignmentCodes",
   "activityRsrcAssignmentUdfs",
   "activityUdfs",
   "actualUnitsCostWoRsrcAssignmnt",
   "addNewActivitiesData",
   "addNewRsrcRole",
   "allActivities",
   "batchModeEnabled",
   "budgetUnitsCost",
   "budgetUnitsCostWoRsrcAssignmnt",
   "constraints",
   "datesDurationDatadates",
   "deleteNonExistingActivities",
   "expenseUdfs",
   "expenses",
   "filteredActivities",
   "generalActivitiInfo",
   "ignoreLastUpdateDate",
   "issueUDFs",
   "newActivityInformation",
   "newBudgetUnitsCost",
   "objectId",
   "projectDetails",
   "projectRisksIssuesAndThresholds",
   "projectUDFs",
   "relationships",
   "riskAssignments",
   "riskUDFs",
   "steps",
   "stepsUdf",
   "updateExistRsrcRoleAssignment",
   "updateExistingActivities",
   "userName",
   "wpDocumentUDFs",
   "wbsAssignments",
   "wbsUDFs",
   "workProductsAndDocuments"
}) public class UpdateBaselineOptionType
{

   @XmlElement(name = "ActivitiesFilter") protected String activitiesFilter;
   @XmlElement(name = "ActivitiesFilterLogic") protected String activitiesFilterLogic;
   @XmlElement(name = "ActivityCodeAssignments", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean activityCodeAssignments;
   @XmlElement(name = "ActivityFilterId") protected String activityFilterId;
   @XmlElement(name = "ActivityFilterName") protected String activityFilterName;
   @XmlElement(name = "ActivityInformation", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean activityInformation;
   @XmlElement(name = "ActivityNotebooks", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean activityNotebooks;
   @XmlElement(name = "ActivityRsrcAssignmentCodes", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean activityRsrcAssignmentCodes;
   @XmlElement(name = "ActivityRsrcAssignmentUdfs", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean activityRsrcAssignmentUdfs;
   @XmlElement(name = "ActivityUdfs", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean activityUdfs;
   @XmlElement(name = "ActualUnitsCostWoRsrcAssignmnt", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean actualUnitsCostWoRsrcAssignmnt;
   @XmlElement(name = "AddNewActivitiesData", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean addNewActivitiesData;
   @XmlElement(name = "AddNewRsrcRole", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean addNewRsrcRole;
   @XmlElement(name = "AllActivities", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean allActivities;
   @XmlElement(name = "BatchModeEnabled", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean batchModeEnabled;
   @XmlElement(name = "BudgetUnitsCost", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean budgetUnitsCost;
   @XmlElement(name = "BudgetUnitsCostWoRsrcAssignmnt", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean budgetUnitsCostWoRsrcAssignmnt;
   @XmlElement(name = "Constraints", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean constraints;
   @XmlElement(name = "DatesDurationDatadates", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean datesDurationDatadates;
   @XmlElement(name = "DeleteNonExistingActivities", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean deleteNonExistingActivities;
   @XmlElement(name = "ExpenseUdfs", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean expenseUdfs;
   @XmlElement(name = "Expenses", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean expenses;
   @XmlElement(name = "FilteredActivities", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean filteredActivities;
   @XmlElement(name = "GeneralActivitiInfo", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean generalActivitiInfo;
   @XmlElement(name = "IgnoreLastUpdateDate", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean ignoreLastUpdateDate;
   @XmlElement(name = "IssueUDFs", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean issueUDFs;
   @XmlElement(name = "NewActivityInformation", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean newActivityInformation;
   @XmlElement(name = "NewBudgetUnitsCost", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean newBudgetUnitsCost;
   @XmlElement(name = "ObjectId", nillable = true) protected Integer objectId;
   @XmlElement(name = "ProjectDetails", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean projectDetails;
   @XmlElement(name = "ProjectRisksIssuesAndThresholds", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean projectRisksIssuesAndThresholds;
   @XmlElement(name = "ProjectUDFs", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean projectUDFs;
   @XmlElement(name = "Relationships", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean relationships;
   @XmlElement(name = "RiskAssignments", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean riskAssignments;
   @XmlElement(name = "RiskUDFs", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean riskUDFs;
   @XmlElement(name = "Steps", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean steps;
   @XmlElement(name = "StepsUdf", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean stepsUdf;
   @XmlElement(name = "UpdateExistRsrcRoleAssignment", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean updateExistRsrcRoleAssignment;
   @XmlElement(name = "UpdateExistingActivities", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean updateExistingActivities;
   @XmlElement(name = "UserName") protected String userName;
   @XmlElement(name = "WPDocumentUDFs", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean wpDocumentUDFs;
   @XmlElement(name = "WbsAssignments", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean wbsAssignments;
   @XmlElement(name = "WbsUDFs", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean wbsUDFs;
   @XmlElement(name = "WorkProductsAndDocuments", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "boolean") protected Boolean workProductsAndDocuments;

   /**
    * Gets the value of the activitiesFilter property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public String getActivitiesFilter()
   {
      return activitiesFilter;
   }

   /**
    * Sets the value of the activitiesFilter property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setActivitiesFilter(String value)
   {
      this.activitiesFilter = value;
   }

   /**
    * Gets the value of the activitiesFilterLogic property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public String getActivitiesFilterLogic()
   {
      return activitiesFilterLogic;
   }

   /**
    * Sets the value of the activitiesFilterLogic property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setActivitiesFilterLogic(String value)
   {
      this.activitiesFilterLogic = value;
   }

   /**
    * Gets the value of the activityCodeAssignments property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isActivityCodeAssignments()
   {
      return activityCodeAssignments;
   }

   /**
    * Sets the value of the activityCodeAssignments property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setActivityCodeAssignments(Boolean value)
   {
      this.activityCodeAssignments = value;
   }

   /**
    * Gets the value of the activityFilterId property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public String getActivityFilterId()
   {
      return activityFilterId;
   }

   /**
    * Sets the value of the activityFilterId property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setActivityFilterId(String value)
   {
      this.activityFilterId = value;
   }

   /**
    * Gets the value of the activityFilterName property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public String getActivityFilterName()
   {
      return activityFilterName;
   }

   /**
    * Sets the value of the activityFilterName property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setActivityFilterName(String value)
   {
      this.activityFilterName = value;
   }

   /**
    * Gets the value of the activityInformation property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isActivityInformation()
   {
      return activityInformation;
   }

   /**
    * Sets the value of the activityInformation property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setActivityInformation(Boolean value)
   {
      this.activityInformation = value;
   }

   /**
    * Gets the value of the activityNotebooks property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isActivityNotebooks()
   {
      return activityNotebooks;
   }

   /**
    * Sets the value of the activityNotebooks property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setActivityNotebooks(Boolean value)
   {
      this.activityNotebooks = value;
   }

   /**
    * Gets the value of the activityRsrcAssignmentCodes property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isActivityRsrcAssignmentCodes()
   {
      return activityRsrcAssignmentCodes;
   }

   /**
    * Sets the value of the activityRsrcAssignmentCodes property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setActivityRsrcAssignmentCodes(Boolean value)
   {
      this.activityRsrcAssignmentCodes = value;
   }

   /**
    * Gets the value of the activityRsrcAssignmentUdfs property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isActivityRsrcAssignmentUdfs()
   {
      return activityRsrcAssignmentUdfs;
   }

   /**
    * Sets the value of the activityRsrcAssignmentUdfs property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setActivityRsrcAssignmentUdfs(Boolean value)
   {
      this.activityRsrcAssignmentUdfs = value;
   }

   /**
    * Gets the value of the activityUdfs property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isActivityUdfs()
   {
      return activityUdfs;
   }

   /**
    * Sets the value of the activityUdfs property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setActivityUdfs(Boolean value)
   {
      this.activityUdfs = value;
   }

   /**
    * Gets the value of the actualUnitsCostWoRsrcAssignmnt property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isActualUnitsCostWoRsrcAssignmnt()
   {
      return actualUnitsCostWoRsrcAssignmnt;
   }

   /**
    * Sets the value of the actualUnitsCostWoRsrcAssignmnt property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setActualUnitsCostWoRsrcAssignmnt(Boolean value)
   {
      this.actualUnitsCostWoRsrcAssignmnt = value;
   }

   /**
    * Gets the value of the addNewActivitiesData property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isAddNewActivitiesData()
   {
      return addNewActivitiesData;
   }

   /**
    * Sets the value of the addNewActivitiesData property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setAddNewActivitiesData(Boolean value)
   {
      this.addNewActivitiesData = value;
   }

   /**
    * Gets the value of the addNewRsrcRole property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isAddNewRsrcRole()
   {
      return addNewRsrcRole;
   }

   /**
    * Sets the value of the addNewRsrcRole property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setAddNewRsrcRole(Boolean value)
   {
      this.addNewRsrcRole = value;
   }

   /**
    * Gets the value of the allActivities property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isAllActivities()
   {
      return allActivities;
   }

   /**
    * Sets the value of the allActivities property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setAllActivities(Boolean value)
   {
      this.allActivities = value;
   }

   /**
    * Gets the value of the batchModeEnabled property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isBatchModeEnabled()
   {
      return batchModeEnabled;
   }

   /**
    * Sets the value of the batchModeEnabled property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setBatchModeEnabled(Boolean value)
   {
      this.batchModeEnabled = value;
   }

   /**
    * Gets the value of the budgetUnitsCost property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isBudgetUnitsCost()
   {
      return budgetUnitsCost;
   }

   /**
    * Sets the value of the budgetUnitsCost property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setBudgetUnitsCost(Boolean value)
   {
      this.budgetUnitsCost = value;
   }

   /**
    * Gets the value of the budgetUnitsCostWoRsrcAssignmnt property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isBudgetUnitsCostWoRsrcAssignmnt()
   {
      return budgetUnitsCostWoRsrcAssignmnt;
   }

   /**
    * Sets the value of the budgetUnitsCostWoRsrcAssignmnt property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setBudgetUnitsCostWoRsrcAssignmnt(Boolean value)
   {
      this.budgetUnitsCostWoRsrcAssignmnt = value;
   }

   /**
    * Gets the value of the constraints property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isConstraints()
   {
      return constraints;
   }

   /**
    * Sets the value of the constraints property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setConstraints(Boolean value)
   {
      this.constraints = value;
   }

   /**
    * Gets the value of the datesDurationDatadates property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isDatesDurationDatadates()
   {
      return datesDurationDatadates;
   }

   /**
    * Sets the value of the datesDurationDatadates property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setDatesDurationDatadates(Boolean value)
   {
      this.datesDurationDatadates = value;
   }

   /**
    * Gets the value of the deleteNonExistingActivities property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isDeleteNonExistingActivities()
   {
      return deleteNonExistingActivities;
   }

   /**
    * Sets the value of the deleteNonExistingActivities property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setDeleteNonExistingActivities(Boolean value)
   {
      this.deleteNonExistingActivities = value;
   }

   /**
    * Gets the value of the expenseUdfs property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isExpenseUdfs()
   {
      return expenseUdfs;
   }

   /**
    * Sets the value of the expenseUdfs property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setExpenseUdfs(Boolean value)
   {
      this.expenseUdfs = value;
   }

   /**
    * Gets the value of the expenses property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isExpenses()
   {
      return expenses;
   }

   /**
    * Sets the value of the expenses property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setExpenses(Boolean value)
   {
      this.expenses = value;
   }

   /**
    * Gets the value of the filteredActivities property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isFilteredActivities()
   {
      return filteredActivities;
   }

   /**
    * Sets the value of the filteredActivities property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setFilteredActivities(Boolean value)
   {
      this.filteredActivities = value;
   }

   /**
    * Gets the value of the generalActivitiInfo property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isGeneralActivitiInfo()
   {
      return generalActivitiInfo;
   }

   /**
    * Sets the value of the generalActivitiInfo property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setGeneralActivitiInfo(Boolean value)
   {
      this.generalActivitiInfo = value;
   }

   /**
    * Gets the value of the ignoreLastUpdateDate property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isIgnoreLastUpdateDate()
   {
      return ignoreLastUpdateDate;
   }

   /**
    * Sets the value of the ignoreLastUpdateDate property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setIgnoreLastUpdateDate(Boolean value)
   {
      this.ignoreLastUpdateDate = value;
   }

   /**
    * Gets the value of the issueUDFs property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isIssueUDFs()
   {
      return issueUDFs;
   }

   /**
    * Sets the value of the issueUDFs property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setIssueUDFs(Boolean value)
   {
      this.issueUDFs = value;
   }

   /**
    * Gets the value of the newActivityInformation property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isNewActivityInformation()
   {
      return newActivityInformation;
   }

   /**
    * Sets the value of the newActivityInformation property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setNewActivityInformation(Boolean value)
   {
      this.newActivityInformation = value;
   }

   /**
    * Gets the value of the newBudgetUnitsCost property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isNewBudgetUnitsCost()
   {
      return newBudgetUnitsCost;
   }

   /**
    * Sets the value of the newBudgetUnitsCost property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setNewBudgetUnitsCost(Boolean value)
   {
      this.newBudgetUnitsCost = value;
   }

   /**
    * Gets the value of the objectId property.
    * 
    * @return
    *     possible object is
    *     {@link Integer }
    *     
    */
   public Integer getObjectId()
   {
      return objectId;
   }

   /**
    * Sets the value of the objectId property.
    * 
    * @param value
    *     allowed object is
    *     {@link Integer }
    *     
    */
   public void setObjectId(Integer value)
   {
      this.objectId = value;
   }

   /**
    * Gets the value of the projectDetails property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isProjectDetails()
   {
      return projectDetails;
   }

   /**
    * Sets the value of the projectDetails property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setProjectDetails(Boolean value)
   {
      this.projectDetails = value;
   }

   /**
    * Gets the value of the projectRisksIssuesAndThresholds property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isProjectRisksIssuesAndThresholds()
   {
      return projectRisksIssuesAndThresholds;
   }

   /**
    * Sets the value of the projectRisksIssuesAndThresholds property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setProjectRisksIssuesAndThresholds(Boolean value)
   {
      this.projectRisksIssuesAndThresholds = value;
   }

   /**
    * Gets the value of the projectUDFs property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isProjectUDFs()
   {
      return projectUDFs;
   }

   /**
    * Sets the value of the projectUDFs property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setProjectUDFs(Boolean value)
   {
      this.projectUDFs = value;
   }

   /**
    * Gets the value of the relationships property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isRelationships()
   {
      return relationships;
   }

   /**
    * Sets the value of the relationships property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setRelationships(Boolean value)
   {
      this.relationships = value;
   }

   /**
    * Gets the value of the riskAssignments property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isRiskAssignments()
   {
      return riskAssignments;
   }

   /**
    * Sets the value of the riskAssignments property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setRiskAssignments(Boolean value)
   {
      this.riskAssignments = value;
   }

   /**
    * Gets the value of the riskUDFs property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isRiskUDFs()
   {
      return riskUDFs;
   }

   /**
    * Sets the value of the riskUDFs property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setRiskUDFs(Boolean value)
   {
      this.riskUDFs = value;
   }

   /**
    * Gets the value of the steps property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isSteps()
   {
      return steps;
   }

   /**
    * Sets the value of the steps property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setSteps(Boolean value)
   {
      this.steps = value;
   }

   /**
    * Gets the value of the stepsUdf property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isStepsUdf()
   {
      return stepsUdf;
   }

   /**
    * Sets the value of the stepsUdf property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setStepsUdf(Boolean value)
   {
      this.stepsUdf = value;
   }

   /**
    * Gets the value of the updateExistRsrcRoleAssignment property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isUpdateExistRsrcRoleAssignment()
   {
      return updateExistRsrcRoleAssignment;
   }

   /**
    * Sets the value of the updateExistRsrcRoleAssignment property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setUpdateExistRsrcRoleAssignment(Boolean value)
   {
      this.updateExistRsrcRoleAssignment = value;
   }

   /**
    * Gets the value of the updateExistingActivities property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isUpdateExistingActivities()
   {
      return updateExistingActivities;
   }

   /**
    * Sets the value of the updateExistingActivities property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setUpdateExistingActivities(Boolean value)
   {
      this.updateExistingActivities = value;
   }

   /**
    * Gets the value of the userName property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public String getUserName()
   {
      return userName;
   }

   /**
    * Sets the value of the userName property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setUserName(String value)
   {
      this.userName = value;
   }

   /**
    * Gets the value of the wpDocumentUDFs property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isWPDocumentUDFs()
   {
      return wpDocumentUDFs;
   }

   /**
    * Sets the value of the wpDocumentUDFs property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setWPDocumentUDFs(Boolean value)
   {
      this.wpDocumentUDFs = value;
   }

   /**
    * Gets the value of the wbsAssignments property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isWbsAssignments()
   {
      return wbsAssignments;
   }

   /**
    * Sets the value of the wbsAssignments property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setWbsAssignments(Boolean value)
   {
      this.wbsAssignments = value;
   }

   /**
    * Gets the value of the wbsUDFs property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isWbsUDFs()
   {
      return wbsUDFs;
   }

   /**
    * Sets the value of the wbsUDFs property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setWbsUDFs(Boolean value)
   {
      this.wbsUDFs = value;
   }

   /**
    * Gets the value of the workProductsAndDocuments property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Boolean isWorkProductsAndDocuments()
   {
      return workProductsAndDocuments;
   }

   /**
    * Sets the value of the workProductsAndDocuments property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setWorkProductsAndDocuments(Boolean value)
   {
      this.workProductsAndDocuments = value;
   }

}
