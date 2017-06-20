package com.smw.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

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
 * 销售单付款明细表
 * @author suen
 * @date 2016年5月18日-下午5:22:25
 * @version 1.0
 */
@Entity
@Table(name="sales_tender")
public class SalesTender implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -6525415724438198059L;

	/**
	 * 主键自增
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    /**
	 * 销售单号(外键：sales_summary.txdocno)
	 */
	@ManyToOne(targetEntity=SalesSummary.class,fetch=FetchType.EAGER)
	@JoinColumn(nullable=false,name="txdocno")
    private SalesSummary txdocno;

    /**
	 * 行号
	 */
	@Column(nullable=false,length=36)
    private String lineno;

    /**
	 * 付款方式编码(外键：xf_tender.xf_tendercode)
	 */
	@ManyToOne(targetEntity=XfTender.class,fetch=FetchType.EAGER)
	@JoinColumn(nullable=false,name="tendercode")
    private XfTender tendercode;

    /**
	 * 付款金额(退货时取负值)
	 */
	@Column(nullable=false)
    private BigDecimal payamount;

    /**
	 * 本位币金额,即人民币金额，同xf_payamount(退货时取负值)
	 */
	@Column(nullable=false)
    private BigDecimal baseamount;

    /**
	 * 超额金额,保留Default: 0(退货时取负值,用于记录现金券等不能找补的付款方式,例如：应付100元，而现金券是120，那么超额金额就是120-100=20)
	 */
	@Column(nullable=false)
    private BigDecimal excessamount;

	/**
	 * 交易附加域,主要用于银行卡支付保存信息，格式为：“时间&凭证号&系统参考号”，
	 */
	@Column(nullable=true)
	private String transMemo;
	
	/**
	 * 支付的账户号,没有则为空
	 */
	@Column(nullable=true)
    private String accountNo;
	
	@Column(nullable=true,length=10)
    private String gwPaymentRate;
	
	
	

	public String getGwPaymentRate() {
		return gwPaymentRate;
	}

	public void setGwPaymentRate(String gwPaymentRate) {
		this.gwPaymentRate = gwPaymentRate;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPayamount() {
        return payamount;
    }

    public void setPayamount(BigDecimal payamount) {
        this.payamount = payamount;
    }

    public BigDecimal getBaseamount() {
        return baseamount;
    }

    public void setBaseamount(BigDecimal baseamount) {
        this.baseamount = baseamount;
    }

    public BigDecimal getExcessamount() {
        return excessamount;
    }

    public void setExcessamount(BigDecimal excessamount) {
        this.excessamount = excessamount;
    }

	public SalesSummary getTxdocno() {
		return txdocno;
	}

	public void setTxdocno(SalesSummary txdocno) {
		this.txdocno = txdocno;
	}

	public XfTender getTendercode() {
		return tendercode;
	}

	public void setTendercode(XfTender tendercode) {
		this.tendercode = tendercode;
	}

	public String getLineno() {
		return lineno;
	}

	public void setLineno(String lineno) {
		this.lineno = lineno;
	}

	public String getTransMemo() {
		return transMemo;
	}

	public void setTransMemo(String transMemo) {
		this.transMemo = transMemo;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}


}