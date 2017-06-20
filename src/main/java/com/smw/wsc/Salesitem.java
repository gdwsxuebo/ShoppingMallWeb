
package com.smw.wsc;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>salesitem complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡiscounteritemcode���Ե�ֵ��
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
     * ����iscounteritemcode���Ե�ֵ��
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
     * ��ȡlineno���Ե�ֵ��
     * 
     */
    public int getLineno() {
        return lineno;
    }

    /**
     * ����lineno���Ե�ֵ��
     * 
     */
    public void setLineno(int value) {
        this.lineno = value;
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
     * ��ȡcounteritemcode���Ե�ֵ��
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
     * ����counteritemcode���Ե�ֵ��
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
     * ��ȡitemcode���Ե�ֵ��
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
     * ����itemcode���Ե�ֵ��
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
     * ��ȡplucode���Ե�ֵ��
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
     * ����plucode���Ե�ֵ��
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
     * ��ȡcolorcode���Ե�ֵ��
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
     * ����colorcode���Ե�ֵ��
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
     * ��ȡsizecode���Ե�ֵ��
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
     * ����sizecode���Ե�ֵ��
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
     * ��ȡitemlotnum���Ե�ֵ��
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
     * ����itemlotnum���Ե�ֵ��
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
     * ��ȡserialnum���Ե�ֵ��
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
     * ����serialnum���Ե�ֵ��
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
     * ��ȡisdeposit���Ե�ֵ��
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
     * ����isdeposit���Ե�ֵ��
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
     * ��ȡiswholesale���Ե�ֵ��
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
     * ����iswholesale���Ե�ֵ��
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
     * ��ȡinvttype���Ե�ֵ��
     * 
     */
    public short getInvttype() {
        return invttype;
    }

    /**
     * ����invttype���Ե�ֵ��
     * 
     */
    public void setInvttype(short value) {
        this.invttype = value;
    }

    /**
     * ��ȡqty���Ե�ֵ��
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
     * ����qty���Ե�ֵ��
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
     * ��ȡexstk2Sales���Ե�ֵ��
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
     * ����exstk2Sales���Ե�ֵ��
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
     * ��ȡoriginalprice���Ե�ֵ��
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
     * ����originalprice���Ե�ֵ��
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
     * ��ȡsellingprice���Ե�ֵ��
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
     * ����sellingprice���Ե�ֵ��
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
     * ��ȡpricemode���Ե�ֵ��
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
     * ����pricemode���Ե�ֵ��
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
     * ��ȡpriceapprove���Ե�ֵ��
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
     * ����priceapprove���Ե�ֵ��
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
     * ��ȡitemdiscount���Ե�ֵ��
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
     * ����itemdiscount���Ե�ֵ��
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
     * ��ȡvipdiscountpercent���Ե�ֵ��
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
     * ����vipdiscountpercent���Ե�ֵ��
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
     * ��ȡvipdiscountless���Ե�ֵ��
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
     * ����vipdiscountless���Ե�ֵ��
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
     * ��ȡpromotion���Ե�ֵ��
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
     * ����promotion���Ե�ֵ��
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
     * ��ȡtotaldiscountless1���Ե�ֵ��
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
     * ����totaldiscountless1���Ե�ֵ��
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
     * ��ȡtotaldiscountless2���Ե�ֵ��
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
     * ����totaldiscountless2���Ե�ֵ��
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
     * ��ȡtotaldiscountless���Ե�ֵ��
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
     * ����totaldiscountless���Ե�ֵ��
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
     * ��ȡtax���Ե�ֵ��
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
     * ����tax���Ե�ֵ��
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
     * ��ȡbonusearn���Ե�ֵ��
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
     * ����bonusearn���Ե�ֵ��
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
     * ��ȡsalesitemremark���Ե�ֵ��
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
     * ����salesitemremark���Ե�ֵ��
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
     * ��ȡrefundreasoncode���Ե�ֵ��
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
     * ����refundreasoncode���Ե�ֵ��
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

}
