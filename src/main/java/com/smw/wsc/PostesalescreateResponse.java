
package com.smw.wsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="postesalescreateResult" type="{http://tempurl.org}postesalescreateresponse" minOccurs="0"/>
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
    "postesalescreateResult"
})
@XmlRootElement(name = "postesalescreateResponse")
public class PostesalescreateResponse {

    protected Postesalescreateresponse2 postesalescreateResult;

    /**
     * ��ȡpostesalescreateResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Postesalescreateresponse2 }
     *     
     */
    public Postesalescreateresponse2 getPostesalescreateResult() {
        return postesalescreateResult;
    }

    /**
     * ����postesalescreateResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Postesalescreateresponse2 }
     *     
     */
    public void setPostesalescreateResult(Postesalescreateresponse2 value) {
        this.postesalescreateResult = value;
    }

}
