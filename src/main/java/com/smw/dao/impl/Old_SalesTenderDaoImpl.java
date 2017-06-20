package com.smw.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.smw.common.util.BasePageResultVo;
import com.smw.dao.Old_SalesTenderDao;
import com.smw.dao.SalesTenderDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.pojo.SalesTender;
import com.smw.pojo.XfTender;
import com.smw.pojo.oldSalesSummary.OldSalesTender;

/**
 * 老销售付款数据访问层实现接口
 * @author suen
 * @date 2016年5月20日-下午12:11:08
 * @version 1.0
 */
@Repository
public class Old_SalesTenderDaoImpl extends BaseDaoSupport<OldSalesTender> implements Old_SalesTenderDao {

	/**
	 * 保存或者更新支付方式
	 * @param oldSalesTender 支付方式
	 */
	@Override
	public void saveOrUpdateOST(OldSalesTender oldSalesTender) {
		saveOrUpdate(oldSalesTender);
	}

	/**
	 * 根据销售单号获取销售的支付方式
	 * @param txdocno 销售单号
	 * @return
	 */
	@Override
	public List<OldSalesTender> getOSTByTxDocno(String txdocno) {
		return getList("txdocno.txdocno=?",txdocno);
	}

	
}
