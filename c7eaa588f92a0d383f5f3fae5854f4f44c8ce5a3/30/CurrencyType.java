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
 * &lt;p&gt;Java class for CurrencyType complex type.
 *
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 *
 * &lt;pre&gt;
 * &amp;lt;complexType name="CurrencyType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="CreateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="CreateUser" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="255"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="DecimalPlaces" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&amp;gt;
 *               &amp;lt;minInclusive value="0"/&amp;gt;
 *               &amp;lt;maxInclusive value="2"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="DecimalSymbol" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;enumeration value="Period"/&amp;gt;
 *               &amp;lt;enumeration value="Comma"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="DigitGroupingSymbol" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;enumeration value="Period"/&amp;gt;
 *               &amp;lt;enumeration value="Comma"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="ExchangeRate" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}double"&amp;gt;
 *               &amp;lt;minInclusive value="1.0E-6"/&amp;gt;
 *               &amp;lt;maxInclusive value="9.99999999999999E8"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="Id" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="6"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="IsBaseCurrency" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LastUpdateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LastUpdateUser" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="255"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="Name" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="40"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="NegativeSymbol" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;enumeration value="(#1.1)"/&amp;gt;
 *               &amp;lt;enumeration value="-#1.1"/&amp;gt;
 *               &amp;lt;enumeration value="#-1.1"/&amp;gt;
 *               &amp;lt;enumeration value="#1.1-"/&amp;gt;
 *               &amp;lt;enumeration value="(1.1#)"/&amp;gt;
 *               &amp;lt;enumeration value="-1.1#"/&amp;gt;
 *               &amp;lt;enumeration value="1.1-#"/&amp;gt;
 *               &amp;lt;enumeration value="1.1#-"/&amp;gt;
 *               &amp;lt;enumeration value="-1.1 #"/&amp;gt;
 *               &amp;lt;enumeration value="-# 1.1"/&amp;gt;
 *               &amp;lt;enumeration value="1.1 #-"/&amp;gt;
 *               &amp;lt;enumeration value="# 1.1-"/&amp;gt;
 *               &amp;lt;enumeration value="# -1.1"/&amp;gt;
 *               &amp;lt;enumeration value="1.1- #"/&amp;gt;
 *               &amp;lt;enumeration value="(# 1.1)"/&amp;gt;
 *               &amp;lt;enumeration value="(1.1 #)"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="ObjectId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="PositiveSymbol" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;enumeration value="#1.1"/&amp;gt;
 *               &amp;lt;enumeration value="1.1#"/&amp;gt;
 *               &amp;lt;enumeration value="# 1.1"/&amp;gt;
 *               &amp;lt;enumeration value="1.1 #"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="Symbol" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="3"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD) @XmlType(name = "CurrencyType", propOrder =
{
   "createDate",
   "createUser",
   "decimalPlaces",
   "decimalSymbol",
   "digitGroupingSymbol",
   "exchangeRate",
   "id",
   "isBaseCurrency",
   "lastUpdateDate",
   "lastUpdateUser",
   "name",
   "negativeSymbol",
   "objectId",
   "positiveSymbol",
   "symbol"
}) public class CurrencyType
{

   @XmlElement(name = "CreateDate", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "dateTime") protected Date createDate;
   @XmlElement(name = "CreateUser") protected String createUser;
   @XmlElement(name = "DecimalPlaces") protected Integer decimalPlaces;
   @XmlElement(name = "DecimalSymbol") protected String decimalSymbol;
   @XmlElement(name = "DigitGroupingSymbol") protected String digitGroupingSymbol;
   @XmlElement(name = "ExchangeRate") protected Double exchangeRate;
   @XmlElement(name = "Id") protected String id;
   @XmlElement(name = "IsBaseCurrency", nillable = true) protected Boolean isBaseCurrency;
   @XmlElement(name = "LastUpdateDate", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "dateTime") protected Date lastUpdateDate;
   @XmlElement(name = "LastUpdateUser") protected String lastUpdateUser;
   @XmlElement(name = "Name") protected String name;
   @XmlElement(name = "NegativeSymbol") protected String negativeSymbol;
   @XmlElement(name = "ObjectId") protected Integer objectId;
   @XmlElement(name = "PositiveSymbol") protected String positiveSymbol;
   @XmlElement(name = "Symbol") protected String symbol;

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
    * Gets the value of the decimalPlaces property.
    *
    * @return
    *     possible object is
    *     {@link Integer }
    *
    */
   public Integer getDecimalPlaces()
   {
      return decimalPlaces;
   }

   /**
    * Sets the value of the decimalPlaces property.
    *
    * @param value
    *     allowed object is
    *     {@link Integer }
    *
    */
   public void setDecimalPlaces(Integer value)
   {
      this.decimalPlaces = value;
   }

   /**
    * Gets the value of the decimalSymbol property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getDecimalSymbol()
   {
      return decimalSymbol;
   }

   /**
    * Sets the value of the decimalSymbol property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setDecimalSymbol(String value)
   {
      this.decimalSymbol = value;
   }

   /**
    * Gets the value of the digitGroupingSymbol property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getDigitGroupingSymbol()
   {
      return digitGroupingSymbol;
   }

   /**
    * Sets the value of the digitGroupingSymbol property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setDigitGroupingSymbol(String value)
   {
      this.digitGroupingSymbol = value;
   }

   /**
    * Gets the value of the exchangeRate property.
    *
    * @return
    *     possible object is
    *     {@link Double }
    *
    */
   public Double getExchangeRate()
   {
      return exchangeRate;
   }

   /**
    * Sets the value of the exchangeRate property.
    *
    * @param value
    *     allowed object is
    *     {@link Double }
    *
    */
   public void setExchangeRate(Double value)
   {
      this.exchangeRate = value;
   }

   /**
    * Gets the value of the id property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getId()
   {
      return id;
   }

   /**
    * Sets the value of the id property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setId(String value)
   {
      this.id = value;
   }

   /**
    * Gets the value of the isBaseCurrency property.
    *
    * @return
    *     possible object is
    *     {@link Boolean }
    *
    */
   public Boolean isIsBaseCurrency()
   {
      return isBaseCurrency;
   }

   /**
    * Sets the value of the isBaseCurrency property.
    *
    * @param value
    *     allowed object is
    *     {@link Boolean }
    *
    */
   public void setIsBaseCurrency(Boolean value)
   {
      this.isBaseCurrency = value;
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
    * Gets the value of the name property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getName()
   {
      return name;
   }

   /**
    * Sets the value of the name property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setName(String value)
   {
      this.name = value;
   }

   /**
    * Gets the value of the negativeSymbol property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getNegativeSymbol()
   {
      return negativeSymbol;
   }

   /**
    * Sets the value of the negativeSymbol property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setNegativeSymbol(String value)
   {
      this.negativeSymbol = value;
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
    * Gets the value of the positiveSymbol property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getPositiveSymbol()
   {
      return positiveSymbol;
   }

   /**
    * Sets the value of the positiveSymbol property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setPositiveSymbol(String value)
   {
      this.positiveSymbol = value;
   }

   /**
    * Gets the value of the symbol property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getSymbol()
   {
      return symbol;
   }

   /**
    * Sets the value of the symbol property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setSymbol(String value)
   {
      this.symbol = value;
   }

}
