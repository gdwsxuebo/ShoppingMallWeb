
package com.smw.wsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>postdailysalesestimatecreaterequest complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡsalesestimate���Ե�ֵ��
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
     * ����salesestimate���Ե�ֵ��
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
