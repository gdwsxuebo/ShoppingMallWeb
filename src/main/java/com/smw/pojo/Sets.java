package com.smw.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 设置表
 * @author suen
 * @date 2016年5月28日-下午3:19:48
 * @version 1.0
 */
@Entity
@Table(name="xf_Sets")
public class Sets implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4158356652127825663L;

	/**
	 * ID键
	 */
	@Id
	@Column(length=30)
	private String id;
	
	/**
	 * 值
	 */
	@Column(length=2000,nullable=false)
	private String value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
