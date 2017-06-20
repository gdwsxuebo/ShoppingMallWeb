package com.smw.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.smw.enums.ActivityStatusEnum;
import com.smw.enums.RoleTypeEnum;

/**
 * 用户权限组
 *
 */
@Entity
@Table(name = "gw_role")
public class GwRole implements Serializable {

	private static final long serialVersionUID = -8698315809181122417L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",length = 11)
	private Integer id;

	@Column(name = "cn_name", nullable = true, length = 100)
	private String cnName; // 中文名称

	@Column(name = "en_name", nullable = true, length = 100)
	private String enName; // 英文名称

	@Column(name = "note", nullable = true, length = 100)
	private String note; // 描述

	@Column(name = "label", nullable = true, length = 2)
	private Integer label; // 用处: 1- server端 2 – pos端 3-server端和pos端

	@Column(name = "state", nullable = true, length = 1)
	private Integer state; // 状态（1-正常 0 – 隐藏）

	@Column(name = "time", nullable = true, length = 100)
	private String time; // 修改时间

	@Column(name = "order_num", nullable = true, length = 100)
	private Integer orderNum; // 排序
	
	@Transient
	private String stateName;
	
	@Transient
	private String labelName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}


	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getLabel() {
		return label;
	}

	public void setLabel(Integer label) {
		this.label = label;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getStateName() {
		return ActivityStatusEnum.getDisplayName(state);
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getLabelName() {
		return RoleTypeEnum.getDisplayName(label);
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

}
