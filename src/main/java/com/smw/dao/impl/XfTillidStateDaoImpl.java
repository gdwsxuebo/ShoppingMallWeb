package com.smw.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.smw.common.util.BasePageResultVo;
import com.smw.dao.XfTillidStateDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.dao.BaseDao_suen.Order;
import com.smw.pojo.XfTillidState;

/**
 * 平板收银机在线数据访问层实现接口
 * 
 * @author suen
 * @date 2016年5月19日-下午2:35:54
 * @version 1.0
 */
@Repository
public class XfTillidStateDaoImpl extends BaseDaoSupport<XfTillidState> implements XfTillidStateDao {

	@Override
	public XfTillidState selectByStrId(String tillid) {
		return get(tillid);
	}

	/**
	 * 更新或者保存平板收银机在线
	 * @param xfTillidState 平板收银机在线
	 */
	@Override
	public void saveOrUpdateXFTS(XfTillidState xfTillidState) {
		saveOrUpdate(xfTillidState);
	}

	/**
	 * 查询收银机集合
	 * @param storeCode 店铺号
	 * @param key  搜索条件
	 */
	@Override
	public Integer getCount(String storeCode, String key) {
		String count;
		if (storeCode == null) {
			if (key == null) {
				count = executeHQL("SELECT COUNT(1) FROM XfTillidState").toString();
			} else {
				count = executeHQL(
						"SELECT COUNT(1) FROM XfTillidState x WHERE x.tillid like ? OR x.xfStorecode.xfStorecode like ? OR tillid like ?",
						"%" + key + "%", "%" + key + "%", "%" + key + "%").toString();
			}
		} else {
			if (key == null) {
				count = executeHQL("SELECT COUNT(1) FROM XfTillidState x WHERE x.xfStorecode.xfStorecode = ?",
						storeCode).toString();
			} else {
				count = executeHQL(
						"SELECT COUNT(1) FROM XfTillidState x WHERE x.xfStorecode.xfStorecode = ? AND x.tillid like ? AND tillid like ?",
						storeCode, "%" + key + "%", "%" + key + "%").toString();
			}
		}
		return count == null ? 0 : Integer.parseInt(count);
	}

	/**
	 * 查询收银机集合
	 * @param pageIndex 页码
	 * @param pageSiz  每页显示的行数
	 * @param storeCode 店铺号
	 * @param key  搜索条件
	 */
	@Override
	public List<XfTillidState> getXfTillidList(Integer pageIndex, Integer pageSiz, String storeCode, String key) {
		List<XfTillidState> xfTillidStates;
		if (storeCode == null) {
			if (key == null) {
				xfTillidStates=getList(pageIndex, pageSiz,"1=1",Order.DESC,"visitTime");
			} else {
				xfTillidStates=getList(pageIndex, pageSiz, " xfStorecode.xfStorecode like ? OR tillid like ?",Order.DESC, "visitTime", "%" + key + "%", "%" + key + "%");
			}
		} else {
			if (key == null) {
				xfTillidStates=getList(pageIndex, pageSiz, " xfStorecode.xfStorecode = ?",storeCode,Order.DESC,"visitTime");
			} else {
				xfTillidStates=getList(pageIndex, pageSiz, " xfStorecode.xfStorecode = ? OR tillid like ? ", storeCode, Order.DESC,"visitTime","%" + key + "%", "%" + key + "%", "%" + key + "%");
			}
		}
		return xfTillidStates;
	}

	/**
	 * 根据店铺编号和以平板编号进行分组统计平板数
	 * @param xfStorecode 店铺编号
	 * @return
	 */
	@Override
	public int getCountByXfSCodeGRBYId(String xfStorecode) {
		List<Object> executeHQL = executeHQLTillid("select count(1) from XfTillidState x where x.xfStorecode.xfStorecode=? GROUP BY x.tillid", xfStorecode);
		return executeHQL.size();
	}

	/**
	 * 根据店铺编号删除平板收银机
	 * @param xfStorecode 店铺编号
	 */
	@Override
	public void deleteXFTSByStCod(String xfStorecode) {
		deleteHQL("delete FROM XfTillidState x WHERE x.xfStorecode.xfStorecode=?", xfStorecode);
	}

	/*//通过店铺ID,查询非中央店铺收银机数量
	 * @see com.smw.dao.XfTillidStateDao#getTillidByStoreId(java.lang.String)
	 */
	@Override
	public Integer getTillidByStoreId(String storeId) {
		return  getCountBySQL("select count(*) from xf_tillid_state where xfStorecode=?", storeId);
	}
	/**
	 *通过店铺ID,查询中央店铺收银机数量.
	 */
	@Override
	public Integer getTillidCountByCoreStoreId(String storeid) {

		return getCountBySQL(" select count(*) from xf_tillid_state where (xfStorecode in( select xfStorecode from xf_store_center where  xfCenterstorecode=?)) OR (xfStorecode =?) ", storeid,storeid);
	}
	/**
	 * 通过店铺编号查询收银机。
	 */
	@Override
	public List<XfTillidState> getTillidListBy(String storeid) {
		// TODO Auto-generated method stub
		return getList(" xfStorecode.xfStorecode = ?", storeid);
	}
	/**
	 * 查询所有收银机
	 */
	@Override
	public List<XfTillidState> getAllTillids() {
		// TODO Auto-generated method stub
		return getList();
	}

	@Override
	public XfTillidState getXfTillidStateBytillid(String tillids) {
		// TODO Auto-generated method stub
		String hql ="from XfTillidState where tillid = '" + tillids + "'";
		return getUniObj(hql);
	}

	@Override
	public XfTillidState getXfTillidStateBystaffCode(String staffCode) {
		// TODO Auto-generated method stub
		String hql ="from XfTillidState where xfStaffcode = '" + staffCode + "'";
		return getUniObj(hql);
	}

	@Override
	public void delTillidStateTabe() {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM xf_tillid_state";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.executeUpdate();
	}

	



}
