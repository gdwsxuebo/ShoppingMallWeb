package com.smw.license;

import com.smw.license.verify.VerifyLicense;

/**
 * 
 * @author suen
 * @date 2016年6月13日-下午2:20:53
 * @version  jdk1.8
 */
public class LicenseVerify_val {
	public static void main(String[] args){
		String fileUrl = LicenseVerify_val.class.getResource("/").getPath();
		System.out.println(fileUrl.substring(1));
		/*VerifyLicense vLicense = new VerifyLicense();
		try{
			String fileUrl = LicenseVerify_val.class.getResource("/").getFile();
			System.out.println(fileUrl);
			vLicense.setParam(fileUrl+"\\verifyparam.properties");
			
			vLicense.verify();
		}
		catch(Exception er){
			er.printStackTrace();
		}*/

	}
	
	/**
	 * 验证是否有效
	 * @return
	 */
	public static Boolean verifyLicense(){
		VerifyLicense vLicense = new VerifyLicense();
		try{
			String fileUrl = LicenseVerify_val.class.getResource("/").getPath();
			//System.out.println(fileUrl);
			vLicense.setParam(fileUrl+"verifyparam.properties");
			
			return vLicense.verify();
		}
		catch(Exception er){
			er.printStackTrace();
		}
		return false;
	}
}
