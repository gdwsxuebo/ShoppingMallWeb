
package com.smw.wsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>postsalescreateresponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡheader���Ե�ֵ��
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
     * ����header���Ե�ֵ��
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
     * ��ȡsalestotal���Ե�ֵ��
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
     * ����salestotal���Ե�ֵ��
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
