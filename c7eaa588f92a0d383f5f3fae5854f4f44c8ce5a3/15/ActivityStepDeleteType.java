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
 * &lt;p&gt;Java class for ActivityStepDeleteType complex type.
 *
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 *
 * &lt;pre&gt;
 * &amp;lt;complexType name="ActivityStepDeleteType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="ActivityObjectId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ActivityStepName" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="120"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="ActivityStepObjectId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ChangeSetObjectId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RequestUserObjectId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD) @XmlType(name = "ActivityStepDeleteType", propOrder =
{
   "activityObjectId",
   "activityStepName",
   "activityStepObjectId",
   "changeSetObjectId",
   "date",
   "requestUserObjectId"
}) public class ActivityStepDeleteType
{

   @XmlElement(name = "ActivityObjectId") protected Integer activityObjectId;
   @XmlElement(name = "ActivityStepName") protected String activityStepName;
   @XmlElement(name = "ActivityStepObjectId") protected Integer activityStepObjectId;
   @XmlElement(name = "ChangeSetObjectId") protected Integer changeSetObjectId;
   @XmlElement(name = "Date", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "dateTime") protected Date date;
   @XmlElement(name = "RequestUserObjectId", nillable = true) protected Integer requestUserObjectId;

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
    * Gets the value of the activityStepName property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getActivityStepName()
   {
      return activityStepName;
   }

   /**
    * Sets the value of the activityStepName property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setActivityStepName(String value)
   {
      this.activityStepName = value;
   }

   /**
    * Gets the value of the activityStepObjectId property.
    *
    * @return
    *     possible object is
    *     {@link Integer }
    *
    */
   public Integer getActivityStepObjectId()
   {
      return activityStepObjectId;
   }

   /**
    * Sets the value of the activityStepObjectId property.
    *
    * @param value
    *     allowed object is
    *     {@link Integer }
    *
    */
   public void setActivityStepObjectId(Integer value)
   {
      this.activityStepObjectId = value;
   }

   /**
    * Gets the value of the changeSetObjectId property.
    *
    * @return
    *     possible object is
    *     {@link Integer }
    *
    */
   public Integer getChangeSetObjectId()
   {
      return changeSetObjectId;
   }

   /**
    * Sets the value of the changeSetObjectId property.
    *
    * @param value
    *     allowed object is
    *     {@link Integer }
    *
    */
   public void setChangeSetObjectId(Integer value)
   {
      this.changeSetObjectId = value;
   }

   /**
    * Gets the value of the date property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public Date getDate()
   {
      return date;
   }

   /**
    * Sets the value of the date property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setDate(Date value)
   {
      this.date = value;
   }

   /**
    * Gets the value of the requestUserObjectId property.
    *
    * @return
    *     possible object is
    *     {@link Integer }
    *
    */
   public Integer getRequestUserObjectId()
   {
      return requestUserObjectId;
   }

   /**
    * Sets the value of the requestUserObjectId property.
    *
    * @param value
    *     allowed object is
    *     {@link Integer }
    *
    */
   public void setRequestUserObjectId(Integer value)
   {
      this.requestUserObjectId = value;
   }

}
