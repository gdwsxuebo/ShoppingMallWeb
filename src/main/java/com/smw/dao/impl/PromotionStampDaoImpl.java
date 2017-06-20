package com.smw.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.smw.common.util.DateUtil;
import com.smw.common.util.StringUtil;
import com.smw.dao.PromotionRuleDao;
import com.smw.dao.PromotionStampDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.pojo.PromotionRange;
import com.smw.pojo.PromotionRule;
import com.smw.pojo.PromotionStamp;
import com.smw.pojo.PromotionUseRange;
import com.smw.pojo.temporary.PsStatisticsSubtotal;
import com.smw.pojo.temporary.PsStatisticsTotal;
import com.smw.pojo.temporary.PsStatisticsTotalAll;
import com.smw.service.PromotionRuleService;

/**
 * 促销劵数据访问层实现接口
 * @author suen
 * @date 2016年6月22日-上午10:59:44
 * @version  jdk1.8
 */
@Repository
public class PromotionStampDaoImpl extends BaseDaoSupport<PromotionStamp> implements PromotionStampDao {

	/**
	 * 根据促销规则ID统计促销劵数量
	 * @param id 促销规则ID
	 * @return
	 */
	@Override
	public Integer getCountByPRId(BigDecimal id) {
		Object executeHQL = executeHQL("SELECT COUNT(1) FROM PromotionStamp p WHERE p.promotionRule.id=?", id);
		return executeHQL==null?0:Integer.parseInt(executeHQL.toString());
	}

	/**
	 * 保存或者更新促销劵
	 * @param promotionStamp 促销劵
	 * @return
	 */
	@Override
	public void saveOrUpdatePS(PromotionStamp promotionStamp) {
		saveOrUpdate(promotionStamp);
	}

	/**
	 * 根据促销劵ID查询促销劵信息
	 * @param id 促销劵ID
	 * @return
	 */
	@Override
	public PromotionStamp getPSById(String id) {
		PromotionStamp promotionStamp = get(new BigDecimal(id));
		PromotionRule promotionRule;
		if (promotionStamp!=null && (promotionRule = promotionStamp.getPromotionRule())!=null) {
			//促销发放店铺
			promotionRule.setIssueRanges(promotionRangeDaoImpl.getPRSByPRUId(promotionRule.getId()));
			//促销使用店铺
			promotionRule.setUseRanges(promotionUseRangeDaoImpl.getPURByPRUId(promotionRule.getId()));
		}
		return promotionStamp;
	}

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
	 * 劵规则
	 */
	@Resource
	private PromotionRuleDao promotionRuleDao;
	/**
	 * 根据条件参数查询促销劵统计信息
	 * @param beginDate 统计起始时间
	 * @param endDate 统计结束日期
	 * @param pss 促销优惠规则
	 * @param issueRanges 发放店铺
	 * @param useRanges 使用店铺
	 * @return
	 */
	@Override
	public List<PsStatisticsTotal> getListByParams(String beginDate, String endDate, String pss, String issueRanges,
			String useRanges) {

		String sqlDate="";
		//日期范围
		if (!StringUtil.isNUllStr(beginDate) && !StringUtil.isNUllStr(endDate)) {
			sqlDate=" AND PS.creationDate BETWEEN '"+beginDate+"' AND '"+endDate+"'";
		}

		//促销规则集合
		List<PromotionRule> prList;
		if (!StringUtil.isNUllStr(pss) && StringUtil.isNUllStr(issueRanges) && StringUtil.isNUllStr(useRanges)) {
			String[] split = pss.split(",");
			prList=new ArrayList<>();
			PromotionRule promotionRule;
			for (String id : split) {
				//根据ID查询促销规则
				promotionRule=promotionRuleDao.getPRByid(id);
				//添加到促销集合中
				prList.add(promotionRule);
			}
		}else if(!StringUtil.isNUllStr(issueRanges) || !StringUtil.isNUllStr(useRanges)){
			StringBuffer buffer=new StringBuffer();
			buffer.append("SELECT pr.id from promotion_rule pr,promotion_range pra,promotion_use_range pur ");
			buffer.append("where pr.id=pra.promotionRule and pr.id=pur.promotionRule ");
			if(!StringUtil.isNUllStr(pss)){
				buffer.append("and pr.id in ('"+pss+"') ");
			}
			if(!StringUtil.isNUllStr(issueRanges)){
				buffer.append("and pra.xfStorecode in (");
				String[] split = issueRanges.split(",");
				for (int i = 0,len=split.length; i < len; i++) {
					if (i!=len-1) {
						buffer.append("'"+split[i]+"',");
					}else{
						buffer.append("'"+split[i]+"') ");
					}
				}
			}
			if(!StringUtil.isNUllStr(useRanges)){
				buffer.append("and pur.xfStorecode in (");
				String[] split = useRanges.split(",");
				for (int i = 0,len=split.length; i < len; i++) {
					if (i!=len-1) {
						buffer.append("'"+split[i]+"',");
					}else{
						buffer.append("'"+split[i]+"') ");
					}
				}
			}
			buffer.append("and pr.isShow=true GROUP BY pr.id ");
			List<Object> executeSql = getTListSql(null, null, buffer.toString());
			prList=new ArrayList<>();
			PromotionRule promotionRule;
			for (Object object : executeSql) {
				//根据ID查询促销规则
				promotionRule=promotionRuleDao.getPRByid(object.toString());
				//添加到促销集合中
				prList.add(promotionRule);
			}
		}else{
			prList = promotionRuleDao.getPRList(1, 1, null);
		}
		List<PsStatisticsTotal> pts=new ArrayList<>();
		
		if (prList!=null && prList.size()>0) {
			PsStatisticsTotal psStatisticsTotal;
			PsStatisticsSubtotal psStatisticsSubtotal;
			for (PromotionRule promotionRule : prList) {
				psStatisticsTotal=new PsStatisticsTotal();
				//关联的促销规则
				psStatisticsTotal.setPromotionRule(promotionRule);
				//发放规则ID
				BigDecimal prId = promotionRule.getId();
				//发放数量合计
//				Object issueNumTotal=getUniObjHql("SELECT COUNT(1) FROM PromotionStamp PS WHERE PS.promotionRule.id=?"+sqlDate,prId);
				Object issueNumTotal=getUniObjHql("SELECT number FROM PromotionRule PS WHERE PS.id=?"+sqlDate,prId);
				if(issueNumTotal!=null){
					psStatisticsTotal.setIssueNumTotal(Integer.parseInt(issueNumTotal.toString()));
				}
				//使用数量合计
				Object useNumTotal=getUniObjHql("SELECT COUNT(1) FROM PromotionStamp PS WHERE PS.promotionRule.id=? AND PS.isUse=TRUE"+sqlDate,prId);
				if (useNumTotal!=null) {
					psStatisticsTotal.setUseNumTotal(Integer.parseInt(useNumTotal.toString()));
				}
				//发放金额合计
//				Object issueMoneyTotal=getUniObjHql("SELECT SUM(PS.promotionRule.offsetMoney) FROM PromotionStamp PS WHERE PS.promotionRule.id=?"+sqlDate, prId);
				Object issueMoneyTotal=getUniObjHql("SELECT (number * offsetMoney) FROM PromotionRule PS WHERE PS.id=?"+sqlDate, prId);
				if(issueMoneyTotal!=null){
					psStatisticsTotal.setIssueMoneyTotal(new BigDecimal(issueMoneyTotal.toString()));
				}
				//使用金额合计
				Object useMoneyTotal=getUniObjHql("SELECT SUM(PS.promotionRule.offsetMoney) FROM PromotionStamp PS WHERE PS.promotionRule.id=? AND PS.isUse=TRUE"+sqlDate, prId);
				if(useMoneyTotal!=null){
					psStatisticsTotal.setUseMoneyTotal(new BigDecimal(useMoneyTotal.toString()));
				}
				List<PsStatisticsSubtotal> subPromotionInfos=new ArrayList<>();
				//促销规则发行店铺
				List<PromotionRange>	fxStores=promotionRangeDaoImpl.getPRSByPRUId(prId);
				for(PromotionRange fxStore:fxStores){
				PsStatisticsSubtotal subTotal=new PsStatisticsSubtotal();
				subTotal.setXfStore(fxStore.getXfStore());
				subTotal.setIssueNumSubtotal(0);
				BigDecimal  offSetMoney=fxStore.getPromotionRule().getOffsetMoney();
				String sql="select count(*) as count from promotion_stamp where storecode=? and promotionRule=? ";
				Map<String, Object> proMotion=	queryBySqlToUniqueMap_GWS(sql,fxStore.getXfStore().getXfStorecode(),prId);
				BigInteger count= (BigInteger) proMotion.get("count");
				subTotal.setIssueNumSubtotal(count.intValue());
				subTotal.setIssueMoneySubtotal(offSetMoney.multiply(new BigDecimal(count)));
				subTotal.setUseMoneySubtotal(new BigDecimal(0));
				subTotal.setUseNumSubtotal(0);
				subPromotionInfos.add(subTotal);
				}			
				//判断规则适用店铺
				List<PromotionUseRange> syStores=promotionUseRangeDaoImpl.getPURByPRUId(prId);
				for(PromotionUseRange syStore:syStores){
					PsStatisticsSubtotal subTotal=new PsStatisticsSubtotal();
					subTotal.setXfStore(syStore.getXfStore());
					subTotal.setIssueNumSubtotal(0);
					BigDecimal  offSetMoney=syStore.getPromotionRule().getOffsetMoney();
					String sql="select count(*) as count from promotion_stamp where storecode=? and promotionRule=? and  isUse='1' ";
					Map<String, Object> proMotion=	queryBySqlToUniqueMap_GWS(sql,syStore.getXfStore().getXfStorecode(),prId);
					BigInteger count=(BigInteger) proMotion.get("count");
					subTotal.setIssueNumSubtotal(0);
					subTotal.setIssueMoneySubtotal(new BigDecimal(0));
					subTotal.setUseMoneySubtotal(offSetMoney.multiply(new BigDecimal(count)));
					subTotal.setUseNumSubtotal(count.intValue());
					subPromotionInfos.add(subTotal);
				}	
				psStatisticsTotal.setSubtotals(subPromotionInfos);	
				//添加到总计集合
				pts.add(psStatisticsTotal);
			}
		}
		return pts;
	
	}
	/**
	 * 会员促销券统计 
	 */
	@Override
	public List<PsStatisticsTotalAll> getList(Integer pageIndex, Integer pageSize, String key) {
		//促销规则集合
		List<PromotionRule> prList;
		if (!StringUtil.isNUllStr(key)) {
			//根据标题查询促销规则
			prList=promotionRuleDao.getPRList(pageIndex, pageSize, key);
		}else{
				prList = promotionRuleDao.getPRList(pageIndex,pageSize, null);
		}
		List<PsStatisticsTotalAll> pts=new ArrayList<>();
		if (prList!=null && prList.size()>0) {
			PsStatisticsTotalAll psStatisticsTotal;
			for (PromotionRule promotionRule : prList) {
				psStatisticsTotal=new PsStatisticsTotalAll();
				//关联的促销规则
				psStatisticsTotal.setPromotionRule(promotionRule);
				//发放规则ID
				BigDecimal prId = promotionRule.getId();
				//发放数量合计
//				Object issueNumTotal=getUniObjHql("SELECT COUNT(1) FROM PromotionStamp PS WHERE PS.promotionRule.id=?"+sqlDate,prId);
				Object issueNumTotal=getUniObjHql("SELECT number FROM PromotionRule PS WHERE PS.id=?",prId);
				if(issueNumTotal!=null){
					psStatisticsTotal.setIssueNumTotal(Integer.parseInt(issueNumTotal.toString()));
				}
				//使用数量合计
				Object useNumTotal=getUniObjHql("SELECT COUNT(1) FROM PromotionStamp PS WHERE PS.promotionRule.id=? AND PS.isUse=TRUE",prId);
				if (useNumTotal!=null) {
					psStatisticsTotal.setUseNumTotal(Integer.parseInt(useNumTotal.toString()));
				}
				//发放金额合计
//				Object issueMoneyTotal=getUniObjHql("SELECT SUM(PS.promotionRule.offsetMoney) FROM PromotionStamp PS WHERE PS.promotionRule.id=?"+sqlDate, prId);
				Object issueMoneyTotal=getUniObjHql("SELECT (number * offsetMoney) FROM PromotionRule PS WHERE PS.id=?", prId);
				if(issueMoneyTotal!=null){
					psStatisticsTotal.setIssueMoneyTotal(new BigDecimal(issueMoneyTotal.toString()));
				}
				//使用金额合计
				Object useMoneyTotal=getUniObjHql("SELECT SUM(PS.promotionRule.offsetMoney) FROM PromotionStamp PS WHERE PS.promotionRule.id=? AND PS.isUse=TRUE", prId);
				if(useMoneyTotal!=null){
					psStatisticsTotal.setUseMoneyTotal(new BigDecimal(useMoneyTotal.toString()));
				}
				//发放店铺的数量
				Long	rangeCount=(long) 0;
				rangeCount=promotionRangeDaoImpl.getcountById(prId);
				Long    useCount=(long) 0;
				useCount=promotionUseRangeDaoImpl.getcountById(prId);
				psStatisticsTotal.setStoreNumTotal(rangeCount+useCount);
				//添加到总计集合
				pts.add(psStatisticsTotal);
			}
		}
		return pts;
	}

	@Override
	public List<PsStatisticsSubtotal> getSubtotalList(Integer pageIndex, Integer pageSize, String key, BigDecimal id) {
		List<PsStatisticsSubtotal> subPromotionInfos=new ArrayList<>();
		String rid=id.toString();
		//促销规则发行店铺
		PromotionRule p=promotionRuleDao.getPRByid(rid);
		String title=p.getTitle();
		
		List<PromotionRange>	fxStores=promotionRangeDaoImpl.getPRSByPRUId(pageIndex,pageSize,key,id);
		for(PromotionRange fxStore:fxStores){
		PsStatisticsSubtotal subTotal=new PsStatisticsSubtotal();
		subTotal.setXfStore(fxStore.getXfStore());
		subTotal.setIssueNumSubtotal(0);
		BigDecimal  offSetMoney=fxStore.getPromotionRule().getOffsetMoney();
		String sql="select count(*) as count from promotion_stamp where storecode=? and promotionRule=? ";
		Map<String, Object> proMotion=	queryBySqlToUniqueMap_GWS(sql,fxStore.getXfStore().getXfStorecode(),id);
		BigInteger count= (BigInteger) proMotion.get("count");
		subTotal.setIssueNumSubtotal(count.intValue());
		subTotal.setIssueMoneySubtotal(offSetMoney.multiply(new BigDecimal(count)));
		subTotal.setUseMoneySubtotal(new BigDecimal(0));
		subTotal.setUseNumSubtotal(0);
		subTotal.setTitle(title);
		subPromotionInfos.add(subTotal);
		}			
		//判断规则适用店铺
		List<PromotionUseRange> syStores=promotionUseRangeDaoImpl.getPURByPRUId(pageIndex,pageSize,key,id);
		for(PromotionUseRange syStore:syStores){
			PsStatisticsSubtotal subTotal=new PsStatisticsSubtotal();
			subTotal.setXfStore(syStore.getXfStore());
			subTotal.setIssueNumSubtotal(0);
			BigDecimal  offSetMoney=syStore.getPromotionRule().getOffsetMoney();
			String sql="select count(*) as count from promotion_stamp where storecode=? and promotionRule=? and  isUse='1' ";
			Map<String, Object> proMotion=	queryBySqlToUniqueMap_GWS(sql,syStore.getXfStore().getXfStorecode(),id);
			BigInteger count=(BigInteger) proMotion.get("count");
			subTotal.setIssueNumSubtotal(0);
			subTotal.setIssueMoneySubtotal(new BigDecimal(0));
			subTotal.setUseMoneySubtotal(offSetMoney.multiply(new BigDecimal(count)));
			subTotal.setUseNumSubtotal(count.intValue());
			subTotal.setTitle(title);
			subPromotionInfos.add(subTotal);
		}	
		return subPromotionInfos;
	}

	@Override
	public Integer getSubtotalCountByPRId(String key, BigDecimal id) {
		// TODO Auto-generated method stub
		//促销规则发行店铺
		Long fxStoresCount=(long)0;
		fxStoresCount=promotionRangeDaoImpl.getcountById(id);
		//判断规则适用店铺
		Long syStoresCount=(long)0;
		syStoresCount=promotionUseRangeDaoImpl.getcountById(id);
		return (int) (syStoresCount+fxStoresCount);
	}
	
}
