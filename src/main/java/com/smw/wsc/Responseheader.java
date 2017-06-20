
package com.smw.wsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>responseheader complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="responseheader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="responsecode" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="responsemessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pagerecords" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pageno" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="updatecount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="maxrecords" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="maxpageno" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="messagetype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="messageid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseheader", propOrder = {
    "responsecode",
    "responsemessage",
    "pagerecords",
    "pageno",
    "updatecount",
    "maxrecords",
    "maxpageno",
    "messagetype",
    "messageid",
    "version"
})
public class Responseheader {

    protected short responsecode;
    protected String responsemessage;
    protected int pagerecords;
    protected int pageno;
    protected int updatecount;
    protected int maxrecords;
    protected int maxpageno;
    protected String messagetype;
    protected String messageid;
    protected String version;

    /**
     * 获取responsecode属性的值。
     * 
     */
    public short getResponsecode() {
        return responsecode;
    }

    /**
     * 设置responsecode属性的值。
     * 
     */
    public void setResponsecode(short value) {
        this.responsecode = value;
    }

    /**
     * 获取responsemessage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponsemessage() {
        return responsemessage;
    }

    /**
     * 设置responsemessage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponsemessage(String value) {
        this.responsemessage = value;
    }

    /**
     * 获取pagerecords属性的值。
     * 
     */
    public int getPagerecords() {
        return pagerecords;
    }

    /**
     * 设置pagerecords属性的值。
     * 
     */
    public void setPagerecords(int value) {
        this.pagerecords = value;
    }

    /**
     * 获取pageno属性的值。
     * 
     */
    public int getPageno() {
        return pageno;
    }

    /**
     * 设置pageno属性的值。
     * 
     */
    public void setPageno(int value) {
        this.pageno = value;
    }

    /**
     * 获取updatecount属性的值。
     * 
     */
    public int getUpdatecount() {
        return updatecount;
    }

    /**
     * 设置updatecount属性的值。
     * 
     */
    public void setUpdatecount(int value) {
        this.updatecount = value;
    }

    /**
     * 获取maxrecords属性的值。
     * 
     */
    public int getMaxrecords() {
        return maxrecords;
    }

    /**
     * 设置maxrecords属性的值。
     * 
     */
    public void setMaxrecords(int value) {
        this.maxrecords = value;
    }

    /**
     * 获取maxpageno属性的值。
     * 
     */
    public int getMaxpageno() {
        return maxpageno;
    }

    /**
     * 设置maxpageno属性的值。
     * 
     */
    public void setMaxpageno(int value) {
        this.maxpageno = value;
    }

    /**
     * 获取messagetype属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessagetype() {
        return messagetype;
    }

    /**
     * 设置messagetype属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessagetype(String value) {
        this.messagetype = value;
    }

    /**
     * 获取messageid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageid() {
        return messageid;
    }

    /**
     * 设置messageid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageid(String value) {
        this.messageid = value;
    }

    /**
     * 获取version属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置version属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

}
