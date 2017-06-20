package com.smw.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smw.dao.PromotionRuleDao;
import com.smw.pojo.PromotionRange;
import com.smw.pojo.PromotionRule;
import com.smw.pojo.PromotionUseRange;
import com.smw.service.PromotionRangeService;
import com.smw.service.PromotionRuleService;
import com.smw.service.PromotionUseRangeService;

/**
 * 促销规则服务层实现接口
 * @author suen
 * @date 2016年6月17日-下午2:09:56
 * @version  jdk1.8
 */
@Service
public class PromotionRuleServiceImpl implements PromotionRuleService{
	
	@Resource
	private  PromotionRuleDao  promotionRuleDao;
	/**
	 * 普通
	 */
	public Integer getOrCount(String key){
		return promotionRuleDao.getOrCount(key);
	}
	public List<PromotionRule> getOrList(Integer pageIndex, Integer pageSize, String key) {
		return promotionRuleDao.getOrList(pageIndex,pageSize,key);
	}
	/**
	 * 统计促销规则数量
	 * @param key 搜索条件
	 * @return
	 */
	@Override
	public Integer getPRCount(String key) {
		return promotionRuleDao.getPRCount(key);
	}

	/**
	 * 查询促销规则集合
	 * @param pageIndex 页码
	 * @param pageSize 每页显示的行数
	 * @param key 搜索条件
	 * @return
	 */
	@Override
	public List<PromotionRule> getPRList(Integer pageIndex, Integer pageSize, String key) {
		return promotionRuleDao.getPRList(pageIndex,pageSize,key);
	}

	/**
	 * 保存或者更新促销规则
	 * @param promotionRule 促销规则对象
	 */
	@Override
	public void saveOrUpdatePR(PromotionRule promotionRule) {
		promotionRuleDao.saveOrUpdatePR(promotionRule);
	}

	/**
	 * 根据id获取促销规则
	 * @param id id主键
	 * @return
	 */
	@Override
	public PromotionRule getPRByid(String id) {
		return promotionRuleDao.getPRByid(id);
	}

	/**
	 * 更新促销信息
	 * @param promotionRangeService 促销范围
	 * @param promotionUseRangeService 促销使用范围
	 * @param promotionRule 促销对象
	 * @param promotionRanges 促销范围集合
	 * @param promotionUseRanges 促销使用范围集合
	 */
	@Override
	public void updatePR(PromotionRangeService promotionRangeService, PromotionUseRangeService promotionUseRangeService,PromotionRule promotionRule, List<PromotionRange> promotionRanges,
			List<PromotionUseRange> promotionUseRanges) {
		List<PromotionUseRange> useRanges = new ArrayList<>();
		for (PromotionUseRange promotionUseRange : promotionRule.getUseRanges()) {
			useRanges.add(promotionUseRange);
		}
		List<PromotionRange> issueRanges = new ArrayList<>();
		for (PromotionRange promotionRange : promotionRule.getIssueRanges()) {
			issueRanges.add(promotionRange);
		}
		//促销范围重新赋值
		promotionRule.setIssueRanges(promotionRanges);
		//促销使用范围重新赋值
		promotionRule.setUseRanges(promotionUseRanges);
		//更新
		saveOrUpdatePR(promotionRule);
		//清空促销使用范围
		for (PromotionUseRange promotionUseRange : useRanges) {
			promotionUseRangeService.deletePUR(promotionUseRange.getId().toString());
		}
		//清空促销范围
		for (PromotionRange promotionRange : issueRanges) {
			promotionRangeService.deletePRS(promotionRange.getId().toString());
		}
	}

	/**
	 * 根据ID删除促销
	 * @param id 主键ID
	 */
	@Override
	public void deletePRById(String id) {
		promotionRuleDao.deletePRById(id);
	}

	/**
	 * 根据筛选条件查询促销
	 * @param issueDate 发放有效期
	 * @param lowestConsumptionMoney 消费金额
	 * @param issueTime 现在时间段
	 * @return
	 */
	@Override
	public List<PromotionRule> getPRListByParams(Date issueDate, BigDecimal lowestConsumptionMoney,
			String issueTime) {
		return promotionRuleDao.getPRListByParams(issueDate,lowestConsumptionMoney,issueTime);
	}

	@Override
	public List<PromotionRule> getValidPromotionRule(String storeCode, String curDate, String curD,
			BigDecimal lowestConsumptionMoney, int isVipUse) {

		return promotionRuleDao.getValidPromotionRule( storeCode,  curDate,  curD,
				 lowestConsumptionMoney,  isVipUse);
	}
	/**
	 * 判断标题是否重复
	 */
	@Override
	public PromotionRule isRepeat(String title) {
		return promotionRuleDao.isRepeat(title);
	}
	
	/**
	 * 判断标题是否重复
	 */
	@Override
	public PromotionRule isOrRepeat(String title) {
		return promotionRuleDao.isOrRepeat(title);
	}
	
	
}
