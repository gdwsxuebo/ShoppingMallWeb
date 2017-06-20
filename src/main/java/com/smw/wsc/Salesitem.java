
package com.smw.wsc;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>salesitem complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="salesitem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="iscounteritemcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lineno" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="storecode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mallitemcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="counteritemcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="itemcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="plucode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="colorcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sizecode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="itemlotnum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serialnum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isdeposit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iswholesale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="invttype" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="qty" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="exstk2sales" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="originalprice" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="sellingprice" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="pricemode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="priceapprove" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="couponnumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coupongroup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coupontype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="itemdiscount" type="{http://tempurl.org}ArrayOfSalesdiscount" minOccurs="0"/>
 *         &lt;element name="vipdiscountpercent" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="vipdiscountless" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="promotion" type="{http://tempurl.org}ArrayOfSalespromtion" minOccurs="0"/>
 *         &lt;element name="totaldiscountless1" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="totaldiscountless2" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="totaldiscountless" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="tax" type="{http://tempurl.org}ArrayOfSalestax" minOccurs="0"/>
 *         &lt;element name="netamount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="bonusearn" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="salesitemremark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="refundreasoncode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="extendparam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "salesitem", propOrder = {
    "iscounteritemcode",
    "lineno",
    "storecode",
    "mallitemcode",
    "counteritemcode",
    "itemcode",
    "plucode",
    "colorcode",
    "sizecode",
    "itemlotnum",
    "serialnum",
    "isdeposit",
    "iswholesale",
    "invttype",
    "qty",
    "exstk2Sales",
    "originalprice",
    "sellingprice",
    "pricemode",
    "priceapprove",
    "couponnumber",
    "coupongroup",
    "coupontype",
    "itemdiscount",
    "vipdiscountpercent",
    "vipdiscountless",
    "promotion",
    "totaldiscountless1",
    "totaldiscountless2",
    "totaldiscountless",
    "tax",
    "netamount",
    "bonusearn",
    "salesitemremark",
    "refundreasoncode",
    "extendparam"
})
public class Salesitem {

    protected String iscounteritemcode;
    protected int lineno;
    protected String storecode;
    protected String mallitemcode;
    protected String counteritemcode;
    protected String itemcode;
    protected String plucode;
    protected String colorcode;
    protected String sizecode;
    protected String itemlotnum;
    protected String serialnum;
    protected String isdeposit;
    protected String iswholesale;
    protected short invttype;
    @XmlElement(required = true)
    protected BigDecimal qty;
    @XmlElement(name = "exstk2sales", required = true)
    protected BigDecimal exstk2Sales;
    @XmlElement(required = true)
    protected BigDecimal originalprice;
    @XmlElement(required = true)
    protected BigDecimal sellingprice;
    protected String pricemode;
    protected String priceapprove;
    protected String couponnumber;
    protected String coupongroup;
    protected String coupontype;
    protected ArrayOfSalesdiscount itemdiscount;
    @XmlElement(required = true)
    protected BigDecimal vipdiscountpercent;
    @XmlElement(required = true)
    protected BigDecimal vipdiscountless;
    protected ArrayOfSalespromtion promotion;
    @XmlElement(required = true)
    protected BigDecimal totaldiscountless1;
    @XmlElement(required = true)
    protected BigDecimal totaldiscountless2;
    @XmlElement(required = true)
    protected BigDecimal totaldiscountless;
    protected ArrayOfSalestax tax;
    @XmlElement(required = true)
    protected BigDecimal netamount;
    @XmlElement(required = true)
    protected BigDecimal bonusearn;
    protected String salesitemremark;
    protected String refundreasoncode;
    protected String extendparam;

    /**
     * 获取iscounteritemcode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIscounteritemcode() {
        return iscounteritemcode;
    }

    /**
     * 设置iscounteritemcode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIscounteritemcode(String value) {
        this.iscounteritemcode = value;
    }

    /**
     * 获取lineno属性的值。
     * 
     */
    public int getLineno() {
        return lineno;
    }

    /**
     * 设置lineno属性的值。
     * 
     */
    public void setLineno(int value) {
        this.lineno = value;
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
     * 获取counteritemcode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCounteritemcode() {
        return counteritemcode;
    }

    /**
     * 设置counteritemcode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCounteritemcode(String value) {
        this.counteritemcode = value;
    }

    /**
     * 获取itemcode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemcode() {
        return itemcode;
    }

    /**
     * 设置itemcode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemcode(String value) {
        this.itemcode = value;
    }

    /**
     * 获取plucode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlucode() {
        return plucode;
    }

    /**
     * 设置plucode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlucode(String value) {
        this.plucode = value;
    }

    /**
     * 获取colorcode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColorcode() {
        return colorcode;
    }

    /**
     * 设置colorcode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColorcode(String value) {
        this.colorcode = value;
    }

    /**
     * 获取sizecode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSizecode() {
        return sizecode;
    }

    /**
     * 设置sizecode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSizecode(String value) {
        this.sizecode = value;
    }

    /**
     * 获取itemlotnum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemlotnum() {
        return itemlotnum;
    }

    /**
     * 设置itemlotnum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemlotnum(String value) {
        this.itemlotnum = value;
    }

    /**
     * 获取serialnum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerialnum() {
        return serialnum;
    }

    /**
     * 设置serialnum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerialnum(String value) {
        this.serialnum = value;
    }

    /**
     * 获取isdeposit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsdeposit() {
        return isdeposit;
    }

    /**
     * 设置isdeposit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsdeposit(String value) {
        this.isdeposit = value;
    }

    /**
     * 获取iswholesale属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIswholesale() {
        return iswholesale;
    }

    /**
     * 设置iswholesale属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIswholesale(String value) {
        this.iswholesale = value;
    }

    /**
     * 获取invttype属性的值。
     * 
     */
    public short getInvttype() {
        return invttype;
    }

    /**
     * 设置invttype属性的值。
     * 
     */
    public void setInvttype(short value) {
        this.invttype = value;
    }

    /**
     * 获取qty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getQty() {
        return qty;
    }

    /**
     * 设置qty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setQty(BigDecimal value) {
        this.qty = value;
    }

    /**
     * 获取exstk2Sales属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getExstk2Sales() {
        return exstk2Sales;
    }

    /**
     * 设置exstk2Sales属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setExstk2Sales(BigDecimal value) {
        this.exstk2Sales = value;
    }

    /**
     * 获取originalprice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOriginalprice() {
        return originalprice;
    }

    /**
     * 设置originalprice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOriginalprice(BigDecimal value) {
        this.originalprice = value;
    }

    /**
     * 获取sellingprice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSellingprice() {
        return sellingprice;
    }

    /**
     * 设置sellingprice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSellingprice(BigDecimal value) {
        this.sellingprice = value;
    }

    /**
     * 获取pricemode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricemode() {
        return pricemode;
    }

    /**
     * 设置pricemode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricemode(String value) {
        this.pricemode = value;
    }

    /**
     * 获取priceapprove属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriceapprove() {
        return priceapprove;
    }

    /**
     * 设置priceapprove属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriceapprove(String value) {
        this.priceapprove = value;
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
     * 获取itemdiscount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSalesdiscount }
     *     
     */
    public ArrayOfSalesdiscount getItemdiscount() {
        return itemdiscount;
    }

    /**
     * 设置itemdiscount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSalesdiscount }
     *     
     */
    public void setItemdiscount(ArrayOfSalesdiscount value) {
        this.itemdiscount = value;
    }

    /**
     * 获取vipdiscountpercent属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVipdiscountpercent() {
        return vipdiscountpercent;
    }

    /**
     * 设置vipdiscountpercent属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVipdiscountpercent(BigDecimal value) {
        this.vipdiscountpercent = value;
    }

    /**
     * 获取vipdiscountless属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVipdiscountless() {
        return vipdiscountless;
    }

    /**
     * 设置vipdiscountless属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVipdiscountless(BigDecimal value) {
        this.vipdiscountless = value;
    }

    /**
     * 获取promotion属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSalespromtion }
     *     
     */
    public ArrayOfSalespromtion getPromotion() {
        return promotion;
    }

    /**
     * 设置promotion属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSalespromtion }
     *     
     */
    public void setPromotion(ArrayOfSalespromtion value) {
        this.promotion = value;
    }

    /**
     * 获取totaldiscountless1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotaldiscountless1() {
        return totaldiscountless1;
    }

    /**
     * 设置totaldiscountless1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotaldiscountless1(BigDecimal value) {
        this.totaldiscountless1 = value;
    }

    /**
     * 获取totaldiscountless2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotaldiscountless2() {
        return totaldiscountless2;
    }

    /**
     * 设置totaldiscountless2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotaldiscountless2(BigDecimal value) {
        this.totaldiscountless2 = value;
    }

    /**
     * 获取totaldiscountless属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotaldiscountless() {
        return totaldiscountless;
    }

    /**
     * 设置totaldiscountless属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotaldiscountless(BigDecimal value) {
        this.totaldiscountless = value;
    }

    /**
     * 获取tax属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSalestax }
     *     
     */
    public ArrayOfSalestax getTax() {
        return tax;
    }

    /**
     * 设置tax属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSalestax }
     *     
     */
    public void setTax(ArrayOfSalestax value) {
        this.tax = value;
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
     * 获取bonusearn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBonusearn() {
        return bonusearn;
    }

    /**
     * 设置bonusearn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBonusearn(BigDecimal value) {
        this.bonusearn = value;
    }

    /**
     * 获取salesitemremark属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesitemremark() {
        return salesitemremark;
    }

    /**
     * 设置salesitemremark属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesitemremark(String value) {
        this.salesitemremark = value;
    }

    /**
     * 获取refundreasoncode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefundreasoncode() {
        return refundreasoncode;
    }

    /**
     * 设置refundreasoncode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefundreasoncode(String value) {
        this.refundreasoncode = value;
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

}
