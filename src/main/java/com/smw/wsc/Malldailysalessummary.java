
package com.smw.wsc;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>malldailysalessummary complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="malldailysalessummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mallstorecode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mallid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="txdate_yyyymmdd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="txtime_hhmmss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ttlsalesqty" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="ttlsalesamt" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="ttldoccount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="salesstorecount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="missuploadcount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="storemodifycount" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "malldailysalessummary", propOrder = {
    "mallstorecode",
    "mallid",
    "txdateYyyymmdd",
    "txtimeHhmmss",
    "ttlsalesqty",
    "ttlsalesamt",
    "ttldoccount",
    "salesstorecount",
    "missuploadcount",
    "storemodifycount",
    "remarks"
})
public class Malldailysalessummary {

    protected String mallstorecode;
    protected String mallid;
    @XmlElement(name = "txdate_yyyymmdd")
    protected String txdateYyyymmdd;
    @XmlElement(name = "txtime_hhmmss")
    protected String txtimeHhmmss;
    @XmlElement(required = true)
    protected BigDecimal ttlsalesqty;
    @XmlElement(required = true)
    protected BigDecimal ttlsalesamt;
    protected int ttldoccount;
    protected int salesstorecount;
    protected int missuploadcount;
    protected int storemodifycount;
    protected String remarks;

    /**
     * 获取mallstorecode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMallstorecode() {
        return mallstorecode;
    }

    /**
     * 设置mallstorecode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMallstorecode(String value) {
        this.mallstorecode = value;
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
     * 获取salesstorecount属性的值。
     * 
     */
    public int getSalesstorecount() {
        return salesstorecount;
    }

    /**
     * 设置salesstorecount属性的值。
     * 
     */
    public void setSalesstorecount(int value) {
        this.salesstorecount = value;
    }

    /**
     * 获取missuploadcount属性的值。
     * 
     */
    public int getMissuploadcount() {
        return missuploadcount;
    }

    /**
     * 设置missuploadcount属性的值。
     * 
     */
    public void setMissuploadcount(int value) {
        this.missuploadcount = value;
    }

    /**
     * 获取storemodifycount属性的值。
     * 
     */
    public int getStoremodifycount() {
        return storemodifycount;
    }

    /**
     * 设置storemodifycount属性的值。
     * 
     */
    public void setStoremodifycount(int value) {
        this.storemodifycount = value;
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
