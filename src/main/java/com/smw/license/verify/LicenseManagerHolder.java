package com.smw.license.verify;
import com.smw.license.schlichtherle.license.LicenseManager;
import com.smw.license.schlichtherle.license.LicenseParam;

/**
 * LicenseManager
 * @author wenhao  e-mail:wenhaoru@outlook.com
 */
public class LicenseManagerHolder {
	
	private static LicenseManager licenseManager;
 
	public static synchronized LicenseManager getLicenseManager(LicenseParam licenseParams) {
    	if (licenseManager == null) {
    		licenseManager = new LicenseManager(licenseParams);
    	}
    	return licenseManager;
    }
}