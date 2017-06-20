package com.smw.common.util;

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

	 public static void main(String[] args)
	 {
		 System.out.println(getMD5("test"));
		 //Bestoay
		 System.out.println(getMD5("bestoay"));
	 }
	}
