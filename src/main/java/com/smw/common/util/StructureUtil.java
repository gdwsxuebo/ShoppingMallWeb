package com.smw.common.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 构造工具类
 * @author 豪
 *
 */
public class StructureUtil {

	/**
	 * 传入状态参数构造返回对象
	 * @param status  提示代码、提示信息、bug信息、url
	 * @return
	 */
	public static BaseResultVo returnObject(String status[])
	{
		if(status.length!=4)
			return null ;
		BaseResultVo baseResultVo=new BaseResultVo();
		Status state=new Status();
		state.setCode(status[0]);//提示代码
		state.setMsg(status[1]);//提示信息
		state.setDebugMsg(status[2]);//bug信息
		state.setUrl(status[3]);//url
		baseResultVo.setStatus(state);
		
		return baseResultVo ;
	}
	public static BasePageResultVo returnObject(BasePageResultVo basePageResultVo,String status[])
	{
		if(status.length!=4)
			return null ;
		if(basePageResultVo==null)
			basePageResultVo = new BasePageResultVo();
		Status state=new Status();
		state.setCode(status[0]);//提示代码
		state.setMsg(status[1]);//提示信息
		state.setDebugMsg(status[2]);//bug信息
		state.setUrl(status[3]);//url
		basePageResultVo.setStatus(state);
		
		return basePageResultVo ;
	}
	/**
	 * 获取请求地址信息
	 * @param request
	 * @return
	 */
	public static String getUrl(HttpServletRequest request)
	{
		 Map<String, String[]> params = request.getParameterMap();  
	      String queryString = "";  
		     for (String key : params.keySet()) {  
		           String[] values = params.get(key);  
		           for (int i = 0; i < values.length; i++) {  
		              String value = values[i];  
		              queryString += key + "=" + value + "&";  
		          }  
		       }  
		      // 去掉最后一个空格  
		       queryString = queryString.substring(0, queryString.length() - 1); 
		       
		 return     request.getRequestURL()+"?"+queryString ; 
	}
}
