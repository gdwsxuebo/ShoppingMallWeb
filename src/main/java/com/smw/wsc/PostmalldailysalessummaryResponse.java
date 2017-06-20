
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
     * 获取postmalldailysalessummaryResult属性的值。
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
     * 设置postmalldailysalessummaryResult属性的值。
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
