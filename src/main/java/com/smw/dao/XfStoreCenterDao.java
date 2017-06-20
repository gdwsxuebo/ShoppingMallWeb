package com.smw.dao;

import java.util.List;

import com.smw.pojo.XfStore;
import com.smw.pojo.XfStoreCenter;

/**
 * 中央店铺数据访问层接口
 * @author suen
 * @date 2016年5月25日-下午4:42:50
 * @version 1.0
 */
public interface XfStoreCenterDao {

	/**
	 * 根据被关联的店铺查询是哪一个店铺关联的该店铺
	 * @param xfStorecode 被关联的店铺
	 * @return
	 */
	XfStoreCenter getStoreCenter(String xfStorecode);

	/**
	 * 查询中央店铺信息
	 * @param codeOrName 店铺编号或者名称
	 * @return
	 */
	List<XfStoreCenter> getStoreCenterList(String codeOrName);

	/**
	 * 取消设置中央店铺
	 * @param xfStorecode 店铺号
	 */
	void deleteXfStoreCenter(String xfStorecode);

	/**
	 * 设置为中央店铺
	 * @param xfStore 中央店铺对象
	 */
	void saveSingXSC(XfStoreCenter center);

	/**
	 * 删除关联的店铺
	 * @param xfStore 被关联店铺
	 * @param xfStoreCenter  中心店铺
	 */
	void deleteXFSC(XfStore xfStore, XfStore xfStoreCenter);

	/**
	 * 根据中央店铺编号查询子店铺
	 * @param storeCode 中央店铺编号
	 * @return
	 */
	List<XfStoreCenter> getStoreCenterListByStoreCode(String storeCode);


}
