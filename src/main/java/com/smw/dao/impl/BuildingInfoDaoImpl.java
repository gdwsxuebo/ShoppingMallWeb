package com.smw.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.smw.dao.BuildingInfoDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.pojo.BuildingInfo;

@Repository
public class BuildingInfoDaoImpl extends BaseDaoSupport<BuildingInfo> implements BuildingInfoDao{

	public BuildingInfo getBuildingInfoById(String id) {
		
		return null;
	}
	
	
	/**
	 *  保存楼宇信息
	 */
	public void saveBuildingInfo(List<BuildingInfo> buildingInfo) {
		for (BuildingInfo bi : buildingInfo) {
			saveOrUpdate(bi);
		}
	}

	public Integer getCount(String key) {
		String count;
		if(key == null){
			count = executeHQL("select count(1) from BuildingInfo").toString();
		}else{
			count = executeHQL("select count(1) from BuildingInfo x where x.name like ? or x.bm like ",
					"%" + key + "%", "%" + key + "%").toString();
		}
		return count == null ? 0 : Integer.parseInt(count);
	}

	public List<BuildingInfo> getBuildingList(Integer pageIndex, Integer pageSize, String key) {
		List<BuildingInfo> buildingInfos;
		if(key == null){
			buildingInfos=getList(pageIndex,pageSize," state = 1 ");
		}else{
			buildingInfos=getList(pageIndex, pageSize, " name like ? or bm like ? ", "%" + key + "%", "%" + key + "%");
		}
		return buildingInfos;
	}

	/**
	 * 清空表
	 */
	public void delBuilding(){
//		String sql = "TRUNCATE TABLE building_info";
		String sql = "DELETE FROM building_info";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.executeUpdate();
	}

}
