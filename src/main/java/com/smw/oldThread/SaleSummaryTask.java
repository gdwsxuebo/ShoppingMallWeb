package com.smw.oldThread;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gws.service.impl.GdwsMisService;
import com.smw.common.util.ApiDataMap;
import com.smw.pojo.SalesItem;
import com.smw.pojo.SalesSummary;
import com.smw.pojo.SalesTender;
import com.smw.pojo.Sets;
import com.smw.service.SalesItemService;
import com.smw.service.SalesSummaryService;
import com.smw.service.SalesTenderService;
import com.smw.service.SetService;


public class SaleSummaryTask extends ApiDataMap implements Runnable{
	public	Logger logger = LoggerFactory.getLogger(SalesSummary.class);
	private SalesSummaryService salesSummaryService;
	private SalesItemService salesItemService;
	private SetService setService;
	private SalesTenderService salesTenderService;
	
	public void setSalesSummary(SalesSummaryService salesSummaryService,SalesItemService salesItemService
			,SetService setService,SalesTenderService salesTenderService){
		this.salesItemService = salesItemService;
		this.salesSummaryService=salesSummaryService;
		this.setService=setService;
		this.salesTenderService=salesTenderService;
	}
	
	private synchronized Object saleSummary(){
		try {
			List<Map<String, Object>> saleData = new ArrayList<Map<String, Object>>();
			List<SalesSummary> salesList = salesSummaryService.list(false,2);
			if(salesList!=null && salesList.size()!=0){
				for (SalesSummary ss : salesList) {
					GdwsMisService gdwsMisService=new GdwsMisService();
					String docCode = ss.getTxdocno();
					String storeCode = ss.getStorecode().getXfStorecode();
					JSONObject data=new JSONObject();
					data.put("type", 1);
					data.put("gwShopsLeaseCode", storeCode);
					data.put("gwTillid", ss.getTillid());//收银机编号
					data.put("gwTxdate", new SimpleDateFormat("yyyy-MM-dd").format(ss.getTxdate()));
					data.put("gwTxtime", new SimpleDateFormat("HH:mm:ss").format(ss.getTxtime()));
					data.put("gwTxserial", ss.getTxdocno());
					data.put("gwSalesnumber", ss.getNetqty().intValue());
					data.put("gwSalesamount", ss.getNetamount());
					data.put("gwMemberNumber", String.valueOf(ss.getVipcode()));
					data.put("gwTaxSaleamount", ss.getNetamount());
					data.put("gwState", ss.getNetamount().doubleValue()>=0?1:2);
					data.put("isSalesReturn", Integer.parseInt(ss.getIsSalesReturn()));
					List<SalesItem> salesItems = salesItemService.getXfItemByDocCode(docCode);
					JSONArray items=new JSONArray();
					for (SalesItem si : salesItems) {
						JSONObject item=new JSONObject();
						item.put("saleCode", docCode);
						item.put("goodsInfoCode", si.getPlu().getXfPlu());
						//item.put("price", si.getNetamount());
						item.put("price", si.getNetamount().divide(si.getQty()));
						item.put("number", si.getQty());
						//item.put("totalMoney", si.getNetamount().multiply(si.getQty()).doubleValue());
						item.put("totalMoney", si.getNetamount());
						item.put("saleDate", new SimpleDateFormat("yyyy-MM-dd").format(ss.getTxdate()));
						items.add(item);
					}
					data.put("saleGoodsList", items);
					JSONArray tenders=new JSONArray();
					List<SalesTender>  saleTenders=salesTenderService.getSalesTenderListByTxdocno(docCode);
					for(SalesTender st:saleTenders){
						JSONObject tender=new JSONObject();
						tender.put("saleCode", docCode);
						JSONObject misJson=(JSONObject)gdwsMisService.getRate(storeCode);
						String misCode=misJson.getJSONObject("status").getString("code");
						BigDecimal rate= misJson.getBigDecimal("rate");
						if("10010".equals(misCode)){
							rate=new BigDecimal(st.getTendercode().getRate());
						}
						tender.put("paymentTenderRate", rate);
						//						tender.put("paymentTenderRate", new BigDecimal("0.0001"));
						tender.put("paymentTenderCode", st.getTendercode().getXfTendercode());
						tender.put("paymentMoney", st.getPayamount());
						tender.put("saleDate", new SimpleDateFormat("yyyy-MM-dd").format(ss.getTxdate()));
						tenders.add(tender);
					}
					data.put("salePaymentTenderList", tenders);
					saleData.add(data);
				}
				logger.info("上传到MIS的数据  == " + saleData.toString());
				GdwsMisService gdwsMisService=new GdwsMisService();
				JSONObject 	misUpTransInfo=(JSONObject) gdwsMisService.uploadTransInfo(saleData.toString());
				logger.info("MIS 返回的结果"+misUpTransInfo.toJSONString());
				String misCode=misUpTransInfo.getJSONObject("status").getString("code");
				if("10000".equals(misCode)){
					StringBuffer buff=new StringBuffer();
					for (SalesSummary ss : salesList) {
						buff.append(ss.getTxdocno()+" ");
						ss.setNewTransferEspos(true);
						salesSummaryService.updateSalesSummaryState(ss);
					}
					logger.info("销售数据上传到新MIS 成功"+buff.toString());
				}else{
					logger.error("销售数据上传到新MIS "+misUpTransInfo.toJSONString());
					return unSuccessful(unSuccessfulMsg);
				}
			}
			return success(successMsg);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("销售数据上传到新MIS出错！",e);
		}
		return unSuccessful();
	}
	/**
	 * 获取参数
	 * 
	 * @param DYLX
	 *            调用接口类型
	 * @param data
	 *            数据
	 * @return
	 */
	private Map<String, Object> getParams(String DYLX, String data) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// 接口用户标识
		paramMap.put("YHBS", "E84E7273764D2D77E9FC4E9C521E8C3750");
		// 调用系统类别
		paramMap.put("XTLB", "suen");
		// 调用接口类型
		paramMap.put("DYLX", DYLX);
		if (data != null) {
			// 数据
			paramMap.put("DATA", data);
		}
		return paramMap;
	}
	Sets sets;
	@Override
	public void run() {
		while(true){
			try {
				//读取数据库的休眠时间
				sets=setService.getSets("saleSummaryTime");
				if (sets!=null) {
					Thread.sleep(Long.parseLong(sets.getValue()));
				}else{
					//休眠30分钟
					Thread.sleep(30*60*1000);
				}
				//新MIS
				saleSummary();
				//JoinOLDMis.saveSalesSummary(salesSummaryService,salesItemService,salesTenderService);
				//V61.saveSalesSummary(salesSummaryService, salesItemService, salesTenderService);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
