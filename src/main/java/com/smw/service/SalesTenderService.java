package com.smw.service;


import java.util.List;

import com.smw.pojo.SalesTender;

/**
 * 销售付款服务层接口
 * @author suen
 * @date 2016年5月20日-下午12:09:07
 * @version 1.0
 */
public interface SalesTenderService{

	/**
	 * 保存销售付款
	 * @param salesTender 销售付款
	 */
	void saveSalesTender(SalesTender salesTender);

	/**
	 * 根据销售单号统计金额
	 * @param txdocno 销售单号
	 * @return
	 */
	List<SalesTender> getSalesTenderListByTxdocno(String txdocno);

	/**
	 * 根据销售单号查询销售单付款明细集合
	 * @param txdocno 销售单号
	 * @return
	 */
	List<SalesTender> getSTSByTxdocno(String txdocno);

	/**
	 * 根据销售单号删除销售单付款明细
	 * @param txdocno 销售单号
	 */
	void deleteSTBySSTXD(String txdocno);

}
