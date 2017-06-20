package com.smw.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 收银员角色对应表
 * @author Administrator
 *
 */
@Entity
@Table(name = "xf_staff_role")
public class StaffRole implements Serializable {

	private static final long serialVersionUID = 4226512253142419965L;

	@Id
	@Column(precision = 19, scale = 0)
	private String xfStaffcode;

	@Column(name = "authority")
	private String authority;

	@Column(name = "gw_role_id")
	private Integer gwRoleId;

	public String getXfStaffcode() {
		return xfStaffcode;
	}

	public void setXfStaffcode(String xfStaffcode) {
		this.xfStaffcode = xfStaffcode;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Integer getGwRoleId() {
		return gwRoleId;
	}

	public void setGwRoleId(Integer gwRoleId) {
		this.gwRoleId = gwRoleId;
	}

}
