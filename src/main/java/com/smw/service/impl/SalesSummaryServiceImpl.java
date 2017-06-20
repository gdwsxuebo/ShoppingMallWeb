package com.smw.service.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gws.service.impl.CrmService;
import com.smw.common.util.DateUtil;
import com.smw.common.util.PropertiesUtil;
import com.smw.common.util.StringUtil;
import com.smw.dao.SalesSummaryDao;
import com.smw.pojo.PromotionStamp;
import com.smw.pojo.SalesItem;
import com.smw.pojo.SalesSummary;
import com.smw.pojo.SalesTender;
import com.smw.service.PromotionStampService;
import com.smw.service.SalesItemService;
import com.smw.service.SalesSummaryService;
import com.smw.service.SalesTenderService;
import com.smw.service.XfItemService;
import com.smw.service.XfMallService;
import com.smw.service.XfStaffService;
import com.smw.service.XfStoreService;
import com.smw.service.XfTenderService;
import com.smw.utils.MD5;
import com.smw.web.JoinOLDMis;

/**
 * 销售汇总服务层实现接口
 * 
 * @author suen
 * @date 2016年5月19日-下午3:39:14
 * @version 1.0
 */
@Service
public class SalesSummaryServiceImpl implements SalesSummaryService {
	/**
	 * crm秘钥
	 */
	private static String crmMiyao;
	/**
	 * crm用户
	 */
	private static String crmUser;

	static {
		PropertiesUtil p = new PropertiesUtil("systemConfig.properties");
		crmMiyao = p.readProperty("crmMiyao");
		crmUser = p.readProperty("crmUser");
	}
	@Resource
	private SalesSummaryDao salesSummaryDao;
	@Resource CrmService crmService;//GDWS CRM

	@Override
	public List<SalesSummary> selectSalesSummaryByXfStorecodeAndDate(Map<String, Object> map) {
		return salesSummaryDao.selectSalesSummaryByXfStorecodeAndDate(map);
	}

	/**
	 * 保持销售记录
	 * 
	 * @param salesItemService
	 *            销售单货品明细服务层
	 * @param salesTenderService
	 *            销售单付款服务层
	 * @param promotionStampService
	 *            促销劵
	 * @param data
	 *            数据
	 * @return
	 */
	@Override
	public Map saveSalesSummary(XfMallService xfMallService, XfStoreService xfStoreService,
			XfStaffService xfStaffService, XfItemService xfItemService, XfTenderService xfTenderService,
			SalesItemService salesItemService, SalesTenderService salesTenderService,
			PromotionStampService promotionStampService, String data) {
		
		JSONObject miBySales=null;
		JSONObject parseObject = JSON.parseObject(data);
		// 销售单号
		String txdocno = parseObject.getString("txdocno");
		String originalTxdocno=parseObject.getString("originalTxdocno");
		if(!StringUtil.isNUllStr(originalTxdocno)){
			salesSummaryDao.saveRefundOrder(originalTxdocno);	
		}
		
		// 根据销售单号查询是否已有，已有表示已上传了,则不需要保存上传了
		SalesSummary salesSummary = salesSummaryDao.getSSByTxdocno(txdocno);
		if (salesSummary != null) {
			//如果标示是不为上传则删除原来数据，保存新数据，否则不操作
			if(!"1".equals(salesSummary.getIsUpload())){
				//删除商品明细
				salesItemService.deleteSIBySSTXD(txdocno);
				//删除支付方式
				salesTenderService.deleteSTBySSTXD(txdocno);
				//删除销售数据
				salesSummaryDao.deleteSSByTXD(txdocno);
			}else{
				return null;
			}
		}
		
		salesSummary = new SalesSummary();
		// 商场编号
		String mallid = parseObject.getString("mallid");
		// 商场
		salesSummary.setMallid(xfMallService.selectByStrId(mallid));
		// 店铺号
		String storecode = parseObject.getString("storecode");
		// 店铺
		salesSummary.setStorecode(xfStoreService.getXfStoreByStoreCode(storecode));
		// 收银店铺号
		String cashStorecode = parseObject.getString("cashStorecode");
		// 店铺
		salesSummary.setCashStorecode(xfStoreService.getXfStoreByStoreCode(cashStorecode));
		// 收银机号
		String tillid = parseObject.getString("tillid");
		salesSummary.setTillid(tillid);
		// 销售员
		String salesman = parseObject.getString("salesman");
		try {
			salesSummary.setSalesman(URLDecoder.decode(salesman, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			salesSummary.setSalesman(salesman);
			e1.printStackTrace();
		}
		// 员工号
		String staffcode = parseObject.getString("staffcode");
		salesSummary.setStaffcode(xfStaffService.getXfStaffByStaffCode(staffcode));
		// 销售单号
		// String txdocno = parseObject.getString("txdocno");
		salesSummary.setTxdocno(txdocno);
		// 销售总数量
		String netqty = parseObject.getString("netqty");
		salesSummary.setNetqty(new BigDecimal(netqty));
		// 销售净金额(退货时取负值)
		String netamount = parseObject.getString("netamount");
		salesSummary.setNetamount(new BigDecimal(netamount));
		
		// 超额金额
		String change = parseObject.getString("change");
		salesSummary.setChangemount(new BigDecimal(change));
		
		// 交易日期
		String txdate = parseObject.getString("txdate");
		String txtime = parseObject.getString("txtime");
		Date txtdateD=	DateUtil.getDate(txdate, "yyyyMMdd");
		Time	txtimeD=DateUtil.getSqlTime(txtime, "HH:mm:ss");
		salesSummary.setTxdate(DateUtil.newSalesRule(txtdateD, txtimeD));
		// 交易时间
		salesSummary.setTxtime(DateUtil.getSqlTime(txtime, "HH:mm:ss"));
		// 会员号(外键：MIS系统)
		String vipcode = parseObject.getString("vipcode");
		salesSummary.setVipcode(vipcode);
		// 退货销售单号
		if (!StringUtil.isNUllStr(originalTxdocno)) {
			salesSummary.setOriginalTxdocno(originalTxdocno);salesSummary.setState("1");
			//退货员工
			salesSummary.setRefundStaff(parseObject.getString("refundStaff"));
		}
		String stsObject = parseObject.getString("sts");
		JSONArray ja = JSON.parseArray(stsObject);
		Iterator<Object> it = ja.iterator();
		 while (it.hasNext()) {
             JSONObject ob = (JSONObject) it.next();
             String promotionStampId = ob.getString("transMemo");
            if("PC".equals(ob.getString("tendercode")) && !StringUtil.isNUllStr(promotionStampId)){
            	 // 查询促销劵
            	 PromotionStamp promotionStamp = promotionStampService.getPSById(promotionStampId);
            	 if (promotionStamp != null) {
            		 if (!promotionStamp.getIsUse()) {
            			 // 已使用
            			 promotionStamp.setIsUse(true);
            			 // 更新
            			 promotionStampService.saveOrUpdatePS(promotionStamp);
            		 }
            		 
            	 }
             }
		 }
		 
		
		// 翼支付支付码
		String barcode = parseObject.getString("barcode");
		if (!StringUtil.isNUllStr(barcode)) {
			salesSummary.setBarcode(barcode);
		}
		// 是否要上传
		String isUpload = parseObject.getString("isUpload");
		if (!StringUtil.isNUllStr(isUpload)) {
			salesSummary.setIsUpload(isUpload);
		}
		
		String isSalesReturn   = parseObject.getString("isSalesReturn");
		if (!StringUtil.isNUllStr(isSalesReturn)) {
			salesSummary.setIsSalesReturn(isSalesReturn);;
		}
		if ("1".equals(isUpload) && !StringUtil.isNUllStr(vipcode)) {
		if(StringUtil.isNUllStr(originalTxdocno))	
			try { 
				miBySales=(JSONObject) crmService.getMiBySalesService(data);
				if(miBySales!=null) salesSummary.setBonusearn(((JSONObject) miBySales).getJSONObject("data").getIntValue("theIntegral"));//本次销售获取的积分
				miBySales.put("flag", "1");
			} catch (Exception e) {
			e.printStackTrace();miBySales.put("flag", "0");
			System.out.println("从CRM获取积分异常---销售");
			}
		else{
			try { 
				miBySales=(JSONObject) crmService.getDeMiBySalesService(originalTxdocno);
				if(miBySales!=null) salesSummary.setBonusearn(((JSONObject) miBySales).getJSONObject("data").getIntValue("theIntegral"));//本次销售获取的积分
				miBySales.put("flag", "1");
			} catch (Exception e) {
			e.printStackTrace();miBySales.put("flag", "0");
			System.out.println("从CRM获取积分异常---退货");
			}
		}	
		}

		// 保持销售记录
		salesSummaryDao.saveSalesSummary(salesSummary);
		
		// 销售净金额(退货时取负值)
		JSONArray sts = parseObject.getJSONArray("sts");
		// 销售单货品明细集合
		List<SalesItem> salesItems = new ArrayList<>();
		SalesItem salesItem;
		JSONArray sis = parseObject.getJSONArray("sis");
		int i=0;
		for (Object object : sis) {

			parseObject = (JSONObject) object;
			salesItem = new SalesItem();
			// 销售单号
			salesItem.setTxdocno(salesSummary);
			String lineno= parseObject.getString("lineno");
			if("0".equals(lineno)) lineno=String.valueOf(i);i++;
			// 行号
			salesItem.setLineno(lineno);
			// 商品编号
			salesItem.setPlu(xfItemService.getXfItem(parseObject.getString("plu")));
			// 数量(退货时取负值)
			salesItem.setQty(new BigDecimal(parseObject.getString("qty")));
			// 净金额(退货时取负值)
			salesItem.setNetamount(new BigDecimal(parseObject.getString("netamount")));
			// 折扣金额(退货时取负值)
			salesItem.setDiscountamount(new BigDecimal(parseObject.getString("discountamoun")));
			// 获得积分(退货时取负值)
			salesItem.setBonusearn(new BigDecimal(parseObject.getString("bonusearn")));
			//商品重量weight
			if(!StringUtil.isNUllStr(parseObject.getString("weight"))){
				salesItem.setWeight(new BigDecimal(parseObject.getString("weight")));
			}else{
				salesItem.setWeight(new BigDecimal(0));
			}
			
			
			// 保存
			salesItemService.saveSalesItem(salesItem);
			salesItems.add(salesItem);
		}
		// 销售单
		List<SalesTender> tenders = new ArrayList<>();
		SalesTender salesTender;
		String tenderStr = "";
		for (Object object : sts) {
			parseObject = (JSONObject) object;
			// 销售单
			salesTender = new SalesTender();
			// 销售单号
			salesTender.setTxdocno(salesSummary);
			// 行号
			salesTender.setLineno(parseObject.getString("lineno"));
			// 付款方式编码
			salesTender.setTendercode(xfTenderService.getXfTenderByCode(parseObject.getString("tendercode")));
			// 付款金额(退货时取负值)
			salesTender.setPayamount(new BigDecimal(parseObject.getString("payamount")));
			// 本位币金额,即人民币金额，同xf_payamount(退货时取负值)
			salesTender.setBaseamount(new BigDecimal(parseObject.getString("baseamount")));
			// 超额金额,保留Default:
			// 0(退货时取负值,用于记录现金券等不能找补的付款方式,例如：应付100元，而现金券是120，那么超额金额就是120-100=20)
			salesTender.setExcessamount(new BigDecimal(parseObject.getString("excessamount")));
			//交易附加域,主要用于银行卡支付保存信息，格式为：“时间&凭证号&系统参考号”，
			salesTender.setTransMemo(parseObject.getString("transMemo"));
			//支付的账户号,没有则为空
			salesTender.setAccountNo(parseObject.getString("accountNo"));
			if(!StringUtil.isEmpty(parseObject.getString("GwPaymentRate"))) 
				salesTender.setGwPaymentRate(parseObject.getString("GwPaymentRate"));
			// 保存
			salesTenderService.saveSalesTender(salesTender);
			tenders.add(salesTender);
		}
		salesSummary.setSis(salesItems);
		salesSummary.setSts(tenders);	
		if(miBySales==null)miBySales=new JSONObject();
		miBySales.put("sflag", "1");
		return miBySales;
	}

	/**
	 * 把一个空串返回成0
	 * 
	 * @param val
	 * @return
	 */
	public static Object cleanObject2Number(Object val) {
		if (val == null || val.equals("")) {
			return 0;
		} else {
			return val.toString();
		}
	}

	/**
	 * 获取销售记录列表
	 * 
	 * @param transferEspos
	 * @param type
	 *            类型 1老MIS 2新MIS
	 * @return
	 */
	@Override
	public List<SalesSummary> list(Boolean transferEspos, int type) {
		return salesSummaryDao.list(transferEspos, type);
	}

	/**
	 * 销售记录总数
	 * 
	 * @param storeCode
	 *            店铺号
	 * @param key
	 *            搜索条件
	 * @return
	 */
	@Override
	public Integer getCount(String storeCode, String key) {
		return salesSummaryDao.getCount(storeCode, key);
	}

	/**
	 * 获取销售记录集合
	 * 
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            每页显示的行数
	 * @param storeCode
	 *            店铺号
	 * @param key
	 *            搜索条件
	 * @return
	 */
	@Override
	public List<SalesSummary> getSSList(Integer pageIndex, Integer pageSize, String storeCode, String key) {
		return salesSummaryDao.getSSList(pageIndex, pageSize, storeCode, key);
	}

	/**
	 * 保存或者修改销售记录
	 * 
	 * @param ss
	 */
	@Override
	public void updateSalesSummaryState(SalesSummary ss) {
		salesSummaryDao.updateSalesSummaryState(ss);
	}

	/**
	 * 根据销售单号删除销售数据
	 * 
	 * @param txdocno
	 *            销售单号
	 */
	@Override
	public void deleteSSByTXD(String txdocno) {
		salesSummaryDao.deleteSSByTXD(txdocno);
	}

	/**
	 * 根据销售单号查询销售数据
	 * 
	 * @param txdocno
	 *            销售单号
	 * @return
	 */
	@Override
	public SalesSummary getSSByTxd(String txdocno) {
		return salesSummaryDao.getSSByTxd(txdocno);
	}

	/**
	 * 根据原销售单号查询销售信息
	 * @param txdocno 原销售销售单号
	 * @return
	 */
	@Override
	public SalesSummary getSSByOriginalTxdocno(String txdocno) {
		return salesSummaryDao.getSSByOriginalTxdocno(txdocno);
	}

	@Override
	public SalesSummary getEnalbleSSByTxd(String txdocno) {

		return salesSummaryDao.getEnalbleSSByTxd(txdocno);
	}

	/**
	 * 根据日期获取销售总金额
	 */
	public String getMoneyByDate(String date) {
		return salesSummaryDao.getMoneyByDate(date);
	}
	
	/**
	 * 清空week_sale_money表
	 */
	public void delWeekSaleMoney(){
		salesSummaryDao.delWeekSaleMoney();
	}

	@Override
	public void saveSaleSummary(SalesSummary salesSummary) {
		salesSummaryDao.saveSalesSummary(salesSummary);
		
	}

	@Override
	public SalesSummary getSummaryBycode(String txdocno) {
		// TODO Auto-generated method stub
		return salesSummaryDao.getSummaryBycode(txdocno);
	}
	
}
