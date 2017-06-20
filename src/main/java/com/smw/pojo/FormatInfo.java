package com.smw.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 业态信息
 */
@Entity
@Table(name = "format_info")
public class FormatInfo implements Serializable {

	private static final long serialVersionUID = -2868974000788061358L;

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "bm")
	private String bm;

	@Column(name = "fid")
	private String fid;

	@Column(name = "name")
	private String name;

	@Column(name = "state")
	private Integer state;

	@Column(name = "time")
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

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}
}
