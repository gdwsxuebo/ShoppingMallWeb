package com.smw.service;


import java.util.List;

import com.smw.pojo.SalesTender;
import com.smw.pojo.oldSalesSummary.OldSalesTender;

/**
 * 老销售付款服务层接口
 * @author suen
 * @date 2016年5月20日-下午12:09:07
 * @version 1.0
 */
public interface Old_SalesTenderService{

	/**
	 * 保存或者更新支付方式
	 * @param oldSalesTender 支付方式
	 */
	void saveOrUpdateOST(OldSalesTender oldSalesTender);

	/**
	 * 根据销售单号获取销售的支付方式
	 * @param txdocno 销售单号
	 * @return
	 */
	List<OldSalesTender> getOSTByTxDocno(String txdocno);

	

}
