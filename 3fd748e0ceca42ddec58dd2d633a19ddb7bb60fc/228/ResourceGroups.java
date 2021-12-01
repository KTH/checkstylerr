//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2010.08.02 at 09:18:52 PM BST
//

package net.sf.mpxj.planner.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 */
@XmlAccessorType(XmlAccessType.FIELD) @XmlType(name = "", propOrder =
{
   "group"
}) @XmlRootElement(name = "resource-groups") public class ResourceGroups
{

   @XmlAttribute(name = "default_group") @XmlJavaTypeAdapter(NormalizedStringAdapter.class) protected String defaultGroup;
   protected List<Group> group;

   /**
    * Gets the value of the defaultGroup property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getDefaultGroup()
   {
      return defaultGroup;
   }

   /**
    * Sets the value of the defaultGroup property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setDefaultGroup(String value)
   {
      this.defaultGroup = value;
   }

   /**
    * Gets the value of the group property.
    *
    * <p>
    * This accessor method returns a reference to the live list,
    * not a snapshot. Therefore any modification you make to the
    * returned list will be present inside the JAXB object.
    * This is why there is not a <CODE>set</CODE> method for the group property.
    *
    * <p>
    * For example, to add a new item, do as follows:
    * <pre>
    *    getGroup().add(newItem);
    * </pre>
    *
    *
    * <p>
    * Objects of the following type(s) are allowed in the list
    * {@link Group }
    *
    *
    */
   public List<Group> getGroup()
   {
      if (group == null)
      {
         group = new ArrayList<>();
      }
      return this.group;
   }

}
