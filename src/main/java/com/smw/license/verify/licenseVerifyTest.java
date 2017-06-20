package com.smw.license.verify;

/**
 * 
 * @author wenhao  e-mail:wenhaoru@outlook.com
 *
 */
public class licenseVerifyTest {
	public static void main(String[] args){
		VerifyLicense vLicense = new VerifyLicense();
		try{
			String fileUrl = licenseVerifyTest.class.getResource("/").getPath();
			vLicense.setParam(fileUrl+"verifyparam.properties");
			//vLicense.getnotAfter();
			vLicense.verify();
		}
		catch(Exception er){
			er.printStackTrace();
		}

	}
}
