package com.smw.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smw.common.util.BasePageResultVo;
import com.smw.dao.SalesItemDao;
import com.smw.pojo.SalesItem;
import com.smw.service.SalesItemService;

/**
 * 销售单货品明细表
 * @author suen
 * @date 2016年5月20日-下午1:50:58
 * @version 1.0
 */
@Service
public class SalesItemServiceImpl implements SalesItemService {

	@Resource
	private  SalesItemDao  salesItemDao;
	
	/**
	 * 保存售单货品明细
	 * @param salesItem 售单货品明细
	 */
	@Override
	public void saveSalesItem(SalesItem salesItem) {
		salesItemDao.saveSalesItem(salesItem);
	}

	/**
	 * 根据销售单号获取销售单明细
	 * @param docCode
	 * @return
	 */
	@Override
	public List<SalesItem> getXfItemByDocCode(String docCode) {
		return salesItemDao.getXfItemByDocCode(docCode);
	}

	/**
	 * 根据销售单号删除销售货品明细
	 * @param txdocno 销售单号
	 */
	@Override
	public void deleteSIBySSTXD(String txdocno) {
		salesItemDao.deleteSIBySSTXD(txdocno);
	}
	
}
