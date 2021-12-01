//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.3
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2020.12.29 at 11:06:27 AM GMT
//

package net.sf.mpxj.primavera.schema;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * &lt;p&gt;Java class for ActivityPeriodActualType complex type.
 *
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 *
 * &lt;pre&gt;
 * &amp;lt;complexType name="ActivityPeriodActualType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="ActivityObjectId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ActualExpenseCost" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ActualLaborCost" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ActualLaborUnits" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ActualMaterialCost" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ActualNonLaborCost" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ActualNonLaborUnits" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="CreateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="CreateUser" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="255"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="EarnedValueCost" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="EarnedValueLaborUnits" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FinancialPeriodObjectId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="IsBaseline" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="IsTemplate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LastUpdateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LastUpdateUser" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="255"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="PlannedValueCost" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="PlannedValueLaborUnits" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ProjectObjectId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="WBSObjectId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD) @XmlType(name = "ActivityPeriodActualType", propOrder =
{
   "activityObjectId",
   "actualExpenseCost",
   "actualLaborCost",
   "actualLaborUnits",
   "actualMaterialCost",
   "actualNonLaborCost",
   "actualNonLaborUnits",
   "createDate",
   "createUser",
   "earnedValueCost",
   "earnedValueLaborUnits",
   "financialPeriodObjectId",
   "isBaseline",
   "isTemplate",
   "lastUpdateDate",
   "lastUpdateUser",
   "plannedValueCost",
   "plannedValueLaborUnits",
   "projectObjectId",
   "wbsObjectId"
}) public class ActivityPeriodActualType
{

   @XmlElement(name = "ActivityObjectId") protected Integer activityObjectId;
   @XmlElement(name = "ActualExpenseCost", nillable = true) protected Double actualExpenseCost;
   @XmlElement(name = "ActualLaborCost", nillable = true) protected Double actualLaborCost;
   @XmlElement(name = "ActualLaborUnits", nillable = true) protected Double actualLaborUnits;
   @XmlElement(name = "ActualMaterialCost", nillable = true) protected Double actualMaterialCost;
   @XmlElement(name = "ActualNonLaborCost", nillable = true) protected Double actualNonLaborCost;
   @XmlElement(name = "ActualNonLaborUnits", nillable = true) protected Double actualNonLaborUnits;
   @XmlElement(name = "CreateDate", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "dateTime") protected Date createDate;
   @XmlElement(name = "CreateUser") protected String createUser;
   @XmlElement(name = "EarnedValueCost", nillable = true) protected Double earnedValueCost;
   @XmlElement(name = "EarnedValueLaborUnits", nillable = true) protected Double earnedValueLaborUnits;
   @XmlElement(name = "FinancialPeriodObjectId") protected Integer financialPeriodObjectId;
   @XmlElement(name = "IsBaseline") protected Boolean isBaseline;
   @XmlElement(name = "IsTemplate") protected Boolean isTemplate;
   @XmlElement(name = "LastUpdateDate", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "dateTime") protected Date lastUpdateDate;
   @XmlElement(name = "LastUpdateUser") protected String lastUpdateUser;
   @XmlElement(name = "PlannedValueCost", nillable = true) protected Double plannedValueCost;
   @XmlElement(name = "PlannedValueLaborUnits", nillable = true) protected Double plannedValueLaborUnits;
   @XmlElement(name = "ProjectObjectId") protected Integer projectObjectId;
   @XmlElement(name = "WBSObjectId", nillable = true) protected Integer wbsObjectId;

   /**
    * Gets the value of the activityObjectId property.
    * 
    * @return
    *     possible object is
    *     {@link Integer }
    *     
    */
   public Integer getActivityObjectId()
   {
      return activityObjectId;
   }

   /**
    * Sets the value of the activityObjectId property.
    * 
    * @param value
    *     allowed object is
    *     {@link Integer }
    *     
    */
   public void setActivityObjectId(Integer value)
   {
      this.activityObjectId = value;
   }

   /**
    * Gets the value of the actualExpenseCost property.
    * 
    * @return
    *     possible object is
    *     {@link Double }
    *     
    */
   public Double getActualExpenseCost()
   {
      return actualExpenseCost;
   }

   /**
    * Sets the value of the actualExpenseCost property.
    * 
    * @param value
    *     allowed object is
    *     {@link Double }
    *     
    */
   public void setActualExpenseCost(Double value)
   {
      this.actualExpenseCost = value;
   }

   /**
    * Gets the value of the actualLaborCost property.
    * 
    * @return
    *     possible object is
    *     {@link Double }
    *     
    */
   public Double getActualLaborCost()
   {
      return actualLaborCost;
   }

   /**
    * Sets the value of the actualLaborCost property.
    * 
    * @param value
    *     allowed object is
    *     {@link Double }
    *     
    */
   public void setActualLaborCost(Double value)
   {
      this.actualLaborCost = value;
   }

   /**
    * Gets the value of the actualLaborUnits property.
    * 
    * @return
    *     possible object is
    *     {@link Double }
    *     
    */
   public Double getActualLaborUnits()
   {
      return actualLaborUnits;
   }

   /**
    * Sets the value of the actualLaborUnits property.
    * 
    * @param value
    *     allowed object is
    *     {@link Double }
    *     
    */
   public void setActualLaborUnits(Double value)
   {
      this.actualLaborUnits = value;
   }

   /**
    * Gets the value of the actualMaterialCost property.
    * 
    * @return
    *     possible object is
    *     {@link Double }
    *     
    */
   public Double getActualMaterialCost()
   {
      return actualMaterialCost;
   }

   /**
    * Sets the value of the actualMaterialCost property.
    * 
    * @param value
    *     allowed object is
    *     {@link Double }
    *     
    */
   public void setActualMaterialCost(Double value)
   {
      this.actualMaterialCost = value;
   }

   /**
    * Gets the value of the actualNonLaborCost property.
    * 
    * @return
    *     possible object is
    *     {@link Double }
    *     
    */
   public Double getActualNonLaborCost()
   {
      return actualNonLaborCost;
   }

   /**
    * Sets the value of the actualNonLaborCost property.
    * 
    * @param value
    *     allowed object is
    *     {@link Double }
    *     
    */
   public void setActualNonLaborCost(Double value)
   {
      this.actualNonLaborCost = value;
   }

   /**
    * Gets the value of the actualNonLaborUnits property.
    * 
    * @return
    *     possible object is
    *     {@link Double }
    *     
    */
   public Double getActualNonLaborUnits()
   {
      return actualNonLaborUnits;
   }

   /**
    * Sets the value of the actualNonLaborUnits property.
    * 
    * @param value
    *     allowed object is
    *     {@link Double }
    *     
    */
   public void setActualNonLaborUnits(Double value)
   {
      this.actualNonLaborUnits = value;
   }

   /**
    * Gets the value of the createDate property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Date getCreateDate()
   {
      return createDate;
   }

   /**
    * Sets the value of the createDate property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setCreateDate(Date value)
   {
      this.createDate = value;
   }

   /**
    * Gets the value of the createUser property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public String getCreateUser()
   {
      return createUser;
   }

   /**
    * Sets the value of the createUser property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setCreateUser(String value)
   {
      this.createUser = value;
   }

   /**
    * Gets the value of the earnedValueCost property.
    * 
    * @return
    *     possible object is
    *     {@link Double }
    *     
    */
   public Double getEarnedValueCost()
   {
      return earnedValueCost;
   }

   /**
    * Sets the value of the earnedValueCost property.
    * 
    * @param value
    *     allowed object is
    *     {@link Double }
    *     
    */
   public void setEarnedValueCost(Double value)
   {
      this.earnedValueCost = value;
   }

   /**
    * Gets the value of the earnedValueLaborUnits property.
    * 
    * @return
    *     possible object is
    *     {@link Double }
    *     
    */
   public Double getEarnedValueLaborUnits()
   {
      return earnedValueLaborUnits;
   }

   /**
    * Sets the value of the earnedValueLaborUnits property.
    * 
    * @param value
    *     allowed object is
    *     {@link Double }
    *     
    */
   public void setEarnedValueLaborUnits(Double value)
   {
      this.earnedValueLaborUnits = value;
   }

   /**
    * Gets the value of the financialPeriodObjectId property.
    * 
    * @return
    *     possible object is
    *     {@link Integer }
    *     
    */
   public Integer getFinancialPeriodObjectId()
   {
      return financialPeriodObjectId;
   }

   /**
    * Sets the value of the financialPeriodObjectId property.
    * 
    * @param value
    *     allowed object is
    *     {@link Integer }
    *     
    */
   public void setFinancialPeriodObjectId(Integer value)
   {
      this.financialPeriodObjectId = value;
   }

   /**
    * Gets the value of the isBaseline property.
    * 
    * @return
    *     possible object is
    *     {@link Boolean }
    *     
    */
   public Boolean isIsBaseline()
   {
      return isBaseline;
   }

   /**
    * Sets the value of the isBaseline property.
    * 
    * @param value
    *     allowed object is
    *     {@link Boolean }
    *     
    */
   public void setIsBaseline(Boolean value)
   {
      this.isBaseline = value;
   }

   /**
    * Gets the value of the isTemplate property.
    * 
    * @return
    *     possible object is
    *     {@link Boolean }
    *     
    */
   public Boolean isIsTemplate()
   {
      return isTemplate;
   }

   /**
    * Sets the value of the isTemplate property.
    * 
    * @param value
    *     allowed object is
    *     {@link Boolean }
    *     
    */
   public void setIsTemplate(Boolean value)
   {
      this.isTemplate = value;
   }

   /**
    * Gets the value of the lastUpdateDate property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Date getLastUpdateDate()
   {
      return lastUpdateDate;
   }

   /**
    * Sets the value of the lastUpdateDate property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setLastUpdateDate(Date value)
   {
      this.lastUpdateDate = value;
   }

   /**
    * Gets the value of the lastUpdateUser property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public String getLastUpdateUser()
   {
      return lastUpdateUser;
   }

   /**
    * Sets the value of the lastUpdateUser property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setLastUpdateUser(String value)
   {
      this.lastUpdateUser = value;
   }

   /**
    * Gets the value of the plannedValueCost property.
    * 
    * @return
    *     possible object is
    *     {@link Double }
    *     
    */
   public Double getPlannedValueCost()
   {
      return plannedValueCost;
   }

   /**
    * Sets the value of the plannedValueCost property.
    * 
    * @param value
    *     allowed object is
    *     {@link Double }
    *     
    */
   public void setPlannedValueCost(Double value)
   {
      this.plannedValueCost = value;
   }

   /**
    * Gets the value of the plannedValueLaborUnits property.
    * 
    * @return
    *     possible object is
    *     {@link Double }
    *     
    */
   public Double getPlannedValueLaborUnits()
   {
      return plannedValueLaborUnits;
   }

   /**
    * Sets the value of the plannedValueLaborUnits property.
    * 
    * @param value
    *     allowed object is
    *     {@link Double }
    *     
    */
   public void setPlannedValueLaborUnits(Double value)
   {
      this.plannedValueLaborUnits = value;
   }

   /**
    * Gets the value of the projectObjectId property.
    * 
    * @return
    *     possible object is
    *     {@link Integer }
    *     
    */
   public Integer getProjectObjectId()
   {
      return projectObjectId;
   }

   /**
    * Sets the value of the projectObjectId property.
    * 
    * @param value
    *     allowed object is
    *     {@link Integer }
    *     
    */
   public void setProjectObjectId(Integer value)
   {
      this.projectObjectId = value;
   }

   /**
    * Gets the value of the wbsObjectId property.
    * 
    * @return
    *     possible object is
    *     {@link Integer }
    *     
    */
   public Integer getWBSObjectId()
   {
      return wbsObjectId;
   }

   /**
    * Sets the value of the wbsObjectId property.
    * 
    * @param value
    *     allowed object is
    *     {@link Integer }
    *     
    */
   public void setWBSObjectId(Integer value)
   {
      this.wbsObjectId = value;
   }

}
