package com.smw.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单的封装数据的集合，用来app调用接口时返回封装的数据
 * @author suen
 * @date 2016年5月18日-下午8:23:44
 * @version 1.0
 */
public class ApiDataMap {
	
	//消息提示
	public static final String paramsErrorMsg="参数错误！";
	public static final String loginUnSuccessfulMsg="登录失败！";
	public static final String loginSuccessMsg="登录成功！";
	public static final String successMsg="操作成功！";
	public static final String unSuccessfulMsg="操作失败！";
	//数据集合
	private static Map<String, Object> data;
	
	/**
	 * 成功
	 * @param obj 对象数组，长度只为2，第一个索引位置放消息，第二个索引位置放数据，如果不传或者传null消息默认“操作成功”，数据默认“{}”
	 * @return
	 */
	public static Map<String, Object> success(Object... obj){
		if (obj==null|| obj.length==0) {
			obj=new Object[]{successMsg,"{}"};
		}
		data=new HashMap<String,Object>();
		//状态编码 1成功 0失败
		data.put("code", "1");
		//消息
		data.put("msg", obj.length==1?successMsg:obj[0]);
		//数据
		data.put("data", obj.length==1?obj[0]:obj[1]);
		return data;
	}
	
	/**
	 * 失败
	 * @param msg 消息数组 可以不传，不传消息提示语默认是“操作失败！”
	 * @return
	 */
	public static Map<String, Object> unSuccessful(String... msg){
		if (msg==null || msg.length==0) {
			msg=new String[]{unSuccessfulMsg};
		}
		data=new HashMap<String,Object>();
		//状态编码 1成功 0失败
		data.put("code", "0");
		//消息
		data.put("msg", msg[0]);
		//数据
		data.put("data", "{}");
		return data;
	}
	
	/**
	 * 获取单个数据集合
	 * @return
	 */
	public static Map<String, String> getSingleDataMap(){
		return new HashMap<String, String>();
	}
	
	/**
	 * 处理值如果一个值为null转为""，如果非字符串转为字符串
	 * @param val 值
	 * @return
	 */
	public static String cleanObjNull(Object val){
		if (val==null) {
			return "";
		}else{
			return val.toString();
		}
	}
	
	/**
	 * 把一个空串返回成0
	 * @param val
	 * @return
	 */
	public static Object cleanObject2Number(Object val){
		if ( val==null || val.equals("")) {
			return 0;
		}else{
			return val.toString();
		}
	}
}
