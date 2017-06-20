package com.smw.web;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.smw.common.util.DateUtil;
import com.smw.common.util.PropertiesUtil;
import com.smw.pojo.SalesItem;
import com.smw.pojo.SalesSummary;
import com.smw.pojo.SalesTender;
import com.smw.service.SalesItemService;
import com.smw.service.SalesSummaryService;
import com.smw.service.SalesTenderService;
import com.techtrans.espos.ps.jsonsign.JsonSignImpl;

/**
 * 上传销售数据到V61系统
 * 
 * @author suen
 * @date 2016年7月6日-下午3:34:47
 * @version jdk1.8
 */
public class V61 {
	private static Logger logger = LoggerFactory.getLogger(V61.class);
	/**
	 * 是否上传销售数据到V61
	 */
	public static String isUploadV61 = "";
	/**
	 * V61接口地址
	 */
	private static String V61Url = "";
	static {
		//读取配置文件
		PropertiesUtil p = new PropertiesUtil("wsdl-config.properties");
		isUploadV61 = p.readProperty("isUploadV61");
		V61Url = p.readProperty("V61Url");
	}

	/**
	 * 保存一笔销售记录到61
	 * 
	 * @return
	 */
	public static void saveSalesSummary(SalesSummaryService salesSummaryService, SalesItemService salesItemService,
			SalesTenderService salesTenderService) {
		try {
			if (isUploadV61 == null || "false".equals(isUploadV61)) {
				return;
			}
			// 查询销售数据
			List<SalesSummary> salesList = salesSummaryService.list(false, 1);
			if (salesList != null && salesList.size() != 0) {
				for (SalesSummary salesSummary : salesList) {
					String txdocno = salesSummary.getTxdocno();
					//商品店铺号
					String xfStorecode = salesSummary.getStorecode().getXfStorecode();
					//收银店铺
					String cashStorecode = salesSummary.getCashStorecode().getXfStorecode();
					
					String docKey=new SimpleDateFormat("yyyyMMdd").format(salesSummary.getTxdate())+"."+cashStorecode+"."+salesSummary.getTillid()+"."+txdocno;
					Map<String, Object> map = new HashMap<>();
					map.put("signature", "");
					map.put("apiKey", "");
					map.put("docKey", docKey);
					// 头部
					Map<String, Object> transHeader = new HashMap<>();
					// 交易日期yyyy-mm-dd
					transHeader.put("txDate", DateUtil.format(salesSummary.getTxdate(), "yyyy-MM-dd"));
					// 客戶端发生的日期及时间
					transHeader.put("ledgerDatetime",
							DateUtil.format(salesSummary.getTxdate(), "yyyy-MM-dd") + " " + salesSummary.getTxtime());
					// 店铺编号
					transHeader.put("storeCode", cashStorecode);
					// 收银机号
					transHeader.put("tillId", salesSummary.getTillid());
					// 销售单号
					transHeader.put("docNo", txdocno);
					// 添加到头部信息
					map.put("transHeader", transHeader);

					// salesTotal
					Map<String, Object> salesTotal = new HashMap<>();
					//收款员编号
					salesTotal.put("cashier", salesSummary.getStaffcode().getXfStaffcode());
					//会员编号
					salesTotal.put("vipCode", salesSummary.getVipcode());
					//净销售数量（销售为正数，退货为负数）
					salesTotal.put("netQty", salesSummary.getNetqty());
					//净销售金额（销售为正数，退货为负数）
					salesTotal.put("netAmount", salesSummary.getNetamount());
					//扩展参数(区分中央收银)
					if (!xfStorecode.equals(cashStorecode)) {
						salesTotal.put("extendParameter", "BS,"+xfStorecode+" ");
					}
					//添加到销售信息
					map.put("salesTotal", salesTotal);
					
					List<Map<String, Object>> salesItems = new ArrayList<Map<String, Object>>();
					Map<String, Object> salesItem = null;
					// 销售单货品明细表数组
					List<SalesItem> sis = salesItemService.getXfItemByDocCode(txdocno);
					for (SalesItem si : sis) {
						salesItem = new HashMap<>();
						// 销售行号
						salesItem.put("salesLineNumber", si.getLineno());
						// 商铺号
						salesItem.put("itemCode", si.getPlu().getXfPlu());
						// 商品组织架构
						salesItem.put("itemOrgId", si.getPlu().getItemOrgId());
						// 货品批号
						salesItem.put("itemLotNum", si.getLineno());
						// 库存类型 : 0 to 5 0–正常类型
						salesItem.put("inventoryType", "0");
						// 销售数量（销售为正数，退货为负数）
						salesItem.put("qty", si.getQty());
						// 货品折扣金额
						salesItem.put("itemDiscountLess", si.getDiscountamount());
						// 整单折扣所摊分的金额
						salesItem.put("totalDiscountLess", si.getDiscountamount());
						// 净销售金额（即实收金额）（销售为正数，退货为负数）
						salesItem.put("netAmount", si.getNetamount());
						//扩展参数(区分中央收银)
						if (!xfStorecode.equals(cashStorecode)) {
							salesItem.put("extendParameter", "BS,"+xfStorecode+" ");
						}
						salesItems.add(salesItem);
					}
					// 条件到销售商铺集合中
					map.put("salesItem", salesItems);

					List<Map<String, Object>> salesTenders = new ArrayList<>();
					Map<String, Object> salesTender;
					// 销售单付款明细表数组
					List<SalesTender> sts = salesTenderService.getSTSByTxdocno(txdocno);
					for (SalesTender st : sts) {
						salesTender = new HashMap<>();
						// 本地货币编号
						salesTender.put("baseCurrencyCode", "RMB");
						// 付款方式编号
						salesTender.put("tenderCode", st.getTendercode().getXfTendercode());
						// 付款金额
						salesTender.put("payAmount", st.getPayamount());
						// 本位币付款金额
						salesTender.put("baseAmount", st.getBaseamount());
						// 多收金额（例如礼券不设找零）
						salesTender.put("excessAmount", st.getExcessamount());
						//扩展参数(区分中央收银)
						if (!xfStorecode.equals(cashStorecode)) {
							salesTender.put("extendParameter", "-BS"+xfStorecode+",");
						}
						salesTenders.add(salesTender);
					}
					// 添加到支付方式
					map.put("salesTender", salesTenders);

					PostMethod postMethod = new PostMethod(V61Url);
					Gson gson = new Gson();
					String json = gson.toJson(map);
					//System.out.println(json);
					JsonParser jsonParser = new JsonParser();
					JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
					jsonObject.entrySet().iterator();

					JsonSignImpl sign = new JsonSignImpl();
					map.put("apiKey", "CRMINT");
					map.put("signature", sign.jsonSign(jsonObject));
					json = gson.toJson(map);
					System.out.println(json);
					byte[] b = json.getBytes("utf-8");
					InputStream is = new ByteArrayInputStream(b, 0, b.length);
					RequestEntity re = new InputStreamRequestEntity(is, b.length, "application/json; charset=utf-8");
					postMethod.setRequestEntity(re);

					// 最后生成一个HttpClient对象，并发出postMethod请求
					HttpClient httpClient = new HttpClient();
					int statusCode = httpClient.executeMethod(postMethod);
					if (statusCode == 200) {
						String result=postMethod.getResponseBodyAsString();
						JSONObject parseObject = JSON.parseObject(result);
						if ("0".equals(parseObject.getString("errorCode"))) {
							System.out.println("成功");
							//更新上传销售数据到V61成功
							salesSummary.setTransferEspos(true);
							salesSummaryService.updateSalesSummaryState(salesSummary);
						}else{
							logger.error("上传销售数据到V61     数据："+json+"  61返回结果："+postMethod.getResponseBodyAsString());
						}
					} else {
						/*System.out.println("调用失败！错误码：" + statusCode);
						System.out.println(postMethod.getResponseBodyAsString());*/
						logger.error("上传销售数据到V61     数据："+json+"  61返回结果："+postMethod.getResponseBodyAsString());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("上传到V61  销售数据" + e.getMessage());
		}
	}
}
