package com.smw.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.smw.common.util.BaseResultVo;
import com.smw.dao.GwRoleDao;
import com.smw.dao.GwRoleGroupDao;
import com.smw.pojo.GwRole;
import com.smw.service.GwRoleService;

/**
 * 
 * 
 * GwRoleServiceImpl:角色业务层实现
 *
 * @author  shengjinpeng
 * @date    2017年2月21日
 * @version jdk1.8
 *
 */
@Service
public class GwRoleServiceImpl implements GwRoleService {

	@Resource
	private GwRoleDao gwRoleDao;
	
	@Resource
	private GwRoleGroupDao gwRoleGroupDao;
	
	Logger logger = LoggerFactory.getLogger(GwRoleServiceImpl.class);

	@Override
	public GwRole selectById(Integer id) {
		// TODO Auto-generated method stub
		return gwRoleDao.selectById(id);
	}

	@Override
	public void saveOrupdateGwRole(GwRole role) {
		// TODO Auto-generated method stub
		gwRoleDao.saveOrupdateGwRole(role);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		gwRoleDao.deleteById(id);
	}

	@Override
	public List<GwRole> selectAllList() {
		// TODO Auto-generated method stub
		return gwRoleDao.selectAllList();
	}

	@Override
	public List<GwRole> selectPageList(Integer pageIndex, Integer pageSize) {
		// TODO Auto-generated method stub
		return gwRoleDao.selectPageList(pageIndex, pageSize);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Integer.valueOf(gwRoleDao.getCount().toString());
	}

	@Override
	public BaseResultVo deleteGwRoleById(Integer id) {
		gwRoleGroupDao.deleteByRoleId(id);
		gwRoleDao.deleteById(id);
		return BaseResultVo.responseSuccess("删除成功");
	}

	@Override
	public Integer getCount(String key) {
		// TODO Auto-generated method stub
		return Integer.valueOf(gwRoleDao.getCount(key).toString());
	}

	@Override
	public List<GwRole> selectPageList(Integer pageIndex, Integer pageSize, String key) {
		// TODO Auto-generated method stub
		return gwRoleDao.selectPageList(pageIndex, pageSize,key);
	}

	
}
