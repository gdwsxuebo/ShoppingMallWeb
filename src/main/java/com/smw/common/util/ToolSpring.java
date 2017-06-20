package com.smw.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class ToolSpring implements ApplicationContextAware {

	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		if(ToolSpring.applicationContext==null)
		{
			ToolSpring.applicationContext = applicationContext;
		}
	}

	public static ApplicationContext getApplicationContext()
	{
		return applicationContext;
	}
	
	public static Object getBean(String name)
	{
		return getApplicationContext().getBean(name);
	}
	
}
