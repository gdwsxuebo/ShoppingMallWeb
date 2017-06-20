package com.smw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.smw.dao.XfStoreDao;
import com.smw.pojo.SalesSummary;
import com.smw.pojo.XfStore;
import com.smw.service.SalesItemService;
import com.smw.service.SalesSummaryService;
import com.smw.service.SalesTenderService;
import com.smw.service.XfItemService;
import com.smw.service.XfStaffService;
import com.smw.service.XfStoreService;
import com.smw.service.XfTillidStateService;

/**
 * 店铺信息服务层实现接口
 * 
 * @author suen
 * @date 2016年5月18日-下午9:06:53
 * @version 1.0
 */
@Service
public class XfStoreServiceImpl implements XfStoreService {

	@Resource
	private XfStoreDao XfStoreDao;
	/**
	 *  通过楼宇或业态查询店铺
	 */
	public List<XfStore> getStoreByBF(Integer pageIndex, Integer pageSize, String buildings, String formats){
		return XfStoreDao.getStoreByBF(pageIndex, pageSize, buildings, formats);
	}
	/**
	 * 带条件查询商铺总数
	 * 
	 * @param storecode
	 *            关联的店铺号
	 * @param key
	 *            搜索条件
	 * @return
	 */
	@Override
	public Integer getCount(String storecode, String key) {
		return XfStoreDao.getCount(storecode, key);
	}

	/**
	 * 根据店铺编号查询店铺信息
	 * 
	 * @param storeCode
	 *            店铺编号
	 * @return
	 */
	@Override
	public XfStore getXfStoreByStoreCode(String storeCode) {
		// TODO Auto-generated method stub
		return XfStoreDao.getXfStoreByStoreCode(storeCode);
	}

	/**
	 * 获取店铺列表
	 * 
	 * @return
	 */
	public List<XfStore> list() {
		return XfStoreDao.list();
	}

	/**
	 * 修改或者保存店铺列表
	 * 
	 * @param stores
	 */
	@Override
	public void saveOrUpdateXfStores(List<XfStore> stores) {
		XfStoreDao.saveOrUpdateXfStores(stores);
	}

	/**
	 * 获取
	 * 
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            每页显示的行数
	 * @param xfStaffcode
	 *            店铺号
	 * @param key
	 *            搜索条件
	 * @return
	 */
	@Override
	public List<XfStore> getXfStoreList(Integer pageIndex, Integer pageSize, String xfStaffcode, String key) {
		return XfStoreDao.getXfStoreList(pageIndex, pageSize, xfStaffcode, key);
	}

	/**
	 * 保存或者修改单个店铺信息
	 * 
	 * @param xfStore
	 *            店铺对象
	 */
	@Override
	public void saveOrUpdateSingXfStores(XfStore xfStore) {
		XfStoreDao.saveOrUpdateSingXfStores(xfStore);
	}

	/**
	 * 修改或者保存店铺
	 * 
	 * @param stores
	 */
	@Override
	public void saveOrUpdateXfStore(XfStore xs) {
		XfStoreDao.saveOrUpdateXfStore(xs);
	}

	/**
	 * 根据店铺编号删除店铺
	 * 
	 * @param xfStorecode
	 *            店铺编号
	 */
	@Override
	public void deleteXfStoreByStCode(String xfStorecode) {
		// 删除店铺
		XfStoreDao.deleteXfStoreByStCode(xfStorecode);
	}

	@Override
	public List<XfStore> listAllStore() {

		return XfStoreDao.listAllStore();
	}

	@Override
	public List<XfStore> selectAllxfStore() {

		return XfStoreDao.listAllStore();
	}

	@Override
	public void deleteXfCenterStore(List<String> delCenterStore) {
		XfStoreDao.deleteXfStoreByStCode(delCenterStore);

	}

	@Override
	public XfStore getXfStoreByCode(String storeCode) {
		return XfStoreDao.getXfStoreByCode(storeCode);
	}
	@Override
	public List<XfStore> getByXfStoreNameORCode(String codeOrName) {
		// TODO Auto-generated method stub
		return XfStoreDao.getByXfStoreNameORCode(codeOrName);
	}
}
