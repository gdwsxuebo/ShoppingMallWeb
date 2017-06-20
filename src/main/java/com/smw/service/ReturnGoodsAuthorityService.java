package com.smw.service;

import java.util.List;

import com.smw.pojo.ReturnGoodsAuthority;

/**
 * 退货权限服务层接口
 * @author suen
 * @date 2016年7月26日-下午5:25:31
 * @version 1.0
 */
public interface ReturnGoodsAuthorityService {

	/**
	 * 获取退货权限的员工
	 * @param staffCodeOrName 员工号与名称
	 * @return
	 */
	List<ReturnGoodsAuthority> getRGA(String staffCodeOrName);

	/**
	 * 保存或者更新退货权限
	 * @param returnGoodsAuthority 退货权限
	 */
	void saveOrUpdateRGA(ReturnGoodsAuthority returnGoodsAuthority);

	/**
	 * 根据员工编号获取退货权限
	 * @param xfStaffCode 员工编号
	 * @return
	 */
	ReturnGoodsAuthority getRGAByXfStaffCode(String xfStaffCode);

	/**
	 * 删除退货权限
	 * @param rga 退货权限
	 */
	void deleteRGA(ReturnGoodsAuthority rga);
	
}
