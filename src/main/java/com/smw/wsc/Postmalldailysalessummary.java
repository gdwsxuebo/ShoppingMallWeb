
package com.smw.wsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="astr_request" type="{http://tempurl.org}postmalldailysalessummaryrequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "astrRequest"
})
@XmlRootElement(name = "postmalldailysalessummary")
public class Postmalldailysalessummary {

    @XmlElement(name = "astr_request")
    protected Postmalldailysalessummaryrequest astrRequest;

    /**
     * 获取astrRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Postmalldailysalessummaryrequest }
     *     
     */
    public Postmalldailysalessummaryrequest getAstrRequest() {
        return astrRequest;
    }

    /**
     * 设置astrRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Postmalldailysalessummaryrequest }
     *     
     */
    public void setAstrRequest(Postmalldailysalessummaryrequest value) {
        this.astrRequest = value;
    }

}
