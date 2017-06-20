package com.smw.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.smw.common.util.BasePageResultVo;
import com.smw.dao.Old_SalesItemDao;
import com.smw.dao.SalesItemDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.pojo.SalesItem;
import com.smw.pojo.oldSalesSummary.OldSalesItem;

/**
 * 旧销售单货品明细表数据访问层接口
 * @author suen
 * @date 2016年5月20日-下午1:52:53
 * @version 1.0
 */
@Repository
public class Old_SalesItemDaoImpl extends BaseDaoSupport<OldSalesItem> implements Old_SalesItemDao {

	/**
	 * 保存或者更新销售商品
	 * @param oldSalesItem 商品
	 */
	@Override
	public void saveOrUpdateOSI(OldSalesItem oldSalesItem) {
		saveOrUpdate(oldSalesItem);
	}

	/**
	 * 根据销售单号查询销售商品信息
	 * @param txdocno 销售单号
	 * @return
	 */
	@Override
	public List<OldSalesItem> getOSIListByTxDocno(String txdocno) {
		return getList("txdocno.txdocno=?",txdocno);
	}

}
