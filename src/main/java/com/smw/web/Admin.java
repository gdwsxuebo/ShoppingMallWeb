package com.smw.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smw.license.LicenseVerify_val;
import com.smw.pojo.GwMenuTree;
import com.smw.pojo.GwRoleGroup;
import com.smw.pojo.XfStaff;
import com.smw.pojo.XfStaffRole;
import com.smw.service.GwMenuTreeService;
import com.smw.service.GwRoleGroupService;
import com.smw.service.SetService;
import com.smw.service.XfStaffService;

/**
 * 管理登录
 * @author suen
 * @date 2016年5月21日-下午4:01:25
 * @version 1.0
 */
@Controller
@RequestMapping("web/admin")
public class Admin {
	private static final String XfStaff = null;
	Logger logger = LoggerFactory.getLogger(Admin.class); 
	/**
	 * 员工
	 */
	@Resource
	private XfStaffService xfStaffService;
	
	/**
	 * 设置
	 */
	@Resource
	private SetService setService;
	
	@Resource
	private GwMenuTreeService gwMenuTreeService;
	
	@Resource
	private GwRoleGroupService gwRoleGroupService;
	
	/**
	 * 员工登录
	 * @param request
	 * @param staffcode 员工号
	 * @param password  密码
	 * @return
	 */
	@RequestMapping(value="login")
	public Object login(HttpServletRequest request,String staffcode,String password){
		try {
			if(request.getSession().getAttribute("XfStaff")!=null){
				XfStaff xfStaff = (XfStaff)request.getSession().getAttribute("XfStaff");
				List<GwRoleGroup> roleGroup = gwRoleGroupService.getRoleGroupsByRoleId(xfStaff.getStaffRole().getGwRoleId().getId());
				List<GwMenuTree> menuTreeList = new ArrayList<>();
				for (GwRoleGroup gwRoleGroup : roleGroup) {
					if(gwRoleGroup.getGwMenuTreeId().getFid()==0) {
						menuTreeList.add(gwRoleGroup.getGwMenuTreeId());
					}
				}
				
				for (GwMenuTree gwMenuTree : menuTreeList) {
					List<GwRoleGroup> roleGroupList = gwRoleGroupService.getRoleGroupsByRoleId(xfStaff.getStaffRole().getGwRoleId().getId(),gwMenuTree.getId());
					List<GwMenuTree> children = new ArrayList<>();
					for (GwRoleGroup gwRoleGroup : roleGroupList) {
						children.add(gwRoleGroup.getGwMenuTreeId());
					}
					
					if(children.size()!=0) {
						gwMenuTree.setChildrenList(children);
					}
				}
				request.getSession().setAttribute("menuTreeList",menuTreeList);
				return "redirect:/web/xfmall/getXfMall";
			}else if (staffcode==null || password==null || staffcode.length()>15 || password.length()>32) {
				request.getSession().setAttribute("msg", "登录失败！");
			}else{
				XfStaff xfStaff=new XfStaff();
				//账号（编号）
				xfStaff.setXfStaffcode(staffcode);
				//密码
				xfStaff.setXfPassword(password);
				//登录
				xfStaff = xfStaffService.getXfStaff(xfStaff);
				if (xfStaff!=null) {
					//权限
					XfStaffRole staffRole = xfStaff.getStaffRole();
					//员工不可以登录后台管理系统
					if (staffRole==null || "ROLE_STORE_USER".equals(staffRole.getAuthority()) 
							|| "ROLE_STORE_ADMIN".equals(staffRole.getAuthority())) {
						request.getSession().setAttribute("msg", "无权限登录！");
						return "redirect:/login.jsp";
					}
					//清空密码
					xfStaff.setXfPassword("");
					//员工信息存入session中
					request.getSession().setAttribute("XfStaff",xfStaff);
					//清除消息
					request.getSession().setAttribute("msg", "");
					//存放菜单
					List<GwRoleGroup> roleGroup = gwRoleGroupService.getRoleGroupsByRoleId(staffRole.getGwRoleId().getId());
					List<GwMenuTree> menuTreeList = new ArrayList<>();
					for (GwRoleGroup gwRoleGroup : roleGroup) {
						if(gwRoleGroup.getGwMenuTreeId().getFid()==0) {
							menuTreeList.add(gwRoleGroup.getGwMenuTreeId());
						}
					}
					
					for (GwMenuTree gwMenuTree : menuTreeList) {
						List<GwRoleGroup> roleGroupList = gwRoleGroupService.getRoleGroupsByRoleId(staffRole.getGwRoleId().getId(),gwMenuTree.getId());
						List<GwMenuTree> children = new ArrayList<>();
						for (GwRoleGroup gwRoleGroup : roleGroupList) {
							children.add(gwRoleGroup.getGwMenuTreeId());
						}
						
						if(children.size()!=0) {
							gwMenuTree.setChildrenList(children);
						}
					}
					request.getSession().setAttribute("menuTreeList",menuTreeList);
					//检查老MIS开关
					//Sets sets = setService.getSets("isOpenOldMis");
					String isOpenOldMis = Init.INITURL.get("isOpenOldMis");
			    	if("true".equals(isOpenOldMis)) {
			    		request.getSession().setAttribute("isOpenOldMis",true);
			    	}else{
			    		request.getSession().setAttribute("isOpenOldMis",false);
			    	}
			    	//验证收银服务端许可证是否已失效
			    	Boolean verifyLicense = LicenseVerify_val.verifyLicense();
			    	if (!verifyLicense) {
						request.getSession().setAttribute("licenseMsg", "POS Server许可证已失效！");
					}else{
						request.getSession().setAttribute("licenseMsg",null);
					}
					return "redirect:/web/xfmall/getXfMall";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户登录"+e.getMessage());
		}
		request.getSession().setAttribute("msg", "登录失败！");
		return "redirect:/login.jsp";
	}
	
	/**
	 * 退出
	 * @param request
	 * @return
	 */
	@RequestMapping("adminOut")
	public Object out(HttpServletRequest request){
		try {
			request.getSession().setAttribute("XfStaff", null);
			request.getSession().setAttribute("msg", "已退出！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户退出"+e.getMessage());
		}
		return "redirect:/login.jsp";
	}
	
	/**
	 * 验证旧密码是否正确
	 */
	@RequestMapping("/isOldPwd")
	@ResponseBody
	public Object isOldPwd(HttpServletRequest request,String oldPwd){
		XfStaff sessionStaff = (XfStaff) request.getSession().getAttribute("XfStaff");
		XfStaff xfStaff = xfStaffService.getXfStaffByStaffCode(sessionStaff.getXfStaffcode());
		if(xfStaff.getXfPassword().equals(oldPwd)){
			return true;
		}
		return false;
	}
	
	/**
	 * @param oldPwd  旧密码
	 * @param newPwd  新密码
	 */
	@RequestMapping("/editPwd")
	public Object editPwd(HttpServletRequest request, String newPwd){
		XfStaff sessionStaff = (XfStaff) request.getSession().getAttribute("XfStaff");
		XfStaff xfStaff = xfStaffService.getXfStaffByStaffCode(sessionStaff.getXfStaffcode());
		xfStaff.setXfPassword(newPwd);
		xfStaffService.saveOrUpdateXfStaff(xfStaff);
		request.getSession().setAttribute("msg", "修改密码成功，请重新登录！");
		return "redirect:/login.jsp";
	}
	
	
}
