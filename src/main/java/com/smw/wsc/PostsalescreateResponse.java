
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
 *         &lt;element name="postsalescreateResult" type="{http://tempurl.org}postsalescreateresponse" minOccurs="0"/>
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
    "postsalescreateResult"
})
@XmlRootElement(name = "postsalescreateResponse")
public class PostsalescreateResponse {

    protected Postsalescreateresponse2 postsalescreateResult;

    /**
     * ��ȡpostsalescreateResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Postsalescreateresponse2 }
     *     
     */
    public Postsalescreateresponse2 getPostsalescreateResult() {
        return postsalescreateResult;
    }

    /**
     * ����postsalescreateResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Postsalescreateresponse2 }
     *     
     */
    public void setPostsalescreateResult(Postsalescreateresponse2 value) {
        this.postsalescreateResult = value;
    }

}
