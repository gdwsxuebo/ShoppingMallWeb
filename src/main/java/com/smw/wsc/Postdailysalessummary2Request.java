
package com.smw.wsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>postdailysalessummary2request complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡheader���Ե�ֵ��
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
     * ����header���Ե�ֵ��
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
     * ��ȡsalessummary���Ե�ֵ��
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
     * ����salessummary���Ե�ֵ��
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
