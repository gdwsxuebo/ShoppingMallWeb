
package com.smw.wsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>postdailysalessummary2request complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="postdailysalessummary2request">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://tempurl.org}requestheader" minOccurs="0"/>
 *         &lt;element name="salessummary" type="{http://tempurl.org}dailysalessummary2" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "postdailysalessummary2request", propOrder = {
    "header",
    "salessummary"
})
public class Postdailysalessummary2Request {

    protected Requestheader header;
    protected Dailysalessummary2 salessummary;

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
     * 获取salessummary属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Dailysalessummary2 }
     *     
     */
    public Dailysalessummary2 getSalessummary() {
        return salessummary;
    }

    /**
     * 设置salessummary属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Dailysalessummary2 }
     *     
     */
    public void setSalessummary(Dailysalessummary2 value) {
        this.salessummary = value;
    }

}
