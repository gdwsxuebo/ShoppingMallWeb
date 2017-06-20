package com.smw.dao;

import java.util.List;

import com.smw.pojo.StoreAuth;
import com.smw.pojo.TillidToStore;

public interface StoreAuthDao {
	
	/**
	 * 收银机权限总数
	 */
	Integer getCount(String key);
	
	/**
	 * 获取收银机权限集合
	 */
	List<StoreAuth> getStoreAuthList(Integer pageIndex, Integer pageSize, String key);
	
	/**
	 * 获取收银机对象
	 */
	StoreAuth getStoreAuthByHql(String tillid);
	
	/**
	 *  添加或修改
	 */
	void addOrUpdateStoreAuth(StoreAuth storeAuth);
	
	/**
	 * 删除收银机权限配置
	 */
	void deleteStoreAuth(String id);

	List<String> getStoreAuthsByHql();

	List<StoreAuth> getListsStoreAuth();

	void saveOrUpdatestoreAuths(List<StoreAuth> storeAuths);

	List<StoreAuth> getStoreAuthListBystorecode(String storecode);

	
}
