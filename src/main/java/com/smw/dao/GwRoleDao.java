package com.smw.dao;

import java.util.List;

import com.smw.pojo.GwRole;

/**
 * 
 * 
 * GwRoleDao:角色数据层接口
 *
 * @author  shengjinpeng
 * @date    2017年2月21日
 * @version jdk1.8
 *
 */
public interface GwRoleDao{


	GwRole selectById(Integer id);

	void saveOrupdateGwRole(GwRole role);

	void deleteById(Integer id);
	
	List<GwRole> selectAllList();
	
	List<GwRole> selectPageList(Integer pageIndex, Integer pageSize);
	
	Long getCount();

	Object getCount(String key);

	List<GwRole> selectPageList(Integer pageIndex, Integer pageSize, String key);
    
}