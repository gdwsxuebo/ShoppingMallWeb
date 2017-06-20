package com.smw.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.smw.dao.TillidToStoreDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.pojo.StoreAuth;
import com.smw.pojo.TillidToStore;
@Repository
public class TillidToStoreDaoImpl extends BaseDaoSupport<TillidToStore> implements TillidToStoreDao {

	@Override
	public void addOrUpdateTillidToStore(TillidToStore tillidToStore) {
		// TODO Auto-generated method stub
		saveOrUpdate(tillidToStore);
		
	}

	@Override
	public void deleteTillidToStoreByTillid(String tillid) {
		// TODO Auto-generated method stub
		// delete(tillid);
		String sql = "DELETE  FROM xf_store_tillid WHERE xf_store_tillid.tillid=?";
		Query q = getSession().createSQLQuery(sql);
		q.setString(0, tillid);
		q.executeUpdate();
	}

	@Override
	public List<TillidToStore> getListTillidToStores() {
		// TODO Auto-generated method stub
		return getList();
	}

	@Override
	public void saveOrUpdateTillidToStores(List<TillidToStore> tillidToStores) {
		// TODO Auto-generated method stub
		for(TillidToStore tillidToStore:tillidToStores){
			saveOrUpdate(tillidToStore);
		}
	}

	@Override
	public TillidToStore getTillidToStoreByTillid(String tillidId) {
		// TODO Auto-generated method stub
		return getUniObj("FROM TillidToStore c WHERE  c.tillid=?", tillidId);
	}

	@Override
	public List<TillidToStore> getListTillidToStoresByTillid(String tillid) {
		// TODO Auto-generated method stub
		return getList("tillid=?", tillid);
	}

	@Override
	public List<TillidToStore> getTillidListBystorecode(String storecode) {
		// TODO Auto-generated method stub
		return getList("xfStoreCode=?", storecode);
	}

	@Override
	public void deleteTillidToStoreById(String id) {	
		String sql = "DELETE  FROM xf_store_tillid WHERE xf_store_tillid.id=?";
		Query q = getSession().createSQLQuery(sql);
		q.setString(0, id);
		q.executeUpdate();
		
	}

	
		
	

}
