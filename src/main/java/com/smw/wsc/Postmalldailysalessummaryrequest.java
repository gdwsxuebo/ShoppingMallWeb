
package com.smw.wsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>postmalldailysalessummaryrequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="postmalldailysalessummaryrequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://tempurl.org}requestheader" minOccurs="0"/>
 *         &lt;element name="salessummary" type="{http://tempurl.org}malldailysalessummary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "postmalldailysalessummaryrequest", propOrder = {
    "header",
    "salessummary"
})
public class Postmalldailysalessummaryrequest {

    protected Requestheader header;
    protected Malldailysalessummary salessummary;

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
     *     {@link Malldailysalessummary }
     *     
     */
    public Malldailysalessummary getSalessummary() {
        return salessummary;
    }

    /**
     * 设置salessummary属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Malldailysalessummary }
     *     
     */
    public void setSalessummary(Malldailysalessummary value) {
        this.salessummary = value;
    }

}
