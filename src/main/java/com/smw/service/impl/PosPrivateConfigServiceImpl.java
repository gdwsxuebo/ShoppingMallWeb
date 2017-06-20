package com.smw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smw.common.util.BaseResultVo;
import com.smw.dao.PosPrivateConfigDao;
import com.smw.pojo.PosConfigModel;
import com.smw.service.PosPrivateConfigService;

@Service
public class PosPrivateConfigServiceImpl implements PosPrivateConfigService{

	@Resource
	private PosPrivateConfigDao posPrivateConfigDao;
	
	/**
	 *  收银机私有配置
	 */
	public Integer getCount(String key) {
		return posPrivateConfigDao.getCount(key);
	}

	/**
	 *  收银机私有配置集合
	 */
	public List<PosConfigModel> getPosPrivateConfigList(Integer pageIndex, Integer pageSize, String key) {
		return posPrivateConfigDao.getPosPrivateConfigList(pageIndex, pageSize, key);
	}
	
	/**
	 * 获取收银机私有配置对象
	 */
	public PosConfigModel getPosConfigByTillid(String tillid){
		return posPrivateConfigDao.getPosConfigByTillid(tillid);
	}

	@Override
	public void addOrUpdatePosPrivateConfig(PosConfigModel posConfigModel) {
		// TODO Auto-generated method stub
		 posPrivateConfigDao.addOrUpdatePosPrivateConfig(posConfigModel);
	}

	@Override
	public List<PosConfigModel> selectAllList() {
		// TODO Auto-generated method stub
		return posPrivateConfigDao.selectAllList();
	}

	@Override
	public BaseResultVo deletePosPrivateConfigByid(String id) {
		// TODO Auto-generated method stub
		
		posPrivateConfigDao.deletePosPrivateConfigByid(id);
	
		return BaseResultVo.responseSuccess("删除成功");
	
	
	}

	@Override
	public PosConfigModel selectById(Integer id) {
		// TODO Auto-generated method stub
		return posPrivateConfigDao.selectById(id);
	}

	@Override
	public PosConfigModel getPosPrivateConfigById(Integer id) {
		// TODO Auto-generated method stub
		return posPrivateConfigDao.getPosPrivateConfigById(id);
	}

	@Override
	public List<PosConfigModel> getPosPrivateConfigsList() {
		// TODO Auto-generated method stub
		return posPrivateConfigDao.getPosPrivateConfigsList();
	}

}
