
package com.smw.wsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>postsalescreaterequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="postsalescreaterequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://tempurl.org}requestheader" minOccurs="0"/>
 *         &lt;element name="salestotal" type="{http://tempurl.org}saleshdr" minOccurs="0"/>
 *         &lt;element name="salesitems" type="{http://tempurl.org}ArrayOfSalesitem" minOccurs="0"/>
 *         &lt;element name="salestenders" type="{http://tempurl.org}ArrayOfSalestender" minOccurs="0"/>
 *         &lt;element name="salesdlvy" type="{http://tempurl.org}salesdelivery" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "postsalescreaterequest", propOrder = {
    "header",
    "salestotal",
    "salesitems",
    "salestenders",
    "salesdlvy"
})
public class Postsalescreaterequest {

    protected Requestheader header;
    protected Saleshdr salestotal;
    protected ArrayOfSalesitem salesitems;
    protected ArrayOfSalestender salestenders;
    protected Salesdelivery salesdlvy;

    /**
     * 获取header属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Requestheader }
     *     
     */
    public Requestheader getHeader() {
        return header;
    }

    /**
     * 设置header属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Requestheader }
     *     
     */
    public void setHeader(Requestheader value) {
        this.header = value;
    }

    /**
     * 获取salestotal属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Saleshdr }
     *     
     */
    public Saleshdr getSalestotal() {
        return salestotal;
    }

    /**
     * 设置salestotal属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Saleshdr }
     *     
     */
    public void setSalestotal(Saleshdr value) {
        this.salestotal = value;
    }

    /**
     * 获取salesitems属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSalesitem }
     *     
     */
    public ArrayOfSalesitem getSalesitems() {
        return salesitems;
    }

    /**
     * 设置salesitems属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSalesitem }
     *     
     */
    public void setSalesitems(ArrayOfSalesitem value) {
        this.salesitems = value;
    }

    /**
     * 获取salestenders属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSalestender }
     *     
     */
    public ArrayOfSalestender getSalestenders() {
        return salestenders;
    }

    /**
     * 设置salestenders属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSalestender }
     *     
     */
    public void setSalestenders(ArrayOfSalestender value) {
        this.salestenders = value;
    }

    /**
     * 获取salesdlvy属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Salesdelivery }
     *     
     */
    public Salesdelivery getSalesdlvy() {
        return salesdlvy;
    }

    /**
     * 设置salesdlvy属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Salesdelivery }
     *     
     */
    public void setSalesdlvy(Salesdelivery value) {
        this.salesdlvy = value;
    }

}
