
package com.smw.wsc;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>esalesitem complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡdiscountamount���Ե�ֵ��
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
     * ����discountamount���Ե�ֵ��
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

}
