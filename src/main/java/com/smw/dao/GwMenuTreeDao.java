package com.smw.dao;

import java.util.List;

import com.smw.pojo.GwMenuTree;

/**
 * 
 * 
 * GwMenuTreeDao:菜单数据层接口
 *
 * @author  shengjinpeng
 * @date    2017年2月21日
 * @version jdk1.8
 *
 */
public interface GwMenuTreeDao{

	/**
	 * 
	 * selectAllList:查询所有菜单
	 *
	 * @author   shengjinpeng
	 * @date     2017年2月21日
	 *
	 * @return
	 */
	List<GwMenuTree> selectAllList();

	/**
	 * 
	 * selectListByFid:根据父id获得子列表
	 *
	 * @author   shengjinpeng
	 * @date     2017年2月21日
	 *
	 * @return
	 */
	List<GwMenuTree> selectListByFid(Integer fid);
	
	GwMenuTree selectById(Integer id);
	
	void saveInitAllMenu();

}