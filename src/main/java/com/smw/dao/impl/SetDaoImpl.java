package com.smw.dao.impl;

import org.springframework.stereotype.Repository;

import com.smw.dao.SetDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.pojo.Sets;

/**
 * 设置数据访问层实现接口
 * @author suen
 * @date 2016年5月28日-下午3:31:34
 * @version 1.0
 */
@Repository
public class SetDaoImpl extends BaseDaoSupport<Sets> implements SetDao {

	/**
	 * 获取设置信息
	 * @param id 主键ID
	 * @return
	 */
	@Override
	public Sets getSets(String id) {
		return get(id);
	}

	/**
	 * 保存或者修改设置
	 * @param sets 设置信息
	 * @return
	 */
	@Override
	public void saveOrUpdateSets(Sets sets) {
		saveOrUpdate(sets);
	}

	/**
	 * 根据设置的ID删除设置信息
	 * @param id 设置的ID
	 */
	@Override
	public void deleteSetsById(String id) {
		delete(id);
	}

}
