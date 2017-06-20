
package com.smw.wsc;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfSalespromtion complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSalespromtion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="salespromtion" type="{http://tempurl.org}salespromtion" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSalespromtion", propOrder = {
    "salespromtion"
})
public class ArrayOfSalespromtion {

    @XmlElement(nillable = true)
    protected List<Salespromtion> salespromtion;

    /**
     * Gets the value of the salespromtion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the salespromtion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSalespromtion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Salespromtion }
     * 
     * 
     */
    public List<Salespromtion> getSalespromtion() {
        if (salespromtion == null) {
            salespromtion = new ArrayList<Salespromtion>();
        }
        return this.salespromtion;
    }

}
