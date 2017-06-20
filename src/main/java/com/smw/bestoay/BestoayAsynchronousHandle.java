package com.smw.bestoay;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 翼支付异步操作
 * 
 * @author suen
 * @date 2016年6月27日-下午2:37:46
 * @version jdk1.8
 */
public class BestoayAsynchronousHandle {
	// 日志
	private static Logger logger = LoggerFactory.getLogger(BestoayAsynchronousHandle.class);

	public static Object asynchronousHandle(HttpServletRequest request) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("翼支付异步操作 "+e.getMessage());
		}
		return "";
	}
}
