package com.smw.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smw.common.util.ToolSpring;
import com.smw.license.util.LicenseCheckModel;
import com.smw.license.verify.VerifyLicense;
import com.smw.license.verify.licenseVerifyTest;
import com.smw.pojo.GwMenuTree;
import com.smw.pojo.GwRole;
import com.smw.pojo.GwRoleGroup;
import com.smw.pojo.XfStaff;
import com.smw.pojo.XfStaffRole;
import com.smw.service.GwRoleGroupService;
import com.smw.service.GwRoleService;
import com.smw.service.XfStaffService;
import com.wenhao.sso.client.filter.SsoUser;

public class SessionFilterSso implements Filter {
	// 不拦截的地址
	private static final String[] IGNORE_URI = { "/ShoppingMallWeb/webapi", "/init", "/login", "resource/", "gwspay/", "gws/" };

	public void destroy() {
	}

	
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		// pos接口地址
		String misApi = "/ShoppingMallWeb/webapi";
		// 照片地址
		String zpApi = "/ShoppingMallWeb/resource";
		// 当前请求的url
		String requestUrl = request.getRequestURI().toString();
		// 判断是否是MIS接口地址
		if (requestUrl.indexOf(misApi) != -1) {
			chain.doFilter(request, response);
			return;
		}
		// 判断是否是照片接口地址
		if (requestUrl.indexOf(zpApi) != -1) {
			chain.doFilter(request, response);
			return;
		}

		if (requestUrl.indexOf("/ShoppingMallWeb/login.jsp") != -1) {
			chain.doFilter(request, response);
			return;
		}
		
		
		
		// 用户cookie是否存在
		isHasCookie(request);
		// 获取用户sso客户端登录信息
		SsoUser ssoUser = (SsoUser) request.getSession().getAttribute("ssoUser");
		
		// license许可验证
		VerifyLicense vLicense = new VerifyLicense();
		String notAfter = null;
		try {
			String fileUrl = licenseVerifyTest.class.getResource("/").getPath();
			vLicense.setParam(fileUrl + "verifyparam.properties");
			boolean tag = vLicense.verify();
			if (!tag) {
				request.getSession().setAttribute("msg", "许可认证已过期!");
				response.sendRedirect("/ShoppingMallWeb/login.jsp");
				return;
			} else {

				notAfter = vLicense.getnotAfter();
				if (notAfter != null) {
					request.getSession().setAttribute("notAfter", "许可证到期时间:" + notAfter);
				} else {
					request.getSession().setAttribute("notAfter", "");
				}
			}

		} catch (Exception er) {
			er.printStackTrace();
		}

		if (ssoUser == null) {
			response.sendRedirect("/ShoppingMallWeb/web/admin/login");
		} else {
			XfStaff staff = (XfStaff) request.getSession().getAttribute("XfStaff");
			if (staff == null) {
				String username = ssoUser.getUsername();
				XfStaffService gwAdminUsersServiceImpl = (XfStaffService) ToolSpring.getBean("xfStaffServiceImpl");
				GwRoleGroupService gwRoleGroupService = (GwRoleGroupService) ToolSpring.getBean("gwRoleGroupServiceImpl");
				GwRoleService gwRoleService = (GwRoleService) ToolSpring.getBean("gwRoleServiceImpl");
				XfStaff tempAdminUser = gwAdminUsersServiceImpl.getXfStaffByStaffCode(username);
				if (tempAdminUser == null) {
					XfStaff staffinit = new XfStaff();
					staffinit.setXfStaffcode(ssoUser.getUsername());
					staffinit.setXfName(ssoUser.getUsername());
					staffinit.setXfPassword(ssoUser.getPassword());
					staffinit.setEnabled(true);
					staffinit.setIsReturnGoodsAuth(0);
					// 设置权限名称
				/*	XfStaffRole role = new XfStaffRole();
					role.setAuthority("ROLE_MALL_ADMIN");// 商场
					role.setXfStaffcode(staffinit);
					List<GwRole> gwRoleList = gwRoleService.selectAllList();
					Integer temp= new Integer(0);
					if(gwRoleList.size()>0){
						temp= gwRoleList.get(0).getId();
						for(GwRole gwRole:gwRoleList){
							if(gwRole.getId()<temp){
								temp = gwRole.getId();
							}
							
						}
						GwRole gwRole = gwRoleService.selectById(temp);
						role.setGwRoleId(gwRole);
					}
					
					staffinit.setStaffRole(role);*/
					gwAdminUsersServiceImpl.saveOrUpdateXfStaff(staffinit);
					// 将用户信息存入session
					request.getSession().setAttribute("XfStaff", staffinit);
				/*	List<GwRoleGroup> roleGroup = gwRoleGroupService.getRoleGroupsByRoleId(staffinit.getStaffRole().getGwRoleId().getId());
					List<GwMenuTree> menuTreeList = new ArrayList<>();
					for (GwRoleGroup gwRoleGroup : roleGroup) {
						if (gwRoleGroup.getGwMenuTreeId().getFid() == 0) {
							menuTreeList.add(gwRoleGroup.getGwMenuTreeId());
						}
					}

					for (GwMenuTree gwMenuTree : menuTreeList) {
						List<GwRoleGroup> roleGroupList = gwRoleGroupService.getRoleGroupsByRoleId(staffinit.getStaffRole().getGwRoleId().getId(), gwMenuTree.getId());
						List<GwMenuTree> children = new ArrayList<>();
						for (GwRoleGroup gwRoleGroup : roleGroupList) {
							children.add(gwRoleGroup.getGwMenuTreeId());
						}
						if (children.size() != 0) {
							gwMenuTree.setChildrenList(children);
						}
					}
					request.getSession().setAttribute("menuTreeList", menuTreeList);*/
					response.sendRedirect("/ShoppingMallWeb/web/xfmall/getXfMall");
					return;
				} else {
					staff = new XfStaff();
					staff.setXfStaffcode(tempAdminUser.getXfStaffcode());
					staff.setXfName(tempAdminUser.getXfName());
					staff.setXfPassword(tempAdminUser.getXfPassword());
					staff.setStaffRole(tempAdminUser.getStaffRole());
					staff.setEnabled(true);
					// 将用户信息存入session
					request.getSession().setAttribute("XfStaff", staff);
					
					
					if(tempAdminUser.getStaffRole()!=null){
						List<GwRoleGroup> roleGroup = gwRoleGroupService.getRoleGroupsByRoleId(tempAdminUser.getStaffRole().getGwRoleId().getId());
						List<GwMenuTree> menuTreeList = new ArrayList<>();
						for (GwRoleGroup gwRoleGroup : roleGroup) {
							if (gwRoleGroup.getGwMenuTreeId().getFid() == 0) {
								menuTreeList.add(gwRoleGroup.getGwMenuTreeId());
							}
						}

						for (GwMenuTree gwMenuTree : menuTreeList) {
							List<GwRoleGroup> roleGroupList = gwRoleGroupService.getRoleGroupsByRoleId(tempAdminUser.getStaffRole().getGwRoleId().getId(), gwMenuTree.getId());
							List<GwMenuTree> children = new ArrayList<>();
							for (GwRoleGroup gwRoleGroup : roleGroupList) {
								children.add(gwRoleGroup.getGwMenuTreeId());
							}
							if (children.size() != 0) {
								gwMenuTree.setChildrenList(children);
							}
						}
						request.getSession().setAttribute("menuTreeList", menuTreeList);
					}
					response.sendRedirect("/ShoppingMallWeb/web/xfmall/getXfMall");
					return;
				}
			} else {
				String url = request.getRequestURI().toString();
				if (url.equals("/") || url.equals("/ShoppingMallWeb/web/admin/login") || url.equals("/ShoppingMallWeb/")) {
					response.sendRedirect("/ShoppingMallWeb/web/xfmall/getXfMall");
					return;
				}
			}
		}

		if (!isHasNoFilterUrl(request)) {
			XfStaff user = (XfStaff) request.getSession().getAttribute("XfStaff");
			// System.out.println("====后台管理过滤开始======");
			if (user != null) {
				
			} else {
				// 不用拦截的地址
				String url = request.getRequestURI().toString();
				for (String s : IGNORE_URI) {
					if (url.startsWith(s)) {
						chain.doFilter(request, response);
					}
				}
				// ajax请求
				if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
					System.out.println("ajax请求为空");
					// BaseResultVo baseResultVo=new BaseResultVo();
					// Status status=new Status();
					// status.setCode(ResponseCode.SESSION_EXPIRE);//提示代码
					// status.setMsg("会话过期");//提示信息
					// status.setDebugMsg("");//bug信息
					// status.setUrl("");//url
					// //write info
					// baseResultVo.setStatus(status);
					// //CommonUtil.outputAjaxJsonData(baseResultVo,response);
					// PrintWriter printWriter = response.getWriter();
					// printWriter.print("{\"sessionState\":0}");
					// printWriter.flush();
					// printWriter.close();
					response.sendRedirect("/system/sessionExpire.do");
					return;
				} else {
					// 非ajax请求
					// 重定向地址
					System.out.println("非ajax请求");
					response.sendRedirect("/");
					return;
				}
			}
		} else {
			System.out.println("不用拦截");
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

	/**
	 * isHasNoFilterUrl:是否是不需要拦截的url
	 * @date 2016年3月7日
	 * @param request
	 */
	public boolean isHasNoFilterUrl(HttpServletRequest request) {
		// 不用拦截的地址
		String url = request.getRequestURI().toString();
		for (String s : IGNORE_URI) {
			if (url.equals(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * isHasCookie:用户信息的cookie信息是否存在
	 * @author yumaochun
	 * @date 2016年11月2日
	 */
	public boolean isHasCookie(HttpServletRequest request) {
		boolean flag = false;
		Cookie[] cookies = request.getCookies();
		if (null == cookies) {
			// System.out.println("没有cookie==============");
		} else {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				System.out.println("cookie:" + cookie.getName() + ",value:" + cookie.getValue());
				if (name.equals("sso")) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
}
