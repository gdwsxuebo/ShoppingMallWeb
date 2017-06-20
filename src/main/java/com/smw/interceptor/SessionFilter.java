package com.smw.interceptor;

import java.io.IOException;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smw.common.util.LogInfoUtil;
import com.smw.pojo.XfStaff;
import com.smw.web.Init;


public class SessionFilter implements Filter {

	// 不拦截的地址
	private static final String[] IGNORE_URI = { "/webapi", "/init", "/login", "resource/","gwspay/","gws/" };

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String isToInit = Init.INITURL.get("isToInit");
		if (!isHasNoFilterUrl(request) && "false".equals(isToInit)) {
			XfStaff xfStaff = (XfStaff) request.getSession().getAttribute("XfStaff");
			//System.out.println("====后台管理过滤开始======");
			if (xfStaff != null) {
				chain.doFilter(request, response);
			} else {
				// 跳转首页
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				request.getSession().setAttribute("msg", "上次登录已过时！");
			}
		} else {
			String url = request.getRequestURI().toString();
			if (url.contains("/webapi") || url.contains("resource/")) {
				chain.doFilter(request, response);
			} else {
				if ("true".equals(isToInit)) {
					if(url.contains("web/init/init")){
						chain.doFilter(request, response);
					}else{
						request.getRequestDispatcher("/init.jsp").forward(request,response);
					}
				}else{
					if(url.contains("/init")){
						//跳转首页
				    	request.getRequestDispatcher("/login.jsp").forward(request,response);
					}else{
						chain.doFilter(request, response);
					}
				}
			}
		}
		//保存日志
//		LogInfoUtil.saveLogInfo(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * isHasNoFilterUrl:是否是不需要拦截的url
	 *
	 * @date 2016年3月7日
	 * @param request
	 * @return
	 */
	public boolean isHasNoFilterUrl(HttpServletRequest request) {
		boolean result=false;
		// 不用拦截的地址
		String url = request.getRequestURI().toString();
		//System.out.println(url);
		for (String s : IGNORE_URI) {
			if (url.contains(s)) {
				result= true;break;
			}
		}
		return result;
	}

}
