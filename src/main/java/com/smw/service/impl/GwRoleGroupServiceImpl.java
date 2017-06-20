package com.smw.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smw.dao.GwRoleGroupDao;
import com.smw.pojo.GwRoleGroup;
import com.smw.service.GwRoleGroupService;

/**
 * 
 * 
 * GwRoleServiceImpl:角色业务层实现
 *
 * @author  shengjinpeng
 * @date    2017年2月21日
 * @version jdk1.8
 *
 */
@Service
public class GwRoleGroupServiceImpl implements GwRoleGroupService {

	@Resource
	private GwRoleGroupDao gwRoleGroupDao;

	public void saveOrUpdate(GwRoleGroup roleGroup) {
		gwRoleGroupDao.saveOrUpdateRoleGroup(roleGroup);
	}

	public List<GwRoleGroup> getRoleGroupsByRoleId(Integer roleId) {
		return gwRoleGroupDao.getRoleGroupsByRoleId(roleId);
	}

	public void deleteListBy(Integer roleId, Integer[] menuId) {
		gwRoleGroupDao.deleteListBy(roleId, menuId);
	}

	public void deleteByRoleId(Integer roleId) {
		gwRoleGroupDao.deleteByRoleId(roleId);
	}

	public List<GwRoleGroup> getRoleGroupsByRoleId(Integer roleId, Integer fid) {
		return gwRoleGroupDao.getRoleGroupsByRoleId(roleId,fid);
	}


	
}
