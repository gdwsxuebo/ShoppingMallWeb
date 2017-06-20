
package com.smw.wsc;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>salestax complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="salestax">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="taxrate" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="taxamount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "salestax", propOrder = {
    "taxrate",
    "taxamount"
})
public class Salestax {

    @XmlElement(required = true)
    protected BigDecimal taxrate;
    @XmlElement(required = true)
    protected BigDecimal taxamount;

    /**
     * 获取taxrate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTaxrate() {
        return taxrate;
    }

    /**
     * 设置taxrate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTaxrate(BigDecimal value) {
        this.taxrate = value;
    }

    /**
     * 获取taxamount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTaxamount() {
        return taxamount;
    }

    /**
     * 设置taxamount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTaxamount(BigDecimal value) {
        this.taxamount = value;
    }

}
