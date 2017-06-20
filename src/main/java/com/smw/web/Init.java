package com.smw.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smw.common.util.DateUtil;
import com.smw.pojo.GwMenuTree;
import com.smw.pojo.GwRole;
import com.smw.pojo.GwRoleGroup;
import com.smw.pojo.Sets;
import com.smw.pojo.XfStaff;
import com.smw.pojo.XfStaffRole;
import com.smw.service.GwMenuTreeService;
import com.smw.service.GwRoleGroupService;
import com.smw.service.GwRoleService;
import com.smw.service.SetService;
import com.smw.service.XfMallService;
import com.smw.service.XfStaffService;

/**
 * 初始化
 * 
 * @author suen
 * @date 2016年5月22日-下午1:12:09
 * @version 1.0
 */
@Controller
@RequestMapping("web/init")
public class Init {
	Logger logger = LoggerFactory.getLogger(Init.class);
	
	
	/**
	 * 访问地址集合
	 */
	public static final Map<String, String> INITURL = new HashMap<>();

	/**
	 * 商场
	 */
	@Resource
	private XfMallService xfMallService;

	/**
	 * 员工
	 */
	@Resource
	private XfStaffService xfStaffService;

	/**
	 * 新MIS
	 */
	@Resource
	private JoinMis joinMis;
	
	/**
	 * 设置
	 */
	@Resource
	private SetService setService;
	
	@Resource
	private GwRoleService gwRoleService;
	
	@Resource
	private GwMenuTreeService gwMenuTreeService;
	
	@Resource
	private GwRoleGroupService gwRoleGroupService;

	/**
	 * 获取参数
	 * 
	 * @param DYLX
	 *            调用接口类型
	 * @param data
	 *            数据
	 * @return
	 */
	private Map<String, Object> getParams(String DYLX, String data) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// 接口用户标识
		paramMap.put("YHBS", "E84E7273764D2D77E9FC4E9C521E8C3750");
		// 调用系统类别
		paramMap.put("XTLB", "suen");
		// 调用接口类型
		paramMap.put("DYLX", DYLX);
		// 数据
		paramMap.put("DATA", data);
		return paramMap;
	}

	/**
	 * 初始数据
	 * 
	 * @param misUrl
	 *            mis系统访问地址
	 * @param phpUrl
	 *            php访问地址
	 * @return
	 */
	@RequestMapping("init")
	@ResponseBody
	public Object init(String staffcode, String password, Boolean isOpenOldMis) {
		Map<String, Object> msg = new HashMap<>();
		try {
			if (staffcode == null || password == null) {
				msg.put("flag", false);
				msg.put("msg", "设置失败！");
				return msg;
			} else {
				if (!isOpenOldMis) {
					// 获取商场
					// 访问MIS
					Map<String, Object> mall = (Map<String, Object>) joinMis.getMall();
					if ("1".equals(mall.get("code").toString())) {
						XfStaff xfStaff = new XfStaff();
						// 设置启用
						xfStaff.setEnabled(true);
						// 名称
						xfStaff.setXfName(staffcode);
						// 账号
						xfStaff.setXfStaffcode(staffcode);
						// 登录密码
						xfStaff.setXfPassword(password);
						xfStaff.setIsReturnGoodsAuth(0);
						//菜单权限
						GwRole gwRole = new GwRole();
						gwRole.setCnName("超级管理员");
						gwRole.setEnName("superadmin");
						gwRole.setLabel(3);
						gwRole.setOrderNum(0);
						gwRole.setState(1);
						gwRole.setTime(DateUtil.getCurrentDatetime("-"));
						gwRoleService.saveOrupdateGwRole(gwRole);
						gwMenuTreeService.saveInitAllMenu();
						List<GwMenuTree> menuList = gwMenuTreeService.selectAllList();
						for (GwMenuTree gwMenuTree : menuList) {
							GwRoleGroup roleGroup = new GwRoleGroup();
							roleGroup.setGwRoleId(gwRole);
							roleGroup.setGwMenuTreeId(gwMenuTree);
							roleGroup.setTime(DateUtil.getCurrentDatetime("-"));
							gwRoleGroupService.saveOrUpdate(roleGroup);
						}
						// 权限
						XfStaffRole xfStaffRole = new XfStaffRole();
						xfStaffRole.setAuthority("ROLE_MALL_ADMIN");
						xfStaffRole.setXfStaffcode(xfStaff);
						xfStaffRole.setGwRoleId(gwRole);
						xfStaff.setStaffRole(xfStaffRole);
						// 保存
						xfStaffService.saveOrUpdateXfStaff(xfStaff);
						//移除老MIS开关
						INITURL.remove("isOpenOldMis");
						Sets sets=new Sets();
						sets.setId("isOpenOldMis");
						sets.setValue("false");
						setService.saveOrUpdateSets(sets);
						//已有员工则不需要设置跳转到初始化页面
						INITURL.put("isToInit", "false");
						msg.put("flag", true);
						msg.put("msg", "设置成功！");
						return msg;
					}
				} else {
					XfStaff xfStaff = new XfStaff();
					// 设置启用
					xfStaff.setEnabled(true);
					// 名称
					xfStaff.setXfName(staffcode);
					// 账号
					xfStaff.setXfStaffcode(staffcode);
					// 登录密码
					xfStaff.setXfPassword(password);
					xfStaff.setIsReturnGoodsAuth(0);
					//菜单权限
					GwRole gwRole = new GwRole();
					gwRole.setCnName("超级管理员");
					gwRole.setEnName("superadmin");
					gwRole.setLabel(3);
					gwRole.setOrderNum(0);
					gwRole.setState(1);
					gwRole.setTime(DateUtil.getCurrentDatetime("-"));
					gwRoleService.saveOrupdateGwRole(gwRole);
					gwMenuTreeService.saveInitAllMenu();
					List<GwMenuTree> menuList = gwMenuTreeService.selectAllList();
					for (GwMenuTree gwMenuTree : menuList) {
						GwRoleGroup roleGroup = new GwRoleGroup();
						roleGroup.setGwRoleId(gwRole);
						roleGroup.setGwMenuTreeId(gwMenuTree);
						roleGroup.setTime(DateUtil.getCurrentDatetime("-"));
						gwRoleGroupService.saveOrUpdate(roleGroup);
					}
					// 权限
					XfStaffRole xfStaffRole = new XfStaffRole();
					xfStaffRole.setAuthority("ROLE_MALL_ADMIN");
					xfStaffRole.setXfStaffcode(xfStaff);
					xfStaffRole.setGwRoleId(gwRole);
					xfStaff.setStaffRole(xfStaffRole);
					// 保存
					xfStaffService.saveOrUpdateXfStaff(xfStaff);
					//已有员工则不需要设置跳转到初始化页面
					INITURL.put("isToInit", "false");
					//开老MIS开关放进内存中
					INITURL.put("isOpenOldMis", "true");
					//持久化
					Sets sets=new Sets();
					sets.setId("isOpenOldMis");
					sets.setValue("true");
					setService.saveOrUpdateSets(sets);
					
					msg.put("flag", true);
					msg.put("msg", "设置成功！");
					return msg;
				}
			}
		} catch (Exception e) {
			//出错设置跳转到初始化页面
			INITURL.put("isToInit", "true");
			e.printStackTrace();
			logger.error("管理员初始数据时"+e.getMessage());
		}
		msg.put("flag", false);
		msg.put("msg", "设置失败！");
		return msg;
	}
}
