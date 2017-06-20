package com.smw.pojo.temporary;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.smw.pojo.PromotionRule;

/**
 * 临时促销劵统计
 * @author suen
 * @date 2016年6月23日-下午5:02:44
 * @version  jdk1.8
 */
public class PsStatisticsTotal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 149754654769501026L;

	/**
	 * 促销规则
	 */
	private PromotionRule promotionRule;
	
	/**
	 * 发放数量合计
	 */
	private Integer issueNumTotal=0;
	
	/**
	 * 发放金额合计
	 */
	private BigDecimal issueMoneyTotal=new BigDecimal(0);
	
	/**
	 * 使用数量合计
	 */
	private Integer useNumTotal=0;
	
	/**
	 * 使用金额
	 */
	private BigDecimal useMoneyTotal=new BigDecimal(0);
	
	/**
	 * 小计集合
	 */
	private List<PsStatisticsSubtotal> subtotals=new ArrayList<>();

	public Integer getIssueNumTotal() {
		return issueNumTotal;
	}

	public void setIssueNumTotal(Integer issueNumTotal) {
		this.issueNumTotal = issueNumTotal;
	}

	public BigDecimal getIssueMoneyTotal() {
		return issueMoneyTotal;
	}

	public void setIssueMoneyTotal(BigDecimal issueMoneyTotal) {
		this.issueMoneyTotal = issueMoneyTotal;
	}

	public Integer getUseNumTotal() {
		return useNumTotal;
	}

	public void setUseNumTotal(Integer useNumTotal) {
		this.useNumTotal = useNumTotal;
	}

	public List<PsStatisticsSubtotal> getSubtotals() {
		return subtotals;
	}

	public void setSubtotals(List<PsStatisticsSubtotal> subtotals) {
		this.subtotals = subtotals;
	}
	public PromotionRule getPromotionRule() {
		return promotionRule;
	}

	public void setPromotionRule(PromotionRule promotionRule) {
		this.promotionRule = promotionRule;
	}

	public BigDecimal getUseMoneyTotal() {
		return useMoneyTotal;
	}

	public void setUseMoneyTotal(BigDecimal useMoneyTotal) {
		this.useMoneyTotal = useMoneyTotal;
	}
}
