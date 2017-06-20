package com.smw.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * 权限组具备的功能
 *
 */
@Entity
@Table(name = "gw_role_group")
public class GwRoleGroup implements Serializable {

	private static final long serialVersionUID = -7388171130839147125L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(targetEntity = GwRole.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "gw_role_id")
	private GwRole gwRoleId; // 用户权限组【gw_role】id

	@ManyToOne(targetEntity = GwMenuTree.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "gw_menu_tree_id", nullable = false)
	private GwMenuTree gwMenuTreeId; // 导航菜单【gw_menu_tree】ID

	@Column(name = "time", nullable = true, length = 20)
	private String time; // 修改时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public GwRole getGwRoleId() {
		return gwRoleId;
	}

	public void setGwRoleId(GwRole gwRoleId) {
		this.gwRoleId = gwRoleId;
	}

	public GwMenuTree getGwMenuTreeId() {
		return gwMenuTreeId;
	}

	public void setGwMenuTreeId(GwMenuTree gwMenuTreeId) {
		this.gwMenuTreeId = gwMenuTreeId;
	}


}
