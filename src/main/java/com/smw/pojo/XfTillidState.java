package com.smw.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 平板收银机在线表
 * @author suen
 * @date 2016年5月18日-下午4:59:09
 * @version 1.0
 */
@Entity
@Table(name="xf_tillid_state")
public class XfTillidState implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 5835102451111496378L;

	/**
	 * 收银机号(后台管理只需要在店铺信息中有一个字段记录有几台收银机（3）。前台配置平板的时候，在系统管理里手工配置是01、02、03号收银机。)
	 */
	@Id
	@Column(length=15)
    private String tillid;

    /**
     * 店铺编码(外键：xf_store.xf_storecode)
     */
	@Id
	@ManyToOne(targetEntity=XfStore.class,fetch=FetchType.EAGER)
	@JoinColumn(nullable=false,name="xfStorecode")
    private XfStore xfStorecode;
    
	/**
	 * 设备信息(可作为设备标识)
	 */
	@Column(nullable=false,length=36)
    private String deviceInfo;

    /**
     * 收银机的IP地址(在线收银机显示现有IP地址，离线收银机显示上次连线的IP地址)
     */
	@Column(nullable=false,length=36)
    private String ip;

    /**
     * 最近访问时间
     */
	@Column(nullable=false)
    private Date visitTime;

    /**
     * 更新提醒(后台数据有更新，前端查询该字段决定是否更新。1:有更新, 0:已更新)
     */
	@Column(nullable=false)
    private Boolean xfUpdate;
	
	/*
	 * 收银机是否在线，1 ：是  0：否
	 * */
	@Column(nullable=false)
	private String onlineType;

	
	/**
	 * 收银机登录用户名
	 * */
	
	@Column(nullable=false)
	private String xfStaffcode;

	//字符串日期时间
	@Transient
	private String strDate;
	@Transient
	private int state;
	
	
	
    public String getOnlineType() {
		return onlineType;
	}

	public void setOnlineType(String onlineType) {
		this.onlineType = onlineType;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo == null ? null : deviceInfo.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public Boolean getXfUpdate() {
        return xfUpdate;
    }

    public void setXfUpdate(Boolean xfUpdate) {
        this.xfUpdate = xfUpdate;
    }

	public String getTillid() {
		return tillid;
	}

	public void setTillid(String tillid) {
		this.tillid = tillid;
	}

	public XfStore getXfStorecode() {
		return xfStorecode;
	}

	public void setXfStorecode(XfStore xfStorecode) {
		this.xfStorecode = xfStorecode;
	}

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public String getXfStaffcode() {
		return xfStaffcode;
	}

	public void setXfStaffcode(String xfStaffcode) {
		this.xfStaffcode = xfStaffcode;
	}
	
	
    
}