
package com.smw.wsc;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>salesdiscount complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="salesdiscount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="discountapprove" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="discountmode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="discountvalue" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="discountless" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "salesdiscount", propOrder = {
    "discountapprove",
    "discountmode",
    "discountvalue",
    "discountless"
})
public class Salesdiscount {

    protected String discountapprove;
    protected String discountmode;
    @XmlElement(required = true)
    protected BigDecimal discountvalue;
    @XmlElement(required = true)
    protected BigDecimal discountless;

    /**
     * 获取discountapprove属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountapprove() {
        return discountapprove;
    }

    /**
     * 设置discountapprove属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountapprove(String value) {
        this.discountapprove = value;
    }

    /**
     * 获取discountmode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountmode() {
        return discountmode;
    }

    /**
     * 设置discountmode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountmode(String value) {
        this.discountmode = value;
    }

    /**
     * 获取discountvalue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDiscountvalue() {
        return discountvalue;
    }

    /**
     * 设置discountvalue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDiscountvalue(BigDecimal value) {
        this.discountvalue = value;
    }

    /**
     * 获取discountless属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDiscountless() {
        return discountless;
    }

    /**
     * 设置discountless属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDiscountless(BigDecimal value) {
        this.discountless = value;
    }

}
