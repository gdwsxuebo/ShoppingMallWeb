
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
     * ��ȡpostdailysalessummary2Result���Ե�ֵ��
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
     * ����postdailysalessummary2Result���Ե�ֵ��
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
