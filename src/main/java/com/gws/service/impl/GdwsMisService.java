package com.gws.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.gws.util.CrmHttpClientUtil;
import com.gws.util.GdwsConfig;
import com.smw.utils.MD5;

@Service
public class GdwsMisService {
	private int SocketTime=GdwsConfig.MisSocketTime;
	private int ConnectTime=GdwsConfig.MisConnectTime;
	private int requestTime=GdwsConfig.MisRequsetTime;
	private Logger log=LoggerFactory.getLogger(GdwsMisService.class);
	public Object loginMis(String account,String password){
		Map<String, String> maps=new HashMap<>();
		maps.put("account", account);
		maps.put("password",MD5.getMD5(password));
		System.out.println(account+"=====>"+password);
		String result=CrmHttpClientUtil.getInstance(SocketTime, ConnectTime, requestTime).sendHttpPost(GdwsConfig.MisLoginUrl, maps);
		System.out.println(GdwsConfig.MisLoginUrl);
		System.out.println(result);
		System.out.println("登录Mis返回的结果"+result);
		log.info("登录Mis返回的结果"+result);
		return JSONObject.parse(result);
	}
	public String GetToken(){
		JSONObject json=(JSONObject) loginMis(GdwsConfig.MisAccount, GdwsConfig.MisPassword);
		String token=json.getString("data");
		return token;
	}
	
	public Object getMisTender(String mallInfoCode){
		Map<String, String> maps=new HashMap<>();
		maps.put("token", GetToken());
		maps.put("mallInfoCode", GdwsConfig.MisSynch);
		String result=CrmHttpClientUtil.getInstance(SocketTime, ConnectTime, requestTime).sendHttpPost(GdwsConfig.MisGetPaymentRent, maps);
		System.out.println("获取支付方式返回的结果"+result);
		log.info("获取支付方式返回的结果"+result);
		return JSONObject.parse(result);
	}

	public Object getMisValidStore(){
		Map<String, String> maps=new HashMap<>();
		maps.put("token", GetToken());
		String result=CrmHttpClientUtil.getInstance(SocketTime, ConnectTime, requestTime).sendHttpPost(GdwsConfig.MisGetValidStore, maps);
		System.out.println("获取店铺返回的结果"+result);
		log.info("获取店铺返回的结果"+result);
		return JSONObject.parse(result);
	}
	
	public Object getMisItemByStoreCode(String shopsLeaseCode){
		Map<String, String> maps=new HashMap<>();
		maps.put("token", GetToken());
		maps.put("shopsLeaseCode", shopsLeaseCode);
		String result=CrmHttpClientUtil.getInstance(SocketTime, ConnectTime, requestTime).sendHttpPost(GdwsConfig.MisStore_Item, maps);
		System.out.println("获取"+shopsLeaseCode+"店铺下商品返回的结果:"+result);
		log.info("获取"+shopsLeaseCode+"店铺下商品返回的结果:"+result);
		return JSONObject.parse(result);
	}
	public Object uploadTransInfo(String DATA){
		log.info("上传销售数据传递的数据:"+DATA);
		Map<String, String> maps=new HashMap<>();
		maps.put("token", GetToken());
		maps.put("DATA", DATA);
		String result=CrmHttpClientUtil.getInstance(SocketTime, ConnectTime, requestTime).sendHttpPost(GdwsConfig.MisUploadTrans, maps);
		log.info("上传销售数据返回的结果:"+result);
		return JSONObject.parse(result);
	}
	
	/**获取支付方式手续费率**/
	public String getPaymentRent(){
		Map<String, String> maps=new HashMap<>();
		maps.put("token", GetToken());
		String result=CrmHttpClientUtil.getInstance(SocketTime, ConnectTime, requestTime).sendHttpPost(GdwsConfig.MisGetPaymentRent, maps);
		log.info("获取返回的结果"+result);
		return result;
	}
	/**
	 * 获取业态信息
	 */
	public Object getFormatInfo(){
		Map<String, String> maps=new HashMap<>();
		maps.put("token", GetToken());
		String result=CrmHttpClientUtil.getInstance(SocketTime, ConnectTime, requestTime).sendHttpPost(GdwsConfig.GetFormatInfo, maps);
		log.info("获取返回的结果"+result);
		return JSONObject.parse(result);
	}
	
	public Object getRate(String contractCode){
		Map<String, String> maps=new HashMap<>();
		maps.put("token", GetToken());
		maps.put("contractCode", contractCode);
		String result=CrmHttpClientUtil.getInstance(SocketTime, ConnectTime, requestTime).sendHttpPost(GdwsConfig.MisGetSalesReturnDate, maps);
		System.out.println("获取支付方式返回的结果"+result);
		log.info("获取手续费率返回的结果"+result);
		return JSONObject.parse(result);
	}
	/**
	 * 获取楼宇信息
	 */
	public Object getBuildingInfo(){
		Map<String, String> maps=new HashMap<>();
		maps.put("token", GetToken());
		String result=CrmHttpClientUtil.getInstance(SocketTime, ConnectTime, requestTime).sendHttpPost(GdwsConfig.GetBuildingInfo, maps);
		log.info("获取返回的结果"+result);
		return JSONObject.parse(result);
	}
	
	/**获取收银机信息**/
	public Object getTillids(){
		Map<String, String> maps=new HashMap<>();
		maps.put("token", GetToken());
		String result=CrmHttpClientUtil.getInstance(SocketTime, ConnectTime, requestTime).sendHttpPost(GdwsConfig.GetMISTillids, maps);
		log.info("获取返回的结果"+result);
		return JSONObject.parse(result);
	}
	
	/**获取收银机关联的店铺**/
	public Object getTillid_Store(){
		Map<String, String> maps=new HashMap<>();
		maps.put("token", GetToken());
		String result=CrmHttpClientUtil.getInstance(SocketTime, ConnectTime, requestTime).sendHttpPost(GdwsConfig.GetMISTillid_Store, maps);
		log.info("获取返回的结果"+result);
		return JSONObject.parse(result);
	}
	

	
	public static void main(String[] args) {
		GdwsMisService g=new GdwsMisService();
		g.GetToken();
		g.getTillids();
		//g.getBuildingInfo();
		//g.getTillid_Store();
	}
	
	
	
	
}
