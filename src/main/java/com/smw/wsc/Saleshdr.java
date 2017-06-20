
package com.smw.wsc;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>saleshdr complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
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
     * 获取reservedocno属性的值。
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
     * 设置reservedocno属性的值。
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
     * 获取salestype属性的值。
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
     * 设置salestype属性的值。
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
     * 获取orgtxdateYyyymmdd属性的值。
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
     * 设置orgtxdateYyyymmdd属性的值。
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
     * 获取orgstorecode属性的值。
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
     * 设置orgstorecode属性的值。
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
     * 获取orgtillid属性的值。
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
     * 设置orgtillid属性的值。
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
     * 获取orgtxdocno属性的值。
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
     * 设置orgtxdocno属性的值。
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
     * 获取mallitemcode属性的值。
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
     * 设置mallitemcode属性的值。
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
     * 获取vipcode属性的值。
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
     * 设置vipcode属性的值。
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
     * 获取salesman属性的值。
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
     * 设置salesman属性的值。
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
     * 获取demographiccode属性的值。
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
     * 设置demographiccode属性的值。
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
     * 获取demographicdata属性的值。
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
     * 设置demographicdata属性的值。
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
     * 获取netqty属性的值。
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
     * 设置netqty属性的值。
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
     * 获取originalamount属性的值。
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
     * 设置originalamount属性的值。
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
     * 获取sellingamount属性的值。
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
     * 设置sellingamount属性的值。
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
     * 获取couponnumber属性的值。
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
     * 设置couponnumber属性的值。
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
     * 获取coupongroup属性的值。
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
     * 设置coupongroup属性的值。
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
     * 获取coupontype属性的值。
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
     * 设置coupontype属性的值。
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
     * 获取couponqty属性的值。
     * 
     */
    public short getCouponqty() {
        return couponqty;
    }

    /**
     * 设置couponqty属性的值。
     * 
     */
    public void setCouponqty(short value) {
        this.couponqty = value;
    }

    /**
     * 获取totaldiscount属性的值。
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
     * 设置totaldiscount属性的值。
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
     * 获取ttltaxamount1属性的值。
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
     * 设置ttltaxamount1属性的值。
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
     * 获取ttltaxamount2属性的值。
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
     * 设置ttltaxamount2属性的值。
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
     * 获取netamount属性的值。
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
     * 设置netamount属性的值。
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
     * 获取paidamount属性的值。
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
     * 设置paidamount属性的值。
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
     * 获取changeamount属性的值。
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
     * 设置changeamount属性的值。
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
     * 获取priceincludetax属性的值。
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
     * 设置priceincludetax属性的值。
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
     * 获取shoptaxgroup属性的值。
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
     * 设置shoptaxgroup属性的值。
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
     * 获取extendparam属性的值。
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
     * 设置extendparam属性的值。
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
     * 获取invoicetitle属性的值。
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
     * 设置invoicetitle属性的值。
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
     * 获取invoicecontent属性的值。
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
     * 设置invoicecontent属性的值。
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
     * 获取issueby属性的值。
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
     * 设置issueby属性的值。
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
     * 获取issuetimeHhmmss属性的值。
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
     * 设置issuetimeHhmmss属性的值。
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
     * 获取ecorderno属性的值。
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
     * 设置ecorderno属性的值。
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
     * 获取buyerremark属性的值。
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
     * 设置buyerremark属性的值。
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
     * 获取orderremark属性的值。
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
     * 设置orderremark属性的值。
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
     * 获取status属性的值。
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
     * 设置status属性的值。
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
     * 获取ttpossalesdocno属性的值。
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
     * 设置ttpossalesdocno属性的值。
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
