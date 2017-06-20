package com.smw.common.util.rsa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.util.logging.resources.logging;

/**
 * RSA工具
 * 
 * @author suen
 * @date 2016年7月4日-上午11:05:49
 * @version jdk1.8
 */
public class RSAHandle {
	// 日志
	private static Logger logger = LoggerFactory.getLogger(RSAHandle.class);

	private static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMHLdVYpaXHSLo7DuSTSbnkzxA8M"
			+ "bSoFTMBgOfrRmJ6HuNwAQMeS7AWlIrtTOXee78P4eWRLPEwVpv82eSZCTDwy0pAbXVZse88nKBRC"
			+ "dXSlPpM98y4JfK72CZlPtGoB+M3AWNP35nqNmy16zBMyZXfs5+nRI1lW+5KuB8fTr+V5AgMBAAEC"
			+ "gYAgLmwuwbO77f5DQOdG4RbtU5ZUNWkPWE/1hkU6jIG+maUuC3C2c4R0L4cD+n8JiUAhl+p4qDG8"
			+ "JMBVkzMtEkdcUbTNsSY2HmGVcvdRDdxTPa109j0X/qwkRDpZc04ho/x6UEUYtvQpXYSmKWOSonf7"
			+ "FfX2FN6IKe2IhjgY5IqUgQJBAOqg38duB7uKZd6dNrqhtp0jVjzM4teZKKXC0XEQ9lRHT0WAqq+e"
			+ "SMI/gNyDieEGkgG4W2E1xreVATcTv3HYIUkCQQDTcmoc8MJu/eHlYBOhDuxxPMpcZMnlFUz0u/rC"
			+ "BVsGEPFrNyu0O0vjihGsAOKQF4Q6tzZNAWzhjeNOhrgVW9KxAkAhUPq1vOAIN1zDwZs14SSnJ49l"
			+ "TsqfRrZI42MOJRR1XzLYca7LBTTbsFzB80Ou2ln9OIpxQUuYYeYsRfeXWNNRAkBMotZU4OoHolri"
			+ "cL0MjcUikZ7id+E490xqE0uiUKCaBUAK1PcxHqC1upAHIFb9VJinU0oKBO86nNt61ZcYnqwxAkEA"
			+ "5XVTT68u9FO+kGDY7vb+7v5oSnR1FRkaQ7ZfiJME8hCj7mdH/qmBRxraxnwNQhp9NEaRqtgKa2fW" + "mrzWstp8iw==";

	/**
	 * 私钥解密
	 * 
	 * @param encodedData
	 *            解密数据
	 * @return
	 */
	public static String decryptByPrivateKey(byte[] decodedData) {
		try {
			return new String(RSAUtils.decryptByPrivateKey(decodedData, privateKey));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("私钥解密失败！"+e.getMessage());
		}
		return "";
	}

	/**
	 * 私钥加密
	 * 
	 * @param encodedData
	 *            加密数据
	 * @return
	 */
	public static String encryptByPrivateKey(byte[] encodedData) {
		try {
			return new String(RSAUtils.encryptByPrivateKey(encodedData, privateKey));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("私钥加密失败！"+e.getMessage());
		}
		return "";
	}

	/**
	 * 私钥签名
	 * 
	 * @param encodedData
	 *            加密数据
	 * @return
	 */
	public static String getSign(String encodedData) {
		try {
			return RSAUtils.sign(encodedData.getBytes(), privateKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("私钥签名失败！"+e.getMessage());
		}
		return "";
	}
}
