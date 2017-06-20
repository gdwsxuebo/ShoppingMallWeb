package com.smw.utils;

import java.security.*;
/**
 * 
 * MD5:MD5加密字符串
 *
 * @author yumaochun
 * @date  2016年3月7日
 * @version  jdk1.8
 *
 */
public class MD5 {
	
	 /**
	  * 
	  * getMD5:得到加密后的字符串
	  *
	  * @date 2016年3月7日
	  * @param orgin     待加密的字符串
	  * @return   返回加密的字符串
	  */
	 public static String getMD5(String orgin)
	 {
		 MessageDigest md=null;
		 byte[] bt=null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		 //得到加密后的字节数组(长度为16)
		 bt=md.digest(orgin.getBytes());
		 
		 StringBuffer stbf=new StringBuffer();
		 //对加密后的数组中的各个字节进行转换
		 for(int i=0;i<bt.length;i++)
		 {
			 int b=bt[i];
			 if(b<0)
				 b+=256;
			 if(b<16)
				 stbf.append("0");
			 stbf.append(Integer.toHexString(b));
		 }
		 return stbf.toString();
	 }

	 public final static String getMD532Big(String s) {
			char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','A', 'B', 'C', 'D', 'E', 'F' };
			try {
				byte[] btInput = s.getBytes();
				// 获得MD5摘要算法的 MessageDigest 对象
				MessageDigest mdInst = MessageDigest.getInstance("MD5");
				// 使用指定的字节更新摘要
				mdInst.update(btInput);
				// 获得密文
				byte[] md = mdInst.digest();
				// 把密文转换成十六进制的字符串形式
				int j = md.length;
				char str[] = new char[j * 2];
				int k = 0;
				for (int i = 0; i < j; i++) {
					byte byte0 = md[i];
					str[k++] = hexDigits[byte0 >>> 4 & 0xf];
					str[k++] = hexDigits[byte0 & 0xf];
				}
				return new String(str);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	 public static void main(String[] args)
	 {
		 System.out.println(getMD5("test"));
		
	 }
}
