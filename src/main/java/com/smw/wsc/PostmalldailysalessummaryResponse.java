
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
 *         &lt;element name="postmalldailysalessummaryResult" type="{http://tempurl.org}postmalldailysalessummaryresponse" minOccurs="0"/>
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
    "postmalldailysalessummaryResult"
})
@XmlRootElement(name = "postmalldailysalessummaryResponse")
public class PostmalldailysalessummaryResponse {

    protected Postmalldailysalessummaryresponse2 postmalldailysalessummaryResult;

    /**
     * ��ȡpostmalldailysalessummaryResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Postmalldailysalessummaryresponse2 }
     *     
     */
    public Postmalldailysalessummaryresponse2 getPostmalldailysalessummaryResult() {
        return postmalldailysalessummaryResult;
    }

    /**
     * ����postmalldailysalessummaryResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Postmalldailysalessummaryresponse2 }
     *     
     */
    public void setPostmalldailysalessummaryResult(Postmalldailysalessummaryresponse2 value) {
        this.postmalldailysalessummaryResult = value;
    }

}
