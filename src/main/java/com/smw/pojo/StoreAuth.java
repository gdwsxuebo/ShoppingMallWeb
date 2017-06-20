package com.smw.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * 店铺权限
 */
@Entity
@Table(name="xf_store_auth")
public class StoreAuth implements Serializable {

	private static final long serialVersionUID = -1630247598594325335L;
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private String id;

	@Column(name = "xfStoreCode")
	private String storeCode;
	
	@Column(name="storeName")
	private String storeName;

	@Column(name = "auth_id")
	private String authId;
	
	@Column(name="tillid")
	private String tillid;
	
	@Column(name="v61tillid")
	private String v61tillid;
	
	@Column(name="ctime")
	private String ctime;
	
	@Column(name = "screen_style", length = 1)
	private String screenStyle;// 单双屏幕选择
	
	private String issueRanges;
	
	private String type;//是否中央收银机    1:否  2：是
	
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIssueRanges() {
		return issueRanges;
	}

	public void setIssueRanges(String issueRanges) {
		this.issueRanges = issueRanges;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getTillid() {
		return tillid;
	}

	public void setTillid(String tillid) {
		this.tillid = tillid;
	}

	public String getV61tillid() {
		return v61tillid;
	}

	public void setV61tillid(String v61tillid) {
		this.v61tillid = v61tillid;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getScreenStyle() {
		return screenStyle;
	}

	public void setScreenStyle(String screenStyle) {
		this.screenStyle = screenStyle;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}
