package com.smw.dao;

import com.smw.pojo.Bestoay;

/**
 * 翼支付数据访问层接口
 * @author suen
 * @date 2016年6月27日-下午4:03:24
 * @version  jdk1.8
 */
public interface BestoayDao {

	/**
	 * 更新或者保存翼支付
	 * @param bestoay 翼支付
	 */
	void saveOrUpdateBestoay(Bestoay bestoay);

	/**
	 * 根据支付码查询翼支付
	 * @param barcode 支付码
	 * @return
	 */
	Bestoay getBestoayByBarcode(String barcode);
	
}
