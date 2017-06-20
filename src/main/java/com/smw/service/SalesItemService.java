package com.smw.service;

import java.util.List;


import com.smw.pojo.SalesItem;
import com.smw.pojo.XfItem;

/**
 * 销售单货品明细表服务层接口
 * @author suen
 * @date 2016年5月20日-下午1:50:15
 * @version 1.0
 */
public interface SalesItemService{

	/**
	 * 保存销售单货品明细
	 * @param salesItem 销售单货品明细对象
	 * @return
	 */
	void saveSalesItem(SalesItem salesItem);

	/**
	 * 根据销售单号获取销售单明细
	 * @param docCode
	 * @return
	 */
	List<SalesItem> getXfItemByDocCode(String docCode);

	/**
	 * 根据销售单号删除销售货品明细
	 * @param txdocno 销售单号
	 */
	void deleteSIBySSTXD(String txdocno);


}
