
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
     * 获取postsalescreateResult属性的值。
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
     * 设置postsalescreateResult属性的值。
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
