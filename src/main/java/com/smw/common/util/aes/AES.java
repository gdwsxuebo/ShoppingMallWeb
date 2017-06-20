package com.smw.common.util.aes;

import java.security.SecureRandom;  

import javax.crypto.Cipher;  
import javax.crypto.KeyGenerator;  
import javax.crypto.SecretKey;  
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;  

public class AES {
	private static String key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMHLdVYpaXHSLo7DuSTSbnkzxA8M"
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
	 * 入口函数
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		byte[] decodeBase64 = com.smw.common.util.Base64.decode("E84E7273764D2D77E9FC4E9C521E8C3750");
		System.out.println(new String(decodeBase64));
		String test = aesEncrypt("E84E7273764D2D77E9FC4E9C521E8C3750");
		String oneBaseEncoder = new BASE64Encoder().encode("E84E7273764D2D77E9FC4E9C521E8C3750".getBytes());
		System.out.println("加密后：" + new String(Base64.decodeBase64(oneBaseEncoder)));
		String data = aesDecrypt(test);
		System.out.println("解密后：" + data);
	}

	/**
	 * AES加密算法-加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String aesEncrypt(String data) throws Exception {
		// 构造秘钥生成器
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, new SecureRandom(key.getBytes()));
		// 产生原始对称密钥
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		// 生成密钥
		SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, "AES");
		// AES加密算法
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		byte[] byteContent = data.getBytes("utf-8");
		cipher.init(Cipher.ENCRYPT_MODE, keySpec);
		byte[] result = cipher.doFinal(byteContent);
		// base64处理
		return new BASE64Encoder().encode(result);
	}

	/**
	 * AES加密算法-解密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String aesDecrypt(String data) throws Exception {
		byte[] buf = new BASE64Decoder().decodeBuffer(data); // base64处理

		// 构造秘钥生成器
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, new SecureRandom(key.getBytes()));
		// 产生原始对称密钥
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		// 生成密钥
		SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, "AES");
		// AES加密算法
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, keySpec);
		byte[] result = cipher.doFinal(buf);
		// base64处理
		return new String(result);
	}
}
