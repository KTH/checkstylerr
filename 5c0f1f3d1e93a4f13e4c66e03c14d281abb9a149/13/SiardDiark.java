//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.09.21 at 01:32:34 PM CEST 
//

package dk.sa.xmlns.diark._1_0.tableindex;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN"/&gt;
 *         &lt;element name="dbName" type="{http://www.sa.dk/xmlns/diark/1.0}SQLIdentifier" minOccurs="0"/&gt;
 *         &lt;element name="databaseProduct" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tables" type="{http://www.sa.dk/xmlns/diark/1.0}tablesType"/&gt;
 *         &lt;element name="views" type="{http://www.sa.dk/xmlns/diark/1.0}viewsType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"version", "dbName", "databaseProduct", "tables", "views"})
@XmlRootElement(name = "siardDiark")
public class SiardDiark {

  @XmlElement(required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "NMTOKEN")
  protected String version;
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String dbName;
  protected String databaseProduct;
  @XmlElement(required = true)
  protected TablesType tables;
  protected ViewsType views;

  /**
   * Gets the value of the version property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getVersion() {
    return version;
  }

  /**
   * Sets the value of the version property.
   * 
   * @param value
   *          allowed object is {@link String }
   * 
   */
  public void setVersion(String value) {
    this.version = value;
  }

  /**
   * Gets the value of the dbName property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDbName() {
    return dbName;
  }

  /**
   * Sets the value of the dbName property.
   * 
   * @param value
   *          allowed object is {@link String }
   * 
   */
  public void setDbName(String value) {
    this.dbName = value;
  }

  /**
   * Gets the value of the databaseProduct property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDatabaseProduct() {
    return databaseProduct;
  }

  /**
   * Sets the value of the databaseProduct property.
   * 
   * @param value
   *          allowed object is {@link String }
   * 
   */
  public void setDatabaseProduct(String value) {
    this.databaseProduct = value;
  }

  /**
   * Gets the value of the tables property.
   * 
   * @return possible object is {@link TablesType }
   * 
   */
  public TablesType getTables() {
    return tables;
  }

  /**
   * Sets the value of the tables property.
   * 
   * @param value
   *          allowed object is {@link TablesType }
   * 
   */
  public void setTables(TablesType value) {
    this.tables = value;
  }

  /**
   * Gets the value of the views property.
   * 
   * @return possible object is {@link ViewsType }
   * 
   */
  public ViewsType getViews() {
    return views;
  }

  /**
   * Sets the value of the views property.
   * 
   * @param value
   *          allowed object is {@link ViewsType }
   * 
   */
  public void setViews(ViewsType value) {
    this.views = value;
  }

}
