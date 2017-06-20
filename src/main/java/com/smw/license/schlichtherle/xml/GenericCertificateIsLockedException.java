package com.smw.license.schlichtherle.xml;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;

public class GenericCertificateIsLockedException
  extends PropertyVetoException
{
  public GenericCertificateIsLockedException(PropertyChangeEvent paramPropertyChangeEvent)
  {
    super(paramPropertyChangeEvent.getPropertyName(), paramPropertyChangeEvent);
  }
}
