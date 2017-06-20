package com.smw.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.smw.pojo.PromotionRule;

/**
 * 促销规则数据访问层接口
 * @author suen
 * @date 2016年6月17日-下午2:11:56
 * @version  jdk1.8
 */
public interface PromotionRuleDao {
	/**
	 * 普通
	 */
	Integer getOrCount(String key);
	List<PromotionRule> getOrList(Integer pageIndex, Integer pageSize, String key);
	/**
	 * 统计促销规则数量
	 * @param key 搜索条件
	 * @return
	 */
	Integer getPRCount(String key);

	/**
	 * 查询促销规则集合
	 * @param pageIndex 页码
	 * @param pageSize 每页显示的行数
	 * @param key 搜索条件
	 * @return
	 */
	List<PromotionRule> getPRList(Integer pageIndex, Integer pageSize, String key);

	/**
	 * 保存或者更新促销规则
	 * @param promotionRule 促销规则对象
	 */
	void saveOrUpdatePR(PromotionRule promotionRule);

	/**
	 * 根据id获取促销规则
	 * @param id id主键
	 * @return
	 */
	PromotionRule getPRByid(String id);

	/**
	 * 根据ID删除促销
	 * @param id 主键ID
	 */
	void deletePRById(String id);

	/**
	 * 根据筛选条件查询促销
	 * @param issueDate 发放有效期
	 * @param lowestConsumptionMoney 消费金额
	 * @param issueTime 现在时间段
	 * @return
	 */
	List<PromotionRule> getPRListByParams(Date issueDate, BigDecimal lowestConsumptionMoney,
			String issueTime);
	
	List<PromotionRule> getValidPromotionRule(String storeCode, String curDate, String curD,
			BigDecimal lowestConsumptionMoney, int isVipUse);
	
	/**
	 * 判断标题是否重复
	 */
	PromotionRule isRepeat(String title);
	
	/**
	 * 判断标题是否重复
	 */
	PromotionRule isOrRepeat(String title);
	
	
}
