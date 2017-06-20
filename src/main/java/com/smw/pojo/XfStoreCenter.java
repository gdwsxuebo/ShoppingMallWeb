package com.smw.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 普通店铺对应中央店铺表
 * @author suen
 * @date 2016年5月18日-下午5:03:56
 * @version 1.0
 */
@Entity
@Table(name="xf_store_center")
public class XfStoreCenter implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 7421683728893841833L;

	/**
	 * 中央店铺编号
	 */
	@Id
	@ManyToOne(targetEntity=XfStore.class,fetch=FetchType.EAGER)
	@JoinColumn(nullable=false,name="xfCenterstorecode")
    private XfStore xfCenterstorecode;

    /**
     * 店铺编号
     */
	@Id
	@ManyToOne(targetEntity=XfStore.class,fetch=FetchType.EAGER)
	@JoinColumn(nullable=false,name="xfStorecode")
    private XfStore xfStorecode;

	public XfStore getXfCenterstorecode() {
		return xfCenterstorecode;
	}

	public void setXfCenterstorecode(XfStore xfCenterstorecode) {
		this.xfCenterstorecode = xfCenterstorecode;
	}

	public XfStore getXfStorecode() {
		return xfStorecode;
	}

	public void setXfStorecode(XfStore xfStorecode) {
		this.xfStorecode = xfStorecode;
	}
	
}