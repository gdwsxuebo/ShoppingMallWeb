
package com.smw.wsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="astr_request" type="{http://tempurl.org}postesalescreaterequest" minOccurs="0"/>
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
@XmlRootElement(name = "postesalescreate")
public class Postesalescreate {

    @XmlElement(name = "astr_request")
    protected Postesalescreaterequest astrRequest;

    /**
     * ��ȡastrRequest���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Postesalescreaterequest }
     *     
     */
    public Postesalescreaterequest getAstrRequest() {
        return astrRequest;
    }

    /**
     * ����astrRequest���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Postesalescreaterequest }
     *     
     */
    public void setAstrRequest(Postesalescreaterequest value) {
        this.astrRequest = value;
    }

}
