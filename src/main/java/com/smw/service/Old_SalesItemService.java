package com.smw.service;

import java.util.List;


import com.smw.pojo.SalesItem;
import com.smw.pojo.XfItem;
import com.smw.pojo.oldSalesSummary.OldSalesItem;

/**
 * 旧销售单货品明细表服务层接口
 * @author suen
 * @date 2016年5月20日-下午1:50:15
 * @version 1.0
 */
public interface Old_SalesItemService{

	/**
	 * 保存或者更新销售商品
	 * @param oldSalesItem 商品
	 */
	void saveOrUpdateOSI(OldSalesItem oldSalesItem);

	/**
	 * 根据销售单号查询销售商品信息
	 * @param txdocno 销售单号
	 * @return
	 */
	List<OldSalesItem> getOSIListByTxDocno(String txdocno);

	

}
