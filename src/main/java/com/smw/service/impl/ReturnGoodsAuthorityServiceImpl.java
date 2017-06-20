package com.smw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smw.dao.ReturnGoodsAuthorityDao;
import com.smw.pojo.ReturnGoodsAuthority;
import com.smw.service.ReturnGoodsAuthorityService;

/**
 * 退货权限服务层实现接口
 * @author suen
 * @date 2016年7月26日-下午5:26:06
 * @version 1.0
 */
@Service
public class ReturnGoodsAuthorityServiceImpl implements ReturnGoodsAuthorityService {
	
	@Resource
	private ReturnGoodsAuthorityDao returnGoodsAuthorityDao;

	/**
	 * 获取退货权限的员工
	 * @param staffCodeOrName 员工号与名称
	 * @return
	 */
	@Override
	public List<ReturnGoodsAuthority> getRGA(String staffCodeOrName) {
		return returnGoodsAuthorityDao.getRGA(staffCodeOrName);
	}

	/**
	 * 保存或者更新退货权限
	 * @param returnGoodsAuthority 退货权限
	 */
	@Override
	public void saveOrUpdateRGA(ReturnGoodsAuthority returnGoodsAuthority) {
		returnGoodsAuthorityDao.saveOrUpdateRGA(returnGoodsAuthority);
	}

	/**
	 * 根据员工编号获取退货权限
	 * @param xfStaffCode 员工编号
	 * @return
	 */
	@Override
	public ReturnGoodsAuthority getRGAByXfStaffCode(String xfStaffCode) {
		return returnGoodsAuthorityDao.getRGAByXfStaffCode(xfStaffCode);
	}

	/**
	 * 删除退货权限
	 * @param rga 退货权限
	 */
	@Override
	public void deleteRGA(ReturnGoodsAuthority rga) {
		returnGoodsAuthorityDao.deleteRGA(rga);
	}
	
}
