
package com.smw.wsc;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>salestender complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡtendertype���Ե�ֵ��
     * 
     */
    public short getTendertype() {
        return tendertype;
    }

    /**
     * ����tendertype���Ե�ֵ��
     * 
     */
    public void setTendertype(short value) {
        this.tendertype = value;
    }

    /**
     * ��ȡtendercategory���Ե�ֵ��
     * 
     */
    public short getTendercategory() {
        return tendercategory;
    }

    /**
     * ����tendercategory���Ե�ֵ��
     * 
     */
    public void setTendercategory(short value) {
        this.tendercategory = value;
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
