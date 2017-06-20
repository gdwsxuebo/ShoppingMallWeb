package com.smw.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.smw.common.util.BasePageResultVo;
import com.smw.dao.Old_SalesSummaryDao;
import com.smw.dao.SalesSummaryDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.pojo.SalesSummary;
import com.smw.pojo.oldSalesSummary.OldSalesSummary;

/**
 * 老销售汇总数据访问层实现接口
 * 
 * @author suen
 * @date 2016年5月19日-下午3:41:23
 * @version 1.0
 */
@Repository
public class Old_SalesSummaryDaoImpl extends BaseDaoSupport<OldSalesSummary> implements Old_SalesSummaryDao {

	/**
	 * 保存就销售数据
	 * @param oldSalesSummary 旧销售
	 */
	@Override
	public void saveOrUpdateOSS(OldSalesSummary oldSalesSummary) {
		saveOrUpdate(oldSalesSummary);
	}

	/**
	 * 获取老的销售数据
	 * @param txdocno 销售单号
	 * @return
	 */
	@Override
	public OldSalesSummary getOSSByTxdocno(String txdocno) {
		return get(txdocno);
	}

	
}	
