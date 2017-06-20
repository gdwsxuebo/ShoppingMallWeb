package com.smw.dao;

import java.util.List;

import com.smw.pojo.GwRoleGroup;

/**
 * 
 * 
 * GwRoleGroupDao:角色权限数据层接口
 *
 * @author  shengjinpeng
 * @date    2017年2月22日
 * @version jdk1.8
 *
 */
public interface GwRoleGroupDao{

	
	void saveOrUpdateRoleGroup(GwRoleGroup roleGroup);
	
	public List<GwRoleGroup> getRoleGroupsByRoleId(Integer roleId);
	
	public void deleteListBy(Integer roleId,Integer[] menuId);
	
	void deleteByRoleId(Integer roleId);

	List<GwRoleGroup> getRoleGroupsByRoleId(Integer roleId, Integer fid);
	
}