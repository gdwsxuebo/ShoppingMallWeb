
package com.smw.wsc;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>salestender complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="salestender">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lineno" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tendercode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tendertype" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="tendercategory" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="payamount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="baseamount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="excessamount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="extendparam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "salestender", propOrder = {
    "lineno",
    "tendercode",
    "tendertype",
    "tendercategory",
    "payamount",
    "baseamount",
    "excessamount",
    "extendparam",
    "remark"
})
public class Salestender {

    protected int lineno;
    protected String tendercode;
    protected short tendertype;
    protected short tendercategory;
    @XmlElement(required = true)
    protected BigDecimal payamount;
    @XmlElement(required = true)
    protected BigDecimal baseamount;
    @XmlElement(required = true)
    protected BigDecimal excessamount;
    protected String extendparam;
    protected String remark;

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
     * 获取tendercode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTendercode() {
        return tendercode;
    }

    /**
     * 设置tendercode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTendercode(String value) {
        this.tendercode = value;
    }

    /**
     * 获取tendertype属性的值。
     * 
     */
    public short getTendertype() {
        return tendertype;
    }

    /**
     * 设置tendertype属性的值。
     * 
     */
    public void setTendertype(short value) {
        this.tendertype = value;
    }

    /**
     * 获取tendercategory属性的值。
     * 
     */
    public short getTendercategory() {
        return tendercategory;
    }

    /**
     * 设置tendercategory属性的值。
     * 
     */
    public void setTendercategory(short value) {
        this.tendercategory = value;
    }

    /**
     * 获取payamount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPayamount() {
        return payamount;
    }

    /**
     * 设置payamount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPayamount(BigDecimal value) {
        this.payamount = value;
    }

    /**
     * 获取baseamount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBaseamount() {
        return baseamount;
    }

    /**
     * 设置baseamount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBaseamount(BigDecimal value) {
        this.baseamount = value;
    }

    /**
     * 获取excessamount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getExcessamount() {
        return excessamount;
    }

    /**
     * 设置excessamount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setExcessamount(BigDecimal value) {
        this.excessamount = value;
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
     * 获取remark属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置remark属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

}
