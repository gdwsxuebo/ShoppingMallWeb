
package com.smw.wsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
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
     * 获取alValue1属性的值。
     * 
     */
    public int getAlValue1() {
        return alValue1;
    }

    /**
     * 设置alValue1属性的值。
     * 
     */
    public void setAlValue1(int value) {
        this.alValue1 = value;
    }

    /**
     * 获取alValue2属性的值。
     * 
     */
    public int getAlValue2() {
        return alValue2;
    }

    /**
     * 设置alValue2属性的值。
     * 
     */
    public void setAlValue2(int value) {
        this.alValue2 = value;
    }

}
