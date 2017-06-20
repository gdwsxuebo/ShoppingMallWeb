package com.smw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smw.dao.StoreAuthDao;
import com.smw.dao.TillidToStoreDao;
import com.smw.pojo.StoreAuth;
import com.smw.pojo.TillidToStore;
import com.smw.service.StoreAuthService;

@Service
public class StoreAuthServiceImpl implements StoreAuthService {

	@Resource
	private StoreAuthDao storeAuthDao;
	
	@Resource
	private TillidToStoreDao tillidToStoreDao;
	
	/**
	 * 收银机权限总数
	 */
	public Integer getCount(String key) {
		return storeAuthDao.getCount(key);
	}

	/**
	 * 获取收银机权限集合
	 */
	public List<StoreAuth> getStoreAuthList(Integer pageIndex, Integer pageSize, String key) {
		return storeAuthDao.getStoreAuthList(pageIndex, pageSize, key);
	}
	
	/**
	 * 获取收银机对象
	 */
	public StoreAuth getStoreAuthByHql(String tillid){
		return storeAuthDao.getStoreAuthByHql(tillid);
	}
	
	/**
	 * 添加或修改
	 */
	public void addOrUpdateStoreAuth(StoreAuth storeAuth){
		storeAuthDao.addOrUpdateStoreAuth(storeAuth);
	}
	
	public void deleteStoreAuth(String id){
		storeAuthDao.deleteStoreAuth(id);
	}

	@Override
	public List<String> getStoreAuthsByHql() {
		// TODO Auto-generated method stub
		return storeAuthDao.getStoreAuthsByHql();
	}

	@Override
	public void addOrUpdateTillidToStore(TillidToStore tillidToStore) {
		// TODO Auto-generated method stub
		tillidToStoreDao.addOrUpdateTillidToStore(tillidToStore);
	}

	@Override
	public void deleteTillidToStoreByTillid(String tillid) {
		// TODO Auto-generated method stub
		tillidToStoreDao.deleteTillidToStoreByTillid(tillid);
	}

	@Override
	public List<StoreAuth> getListsStoreAuth() {
		// TODO Auto-generated method stub
		return storeAuthDao.getListsStoreAuth();
	}

	@Override
	public List<TillidToStore> getListTillidToStores() {
		// TODO Auto-generated method stub
		return tillidToStoreDao.getListTillidToStores();
	}

	@Override
	public void saveOrUpdatestoreAuths(List<StoreAuth> storeAuths) {
		// TODO Auto-generated method stub
		storeAuthDao.saveOrUpdatestoreAuths(storeAuths);
	}

	@Override
	public void saveOrUpdateTillidToStores(List<TillidToStore> tillidToStores) {
		// TODO Auto-generated method stub
		tillidToStoreDao.saveOrUpdateTillidToStores(tillidToStores);
	}

	@Override
	public TillidToStore getTillidToStoreByTillid(String tillidId) {
		// TODO Auto-generated method stub
		return tillidToStoreDao.getTillidToStoreByTillid(tillidId);
	}

	@Override
	public List<TillidToStore> getListTillidToStoresByTillid(String tillid) {
		// TODO Auto-generated method stub
		return tillidToStoreDao.getListTillidToStoresByTillid(tillid);
	}

	@Override
	public List<TillidToStore> getTillidListBystorecode(String storecode) {
		// TODO Auto-generated method stub
		return tillidToStoreDao.getTillidListBystorecode(storecode);
	}

	@Override
	public List<StoreAuth> getStoreAuthListBystorecode(String storecode) {
		// TODO Auto-generated method stub
		return storeAuthDao.getStoreAuthListBystorecode(storecode);
	}

	@Override
	public void deleteTillidToStoreById(String id) {
		// TODO Auto-generated method stub
		tillidToStoreDao.deleteTillidToStoreById(id);
	}
	

}
