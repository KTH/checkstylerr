//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.3
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2021.04.09 at 11:15:26 AM BST
//

package net.sf.mpxj.ganttproject.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * &lt;p&gt;Java class for day-type complex type.
 *
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 *
 * &lt;pre&gt;
 * &amp;lt;complexType name="day-type"&amp;gt;
 *   &amp;lt;simpleContent&amp;gt;
 *     &amp;lt;extension base="&amp;lt;http://www.w3.org/2001/XMLSchema&amp;gt;string"&amp;gt;
 *       &amp;lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}int" /&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/simpleContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 *
 *
 */
@SuppressWarnings("all") @XmlAccessorType(XmlAccessType.FIELD) @XmlType(name = "day-type", propOrder =
{
   "value"
}) public class DayType
{

   @XmlValue protected String value;
   @XmlAttribute(name = "id") protected Integer id;

   /**
    * Gets the value of the value property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getValue()
   {
      return value;
   }

   /**
    * Sets the value of the value property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setValue(String value)
   {
      this.value = value;
   }

   /**
    * Gets the value of the id property.
    *
    * @return
    *     possible object is
    *     {@link Integer }
    *
    */
   public Integer getId()
   {
      return id;
   }

   /**
    * Sets the value of the id property.
    *
    * @param value
    *     allowed object is
    *     {@link Integer }
    *
    */
   public void setId(Integer value)
   {
      this.id = value;
   }

}
