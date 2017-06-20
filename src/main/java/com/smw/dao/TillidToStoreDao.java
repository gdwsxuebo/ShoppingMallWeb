package com.smw.dao;

import java.util.List;

import com.smw.pojo.StoreAuth;
import com.smw.pojo.TillidToStore;

public interface TillidToStoreDao {

	void addOrUpdateTillidToStore(TillidToStore tillidToStore);

	void deleteTillidToStoreByTillid(String tillid);

	List<TillidToStore> getListTillidToStores();

	void saveOrUpdateTillidToStores(List<TillidToStore> tillidToStores);

	TillidToStore getTillidToStoreByTillid(String tillidId);

	List<TillidToStore> getListTillidToStoresByTillid(String tillid);

	List<TillidToStore> getTillidListBystorecode(String storecode);

	void deleteTillidToStoreById(String id);


}
