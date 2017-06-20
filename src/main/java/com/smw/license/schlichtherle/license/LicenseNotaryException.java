package com.smw.license.schlichtherle.license;

import java.security.GeneralSecurityException;

public class LicenseNotaryException
  extends GeneralSecurityException
{
  private String alias;
  
  public LicenseNotaryException(String paramString1, String paramString2)
  {
    super(paramString1);
    this.alias = paramString2;
  }
  
  public String getLocalizedMessage()
  {
//	  System.out.println(">>>>>>>>>>>>>>>>>>"+super.getMessage());
    return Resources.getString(super.getMessage(), this.alias);
  }
}
