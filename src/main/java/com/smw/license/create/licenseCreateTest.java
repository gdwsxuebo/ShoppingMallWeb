package com.smw.license.create;


/**
 * 
 * @author wenhao  e-mail:wenhaoru@outlook.com 
 *
 */
public class licenseCreateTest {
	public static void main(String[] args){
		CreateLicense cLicense = new CreateLicense();
		
		//cLicense.setParam("D:\\workspace\\GDWSLicense\\src\\main\\java\\mis\\createparam.properties");
		//
		cLicense.setParam("F:\\createparam.properties");
		cLicense.create();
	}
}
