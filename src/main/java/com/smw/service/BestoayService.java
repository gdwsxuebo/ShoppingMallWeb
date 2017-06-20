package com.smw.service;

import com.smw.pojo.Bestoay;

/**
 * 翼支付服务层接口
 * @author suen
 * @date 2016年6月27日-下午4:01:15
 * @version  jdk1.8
 */
public interface BestoayService {

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
