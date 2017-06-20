package com.smw.pojo.temporary;

import java.io.Serializable;
import java.math.BigDecimal;

import com.smw.pojo.PromotionRule;
import com.smw.pojo.XfStore;

/**
 * 临时促销劵统计
 * @author suen
 * @date 2016年6月23日-下午5:02:44
 * @version  jdk1.8
 */
public class PsStatisticsSubtotal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4497584001191153698L;
	
	/**
	 * 店铺
	 */
	private XfStore xfStore;
	
	/**
	 * 发放数量小计
	 */
	private Integer issueNumSubtotal=0;
	
	/**
	 * 发放金额小计
	 */
	private BigDecimal issueMoneySubtotal=new BigDecimal(0);
	
	/**
	 * 使用数量小计
	 */
	private Integer useNumSubtotal=0;
	
	/**
	 * 使用金额小计
	 */
	private BigDecimal useMoneySubtotal=new BigDecimal(0);
	
	/**
	 * 标题
	 */
	private String title;

	public Integer getIssueNumSubtotal() {
		return issueNumSubtotal;
	}

	public void setIssueNumSubtotal(Integer issueNumSubtotal) {
		this.issueNumSubtotal = issueNumSubtotal;
	}

	public BigDecimal getIssueMoneySubtotal() {
		return issueMoneySubtotal;
	}

	public void setIssueMoneySubtotal(BigDecimal issueMoneySubtotal) {
		this.issueMoneySubtotal = issueMoneySubtotal;
	}

	public Integer getUseNumSubtotal() {
		return useNumSubtotal;
	}

	public void setUseNumSubtotal(Integer useNumSubtotal) {
		this.useNumSubtotal = useNumSubtotal;
	}

	public XfStore getXfStore() {
		return xfStore;
	}

	public void setXfStore(XfStore xfStore) {
		this.xfStore = xfStore;
	}

	public BigDecimal getUseMoneySubtotal() {
		return useMoneySubtotal;
	}

	public void setUseMoneySubtotal(BigDecimal useMoneySubtotal) {
		this.useMoneySubtotal = useMoneySubtotal;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
