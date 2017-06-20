package com.smw.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 店铺信息表
 * 
 * @author suen
 * @date 2016年5月18日-下午4:36:06
 * @version 1.0
 */
@Entity
@Table(name = "xf_store")
public class XfStore implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 5290262183450882199L;

	/**
	 * 店铺编码(编码规则：铺位号-店铺换租流水号) 铺位号：楼层号-铺位流水号 楼层号：B*，L*
	 */
	@Id
	@Column(length = 36)
	private String xfStorecode;

	@Column(name = "gwFormatsTreeId")
	private String gwFormatsTreeId; // 业态

	@Column(name = "gwBuildingTreeId")
	private String gwBuildingTreeId; // 楼宇

	/**
	 * 店铺名称
	 */
	@Column(nullable = false, length = 36)
	private String xfName;

	/**
	 * 商场编号(外键：xf_mall.mallid)
	 */
	@ManyToOne(targetEntity = XfMall.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "xfMallid")
	private XfMall xfMallid;

	/**
	 * 后台管理只需要在店铺信息中有一个字段记录有几台收银机（3）。前台配置平板的时候，在系统管理里手工配置是01、02、03号收银机。
	 */
	@Column(nullable = false)
	private Integer xfTillcount = 0;

	/**
	 * 更新提醒(后台数据有更新，前端查询该字段决定是否更新。1:有更新, 0:已更新)
	 */
	@Column(nullable = false)
	private Boolean xfUpdate = true;

	/**
	 * 中央收银标记(1:是, 0:否)
	 */
	@Column(nullable = false)
	private Boolean xfCenter = false;

	/**
	 * 是否显示 1有效 0失效
	 */
	@Column(nullable = false, length = 1)
	private String isInvalid = "1";

	public String getIsInvalid() {
		return isInvalid;
	}

	public void setIsInvalid(String isInvalid) {
		this.isInvalid = isInvalid;
	}

	/**
	 * 中央店铺信息
	 */
	@Transient
	private XfStore xfCenterStore;

	@Transient
	private String screenStyle;
	
	/**
	 * 签约时间
	 */
	@Column(length = 36)
	private String effectTime;

	public String getXfStorecode() {
		return xfStorecode;
	}

	public void setXfStorecode(String xfStorecode) {
		this.xfStorecode = xfStorecode == null ? null : xfStorecode.trim();
	}

	public String getXfName() {
		return xfName;
	}

	public void setXfName(String xfName) {
		this.xfName = xfName == null ? null : xfName.trim();
	}

	public Integer getXfTillcount() {
		return xfTillcount;
	}

	public void setXfTillcount(Integer xfTillcount) {
		this.xfTillcount = xfTillcount;
	}

	public Boolean getXfUpdate() {
		return xfUpdate;
	}

	public void setXfUpdate(Boolean xfUpdate) {
		this.xfUpdate = xfUpdate;
	}

	public Boolean getXfCenter() {
		return xfCenter;
	}

	public void setXfCenter(Boolean xfCenter) {
		this.xfCenter = xfCenter;
	}

	public XfMall getXfMallid() {
		return xfMallid;
	}

	public void setXfMallid(XfMall xfMallid) {
		this.xfMallid = xfMallid;
	}

	public XfStore getXfCenterStore() {
		return xfCenterStore;
	}

	public void setXfCenterStore(XfStore xfCenterStore) {
		this.xfCenterStore = xfCenterStore;
	}

	public String getScreenStyle() {
		return screenStyle;
	}

	public void setScreenStyle(String screenStyle) {
		this.screenStyle = screenStyle;
	}

	public String getGwFormatsTreeId() {
		return gwFormatsTreeId;
	}

	public void setGwFormatsTreeId(String gwFormatsTreeId) {
		this.gwFormatsTreeId = gwFormatsTreeId;
	}

	public String getGwBuildingTreeId() {
		return gwBuildingTreeId;
	}

	public void setGwBuildingTreeId(String gwBuildingTreeId) {
		this.gwBuildingTreeId = gwBuildingTreeId;
	}

	public String getEffectTime() {
		return effectTime;
	}

	public void setEffectTime(String effectTime) {
		this.effectTime = effectTime;
	}

}