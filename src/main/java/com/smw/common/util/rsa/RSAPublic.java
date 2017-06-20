package com.smw.common.util.rsa;

/**
 * 测试公钥加密
 * @author suen
 * @date 2016年7月4日-上午9:35:05
 * @version  jdk1.8
 */
public class RSAPublic {
	public static void main(String[] args) throws Exception {
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDBy3VWKWlx0i6Ow7kk0m55M8QPDG0qBUzAYDn6"
				+ "0Zieh7jcAEDHkuwFpSK7Uzl3nu/D+HlkSzxMFab/NnkmQkw8MtKQG11WbHvPJygUQnV0pT6TPfMu"
				+ "CXyu9gmZT7RqAfjNwFjT9+Z6jZsteswTMmV37Ofp0SNZVvuSrgfH06/leQIDAQAB";
		//验证签名
		boolean status = RSAUtils.verify("我加密后的数据".getBytes(), publicKey, "我给你的签名");
		//公钥加密
		byte[] data = "公钥加密".getBytes();
		byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);
		//公钥解密
		byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
	}
}
