
package com.smw.wsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
     * 获取postesalescreateResult属性的值。
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
     * 设置postesalescreateResult属性的值。
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
