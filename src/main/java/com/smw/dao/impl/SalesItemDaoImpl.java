package com.smw.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.smw.common.util.BasePageResultVo;
import com.smw.dao.SalesItemDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.pojo.SalesItem;

/**
 * 销售单货品明细表数据访问层接口
 * @author suen
 * @date 2016年5月20日-下午1:52:53
 * @version 1.0
 */
@Repository
public class SalesItemDaoImpl extends BaseDaoSupport<SalesItem> implements SalesItemDao {

	/**
	 * 保存售单货品明细
	 * @param salesItem 售单货品明细
	 */
	@Override
	public void saveSalesItem(SalesItem salesItem) {
		save(salesItem);
	}

	@Override
	public List<SalesItem> getXfItemByDocCode(String docCode) {
		return getList("txdocno.txdocno=?",docCode);
	}

	/**
	 * 根据销售单号删除销售货品明细
	 * @param txdocno 销售单号
	 */
	@Override
	public void deleteSIBySSTXD(String txdocno) {
		deleteHQL("delete FROM SalesItem s WHERE s.txdocno.txdocno=?", txdocno);
	}

}
