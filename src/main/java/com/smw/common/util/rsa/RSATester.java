package com.smw.common.util.rsa;

/**
 * 测试
 * 
 * @author suen
 * @date 2016年7月4日-上午9:23:31
 * @version jdk1.8
 */
public class RSATester {
	static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDBy3VWKWlx0i6Ow7kk0m55M8QPDG0qBUzAYDn6"
			+ "0Zieh7jcAEDHkuwFpSK7Uzl3nu/D+HlkSzxMFab/NnkmQkw8MtKQG11WbHvPJygUQnV0pT6TPfMu"
			+ "CXyu9gmZT7RqAfjNwFjT9+Z6jZsteswTMmV37Ofp0SNZVvuSrgfH06/leQIDAQAB";
	static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMHLdVYpaXHSLo7DuSTSbnkzxA8M"
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

	/*
	 * static { try { Map<String, Object> keyMap = RSAUtils.genKeyPair();
	 * publicKey = RSAUtils.getPublicKey(keyMap); privateKey =
	 * RSAUtils.getPrivateKey(keyMap); System.err.println("公钥: \n\r" +
	 * publicKey); System.err.println("私钥： \n\r" + privateKey); } catch
	 * (Exception e) { e.printStackTrace(); } }
	 */

	public static void main(String[] args) throws Exception {
		/*byte[] ss= new byte[]{-112,-14,-13,-82,82,105,-64,68,-123,-74,-104,83,58,83,4,76,1,15,-117,35,9,17,113,-2,-106,44,35,35,-36,-28,79,107,114,-101,28,100,106,78,37,-20,115,20,38,-65,-50,90,40,-31,76,-101,0,-117,-8,-34,3,-45,-47,-40,-11,-19,-43,-32,111,102,20,84,-100,112,91,-95,66,-122,39,8,-59,-124,-13,79,-113,-123,94,87,-108,73,92,113,14,106,-93,95,-107,33,-41,48,-68,-36,99,-55,122,123,-104,-75,62,-9,-41,-40,-3,77,-105,63,13,23,-112,-114,-26,44,73,-119,78,-15,-27,-123,-110,-49,113,67,-13,88};
		String string = new String(ss,"utf-8");
		System.out.println(string);
		byte[] decodedData = RSAUtils.decryptByPrivateKey(ss, privateKey);
		String target = new String(decodedData);
		System.out.println(target);*/
		test();
		testSign();
	}

	static void test() throws Exception {
		System.err.println("公钥加密——私钥解密");
		String source = "E84E7273764D2D77E9FC4E9C521E8C3750";
		System.out.println("\r加密前文字：\r\n" + source);
		byte[] data = source.getBytes();
		byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);
		System.out.println("加密后文字：\r\n" + new String(encodedData));
		byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData, privateKey);
		String target = new String(decodedData);
		System.out.println("解密后文字: \r\n" + target);
	}

	static void testSign() throws Exception {
		System.err.println("私钥加密——公钥解密");
		String source = "这是一行测试RSA数字签名的无意义文字";
		System.out.println("原文字：\r\n" + source);
		byte[] data = source.getBytes();
		byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);
		System.out.println("加密后：\r\n" + new String(encodedData));
		byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
		String target = new String(decodedData);
		System.out.println("解密后: \r\n" + target);
		System.err.println("私钥签名——公钥验证签名");
		String sign = RSAUtils.sign(encodedData, privateKey);
		System.err.println("签名:\r" + sign);
		boolean status = RSAUtils.verify(encodedData, publicKey, sign);
		System.err.println("验证结果:\r" + status);
	}
}
