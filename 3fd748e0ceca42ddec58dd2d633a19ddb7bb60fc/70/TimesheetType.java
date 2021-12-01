//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2017.09.18 at 02:35:45 PM BST
//

package net.sf.mpxj.primavera.schema;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>Java class for TimesheetType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TimesheetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CreateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="CreateUser" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="IsDaily" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="LastReceivedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="LastUpdateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="LastUpdateUser" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Notes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ResourceId" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ResourceName" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ResourceObjectId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Status" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Submitted"/>
 *               &lt;enumeration value="Approved"/>
 *               &lt;enumeration value="Resource Manager Approved"/>
 *               &lt;enumeration value="Project Manager Approved"/>
 *               &lt;enumeration value="Active"/>
 *               &lt;enumeration value="Rejected"/>
 *               &lt;enumeration value="Not Started"/>
 *               &lt;enumeration value="Resubmitted"/>
 *               &lt;enumeration value="Reopened"/>
 *               &lt;enumeration value="Submitted for RM"/>
 *               &lt;enumeration value="Resubmitted for RM"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="StatusDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="TimesheetPeriodObjectId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ResourceHour" type="{http://xmlns.oracle.com/Primavera/P6/V17.7/API/BusinessObjects}ResourceHourType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD) @XmlType(name = "TimesheetType", propOrder =
{
   "createDate",
   "createUser",
   "isDaily",
   "lastReceivedDate",
   "lastUpdateDate",
   "lastUpdateUser",
   "notes",
   "resourceId",
   "resourceName",
   "resourceObjectId",
   "status",
   "statusDate",
   "timesheetPeriodObjectId",
   "resourceHour"
}) public class TimesheetType
{

   @XmlElement(name = "CreateDate", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "dateTime") protected Date createDate;
   @XmlElement(name = "CreateUser") protected String createUser;
   @XmlElement(name = "IsDaily") protected Boolean isDaily;
   @XmlElement(name = "LastReceivedDate", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "dateTime") protected Date lastReceivedDate;
   @XmlElement(name = "LastUpdateDate", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "dateTime") protected Date lastUpdateDate;
   @XmlElement(name = "LastUpdateUser") protected String lastUpdateUser;
   @XmlElement(name = "Notes") protected String notes;
   @XmlElement(name = "ResourceId") protected String resourceId;
   @XmlElement(name = "ResourceName") protected String resourceName;
   @XmlElement(name = "ResourceObjectId") protected Integer resourceObjectId;
   @XmlElement(name = "Status") protected String status;
   @XmlElement(name = "StatusDate", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "dateTime") protected Date statusDate;
   @XmlElement(name = "TimesheetPeriodObjectId") protected Integer timesheetPeriodObjectId;
   @XmlElement(name = "ResourceHour") protected List<ResourceHourType> resourceHour;

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
    * Gets the value of the isDaily property.
    *
    * @return
    *     possible object is
    *     {@link Boolean }
    *
    */
   public Boolean isIsDaily()
   {
      return isDaily;
   }

   /**
    * Sets the value of the isDaily property.
    *
    * @param value
    *     allowed object is
    *     {@link Boolean }
    *
    */
   public void setIsDaily(Boolean value)
   {
      this.isDaily = value;
   }

   /**
    * Gets the value of the lastReceivedDate property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public Date getLastReceivedDate()
   {
      return lastReceivedDate;
   }

   /**
    * Sets the value of the lastReceivedDate property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setLastReceivedDate(Date value)
   {
      this.lastReceivedDate = value;
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
    * Gets the value of the notes property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getNotes()
   {
      return notes;
   }

   /**
    * Sets the value of the notes property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setNotes(String value)
   {
      this.notes = value;
   }

   /**
    * Gets the value of the resourceId property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getResourceId()
   {
      return resourceId;
   }

   /**
    * Sets the value of the resourceId property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setResourceId(String value)
   {
      this.resourceId = value;
   }

   /**
    * Gets the value of the resourceName property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getResourceName()
   {
      return resourceName;
   }

   /**
    * Sets the value of the resourceName property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setResourceName(String value)
   {
      this.resourceName = value;
   }

   /**
    * Gets the value of the resourceObjectId property.
    *
    * @return
    *     possible object is
    *     {@link Integer }
    *
    */
   public Integer getResourceObjectId()
   {
      return resourceObjectId;
   }

   /**
    * Sets the value of the resourceObjectId property.
    *
    * @param value
    *     allowed object is
    *     {@link Integer }
    *
    */
   public void setResourceObjectId(Integer value)
   {
      this.resourceObjectId = value;
   }

   /**
    * Gets the value of the status property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getStatus()
   {
      return status;
   }

   /**
    * Sets the value of the status property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setStatus(String value)
   {
      this.status = value;
   }

   /**
    * Gets the value of the statusDate property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public Date getStatusDate()
   {
      return statusDate;
   }

   /**
    * Sets the value of the statusDate property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setStatusDate(Date value)
   {
      this.statusDate = value;
   }

   /**
    * Gets the value of the timesheetPeriodObjectId property.
    *
    * @return
    *     possible object is
    *     {@link Integer }
    *
    */
   public Integer getTimesheetPeriodObjectId()
   {
      return timesheetPeriodObjectId;
   }

   /**
    * Sets the value of the timesheetPeriodObjectId property.
    *
    * @param value
    *     allowed object is
    *     {@link Integer }
    *
    */
   public void setTimesheetPeriodObjectId(Integer value)
   {
      this.timesheetPeriodObjectId = value;
   }

   /**
    * Gets the value of the resourceHour property.
    *
    * <p>
    * This accessor method returns a reference to the live list,
    * not a snapshot. Therefore any modification you make to the
    * returned list will be present inside the JAXB object.
    * This is why there is not a <CODE>set</CODE> method for the resourceHour property.
    *
    * <p>
    * For example, to add a new item, do as follows:
    * <pre>
    *    getResourceHour().add(newItem);
    * </pre>
    *
    *
    * <p>
    * Objects of the following type(s) are allowed in the list
    * {@link ResourceHourType }
    *
    *
    */
   public List<ResourceHourType> getResourceHour()
   {
      if (resourceHour == null)
      {
         resourceHour = new ArrayList<>();
      }
      return this.resourceHour;
   }

}
