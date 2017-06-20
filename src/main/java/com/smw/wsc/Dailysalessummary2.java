
package com.smw.wsc;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>dailysalessummary2 complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡlocalstorecode���Ե�ֵ��
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
     * ����localstorecode���Ե�ֵ��
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
     * ��ȡtxdateYyyymmdd���Ե�ֵ��
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
     * ����txdateYyyymmdd���Ե�ֵ��
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
     * ��ȡtxtimeHhmmss���Ե�ֵ��
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
     * ����txtimeHhmmss���Ե�ֵ��
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
     * ��ȡmallid���Ե�ֵ��
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
     * ����mallid���Ե�ֵ��
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
     * ��ȡstorecode���Ե�ֵ��
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
     * ����storecode���Ե�ֵ��
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
     * ��ȡtillid���Ե�ֵ��
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
     * ����tillid���Ե�ֵ��
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
     * ��ȡtxdocno���Ե�ֵ��
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
     * ����txdocno���Ե�ֵ��
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
     * ��ȡttlsalesqty���Ե�ֵ��
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
     * ����ttlsalesqty���Ե�ֵ��
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
     * ��ȡttlrefundqty���Ե�ֵ��
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
     * ����ttlrefundqty���Ե�ֵ��
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
     * ��ȡttlsalesamt���Ե�ֵ��
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
     * ����ttlsalesamt���Ե�ֵ��
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
     * ��ȡttlrefundamt���Ե�ֵ��
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
     * ����ttlrefundamt���Ե�ֵ��
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
     * ��ȡttldoccount���Ե�ֵ��
     * 
     */
    public int getTtldoccount() {
        return ttldoccount;
    }

    /**
     * ����ttldoccount���Ե�ֵ��
     * 
     */
    public void setTtldoccount(int value) {
        this.ttldoccount = value;
    }

    /**
     * ��ȡcashier���Ե�ֵ��
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
     * ����cashier���Ե�ֵ��
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
     * ��ȡissuedateYyyymmdd���Ե�ֵ��
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
     * ����issuedateYyyymmdd���Ե�ֵ��
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
     * ��ȡiszerosales���Ե�ֵ��
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
     * ����iszerosales���Ե�ֵ��
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
     * ��ȡremarks���Ե�ֵ��
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
     * ����remarks���Ե�ֵ��
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
