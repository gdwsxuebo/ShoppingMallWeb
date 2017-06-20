package com.smw.dao.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.smw.dao.BestoayDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.pojo.Bestoay;

/**
 * 翼支付数据访问层实现接口
 * @author suen
 * @date 2016年6月27日-下午4:03:58
 * @version  jdk1.8
 */
@Repository
public class BestoayDaoImpl extends BaseDaoSupport<Bestoay> implements BestoayDao {

	/**
	 * 更新或者保存翼支付
	 * @param bestoay 翼支付
	 */
	@Override
	public void saveOrUpdateBestoay(Bestoay bestoay) {
		saveOrUpdate(bestoay);
	}

	/**
	 * 根据支付码查询翼支付
	 * @param barcode 支付码
	 * @return
	 */
	@Override
	public Bestoay getBestoayByBarcode(String barcode) {
		return get(new BigDecimal(barcode));
	}
	
}
