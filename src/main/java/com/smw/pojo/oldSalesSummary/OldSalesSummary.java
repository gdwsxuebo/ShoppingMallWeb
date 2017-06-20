package com.smw.pojo.oldSalesSummary;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 旧销售汇总表
 * @author suen
 * @date 2016年5月18日-下午5:12:40
 * @version 1.0
 */
@Entity
@Table(name="old_sales_summary")
public class OldSalesSummary implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 8871363576991000664L;

	/**
	 * 销售单号(编码规则：店铺号-收银机号-YYYYMMDD-销售单流水号)
	 */
	@Id
	@Column(nullable=false,length=50)
    private String txdocno;

    /**
	 * 交易日期
	 */
	@Column(nullable=false)
    private Date txdate;

    /**
	 * 交易时间
	 */
	@Column(nullable=false)
    private Time txtime;

    /**
	 * 商场编号
	 */
	@Column(length=36)
    private String mallid;

    /**
	 * 店铺号
	 */
	@Column(length=36)
    private String storecode;

    /**
	 * 收银机号(后台管理只需要在店铺信息中有一个字段记录有几台收银机（3）。前台配置平板的时候，在系统管理里手工配置是01、02、03号收银机。)
	 */
	@Column(nullable=false,length=36)
    private String tillid;

    /**
	 * 收银员编号
	 */
	@Column(length=36)
    private String staffcode;

    /**
	 * 会员号
	 */
	@Column(nullable=true,length=36)
    private String vipcode;

	/**
	 * 销售总数量(退货时取负值)
	 */
	@Column(nullable=false)
    private BigDecimal netqty;

    /**
	 * 销售净金额(退货时取负值)
	 */
	@Column(nullable=false)
    private BigDecimal netamount;

    /**
	 * 原销售单号(编码规则：店铺号-收银机号-YYYYMMDD-销售单流水号)
	 */
	@Column(nullable=true,length=36)
    private String originalTxdocno;
    
    /**
     * 收银员编号
     */
	@Column(nullable=false,name="cashierId")
    private String cashierId;

    /**
     * 销售单货品明细表
     */
	@Transient
    private List<OldSalesItem> sis;
    
    /**
     * 销售单付款明细表
     */
    @Transient
	private List<OldSalesTender> sts;

	public String getTxdocno() {
		return txdocno;
	}

	public void setTxdocno(String txdocno) {
		this.txdocno = txdocno;
	}

	public Date getTxdate() {
		return txdate;
	}

	public void setTxdate(Date txdate) {
		this.txdate = txdate;
	}

	public Time getTxtime() {
		return txtime;
	}

	public void setTxtime(Time txtime) {
		this.txtime = txtime;
	}

	public String getMallid() {
		return mallid;
	}

	public void setMallid(String mallid) {
		this.mallid = mallid;
	}

	public String getStorecode() {
		return storecode;
	}

	public void setStorecode(String storecode) {
		this.storecode = storecode;
	}

	public String getTillid() {
		return tillid;
	}

	public void setTillid(String tillid) {
		this.tillid = tillid;
	}

	public String getStaffcode() {
		return staffcode;
	}

	public void setStaffcode(String staffcode) {
		this.staffcode = staffcode;
	}

	public String getVipcode() {
		return vipcode;
	}

	public void setVipcode(String vipcode) {
		this.vipcode = vipcode;
	}

	public BigDecimal getNetqty() {
		return netqty;
	}

	public void setNetqty(BigDecimal netqty) {
		this.netqty = netqty;
	}

	public BigDecimal getNetamount() {
		return netamount;
	}

	public void setNetamount(BigDecimal netamount) {
		this.netamount = netamount;
	}

	public String getOriginalTxdocno() {
		return originalTxdocno;
	}

	public void setOriginalTxdocno(String originalTxdocno) {
		this.originalTxdocno = originalTxdocno;
	}

	public String getCashierId() {
		return cashierId;
	}

	public void setCashierId(String cashierId) {
		this.cashierId = cashierId;
	}

	public List<OldSalesItem> getSis() {
		return sis;
	}

	public void setSis(List<OldSalesItem> sis) {
		this.sis = sis;
	}

	public List<OldSalesTender> getSts() {
		return sts;
	}

	public void setSts(List<OldSalesTender> sts) {
		this.sts = sts;
	}
    
}