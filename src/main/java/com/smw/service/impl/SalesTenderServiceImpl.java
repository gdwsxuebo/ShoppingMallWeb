package com.smw.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smw.common.util.BasePageResultVo;
import com.smw.dao.SalesTenderDao;
import com.smw.pojo.SalesTender;
import com.smw.service.SalesTenderService;
/**
 * 销售付款服务层实现接口
 * @author suen
 * @date 2016年5月20日-下午12:09:51
 * @version 1.0
 */
@Service
public class SalesTenderServiceImpl implements SalesTenderService {

	@Resource
	private  SalesTenderDao salesTenderDao;
	
	/**
	 * 保存销售付款
	 * @param salesTender 销售付款
	 */
	@Override
	public void saveSalesTender(SalesTender salesTender) {
		salesTenderDao.saveSalesTender(salesTender);
	}

	/**
	 * 根据销售单号
	 * @param txdocno 销售单号
	 * @return
	 */
	@Override
	public List<SalesTender> getSalesTenderListByTxdocno(String txdocno) {
		return salesTenderDao.getSalesTenderListByTxdocno(txdocno);
	}

	/**
	 * 根据销售单号查询销售单付款明细集合
	 * @param txdocno 销售单号
	 * @return
	 */
	@Override
	public List<SalesTender> getSTSByTxdocno(String txdocno) {
		return salesTenderDao.getSTSByTxdocno(txdocno);
	}

	/**
	 * 根据销售单号删除销售单付款明细
	 * @param txdocno 销售单号
	 */
	@Override
	public void deleteSTBySSTXD(String txdocno) {
		salesTenderDao.deleteSTBySSTXD(txdocno);
	}
}
