package com.smw.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smw.dao.BestoayDao;
import com.smw.pojo.Bestoay;
import com.smw.service.BestoayService;

/**
 * 翼支付实现接口层
 * @author suen
 * @date 2016年6月27日-下午4:02:00
 * @version  jdk1.8
 */
@Service
public class BestoayServiceImpl implements BestoayService {
	
	@Resource
	private BestoayDao bestoayDao;

	/**
	 * 更新或者保存翼支付
	 * @param bestoay 翼支付
	 */
	@Override
	public void saveOrUpdateBestoay(Bestoay bestoay) {
		bestoayDao.saveOrUpdateBestoay(bestoay);
	}

	/**
	 * 根据支付码查询翼支付
	 * @param barcode 支付码
	 * @return
	 */
	@Override
	public Bestoay getBestoayByBarcode(String barcode) {
		return bestoayDao.getBestoayByBarcode(barcode);
	}
}
