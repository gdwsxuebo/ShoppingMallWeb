package com.smw.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smw.common.util.BaseResultVo;
import com.smw.dao.PosPrivateConfigDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.pojo.PosConfigModel;

@Repository
public class PosPrivateConfigDaoImpl extends BaseDaoSupport<PosConfigModel> implements PosPrivateConfigDao {

	/**
	 *  收银机私有配置
	 */
	public Integer getCount(String key) {
		String count;
		if(key == null){
			count = executeHQL("select count(1) from PosConfigModel").toString();
		}else{
			count = executeHQL("select count(1) from PosConfigModel x where x.tillid like ? ", "%" + key + "%").toString();
		}
		return count == null ? 0 : Integer.parseInt(count);
	}

	/**
	 *  收银机私有配置集合
	 */
	public List<PosConfigModel> getPosPrivateConfigList(Integer pageIndex, Integer pageSize, String key) {
		List<PosConfigModel> posPrivateConfigs;
		if(key == null){
			posPrivateConfigs=getList(pageIndex,pageSize);
		}else{
			posPrivateConfigs=getList(pageIndex, pageSize, " tillid like ? ", "%" + key + "%");
		}
		return posPrivateConfigs;
	}
	
	/**
	 * 获取收银机私有配置对象
	 */
	public PosConfigModel getPosConfigByTillid(String tillid){
		String hql ="from PosConfigModel where tillid = '" + tillid+"'";
		return getUniObj(hql);
	}

	@Override
	public void addOrUpdatePosPrivateConfig(PosConfigModel posConfigModel) {
		// TODO Auto-generated method stub
		saveOrUpdate(posConfigModel);
	}

	@Override
	public List<PosConfigModel> selectAllList() {
		// TODO Auto-generated method stub
		return getList();
	}

	@Override
	public void deletePosPrivateConfigByid(String id) {
		// TODO Auto-generated method stub
		String hql ="from PosConfigModel where id = " + id;
		PosConfigModel p=getUniObj(hql);
		delete(p);
		
	}

	@Override
	public PosConfigModel selectById(Integer id) {
		// TODO Auto-generated method stub
		String hql ="from PosConfigModel where id = " + id;
		PosConfigModel p=getUniObj(hql);
		return p;
	}

	@Override
	public PosConfigModel getPosPrivateConfigById(Integer id) {
		// TODO Auto-generated method stub
		String hql ="from PosConfigModel where id = " + id;
		PosConfigModel p=getUniObj(hql);
		
		return p;
	}

	@Override
	public List<PosConfigModel> getPosPrivateConfigsList() {
		// TODO Auto-generated method stub
		return getList();
	}
	

}
