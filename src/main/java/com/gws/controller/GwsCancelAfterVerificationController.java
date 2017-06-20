package com.gws.controller;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.gws.util.HttpClientUtil;
import com.gws.util.StringUtil;

/**
 * 核销
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/gws")
public class GwsCancelAfterVerificationController {

	private	Logger log=LoggerFactory.getLogger(GwsCancelAfterVerificationController.class);
	
	@RequestMapping("/getpassinfo")
	@ResponseBody
	public String getpassinfo(HttpServletRequest request){
		try {
			String cancelAfterVerificationUrl = StringUtil.getNsyhInfo("cancelAfterVerificationUrl");
			String	resultStr=	HttpClientUtil.getInstance().sendHttpPostParamMap(cancelAfterVerificationUrl+"/Getpassinfo", request.getParameterMap());
			resultStr=resultStr.replace("\\","");
			resultStr=	resultStr.substring(resultStr.indexOf("{"), resultStr.length());
			log.info("获取验证码返回的结果打印"+resultStr);
			return resultStr;
		} catch (Exception e) {
			JSONObject json=new JSONObject();
			json.put("errcode", 1);
			json.put("errmsg", "网络异常或者"+e.getMessage());
			log.error("获取验证码调用 getpassinfo失败",e);
			return json.toJSONString();
		}
	
	}
	
	@RequestMapping("/checkpass")
	@ResponseBody
	public String checkpass(HttpServletRequest request){
		try { 
			/*Map<String, String[]> map=request.getParameterMap();
			for(Entry<String, String[]> m: map.entrySet()){
				System.out.println(m.getKey()+"     "+m.getValue()[0]);
			}*/
			String cancelAfterVerificationUrl = StringUtil.getNsyhInfo("cancelAfterVerificationUrl");
			String	resultStr=	HttpClientUtil.getInstance().sendHttpPostParamMap(cancelAfterVerificationUrl+"Checkpass", request.getParameterMap());
			resultStr=resultStr.replace("\\","");
			resultStr=	resultStr.substring(resultStr.indexOf("{"), resultStr.length());
			log.info("核销返回的结果打印"+resultStr);
			return resultStr;
		} catch (Exception e) {
			JSONObject json=new JSONObject();
			json.put("errcode", 1);
			json.put("errmsg", "net connection fail "+e.getMessage());
			log.error("核销调用 getpassinfo失败",e);
			return json.toJSONString();
		}
	
	}
}
