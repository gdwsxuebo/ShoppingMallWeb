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
 * 旧销售单货品明细表
 * @author suen
 * @date 2016年5月18日-下午4:27:02
 * @version 1.0
 */
@Entity()
@Table(name="old_sales_item")
public class OldSalesItem implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 2129872867129653970L;

	/**
	 * 自增量id
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
	 * 商品编号(外键：xf_item.plu)
	 */
	@Column(nullable=false,name="pluId",length=36)
    private String plu;

    /**
	 * 数量(退货时取负值)
	 */
	@Column(nullable=false)
    private BigDecimal qty;

    /**
	 * 折扣金额(退货时取负值)
	 */
	@Column(nullable=false)
    private BigDecimal discountamount;

    /**
	 * 净金额(退货时取负值)
	 */
	@Column(nullable=false)
    private BigDecimal netamount;

    /**
	 * 获得积分(退货时取负值)
	 */
	@Column(nullable=false)
    private BigDecimal bonusearn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getPlu() {
		return plu;
	}

	public void setPlu(String plu) {
		this.plu = plu;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getDiscountamount() {
		return discountamount;
	}

	public void setDiscountamount(BigDecimal discountamount) {
		this.discountamount = discountamount;
	}

	public BigDecimal getNetamount() {
		return netamount;
	}

	public void setNetamount(BigDecimal netamount) {
		this.netamount = netamount;
	}

	public BigDecimal getBonusearn() {
		return bonusearn;
	}

	public void setBonusearn(BigDecimal bonusearn) {
		this.bonusearn = bonusearn;
	}
	
}