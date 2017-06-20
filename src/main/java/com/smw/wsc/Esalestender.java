
package com.smw.wsc;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>esalestender complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="esalestender">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lineno" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tendercode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "esalestender", propOrder = {
    "lineno",
    "tendercode",
    "payamount",
    "baseamount",
    "excessamount",
    "extendparam",
    "remark"
})
public class Esalestender {

    protected int lineno;
    protected String tendercode;
    @XmlElement(required = true)
    protected BigDecimal payamount;
    @XmlElement(required = true)
    protected BigDecimal baseamount;
    @XmlElement(required = true)
    protected BigDecimal excessamount;
    protected String extendparam;
    protected String remark;

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
     * ��ȡtendercode���Ե�ֵ��
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
     * ����tendercode���Ե�ֵ��
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
     * ��ȡpayamount���Ե�ֵ��
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
     * ����payamount���Ե�ֵ��
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
     * ��ȡbaseamount���Ե�ֵ��
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
     * ����baseamount���Ե�ֵ��
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
     * ��ȡexcessamount���Ե�ֵ��
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
     * ����excessamount���Ե�ֵ��
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
     * ��ȡremark���Ե�ֵ��
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
     * ����remark���Ե�ֵ��
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
