
package com.smw.wsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>postsalescreaterequest complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="postsalescreaterequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://tempurl.org}requestheader" minOccurs="0"/>
 *         &lt;element name="salestotal" type="{http://tempurl.org}saleshdr" minOccurs="0"/>
 *         &lt;element name="salesitems" type="{http://tempurl.org}ArrayOfSalesitem" minOccurs="0"/>
 *         &lt;element name="salestenders" type="{http://tempurl.org}ArrayOfSalestender" minOccurs="0"/>
 *         &lt;element name="salesdlvy" type="{http://tempurl.org}salesdelivery" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "postsalescreaterequest", propOrder = {
    "header",
    "salestotal",
    "salesitems",
    "salestenders",
    "salesdlvy"
})
public class Postsalescreaterequest {

    protected Requestheader header;
    protected Saleshdr salestotal;
    protected ArrayOfSalesitem salesitems;
    protected ArrayOfSalestender salestenders;
    protected Salesdelivery salesdlvy;

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
     * ��ȡsalestotal���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Saleshdr }
     *     
     */
    public Saleshdr getSalestotal() {
        return salestotal;
    }

    /**
     * ����salestotal���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Saleshdr }
     *     
     */
    public void setSalestotal(Saleshdr value) {
        this.salestotal = value;
    }

    /**
     * ��ȡsalesitems���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSalesitem }
     *     
     */
    public ArrayOfSalesitem getSalesitems() {
        return salesitems;
    }

    /**
     * ����salesitems���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSalesitem }
     *     
     */
    public void setSalesitems(ArrayOfSalesitem value) {
        this.salesitems = value;
    }

    /**
     * ��ȡsalestenders���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSalestender }
     *     
     */
    public ArrayOfSalestender getSalestenders() {
        return salestenders;
    }

    /**
     * ����salestenders���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSalestender }
     *     
     */
    public void setSalestenders(ArrayOfSalestender value) {
        this.salestenders = value;
    }

    /**
     * ��ȡsalesdlvy���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Salesdelivery }
     *     
     */
    public Salesdelivery getSalesdlvy() {
        return salesdlvy;
    }

    /**
     * ����salesdlvy���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Salesdelivery }
     *     
     */
    public void setSalesdlvy(Salesdelivery value) {
        this.salesdlvy = value;
    }

}
