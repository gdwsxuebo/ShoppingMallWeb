package com.smw.service;

import java.util.List;

import com.smw.pojo.XfStore;

/**
 * 店铺信息服务层接口
 * @author suen
 * @date 2016年5月18日-下午9:03:31
 * @version 1.0
 */
public interface XfStoreService{
	/**
	 *  通过楼宇或业态查询店铺
	 */
	List<XfStore> getStoreByBF(Integer pageIndex, Integer pageSize, String buildings, String formats);

	/**
	 * 带条件查询商铺总数
	 * @param storecode 关联的店铺号
	 * @param key 搜索条件
	 * @return
	 */
	Integer getCount(String storecode, String key);

	/**
	 * 根据店铺编号查询店铺信息
	 * @param storeCode 店铺编号
	 * @return
	 */
	XfStore getXfStoreByStoreCode(String storeCode);

	/**
	 * 获取店铺列表
	 * @return
	 */
	List<XfStore> list();

	/**
	 * 修改或者保存店铺列表
	 * @param stores
	 */
	void saveOrUpdateXfStores(List<XfStore> stores);

	/**
	 * 获取
	 * @param pageIndex 页码
	 * @param pageSize 每页显示的行数
	 * @param xfStaffcode 店铺号
	 * @param key 搜索条件
	 * @return
	 */
	List<XfStore> getXfStoreList(Integer pageIndex, Integer pageSize, String xfStaffcode, String key);

	/**
	 * 保存或者修改单个店铺信息
	 * @param xfStore 店铺对象
	 */
	void saveOrUpdateSingXfStores(XfStore xfStore);
	
	/**
	 * 修改或者保存店铺
	 * @param xs
	 */
	void saveOrUpdateXfStore(XfStore xs);

	/**
	 * 根据店铺编号删除店铺
	 * @param xfStorecode 店铺编号
	 */
	void deleteXfStoreByStCode(String xfStorecode);

	List<XfStore> listAllStore();

	List<XfStore> selectAllxfStore();

	void deleteXfCenterStore(List<String> delCenterStore);

	XfStore getXfStoreByCode(String storeCode);

	List<XfStore> getByXfStoreNameORCode(String codeOrName);
}
