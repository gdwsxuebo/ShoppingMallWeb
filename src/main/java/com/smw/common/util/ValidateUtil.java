package com.smw.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.servlet.http.HttpServletRequest;
/**
 * 
 * ValidateUtil:验证工具类
 *
 * @author yumaochun
 * @date  2016年3月5日
 * @version  jdk1.8
 *
 */
public class ValidateUtil {
	
	//日志
	private final static Log logger = LogFactory.getLog(ValidateUtil.class);
	
	/**
	 * 
	 * checkCaptchaCode:验证验证码是否存在
	 *
	 * @date 2016年3月5日
	 * @param userNumber               待验证的验证码
	 * @param request                  request
	 * @return        true-验证码存在，false-验证码不存在
	 */
	public static boolean checkCaptchaCode(String userNumber,HttpServletRequest request){
		if(userNumber == null){
			return false;
		}
		Object randObj = request.getSession().getAttribute("rand");
		if(randObj == null){
			return false;
		}
		String randNumber = randObj.toString();
		if(userNumber.equals(randNumber)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 
	 * isNUllStr:验证字符串是否是空
	 *
	 * @date 2016年3月7日
	 * @param str      待验证的字符串
	 * @return   true-空字符串，false-非空字符串
	 */
	public static boolean isNUllStr(String str){
		if(str!=null&&!str.equals("")){
			return false;
		}else{
			return true;
		}
		
	}
	
	/**
	 * 
	 * isLengthRangeStr:验证字符串的，长度范围
	 *
	 * @date 2016年3月7日
	 * @param str
	 * @param minLength
	 * @param maxLength
	 * @return        true-验证成功，false-验证失败
	 */
	public static boolean isLengthRangeStr(String str,int minLength,int maxLength){
		//验证是否是空字符串
		if(isNUllStr(str)){
			return false;
		}else{
			int len=str.length();
			if(len>=minLength&&len<=maxLength){
				return true;
			}
		}
		return false;
	}
	
	
	//Is available length str
	public static boolean isAvailableLengthStr(String str,int start,int end){
		if(str == null){
			return false;
		}
		return validate(str, "^.{"+start+","+end+"}$");
	}
	
	/**
	 * 
	 * isAvailableAccountStr:只能输入英文、数字、下划线、或者邮箱
	 *
	 * @date 2016年3月5日
	 * @param str            待验证的字符串
	 * @param start          最小长度
	 * @param end            最大长度
	 * @return    true-合格，false-不合格
	 */
	public static boolean isAvailableAccountStr(String str,int start,int end){
		if(str == null){
			return false;
		}
		return validate(str, "^[a-zA-Z0-9_@\\.]{"+start+","+end+"}$");
	}
	
	/**
	 * 
	 * isDouble:是不是double类型的值
	 *
	 * @date 2016年3月5日
	 * @param str    待验证的字符串
	 * @return
	 */
	public static boolean isDouble(String str){
		if(str == null){
			return false;
		}
		return validate(str, "^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?[dD]?$");
	}
	
	//Is available length str
	public static boolean isAvailableLength(String str,int start,int end){
		if(str == null){
			return false;
		}
		return validate(str, "^\\w{"+start+","+end+"}$");
	}
	/**
	 * 
	 * isNum:验证是不是数字
	 *
	 * @date 2016年3月5日
	 * @param str               待验证的字符串
	 * @return   true-是数字，false-不是数字
	 */
	public static boolean isNum(String str)
	{
		if(str == null){
			return false;
		}
		return validate(str,"^\\d{0,11}$");
	}
	
	/**
	 * 
	 * isAvailableUnicodeLength:Is available length str[unicode char]
	 *    验证字符串 char  是否在指定的字符串长度之间
	 *
	 * @date 2016年3月5日
	 * @param str             待验证的字符串
	 * @param start           字符串最小长度
	 * @param end             字符串最大长度
	 * @return          
	 */
	public static boolean isAvailableUnicodeLength(String str,int start,int end){
		if(str == null){
			return false;
		}
		return validate(str, "^[\\d\\D]{"+start+","+end+"}$");
	}
	/**
	 * 
	 * isIp:验证ip地址格式
	 *
	 * @date 2016年3月5日
	 * @param sIp                待验证的ip地址
	 * @return       返回：true-ip地址格式正确，false-ip地址格式不正确
	 */
	public static boolean isIp(String sIp)
	{
		if(sIp == null){
			return false;
		}
		return validate(sIp, "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
	}
	
	/**
	 * 
	 * isEmail:验证是不是电子邮箱格式
	 *
	 * @date 2016年3月5日
	 * @param email                  待验证的邮箱号
	 * @return  返回：true-邮箱格式正确，false-邮箱格式不正确
	 */
	public static boolean isEmail(String email){
		if(email == null){
			return false;
		}
	    return validate(email, "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
	}
	/**
	 * 
	 * isDate:验证是不是日期格式(yyyy-MM--dd)
	 *
	 * @date 2016年3月5日
	 * @param date              待验证的日期
	 * @return         返回：true-是日期格式，false-不是日期格式
	 */
	public static boolean isDate(String date)
	{
		if(date == null){
			return false;
		}
		return validate(date, "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$");
	}
	/**
	 * 
	 * isDate:验证是不是日期格式(yyyyMMdd HH:mm:ss)
	 *
	 * @date 2016年3月5日
	 * @param date              待验证的日期
	 * @return         返回：true-是日期格式，false-不是日期格式
	 */
	public static boolean isDateTime(String dateTime)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		Date d = null;
		if(dateTime != null && !dateTime.equals(""))
		{

		    if(dateTime.split("/").length > 1)
		   {
		       dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		   }
		   if (dateTime.split("-").length > 1)
		   {
		       dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   }
		}
		else{
			return false;
		}
		try {
			//格式正确
			d = dateFormat.parse(dateTime);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean userPassPort(String str)
	{
		if(str == null){
			return false;
		}
	    return validate(str, "^\\d{15}|\\d{18}$");
		
	}
	
	/**
	 * 
	 * isJwdu:验证经纬度
	 *
	 * @date 2016年3月5日
	 * @param str
	 * @return
	 */
	public static boolean isJwdu(String str)
	{
		if(str == null){
			return false;
		}
		return validate(str, "^-?(1[0-7]?[0-9]?|[0-9][0-9]|[0-9]|0)(\\.\\d{0,6})?$");
	}
	/**
	 * 
	 * isPhone:验证手机号码格式是否正确
	 *
	 * @date 2016年3月5日
	 * @param str            待验证的手机号
	 * @return   返回：true-是手机号，false-手机号格式不正确
	 */
	public static boolean isPhone(String str)
	{
		if(str == null){
			return false;
		}
		return validate(str, "^0?(13|14|15|18)[0-9]{9}$");
	}
	/**
	 * 
	 * validate:验证字符串
	 *
	 * @date 2016年3月5日
	 * @param strValue        待验证的内容
	 * @param regexValue      正则表达式
	 * @return   返回：true-成功，false-失败
	 */
	public static boolean validate(String strValue,String regexValue){
		if(strValue == null){
			return false;
		}
		Pattern pattern = Pattern.compile(regexValue);     
	    Matcher matcher = pattern.matcher(strValue);
	    return matcher.find();
	}
	/**
	 * 
	 * isChineseName:验证是不是中文
	 *
	 * @date 2016年3月5日
	 * @param str       待验证的字符串
	 * @return   返回：true-是中文，false-不是中文
	 */
	public static boolean isChineseName(String str){
		String regex = "[\u4E00-\u9FA5]+";
		return validate(str, regex);
	}
	
	public static void main(String[] args) {
		
		
		//System.out.println(isAvailableLength("1231你",1,8));
		//收货地址机
		//System.out.println(isAvailableUnicodeLength("啊才cccc", 2, 6));
		//System.out.println(isAvailableLength("npcmon1", 1, 2));
		//System.out.println(isAvailableLengthStr("npcmon1.6", 0, 9));
		//System.out.println(isCellphone("13551097681"));
		//System.out.println(isQQ("52133"));
		//System.out.println(isEmail("wags@aa.com"));
		System.out.println(isChineseName("是才存车场才1r"));
		//System.out.println(isAvailableAccountStr("aaa@qq.com", 0, 19));
//		System.out.println(isDouble("32"));
//		System.out.println(validate("Content-Type: text/html; charset=GBK kjlfdbsfdsfldsl1231^%$$% fdsdskfjdlskfjdlskfdlsjfldsjfls", "charset=(\\w+)"));
		//System.out.println(isJwdu("-159"));
		//System.out.println(isPhone("11000000000"));
		//System.out.println(isDateTime("2012-05-12 12:21:11"));
	}
}
