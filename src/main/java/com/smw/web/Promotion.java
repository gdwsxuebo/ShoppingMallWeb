package com.smw.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gws.service.impl.GdwsGdpsService;
import com.smw.common.util.DateUtil;
import com.smw.common.util.SetEnum;
import com.smw.common.util.StringUtil;
import com.smw.common.util.paging.Paging;
import com.smw.pojo.PromotionRange;
import com.smw.pojo.PromotionRule;
import com.smw.pojo.PromotionUseRange;
import com.smw.pojo.Sets;
import com.smw.pojo.XfStore;
import com.smw.pojo.temporary.PsStatisticsSubtotal;
import com.smw.pojo.temporary.PsStatisticsTotalAll;
import com.smw.service.BuildingInfoService;
import com.smw.service.FormatInfoService;
import com.smw.service.PromotionRangeService;
import com.smw.service.PromotionRuleService;
import com.smw.service.PromotionStampService;
import com.smw.service.PromotionUseRangeService;
import com.smw.service.SetService;
import com.smw.service.XfStoreService;

/**
 * 促销
 * @author suen
 * @date 2016年6月17日-上午11:33:27
 * @version  jdk1.8
 */
@Controller
@RequestMapping("web/promotion")
public class Promotion {
	Logger logger = LoggerFactory.getLogger(Promotion.class);
	
	/**
	 * 促销规则
	 */
	@Resource
	private PromotionRuleService promotionRuleService;
	
	/**
	 * 店铺
	 */
	@Resource
	private XfStoreService xfStoreService;
	
	/**
	 * 促销范围
	 */
	@Resource
	private PromotionRangeService promotionRangeService;
	
	/**
	 * 促销使用范围
	 */
	@Resource
	private PromotionUseRangeService promotionUseRangeService;
	
	/**
	 * 促销优惠劵
	 */
	@Resource
	private PromotionStampService promotionStampService;
	
	/**
	 * 设置
	 */
	@Resource
	private SetService setService;
	
	/**
	 * 楼宇
	 */
	@Resource
	private BuildingInfoService buildingInfoService;
	
	/**
	 * 业态
	 */
	@Resource
	private FormatInfoService formatinfoService;
	
	
	/**
	 * 获取促销信息
	 */
	@RequestMapping("/getPromotion")
	public Object getPromotion(HttpServletRequest request,Integer pageIndex,Integer pageSize,String key){
		try {
			if (pageIndex == null) {
				pageIndex = Integer.parseInt(SetEnum.pageIndex.getValue());
			}
			if (pageSize == null) {
				pageSize = Integer.parseInt(SetEnum.pageSize.getValue());
			}
			if (key != null && !"".equals(key)) {
				key= new String(key.getBytes("iso-8859-1"),"utf-8");
				key = key.trim();
				request.setAttribute("key", key);
			}
			else{
	        	key=null;
	        }
			//数量
			Integer count=promotionRuleService.getPRCount(key);
			//促销规则集合
			List<PromotionRule> promotionRules=promotionRuleService.getPRList(pageIndex,pageSize,key);
			//得到促销规则的id 从表中得到是否有关联数据  有着setisshow is 0；
			for (PromotionRule pr : promotionRules) {
				Integer countStamp = promotionStampService.getCountByPRId(pr.getId());
				if(countStamp > 0){
					pr.setIsEdit("1");
				}else{
					pr.setIsEdit("0");
				}
			}
			
			//分页对象
			Paging<PromotionRule> paging=new Paging<>(pageIndex, pageSize, count==null?0:count, promotionRules);
			request.getSession().setAttribute("paging", paging);
			request.getSession().setAttribute("select", "setPromotion");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取促销 "+e.getMessage());
		}
		return "/promotion";
	}
	
	/**
	 * 获取店铺
	 * @return
	 */
	@RequestMapping(value="getStores")
	@ResponseBody
	public Object getStores(){
		return xfStoreService.getXfStoreList(null, null, null, null);
	}
	
	/**
	 * 添加促销规则
	 * @param request
	 * @param title 标题
	 * @param number 数量
	 * @param detailExplain 说明
	 * @param beginIssueDate 开始有效时间
	 * @param endIssueDate 结束有效时间
	 * @param issueVipCondition 发放会员条件 true 会员才能满足 false都满足
	 * @param lowestConsumptionMoney 最低消费金额
	 * @param rank 等级，数字越大等级越高
	 * @param issueRanges 发放范围
	 * @param issueBeginDate 发放开始时间
	 * @param issueEndDate 发放结束时间
	 * @param useValidNum 使用有效天数
	 * @param useBeginDate 使用有效开始日期
	 * @param useEndDate 使用有效结束日期
	 * @param useRanges 使用范围
	 * @param useBeginPeriod 使用开始时段
	 * @param useEndPeriod 使用结束时段
	 * @param offsetMoney 优惠金额
	 * @return
	 */
	@RequestMapping(value="addPR")
	public Object addPR(HttpServletRequest request,String title,Integer number,String detailExplain
			,String beginIssueDate,String endIssueDate,Double lowestConsumptionMoney
			,Integer rank,String issueRanges,String issueBeginDate,String issueEndDate,Integer useValidNum,String useBeginDate
			,String useEndDate,String useRanges,String useBeginPeriod,String useEndPeriod,String offsetMoney,String building,String format){
		rank=1;
		try {
			if (StringUtil.isNUllStr(title) || number==null || StringUtil.isNUllStr(detailExplain)
					|| StringUtil.isNUllStr(beginIssueDate) || StringUtil.isNUllStr(endIssueDate) 
					|| lowestConsumptionMoney==null || StringUtil.isNUllStr(offsetMoney)
					|| rank==null || StringUtil.isNUllStr(issueRanges) || StringUtil.isNUllStr(useRanges)
					|| StringUtil.isNUllStr(issueBeginDate) || StringUtil.isNUllStr(issueEndDate)
					|| StringUtil.isNUllStr(useBeginPeriod) || StringUtil.isNUllStr(useEndPeriod)) {
				return getPromotion(request, 1, 10, null); 
			}
			//促销规则对象
			PromotionRule promotionRule=new PromotionRule();
			//标题
			promotionRule.setTitle(title);
			
			//说明
			promotionRule.setDetailExplain(detailExplain);
			//数量
			promotionRule.setNumber(number);
			promotionRule.setBeginIssueDate(DateUtil.getDate(beginIssueDate));			//开始有效时间
			promotionRule.setEndIssueDate(DateUtil.getDate(endIssueDate));			//结束有效时间
			promotionRule.setIssueVipCondition(true);			//发放会员条件 true 会员才能满足 false都满足
			promotionRule.setLowestConsumptionMoney(new BigDecimal(lowestConsumptionMoney));			//最低消费金额
			promotionRule.setIssueBeginDate(DateUtil.getSqlTime(issueBeginDate, "HH:mm:ss"));			//发放开始时间段
			promotionRule.setIssueEndDate(DateUtil.getSqlTime(issueEndDate, "HH:mm:ss"));			//发放结束时间
			promotionRule.setType("1");
			//发放集合
			List<PromotionRange> promotionRanges=new ArrayList<>();
			XfStore xfStore;
			PromotionRange promotionRange;
			String[] split = issueRanges.split(",");
			for (String storeCode : split) {
				xfStore = xfStoreService.getXfStoreByStoreCode(storeCode);
				if (xfStore!=null) {
					promotionRange=new PromotionRange();
					//关联促销规则
					promotionRange.setPromotionRule(promotionRule);
					//店铺
					promotionRange.setXfStore(xfStore);
					//添加发放的店铺
					promotionRanges.add(promotionRange);
					System.out.println("ID == " + promotionRange.getId());
				}
			}
			//发放范围
			promotionRule.setIssueRanges(promotionRanges);
			//等级
			promotionRule.setRank(rank);
			
			if (useValidNum!=null) {
				//使用有效天数
				promotionRule.setUseValidNum(useValidNum);
			}else{
				if (StringUtil.isNUllStr(useBeginDate) || StringUtil.isNUllStr(useEndDate)) {
					return getPromotion(request, 1, 10, null);
				}
				//使用有效开始日期
				promotionRule.setUseBeginDate(DateUtil.getDate(useBeginDate));
				//使用有效结束日期
				promotionRule.setUseEndDate(DateUtil.getDate(useEndDate));
			}
			//使用集合
			List<PromotionUseRange> promotionUseRanges=new ArrayList<>();
			PromotionUseRange promotionUseRange;
			split = useRanges.split(",");
			for (String storeCode : split) {
				xfStore = xfStoreService.getXfStoreByStoreCode(storeCode);
				if (xfStore!=null) {
					promotionUseRange = new PromotionUseRange();
					//关联促销规则
					promotionUseRange.setPromotionRule(promotionRule);
					//店铺
					promotionUseRange.setXfStore(xfStore);
					//添加使用的店铺
					promotionUseRanges.add(promotionUseRange);
				}
			}
			//使用范围
			promotionRule.setUseRanges(promotionUseRanges);
			//使用开始时段
			promotionRule.setUseBeginPeriod(DateUtil.getSqlTime(useBeginPeriod, "HH:mm:ss"));
			//使用结束时段
			promotionRule.setUseEndPeriod(DateUtil.getSqlTime(useEndPeriod, "HH:mm:ss"));
			//优惠金额
			promotionRule.setOffsetMoney(new BigDecimal(offsetMoney));
			//保存或者更新
			promotionRuleService.saveOrUpdatePR(promotionRule);
			return getPromotion(request, 1, 10, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加促销规则 "+e.getMessage());
		}
		return getPromotion(request, 1, 10, null);
	}
	
	/**
	 * 获取促销规则信息
	 * @return
	 */
	@RequestMapping(value="getPR")
	@ResponseBody
	public Object getPR(String id){
		try {
			if (StringUtil.isNUllStr(id)) {
				return null;
			}
			Map<String, Object> map=new HashMap<>();
			PromotionRule prByid = promotionRuleService.getPRByid(id);
			if (prByid!=null) {
				//id
				map.put("id", prByid.getId());
				//标题
				map.put("title", prByid.getTitle());
				//说明
				map.put("detailExplain", prByid.getDetailExplain());
				//数量
				map.put("number", prByid.getNumber());
				//开始有效时间
				map.put("beginIssueDate", DateUtil.format(prByid.getBeginIssueDate(), "yyyy-MM-dd"));
				//结束有效时间
				map.put("endIssueDate", DateUtil.format(prByid.getEndIssueDate(), "yyyy-MM-dd"));
				//是否只有vip可用
				map.put("issueVipCondition", prByid.getIssueVipCondition());
				//最低消费金额
				map.put("lowestConsumptionMoney", prByid.getLowestConsumptionMoney());
				//发放开始时间段
				map.put("issueBeginDate", prByid.getIssueBeginDate());
				//发放结束时间段
				map.put("issueEndDate", prByid.getIssueEndDate());
				//中间集合
				Map<String, Object> emp;
				List<Map<String, Object>> empList=new ArrayList<>();
				//发放范围
				List<PromotionRange> issueRanges = prByid.getIssueRanges();
				for (PromotionRange promotionRange : issueRanges) {
					emp=new HashMap<>();
					//主键
					emp.put("id", promotionRange.getId());
					//关联的店铺
					emp.put("xfStorecode", promotionRange.getXfStore().getXfStorecode());
					emp.put("XfName", promotionRange.getXfStore().getXfName());
					empList.add(emp);
				}
				map.put("issueRanges",empList);
				//等级，数字越大等级越高
				map.put("rank", prByid.getRank());
				//使用有效天数
				map.put("useValidNum", prByid.getUseValidNum());
				//使用有效开始日期
				map.put("useBeginDate", DateUtil.format(prByid.getUseBeginDate(), "yyyy-MM-dd"));
				//使用有效结束日期
				map.put("useEndDate", DateUtil.format(prByid.getUseEndDate(), "yyyy-MM-dd"));
				//使用范围
				List<PromotionUseRange> useRanges = prByid.getUseRanges();
				//使用范围集合
				empList=new ArrayList<>();
				for (PromotionUseRange promotionUseRange : useRanges) {
					emp=new HashMap<>();
					//主键
					emp.put("id", promotionUseRange.getId());
					//店铺编号
					emp.put("xfStorecode", promotionUseRange.getXfStore().getXfStorecode());
					emp.put("XfName", promotionUseRange.getXfStore().getXfName());
					empList.add(emp);
				}
				map.put("useRanges",empList);
				//使用开始时段
				map.put("useBeginPeriod", prByid.getUseBeginPeriod());
				//使用结束时段
				map.put("useEndPeriod", prByid.getUseEndPeriod());
				//优惠金额
				map.put("offsetMoney", prByid.getOffsetMoney());
				//查询是否已产生了劵
				Integer countByPRId = promotionStampService.getCountByPRId(prByid.getId());
				//System.out.println(prByid.getType());
				map.put("type",(countByPRId==0?countByPRId:prByid.getType()));
			}
			System.out.println("根据促销id查询到的信息"+map.values());
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询单个促销规则信息 "+e.getMessage());
		}
		return null;
	}
	
	/**
	 * 修改促销规则
	 * @param request
	 * @param PRID 主键ID
	 * @param title 标题
	 * @param number 数量
	 * @param detailExplain 说明
	 * @param beginIssueDate 开始有效时间
	 * @param endIssueDate 结束有效时间
	 * @param issueVipCondition 发放会员条件 true 会员才能满足 false都满足
	 * @param lowestConsumptionMoney 最低消费金额
	 * @param rank 等级，数字越大等级越高
	 * @param issueRanges 发放范围
	 * @param issueBeginDate 发放开始时间
	 * @param issueEndDate 发放结束时间
	 * @param useValidNum 使用有效天数
	 * @param useBeginDate 使用有效开始日期
	 * @param useEndDate 使用有效结束日期
	 * @param useRanges 使用范围
	 * @param useBeginPeriod 使用开始时段
	 * @param useEndPeriod 使用结束时段
	 * @param offsetMoney 优惠金额
	 * @return
	 */
	@RequestMapping(value="updatePR")
	public Object updatePR(HttpServletRequest request,String PRID,String title,Integer number,String detailExplain
			,String beginIssueDate,String endIssueDate,Double lowestConsumptionMoney
			,Integer rank,String issueRanges,String issueBeginDate,String issueEndDate,Integer useValidNum,String useBeginDate
			,String useEndDate,String useRanges,String useBeginPeriod,String useEndPeriod,String offsetMoney,String building,String format){
		
		rank=1;
		try {
			if (StringUtil.isNUllStr(PRID) || StringUtil.isNUllStr(title) || number==null || StringUtil.isNUllStr(detailExplain)
					|| StringUtil.isNUllStr(beginIssueDate) || StringUtil.isNUllStr(endIssueDate) 
					|| lowestConsumptionMoney==null || StringUtil.isNUllStr(offsetMoney)
					|| rank==null || StringUtil.isNUllStr(issueRanges) || StringUtil.isNUllStr(useRanges)
							|| StringUtil.isNUllStr(issueBeginDate) || StringUtil.isNUllStr(issueEndDate)
					|| StringUtil.isNUllStr(useBeginPeriod) || StringUtil.isNUllStr(useEndPeriod)) {
				return getPromotion(request, 1, 10, null); 
			}
			//获取促销规则对象
			PromotionRule promotionRule = promotionRuleService.getPRByid(PRID);
			if (promotionRule==null) {
				return getPromotion(request, 1, 10, null); 
			}
			//标题
			promotionRule.setTitle(title);
			
			//说明
			promotionRule.setDetailExplain(detailExplain);
			//数量
			promotionRule.setNumber(number);
			//开始有效时间
			promotionRule.setBeginIssueDate(DateUtil.getDate(beginIssueDate));
			//结束有效时间
			promotionRule.setEndIssueDate(DateUtil.getDate(endIssueDate));
			//发放会员条件 true 会员才能满足 false都满足
			promotionRule.setIssueVipCondition(true);
			//最低消费金额
			promotionRule.setLowestConsumptionMoney(new BigDecimal(lowestConsumptionMoney));
			//发放开始时间段
			promotionRule.setIssueBeginDate(DateUtil.getSqlTime(issueBeginDate, "HH:mm:ss"));
			//发放结束时间
			promotionRule.setIssueEndDate(DateUtil.getSqlTime(issueEndDate, "HH:mm:ss"));
			//发放集合
			List<PromotionRange> promotionRanges=new ArrayList<>();
			XfStore xfStore;
			PromotionRange promotionRange;
			String[] split = issueRanges.split(",");
			for (String storeCode : split) {
				xfStore = xfStoreService.getXfStoreByStoreCode(storeCode);
				if (xfStore!=null) {
					promotionRange=new PromotionRange();
					//关联促销规则
					promotionRange.setPromotionRule(promotionRule);
					//店铺
					promotionRange.setXfStore(xfStore);
					//添加发放的店铺
					promotionRanges.add(promotionRange);
				}
			}
			//等级
			promotionRule.setRank(rank);
			
			if (useValidNum!=null) {
				//使用有效天数
				promotionRule.setUseValidNum(useValidNum);
			}else{
				if (StringUtil.isNUllStr(useBeginDate) || StringUtil.isNUllStr(useEndDate)) {
					return getPromotion(request, 1, 10, null);
				}
				//使用有效开始日期
				promotionRule.setUseBeginDate(DateUtil.getDate(useBeginDate));
				//使用有效结束日期
				promotionRule.setUseEndDate(DateUtil.getDate(useEndDate));
			}
			
			//使用集合
			List<PromotionUseRange> promotionUseRanges=new ArrayList<>();
			PromotionUseRange promotionUseRange;
			split = useRanges.split(",");
			for (String storeCode : split) {
				xfStore = xfStoreService.getXfStoreByStoreCode(storeCode);
				if (xfStore!=null) {
					promotionUseRange=new PromotionUseRange();
					//关联促销规则
					promotionUseRange.setPromotionRule(promotionRule);
					//店铺
					promotionUseRange.setXfStore(xfStore);
					//添加使用的店铺
					promotionUseRanges.add(promotionUseRange);
				}
			}
			//使用开始时段
			promotionRule.setUseBeginPeriod(DateUtil.getSqlTime(useBeginPeriod, "HH:mm:ss"));
			//使用结束时段
			promotionRule.setUseEndPeriod(DateUtil.getSqlTime(useEndPeriod, "HH:mm:ss"));
			//优惠金额
			promotionRule.setOffsetMoney(new BigDecimal(offsetMoney));
			//保存或者更新
			promotionRuleService.updatePR(promotionRangeService,promotionUseRangeService,promotionRule,promotionRanges,promotionUseRanges);
			return getPromotion(request, 1, 10, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改促销规则 "+e.getMessage());
		}
		return getPromotion(request, 1, 10, null);
	}
	
	/**
	 * 删除促销规则
	 * @param id 主键ID
	 * @return
	 */
	@RequestMapping(value="deletePR")
	@ResponseBody
	public Object deletePR(String id){
		try {
			if (StringUtil.isNUllStr(id)) {
				return false;
			}
			promotionRuleService.deletePRById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除促销"+e.getMessage());
		}
		return false;
	}
	
	/**
	 * 促销优惠劵统计
	 * @param request
	 * @param pageIndex 页码
	 * @param pageSize 每页显示的行数
	 * @param key 搜索条件
	 * @return
	 */
//	@RequestMapping(value="getPsStatistics")
//	public Object getPsStatistics(HttpServletRequest request,
//			String beginDate,String endDate,String pss,String issueRanges,String useRanges){
//		List<PsStatisticsTotal> psStatisticsTotals;
//		try {
//			//总计与小计集合
//			psStatisticsTotals=promotionStampService.getListByParams(beginDate,endDate,pss,issueRanges,useRanges);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("促销劵统计 "+e.getMessage());
//			psStatisticsTotals=new ArrayList<>();
//		}
//		//设置选中菜单项
//		request.getSession().setAttribute("select", "promotion");
//		//设置选中子菜单项
//		request.getSession().setAttribute("submenu", "getPsStatistics");
//		//设置返回值
//		request.setAttribute("psStatisticsTotals", psStatisticsTotals);
//		return "/promotionStamp";
//	}
	
	@RequestMapping(value="getPsStatistics")
	public Object getPsStatistics(HttpServletRequest request,Integer pageIndex, Integer pageSize, String key){
		try {
			if (pageIndex == null) {
				pageIndex = Integer.parseInt(SetEnum.pageIndex.getValue());
			}
			if (pageSize == null) {
				pageSize = Integer.parseInt(SetEnum.pageSize.getValue());
			}
			if (key != null) {
				key= new String(key.getBytes("iso-8859-1"),"utf-8");
				key = key.trim();
				request.setAttribute("key", key);
			}
			//数据总条数
			Integer totalCount = 0;
			List<PsStatisticsTotalAll> psStatisticsTotalAlls;
			//根据促销的标题得到所有促销规则
			totalCount=promotionRuleService.getPRCount(key);
			psStatisticsTotalAlls=promotionStampService.getList(pageIndex, pageSize, key);
			//总计与小计集合
//			psStatisticsTotals=promotionStampService.getListByParams(beginDate,endDate,key,issueRanges,useRanges);
			// 封装到分页对象中
			Paging<PsStatisticsTotalAll> paging = new Paging<>(pageIndex, pageSize, totalCount, psStatisticsTotalAlls);
			// 放在request中
			request.getSession().setAttribute("paging", paging);
			request.getSession().setAttribute("select", "getPsStatistics");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("促销劵统计 "+e.getMessage());
		}
		return "/promotionStamp";
	}	
	
	@RequestMapping(value="getPsStatisticsByid")
	public Object getPsStatisticsByid(HttpServletRequest request,Integer pageIndex, Integer pageSize, String key,BigDecimal id){
		try {
			if (pageIndex == null) {
				pageIndex = Integer.parseInt(SetEnum.pageIndex.getValue());
			}
			if (pageSize == null) {
				pageSize = Integer.parseInt(SetEnum.pageSize.getValue());
			}
			if (key != null) {
				key= new String(key.getBytes("iso-8859-1"),"utf-8");
				key = key.trim();
				request.setAttribute("key", key);
			}
			//数据总条数
			Integer totalCount = 0;
			List<PsStatisticsSubtotal> psStatisticsSubtotals;
			//根据促销的标题得到所有促销规则
			totalCount=promotionStampService.getSubtotalCountByPRId(key,id);
			psStatisticsSubtotals=promotionStampService.getSubtotalList(pageIndex, pageSize/2, key,id);
			//总计与小计集合
//			psStatisticsTotals=promotionStampService.getListByParams(beginDate,endDate,key,issueRanges,useRanges);
			// 封装到分页对象中
			Paging<PsStatisticsSubtotal> paging = new Paging<>(pageIndex, pageSize, totalCount, psStatisticsSubtotals);
			// 放在request中
			request.getSession().setAttribute("paging", paging);
			request.getSession().setAttribute("rid", id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("促销劵统计 "+e.getMessage());
//			psStatisticsTotals=new ArrayList<>();
		}
		
		//设置返回值
//		request.setAttribute("psStatisticsTotals", psStatisticsTotals);
		return "/promotionStampStore";
	}	
	/**
	 * 获取促销规则
	 * @return
	 */
	@RequestMapping(value="getPromotionRules")
	@ResponseBody
	public Object getPromotionRules(String title){
		//获取促销规则集合
		List<PromotionRule> prList = promotionRuleService.getPRList(null, null, title);
		List<Map<String, Object>> list=new ArrayList<>();
		if (prList!=null && prList.size()>0) {
			Map<String, Object> map;
			for (PromotionRule promotionRule : prList) {
				map=new HashMap<>();
				//id
				map.put("id",promotionRule.getId());
				//标题
				map.put("title", promotionRule.getTitle());
				list.add(map);
			}
		}
		return list;
	}
	
	/**
	 * 获取店铺
	 * @return
	 */
	@RequestMapping(value="getXfStores")
	@ResponseBody
	public Object getXfStores(String codeOrName){
		List<XfStore> xfStoreList = xfStoreService.getXfStoreList(null, null, null, codeOrName);
		return xfStoreList;
	}
	
	/**
	 * 修改如果同时满足促销规则最多可以选择多少个
	 * @param maxSelectPR 个数
	 * @return
	 */
	@RequestMapping(value="maxSelectPR")
	@ResponseBody
	public Object maxSelectPR(String maxSelectPR){
		if (!StringUtil.isNUllStr(maxSelectPR)) {
			//获取促销设置个数
			Sets sets = setService.getSets("maxSelectPR");
			if (sets!=null) {
				sets.setValue(maxSelectPR);
				//更新设置
				setService.saveOrUpdateSets(sets);
				//更新内存中
				Init.INITURL.put("maxSelectPR", maxSelectPR);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 改变规则的类型
	 * @param request
	 * @param id 规则主键
	 * @param type 1操作有   2只能用劵   3停用
	 * @return
	 */
	@RequestMapping(value="changePRType")
	public  Object changePRType(HttpServletRequest request,String PRID,String type){
		try {
			if (!StringUtil.isNUllStr(PRID) && type!=null && ("1".equals(type) || "2".equals(type) || "3".equals(type))) {
				PromotionRule prByid = promotionRuleService.getPRByid(PRID);
				if (prByid!=null) {
					//设置类型
					prByid.setType(type);
					//更新
					promotionRuleService.saveOrUpdatePR(prByid);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("操作规则类型 "+e.getMessage());
		}
		return getPromotion(request, null, null, null);
	}
	
	/**
	 * 普通
	 */
	@RequestMapping("/getOrdinaryPromotion")
	public Object getOrdinaryPromotion(HttpServletRequest request,Integer pageIndex,Integer pageSize,String key){
		try {
			if (pageIndex == null) {
				pageIndex = Integer.parseInt(SetEnum.pageIndex.getValue());
			}
			if (pageSize == null) {
				pageSize = Integer.parseInt(SetEnum.pageSize.getValue());
			}
			if (key != null && !"".equals(key)) {
				key= new String(key.getBytes("iso-8859-1"),"utf-8");
				key = key.trim();
				request.setAttribute("key", key);
			}
			else{
	        	key=null;
	        }
			//数量
			Integer count=promotionRuleService.getOrCount(key);
			//促销规则集合
			List<PromotionRule> promotionRules=promotionRuleService.getOrList(pageIndex,pageSize,key);
			//得到促销规则的id 从表中得到是否有关联数据  有着setisshow is 0；
			for (PromotionRule pr : promotionRules) {
				Integer countStamp = promotionStampService.getCountByPRId(pr.getId());
				if(countStamp > 0){
					pr.setIsEdit("1");
				}else{
					pr.setIsEdit("0");
				}
			}
			
			//分页对象
			Paging<PromotionRule> paging=new Paging<>(pageIndex, pageSize, count==null?0:count, promotionRules);
			request.getSession().setAttribute("paging", paging);
			request.getSession().setAttribute("select","ordinaryPromotion");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取促销 "+e.getMessage());
		}
		return "/ordinaryPromotion";
	}
	
	@RequestMapping(value="addOr")
	public Object addOr(HttpServletRequest request,String title,Integer number,String detailExplain
			,String beginIssueDate,String endIssueDate,Double lowestConsumptionMoney
			,Integer rank,String issueRanges,String issueBeginDate,String issueEndDate,Integer useValidNum,String useBeginDate
			,String useEndDate,String useRanges,String useBeginPeriod,String useEndPeriod,String offsetMoney){
		rank=1;
		try {
			if (StringUtil.isNUllStr(title) || number==null || StringUtil.isNUllStr(detailExplain)
					|| StringUtil.isNUllStr(beginIssueDate) || StringUtil.isNUllStr(endIssueDate) 
					|| lowestConsumptionMoney==null || StringUtil.isNUllStr(offsetMoney)
					|| rank==null || StringUtil.isNUllStr(issueRanges) || StringUtil.isNUllStr(useRanges)
					|| StringUtil.isNUllStr(issueBeginDate) || StringUtil.isNUllStr(issueEndDate)
					|| StringUtil.isNUllStr(useBeginPeriod) || StringUtil.isNUllStr(useEndPeriod)) {
				return getPromotion(request, 1, 10, null); 
			}
			//促销规则对象
			PromotionRule promotionRule=new PromotionRule();
			//标题
			promotionRule.setTitle(title);
			
			//说明
			promotionRule.setDetailExplain(detailExplain);
			//数量
			promotionRule.setNumber(number);
			promotionRule.setBeginIssueDate(DateUtil.getDate(beginIssueDate));			//开始有效时间
			promotionRule.setEndIssueDate(DateUtil.getDate(endIssueDate));			//结束有效时间
			promotionRule.setIssueVipCondition(false);			//发放会员条件 true 会员才能满足 false都满足
			promotionRule.setLowestConsumptionMoney(new BigDecimal(lowestConsumptionMoney));			//最低消费金额
			promotionRule.setIssueBeginDate(DateUtil.getSqlTime(issueBeginDate, "HH:mm:ss"));			//发放开始时间段
			promotionRule.setIssueEndDate(DateUtil.getSqlTime(issueEndDate, "HH:mm:ss"));			//发放结束时间
			promotionRule.setType("1");
			//发放集合
			List<PromotionRange> promotionRanges=new ArrayList<>();
			XfStore xfStore;
			PromotionRange promotionRange;
			String[] split = issueRanges.split(",");
			for (String storeCode : split) {
				xfStore = xfStoreService.getXfStoreByStoreCode(storeCode);
				if (xfStore!=null) {
					promotionRange=new PromotionRange();
					//关联促销规则
					promotionRange.setPromotionRule(promotionRule);
					//店铺
					promotionRange.setXfStore(xfStore);
					//添加发放的店铺
					promotionRanges.add(promotionRange);
				}
			}
			//发放范围
			promotionRule.setIssueRanges(promotionRanges);
			//等级
			promotionRule.setRank(rank);
			
			if (useValidNum!=null) {
				//使用有效天数
				promotionRule.setUseValidNum(useValidNum);
			}else{
				if (StringUtil.isNUllStr(useBeginDate) || StringUtil.isNUllStr(useEndDate)) {
					return getPromotion(request, 1, 10, null);
				}
				//使用有效开始日期
				promotionRule.setUseBeginDate(DateUtil.getDate(useBeginDate));
				//使用有效结束日期
				promotionRule.setUseEndDate(DateUtil.getDate(useEndDate));
			}
			//使用集合
			List<PromotionUseRange> promotionUseRanges=new ArrayList<>();
			PromotionUseRange promotionUseRange;
			split = useRanges.split(",");
			for (String storeCode : split) {
				xfStore = xfStoreService.getXfStoreByStoreCode(storeCode);
				if (xfStore!=null) {
					promotionUseRange=new PromotionUseRange();
					//关联促销规则
					promotionUseRange.setPromotionRule(promotionRule);
					//店铺
					promotionUseRange.setXfStore(xfStore);
					//添加使用的店铺
					promotionUseRanges.add(promotionUseRange);
				}
			}
			//使用范围
			promotionRule.setUseRanges(promotionUseRanges);
			//使用开始时段
			promotionRule.setUseBeginPeriod(DateUtil.getSqlTime(useBeginPeriod, "HH:mm:ss"));
			//使用结束时段
			promotionRule.setUseEndPeriod(DateUtil.getSqlTime(useEndPeriod, "HH:mm:ss"));
			//优惠金额
			promotionRule.setOffsetMoney(new BigDecimal(offsetMoney));
			//保存或者更新
			promotionRuleService.saveOrUpdatePR(promotionRule);
			return getOrdinaryPromotion(request, 1, 10, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加促销规则 "+e.getMessage());
		}
		return getOrdinaryPromotion(request, 1, 10, null);
	}
	
	@RequestMapping(value="updateOr")
	public Object updateOr(HttpServletRequest request,String PRID,String title,Integer number,String detailExplain
			,String beginIssueDate,String endIssueDate,Double lowestConsumptionMoney
			,Integer rank,String issueRanges,String issueBeginDate,String issueEndDate,Integer useValidNum,String useBeginDate
			,String useEndDate,String useRanges,String useBeginPeriod,String useEndPeriod,String offsetMoney){
		
		rank=1;
		try {
			if (StringUtil.isNUllStr(PRID) || StringUtil.isNUllStr(title) || number==null || StringUtil.isNUllStr(detailExplain)
					|| StringUtil.isNUllStr(beginIssueDate) || StringUtil.isNUllStr(endIssueDate) 
					|| lowestConsumptionMoney==null || StringUtil.isNUllStr(offsetMoney)
					|| rank==null || StringUtil.isNUllStr(issueRanges) || StringUtil.isNUllStr(useRanges)
							|| StringUtil.isNUllStr(issueBeginDate) || StringUtil.isNUllStr(issueEndDate)
					|| StringUtil.isNUllStr(useBeginPeriod) || StringUtil.isNUllStr(useEndPeriod)) {
				return getPromotion(request, 1, 10, null); 
			}
			//获取促销规则对象
			PromotionRule promotionRule = promotionRuleService.getPRByid(PRID);
			if (promotionRule==null) {
				return getPromotion(request, 1, 10, null); 
			}
			//标题
			promotionRule.setTitle(title);
			//说明
			promotionRule.setDetailExplain(detailExplain);
			//数量
			promotionRule.setNumber(number);
			//开始有效时间
			promotionRule.setBeginIssueDate(DateUtil.getDate(beginIssueDate));
			//结束有效时间
			promotionRule.setEndIssueDate(DateUtil.getDate(endIssueDate));
			//发放会员条件 true 会员才能满足 false都满足
			promotionRule.setIssueVipCondition(false);
			//最低消费金额
			promotionRule.setLowestConsumptionMoney(new BigDecimal(lowestConsumptionMoney));
			//发放开始时间段
			promotionRule.setIssueBeginDate(DateUtil.getSqlTime(issueBeginDate, "HH:mm:ss"));
			//发放结束时间
			promotionRule.setIssueEndDate(DateUtil.getSqlTime(issueEndDate, "HH:mm:ss"));
			//发放集合
			List<PromotionRange> promotionRanges=new ArrayList<>();
			XfStore xfStore;
			PromotionRange promotionRange;
			String[] split = issueRanges.split(",");
			for (String storeCode : split) {
				xfStore = xfStoreService.getXfStoreByStoreCode(storeCode);
				if (xfStore!=null) {
					promotionRange=new PromotionRange();
					//关联促销规则
					promotionRange.setPromotionRule(promotionRule);
					//店铺
					promotionRange.setXfStore(xfStore);
					//添加发放的店铺
					promotionRanges.add(promotionRange);
				}
			}
			//等级
			promotionRule.setRank(rank);
			
			if (useValidNum!=null) {
				//使用有效天数
				promotionRule.setUseValidNum(useValidNum);
			}else{
				if (StringUtil.isNUllStr(useBeginDate) || StringUtil.isNUllStr(useEndDate)) {
					return getPromotion(request, 1, 10, null);
				}
				//使用有效开始日期
				promotionRule.setUseBeginDate(DateUtil.getDate(useBeginDate));
				//使用有效结束日期
				promotionRule.setUseEndDate(DateUtil.getDate(useEndDate));
			}
			
			//使用集合
			List<PromotionUseRange> promotionUseRanges=new ArrayList<>();
			PromotionUseRange promotionUseRange;
			split = useRanges.split(",");
			for (String storeCode : split) {
				xfStore = xfStoreService.getXfStoreByStoreCode(storeCode);
				if (xfStore!=null) {
					promotionUseRange=new PromotionUseRange();
					//关联促销规则
					promotionUseRange.setPromotionRule(promotionRule);
					//店铺
					promotionUseRange.setXfStore(xfStore);
					//添加使用的店铺
					promotionUseRanges.add(promotionUseRange);
				}
			}
			//使用开始时段
			promotionRule.setUseBeginPeriod(DateUtil.getSqlTime(useBeginPeriod, "HH:mm:ss"));
			//使用结束时段
			promotionRule.setUseEndPeriod(DateUtil.getSqlTime(useEndPeriod, "HH:mm:ss"));
			//优惠金额
			promotionRule.setOffsetMoney(new BigDecimal(offsetMoney));
			//保存或者更新
			promotionRuleService.updatePR(promotionRangeService,promotionUseRangeService,promotionRule,promotionRanges,promotionUseRanges);
			return getOrdinaryPromotion(request, 1, 10, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改促销规则 "+e.getMessage());
		}
		return getOrdinaryPromotion(request, 1, 10, null);
	}
	
	/**
	 * 判断标题是否重复-会员
	 */
	@RequestMapping("/isRepeat")
	@ResponseBody
	public Object isRepeat(HttpServletRequest request,String title){
		PromotionRule promotionRule = promotionRuleService.isRepeat(title);
		if(promotionRule != null){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断标题是否重复-谱图
	 */
	@RequestMapping("/isOrRepeat")
	@ResponseBody
	public Object isOrRepeat(HttpServletRequest request,String title){
		PromotionRule promotionRule = promotionRuleService.isOrRepeat(title);
		if(promotionRule != null){
			return true;
		}
		return false;
	}
	
	/*
	 * 会员促销验证
	 * */
	
	/**
	 * gdps促销平台连接
	 * **/
	@Autowired private GdwsGdpsService gdwsGdpsService;
	
	@RequestMapping(value="getPromotionCheck")
	public Object getPromotionCheck(HttpServletRequest request){
		try {			
			request.getSession().setAttribute("select", "getPromotionCheck");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("促销劵统计 "+e.getMessage());
		}
		return "/promotionCheck";
	}
	
	/*
	 * 获取促销验证码图片
	 * */
	
	@RequestMapping(value="getImageBase")
	@ResponseBody
	public Object getImageBase(HttpServletRequest request,String code){
		System.out.println(code);
		Map<String, Object> map = new HashMap<>();
		JSONObject  result=	(JSONObject) gdwsGdpsService.getGdpsImageBase(code);
		String gdpsCode=result.getJSONObject("status").getString("code");
		
		
		if("10000".equals(gdpsCode)){
			String BaseData = result.getJSONObject("data").getString("img");
			map.put("base64", BaseData);
			map.put("msg", "");
		}else{
			map.put("msg", result.getJSONObject("status").getString("msg"));
		}
		map.put("code", code);
		return map;
		
	}
	
	
	/*
	 * 验证平台促销券
	 * */
	
	@RequestMapping(value="checkPromotionCodes")
	@ResponseBody
	public Object checkPromotionCodes(HttpServletRequest request,String codes,String type){
		
		if(type.endsWith("1")){//集字送礼券
			System.out.println(codes.substring(1, codes.length()-1));
			
			Map<String, Object> map = new HashMap<>();
			JSONObject  result=	(JSONObject) gdwsGdpsService.getPromotionCheck(codes.substring(1, codes.length()-1));
			String gdpsCode=result.getJSONObject("status").getString("code");
			
			if("10000".equals(gdpsCode)){
				String value = result.getJSONObject("data").getString("value");
				String valueName = result.getJSONObject("data").getString("valueName");
				map.put("value", value);
				map.put("valueName", valueName);
			}else{
				map.put("value", "");
				map.put("valueName", "未查询到相关礼品");
			}
			return map;
		}else{//普通礼品券
			System.out.println(codes);
			
			Map<String, Object> map = new HashMap<>();
			JSONObject  result=	(JSONObject) gdwsGdpsService.getPromotionCheckPtCode(codes);
			String gdpsCode=result.getJSONObject("status").getString("code");
			
			if("10000".equals(gdpsCode)){
				String value = result.getJSONObject("data").getString("value");
				String valueName = result.getJSONObject("data").getString("valueName");
				map.put("value", value);
				map.put("valueName", valueName);
			}else{
				map.put("value", codes);
				map.put("valueName", "未查询到相关礼品");
			}
			return map;
		}
			
		
	}
	
}
