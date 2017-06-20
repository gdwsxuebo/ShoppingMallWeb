package com.gws.service.impl;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gws.util.GdwsConfig;
import com.gws.util.CrmHttpClientUtil;
import com.gws.util.StringUtil;

@Service
public class CrmService {
	private int SocketTime=GdwsConfig.CrmSocketTime;
	private int ConnectTime=GdwsConfig.CrmConnectTime;
	private int requestTime=GdwsConfig.CrmrequestTime;
	private Logger log=LoggerFactory.getLogger(CrmService.class);
	public Object loginCRM(String account){
		System.out.println("登录CRM");
		Map<String, String> maps=new HashMap<>();
		maps.put("account", account);
		String result=CrmHttpClientUtil.getInstance(SocketTime, ConnectTime, requestTime).sendHttpPost(GdwsConfig.loginUrl, maps);
		System.out.println("登录CRM返回的结果"+result);
		log.info("登录CRM返回的结果"+result);
		return JSONObject.parse(result);
	}
/*	public Object getMiByVipCode(String vipCode){
		Object login=loginCRM(GdwsConfig.account);
		if(login==null) return null;
		JSONObject loginObj=(JSONObject) login;
		String token=loginObj.getJSONObject("data").getString("token");
		System.out.println("通过VIP号获取会员信息");
		Map<String, String> maps=new HashMap<>();
		maps.put("account", GdwsConfig.account);
		maps.put("token", token);
		maps.put("vipCode",vipCode);
		String result=CrmHttpClientUtil.getInstance(SocketTime, ConnectTime, requestTime).sendHttpPost(GdwsConfig.vipInfoByVipCode, maps);
		System.out.println("通过VIP号"+vipCode+"获取的结果为"+result);
		log.info("通过VIP号"+vipCode+"获取的结果为"+result);
		return JSONObject.parse(result);
	}
	*/
	public Object getMiByPhone(String phone){
		Object login=loginCRM(GdwsConfig.account);
		if(login==null) return null;
		JSONObject loginObj=(JSONObject) login;
		String token=loginObj.getJSONObject("data").getString("token");
		System.out.println("通过手机号获取会员信息");
		Map<String, String> maps=new HashMap<>();
		maps.put("account", GdwsConfig.account);
		maps.put("token", token);
		maps.put("phone",phone);
		String result=CrmHttpClientUtil.getInstance(SocketTime, ConnectTime, requestTime).sendHttpPost(GdwsConfig.MIByPhone, maps);
		System.out.println("通过手机号"+phone+"获取的结果为"+result);
		log.info("通过手机号"+phone+"获取的结果为"+result);
		return JSONObject.parse(result);
	}
	
	public Object MIByCardCodeOrVipCode(String cardCode, boolean flag){
		Object login=loginCRM(GdwsConfig.account);
		if(login==null) return null;
		JSONObject loginObj=(JSONObject) login;
		String token=loginObj.getJSONObject("data").getString("token");
		System.out.println("通过卡号或会员号信息");
		Map<String, String> maps=new HashMap<>();
		maps.put("account", GdwsConfig.account);
		maps.put("token", token);
		if(flag) maps.put("data","{\"vipCode\":\""+cardCode+"\"}");
		else maps.put("data","{\"cardCode\":\""+cardCode+"\"}");
		String result=CrmHttpClientUtil.getInstance(SocketTime, ConnectTime, requestTime).sendHttpPost(GdwsConfig.MIByCardCodeOrVipCode, maps);
		System.out.println("通过卡号或会员号信息"+cardCode+"获取的结果为"+result);
		log.info("通过卡号或会员号信息"+cardCode+"获取的结果为"+result);
		return JSONObject.parse(result);
	}
	public Object getMiBySales(String data){
		Object login=loginCRM(GdwsConfig.account);
		if(login==null) return null;
		JSONObject loginObj=(JSONObject) login;
		String token=loginObj.getJSONObject("data").getString("token");
		System.out.println("获取销售数据积分");
		Map<String, String> maps=new HashMap<>();
		maps.put("account", GdwsConfig.account);
		maps.put("token", token);
		maps.put("data",data);
		String result=CrmHttpClientUtil.getInstance(SocketTime, ConnectTime, requestTime).sendHttpPost(GdwsConfig.MiSales, maps);
		System.out.println("获取销售数据积分"+data+"获取的结果为"+result);
		log.info("获取销售数据积分"+data+"获取的结果为"+result);
		return JSONObject.parse(result);
	}
	
	public Object getVipInfoService(String vipCode){
		if(vipCode.matches(GdwsConfig.phoneRule)) return getMiByPhone(vipCode);
	    else if(vipCode.matches(GdwsConfig.vipRule)) return  MIByCardCodeOrVipCode(vipCode,true);
	    else if(vipCode.matches(GdwsConfig.cardRule)) return  MIByCardCodeOrVipCode(vipCode,false);	
		else return null;
	}
	public static void main(String[] args) {
		String vipCode = "510100201612210002";
		boolean s = vipCode.matches(GdwsConfig.vipRule);
		System.out.println(s);
	}
	
	public Object getMiBySalesService(String data){
		JSONObject posToMis=new JSONObject();
		JSONObject etcToPos=JSONObject.parseObject(data);
		String gwTxserial=etcToPos.get("txdocno").toString();posToMis.put("gwTxserial",gwTxserial);//销售单号
		String gwSalesnumber=etcToPos.getString("netqty");posToMis.put("gwSalesnumber", gwSalesnumber);//销售总数量
		String gwSalesamount=etcToPos.getString("netamount");posToMis.put("gwSalesamount", new BigDecimal(gwSalesamount));//销售总金额
		String gwTxdate=etcToPos.getString("txdate");posToMis.put("gwTxdate", StringUtil.formatData(gwTxdate));//交易日期
		String gwTxtime=etcToPos.getString("txtime");posToMis.put("gwTxtime", gwTxtime);//交易时间
		String gwTillid=etcToPos.getString("tillid");posToMis.put("gwTillid", gwTillid);//收银机编号
		String gwMemberCode=etcToPos.getString("vipcode");posToMis.put("gwMemberCode", gwMemberCode);//vip编号
		String gwStoreCode = etcToPos.getString("storecode");posToMis.put("gwStoreCode", gwStoreCode);//店铺号
		//支付方式 待定
		JSONArray payFromEetc=etcToPos.getJSONArray("sts");
		JSONArray toMisPays=new JSONArray();
		for(Object pay:payFromEetc){
			JSONObject payObj=(JSONObject) pay;
			JSONObject toMisPay=new JSONObject();
			toMisPay.put("code", payObj.get("tendercode"));//支付方式
			toMisPay.put("state", 1);//是否需要积分（0-否，1-是）
			toMisPay.put("payMoney", new BigDecimal(payObj.getString("baseamount")));//付款金额
			toMisPays.add(toMisPay);
		}
		posToMis.put("gwPaymenttenderCode", toMisPays);//待格式化处理_销售数据明细
		return getMiBySales(posToMis.toJSONString());
	}
	
	public Object getDeMiBySalesService(String originalTxdocno){

		Object login=loginCRM(GdwsConfig.account);
		if(login==null) return null;
		JSONObject loginObj=(JSONObject) login;
		String token=loginObj.getJSONObject("data").getString("token");
		System.out.println("获取销售数据扣积分"+originalTxdocno);
		Map<String, String> maps=new HashMap<>();
		maps.put("account", GdwsConfig.account);
		maps.put("token", token);
		maps.put("gwTxserial",originalTxdocno);
		String result=CrmHttpClientUtil.getInstance(SocketTime, ConnectTime, requestTime).sendHttpPost(GdwsConfig.DeMiSales, maps);
		System.out.println("销售扣除的积分返回的结果"+result);
		return JSONObject.parse(result);
	}
}
