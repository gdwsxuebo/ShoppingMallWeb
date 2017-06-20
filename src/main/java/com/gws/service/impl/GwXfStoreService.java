package com.gws.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gws.dao.impl.GwXfStoreDao;
import com.smw.pojo.XfStore;

@Service
public class GwXfStoreService {
	@Resource GwXfStoreDao xfStoreDao;
	
	public List<XfStore> getStoreList(){
		return xfStoreDao.getStoreList();
	}

	public List<Map<String, Object>> getAllStoreInfo(){
		return xfStoreDao.getAllStoreInfo();
	}
	public XfStore getStoreInfoByTillid(String tillid){
		return xfStoreDao.getStoreInfoByTillid(tillid);
	}

	public List<Map<String, Object>> getXfStoreList(int page, int rows){
		return	xfStoreDao.getXfStoreList(page,rows);

	};
	
	public List<Map<String, Object>> getAllStoreTree() {
		return xfStoreDao.getAllStoreTree();
	}
	/**
	 * 获取除了中央店铺的其他店铺
	 */
	public List<Map<String, Object>> queryStoreCenterStore(String storecode) {
		
		return xfStoreDao.queryStoreCenterStore(storecode);
	}
	
	/**
	 * 模糊查询
	 */
	public List<Map<String, Object>> getStoreByStorecode(String storeCode){
		return xfStoreDao.getStoreByStorecode(storeCode);
	}
	
	/**
	 *  搜索
	 */
	public List<Map<String, Object>> getStoreByName(String storeName,String storecode){
		return xfStoreDao.getStoreByName(storeName,storecode);
	}
	
}
