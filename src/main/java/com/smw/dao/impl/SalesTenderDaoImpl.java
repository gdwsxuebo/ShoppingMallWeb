package com.smw.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.smw.common.util.BasePageResultVo;
import com.smw.dao.SalesTenderDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.pojo.SalesTender;
import com.smw.pojo.XfTender;

/**
 * 销售付款数据访问层实现接口
 * @author suen
 * @date 2016年5月20日-下午12:11:08
 * @version 1.0
 */
@Repository
public class SalesTenderDaoImpl extends BaseDaoSupport<SalesTender> implements SalesTenderDao {

	/**
	 * 保存销售付款
	 * @param salesTender 销售付款
	 */
	@Override
	public void saveSalesTender(SalesTender salesTender) {
		save(salesTender);
	}

	/**
	 * 根据销售单号
	 * @param txdocno 销售单号
	 * @return
	 */
	@Override
	public List<SalesTender> getSalesTenderListByTxdocno(String txdocno) {
		Query createQuery = getSession().createQuery("SELECT s.payamount as payamount,s.tendercode as tendercode,s.gwPaymentRate as gwPaymentRate FROM SalesTender s WHERE s.txdocno.txdocno=?");
		createQuery.setParameter(0, txdocno);
		List list = createQuery.list();
		List<SalesTender> salesTenders=new ArrayList<>();
		Object[] ob;
		SalesTender salesTender;
		for (Object object : list) {
			ob=(Object[]) object;
			salesTender=new SalesTender();
			//金额
			salesTender.setPayamount(new BigDecimal(ob[0].toString()));
			//付款方式对象
			salesTender.setTendercode((XfTender)ob[1]);
			salesTender.setGwPaymentRate(String.valueOf(ob[2]));
			salesTenders.add(salesTender);
		}
		return salesTenders;
	}

	/**
	 * 根据销售单号查询销售单付款明细集合
	 * @param txdocno 销售单号
	 * @return
	 */
	@Override
	public List<SalesTender> getSTSByTxdocno(String txdocno) {
		return getList("txdocno.txdocno=?",txdocno);
	}

	/**
	 * 根据销售单号删除销售单付款明细
	 * @param txdocno 销售单号
	 */
	@Override
	public void deleteSTBySSTXD(String txdocno) {
		deleteHQL("delete FROM SalesTender s WHERE s.txdocno.txdocno=?", txdocno);
	}
}
