
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
     * 获取postdailysalesestimatecreateResult属性的值。
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
     * 设置postdailysalesestimatecreateResult属性的值。
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
