
package com.smw.wsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>postesalescreaterequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="postesalescreaterequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://tempurl.org}requestheader" minOccurs="0"/>
 *         &lt;element name="esalestotal" type="{http://tempurl.org}esaleshdr" minOccurs="0"/>
 *         &lt;element name="esalesitems" type="{http://tempurl.org}ArrayOfEsalesitem" minOccurs="0"/>
 *         &lt;element name="esalestenders" type="{http://tempurl.org}ArrayOfEsalestender" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "postesalescreaterequest", propOrder = {
    "header",
    "esalestotal",
    "esalesitems",
    "esalestenders"
})
public class Postesalescreaterequest {

    protected Requestheader header;
    protected Esaleshdr esalestotal;
    protected ArrayOfEsalesitem esalesitems;
    protected ArrayOfEsalestender esalestenders;

    /**
     * 获取header属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Requestheader }
     *     
     */
    public Requestheader getHeader() {
        return header;
    }

    /**
     * 设置header属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Requestheader }
     *     
     */
    public void setHeader(Requestheader value) {
        this.header = value;
    }

    /**
     * 获取esalestotal属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Esaleshdr }
     *     
     */
    public Esaleshdr getEsalestotal() {
        return esalestotal;
    }

    /**
     * 设置esalestotal属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Esaleshdr }
     *     
     */
    public void setEsalestotal(Esaleshdr value) {
        this.esalestotal = value;
    }

    /**
     * 获取esalesitems属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEsalesitem }
     *     
     */
    public ArrayOfEsalesitem getEsalesitems() {
        return esalesitems;
    }

    /**
     * 设置esalesitems属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEsalesitem }
     *     
     */
    public void setEsalesitems(ArrayOfEsalesitem value) {
        this.esalesitems = value;
    }

    /**
     * 获取esalestenders属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEsalestender }
     *     
     */
    public ArrayOfEsalestender getEsalestenders() {
        return esalestenders;
    }

    /**
     * 设置esalestenders属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEsalestender }
     *     
     */
    public void setEsalestenders(ArrayOfEsalestender value) {
        this.esalestenders = value;
    }

}
