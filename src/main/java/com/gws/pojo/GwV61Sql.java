package com.gws.pojo;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "V61")
public class GwV61Sql {
	private String XF_STORE;
	private String XF_STAFF;
	private String XF_TENDER;
	private String XF_ITEMMAS;
	private String FILTER_STAFF;
	
	public String getFILTER_STAFF() {
		return FILTER_STAFF;
	}
	public void setFILTER_STAFF(String fILTER_STAFF) {
		FILTER_STAFF = fILTER_STAFF;
	}
	public String getXF_STORE() {
		return XF_STORE;
	}
	public void setXF_STORE(String xF_STORE) {
		XF_STORE = xF_STORE;
	}
	public String getXF_STAFF() {
		return XF_STAFF;
	}
	public void setXF_STAFF(String xF_STAFF) {
		XF_STAFF = xF_STAFF;
	}
	public String getXF_TENDER() {
		return XF_TENDER;
	}
	public void setXF_TENDER(String xF_TENDER) {
		XF_TENDER = xF_TENDER;
	}
	public String getXF_ITEMMAS() {
		return XF_ITEMMAS;
	}
	public void setXF_ITEMMAS(String xF_ITEMMAS) {
		XF_ITEMMAS = xF_ITEMMAS;
	}
	
	
}
