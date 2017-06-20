package com.smw.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.smw.common.util.DateUtil;

/**
 * 促销规则
 * @author suen
 * @date 2016年6月17日-上午9:33:28
 * @version  jdk1.8
 */
@Entity
@Table(name="promotion_rule")
public class PromotionRule implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1296645341467796360L;

	/**
	 * ID
	 */
	@Id
	@Column(precision = 19, scale = 0)
	private BigDecimal id=DateUtil.getCurrRan();
	
	/**
	 * 标题
	 */
	@Column(nullable=false,length=50)
	private String title;
	
	/**
	 * 说明
	 */
	@Column(length=200)
	private String detailExplain;
	
	/**
	 * 数量
	 */
	@Column(nullable=false)
	private Integer number=0;
	
	/**
	 * 开始有效时间
	 */
	@Column(nullable=false)
	private Date beginIssueDate;
	
	/**
	 * 结束有效时间
	 */
	@Column(nullable=false)
	private Date endIssueDate;
	
	/**
	 * 发放会员条件 true 会员才能满足 false都满足
	 */
	@Column(nullable=false)
	private Boolean issueVipCondition=false;
	
	/**
	 *最低消费金额
	 */
	@Column(nullable=false)
	private BigDecimal lowestConsumptionMoney;
	
	/**
	 * 发放促销优惠券的消费起始时间
	 */
	@Column(nullable=false)
	private Time issueBeginDate;
	
	/**
	 * 发放促销优惠券的消费结束时间
	 */
	@Column(nullable=false)
	private Time issueEndDate;
	
	/**
	 * 发放范围 外键PromotionRange
	 */
	@OneToMany(mappedBy="promotionRule",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<PromotionRange> issueRanges=new ArrayList<>();
	
	/**
	 * 等级，数字越大等级越高
	 */
	@Column(nullable=false)
	private Integer rank=1;
	
	/**
	 * 使用有效天数
	 */
	@Column(nullable=true)
	private Integer useValidNum;
	
	/**
	 * 使用有效开始日期
	 */
	@Column(nullable=true)
	private Date useBeginDate;
	
	/**
	 * 使用有效结束日期
	 */
	@Column(nullable=true)
	private Date useEndDate;
	
	/**
	 * 使用范围  外键PromotionUseRange
	 */
	@OneToMany(mappedBy="promotionRule",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<PromotionUseRange> useRanges=new ArrayList<>();
	
	/**
	 * 使用开始时段
	 */
	@Column(nullable=false)
	private Time useBeginPeriod; 
	
	/**
	 * 使用结束时段
	 */
	@Column(nullable=false)
	private Time useEndPeriod;
	
	/**
	 * 优惠金额
	 */
	@Column(nullable=false)
	private BigDecimal offsetMoney=new BigDecimal(0);
	
	/**
	 * 是否显示
	 * @return
	 */
	@Column(nullable=false)
	private Boolean isShow=true;
	
	/**
	 * 促销类型   1操作有   2只能用劵   3停用
	 */
	@Column(nullable=false,length=1)
	private String type="1";

	/**
	 *  1.可修改  0.不可修改
	 */
	@Transient
	private String isEdit;
	
	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetailExplain() {
		return detailExplain;
	}

	public void setDetailExplain(String detailExplain) {
		this.detailExplain = detailExplain;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getBeginIssueDate() {
		return beginIssueDate;
	}

	public void setBeginIssueDate(Date beginIssueDate) {
		this.beginIssueDate = beginIssueDate;
	}

	public Date getEndIssueDate() {
		return endIssueDate;
	}

	public void setEndIssueDate(Date endIssueDate) {
		this.endIssueDate = endIssueDate;
	}

	public Boolean getIssueVipCondition() {
		return issueVipCondition;
	}

	public void setIssueVipCondition(Boolean issueVipCondition) {
		this.issueVipCondition = issueVipCondition;
	}

	public BigDecimal getLowestConsumptionMoney() {
		return lowestConsumptionMoney;
	}

	public void setLowestConsumptionMoney(BigDecimal lowestConsumptionMoney) {
		this.lowestConsumptionMoney = lowestConsumptionMoney;
	}

	public Time getIssueBeginDate() {
		return issueBeginDate;
	}

	public void setIssueBeginDate(Time issueBeginDate) {
		this.issueBeginDate = issueBeginDate;
	}

	public Time getIssueEndDate() {
		return issueEndDate;
	}

	public void setIssueEndDate(Time issueEndDate) {
		this.issueEndDate = issueEndDate;
	}

	public List<PromotionRange> getIssueRanges() {
		return issueRanges;
	}

	public void setIssueRanges(List<PromotionRange> issueRanges) {
		this.issueRanges = issueRanges;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getUseValidNum() {
		return useValidNum;
	}

	public void setUseValidNum(Integer useValidNum) {
		this.useValidNum = useValidNum;
	}

	public Date getUseBeginDate() {
		return useBeginDate;
	}

	public void setUseBeginDate(Date useBeginDate) {
		this.useBeginDate = useBeginDate;
	}

	public Date getUseEndDate() {
		return useEndDate;
	}

	public void setUseEndDate(Date useEndDate) {
		this.useEndDate = useEndDate;
	}

	public List<PromotionUseRange> getUseRanges() {
		return useRanges;
	}

	public void setUseRanges(List<PromotionUseRange> useRanges) {
		this.useRanges = useRanges;
	}

	public Time getUseBeginPeriod() {
		return useBeginPeriod;
	}

	public void setUseBeginPeriod(Time useBeginPeriod) {
		this.useBeginPeriod = useBeginPeriod;
	}

	public Time getUseEndPeriod() {
		return useEndPeriod;
	}

	public void setUseEndPeriod(Time useEndPeriod) {
		this.useEndPeriod = useEndPeriod;
	}

	public BigDecimal getOffsetMoney() {
		return offsetMoney;
	}

	public void setOffsetMoney(BigDecimal offsetMoney) {
		this.offsetMoney = offsetMoney;
	}

	public Boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}
}
