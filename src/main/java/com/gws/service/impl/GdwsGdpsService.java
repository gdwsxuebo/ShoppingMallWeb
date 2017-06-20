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
public class GdwsGdpsService {
	
	private int SocketTime=GdwsConfig.GdpsSocketTime;
	private int ConnectTime=GdwsConfig.GdpsConnectTime;
	private int requestTime=GdwsConfig.GdpsRequestTime;
	private Logger log=LoggerFactory.getLogger(GdwsGdpsService.class);
	public Object loginMis(String account,String password){
		Map<String, String> maps=new HashMap<>();
		maps.put("account", account);
		maps.put("password",MD5.getMD5(password));
		System.out.println(account+"=====>"+password);
		String result=CrmHttpClientUtil.getInstance(SocketTime, ConnectTime, requestTime).sendHttpPost(GdwsConfig.GdpsLoginUrl, maps);
		System.out.println(GdwsConfig.GdpsUrl);
		System.out.println(result);
		System.out.println("登录Gdps返回的结果"+result);
		log.info("登录Gdps返回的结果"+result);
		return JSONObject.parse(result);
	}
	public String GetToken(){
		JSONObject json=(JSONObject) loginMis(GdwsConfig.GdpsAccount, GdwsConfig.GdpsPassword);
		String token=json.getString("data");
		return token;
	}
	
	/*获取优惠券*/
	public Object getGdpsCoupon(String memeberCode,String money,String paymenttender,String shopBm,String promotiontymode){
		Map<String, String> maps=new HashMap<>();
		maps.put("token", GetToken());
		maps.put("openId", GdwsConfig.GdpsEncoding);
		maps.put("memeberCode", memeberCode);
		maps.put("money", money);
		maps.put("paymenttender", paymenttender);
		maps.put("shopBm", shopBm);
		maps.put("promotiontymode", promotiontymode);
		String result=CrmHttpClientUtil.getInstance(SocketTime, ConnectTime, requestTime).sendHttpPost(GdwsConfig.GdpsGetCouponUrl, maps);
		System.out.println("获取优惠券返回的结果"+result);
		log.info("获取优惠券返回的结果"+result);
		return JSONObject.parse(result);
	}
	
	/*验证优惠券*/
	public Object getGdpsCheckCoupon(String memeberCode,String money,String code,String shopBm){
		Map<String, String> maps=new HashMap<>();
		maps.put("token", GetToken());
		maps.put("openId", GdwsConfig.GdpsEncoding);
		maps.put("memeberCode", memeberCode);
		maps.put("money", money);
		maps.put("code", code);
		maps.put("shopBm", shopBm);
		String result=CrmHttpClientUtil.getInstance(SocketTime, ConnectTime, requestTime).sendHttpPost(GdwsConfig.GdpsCheckCouponUrl, maps);
		System.out.println("验证优惠券返回的结果"+result);
		log.info("验证优惠券返回的结果"+result);
		return JSONObject.parse(result);
	}
	
	/*获取图片BASE64编码*/
	public Object getGdpsImageBase(String code){
		Map<String, String> maps=new HashMap<>();
		maps.put("token", GetToken());
		maps.put("openId", GdwsConfig.GdpsEncoding);
		maps.put("code", code);
		String result=CrmHttpClientUtil.getInstance(SocketTime, ConnectTime, requestTime).sendHttpPost(GdwsConfig.GetImageBase64, maps);
		System.out.println(GdwsConfig.GetImageBase64);
		System.out.println("获取BASE64返回的结果"+result);
		log.info("获取BASE64返回的结果"+result);
		return JSONObject.parse(result);
	}
	
	/*验证平台促销券*/
	public Object getPromotionCheck(String code){
		Map<String, String> maps=new HashMap<>();
		maps.put("token", GetToken());
		maps.put("openId", GdwsConfig.GdpsEncoding);
		maps.put("codes", code);
		maps.put("memeberCode", "");
		String result=CrmHttpClientUtil.getInstance(SocketTime, ConnectTime, requestTime).sendHttpPost(GdwsConfig.CheckPromotionCodes, maps);
		System.out.println(GdwsConfig.CheckPromotionCodes);
		System.out.println("平台促销验证平台返回的结果"+result);
		log.info("平台促销验证平台返回的结果"+result);
		return JSONObject.parse(result);
	}
	
	/*验证平台普通礼品券*/
	public Object getPromotionCheckPtCode(String code){
		Map<String, String> maps=new HashMap<>();
		maps.put("token", GetToken());
		maps.put("openId", GdwsConfig.GdpsEncoding);
		maps.put("code", code);
		maps.put("memeberCode", "");
		String result=CrmHttpClientUtil.getInstance(SocketTime, ConnectTime, requestTime).sendHttpPost(GdwsConfig.CheckPromotionCodesPtCode, maps);
		System.out.println(GdwsConfig.CheckPromotionCodesPtCode);
		System.out.println("平台促销验证普通礼品券返回的结果"+result);
		log.info("平台促销验证普通礼品券返回的结果"+result);
		return JSONObject.parse(result);
	}
	
	

}
