package com.smw.dao;

import java.util.List;

import com.smw.pojo.SalesTender;

/**
 * 销售单付款明细表数据访问层接口
 * @author suen
 * @date 2016年5月18日-下午5:26:35
 * @version 1.0
 */
public interface SalesTenderDao{

	/**
	 * 保存销售付款
	 * @param salesTender 销售付款
	 */
	void saveSalesTender(SalesTender salesTender);

	/**
	 * 根据销售单号
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