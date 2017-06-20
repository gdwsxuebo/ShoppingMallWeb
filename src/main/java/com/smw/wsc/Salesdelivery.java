
package com.smw.wsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>salesdelivery complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="salesdelivery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="receiver_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiver_country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiver_province" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiver_city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiver_district" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiver_address1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiver_address2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiver_address3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiver_address4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiver_postal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiver_mobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiver_phone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="logisticscompany" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="logisticsdocno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="expectdeliverydate_yyyymmdd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deliveryremarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "salesdelivery", propOrder = {
    "receiverName",
    "receiverCountry",
    "receiverProvince",
    "receiverCity",
    "receiverDistrict",
    "receiverAddress1",
    "receiverAddress2",
    "receiverAddress3",
    "receiverAddress4",
    "receiverPostal",
    "receiverMobile",
    "receiverPhone",
    "logisticscompany",
    "logisticsdocno",
    "expectdeliverydateYyyymmdd",
    "deliveryremarks"
})
public class Salesdelivery {

    @XmlElement(name = "receiver_name")
    protected String receiverName;
    @XmlElement(name = "receiver_country")
    protected String receiverCountry;
    @XmlElement(name = "receiver_province")
    protected String receiverProvince;
    @XmlElement(name = "receiver_city")
    protected String receiverCity;
    @XmlElement(name = "receiver_district")
    protected String receiverDistrict;
    @XmlElement(name = "receiver_address1")
    protected String receiverAddress1;
    @XmlElement(name = "receiver_address2")
    protected String receiverAddress2;
    @XmlElement(name = "receiver_address3")
    protected String receiverAddress3;
    @XmlElement(name = "receiver_address4")
    protected String receiverAddress4;
    @XmlElement(name = "receiver_postal")
    protected String receiverPostal;
    @XmlElement(name = "receiver_mobile")
    protected String receiverMobile;
    @XmlElement(name = "receiver_phone")
    protected String receiverPhone;
    protected String logisticscompany;
    protected String logisticsdocno;
    @XmlElement(name = "expectdeliverydate_yyyymmdd")
    protected String expectdeliverydateYyyymmdd;
    protected String deliveryremarks;

    /**
     * ��ȡreceiverName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * ����receiverName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverName(String value) {
        this.receiverName = value;
    }

    /**
     * ��ȡreceiverCountry���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverCountry() {
        return receiverCountry;
    }

    /**
     * ����receiverCountry���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverCountry(String value) {
        this.receiverCountry = value;
    }

    /**
     * ��ȡreceiverProvince���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverProvince() {
        return receiverProvince;
    }

    /**
     * ����receiverProvince���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverProvince(String value) {
        this.receiverProvince = value;
    }

    /**
     * ��ȡreceiverCity���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverCity() {
        return receiverCity;
    }

    /**
     * ����receiverCity���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverCity(String value) {
        this.receiverCity = value;
    }

    /**
     * ��ȡreceiverDistrict���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    /**
     * ����receiverDistrict���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverDistrict(String value) {
        this.receiverDistrict = value;
    }

    /**
     * ��ȡreceiverAddress1���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverAddress1() {
        return receiverAddress1;
    }

    /**
     * ����receiverAddress1���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverAddress1(String value) {
        this.receiverAddress1 = value;
    }

    /**
     * ��ȡreceiverAddress2���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverAddress2() {
        return receiverAddress2;
    }

    /**
     * ����receiverAddress2���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverAddress2(String value) {
        this.receiverAddress2 = value;
    }

    /**
     * ��ȡreceiverAddress3���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverAddress3() {
        return receiverAddress3;
    }

    /**
     * ����receiverAddress3���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverAddress3(String value) {
        this.receiverAddress3 = value;
    }

    /**
     * ��ȡreceiverAddress4���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverAddress4() {
        return receiverAddress4;
    }

    /**
     * ����receiverAddress4���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverAddress4(String value) {
        this.receiverAddress4 = value;
    }

    /**
     * ��ȡreceiverPostal���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverPostal() {
        return receiverPostal;
    }

    /**
     * ����receiverPostal���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverPostal(String value) {
        this.receiverPostal = value;
    }

    /**
     * ��ȡreceiverMobile���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverMobile() {
        return receiverMobile;
    }

    /**
     * ����receiverMobile���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverMobile(String value) {
        this.receiverMobile = value;
    }

    /**
     * ��ȡreceiverPhone���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverPhone() {
        return receiverPhone;
    }

    /**
     * ����receiverPhone���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverPhone(String value) {
        this.receiverPhone = value;
    }

    /**
     * ��ȡlogisticscompany���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogisticscompany() {
        return logisticscompany;
    }

    /**
     * ����logisticscompany���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogisticscompany(String value) {
        this.logisticscompany = value;
    }

    /**
     * ��ȡlogisticsdocno���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogisticsdocno() {
        return logisticsdocno;
    }

    /**
     * ����logisticsdocno���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogisticsdocno(String value) {
        this.logisticsdocno = value;
    }

    /**
     * ��ȡexpectdeliverydateYyyymmdd���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpectdeliverydateYyyymmdd() {
        return expectdeliverydateYyyymmdd;
    }

    /**
     * ����expectdeliverydateYyyymmdd���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpectdeliverydateYyyymmdd(String value) {
        this.expectdeliverydateYyyymmdd = value;
    }

    /**
     * ��ȡdeliveryremarks���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryremarks() {
        return deliveryremarks;
    }

    /**
     * ����deliveryremarks���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryremarks(String value) {
        this.deliveryremarks = value;
    }

}
