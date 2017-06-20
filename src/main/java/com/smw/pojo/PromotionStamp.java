package com.smw.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.smw.common.util.DateUtil;

/**
 * 促销劵
 * @author suen
 * @date 2016年6月17日-上午10:28:39
 * @version  jdk1.8
 */
@Entity
@Table(name="promotion_stamp")
public class PromotionStamp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6109235560033227038L;
	
	/**
	 * 主键ID
	 */
	@Id
	@Column(precision = 19, scale = 0)
	private BigDecimal id=DateUtil.getCurrRan();
	
	/**
	 * 关联的销售单  外键SalesSummary
	 */
	@OneToOne(targetEntity=SalesSummary.class,fetch=FetchType.EAGER)
	@JoinColumn(nullable=false,name="salesSummary")
	private SalesSummary salesSummary;
	
	/**
	 * 关联的促销规则 外键promotionRule
	 */
	@OneToOne(targetEntity=PromotionRule.class,fetch=FetchType.EAGER)
	@JoinColumn(nullable=false,name="promotionRule")
	private PromotionRule promotionRule;
	
	/**
	 * 是否已使用
	 */
	@Column(nullable=false)
	private Boolean isUse=false;
	
	/**
	 * 创建日期（生成劵日期）
	 */
	@Column(nullable=false)
	private Date creationDate=new Date();
	
	public String getStorecode() {
		return storecode;
	}

	public void setStorecode(String storecode) {
		this.storecode = storecode;
	}

	@Column(nullable=true,length=20)
	private String	storecode;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public SalesSummary getSalesSummary() {
		return salesSummary;
	}

	public void setSalesSummary(SalesSummary salesSummary) {
		this.salesSummary = salesSummary;
	}

	public PromotionRule getPromotionRule() {
		return promotionRule;
	}

	public void setPromotionRule(PromotionRule promotionRule) {
		this.promotionRule = promotionRule;
	}

	public Boolean getIsUse() {
		return isUse;
	}

	public void setIsUse(Boolean isUse) {
		this.isUse = isUse;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
}
