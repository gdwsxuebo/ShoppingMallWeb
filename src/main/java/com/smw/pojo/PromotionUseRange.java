package com.smw.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.smw.common.util.DateUtil;

/**
 * 促销使用范围
 * @author suen
 * @date 2016年6月17日-上午9:55:39
 * @version  jdk1.8
 */
@Entity
@Table(name="promotion_use_range")
public class PromotionUseRange implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -97413111794199192L;
	
	/**
	 * 主键ID
	 */
	@Id
	@Column(precision = 19, scale = 0)
	private BigDecimal id=DateUtil.getCurrRan();
	
	/**
	 * 关联的促销规则 外键PromotionRule
	 */
	@ManyToOne(targetEntity=PromotionRule.class)
	@JoinColumn(nullable=false,name="promotionRule")
	private PromotionRule promotionRule;
	
	/**
	 * 关联的店铺
	 */
	@OneToOne(targetEntity=XfStore.class,fetch=FetchType.EAGER)
	@JoinColumn(nullable=false,name="xfStorecode")
	private XfStore xfStore;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public PromotionRule getPromotionRule() {
		return promotionRule;
	}

	public void setPromotionRule(PromotionRule promotionRule) {
		this.promotionRule = promotionRule;
	}

	public XfStore getXfStore() {
		return xfStore;
	}

	public void setXfStore(XfStore xfStore) {
		this.xfStore = xfStore;
	}
}
