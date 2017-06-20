package com.smw.license.verify;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.prefs.Preferences;

import com.smw.license.schlichtherle.license.CipherParam;
import com.smw.license.schlichtherle.license.DefaultCipherParam;
import com.smw.license.schlichtherle.license.DefaultKeyStoreParam;
import com.smw.license.schlichtherle.license.DefaultLicenseParam;
import com.smw.license.schlichtherle.license.KeyStoreParam;
import com.smw.license.schlichtherle.license.LicenseContent;
import com.smw.license.schlichtherle.license.LicenseManager;
import com.smw.license.schlichtherle.license.LicenseParam;
import com.smw.license.util.LicenseCheckModel;

/**
 * VerifyLicense
 * @author wenhao  e-mail:wenhaoru@outlook.com
 */
public class VerifyLicense {
	//common param
	private static String PUBLICALIAS = "";
	private static String STOREPWD = "";
	private static String SUBJECT = "";
	private static String licPath = "";
	private static String pubPath = "";
	

	
	public void setParam(String propertiesPath) throws IOException {
		// 
		Properties prop = new Properties();
		//System.out.println("propertiesPath="+propertiesPath);
//		InputStream in = getClass().getResourceAsStream(propertiesPath);		
		InputStream in = new FileInputStream(propertiesPath);		
		prop.load(in);		
		PUBLICALIAS = prop.getProperty("PUBLICALIAS");
		STOREPWD = prop.getProperty("STOREPWD");
		SUBJECT = prop.getProperty("SUBJECT");
		licPath =VerifyLicense.class.getResource("/").getPath()+"license.lic"; //licPath=prop.getProperty("licPath");
		pubPath = VerifyLicense.class.getResource("/").getPath()+"publicCerts.store";//prop.getProperty("pubPath");
	}

	public boolean verify() throws Exception  {		

		LicenseManager licenseManager = LicenseManagerHolder
				.getLicenseManager(initLicenseParams());
		// install license file
		try {
			licenseManager.install(new File(licPath));
			System.out.println("License file instal successfully!");				
		} catch (Exception e) {
			e.printStackTrace();			
			String moreInfo ="License file instal failure";
			System.out.println(moreInfo);
			return false;
			//throw e;
		}
		// verify license file
		try {
			licenseManager.verify();			
			System.out.println("License file verify successfully!");
		} catch (Exception e) {
			e.printStackTrace();			
			String moreInfo ="License file verify failure";			
			System.out.println(moreInfo); 
			throw e;
		}
		return true;
	}

	//
	private static LicenseParam initLicenseParams() {
		Preferences preference = Preferences
				.userNodeForPackage(VerifyLicense.class);
		CipherParam cipherParam = new DefaultCipherParam(STOREPWD);

		KeyStoreParam privateStoreParam = new DefaultKeyStoreParam(
				VerifyLicense.class, pubPath, PUBLICALIAS, STOREPWD, null);
		LicenseParam licenseParams = new DefaultLicenseParam(SUBJECT,
				preference, privateStoreParam, cipherParam);
		return licenseParams;
	}

	public String getnotAfter() {
		// TODO Auto-generated method stub
		LicenseManager licenseManager = LicenseManagerHolder.getLicenseManager(initLicenseParams());
		
		try {
			LicenseCheckModel licenseContent= (LicenseCheckModel) licenseManager.install(new File(licPath)).getExtra();
			String NotAfter = licenseContent.getNotAfter();
			return NotAfter;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
}