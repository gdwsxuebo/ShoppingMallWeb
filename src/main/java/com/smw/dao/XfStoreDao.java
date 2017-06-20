package com.smw.dao;

import java.util.List;

import com.smw.pojo.XfStore;

/**
 * 店铺信息表数据访问层接口
 * @author suen
 * @date 2016年5月18日-下午4:38:38
 * @version 1.0
 */
public interface XfStoreDao{
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

	List<XfStore> list();

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
    
	void saveOrUpdateXfStore(XfStore xs);

	/**
	 * 根据店铺编号删除店铺
	 * @param xfStorecode 店铺编号
	 */
	void deleteXfStoreByStCode(String xfStorecode);

	List<XfStore> listAllStore();

	void deleteXfStoreByStCode(List<String> delCenterStore);

	XfStore getXfStoreByCode(String storeCode);
	List<XfStore> getByXfStoreNameORCode(String codeOrName);
}