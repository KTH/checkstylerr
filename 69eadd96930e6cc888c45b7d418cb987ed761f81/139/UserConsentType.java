//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.3
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2021.01.11 at 07:44:45 PM GMT
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
 * &lt;p&gt;Java class for UserConsentType complex type.
 *
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 *
 * &lt;pre&gt;
 * &amp;lt;complexType name="UserConsentType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="ConsentAcceptanceDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ConsentAcceptanceStatus" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ConsentType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="UserId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD) @XmlType(name = "UserConsentType", propOrder =
{
   "consentAcceptanceDate",
   "consentAcceptanceStatus",
   "consentType",
   "userId"
}) public class UserConsentType
{

   @XmlElement(name = "ConsentAcceptanceDate", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter2.class) @XmlSchemaType(name = "dateTime") protected Date consentAcceptanceDate;
   @XmlElement(name = "ConsentAcceptanceStatus", nillable = true) protected Integer consentAcceptanceStatus;
   @XmlElement(name = "ConsentType", nillable = true) protected Integer consentType;
   @XmlElement(name = "UserId") protected Integer userId;

   /**
    * Gets the value of the consentAcceptanceDate property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Date getConsentAcceptanceDate()
   {
      return consentAcceptanceDate;
   }

   /**
    * Sets the value of the consentAcceptanceDate property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setConsentAcceptanceDate(Date value)
   {
      this.consentAcceptanceDate = value;
   }

   /**
    * Gets the value of the consentAcceptanceStatus property.
    * 
    * @return
    *     possible object is
    *     {@link Integer }
    *     
    */
   public Integer getConsentAcceptanceStatus()
   {
      return consentAcceptanceStatus;
   }

   /**
    * Sets the value of the consentAcceptanceStatus property.
    * 
    * @param value
    *     allowed object is
    *     {@link Integer }
    *     
    */
   public void setConsentAcceptanceStatus(Integer value)
   {
      this.consentAcceptanceStatus = value;
   }

   /**
    * Gets the value of the consentType property.
    * 
    * @return
    *     possible object is
    *     {@link Integer }
    *     
    */
   public Integer getConsentType()
   {
      return consentType;
   }

   /**
    * Sets the value of the consentType property.
    * 
    * @param value
    *     allowed object is
    *     {@link Integer }
    *     
    */
   public void setConsentType(Integer value)
   {
      this.consentType = value;
   }

   /**
    * Gets the value of the userId property.
    * 
    * @return
    *     possible object is
    *     {@link Integer }
    *     
    */
   public Integer getUserId()
   {
      return userId;
   }

   /**
    * Sets the value of the userId property.
    * 
    * @param value
    *     allowed object is
    *     {@link Integer }
    *     
    */
   public void setUserId(Integer value)
   {
      this.userId = value;
   }

}
