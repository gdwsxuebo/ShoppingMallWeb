package com.smw.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class Interceptor implements HandlerInterceptor {

	String[] allowUrl;

	public String[] getAllowUrl() {
		return allowUrl;
	}

	public void setAllowUrl(String[] allowUrl) {
		this.allowUrl = allowUrl;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		
		//请求路径 
		String servletPath = request.getServletPath();
		
		//请求到 如果是后台操作 验证登录
		if(servletPath.contains("/web")) {
			//获取登录管理用户信息
			String  loginadmin = null;
			
			//默认权限登录验证为不通过
			boolean flag = false;
			
			//验证请求链接是否合法
	    	if (loginadmin != null) {
	    		flag = true;
			}
	    	
	    	//如果登录验证不通过，则跳转登录页面
	    	if (!flag && !servletPath.contains("/login")) {
	    		response.sendRedirect(request.getContextPath() + "/error.jsp");
		        return false;
			}else{
				return true;
			}
	    }
	    return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

}
