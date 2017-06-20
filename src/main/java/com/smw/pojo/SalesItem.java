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
 * 销售单货品明细表
 * @author suen
 * @date 2016年5月18日-下午4:27:02
 * @version 1.0
 */
@Entity()
@Table(name="sales_item")
public class SalesItem implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 9009828577119278809L;

	/**
	 * 自增量id
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
	 * 商品编号(外键：xf_item.plu)
	 */
	@ManyToOne(targetEntity=XfItem.class,fetch=FetchType.EAGER)
	@JoinColumn(nullable=false,name="plu")
    private XfItem plu;

    /**
	 * 数量(退货时取负值)
	 */
	@Column(nullable=false)
    private BigDecimal qty;
	
	/**
	 * 重量(退货时取负值)
	 */
	@Column(nullable=false)
    private BigDecimal weight;

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
    
    public SalesSummary getTxdocno() {
		return txdocno;
	}

	public void setTxdocno(SalesSummary txdocno) {
		this.txdocno = txdocno;
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

	public XfItem getPlu() {
		return plu;
	}

	public void setPlu(XfItem plu) {
		this.plu = plu;
	}

	public String getLineno() {
		return lineno;
	}

	public void setLineno(String lineno) {
		this.lineno = lineno;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	
	
    
}