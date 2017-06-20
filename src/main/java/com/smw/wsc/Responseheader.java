
package com.smw.wsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>responseheader complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡresponsecode���Ե�ֵ��
     * 
     */
    public short getResponsecode() {
        return responsecode;
    }

    /**
     * ����responsecode���Ե�ֵ��
     * 
     */
    public void setResponsecode(short value) {
        this.responsecode = value;
    }

    /**
     * ��ȡresponsemessage���Ե�ֵ��
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
     * ����responsemessage���Ե�ֵ��
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
     * ��ȡpagerecords���Ե�ֵ��
     * 
     */
    public int getPagerecords() {
        return pagerecords;
    }

    /**
     * ����pagerecords���Ե�ֵ��
     * 
     */
    public void setPagerecords(int value) {
        this.pagerecords = value;
    }

    /**
     * ��ȡpageno���Ե�ֵ��
     * 
     */
    public int getPageno() {
        return pageno;
    }

    /**
     * ����pageno���Ե�ֵ��
     * 
     */
    public void setPageno(int value) {
        this.pageno = value;
    }

    /**
     * ��ȡupdatecount���Ե�ֵ��
     * 
     */
    public int getUpdatecount() {
        return updatecount;
    }

    /**
     * ����updatecount���Ե�ֵ��
     * 
     */
    public void setUpdatecount(int value) {
        this.updatecount = value;
    }

    /**
     * ��ȡmaxrecords���Ե�ֵ��
     * 
     */
    public int getMaxrecords() {
        return maxrecords;
    }

    /**
     * ����maxrecords���Ե�ֵ��
     * 
     */
    public void setMaxrecords(int value) {
        this.maxrecords = value;
    }

    /**
     * ��ȡmaxpageno���Ե�ֵ��
     * 
     */
    public int getMaxpageno() {
        return maxpageno;
    }

    /**
     * ����maxpageno���Ե�ֵ��
     * 
     */
    public void setMaxpageno(int value) {
        this.maxpageno = value;
    }

    /**
     * ��ȡmessagetype���Ե�ֵ��
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
     * ����messagetype���Ե�ֵ��
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
     * ��ȡmessageid���Ե�ֵ��
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
     * ����messageid���Ե�ֵ��
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
     * ��ȡversion���Ե�ֵ��
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
     * ����version���Ե�ֵ��
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
