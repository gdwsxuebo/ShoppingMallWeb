package com.smw.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.smw.common.util.DateUtil;

/**
 * 商品信息表
 * @author suen
 * @date 2016年5月18日-下午4:44:24
 * @version 1.0
 */
@Entity
@Table(name="xf_item")
public class XfItem implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -442833694408381738L;

	/**
	 * 货号(编码规则：品牌编码+3位流水号)
	 */
	@Id
	@Column(length=36)
    private String xfPlu;

    /**
	 * 店铺编码(外键：xf_store.xf_storecode)
	 */
	@ManyToOne(targetEntity=XfStore.class,fetch=FetchType.EAGER)
	@JoinColumn(nullable=false,name="xfStorecode")
    private XfStore xfStorecode;

    /**
	 * 货品描述
	 */
	@Column(nullable=false,length=36)
    private String xfDesci;

    /**
	 * 货品详细描述
	 */
	@Column(nullable=true,length=60)
    private String xfLongdesc;

    /**
	 * 库存单位
	 */
	@Column(nullable=false,length=36)
    private String xfStkunit;

    /**
	 * 销售单位
	 */
	@Column(nullable=false,length=36)
    private String xfSalesunit;

    /**
	 * 实际销售1件对应的库存减少数 举例：数值设置为3，则销售1件，库存减少3件
	 */
	@Column(nullable=false)
    private BigDecimal xfExstk2sales;

    /**
	 * 最初零售价
	 */
	@Column(nullable=false)
    private BigDecimal xfOrguprice;

    /**
	 * 最初批发价
	 */
	@Column(nullable=false)
    private BigDecimal xfOrgwprice;

    /**
	 * 当前零售价
	 */
	@Column(nullable=false)
    private BigDecimal xfSeluprice;

    /**
	 * 当前批发价
	 */
	@Column(nullable=false)
    private BigDecimal xfSelwprice;
	
	/**
	 * 商品组织架构
	 */
	@Column(nullable=true,length=30)
	private String itemOrgId;
	
	@Column(nullable=true,length=1)
	private String	isInvalid;
	
	public String getIsInvalid() {
		return isInvalid;
	}

	public void setIsInvalid(String isInvalid) {
		this.isInvalid = isInvalid;
	}

	/**
	 * 更新的时间
	 */
	private long update_date=System.currentTimeMillis();

    public String getXfPlu() {
        return xfPlu;
    }

    public void setXfPlu(String xfPlu) {
        this.xfPlu = xfPlu == null ? null : xfPlu.trim();
    }

    public XfStore getXfStorecode() {
		return xfStorecode;
	}

	public void setXfStorecode(XfStore xfStorecode) {
		this.xfStorecode = xfStorecode;
	}

	public String getXfDesci() {
        return xfDesci;
    }

    public void setXfDesci(String xfDesci) {
        this.xfDesci = xfDesci == null ? null : xfDesci.trim();
    }

    public String getXfLongdesc() {
        return xfLongdesc;
    }

    public void setXfLongdesc(String xfLongdesc) {
        this.xfLongdesc = xfLongdesc == null ? null : xfLongdesc.trim();
    }

    public String getXfStkunit() {
        return xfStkunit;
    }

    public void setXfStkunit(String xfStkunit) {
        this.xfStkunit = xfStkunit == null ? null : xfStkunit.trim();
    }

    public String getXfSalesunit() {
        return xfSalesunit;
    }

    public void setXfSalesunit(String xfSalesunit) {
        this.xfSalesunit = xfSalesunit == null ? null : xfSalesunit.trim();
    }

    public BigDecimal getXfExstk2sales() {
        return xfExstk2sales;
    }

    public void setXfExstk2sales(BigDecimal xfExstk2sales) {
        this.xfExstk2sales = xfExstk2sales;
    }

    public BigDecimal getXfOrguprice() {
        return xfOrguprice;
    }

    public void setXfOrguprice(BigDecimal xfOrguprice) {
        this.xfOrguprice = xfOrguprice;
    }

    public BigDecimal getXfOrgwprice() {
        return xfOrgwprice;
    }

    public void setXfOrgwprice(BigDecimal xfOrgwprice) {
        this.xfOrgwprice = xfOrgwprice;
    }

    public BigDecimal getXfSeluprice() {
        return xfSeluprice;
    }

    public void setXfSeluprice(BigDecimal xfSeluprice) {
        this.xfSeluprice = xfSeluprice;
    }

    public BigDecimal getXfSelwprice() {
        return xfSelwprice;
    }

    public void setXfSelwprice(BigDecimal xfSelwprice) {
        this.xfSelwprice = xfSelwprice;
    }

	public long getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(long update_date) {
		this.update_date = update_date;
	}

	public String getItemOrgId() {
		return itemOrgId;
	}

	public void setItemOrgId(String itemOrgId) {
		this.itemOrgId = itemOrgId;
	}
    
}