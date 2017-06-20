package com.smw.pojo;

import java.io.Serializable;
import java.util.Date;

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
 * 退货权限
 * @author suen
 * @date 2016年7月26日-下午5:07:54
 * @version 1.0
 */
@Entity
@Table(name="return_goods_authority")
public class ReturnGoodsAuthority implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -7822758320811099547L;

	/**
	 * 自增量id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	/**
	 * 收银员编号(外键：xf_staff.xf_staffcode)
	 */
	@ManyToOne(targetEntity=XfStaff.class,fetch=FetchType.EAGER)
	@JoinColumn(nullable=false,name="staffcode")
    private XfStaff staffcode;
	
	/**
	 * 创建日期
	 */
	@Column(nullable=false)
	private Date createDate=new Date();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public XfStaff getStaffcode() {
		return staffcode;
	}

	public void setStaffcode(XfStaff staffcode) {
		this.staffcode = staffcode;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    
}