package com.smw.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商场信息表
 * @author suen
 * @date 2016年5月18日-下午4:52:48
 * @version 1.0
 */
@Entity
@Table(name="xf_mall")
public class XfMall implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -5732572009061773620L;

	/**
	 * 商场编码
	 */
	@Id
	@Column(length=15)
    private String xfMallid;

    /**
	 * 商场名称
	 */
	@Column(nullable=false,length=36)
    private String xfMallname;

    public String getXfMallid() {
        return xfMallid;
    }

    public void setXfMallid(String xfMallid) {
        this.xfMallid = xfMallid == null ? null : xfMallid.trim();
    }

    public String getXfMallname() {
        return xfMallname;
    }

    public void setXfMallname(String xfMallname) {
        this.xfMallname = xfMallname == null ? null : xfMallname.trim();
    }
}