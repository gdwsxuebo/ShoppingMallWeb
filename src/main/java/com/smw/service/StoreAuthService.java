package com.smw.service;

import java.util.List;

import com.smw.pojo.StoreAuth;
import com.smw.pojo.TillidToStore;

public interface StoreAuthService {

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
	 * 添加或修改
	 */
	void addOrUpdateStoreAuth(StoreAuth storeAuth);
	
	/**
	 * 删除收银机权限配置
	 */
	void deleteStoreAuth(String id);

	List<String> getStoreAuthsByHql();

	void addOrUpdateTillidToStore(TillidToStore tillidToStore);

	void deleteTillidToStoreByTillid(String tillid);

	List<StoreAuth> getListsStoreAuth();

	List<TillidToStore> getListTillidToStores();

	void saveOrUpdatestoreAuths(List<StoreAuth> insParamStoreAuth);

	void saveOrUpdateTillidToStores(List<TillidToStore> insParamTillidToStore);

	TillidToStore getTillidToStoreByTillid(String tillidId);

	List<TillidToStore> getListTillidToStoresByTillid(String tillid);

	List<TillidToStore> getTillidListBystorecode(String storecode);

	List<StoreAuth> getStoreAuthListBystorecode(String storecode);

	void deleteTillidToStoreById(String id);
}
