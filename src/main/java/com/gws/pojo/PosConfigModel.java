package com.gws.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PosCommonConfigModel 收银机私有配置
 */
@Entity
@Table(name = "pos_conifg")
public class PosConfigModel implements Serializable {
	private static final long serialVersionUID = 1322438797825021692L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "mchtid", nullable = true, length = 100)
	private String mchtId;// 商户号
	@Column(name = "termid", nullable = true, length = 100)
	private String termId;// 终端号
	@Column(name = "authsn", nullable = true, length = 255)
	private String authSN;// 密钥
	@Column(name = "extendparm", nullable = true, length = 255)
	private String extendParm;// 扩展字段
	@Column(name = "conid", nullable = true, length = 32)
	private String conid;// 单独的配置连接
	@Column(name = "utime", nullable = false, length = 100)
	private String utime;// 单独的配置连接
	@Column(name = "storeid", nullable = false)
	private String storeid;// 商铺id
	@Column(name = "tillid", nullable = false)
	private String tillid;
	@Column(name = "v61tillid", nullable = false)
	private String v61tillid;
	@Column(name = "posid", nullable = true)
	private String posid;
	@Column(name = "outline_pay", nullable = true)
	private Integer outlinepay;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMchtId() {
		return mchtId;
	}

	public void setMchtId(String mchtId) {
		this.mchtId = mchtId;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getAuthSN() {
		return authSN;
	}

	public void setAuthSN(String authSN) {
		this.authSN = authSN;
	}

	public String getExtendParm() {
		return extendParm;
	}

	public void setExtendParm(String extendParm) {
		this.extendParm = extendParm;
	}

	public String getConid() {
		return conid;
	}

	public void setConid(String conid) {
		this.conid = conid;
	}

	public String getUtime() {
		return utime;
	}

	public void setUtime(String utime) {
		this.utime = utime;
	}

	public String getStoreid() {
		return storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
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

	public String getPosid() {
		return posid;
	}

	public void setPosid(String posid) {
		this.posid = posid;
	}

	public Integer getOutlinepay() {
		return outlinepay;
	}

	public void setOutlinepay(Integer outlinepay) {
		this.outlinepay = outlinepay;
	}

}
