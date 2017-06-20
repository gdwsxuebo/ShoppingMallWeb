
package com.smw.wsc;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>esalesitem complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="esalesitem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lineno" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="itemcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qty" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="discountamount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="netamount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="bonusearn" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="extendparam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salesitemremark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "esalesitem", propOrder = {
    "lineno",
    "itemcode",
    "qty",
    "discountamount",
    "netamount",
    "bonusearn",
    "extendparam",
    "salesitemremark"
})
public class Esalesitem {

    protected int lineno;
    protected String itemcode;
    @XmlElement(required = true)
    protected BigDecimal qty;
    @XmlElement(required = true)
    protected BigDecimal discountamount;
    @XmlElement(required = true)
    protected BigDecimal netamount;
    @XmlElement(required = true)
    protected BigDecimal bonusearn;
    protected String extendparam;
    protected String salesitemremark;

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
     * 获取discountamount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDiscountamount() {
        return discountamount;
    }

    /**
     * 设置discountamount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDiscountamount(BigDecimal value) {
        this.discountamount = value;
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

}
