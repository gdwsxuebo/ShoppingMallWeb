
package com.smw.wsc;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>salespromtion complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="salespromtion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="promotionid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="promotionuseqty" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="promotionless" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="promotionpkgcount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "salespromtion", propOrder = {
    "promotionid",
    "promotionuseqty",
    "promotionless",
    "promotionpkgcount"
})
public class Salespromtion {

    protected String promotionid;
    @XmlElement(required = true)
    protected BigDecimal promotionuseqty;
    @XmlElement(required = true)
    protected BigDecimal promotionless;
    @XmlElement(required = true)
    protected BigDecimal promotionpkgcount;

    /**
     * 获取promotionid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPromotionid() {
        return promotionid;
    }

    /**
     * 设置promotionid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPromotionid(String value) {
        this.promotionid = value;
    }

    /**
     * 获取promotionuseqty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPromotionuseqty() {
        return promotionuseqty;
    }

    /**
     * 设置promotionuseqty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPromotionuseqty(BigDecimal value) {
        this.promotionuseqty = value;
    }

    /**
     * 获取promotionless属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPromotionless() {
        return promotionless;
    }

    /**
     * 设置promotionless属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPromotionless(BigDecimal value) {
        this.promotionless = value;
    }

    /**
     * 获取promotionpkgcount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPromotionpkgcount() {
        return promotionpkgcount;
    }

    /**
     * 设置promotionpkgcount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPromotionpkgcount(BigDecimal value) {
        this.promotionpkgcount = value;
    }

}
