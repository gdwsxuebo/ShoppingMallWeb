package com.smw.pojo.oldSalesSummary;

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
 * 旧销售单付款明细表
 * @author suen
 * @date 2016年5月18日-下午5:22:25
 * @version 1.0
 */
@Entity
@Table(name="old_sales_tender")
public class OldSalesTender implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1811064176970597593L;

	/**
	 * 主键自增
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    /**
	 * 销售单号(外键：sales_summary.txdocno)
	 */
	@ManyToOne(targetEntity=OldSalesSummary.class,fetch=FetchType.EAGER)
	@JoinColumn(nullable=false,name="txdocno")
    private OldSalesSummary txdocno;

    /**
	 * 行号
	 */
	@Column(nullable=false,length=36)
    private String lineno;

    /**
	 * 付款方式编码
	 */
	@Column(nullable=false,name="tendercode",length=36)
    private String tendercode;

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

	public OldSalesSummary getTxdocno() {
		return txdocno;
	}

	public void setTxdocno(OldSalesSummary txdocno) {
		this.txdocno = txdocno;
	}

	public String getLineno() {
		return lineno;
	}

	public void setLineno(String lineno) {
		this.lineno = lineno;
	}

	public String getTendercode() {
		return tendercode;
	}

	public void setTendercode(String tendercode) {
		this.tendercode = tendercode;
	}
}