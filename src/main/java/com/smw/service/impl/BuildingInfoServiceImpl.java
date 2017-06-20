package com.smw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smw.dao.BuildingInfoDao;
import com.smw.pojo.BuildingInfo;
import com.smw.service.BuildingInfoService;

@Service
public class BuildingInfoServiceImpl implements BuildingInfoService{
	
	@Resource
	private BuildingInfoDao buildingInfoDao;
	
	public void saveBuildingInfo(List<BuildingInfo> buildingInfo) {
		buildingInfoDao.saveBuildingInfo(buildingInfo);
	}

	@Override
	public Integer getCount(String key) {
		return buildingInfoDao.getCount(key);
	}

	@Override
	public List<BuildingInfo> getBuildingList(Integer pageIndex, Integer pageSize, String key) {
		return buildingInfoDao.getBuildingList(pageIndex, pageSize, key);
	}
	
	public void delBuilding(){
		buildingInfoDao.delBuilding();
	}

	public BuildingInfo getBuildingInfoById(String id){
		return buildingInfoDao.getBuildingInfoById(id);
	}
		
}
