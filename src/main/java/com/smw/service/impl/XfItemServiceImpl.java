package com.smw.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.smw.common.util.BasePageResultVo;
import com.smw.dao.XfItemDao;
import com.smw.pojo.XfItem;
import com.smw.service.XfItemService;

/**
 * 商品服务层实现接口层
 * @author suen
 * @date 2016年5月20日-上午10:25:29
 * @version 1.0
 */
@Service
public class XfItemServiceImpl implements XfItemService {

	@Resource
	private XfItemDao xfItemDao;
	
	/**
	 * 根据商铺编号获取商品信息
	 */
	@Override
	public List<XfItem> selectListByStoreCode(String storeCode) {
		return xfItemDao.selectListByStoreCode(storeCode);
	}

	/**
	 * 根据品编号获取商品信息
	 * @param plu 商品编号
	 * @return
	 */
	@Override
	public XfItem getXfItem(String plu) {
		return xfItemDao.getXfItem(plu);
	}

	/**
	 * 商品总数
	 * @param storeCode 店铺号
	 * @param key 搜索条件
	 * @return
	 */
	@Override
	public Integer getCount(String storeCode, String key) {
		return xfItemDao.getCount(storeCode,key);
	}

	/**
	 * 获取商品集合
	 * @param pageIndex 页码
	 * @param pageSize 每页显示的行数
	 * @param storeCode 店铺号
	 * @param key 搜索条件
	 * @return
	 */
	@Override
	public List<XfItem> getXfItemList(Integer pageIndex, Integer pageSize, String storeCode, String key) {
		return xfItemDao.getXfItemList(pageIndex,pageSize,storeCode,key);
	}
	
	/**
	 * 
	 * 修改或者保存商品信息
	 * @param plu
	 */
	@Override
	public void saveOrUpdateXfItem(XfItem plu){
		xfItemDao.saveOrUpdateXfItem(plu);
	}

	/**
	 * 根据商品号删除商品
	 * @param xfPlus 商品
	 */
	@Override
	public void deleteXfItemByXfPlu(String xfPlus) {
		xfItemDao.deleteXfItemByXfPlu(xfPlus);
	}

	/**
	 * 根据店铺号删除商品
	 * @param xfStorecode 店铺编号
	 */
	@Override
	public void deleteXfItemBySC(String xfStorecode) {
		xfItemDao.deleteXfItemBySC(xfStorecode);
	}

	@Override
	public List<XfItem> selectAllByStoreCode(String storeCode) {

		return xfItemDao.selectAllByStoreCode(storeCode);
	}

	@Override
	public int updateXfItemByStoreCode(String storeCode) {

		return xfItemDao.updateXfItemByStoreCode(storeCode);
	}


}
