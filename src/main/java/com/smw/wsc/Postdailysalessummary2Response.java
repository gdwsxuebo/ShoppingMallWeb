
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
 *         &lt;element name="postdailysalessummary2Result" type="{http://tempurl.org}postdailysalessummary2response" minOccurs="0"/>
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
    "postdailysalessummary2Result"
})
@XmlRootElement(name = "postdailysalessummary2Response")
public class Postdailysalessummary2Response {

    protected Postdailysalessummary2Response2 postdailysalessummary2Result;

    /**
     * 获取postdailysalessummary2Result属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Postdailysalessummary2Response2 }
     *     
     */
    public Postdailysalessummary2Response2 getPostdailysalessummary2Result() {
        return postdailysalessummary2Result;
    }

    /**
     * 设置postdailysalessummary2Result属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Postdailysalessummary2Response2 }
     *     
     */
    public void setPostdailysalessummary2Result(Postdailysalessummary2Response2 value) {
        this.postdailysalessummary2Result = value;
    }

}
