package com.smw.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gws.service.impl.synchv61Service;
import com.smw.common.util.DateUtil;
import com.smw.common.util.PropertiesUtil;
import com.smw.common.util.SetEnum;
import com.smw.common.util.StringUtil;
import com.smw.common.util.paging.Paging;
import com.smw.pojo.GwRole;
import com.smw.pojo.Sets;
import com.smw.pojo.XfStaff;
import com.smw.pojo.XfStaffRole;
import com.smw.pojo.XfStore;
import com.smw.service.GwRoleService;
import com.smw.service.SetService;
import com.smw.service.StaffRoleService;
import com.smw.service.XfStaffService;
import com.smw.service.XfStoreService;
import com.smw.utils.XfStoreToPush;

/**
 * 员工
 * @author suen
 * @date 2016年5月26日-下午2:02:55
 * @version 1.0
 */
@Controller
@RequestMapping("web/xfStaff")
public class Staff {
	Logger logger = LoggerFactory.getLogger(Staff.class);
	@Resource
	private XfStaffService xfStaffService;
	/**
	 * 店铺
	 */
	@Resource
	private XfStoreService xfStoreService;
	
	@Resource
	private GwRoleService gwRoleService;
	
	/**
	 * 员工角色
	 */
	@Resource
	private StaffRoleService staffRoleService;
	
	@Resource
	private SetService setService;

	@Autowired synchv61Service synchv61;
	/**
	 * 获取员工
	 */
	@RequestMapping("getStaff")
	public Object getStaff(HttpServletRequest request, Integer pageIndex, Integer pageSize, String key) {
		PropertiesUtil pUtil = new PropertiesUtil("systemConfig.properties");
		request.setAttribute("misorv61", pUtil.readProperty("gdws.ifs.misorv61"));
		try {
			if (pageIndex == null) {
				pageIndex = Integer.parseInt(SetEnum.pageIndex.getValue());
			}
			if (pageSize == null) {
				pageSize = Integer.parseInt(SetEnum.pageSize.getValue());
			}
			if (key != null) {
				key= new String(key.getBytes("iso-8859-1"),"utf-8");
				key = key.trim();
				request.setAttribute("key", key);
			}
			// 员工总数
			Integer totalCount = 0;
			// 员工集合
			List<XfStaff> xfStaffs;
			// 员工
			XfStaff xfStaff = (XfStaff) request.getSession().getAttribute("XfStaff");
			// 权限名称
			String authority = xfStaff.getStaffRole().getAuthority();
			if ("ROLE_MALL_ADMIN".equals(authority)) {
				// 获取总数
				totalCount = xfStaffService.getCount(null, key);
				// 获取集合
				xfStaffs = xfStaffService.getXfStaffList(pageIndex, pageSize, null, key);
			} else if ("ROLE_STORE_ADMIN".equals(authority)) {
				// 获取总数
				totalCount = xfStaffService.getCount(xfStaff.getXfIssuestore().getXfStorecode(), key);
				// 获取集合
				xfStaffs = xfStaffService.getXfStaffList(pageIndex, pageSize,
						xfStaff.getXfIssuestore().getXfStorecode(), key);
			} else {
				xfStaffs = new ArrayList<>();
			}
			for(XfStaff staff:xfStaffs){
				staff.setXfPassword("######");
				XfStaffRole role=staff.getStaffRole();
				if(role!=null)  role.setRoleName(getRoleInof(role.getAuthority()));
				staff.setStaffRole(role);
			}
			// 封装到分页对象中
			Paging<XfStaff> paging = new Paging<>(pageIndex, pageSize, totalCount, xfStaffs);
			// 放在request中
			request.getSession().setAttribute("paging", paging);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取员工"+e.getMessage());
		}
		// 设置菜单选中项
		request.getSession().setAttribute("select", "staff");
		return "/staff";
	}

	/**
	 * 获取商铺集合
	 */
	@RequestMapping("getStores")
	@ResponseBody
	public Object getStores() {
		List<XfStore> xfStoreList = xfStoreService.getXfStoreList(null, null, null, null);
		return xfStoreList;
	}
	
	/**
	 * 获取角色
	 */
	@RequestMapping("getRoles")
	@ResponseBody
	public Object getRoles() {
		List<GwRole> gwRoleList = gwRoleService.selectAllList();
		return gwRoleList;
	}

	/**
	 * 添加员工
	 * @param request
	 * @param pageIndex 页码
	 * @param pageSize 每页显示的行数
	 * @param key 搜索条件
	 * @param xfStaff 员工信息
	 * @param selectRole  选择的权限
	 * @param selectStore  选择的店铺
	 */
	@RequestMapping("addStaff")
	public Object addStaff(HttpServletRequest request, Integer pageIndex, Integer pageSize, String key,
			String xfStaffcode, String xfName, String xfPassword,Integer roleGroup, Boolean enabled, Integer selectRole,
			String selectStore) {
		if (StringUtil.isNUllStr(key)) {
			key = null;
		}
		try {
			if (StringUtil.isNUllStr(xfStaffcode) || StringUtil.isNUllStr(xfPassword) || selectRole == null
					|| xfStaffcode.length() > 36 || xfPassword.length() > 32 || xfName.length() > 36) {
				return getStaff(request, pageIndex, pageSize, key);
			} else {
				XfStore storeCode = null;
				// 如果不是设置商场管理员则肯定关联店铺
				if (selectRole != 3) {
					// 查询店铺
					storeCode = xfStoreService.getXfStoreByStoreCode(selectStore);
					if (storeCode == null) {
						return getStaff(request, pageIndex, pageSize, key);
					}
				}
				XfStaff staff = new XfStaff();
				staff.setEnabled(true);
				staff.setXfStaffcode(xfStaffcode);	// 设置编号
				staff.setXfPassword(xfPassword);	// 设置密码
				staff.setIsReturnGoodsAuth(0);  //是否有退货权限 ，默认无  1.有   0.无
				if (!StringUtil.isNUllStr(xfName)) {
					// 设置名称
					staff.setXfName(xfName);
				}
				// 关联店铺
				staff.setXfIssuestore(storeCode);
				// 设置启用
				if (enabled != null && enabled == true) {
					staff.setEnabled(true);
				} else {
					staff.setEnabled(false);
				}
				// 设置权限名称
				XfStaffRole role = new XfStaffRole();
				if (selectRole == 3) {role.setAuthority("ROLE_MALL_ADMIN");// 商场
				} else if (selectRole == 2) {role.setAuthority("ROLE_STORE_ADMIN");// 商铺
				} else if(selectRole == 1){role.setAuthority("ROLE_STORE_USER");// 员工
				}else {role.setAuthority("ROLE_STORE_SHOP_ASSISTANT"); staff.setXfPassword("$$$$$$");}//营业员
				role.setXfStaffcode(staff);
				GwRole gwRole = gwRoleService.selectById(roleGroup);
				role.setGwRoleId(gwRole);
				staff.setStaffRole(role);
				// 保存
				xfStaffService.saveOrUpdateXfStaff(staff);
				
				
				List<XfStore> countList = new ArrayList<>();
				countList.add(storeCode);
				XfStoreToPush.ToPushByXfStore("staff", countList);//添加员工，发送推送信息
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加员工"+e.getMessage());
		}
		return getStaff(request, pageIndex, pageSize, key);
	}

	/**
	 * 检查员工是否已存在
	 * @param xfStaffcode 员工编号
	 * @return
	 */
	@RequestMapping("checkXfStaffcode")
	@ResponseBody
	public Object checkXfStaffcode(String xfStaffcode) {
		if (StringUtil.isNUllStr(xfStaffcode)) {
			return true;
		}
		XfStaff xfStaff = xfStaffService.getXfStaffByStaffCode(xfStaffcode);
		return xfStaff != null;
	}

	/**
	 * 根据员工编号删除员工
	 * @param xfStaffCode 员工信息
	 * @return
	 */
	@RequestMapping("deleteXfStaffByCode")
	@ResponseBody
	public Object deleteXfStaffByCode(String xfStaffCode) {
		if (StringUtil.isNUllStr(xfStaffCode)) {
			return false;
		}
		// 删除
		staffRoleService.deleteStaffRole(xfStaffCode);
		xfStaffService.deleteXfStaffBySC(xfStaffCode);
		return true;
	}

	/**
	 * 根据员工编号获取员工信息
	 * @param xfStaffCode  员工编号
	 * @return
	 */
	@RequestMapping("getXfStaff")
	@ResponseBody
	public Object getXfStaff(String xfStaffCode) {
		if (StringUtil.isNUllStr(xfStaffCode)) {
			return null;
		}
		System.out.println("xfStaffCode--------------------->"+xfStaffCode);
		Map<String, Object> map = new HashMap<>();
		XfStaff xfStaff = xfStaffService.getXfStaffByStaffCode(xfStaffCode);
		// 员工编号
		map.put("xfStaffcode", xfStaff.getXfStaffcode());
		// 名称
		map.put("xfName", xfStaff.getXfName());
		// 密码
		map.put("xfPassword",xfStaff.getXfPassword());
		// 是否启用
		map.put("enabled", xfStaff.getEnabled());
		
		String authority = "";
		if(xfStaff.getStaffRole()!=null){
			authority = xfStaff.getStaffRole().getAuthority();
		}
		
		
		if ("ROLE_MALL_ADMIN".equals(authority)) {
			map.put("staffRoleInt", 3);// 商场  权限对应的数字
		} else if ("ROLE_STORE_ADMIN".equals(authority)) {
			map.put("staffRoleInt", 2);	// 商铺  权限对应的数字
		} else if("ROLE_STORE_USER".equals(authority)){
			map.put("staffRoleInt", 1);	// 员工 权限对应的数字
		}else {
			map.put("staffRoleInt", 4);
		}
		//商铺
		XfStore xfIssuestore = xfStaff.getXfIssuestore();
		map.put("xfStorecode",xfIssuestore!=null?xfIssuestore.getXfStorecode():"");
		if(xfStaff.getStaffRole()!=null){
			map.put("gwRoleId",xfStaff.getStaffRole().getGwRoleId().getId());
		}
		
		return map;
	}
	
	/**
	 * 修改员工
	 * @param request
	 * @param pageIndex 页码
	 * @param pageSize 每页显示的行数
	 * @param key  搜索条件
	 * @param xfStaff 员工信息
	 * @param selectRole 选择的权限
	 * @param selectStore 选择的店铺
	 */
	@RequestMapping("updateStaff")
	public Object updateStaff(HttpServletRequest request, Integer pageIndex, Integer pageSize, String key,
			String xfStaffcode, String xfName, String xfPassword, Boolean enabled, Integer selectRole,
			String selectStore,Integer roleGroup) {
		if (StringUtil.isNUllStr(key)) {
			key = null;
		}
		try {
			if (StringUtil.isNUllStr(xfStaffcode) || StringUtil.isNUllStr(xfPassword) || selectRole == null
					|| xfStaffcode.length() > 36 || xfPassword.length() > 32 || xfName.length() > 36) {
				return getStaff(request, pageIndex, pageSize, key);
			} else {
				XfStaff staff = xfStaffService.getXfStaffByStaffCode(xfStaffcode);
				if (staff==null) {
					return getStaff(request, pageIndex, pageSize, key);
				}
				XfStore storeCode = null;
				// 如果不是设置商场管理员则肯定关联店铺
				if (selectRole != 3) {
					// 查询店铺
					storeCode = xfStoreService.getXfStoreByStoreCode(selectStore);
					if (storeCode == null) {
						return getStaff(request, pageIndex, pageSize, key);
					}
				}
				// 设置编号
				staff.setXfStaffcode(xfStaffcode);
				staff.setEnabled(true);
				// 设置密码
				staff.setXfPassword(xfPassword);
				if (!StringUtil.isNUllStr(xfName)) {
					// 设置名称
					staff.setXfName(xfName);
				}
				// 关联店铺
				staff.setXfIssuestore(storeCode);
				// 设置启用
				if (enabled != null && enabled == true) {
					staff.setEnabled(true);
				} else {
					staff.setEnabled(false);
				}
				// 设置权限名称
				XfStaffRole role = new XfStaffRole();
				
				if(staff.getStaffRole()!=null){
					role = staff.getStaffRole();
				}else{
					role.setXfStaffcode(staff);
					staff.setStaffRole(role);
				}
				
				if (selectRole == 3) {
					// 商场
					role.setAuthority("ROLE_MALL_ADMIN");
				} else if (selectRole == 2) {
					// 商铺
					role.setAuthority("ROLE_STORE_ADMIN");
				} else if(selectRole == 4){
					role.setAuthority("ROLE_STORE_SHOP_ASSISTANT");
				} else {
					// 员工
					role.setAuthority("ROLE_STORE_USER");
				}
				GwRole gwRole = gwRoleService.selectById(roleGroup);
				role.setGwRoleId(gwRole);
				// 保存
				xfStaffService.saveOrUpdateXfStaff(staff);
				
				List<XfStore> countList = new ArrayList<>();
				countList.add(storeCode);
				XfStoreToPush.ToPushByXfStore("staff", countList);//修改员工，发送推送信息
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改员工"+e.getMessage());
		}
		return getStaff(request, pageIndex, pageSize, key);
	}
	
	/**
	 * 根据店铺编号或者名称查询店铺
	 * @param codeOrName 店铺编号或者名称
	 * @return
	 */
	@RequestMapping(value="getXfStoreByCodeOrName")
	@ResponseBody
	public Object getXfStoreByCodeOrName(String codeOrName){
		List<XfStore> xfStoreList = xfStoreService.getXfStoreList(null, null, null, codeOrName);
		return xfStoreList;
	}
	public String getRoleInof(String roleCode){
		if("ROLE_MALL_ADMIN".equalsIgnoreCase(roleCode)) return "商场管理员";
		if("ROLE_STORE_ADMIN".equalsIgnoreCase(roleCode)) return "店铺管理员";
		if("ROLE_STORE_USER".equalsIgnoreCase(roleCode)) return "店铺员工";
		if("ROLE_STORE_SHOP_ASSISTANT".equalsIgnoreCase(roleCode)) return "店铺营业员";
		return "";
	}
	
	/**
	 * 更新员工
	 * @return
	 */
	@RequestMapping("refreshStaff")
	@ResponseBody
	public Object refreshStore(HttpServletRequest request){
		Map<String, Object> map=new HashMap<>();
		try {
				//从V61获取员工数据
				synchv61.savesynchStaff();
				logger.info("同步员工信息成功----------------");
				map.put("flag", true);
				map.put("msg", "更新员工信息成功！请刷新！");
				Sets sets = new Sets();
				sets.setId("updateTime");
				sets.setValue(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
				setService.saveOrUpdateSets(sets);
				return map;
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新员工"+e.getMessage());
		}
		map.put("flag", false);
		map.put("msg", "更新员工失败！");
		return map;
	}
}
