package com.smw.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smw.dao.XfStoreCenterDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.pojo.XfStore;
import com.smw.pojo.XfStoreCenter;

/**
 * 普通店铺对应中央店铺表数据访问层接口
 * @author suen
 * @date 2016年5月18日-下午5:08:03
 * @version 1.0
 */
@Repository
public class XfStoreCenterDaoImpl extends BaseDaoSupport<XfStoreCenter> implements XfStoreCenterDao {

	/**
	 * 根据被关联的店铺查询是哪一个店铺关联的该店铺
	 * @param xfStorecode 被关联的店铺
	 * @return
	 */
	@Override
	public XfStoreCenter getStoreCenter(String xfStorecode) {
		return getUniObj("FROM XfStoreCenter x WHERE x.xfStorecode.xfStorecode=? AND x.xfCenterstorecode.xfStorecode!=?",xfStorecode, xfStorecode);
	}

	/**
	 * 查询中央店铺信息
	 * @param codeOrName 店铺编号或者名称
	 * @return
	 */
	@Override
	public List<XfStoreCenter> getStoreCenterList(String codeOrName) {
		if (codeOrName==null) {
			return getTList(null, null, "FROM XfStoreCenter x GROUP BY x.xfCenterstorecode.xfStorecode");
		}else{
			return getTList(null, null, "FROM XfStoreCenter x WHERE x.xfCenterstorecode.xfStorecode like ? OR x.xfCenterstorecode.xfName like ? GROUP BY x.xfCenterstorecode.xfStorecode","%"+codeOrName+"%","%"+codeOrName+"%");
		}
	}

	/**
	 * 取消设置中央店铺
	 * @param xfStorecode 店铺号
	 */
	@Override
	public void deleteXfStoreCenter(String xfStorecode) {
		//executeSql("delete FROM xf_store_center where xfCenterstorecode=? and xfStorecode=?", xfStorecode,xfStorecode);
		//删除所有关联中央店铺的数据
		executeSql("delete FROM xf_store_center where xfCenterstorecode=?", xfStorecode);
	}

	/**
	 * 设置为中央店铺
	 * @param xfStore 中央店铺对象
	 */
	@Override
	public void saveSingXSC(XfStoreCenter xfStoreCenter) {
		saveOrUpdate(xfStoreCenter);
	}

	/**
	 * 删除关联的店铺
	 * @param xfStore 被关联店铺
	 * @param xfStoreCenter  中心店铺
	 */
	@Override
	public void deleteXFSC(XfStore xfStore, XfStore xfStoreCenter) {
		executeSql("delete FROM xf_store_center where xfCenterstorecode=? and xfStorecode=?", xfStoreCenter.getXfStorecode(),xfStore.getXfStorecode());
	}

	/**
	 * 根据中央店铺编号查询子店铺
	 * @param storeCode 中央店铺编号
	 * @return
	 */
	@Override
	public List<XfStoreCenter> getStoreCenterListByStoreCode(String storeCode) {
		return getList("xfCenterstorecode.xfStorecode=? AND xfStorecode.xfStorecode not in(?)", storeCode,storeCode);
	}

    
}