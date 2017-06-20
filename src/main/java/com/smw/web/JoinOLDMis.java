package com.smw.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.smw.common.util.ApiDataMap;
import com.smw.common.util.PropertiesUtil;
import com.smw.pojo.SalesItem;
import com.smw.pojo.SalesSummary;
import com.smw.pojo.SalesTender;
import com.smw.service.SalesItemService;
import com.smw.service.SalesSummaryService;
import com.smw.service.SalesTenderService;
import com.smw.utils.ParseXmlUtil;

public class JoinOLDMis extends ApiDataMap {
	private static Logger logger = LoggerFactory.getLogger(JoinOLDMis.class);
	/**
	 * 科传地址
	 */
	private static String CRMURL = "";
	/**
	 * 老MIS地址
	 */
	private static String OLDMISURL = "";
	
	/**
	 * 是否上传销售数据到老MIS
	 */
	private static String upload="";
	static {
		PropertiesUtil p = new PropertiesUtil("wsdl-config.properties");
		CRMURL = p.readProperty("CRMURL");
		//p = new PropertiesUtil("wsdl-config.properties");
		OLDMISURL = p.readProperty("wsdlLocation");
		upload = p.readProperty("upload");
	}

	/**
	 * 获取会员信息封装成json传输
	 */
	public static Map<String, String> setVipInfo(String xmlData) {
		Map<String, String> map = sendRequest(xmlData, "GetVipInfo");
		// System.out.println(map);
		return map;
	}

	/**
	 * 获取销售信息封装成json传输
	 */
	public static Map<String, String> setConsumeInfo(String xmlData) {
		Map<String, String> map = sendRequest(xmlData, "Consume");
		return map;
	}

	/**
	 * 返回VIP注册基本信息
	 * @param xmlData
	 * @return
	 */
	public static Map<String, List<Map<String, String>>> setBasicInfo(String xmlData){
		try {
			byte[] b = xmlData.getBytes("utf-8");
			InputStream is = new ByteArrayInputStream(b, 0, b.length);
			RequestEntity re = new InputStreamRequestEntity(is, b.length, "text/xml; charset=utf-8");
			PostMethod postMethod = new PostMethod(
					CRMURL + "=GetBasicInfo");
			postMethod.setRequestEntity(re);

			HttpClient httpClient = new HttpClient();
			int statusCode = httpClient.executeMethod(postMethod);
			String result = postMethod.getResponseBodyAsString();
			if (200==statusCode) {
				//因接口返回的xml不是标准的不能直接转map故要处理该xml
				result=result.replace("<ExtensionData />", "");
			/*	String beg="<idcardtype>",end="</adjuest_cause>";
				String noStr=result.substring(result.indexOf(beg), (result.lastIndexOf(end))+end.length());
				result=result.replace(noStr, "");*/
				result=result.substring(result.indexOf("<vipgrade>"), (result.lastIndexOf("</vipgrade>"))+"</vipgrade>".length());
				return ParseXmlUtil.XmlStringReader(result);
			}
			logger.info("CRM接口GetBasicInfo返回值:"+result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("CRM接口GetBasicInfo出错！ "+e.getMessage());
		}
		return null;
	}
	
	/**
	 * 会员等级列表 
	 * @param xmlData
	 * @return
	 */
	public static Map<String, List<Map<String, String>>> getGradeList(String xmlData){
		try {
			byte[] b = xmlData.getBytes("utf-8");
			InputStream is = new ByteArrayInputStream(b, 0, b.length);
			RequestEntity re = new InputStreamRequestEntity(is, b.length, "text/xml; charset=utf-8");
			PostMethod postMethod = new PostMethod(
					CRMURL + "=GetGradeList");
			postMethod.setRequestEntity(re);

			HttpClient httpClient = new HttpClient();
			int statusCode = httpClient.executeMethod(postMethod);
			String result = postMethod.getResponseBodyAsString();
			if (200==statusCode) {
				//因接口返回的xml不是标准的不能直接转map故要处理该xml
				result=result.replace("<ExtensionData />", "");
				result=result.substring(result.indexOf("<vipgrade>"), (result.lastIndexOf("</vipgrade>"))+"</vipgrade>".length());
				return ParseXmlUtil.XmlStringReader(result);
			}
			logger.info("CRM接口GetBasicInfo返回值:"+result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("CRM接口GetBasicInfo出错！ "+e.getMessage());
		}
		return null;
	}
	
	/**
	 * 获取销售信息封装成json传输
	 */
	public static Map<String, String> setGetVipDis(String xmlData) {
		Map<String, String> map = sendRequest(xmlData, "GetVipDis");
		return map;
	}
	
	/**
	 * 
	 * 传入xml发送httpclientq
	 * 
	 * @param data
	 * @return
	 */
	private static Map<String, String> sendRequest(String xmlData, String interfaceName) {
		try {
			byte[] b = xmlData.getBytes("utf-8");
			InputStream is = new ByteArrayInputStream(b, 0, b.length);
			RequestEntity re = new InputStreamRequestEntity(is, b.length, "text/xml; charset=utf-8");
			PostMethod postMethod = new PostMethod(
					// "http://111.85.167.70:1999/CRM_VIP_Proxy.asmx?wsdl");
					CRMURL + "=" + interfaceName);
			postMethod.setRequestEntity(re);

			HttpClient httpClient = new HttpClient();
			int statusCode = httpClient.executeMethod(postMethod);
			String result = postMethod.getResponseBodyAsString();

			Map<String, String> map = ParseXmlUtil.xml2Map(result);
			if (statusCode == 200) {
				System.out.println("成功");
			} else {
				System.out.println("失败" + statusCode);
			}
			logger.info("科传 "+interfaceName+"  "+result);
			return map;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("科传 "+interfaceName+"  "+e.getMessage());
		}
		return null;
	}

	/**
	 * 保存一笔销售记录
	 * 
	 * @param salesItemService
	 * 
	 * @return
	 */
	public static void saveSalesSummary(SalesSummaryService salesSummaryService, SalesItemService salesItemService,
			SalesTenderService salesTenderService) {
		try {
			if (upload == null || "false".equals(upload)) {
				return;
			}
			// 查询销售数据
			List<SalesSummary> salesList = salesSummaryService.list(false, 1);
			if (salesList != null && salesList.size() != 0) {
				@SuppressWarnings("unused")
				String esalesitemsStr = "";
				@SuppressWarnings("unused")
				String esalestendersStr = "";

				for (SalesSummary salesSummary : salesList) {
					esalesitemsStr="";
					esalestendersStr="";
					String txdocno = salesSummary.getTxdocno();
					// 销售单货品明细表数组
					List<SalesItem> sis = salesItemService.getXfItemByDocCode(txdocno);
					for (SalesItem si : sis) {

						esalesitemsStr += " <esalesitem>" + "  <lineno>" + cleanObject2Number(si.getLineno())
								+ "</lineno>" + "    <itemcode>" + cleanObject2Number(si.getPlu().getXfPlu())
								+ "</itemcode>" + "   <qty>" + cleanObject2Number(si.getQty()) + "</qty>"
								+ "   <discountamount>" + cleanObject2Number(si.getDiscountamount())
								+ "</discountamount>" + "    <netamount>" + cleanObject2Number(si.getNetamount())
								+ "</netamount>" + "    <bonusearn>" + cleanObject2Number(new BigDecimal(0.00))
								+ "</bonusearn>" + "     <extendparam />" + "     <salesitemremark />"
								+ "    </esalesitem>";
					}
					// 销售单付款明细表数组
					List<SalesTender> sts = salesTenderService.getSTSByTxdocno(txdocno);
					for (SalesTender st : sts) {
						esalestendersStr += "   <esalestender>" + "     <lineno>" + cleanObject2Number(st.getLineno())
								+ "</lineno>" + "     <tendercode>"
								+ cleanObject2Number(st.getTendercode().getXfTendercode()) + "</tendercode>"
								+ "     <payamount>" + cleanObject2Number(st.getPayamount()) + "</payamount>"
								+ "     <baseamount>" + cleanObject2Number(st.getBaseamount()) + "</baseamount>"
								+ "     <excessamount>" + cleanObject2Number(st.getExcessamount()) + "</excessamount>"
								+ "     <extendparam />" + "     <remark />" + "   </esalestender>";
					}
					//针对鸿通城销售单号过长截取-------------------------------------------
					String substring;
					if (txdocno.length()>30) {
						substring = txdocno.substring((txdocno.length())-30);
					}else{
						substring=txdocno;
					}
					
					String xmlData = "<?xml version=\"1.0\" encoding=\"utf-16\"?>"
							+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"
							+ "<soap:Body>" + "  <postesalescreate xmlns=\"http://tempurl.org\">" + "   <astr_request>"
							+ "     <header>" + "       <licensekey />" + "      <username />" + "    <password />"
							+ "       <lang />" + "       <pagerecords>1</pagerecords>" + "       <pageno>1</pageno>"
							+ "       <updatecount>1</updatecount>" + "       <messagetype>SALESDATA</messagetype>"
							+ "     <messageid>332</messageid>" + "      <version>V332M</version>" + "    </header>"
							+ "     <esalestotal>" + "        <txdate_yyyymmdd>"
							+ new SimpleDateFormat("yyyyMMdd").format(salesSummary.getTxdate()) + "</txdate_yyyymmdd>"
							+ "       <txtime_hhmmss>" + new SimpleDateFormat("HHmmss").format(salesSummary.getTxtime())
							+ "</txtime_hhmmss>" + "       <mallid>" + salesSummary.getMallid().getXfMallid()
							+ "</mallid>" + "       <storecode>" + salesSummary.getStorecode().getXfStorecode()
							+ "</storecode>" + "       <tillid>" + salesSummary.getTillid() + "</tillid>"
							+ "      <txdocno>" + substring/*salesSummary.getTxdocno()*/ + "</txdocno>" + "       <cashier>"
							+ salesSummary.getStaffcode().getXfStaffcode()+ "</cashier>" + "       <vipcode />"
							+ "      <salesman>"+(salesSummary.getStorecode().getXfStorecode()
							.equals(salesSummary.getCashStorecode().getXfStorecode())?"0":"1")+"</salesman>" + "       <netqty>" + salesSummary.getNetqty() + "</netqty>"
							+ "       <netamount>" + salesSummary.getNetamount() + "</netamount>"
							+ "      <extendparam />" + "    </esalestotal>" + "<esalesitems>" + esalesitemsStr
							+ "</esalesitems>" + "<esalestenders>" + esalestendersStr + "  </esalestenders>"
							+ "  </astr_request>" + "</postesalescreate>" + "</soap:Body>" + "</soap:Envelope>";

					byte[] b = xmlData.getBytes("utf-8");
					InputStream is = new ByteArrayInputStream(b, 0, b.length);
					RequestEntity re = new InputStreamRequestEntity(is, b.length, "text/xml; charset=utf-8");
					PostMethod postMethod = new PostMethod(
							// "http://111.85.167.70:1999/CRM_VIP_Proxy.asmx?wsdl");
							OLDMISURL + "=postesalescreate");
					postMethod.setRequestEntity(re);

					HttpClient httpClient = new HttpClient();
					int statusCode = httpClient.executeMethod(postMethod);
					String result = postMethod.getResponseBodyAsString();

					Map<String, String> map = ParseXmlUtil.xml2Map(result);
					if (statusCode == 200) {
						if ("0".equals(map.get("responsecode"))) {
							System.out.println("成功");
							salesSummary.setTransferEspos(true);
							salesSummaryService.updateSalesSummaryState(salesSummary);
						}else{
							logger.error("销售上传到332 "+result);
						}

					} else {
						System.out.println("失败" + statusCode);
						logger.error("销售上传到332 "+result);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("上传到老MIS 销售数据"+e.getMessage());
		}
	}
}