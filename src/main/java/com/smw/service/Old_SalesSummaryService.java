package com.smw.service;

import com.smw.pojo.oldSalesSummary.OldSalesSummary;

/**
 * 老销售汇总表服务层接口
 * @author suen
 * @date 2016年5月19日-下午3:38:31
 * @version 1.0
 */
public interface Old_SalesSummaryService{

	/**
	 * 保存就销售数据
	 * @param oldSalesSummary 旧销售
	 */
	void saveOrUpdateOSS(OldSalesSummary oldSalesSummary);

	/**
	 * 获取老的销售数据
	 * @param txdocno 销售单号
	 * @return
	 */
	OldSalesSummary getOSSByTxdocno(String txdocno);

	
}
