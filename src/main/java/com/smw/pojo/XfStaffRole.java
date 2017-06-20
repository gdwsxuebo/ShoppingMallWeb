package com.smw.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 收银员信息表
 * @author suen
 * @date 2016年5月18日-下午5:28:52
 * @version 1.0
 */
@Entity
@Table(name="xf_staff_role")
public class XfStaffRole implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 2245622279377743070L;

	/**
	 * 权限名称
	 */
	@Column(nullable=false,length=36)
    private String authority;

    /**
	 * 收银员编号(外键：xf_staff.xf_staffcode)
	 */
	@Id
	@OneToOne(targetEntity=XfStaff.class,fetch=FetchType.EAGER)
	@JoinColumn(nullable=false,name="xfStaffcode")
    private XfStaff xfStaffcode;
	@Transient
	private String roleName;
	
	
	@ManyToOne(targetEntity = GwRole.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "gw_role_id")
	private GwRole gwRoleId; // 用户权限组【gw_role】id
	
    public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getAuthority() {
        return authority==null?null:authority.trim();
    }

    public void setAuthority(String authority) {
        this.authority = authority == null ? null : authority.trim();
    }

	public XfStaff getXfStaffcode() {
		return xfStaffcode;
	}

	public void setXfStaffcode(XfStaff xfStaffcode) {
		this.xfStaffcode = xfStaffcode;
	}

	public GwRole getGwRoleId() {
		return gwRoleId;
	}

	public void setGwRoleId(GwRole gwRoleId) {
		this.gwRoleId = gwRoleId;
	}
   
}