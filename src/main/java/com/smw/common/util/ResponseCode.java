package com.smw.common.util;
/**
 * 
 * ResponseCode:系统提示信息代码编号
 *
 * @author yumaochun
 * @date  2016年3月5日
 * @version  jdk1.8
 *
 */
public class ResponseCode {
	
	//************************操作提示代码，代码以“1”开始（参数验证，及操作是否成功）**************************
	/**
	 * 操作成功:10000
	 */
	public static String OPR_SUCCESS="10000";
	/**
	 * 操作失败：10010
	 */
	public static String OPR_FAIL="10010";
	
	/**
	 * 无结果：10011
	 */
	public static String NO_RESULT="10011";
	
	
	
	//************************系统错误及异常，错误代码以“0”开始**************************
	
	/**
	 * 系统错误0010
	 */
	public static String SYS_ERROR = "0010";
	/**
	 * 一般异常:0020
	 */
	public static String BASE_ERROR = "0020";
	/**
	 * 参数错误:0030
	 */
	public static String PARAM_ERROR = "0030";
	/**
	 * 空指针错误:0040
	 */
	public static String NULL_ERROR = "0040";
	/**
	 * 数字格式化异常
	 */
	public static String FORMAT_ERROR = "0050";
	/**
	 * 类型强制转换异常 
	 */
	public static String CONVERT_TYPE_ERROR = "0060";
	/**
	 * 参数类型不匹配:0070
	 */
	public static String PARAM_TYPE_ERROR = "0070";
	/**
	 * 接口请求方法类型不匹配（post,get）
	 */
	public static String METHOD_TYPE_ERROR = "0080";
	
}
