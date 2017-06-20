package com.smw.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 付款方式表
 * @author suen
 * @date 2016年5月18日-下午5:08:53
 * @version 1.0
 */
@Entity
@Table(name="xf_tender")
public class XfTender implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -3139230488053033152L;

	/**
	 * 付款方式编码
	 */
	@Id
	@Column(length=15)
    private String xfTendercode;

    /**
	 * 付款方式描述
	 */
	@Column(nullable=false,length=50)
    private String xfTenderdesc;

    /**
	 * 退款控制开关(控制前端收银是否可以退款)
	 */
	@Column(nullable=false)
    private Boolean xfRefund;
	
	@Column(nullable=true,length=1)
	private String isInvalid;
	
	@Column(nullable=true,length=15)
	private String rate;
	
	
    public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getIsInvalid() {
		return isInvalid;
	}

	public void setIsInvalid(String isInvalid) {
		this.isInvalid = isInvalid;
	}

	public String getXfTendercode() {
        return xfTendercode;
    }

    public void setXfTendercode(String xfTendercode) {
        this.xfTendercode = xfTendercode == null ? null : xfTendercode.trim();
    }

    public String getXfTenderdesc() {
        return xfTenderdesc;
    }

    public void setXfTenderdesc(String xfTenderdesc) {
        this.xfTenderdesc = xfTenderdesc == null ? null : xfTenderdesc.trim();
    }

    public Boolean getXfRefund() {
        return xfRefund;
    }

    public void setXfRefund(Boolean xfRefund) {
        this.xfRefund = xfRefund;
    }
}