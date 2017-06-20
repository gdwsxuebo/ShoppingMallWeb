
package com.smw.wsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>postesalescreaterequest complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡheader���Ե�ֵ��
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
     * ����header���Ե�ֵ��
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
     * ��ȡesalestotal���Ե�ֵ��
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
     * ����esalestotal���Ե�ֵ��
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
     * ��ȡesalesitems���Ե�ֵ��
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
     * ����esalesitems���Ե�ֵ��
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
     * ��ȡesalestenders���Ե�ֵ��
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
     * ����esalestenders���Ե�ֵ��
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
