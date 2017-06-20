
package com.smw.wsc;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>saleshdr complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="saleshdr">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="localstorecode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reservedocno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="txdate_yyyymmdd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="txtime_hhmmss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mallid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="storecode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tillid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salestype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="txdocno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgtxdate_yyyymmdd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgstorecode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgtillid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgtxdocno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mallitemcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cashier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vipcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salesman" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="demographiccode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="demographicdata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="netqty" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="originalamount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="sellingamount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="couponnumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coupongroup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coupontype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="couponqty" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="totaldiscount" type="{http://tempurl.org}ArrayOfSalesdiscount" minOccurs="0"/>
 *         &lt;element name="ttltaxamount1" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="ttltaxamount2" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="netamount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="paidamount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="changeamount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="priceincludetax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shoptaxgroup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="extendparam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="invoicetitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="invoicecontent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="issueby" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="issuedate_yyyymmdd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="issuetime_hhmmss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ecorderno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buyerremark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderremark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ttpossalesdocno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "saleshdr", propOrder = {
    "localstorecode",
    "reservedocno",
    "txdateYyyymmdd",
    "txtimeHhmmss",
    "mallid",
    "storecode",
    "tillid",
    "salestype",
    "txdocno",
    "orgtxdateYyyymmdd",
    "orgstorecode",
    "orgtillid",
    "orgtxdocno",
    "mallitemcode",
    "cashier",
    "vipcode",
    "salesman",
    "demographiccode",
    "demographicdata",
    "netqty",
    "originalamount",
    "sellingamount",
    "couponnumber",
    "coupongroup",
    "coupontype",
    "couponqty",
    "totaldiscount",
    "ttltaxamount1",
    "ttltaxamount2",
    "netamount",
    "paidamount",
    "changeamount",
    "priceincludetax",
    "shoptaxgroup",
    "extendparam",
    "invoicetitle",
    "invoicecontent",
    "issueby",
    "issuedateYyyymmdd",
    "issuetimeHhmmss",
    "ecorderno",
    "buyerremark",
    "orderremark",
    "status",
    "ttpossalesdocno"
})
public class Saleshdr {

    protected String localstorecode;
    protected String reservedocno;
    @XmlElement(name = "txdate_yyyymmdd")
    protected String txdateYyyymmdd;
    @XmlElement(name = "txtime_hhmmss")
    protected String txtimeHhmmss;
    protected String mallid;
    protected String storecode;
    protected String tillid;
    protected String salestype;
    protected String txdocno;
    @XmlElement(name = "orgtxdate_yyyymmdd")
    protected String orgtxdateYyyymmdd;
    protected String orgstorecode;
    protected String orgtillid;
    protected String orgtxdocno;
    protected String mallitemcode;
    protected String cashier;
    protected String vipcode;
    protected String salesman;
    protected String demographiccode;
    protected String demographicdata;
    @XmlElement(required = true)
    protected BigDecimal netqty;
    @XmlElement(required = true)
    protected BigDecimal originalamount;
    @XmlElement(required = true)
    protected BigDecimal sellingamount;
    protected String couponnumber;
    protected String coupongroup;
    protected String coupontype;
    protected short couponqty;
    protected ArrayOfSalesdiscount totaldiscount;
    @XmlElement(required = true)
    protected BigDecimal ttltaxamount1;
    @XmlElement(required = true)
    protected BigDecimal ttltaxamount2;
    @XmlElement(required = true)
    protected BigDecimal netamount;
    @XmlElement(required = true)
    protected BigDecimal paidamount;
    @XmlElement(required = true)
    protected BigDecimal changeamount;
    protected String priceincludetax;
    protected String shoptaxgroup;
    protected String extendparam;
    protected String invoicetitle;
    protected String invoicecontent;
    protected String issueby;
    @XmlElement(name = "issuedate_yyyymmdd")
    protected String issuedateYyyymmdd;
    @XmlElement(name = "issuetime_hhmmss")
    protected String issuetimeHhmmss;
    protected String ecorderno;
    protected String buyerremark;
    protected String orderremark;
    protected String status;
    protected String ttpossalesdocno;

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
     * ��ȡreservedocno���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReservedocno() {
        return reservedocno;
    }

    /**
     * ����reservedocno���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReservedocno(String value) {
        this.reservedocno = value;
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
     * ��ȡsalestype���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalestype() {
        return salestype;
    }

    /**
     * ����salestype���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalestype(String value) {
        this.salestype = value;
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
     * ��ȡorgtxdateYyyymmdd���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgtxdateYyyymmdd() {
        return orgtxdateYyyymmdd;
    }

    /**
     * ����orgtxdateYyyymmdd���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgtxdateYyyymmdd(String value) {
        this.orgtxdateYyyymmdd = value;
    }

    /**
     * ��ȡorgstorecode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgstorecode() {
        return orgstorecode;
    }

    /**
     * ����orgstorecode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgstorecode(String value) {
        this.orgstorecode = value;
    }

    /**
     * ��ȡorgtillid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgtillid() {
        return orgtillid;
    }

    /**
     * ����orgtillid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgtillid(String value) {
        this.orgtillid = value;
    }

    /**
     * ��ȡorgtxdocno���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgtxdocno() {
        return orgtxdocno;
    }

    /**
     * ����orgtxdocno���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgtxdocno(String value) {
        this.orgtxdocno = value;
    }

    /**
     * ��ȡmallitemcode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMallitemcode() {
        return mallitemcode;
    }

    /**
     * ����mallitemcode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMallitemcode(String value) {
        this.mallitemcode = value;
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
     * ��ȡvipcode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVipcode() {
        return vipcode;
    }

    /**
     * ����vipcode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVipcode(String value) {
        this.vipcode = value;
    }

    /**
     * ��ȡsalesman���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesman() {
        return salesman;
    }

    /**
     * ����salesman���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesman(String value) {
        this.salesman = value;
    }

    /**
     * ��ȡdemographiccode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDemographiccode() {
        return demographiccode;
    }

    /**
     * ����demographiccode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDemographiccode(String value) {
        this.demographiccode = value;
    }

    /**
     * ��ȡdemographicdata���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDemographicdata() {
        return demographicdata;
    }

    /**
     * ����demographicdata���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDemographicdata(String value) {
        this.demographicdata = value;
    }

    /**
     * ��ȡnetqty���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNetqty() {
        return netqty;
    }

    /**
     * ����netqty���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNetqty(BigDecimal value) {
        this.netqty = value;
    }

    /**
     * ��ȡoriginalamount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOriginalamount() {
        return originalamount;
    }

    /**
     * ����originalamount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOriginalamount(BigDecimal value) {
        this.originalamount = value;
    }

    /**
     * ��ȡsellingamount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSellingamount() {
        return sellingamount;
    }

    /**
     * ����sellingamount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSellingamount(BigDecimal value) {
        this.sellingamount = value;
    }

    /**
     * ��ȡcouponnumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCouponnumber() {
        return couponnumber;
    }

    /**
     * ����couponnumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCouponnumber(String value) {
        this.couponnumber = value;
    }

    /**
     * ��ȡcoupongroup���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoupongroup() {
        return coupongroup;
    }

    /**
     * ����coupongroup���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoupongroup(String value) {
        this.coupongroup = value;
    }

    /**
     * ��ȡcoupontype���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoupontype() {
        return coupontype;
    }

    /**
     * ����coupontype���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoupontype(String value) {
        this.coupontype = value;
    }

    /**
     * ��ȡcouponqty���Ե�ֵ��
     * 
     */
    public short getCouponqty() {
        return couponqty;
    }

    /**
     * ����couponqty���Ե�ֵ��
     * 
     */
    public void setCouponqty(short value) {
        this.couponqty = value;
    }

    /**
     * ��ȡtotaldiscount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSalesdiscount }
     *     
     */
    public ArrayOfSalesdiscount getTotaldiscount() {
        return totaldiscount;
    }

    /**
     * ����totaldiscount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSalesdiscount }
     *     
     */
    public void setTotaldiscount(ArrayOfSalesdiscount value) {
        this.totaldiscount = value;
    }

    /**
     * ��ȡttltaxamount1���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTtltaxamount1() {
        return ttltaxamount1;
    }

    /**
     * ����ttltaxamount1���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTtltaxamount1(BigDecimal value) {
        this.ttltaxamount1 = value;
    }

    /**
     * ��ȡttltaxamount2���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTtltaxamount2() {
        return ttltaxamount2;
    }

    /**
     * ����ttltaxamount2���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTtltaxamount2(BigDecimal value) {
        this.ttltaxamount2 = value;
    }

    /**
     * ��ȡnetamount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNetamount() {
        return netamount;
    }

    /**
     * ����netamount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNetamount(BigDecimal value) {
        this.netamount = value;
    }

    /**
     * ��ȡpaidamount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPaidamount() {
        return paidamount;
    }

    /**
     * ����paidamount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPaidamount(BigDecimal value) {
        this.paidamount = value;
    }

    /**
     * ��ȡchangeamount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getChangeamount() {
        return changeamount;
    }

    /**
     * ����changeamount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setChangeamount(BigDecimal value) {
        this.changeamount = value;
    }

    /**
     * ��ȡpriceincludetax���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriceincludetax() {
        return priceincludetax;
    }

    /**
     * ����priceincludetax���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriceincludetax(String value) {
        this.priceincludetax = value;
    }

    /**
     * ��ȡshoptaxgroup���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShoptaxgroup() {
        return shoptaxgroup;
    }

    /**
     * ����shoptaxgroup���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShoptaxgroup(String value) {
        this.shoptaxgroup = value;
    }

    /**
     * ��ȡextendparam���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendparam() {
        return extendparam;
    }

    /**
     * ����extendparam���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendparam(String value) {
        this.extendparam = value;
    }

    /**
     * ��ȡinvoicetitle���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoicetitle() {
        return invoicetitle;
    }

    /**
     * ����invoicetitle���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoicetitle(String value) {
        this.invoicetitle = value;
    }

    /**
     * ��ȡinvoicecontent���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoicecontent() {
        return invoicecontent;
    }

    /**
     * ����invoicecontent���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoicecontent(String value) {
        this.invoicecontent = value;
    }

    /**
     * ��ȡissueby���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueby() {
        return issueby;
    }

    /**
     * ����issueby���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueby(String value) {
        this.issueby = value;
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
     * ��ȡissuetimeHhmmss���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuetimeHhmmss() {
        return issuetimeHhmmss;
    }

    /**
     * ����issuetimeHhmmss���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuetimeHhmmss(String value) {
        this.issuetimeHhmmss = value;
    }

    /**
     * ��ȡecorderno���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEcorderno() {
        return ecorderno;
    }

    /**
     * ����ecorderno���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEcorderno(String value) {
        this.ecorderno = value;
    }

    /**
     * ��ȡbuyerremark���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuyerremark() {
        return buyerremark;
    }

    /**
     * ����buyerremark���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuyerremark(String value) {
        this.buyerremark = value;
    }

    /**
     * ��ȡorderremark���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderremark() {
        return orderremark;
    }

    /**
     * ����orderremark���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderremark(String value) {
        this.orderremark = value;
    }

    /**
     * ��ȡstatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * ����status���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * ��ȡttpossalesdocno���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTtpossalesdocno() {
        return ttpossalesdocno;
    }

    /**
     * ����ttpossalesdocno���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTtpossalesdocno(String value) {
        this.ttpossalesdocno = value;
    }

}
