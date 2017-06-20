package com.smw.common.util;

import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smw.api.CallParameters;
import com.smw.common.des.UnEncryptData;
import com.smw.pojo.XfStaff;

/**
 * 组装日志信息工具
 * 
 * @author suen
 * @date 2016年6月14日-下午6:26:11
 * @version jdk1.8
 */
public class LogInfoUtil {
	private static Logger logger = LoggerFactory.getLogger(LogInfoUtil.class);

	/**
	 * 日志基本属性：操作人（调用人）、操作方法（调用方法）、时间、请求数据、响应数据、错误信息。 保存日志信息
	 * 
	 * @param request
	 * @param response
	 * @param chain 
	 */
	public static void saveLogInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 获取访问的路径
			String requestURI = request.getRequestURI();
			StringBuffer buffer = new StringBuffer();
			// 访问时间
			buffer.append("访问时间：" + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			// 访问路径
			buffer.append("  访问路径：" + requestURI);
			// 访问方式
			buffer.append("  访问方式：" + request.getMethod());
			// 访问参数
			buffer.append("   访问参数：");

			// 获取所有参数
			Map<String, String[]> params = request.getParameterMap();
			String queryString = "",paramStr="",yu="&";
			int lenPkeySet=params.keySet().size(),lenIte=1;
			for (String key : params.keySet()) {
				if (lenIte==lenPkeySet) {
					yu="";
				}
				String[] values = params.get(key);
				for (int i = 0,len=values.length; i < len; i++) {
					String value = values[i];
					if (requestURI.contains("webapi")) {
						//销售不解密，因为销售数据量过大解密会失败！
						if (paramStr.contains(CallParameters.SAVE_SALES_SUMMARY)) {
							//参数明码字符串
							paramStr+=key + "=" + value + yu;
						}else{
							//参数明码字符串
							paramStr+=key + "=" + new String(UnEncryptData.createUnEncryptData(Base64.decodeBase64(value)),"UTF-8") + yu;
						}
					}
					queryString += key + "=" + value + yu;
				}
				lenIte++;
			}
			// 去掉最后一个空格
			queryString = queryString.length()>1?(queryString.substring(0, queryString.length() - 1)):queryString;
			buffer.append(queryString);
			// 接口
			if (requestURI.contains("webapi")) {
				buffer.append("\n参数明码："+paramStr);
			} else {
				try {
					XfStaff staff = (XfStaff) request.getSession().getAttribute("XfStaff");	// 员工
					// 操作人
					buffer.append("   操作人：" + (staff == null ? "无" : ("账号：" + staff.getXfStaffcode() + " 名称：" + staff.getXfName())));
				} catch (Exception e) {
					throw new RuntimeException("员工未登录的访问");
				}

			}
			System.out.println(buffer.toString());
			//输出日志
			logger.info(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("记录操作日志出错"+e.getMessage());
		}
	}
}
