package com.smw.dao.impl;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.smw.common.util.DateUtil;
import com.smw.dao.PromotionRuleDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.dao.BaseDao_suen.Order;
import com.smw.pojo.PromotionRule;

/**
 * 促销规则数据访问层实现接口
 * @author suen
 * @date 2016年6月17日-下午2:12:39
 * @version  jdk1.8
 */
@Repository
public class PromotionRuleDaoImpl extends BaseDaoSupport<PromotionRule> implements PromotionRuleDao{
	
	/**
	 * 发放店铺
	 */
	@Resource 
	private PromotionRangeDaoImpl promotionRangeDaoImpl;
	
	/**
	 * 使用店铺
	 */
	@Resource
	private PromotionUseRangeDaoImpl promotionUseRangeDaoImpl;
	/**
	 * 普通
	 */
	@Override
	public Integer getOrCount(String key) {
		String count="0";
		if (key==null) {
			count=executeHQL("SELECT COUNT(1) FROM PromotionRule WHERE issueVipCondition=0").toString();
		}else{
			count=executeHQL("SELECT COUNT(1) FROM PromotionRule p WHERE issueVipCondition=0 AND p.title like ? ", "%"+key+"%").toString();
		}
		return count==null?0:Integer.parseInt(count);
	}
	public List<PromotionRule> getOrList(Integer pageIndex, Integer pageSize, String key) {
		List<PromotionRule> list;
		if (key==null) {
			list = getList(pageIndex, pageSize,"issueVipCondition=0");
		}else{
			list=getList(pageIndex, pageSize, " title like ? and issueVipCondition=0 ", "%"+key+"%");
		}
		if (list!=null) {
			for (PromotionRule promotionRule : list) {
				//促销发放店铺
				promotionRule.setIssueRanges(promotionRangeDaoImpl.getPRSByPRUId(promotionRule.getId()));
				//促销使用店铺
				promotionRule.setUseRanges(promotionUseRangeDaoImpl.getPURByPRUId(promotionRule.getId()));
			}
		}
		return list;
	}
	/**
	 * 统计促销规则数量
	 * @param key 搜索条件
	 * @return
	 */
	@Override
	public Integer getPRCount(String key) {
		String count="0";
		if (key==null) {
			count=executeHQL("SELECT COUNT(1) FROM PromotionRule WHERE issueVipCondition=1").toString();
		}else{
			count=executeHQL("SELECT COUNT(1) FROM PromotionRule p WHERE issueVipCondition=1 AND p.title like ? ", "%"+key+"%").toString();
		}
		return count==null?0:Integer.parseInt(count);
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
		List<PromotionRule> list;
		if (key==null) {
			list = getList(pageIndex, pageSize," issueVipCondition=1 ");
		}else{
			list=getList(pageIndex, pageSize, " title like ? AND issueVipCondition=1 ", "%"+key+"%");
		}
		if (list!=null) {
			for (PromotionRule promotionRule : list) {
				//促销发放店铺
				promotionRule.setIssueRanges(promotionRangeDaoImpl.getPRSByPRUId(promotionRule.getId()));
				//促销使用店铺
				promotionRule.setUseRanges(promotionUseRangeDaoImpl.getPURByPRUId(promotionRule.getId()));
			}
		}
		return list;
	}

	/**
	 * 保存或者更新促销规则
	 * @param promotionRule 促销规则对象
	 */
	@Override
	public void saveOrUpdatePR(PromotionRule promotionRule) {
		saveOrUpdate2(promotionRule);
	}

	/**
	 * 根据id获取促销规则
	 * @param id id主键
	 * @return
	 */
	@Override
	public PromotionRule getPRByid(String id) {
		PromotionRule promotionRule = getUniObj("FROM PromotionRule p WHERE p.id=?", new BigDecimal(id));
		if (promotionRule!=null) {
			//促销发放店铺
			promotionRule.setIssueRanges(promotionRangeDaoImpl.getPRSByPRUId(promotionRule.getId()));
			//促销使用店铺
			promotionRule.setUseRanges(promotionUseRangeDaoImpl.getPURByPRUId(promotionRule.getId()));
		}
		return promotionRule;
	}

	/**
	 * 根据ID删除促销
	 * @param id 主键ID
	 */
	@Override
	public void deletePRById(String id) {
		deleteHQL("UPDATE FROM PromotionRule p SET p.isShow=false WHERE p.id=?",new BigDecimal(id));
		//delete(new BigDecimal(id));
	}

	/**
	 * 根据筛选条件查询促销
	 * @param issueDate 发放有效期
	 * @param lowestConsumptionMoney 消费金额
	 * @param issueTime 现在时间段
	 * @return
	 */
	public List<PromotionRule> getPRListByParams(Date issueDate, BigDecimal lowestConsumptionMoney,
			String issueTime) {
		Time sqlTime = DateUtil.getSqlTime(issueTime, "HH:mm:ss");
		String formatDate = DateUtil.format(issueDate, "yyyyMMdd");
		//System.out.println(formatDate);
 		List<PromotionRule> list = getList(formatDate+">=beginIssueDate and "+formatDate+"<=endIssueDate and ?>=lowestConsumptionMoney and ?>=issueBeginDate and ?<=issueEndDate and isShow=true and type=1"
				, Order.DESC,"rank",lowestConsumptionMoney,sqlTime,sqlTime);
		if (list!=null) {
			for (PromotionRule promotionRule : list) {
				//促销发放店铺
				promotionRule.setIssueRanges(promotionRangeDaoImpl.getPRSByPRUId(promotionRule.getId()));
				//促销使用店铺
				promotionRule.setUseRanges(promotionUseRangeDaoImpl.getPURByPRUId(promotionRule.getId()));
			}
		}
		return list;
	}
	
	@Override
	public List<PromotionRule> getValidPromotionRule(String storeCode, String curDate, String curD,
			BigDecimal lowestConsumptionMoney, int isVipUse) {
		String sql=" SELECT B.* "
				+" FROM ( select A.* ,(SELECT COUNT(*) FROM promotion_stamp B WHERE A.ID=B.promotionRule) AS num from promotion_rule A  ) B "
				+" WHERE B.ID IN (select promotionRule from promotion_range where xfStorecode=?) AND "
				+" (? BETWEEN B.beginIssueDate  and B.endIssueDate) "
				+" AND (? BETWEEN B.issueBeginDate and B.issueEndDate) "
				+" AND ?>=B.lowestConsumptionMoney "
				+" AND B.issueVipCondition=? "
				+" AND B.number>b.num ";
		return queryBySqlAllToPojo_GWS( sql, storeCode,curDate,curD,lowestConsumptionMoney,isVipUse);
	}
	
	/**
	 * 判断标题是否重复-会员
	 */
	public PromotionRule isRepeat(String title) {
		String hql ="from PromotionRule where title = '" + title + "' and issueVipCondition = 1";
		return getUniObj(hql);
	}
	
	/**
	 * 判断标题是否重复-普通
	 */
	public PromotionRule isOrRepeat(String title) {
		String hql ="from PromotionRule where title = '" + title + "' and issueVipCondition = 0";
		return getUniObj(hql);
	}

}
