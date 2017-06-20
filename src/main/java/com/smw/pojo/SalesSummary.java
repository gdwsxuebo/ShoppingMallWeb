package com.smw.pojo;

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
 * 销售汇总表
 * @author suen
 * @date 2016年5月18日-下午5:12:40
 * @version 1.0
 */
@Entity
@Table(name="sales_summary")
public class SalesSummary implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 3587240870412787430L;

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
	 * 商场编号(外键：xf_mall.mallid)
	 */
	@ManyToOne(targetEntity=XfMall.class,fetch=FetchType.EAGER)
	@JoinColumn(nullable=false,name="mallid")
    private XfMall mallid;

    /**
	 * 店铺号(外键：xf_store.xf_storecode)
	 */
	@ManyToOne(targetEntity=XfStore.class,fetch=FetchType.EAGER)
	@JoinColumn(nullable=false,name="storecode")
    private XfStore storecode;

    /**
	 * 收银机号(后台管理只需要在店铺信息中有一个字段记录有几台收银机（3）。前台配置平板的时候，在系统管理里手工配置是01、02、03号收银机。)
	 */
	@Column(nullable=false,length=36)
    private String tillid;

    /**
	 * 收银员编号(外键：xf_staff.xf_staffcode)
	 */
	@ManyToOne(targetEntity=XfStaff.class,fetch=FetchType.EAGER)
	@JoinColumn(nullable=false,name="staffcode")
    private XfStaff staffcode;

    /**
	 * 会员号
	 */
	@Column(nullable=true,length=36)
    private String vipcode;

    /**
	 * 销售员(外键：xf_staff.xf_staffcode,销售员和收银员有的情况下不一定是同一个人),但也是xf_staff表中的xf_staffcode
	 */
	@Column(nullable=true,length=36)
    private String salesman;

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
     * 超额金额
     */
	@Column
    private BigDecimal changemount;

    /**
	 * 销售单号(编码规则：店铺号-收银机号-YYYYMMDD-销售单流水号)
	 */
	@Column(nullable=true,length=50)
    private String originalTxdocno;

	/**
	 * 销售数据传输到ESPOS系统标记(1:已传; 0:未传)
	 */
	@Column(nullable=true)
    private Boolean transferEspos = false; 
	
	/**
	 * 销售数据传输到Mis系统标记(1:已传; 0:未传)
	 */
	@Column(nullable=true)
	private Boolean newTransferEspos = false;
    
    /**
     * 收银店铺号(外键：xf_store.xf_storecode) 缺省情况为本店铺号，但如果出现中央店铺代收，此字段则记录实际收款的中央店铺号
     */
	@ManyToOne(targetEntity=XfStore.class,fetch=FetchType.EAGER)
	@JoinColumn(nullable=false,name="cashStorecode")
    private XfStore cashStorecode;
    
	/**
     * 关联的促销劵
     */
	@Column(nullable=true,length=36)
    private String promotionStampId;
	
	/**
	 * 翼支付支付码
	 */
	@Column(nullable=true,length=36)
	private String barcode;
	
	/**
	 * 是否要上传 1代表要上传，其他代表不上传
	 */
	@Column(nullable=true,length=1)
	private String isUpload="1";
	
	
	/*表示是否退款0 未退款，1已退款*/
	@Column(nullable=true,length=1)
	private String state="0";
	
	@Column(nullable=true,length=1)
	private String isSalesReturn;
	
	//销售查询条件的参数begin
    /**
     * 支付金额
     */
	@Transient
    private BigDecimal payamount;
    /**
     * 支付方式 CH:现金  AL：支付宝  BK：银行卡  WX：微信
     */
	@Transient
    private String tendercode;
    
    /**
     * 支付方式名称
     */
	@Transient
    private String xfTenderdesc;
    //end

    /**
     * 销售单货品明细表
     */
	@Transient
    private List<SalesItem> sis;
    
    /**
     * 销售单付款明细表
     */
    @Transient
	private List<SalesTender> sts;
	
	/**
	 * 单笔销售获取的积分
	 */
	@Column
	private Integer bonusearn;
	
	/**
	 * 退货人
	 */
	@Column(nullable=true,length=111)
	private String refundStaff;
	
	
    public Integer getBonusearn() {
		return bonusearn;
	}

    
	public BigDecimal getChangemount() {
		return changemount;
	}


	public void setChangemount(BigDecimal changemount) {
		this.changemount = changemount;
	}


	public void setBonusearn(Integer bonusearn) {
		this.bonusearn = bonusearn;
	}

	public String getState() {
    	if(state==null) state="0";
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

    public String getTxdocno() {
        return txdocno;
    }

    public void setTxdocno(String txdocno) {
        this.txdocno = txdocno == null ? null : txdocno.trim();
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

   

    public String getTillid() {
        return tillid;
    }

    public void setTillid(String tillid) {
        this.tillid = tillid == null ? null : tillid.trim();
    }

    

    public String getVipcode() {
        return vipcode;
    }

    public void setVipcode(String vipcode) {
        this.vipcode = vipcode == null ? null : vipcode.trim();
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman == null ? null : salesman.trim();
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
        this.originalTxdocno = originalTxdocno == null ? null : originalTxdocno.trim();
    }

    public Boolean getTransferEspos() {
        return transferEspos;
    }

    public void setTransferEspos(Boolean transferEspos) {
        this.transferEspos = transferEspos;
    }


	public BigDecimal getPayamount() {
		return payamount;
	}

	public void setPayamount(BigDecimal payamount) {
		this.payamount = payamount;
	}

	public String getTendercode() {
		return tendercode;
	}

	public void setTendercode(String tendercode) {
		this.tendercode = tendercode;
	}

	public String getXfTenderdesc() {
		return xfTenderdesc;
	}

	public void setXfTenderdesc(String xfTenderdesc) {
		this.xfTenderdesc = xfTenderdesc;
	}

	public List<SalesItem> getSis() {
		return sis;
	}

	public void setSis(List<SalesItem> sis) {
		this.sis = sis;
	}

	public List<SalesTender> getSts() {
		return sts;
	}

	public void setSts(List<SalesTender> sts) {
		this.sts = sts;
	}

	public XfMall getMallid() {
		return mallid;
	}

	public void setMallid(XfMall mallid) {
		this.mallid = mallid;
	}

	public XfStore getStorecode() {
		return storecode;
	}

	public void setStorecode(XfStore storecode) {
		this.storecode = storecode;
	}

	public XfStaff getStaffcode() {
		return staffcode;
	}

	public void setStaffcode(XfStaff staffcode) {
		this.staffcode = staffcode;
	}

	public XfStore getCashStorecode() {
		return cashStorecode;
	}

	public void setCashStorecode(XfStore cashStorecode) {
		this.cashStorecode = cashStorecode;
	}

	public SalesSummary() {
		super();
	}

	public Boolean getNewTransferEspos() {
		return newTransferEspos;
	}

	public void setNewTransferEspos(Boolean newTransferEspos) {
		this.newTransferEspos = newTransferEspos;
	}

	public String getPromotionStampId() {
		return promotionStampId;
	}

	public void setPromotionStampId(String promotionStampId) {
		this.promotionStampId = promotionStampId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getIsUpload() {
		return isUpload;
	}

	public void setIsUpload(String isUpload) {
		this.isUpload = isUpload;
	}

	public String getIsSalesReturn() {
		return isSalesReturn;
	}

	public void setIsSalesReturn(String isSalesReturn) {
		this.isSalesReturn = isSalesReturn;
	}

	public String getRefundStaff() {
		return refundStaff;
	}

	public void setRefundStaff(String refundStaff) {
		this.refundStaff = refundStaff;
	}
	
}