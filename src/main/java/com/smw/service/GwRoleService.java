package com.smw.service;


import java.util.List;

import com.smw.common.util.BaseResultVo;
import com.smw.pojo.GwRole;

/**
 * 
 * 
 * GwRoleService:角色业务层接口
 *
 * @author  shengjinpeng
 * @date    2017年2月21日
 * @version jdk1.8
 *
 */
public interface GwRoleService{

	GwRole selectById(Integer id);

	void saveOrupdateGwRole(GwRole role);

	void deleteById(Integer id);
	
	List<GwRole> selectAllList();
	
	List<GwRole> selectPageList(Integer pageIndex, Integer pageSize);
	
	int getCount();

	BaseResultVo deleteGwRoleById(Integer id);

	Integer getCount(String key);

	List<GwRole> selectPageList(Integer pageIndex, Integer pageSize, String key);
}
