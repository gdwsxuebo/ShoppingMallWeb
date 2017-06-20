
package com.smw.wsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>postdailysalesestimatecreaterequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="postdailysalesestimatecreaterequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://tempurl.org}requestheader" minOccurs="0"/>
 *         &lt;element name="salesestimate" type="{http://tempurl.org}dailysalesestimate" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "postdailysalesestimatecreaterequest", propOrder = {
    "header",
    "salesestimate"
})
public class Postdailysalesestimatecreaterequest {

    protected Requestheader header;
    protected Dailysalesestimate salesestimate;

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
     * 获取salesestimate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Dailysalesestimate }
     *     
     */
    public Dailysalesestimate getSalesestimate() {
        return salesestimate;
    }

    /**
     * 设置salesestimate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Dailysalesestimate }
     *     
     */
    public void setSalesestimate(Dailysalesestimate value) {
        this.salesestimate = value;
    }

}
