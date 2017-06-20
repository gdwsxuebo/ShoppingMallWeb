package com.smw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smw.dao.Old_SalesItemDao;
import com.smw.dao.SalesItemDao;
import com.smw.pojo.SalesItem;
import com.smw.pojo.oldSalesSummary.OldSalesItem;
import com.smw.service.Old_SalesItemService;
import com.smw.service.SalesItemService;

/**
 * 旧销售单货品明细表
 * @author suen
 * @date 2016年5月20日-下午1:50:58
 * @version 1.0
 */
@Service
public class Old_SalesItemServiceImpl implements Old_SalesItemService {

	@Resource
	private  Old_SalesItemDao  old_SalesItemDao;

	/**
	 * 保存或者更新销售商品
	 * @param oldSalesItem 商品
	 */
	@Override
	public void saveOrUpdateOSI(OldSalesItem oldSalesItem) {
		old_SalesItemDao.saveOrUpdateOSI(oldSalesItem);
	}

	/**
	 * 根据销售单号查询销售商品信息
	 * @param txdocno 销售单号
	 * @return
	 */
	@Override
	public List<OldSalesItem> getOSIListByTxDocno(String txdocno) {
		return old_SalesItemDao.getOSIListByTxDocno(txdocno);
	}
	
	
	
}
