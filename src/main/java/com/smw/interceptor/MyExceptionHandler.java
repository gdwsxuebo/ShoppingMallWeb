package com.smw.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;




public class MyExceptionHandler implements HandlerExceptionResolver  {
	private Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);
	public ModelAndView resolveException( HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex ) {
		String	exUrl=request.getServletPath();
		String param=request.getParameterMap().toString();
		logger.error("异常URL:"+exUrl+"\n"+"异常Parm:"+param+"\n"+"异常erroMsg:",ex);
		return null;
	}


}