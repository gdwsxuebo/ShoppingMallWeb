
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
 *         &lt;element name="postdailysalesestimatecreateResult" type="{http://tempurl.org}postdailysalesestimatecreateresponse" minOccurs="0"/>
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
    "postdailysalesestimatecreateResult"
})
@XmlRootElement(name = "postdailysalesestimatecreateResponse")
public class PostdailysalesestimatecreateResponse {

    protected Postdailysalesestimatecreateresponse2 postdailysalesestimatecreateResult;

    /**
     * ��ȡpostdailysalesestimatecreateResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Postdailysalesestimatecreateresponse2 }
     *     
     */
    public Postdailysalesestimatecreateresponse2 getPostdailysalesestimatecreateResult() {
        return postdailysalesestimatecreateResult;
    }

    /**
     * ����postdailysalesestimatecreateResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Postdailysalesestimatecreateresponse2 }
     *     
     */
    public void setPostdailysalesestimatecreateResult(Postdailysalesestimatecreateresponse2 value) {
        this.postdailysalesestimatecreateResult = value;
    }

}
