package com.smw.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smw.dao.GwMenuTreeDao;
import com.smw.pojo.GwMenuTree;
import com.smw.service.GwMenuTreeService;

/**
 * 
 * 
 * GwMenuTreeServiceImpl:菜单业务层实现
 *
 * @author  shengjinpeng
 * @date    2017年2月21日
 * @version jdk1.8
 *
 */
@Service
public class GwMenuTreeServiceImpl implements GwMenuTreeService {

	@Resource
	private GwMenuTreeDao gwMenuTreeDao;

	@Override
	public List<GwMenuTree> selectAllList() {
		// TODO Auto-generated method stub
		return gwMenuTreeDao.selectAllList();
	}

	@Override
	public List<GwMenuTree> selectListByFid(Integer fid) {
		// TODO Auto-generated method stub
		return gwMenuTreeDao.selectListByFid(fid);
	}

	@Override
	public GwMenuTree selectById(Integer id) {
		// TODO Auto-generated method stub
		return gwMenuTreeDao.selectById(id);
	}
	@Override
	public void saveInitAllMenu() {
		// TODO Auto-generated method stub
		gwMenuTreeDao.saveInitAllMenu();
	}

	
	
}
