package com.smw.dao;

import java.util.List;
import java.util.Map;

import com.smw.pojo.SalesSummary;
import com.smw.pojo.oldSalesSummary.OldSalesSummary;

/**
 * 老销售汇总表数据访问层接口
 * @author suen
 * @date 2016年5月18日-下午5:17:11
 * @version 1.0
 */
public interface Old_SalesSummaryDao{

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