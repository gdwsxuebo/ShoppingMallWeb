package com.smw.service;

import java.util.List;

import com.smw.pojo.BuildingInfo;

public interface BuildingInfoService {
	
	/**
	 * 保存楼宇信息
	 */
	void saveBuildingInfo(List<BuildingInfo> buildingInfo);
	
	
	/**
	 * 获取总数
	 */
	Integer getCount(String key);
	
	
	/**
	 *  获取集合
	 */
	List<BuildingInfo> getBuildingList(Integer pageIndex, Integer pageSize, String key);
	
	/**
	 * 清空楼宇表
	 */
	void delBuilding();
	
	
	/**
	 * 通过店铺号获取楼宇
	 */
	 BuildingInfo getBuildingInfoById(String id);
	
}
