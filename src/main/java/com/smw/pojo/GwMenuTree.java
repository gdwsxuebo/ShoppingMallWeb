package com.smw.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * GwMenuTree:菜单实体类
 *
 * @author  shengjinpeng
 * @date    2017年2月21日
 * @version jdk1.8
 *
 */
@Entity
@Table(name="gw_menu_tree")
public class GwMenuTree implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7077805861630293832L;

	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    /**
	 * 名称
	 */
	@Column(nullable=false,length=20)
    private String name;

	@Column(nullable=false,length=20)
	private String note;
	
	@Column(nullable=false,length=50)
	private String urladdress;
	
	@Column(nullable=true,length=11)
	private Integer fid;
	
	@Column(nullable=true,length=20)
	private String icon;
	
	@Transient
	private List<GwMenuTree> childrenList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getUrladdress() {
		return urladdress;
	}

	public void setUrladdress(String urladdress) {
		this.urladdress = urladdress;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<GwMenuTree> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<GwMenuTree> childrenList) {
		this.childrenList = childrenList;
	}
	
	
}