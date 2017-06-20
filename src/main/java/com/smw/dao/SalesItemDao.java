package com.smw.dao;

import java.util.List;

import com.smw.pojo.SalesItem;

/**
 * 销售单货品明细表数据访问层接口
 * @author suen
 * @date 2016年5月18日-下午4:29:24
 * @version 1.0
 */
public interface SalesItemDao{

	/**
	 * 保存售单货品明细
	 * @param salesItem 售单货品明细
	 */
	void saveSalesItem(SalesItem salesItem);

	List<SalesItem> getXfItemByDocCode(String docCode);

	/**
	 * 根据销售单号删除销售货品明细
	 * @param txdocno 销售单号
	 */
	void deleteSIBySSTXD(String txdocno);
    
}