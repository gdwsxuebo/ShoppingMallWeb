
package com.smw.wsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>postsalescreateresponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="postsalescreateresponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://tempurl.org}responseheader" minOccurs="0"/>
 *         &lt;element name="salestotal" type="{http://tempurl.org}saleshdr" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "postsalescreateresponse", propOrder = {
    "header",
    "salestotal"
})
public class Postsalescreateresponse2 {

    protected Responseheader header;
    protected Saleshdr salestotal;

    /**
     * 获取header属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Responseheader }
     *     
     */
    public Responseheader getHeader() {
        return header;
    }

    /**
     * 设置header属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Responseheader }
     *     
     */
    public void setHeader(Responseheader value) {
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

}
