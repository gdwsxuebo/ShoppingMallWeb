package com.smw.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 收银员信息表
 * @author suen
 * @date 2016年5月18日-下午4:31:25
 * @version 1.0
 */
@Entity
@Table(name="xf_staff")
public class XfStaff implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1872451279379746606L;

	/**
	 * 员工号(全系统唯一，编码规则：店铺号-人员流水号)
	 */
	@Id
	@Column(length=36)
    private String xfStaffcode;

    /**
	 * 描述（姓名）
	 */
	@Column(nullable=true,length=36)
    private String xfName;

    /**
	 * 密码
	 */
	@Column(nullable=false,length=32)
    private String xfPassword;

    /**
	 * 关联店铺编码(外键：xf_store.xf_storecode)
	 */
	@ManyToOne(targetEntity=XfStore.class,fetch=FetchType.EAGER)
	@JoinColumn(nullable=true,name="xfIssuestore")
    private XfStore xfIssuestore;

    /**
	 * 激活 1(true) 0(false)
	 */
	@Column(nullable=false)
    private Boolean enabled=true;

	/**
	 * 权限名称
	 */
	@OneToOne(mappedBy="xfStaffcode",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(nullable=false,name="staffRole")
	private XfStaffRole staffRole;
	
	/**
	 * 是否有退货权限
	 */
	@Column(nullable=true,length=36)
	private Integer isReturnGoodsAuth;
		
    
    public String getXfStaffcode() {
        return xfStaffcode;
    }

    public void setXfStaffcode(String xfStaffcode) {
        this.xfStaffcode = xfStaffcode == null ? null : xfStaffcode.trim();
    }

    public String getXfName() {
        return xfName;
    }

    public void setXfName(String xfName) {
        this.xfName = xfName == null ? null : xfName.trim();
    }

    public String getXfPassword() {
        return xfPassword;
    }

    public void setXfPassword(String xfPassword) {
        this.xfPassword = xfPassword == null ? null : xfPassword.trim();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

	public XfStore getXfIssuestore() {
		return xfIssuestore;
	}

	public void setXfIssuestore(XfStore xfIssuestore) {
		this.xfIssuestore = xfIssuestore;
	}

	public XfStaffRole getStaffRole() {
		return staffRole;
	}

	public void setStaffRole(XfStaffRole staffRole) {
		this.staffRole = staffRole;
	}

	public Integer getIsReturnGoodsAuth() {
		return isReturnGoodsAuth;
	}

	public void setIsReturnGoodsAuth(Integer isReturnGoodsAuth) {
		this.isReturnGoodsAuth = isReturnGoodsAuth;
	}
}