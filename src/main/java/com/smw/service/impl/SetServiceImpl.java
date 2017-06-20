package com.smw.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smw.dao.SetDao;
import com.smw.pojo.Sets;
import com.smw.service.SetService;

/**
 * 设置服务层实现接口
 * @author suen
 * @date 2016年5月28日-下午3:25:43
 * @version 1.0
 */
@Service
public class SetServiceImpl implements SetService {
	
	@Resource
	private SetDao setDao;
 
	/**
	 * 获取设置信息
	 * @param id 主键ID
	 * @return
	 */
	@Override
	public Sets getSets(String id) {
		return setDao.getSets(id);
	}

	/**
	 * 保存或者修改设置
	 * @param sets 设置信息
	 * @return
	 */
	@Override
	public void saveOrUpdateSets(Sets sets) {
		setDao.saveOrUpdateSets(sets);
	}

	/**
	 * 根据设置的ID删除设置信息
	 * @param id 设置的ID
	 */
	@Override
	public void deleteSetsById(String id) {
		setDao.deleteSetsById(id);
	}

}
