package com.smw.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smw.common.util.SetEnum;
import com.smw.common.util.StringUtil;
import com.smw.common.util.paging.Paging;
import com.smw.pojo.ReturnGoodsAuthority;
import com.smw.pojo.XfStaff;
import com.smw.service.ReturnGoodsAuthorityService;
import com.smw.service.XfStaffService;

/**
 * 退货权限
 * @author suen
 * @date 2016年7月26日-下午5:20:23
 * @version 1.0
 */
@Controller
@RequestMapping("web/returnGoodsAuthority")
public class ReturnGoodsAuthorityWeb {
	Logger logger = LoggerFactory.getLogger(ReturnGoodsAuthorityWeb.class);

	/** 退货权限 */
	@Resource
	private ReturnGoodsAuthorityService returnGoodsAuthorityService;

	/** 员工 */
	@Resource
	private XfStaffService xfStaffService;

	/** 获取可用员工 */
	@RequestMapping("getReturnGoodsAuthority")
	public Object getReturnGoodsAuthority(HttpServletRequest request, Integer pageIndex, Integer pageSize, String key, Integer auth) {
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
			if(auth != null){
				request.setAttribute("auth", auth);
			}
			// 员工总数
			Integer totalCount =  xfStaffService.getEnableStaffCount(key,auth);
			// 员工集合
			List<XfStaff> xfStaffs = xfStaffService.getEnableStaff(pageIndex, pageSize, key,auth);
			// 封装到分页对象中
			Paging<XfStaff> paging = new Paging<>(pageIndex, pageSize, totalCount, xfStaffs);
			// 放在request中
			request.getSession().setAttribute("paging", paging);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("select", "returnGoodsAuthority");
		return "returnGoodsAuthority/returnGoodsAuthority";
	}
	
	/**
	 * 授权
	 */
	@RequestMapping("/staffAuthority")
	@ResponseBody
	public Object staffAuthority(HttpServletRequest request, String staffCode){
		try {
			if (StringUtil.isNUllStr(staffCode)) {
				return false;
			}else{
				XfStaff xfStaff = xfStaffService.getXfStaffByStaffCode(staffCode);
				if(xfStaff != null){
					xfStaff.setIsReturnGoodsAuth(1); // 授权
					xfStaffService.saveOrUpdateXfStaff(xfStaff);
				}else{
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * 取消授权
	 */
	@RequestMapping("/staffCancelAuthority")
	@ResponseBody
	public Object staffCancelAuthority(HttpServletRequest request, String staffCode){
		try {
			if (StringUtil.isNUllStr(staffCode)) {
				return false;
			}else{
				XfStaff xfStaff = xfStaffService.getXfStaffByStaffCode(staffCode);
				if(xfStaff != null){
					xfStaff.setIsReturnGoodsAuth(0); // 取消授权
					xfStaffService.saveOrUpdateXfStaff(xfStaff);
				}else{
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	
	//=========================================================================================

	/**
	 * 添加退货权限
	 * @param xfStaffCode  员工编号
	 */
	@RequestMapping(value = "addReturnGoodsAuthority")
	@ResponseBody
	public Object addReturnGoodsAuthority(String xfStaffCode) {
		if (StringUtil.isNUllStr(xfStaffCode)) {
			return false;
		} else {
			// 根据员工编号获取员工信息
			XfStaff xfStaff = xfStaffService.getXfStaffByStaffCode(xfStaffCode);
			if (xfStaff != null) {
				// 退货权限
				ReturnGoodsAuthority returnGoodsAuthority = new ReturnGoodsAuthority();
				// 设置员工
				returnGoodsAuthority.setStaffcode(xfStaff);
				// 保存
				returnGoodsAuthorityService.saveOrUpdateRGA(returnGoodsAuthority);
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 删除退货权限的员工
	 * @param xfStaffCode 员工编号
	 */
	@RequestMapping(value = "delReturnGoodsAuthority")
	@ResponseBody
	public Object delReturnGoodsAuthority(String xfStaffCode) {
		if (StringUtil.isNUllStr(xfStaffCode)) {
			return false;
		} else {
			// 根据员工编号获取退货权限
			ReturnGoodsAuthority rga = returnGoodsAuthorityService.getRGAByXfStaffCode(xfStaffCode);
			if (rga != null) {
				returnGoodsAuthorityService.deleteRGA(rga);
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 搜索员工
	 * @param searchXfCodeOrName  员工编号与员工名称
	 * @param type  类型 0未退货权限的员工 1有退货权限的员工
	 */
	@RequestMapping(value = "searchXfStaff")
	@ResponseBody
	public Object searchXfStaff(String searchXfCodeOrName, Integer type) {
		// 未退货权限员工
		List<Map<String, Object>> xfStaffs = new ArrayList<>();
		// 有退货权限员工
		List<Map<String, Object>> xfStaffsRGA = new ArrayList<>();
		if (StringUtil.isNUllStr(searchXfCodeOrName)) {
			searchXfCodeOrName = null;
		}
		// 没有退货权限员工
		if (type == 0) {
			// 未退货权限员工
			List<XfStaff> xfStaffList = xfStaffService.getXfStaffList(null, null, null, searchXfCodeOrName);
			if (xfStaffList != null && xfStaffList.size() > 0) {
				Map<String, Object> map;
				for (XfStaff xfStaff : xfStaffList) {
					// 判断是否在有退货权限中
					if (returnGoodsAuthorityService.getRGAByXfStaffCode(xfStaff.getXfStaffcode()) == null) {
						map = new HashMap<>();
						// 员工编号
						map.put("xfStaffcode", xfStaff.getXfStaffcode());
						// 员工名称
						map.put("xfName", xfStaff.getXfName());
						// 所属店铺编号
						map.put("xfIssuestore", xfStaff.getXfIssuestore());
						xfStaffs.add(map);
					}
				}
			}
			return xfStaffs;
		}
		// 有退货权限员工
		else {
			// 查询有退货权限
			List<ReturnGoodsAuthority> rga = returnGoodsAuthorityService.getRGA(searchXfCodeOrName);
			if (rga != null && rga.size() > 0) {
				Map<String, Object> map;
				XfStaff xfStaff;
				for (ReturnGoodsAuthority returnGoodsAuthority : rga) {
					map = new HashMap<>();
					// 员工信息
					xfStaff = returnGoodsAuthority.getStaffcode();
					// 员工编号
					map.put("xfStaffcode", xfStaff.getXfStaffcode());
					// 员工名称
					map.put("xfName", xfStaff.getXfName());
					// 所属店铺编号
					map.put("xfIssuestore", xfStaff.getXfIssuestore());
					xfStaffsRGA.add(map);
				}
			}
			return xfStaffsRGA;
		}
	}
}
