
package com.smw.wsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="al_value1" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="al_value2" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "alValue1",
    "alValue2"
})
@XmlRootElement(name = "test_a_plus_b")
public class TestAPlusB {

    @XmlElement(name = "al_value1")
    protected int alValue1;
    @XmlElement(name = "al_value2")
    protected int alValue2;

    /**
     * ��ȡalValue1���Ե�ֵ��
     * 
     */
    public int getAlValue1() {
        return alValue1;
    }

    /**
     * ����alValue1���Ե�ֵ��
     * 
     */
    public void setAlValue1(int value) {
        this.alValue1 = value;
    }

    /**
     * ��ȡalValue2���Ե�ֵ��
     * 
     */
    public int getAlValue2() {
        return alValue2;
    }

    /**
     * ����alValue2���Ե�ֵ��
     * 
     */
    public void setAlValue2(int value) {
        this.alValue2 = value;
    }

}
