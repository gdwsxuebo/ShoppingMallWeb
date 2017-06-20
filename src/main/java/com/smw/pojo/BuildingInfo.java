package com.smw.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 楼宇信息
 */
@Entity
@Table(name = "building_info")
public class BuildingInfo implements Serializable {

	private static final long serialVersionUID = 7810385021208223291L;

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "bm")
	private String bm;

	@Column(name = "name")
	private String name;
	
	@Column(name = "area")
	private String area;

	@Column(name="state")
	private Integer state;

	@Column(name="time")
	private String time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
