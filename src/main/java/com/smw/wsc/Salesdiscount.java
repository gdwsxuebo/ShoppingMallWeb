
package com.smw.wsc;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>salesdiscount complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡdiscountapprove���Ե�ֵ��
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
     * ����discountapprove���Ե�ֵ��
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
     * ��ȡdiscountmode���Ե�ֵ��
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
     * ����discountmode���Ե�ֵ��
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
     * ��ȡdiscountvalue���Ե�ֵ��
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
     * ����discountvalue���Ե�ֵ��
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
     * ��ȡdiscountless���Ե�ֵ��
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
     * ����discountless���Ե�ֵ��
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
