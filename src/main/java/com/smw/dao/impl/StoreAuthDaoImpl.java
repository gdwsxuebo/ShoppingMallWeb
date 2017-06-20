package com.smw.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.smw.dao.StoreAuthDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.pojo.StoreAuth;

@Repository
public class StoreAuthDaoImpl extends BaseDaoSupport<StoreAuth> implements StoreAuthDao {

	/**
	 * 收银机权限总数
	 */
	public Integer getCount(String key) {
		String count;
		if(key == null){
			count = executeHQL("select count(1) from StoreAuth").toString();
		}else{
			count = executeHQL("select count(1) from StoreAuth x where x.tillid like ? or x.v61tillid like ? or x.storeCode like ? ",
					"%" + key + "%", "%" + key + "%", "%" + key + "%").toString();
		}
		return count == null ? 0 : Integer.parseInt(count);
	}

	/**
	 * 收银机权限集合
	 */
	public List<StoreAuth> getStoreAuthList(Integer pageIndex, Integer pageSize, String key) {
		List<StoreAuth> xfItems;
		if(key == null){
			xfItems=getList(pageIndex,pageSize);
		}else{
			xfItems=getList(pageIndex, pageSize, " tillid like ? or v61tillid like ? or storeCode like ? ", "%" + key + "%", "%" + key + "%", "%" + key + "%");
		}
		return xfItems;
	}

	/**
	 * 获取收银机对象
	 */
	public StoreAuth getStoreAuthByHql(String tillid) {
		String hql ="from StoreAuth where tillid = '" + tillid + "'";
		return getUniObj(hql);
	}
	
	/**
	 * 添加或修改
	 */
	public void addOrUpdateStoreAuth(StoreAuth storeAuth){
		saveOrUpdate(storeAuth);
	}

	/**
	 * 删除
	 */
	public void deleteStoreAuth(String id){
		//delete(id);
		String sql = "DELETE  FROM xf_store_auth WHERE xf_store_auth.id=?";
		Query q = getSession().createSQLQuery(sql);
		q.setString(0, id);
		q.executeUpdate();

	}

	@Override
	public List<String> getStoreAuthsByHql() {
		//String  sql ="select m.tillid from (select a.tillid from xf_store_auth a) m where m.tillid not in (SELECT p.tillid from pos_conifg p )";
		String sql="select tillid from xf_store_auth where not exists(select 1 from pos_conifg where pos_conifg.tillid=xf_store_auth.tillid)";
		Query q=getSession().createSQLQuery(sql);
		return q.list();
	}

	@Override  
	public List<StoreAuth> getListsStoreAuth() {
		// TODO Auto-generated method stub
		return getList();
	}

	@Override
	public void saveOrUpdatestoreAuths(List<StoreAuth> storeAuths) {
		// TODO Auto-generated method stub
		for(StoreAuth storeAuth:storeAuths){
			saveOrUpdate(storeAuth);
		}
	}

	@Override
	public List<StoreAuth> getStoreAuthListBystorecode(String storecode) {
		// TODO Auto-generated method stub
		return getList("xfStoreCode=?",storecode);
	}
}
