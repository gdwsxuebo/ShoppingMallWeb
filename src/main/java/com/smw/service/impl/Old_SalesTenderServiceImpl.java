package com.smw.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smw.common.util.BasePageResultVo;
import com.smw.dao.Old_SalesTenderDao;
import com.smw.dao.SalesTenderDao;
import com.smw.pojo.SalesTender;
import com.smw.pojo.oldSalesSummary.OldSalesTender;
import com.smw.service.Old_SalesTenderService;
import com.smw.service.SalesTenderService;
/**
 * 老销售付款服务层实现接口
 * @author suen
 * @date 2016年5月20日-下午12:09:51
 * @version 1.0
 */
@Service
public class Old_SalesTenderServiceImpl implements Old_SalesTenderService {

	@Resource
	private  Old_SalesTenderDao old_SalesTenderDao;

	/**
	 * 保存或者更新支付方式
	 * @param oldSalesTender 支付方式
	 */
	@Override
	public void saveOrUpdateOST(OldSalesTender oldSalesTender) {
		old_SalesTenderDao.saveOrUpdateOST(oldSalesTender);
	}

	/**
	 * 根据销售单号获取销售的支付方式
	 * @param txdocno 销售单号
	 * @return
	 */
	@Override
	public List<OldSalesTender> getOSTByTxDocno(String txdocno) {
		return old_SalesTenderDao.getOSTByTxDocno(txdocno);
	}
	
	
}
