package com.smw.service;


import java.util.List;

import com.smw.pojo.GwRoleGroup;

/**
 * 
 * 
 * GwRoleService:角色权限业务层接口
 *
 * @author  shengjinpeng
 * @date    2017年2月21日
 * @version jdk1.8
 *
 */
public interface GwRoleGroupService{

	void saveOrUpdate(GwRoleGroup roleGroup);
	
	public List<GwRoleGroup> getRoleGroupsByRoleId(Integer roleId);
	
	public List<GwRoleGroup> getRoleGroupsByRoleId(Integer roleId,Integer fid);
	
	public void deleteListBy(Integer roleId,Integer[] menuId);
	
	void deleteByRoleId(Integer roleId);
}
