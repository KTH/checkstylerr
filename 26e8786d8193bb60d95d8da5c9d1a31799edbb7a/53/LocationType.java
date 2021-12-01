//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.3
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2020.11.30 at 05:04:43 PM GMT
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
 * &lt;p&gt;Java class for LocationType complex type.
 *
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 *
 * &lt;pre&gt;
 * &amp;lt;complexType name="LocationType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="AddressLine1" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="200"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="AddressLine2" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="200"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="City" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="200"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="Country" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="200"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="CountryCode" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="3"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="CreateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="CreateUser" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="255"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="LastUpdateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LastUpdateUser" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="255"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="Latitude" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Longitude" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Municipality" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="200"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="Name" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="255"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="ObjectId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="PostalCode" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="20"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="State" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="200"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="StateCode" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="2"/&amp;gt;
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
@XmlAccessorType(XmlAccessType.FIELD) @XmlType(name = "LocationType", propOrder =
{
   "addressLine1",
   "addressLine2",
   "city",
   "country",
   "countryCode",
   "createDate",
   "createUser",
   "lastUpdateDate",
   "lastUpdateUser",
   "latitude",
   "longitude",
   "municipality",
   "name",
   "objectId",
   "postalCode",
   "state",
   "stateCode"
}) public class LocationType
{

   @XmlElement(name = "AddressLine1") protected String addressLine1;
   @XmlElement(name = "AddressLine2") protected String addressLine2;
   @XmlElement(name = "City") protected String city;
   @XmlElement(name = "Country") protected String country;
   @XmlElement(name = "CountryCode") protected String countryCode;
   @XmlElement(name = "CreateDate", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "dateTime") protected Date createDate;
   @XmlElement(name = "CreateUser") protected String createUser;
   @XmlElement(name = "LastUpdateDate", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "dateTime") protected Date lastUpdateDate;
   @XmlElement(name = "LastUpdateUser") protected String lastUpdateUser;
   @XmlElement(name = "Latitude", nillable = true) protected Double latitude;
   @XmlElement(name = "Longitude", nillable = true) protected Double longitude;
   @XmlElement(name = "Municipality") protected String municipality;
   @XmlElement(name = "Name") protected String name;
   @XmlElement(name = "ObjectId", nillable = true) protected Integer objectId;
   @XmlElement(name = "PostalCode") protected String postalCode;
   @XmlElement(name = "State") protected String state;
   @XmlElement(name = "StateCode") protected String stateCode;

   /**
    * Gets the value of the addressLine1 property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getAddressLine1()
   {
      return addressLine1;
   }

   /**
    * Sets the value of the addressLine1 property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setAddressLine1(String value)
   {
      this.addressLine1 = value;
   }

   /**
    * Gets the value of the addressLine2 property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getAddressLine2()
   {
      return addressLine2;
   }

   /**
    * Sets the value of the addressLine2 property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setAddressLine2(String value)
   {
      this.addressLine2 = value;
   }

   /**
    * Gets the value of the city property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getCity()
   {
      return city;
   }

   /**
    * Sets the value of the city property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setCity(String value)
   {
      this.city = value;
   }

   /**
    * Gets the value of the country property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getCountry()
   {
      return country;
   }

   /**
    * Sets the value of the country property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setCountry(String value)
   {
      this.country = value;
   }

   /**
    * Gets the value of the countryCode property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getCountryCode()
   {
      return countryCode;
   }

   /**
    * Sets the value of the countryCode property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setCountryCode(String value)
   {
      this.countryCode = value;
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
    * Gets the value of the latitude property.
    *
    * @return
    *     possible object is
    *     {@link Double }
    *
    */
   public Double getLatitude()
   {
      return latitude;
   }

   /**
    * Sets the value of the latitude property.
    *
    * @param value
    *     allowed object is
    *     {@link Double }
    *
    */
   public void setLatitude(Double value)
   {
      this.latitude = value;
   }

   /**
    * Gets the value of the longitude property.
    *
    * @return
    *     possible object is
    *     {@link Double }
    *
    */
   public Double getLongitude()
   {
      return longitude;
   }

   /**
    * Sets the value of the longitude property.
    *
    * @param value
    *     allowed object is
    *     {@link Double }
    *
    */
   public void setLongitude(Double value)
   {
      this.longitude = value;
   }

   /**
    * Gets the value of the municipality property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getMunicipality()
   {
      return municipality;
   }

   /**
    * Sets the value of the municipality property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setMunicipality(String value)
   {
      this.municipality = value;
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
    * Gets the value of the postalCode property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getPostalCode()
   {
      return postalCode;
   }

   /**
    * Sets the value of the postalCode property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setPostalCode(String value)
   {
      this.postalCode = value;
   }

   /**
    * Gets the value of the state property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getState()
   {
      return state;
   }

   /**
    * Sets the value of the state property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setState(String value)
   {
      this.state = value;
   }

   /**
    * Gets the value of the stateCode property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getStateCode()
   {
      return stateCode;
   }

   /**
    * Sets the value of the stateCode property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setStateCode(String value)
   {
      this.stateCode = value;
   }

}
