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
import javax.xml.bind.annotation.XmlType;

/**
 * &lt;p&gt;Java class for DisplayCurrencyType complex type.
 *
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 *
 * &lt;pre&gt;
 * &amp;lt;complexType name="DisplayCurrencyType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Currency" type="{http://xmlns.oracle.com/Primavera/P6/V20.12/API/BusinessObjects}CurrencyType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD) @XmlType(name = "DisplayCurrencyType", propOrder =
{
   "currency"
}) public class DisplayCurrencyType
{

   @XmlElement(name = "Currency", required = true) protected CurrencyType currency;

   /**
    * Gets the value of the currency property.
    * 
    * @return
    *     possible object is
    *     {@link CurrencyType }
    *     
    */
   public CurrencyType getCurrency()
   {
      return currency;
   }

   /**
    * Sets the value of the currency property.
    * 
    * @param value
    *     allowed object is
    *     {@link CurrencyType }
    *     
    */
   public void setCurrency(CurrencyType value)
   {
      this.currency = value;
   }

}
