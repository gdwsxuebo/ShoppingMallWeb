package com.smw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smw.dao.XfStoreCenterDao;
import com.smw.pojo.XfStore;
import com.smw.pojo.XfStoreCenter;
import com.smw.service.XfStoreCenterService;
import com.smw.service.XfStoreService;

/**
 * 中央店铺服务层实现接口
 * @author suen
 * @date 2016年5月25日-下午4:41:36
 * @version 1.0
 */
@Service
public class XfStoreCenterServiceImpl implements XfStoreCenterService {
	
	@Resource
	private XfStoreCenterDao xfStoreCenterDao;
	

	/**
	 * 根据被关联的店铺查询是哪一个店铺关联的该店铺
	 * @param xfStorecode 被关联的店铺
	 * @return
	 */
	@Override
	public XfStoreCenter getStoreCenter(String xfStorecode) {
		return xfStoreCenterDao.getStoreCenter(xfStorecode);
	}


	/**
	 * 查询中央店铺信息
	 * @param codeOrName 店铺编号或者名称
	 * @return
	 */
	@Override
	public List<XfStoreCenter> getStoreCenterList(String codeOrName) {
		return xfStoreCenterDao.getStoreCenterList(codeOrName);
	}

	/**
	 * 取消设置中心店铺
	 * @param xfStoreService 店铺服务层
	 * @param xfStore 店铺对象
	 */
	@Override
	public void deleteXfStoreCenter(XfStoreService xfStoreService, XfStore xfStore) {
		//取消中央店铺
		xfStore.setXfCenter(false);
		xfStoreService.saveOrUpdateSingXfStores(xfStore);
		//中央店铺表中的数据去掉
		xfStoreCenterDao.deleteXfStoreCenter(xfStore.getXfStorecode());
	}


	/**
	 * 设置中央店铺
	 * @param xfStoreService 店铺服务层
	 * @param xfStore 店铺对象
	 */
	@Override
	public void saveXfStoreCenter(XfStoreService xfStoreService, XfStore xfStore, XfStoreCenter center) {
		if(center.getXfCenterstorecode().getXfStorecode().equals(center.getXfStorecode().getXfStorecode())){
			//设置中央店铺
			xfStore.setXfCenter(true);
		}
		//保存
		xfStoreService.saveOrUpdateSingXfStores(xfStore);
		//设置一个为中央店铺
		xfStoreCenterDao.saveSingXSC(center);
	}


	/**
	 * 删除关联的店铺
	 * @param xfStore 被关联店铺
	 * @param xfStoreCenter  中心店铺
	 */
	@Override
	public void deleteXFSC( XfStore xfStore, XfStore xfStoreCenter) {
		//删除被关联的店铺
		xfStoreCenterDao.deleteXFSC(xfStore,xfStoreCenter);
	}


	/**
	 * 根据中央店铺编号查询子店铺
	 * @param storeCode 中央店铺编号
	 * @return
	 */
	@Override
	public List<XfStoreCenter> getStoreCenterListByStoreCode(String storeCode) {
		return xfStoreCenterDao.getStoreCenterListByStoreCode(storeCode);
	}


}
