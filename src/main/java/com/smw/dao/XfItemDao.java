package com.smw.dao;

import java.util.List;
import java.util.Map;

import com.smw.pojo.XfItem;

/**
 * 商品信息表数据访问层接口
 * @author suen
 * @date 2016年5月18日-下午4:50:57
 * @version 1.0
 */
public interface XfItemDao{

	/**
	 * 根据店铺查询商品信息
	 * @param storeCode 参数店铺编号
	 * @return
	 */
	List<XfItem> selectListByStoreCode(String storeCode);

	/**
	 * 根据品编号获取商品信息
	 * @param plu 商品编号
	 * @return
	 */
	XfItem getXfItem(String plu);

	/**
	 * 商品总数
	 * @param storeCode 店铺号
	 * @param key 搜索条件
	 * @return
	 */
	Integer getCount(String storeCode, String key);

	/**
	 * 获取商品集合
	 * @param pageIndex 页码
	 * @param pageSize 每页显示的行数
	 * @param storeCode 店铺号
	 * @param key 搜索条件
	 * @return
	 */
	List<XfItem> getXfItemList(Integer pageIndex, Integer pageSize, String storeCode, String key);

	/**
	 * 保存或者更新商品信息
	 * @param plu
	 */
	void saveOrUpdateXfItem(XfItem plu);

	/**
	 * 根据商品号删除商品
	 * @param xfPlus 商品
	 */
	void deleteXfItemByXfPlu(String xfPlus);

	/**
	 * 根据店铺号删除商品
	 * @param xfStorecode 店铺编号
	 */
	void deleteXfItemBySC(String xfStorecode);

	List<XfItem> selectAllByStoreCode(String storeCode);

	int updateXfItemByStoreCode(String storeCode);


}