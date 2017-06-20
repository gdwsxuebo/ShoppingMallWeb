
package com.smw.wsc;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>salespromtion complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡpromotionid���Ե�ֵ��
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
     * ����promotionid���Ե�ֵ��
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
     * ��ȡpromotionuseqty���Ե�ֵ��
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
     * ����promotionuseqty���Ե�ֵ��
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
     * ��ȡpromotionless���Ե�ֵ��
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
     * ����promotionless���Ե�ֵ��
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
     * ��ȡpromotionpkgcount���Ե�ֵ��
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
     * ����promotionpkgcount���Ե�ֵ��
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
