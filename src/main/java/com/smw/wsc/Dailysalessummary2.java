
package com.smw.wsc;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>dailysalessummary2 complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="dailysalessummary2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="localstorecode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="txdate_yyyymmdd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="txtime_hhmmss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mallid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="storecode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tillid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="txdocno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ttlsalesqty" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="ttlrefundqty" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="ttlsalesamt" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="ttlrefundamt" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="ttldoccount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cashier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="issuedate_yyyymmdd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iszerosales" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dailysalessummary2", propOrder = {
    "localstorecode",
    "txdateYyyymmdd",
    "txtimeHhmmss",
    "mallid",
    "storecode",
    "tillid",
    "txdocno",
    "ttlsalesqty",
    "ttlrefundqty",
    "ttlsalesamt",
    "ttlrefundamt",
    "ttldoccount",
    "cashier",
    "issuedateYyyymmdd",
    "iszerosales",
    "remarks"
})
public class Dailysalessummary2 {

    protected String localstorecode;
    @XmlElement(name = "txdate_yyyymmdd")
    protected String txdateYyyymmdd;
    @XmlElement(name = "txtime_hhmmss")
    protected String txtimeHhmmss;
    protected String mallid;
    protected String storecode;
    protected String tillid;
    protected String txdocno;
    @XmlElement(required = true)
    protected BigDecimal ttlsalesqty;
    @XmlElement(required = true)
    protected BigDecimal ttlrefundqty;
    @XmlElement(required = true)
    protected BigDecimal ttlsalesamt;
    @XmlElement(required = true)
    protected BigDecimal ttlrefundamt;
    protected int ttldoccount;
    protected String cashier;
    @XmlElement(name = "issuedate_yyyymmdd")
    protected String issuedateYyyymmdd;
    protected String iszerosales;
    protected String remarks;

    /**
     * 获取localstorecode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalstorecode() {
        return localstorecode;
    }

    /**
     * 设置localstorecode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalstorecode(String value) {
        this.localstorecode = value;
    }

    /**
     * 获取txdateYyyymmdd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxdateYyyymmdd() {
        return txdateYyyymmdd;
    }

    /**
     * 设置txdateYyyymmdd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxdateYyyymmdd(String value) {
        this.txdateYyyymmdd = value;
    }

    /**
     * 获取txtimeHhmmss属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxtimeHhmmss() {
        return txtimeHhmmss;
    }

    /**
     * 设置txtimeHhmmss属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxtimeHhmmss(String value) {
        this.txtimeHhmmss = value;
    }

    /**
     * 获取mallid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMallid() {
        return mallid;
    }

    /**
     * 设置mallid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMallid(String value) {
        this.mallid = value;
    }

    /**
     * 获取storecode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStorecode() {
        return storecode;
    }

    /**
     * 设置storecode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStorecode(String value) {
        this.storecode = value;
    }

    /**
     * 获取tillid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTillid() {
        return tillid;
    }

    /**
     * 设置tillid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTillid(String value) {
        this.tillid = value;
    }

    /**
     * 获取txdocno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxdocno() {
        return txdocno;
    }

    /**
     * 设置txdocno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxdocno(String value) {
        this.txdocno = value;
    }

    /**
     * 获取ttlsalesqty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTtlsalesqty() {
        return ttlsalesqty;
    }

    /**
     * 设置ttlsalesqty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTtlsalesqty(BigDecimal value) {
        this.ttlsalesqty = value;
    }

    /**
     * 获取ttlrefundqty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTtlrefundqty() {
        return ttlrefundqty;
    }

    /**
     * 设置ttlrefundqty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTtlrefundqty(BigDecimal value) {
        this.ttlrefundqty = value;
    }

    /**
     * 获取ttlsalesamt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTtlsalesamt() {
        return ttlsalesamt;
    }

    /**
     * 设置ttlsalesamt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTtlsalesamt(BigDecimal value) {
        this.ttlsalesamt = value;
    }

    /**
     * 获取ttlrefundamt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTtlrefundamt() {
        return ttlrefundamt;
    }

    /**
     * 设置ttlrefundamt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTtlrefundamt(BigDecimal value) {
        this.ttlrefundamt = value;
    }

    /**
     * 获取ttldoccount属性的值。
     * 
     */
    public int getTtldoccount() {
        return ttldoccount;
    }

    /**
     * 设置ttldoccount属性的值。
     * 
     */
    public void setTtldoccount(int value) {
        this.ttldoccount = value;
    }

    /**
     * 获取cashier属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCashier() {
        return cashier;
    }

    /**
     * 设置cashier属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCashier(String value) {
        this.cashier = value;
    }

    /**
     * 获取issuedateYyyymmdd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuedateYyyymmdd() {
        return issuedateYyyymmdd;
    }

    /**
     * 设置issuedateYyyymmdd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuedateYyyymmdd(String value) {
        this.issuedateYyyymmdd = value;
    }

    /**
     * 获取iszerosales属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIszerosales() {
        return iszerosales;
    }

    /**
     * 设置iszerosales属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIszerosales(String value) {
        this.iszerosales = value;
    }

    /**
     * 获取remarks属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置remarks属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemarks(String value) {
        this.remarks = value;
    }

}
