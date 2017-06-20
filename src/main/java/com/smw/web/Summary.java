package com.smw.web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smw.common.util.BasePageResultVo;
import com.smw.common.util.SetEnum;
import com.smw.common.util.paging.Paging;
import com.smw.pojo.SalesItem;
import com.smw.pojo.SalesSummary;
import com.smw.pojo.SalesTender;
import com.smw.pojo.Sets;
import com.smw.pojo.XfStaff;
import com.smw.service.SalesItemService;
import com.smw.service.SalesSummaryService;
import com.smw.service.SalesTenderService;
import com.smw.service.SetService;

import javafx.geometry.Side;

/**
 * 销售
 * @author suen
 * @date 2016年5月26日-下午5:27:42
 * @version 1.0
 */
@Controller
@RequestMapping("web/xfSummary")
public class Summary {
	Logger logger = LoggerFactory.getLogger(Summary.class);
	@Resource
	private SalesSummaryService salesSummaryService;
	
	@Resource
	private SalesItemService salesItemService;
	
	@Resource
	private SalesTenderService salesTenderService;
	
	/**
	 * 设置
	 */
	@Resource
	private SetService setService;
	
	/**
	 * 获取销售数据
	 * @param request
	 * @param pageIndex 页码
	 * @param pageSize 每页显示的行数
	 * @param key 搜索条件
	 * @return
	 */
	@RequestMapping("getSummary")
	public Object getSummary(HttpServletRequest request,Integer pageIndex,Integer pageSize,String key){
		try {
			if (pageIndex == null) {
				pageIndex = Integer.parseInt(SetEnum.pageIndex.getValue());
			}
			if (pageSize==null){
	            pageSize=Integer.parseInt(SetEnum.pageSize.getValue());
	        }
			if (key!=null && !"".equals(key)) {
				key= new String(key.getBytes("iso-8859-1"),"utf-8");
		        key=key.trim();
		        request.setAttribute("key", key);
	        }else{
	        	key=null;
	        }
			//销售记录总数
			Integer totalCount=0;
			//销售记录集合
			List<SalesSummary> ss;
			//员工
			XfStaff xfStaff=(XfStaff)request.getSession().getAttribute("XfStaff");
			//权限名称
			String authority = xfStaff.getStaffRole().getAuthority();
			if ("ROLE_MALL_ADMIN".equals(authority)) {
				//获取总数
				totalCount = salesSummaryService.getCount(null, key);
				//获取集合
				ss=salesSummaryService.getSSList(pageIndex,pageSize,null,key);
			}else if("ROLE_STORE_ADMIN".equals(authority)){
				//获取总数
				totalCount = salesSummaryService.getCount(xfStaff.getXfIssuestore().getXfStorecode(), key);
				//获取集合
				ss=salesSummaryService.getSSList(pageIndex,pageSize,xfStaff.getXfIssuestore().getXfStorecode(),key);
			}else{
				ss=new ArrayList<>();
			}
			//封装到分页对象中
			Paging<SalesSummary> paging=new Paging<>(pageIndex, pageSize, totalCount, ss);
			System.out.println(paging.toString());
			//放在request中
			request.getSession().setAttribute("paging", paging);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取销售数据"+e.getMessage());
		}
		//设置菜单选中项
		request.getSession().setAttribute("select", "summary");
		//线程休眠时间
		request.setAttribute("saleSummaryTime", Init.INITURL.get("saleSummaryTime"));
		return "/summary";
	}
	
	/**
	 * 设置上传销售数据到新MIS的时间间隔
	 * @param time 时间
 	 * @return
	 */
	@RequestMapping("setThTime")
	@ResponseBody
	public Object setThTime(String time){
		try {
			if (time==null) {
				return false;
			}
			Sets sets=new Sets();
			//设置key
			sets.setId("saleSummaryTime");
			//设置时间
			sets.setValue(time);
			//保存
			setService.saveOrUpdateSets(sets);
			//保存在内存中
			com.smw.web.Init.INITURL.put("saleSummaryTime", time);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("设置上传销售数据到MIS时间间隔"+e.getMessage());
		}
		return false;
	}
	@RequestMapping("getSummaryBycode")
	@ResponseBody
	public Object getSummaryBycode(String txdocno) throws JsonProcessingException{
		
		//SalesSummary summary = salesSummaryService.getSummaryBycode(txdocno);
		SalesSummary summary = salesSummaryService.getSSByTxd(txdocno);
		Map<String, Object> map = new HashMap<>();
		map.put("txdocno", summary.getTxdocno());//销售单号
		map.put("txdate", summary.getTxdate());//交易日期
		map.put("txtime", summary.getTxtime());//交易时间
		map.put("mallid", summary.getMallid().getXfMallid());//商场编号
		map.put("storecode", summary.getStorecode().getXfStorecode());//店铺编号
		map.put("xfName", summary.getStorecode().getXfName());//收银店铺
		map.put("staffcode", summary.getStaffcode().getXfStaffcode());//收银员编号
		map.put("vipcode", summary.getVipcode());//会员号
		map.put("salesman", summary.getSalesman());//营业员
		map.put("netqty", summary.getNetqty());//销售总数量
		map.put("netamount", summary.getNetamount().toString());//销售净金额
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		String datetime = sdf.format(summary.getTxdate());
		map.put("date", datetime);
		//货号，品牌，数量，金额
		
		List<SalesItem> sis = salesItemService.getXfItemByDocCode(txdocno);//销售单货品明细
		List listsis = new ArrayList<>();
		for(int i=0;i<sis.size();i++){
			SalesItem it = sis.get(i);
			
			Map mapsis = new HashMap<>();
			mapsis.put("xfplu", it.getPlu().getXfPlu());//货号
			mapsis.put("desci", it.getPlu().getXfDesci());//品牌
			mapsis.put("qty", it.getQty().toString());//数量
			mapsis.put("netamount", it.getNetamount().toString());//净金额
			listsis.add(mapsis);
			
		}
		map.put("listsis", listsis);//商品详情
		//付款，找零
		
		List<SalesTender> sts =salesTenderService.getSTSByTxdocno(txdocno);
		List<Object> liststs = new ArrayList<>();
		BigDecimal payamount= new BigDecimal(0);
		for(int i=0;i<sts.size();i++){
			SalesTender it = sts.get(i);
			Map mapsts = new HashMap<>();
			map.put("net", it.getBaseamount().toString());//应收金额
			payamount=payamount.add(it.getPayamount());
			map.put("qty", payamount.subtract(it.getBaseamount()).toString());//找零金额
			map.put("netamount", it.getBaseamount().toString());//合计金额
			mapsts.put("xfTenderdesc", it.getTendercode().getXfTenderdesc());//付款方式名称
			mapsts.put("payamount", it.getBaseamount().toString());//付款金额
			mapsts.put("transMemo",	it.getTransMemo());//优惠券号
			liststs.add(mapsts);
			
		}
		map.put("desci", payamount.toString());//已收金额
		map.put("liststs", liststs);//支付方式
	/*	map.put("net",summary.getNetamount());//应收金额
		map.put("pay", summary.getPayamount());//已收金额
		map.put("ret", summary.getPayamount().subtract(summary.getNetamount()));//找零金额
		map.put("cot", summary.getNetamount());//合计金额*/
		//付款方式
		
		return map;
		
	}
}
