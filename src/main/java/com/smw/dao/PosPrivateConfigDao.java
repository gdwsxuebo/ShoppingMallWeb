package com.smw.dao;

import java.util.List;

import com.smw.pojo.PosConfigModel;

public interface PosPrivateConfigDao {
	
	/**
	 *  收银机私有配置
	 */
	Integer getCount(String key);
	
	/**
	 *  收银机私有配置集合
	 */
	List<PosConfigModel> getPosPrivateConfigList(Integer pageIndex, Integer pageSize, String key);
	
	/**
	 * 获取收银机私有配置对象
	 */
	public PosConfigModel getPosConfigByTillid(String tillid);

	void addOrUpdatePosPrivateConfig(PosConfigModel posConfigModel);

	List<PosConfigModel> selectAllList();

	void deletePosPrivateConfigByid(String id);

	PosConfigModel selectById(Integer id);

	PosConfigModel getPosPrivateConfigById(Integer id);

	List<PosConfigModel> getPosPrivateConfigsList();
}
