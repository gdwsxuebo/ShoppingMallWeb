package com.smw.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/***
 * 收银机店铺关联表
 */
@Entity
@Table(name="xf_store_tillid")
public class TillidToStore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private String id;
	
	@Column(name = "tillid")
	private String tillid;
	@Column(name = "xfStoreCode")
	private String xfStoreCode;
	
	public String getTillid() {
		return tillid;
	}

	public void setTillid(String tillid) {
		this.tillid = tillid;
	}

	public String getXfStoreCode() {
		return xfStoreCode;
	}

	public void setXfStoreCode(String xfStoreCode) {
		this.xfStoreCode = xfStoreCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	
	
	

}
