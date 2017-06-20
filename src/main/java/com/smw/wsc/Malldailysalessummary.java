
package com.smw.wsc;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>malldailysalessummary complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡmallstorecode���Ե�ֵ��
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
     * ����mallstorecode���Ե�ֵ��
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
     * ��ȡsalesstorecount���Ե�ֵ��
     * 
     */
    public int getSalesstorecount() {
        return salesstorecount;
    }

    /**
     * ����salesstorecount���Ե�ֵ��
     * 
     */
    public void setSalesstorecount(int value) {
        this.salesstorecount = value;
    }

    /**
     * ��ȡmissuploadcount���Ե�ֵ��
     * 
     */
    public int getMissuploadcount() {
        return missuploadcount;
    }

    /**
     * ����missuploadcount���Ե�ֵ��
     * 
     */
    public void setMissuploadcount(int value) {
        this.missuploadcount = value;
    }

    /**
     * ��ȡstoremodifycount���Ե�ֵ��
     * 
     */
    public int getStoremodifycount() {
        return storemodifycount;
    }

    /**
     * ����storemodifycount���Ե�ֵ��
     * 
     */
    public void setStoremodifycount(int value) {
        this.storemodifycount = value;
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
