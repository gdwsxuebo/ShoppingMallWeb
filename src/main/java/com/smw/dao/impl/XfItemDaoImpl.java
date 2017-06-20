package com.smw.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.smw.common.util.BasePageResultVo;
import com.smw.dao.XfItemDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.pojo.XfItem;

/**
 * 商品数据访问层实现接口
 * 
 * @author suen
 * @date 2016年5月20日-上午10:26:56
 * @version 1.0
 */
@Repository
public class XfItemDaoImpl extends BaseDaoSupport<XfItem> implements XfItemDao {

	/**
	 * 根据商铺编号获取商品信息
	 */
	@Override
	public List<XfItem> selectListByStoreCode(String storeCode) {
		return getList("xfStorecode.xfStorecode=? and isInvalid=1", storeCode);
	}

	/**
	 * 根据品编号获取商品信息
	 * 
	 * @param plu
	 *            商品编号
	 * @return
	 */
	@Override
	public XfItem getXfItem(String plu) {
		return getUniObj("FROM XfItem x WHERE x.xfPlu=?", plu);
	}

	/**
	 * 商品总数
	 * 
	 * @param storeCode
	 *            店铺号
	 * @param key
	 *            搜索条件
	 * @return
	 */
	@Override
	public Integer getCount(String storeCode, String key) {
		String count;
		if (storeCode == null) {
			if (key == null) {
				count = executeHQL("SELECT COUNT(1) FROM XfItem where isInvalid='1'").toString();
			} else {
				count = executeHQL(
						"SELECT COUNT(1) FROM XfItem x WHERE  x.isInvalid='1' and (x.xfStorecode.xfStorecode LIKE ? OR x.xfPlu LIKE ? OR x.xfDesci LIKE ?)",
						"%" + key + "%", "%" + key + "%", "%" + key + "%").toString();
			}
		} else {
			if (key == null) {
				count = executeHQL("SELECT COUNT(1) FROM XfItem x WHERE x.xfStorecode.xfStorecode=? AND x.isInvalid='1'", storeCode)
						.toString();
			} else {
				count = executeHQL(
						"SELECT COUNT(1) FROM XfItem x WHERE  x.isInvalid='1' and (x.xfStorecode.xfStorecode=? AND x.xfPlu LIKE ? OR x.xfDesci LIKE ? )",
						storeCode, "%" + key + "%", "%" + key + "%").toString();
			}
		}
		return count == null ? 0 : Integer.parseInt(count);
	}

	/**
	 * 获取商品集合
	 * 
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            每页显示的行数
	 * @param storeCode
	 *            店铺号
	 * @param key
	 *            搜索条件
	 * @return
	 */
	@Override
	public List<XfItem> getXfItemList(Integer pageIndex, Integer pageSize, String storeCode, String key) {
		List<XfItem> xfItems;
		if (storeCode == null) {
			if (key == null) {
				xfItems=getList(pageIndex,pageSize," isInvalid='1'");
			} else {
				xfItems=getList(pageIndex, pageSize, " isInvalid='1' and ( xfStorecode.xfStorecode LIKE ? OR xfPlu LIKE ? OR xfDesci LIKE ?)", "%" + key + "%", "%" + key + "%", "%" + key + "%");
			}
		} else {
			if (key == null) {
				xfItems=getList(pageIndex,pageSize," isInvalid='1' and xfStorecode.xfStorecode=?", storeCode);
			} else {
				xfItems=getList(pageIndex,pageSize,"isInvalid='1' and( xfStorecode.xfStorecode=? AND xfPlu LIKE ? OR xfDesci LIKE ?)", storeCode, "%" + key + "%", "%" + key + "%");
			}
		}
		return xfItems;
	}
	@Override
	public void saveOrUpdateXfItem(XfItem plu){
		saveOrUpdate(plu);
	}

	/**
	 * 根据商品号删除商品
	 * @param xfPlus 商品
	 */
	@Override
	public void deleteXfItemByXfPlu(String xfPlus) {
		delete(xfPlus);
	}

	/**
	 * 根据店铺号删除商品
	 * @param xfStorecode 店铺编号
	 */
	@Override
	public void deleteXfItemBySC(String xfStorecode) {
		deleteHQL("delete FROM XfItem x WHERE x.xfStorecode.xfStorecode=?", xfStorecode);
	}

	@Override
	public List<XfItem> selectAllByStoreCode(String storeCode) {

		return getList("xfStorecode.xfStorecode=? ", storeCode);
	}

	@Override
	public int updateXfItemByStoreCode(String storeCode) {
			Query query=getSession().createQuery("update XfItem set isInvalid=0 where xfstorecode=? ");
			query.setParameter(0, storeCode);
			return 	query.executeUpdate();

	}


}
