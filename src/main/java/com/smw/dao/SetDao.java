package com.smw.dao;

import com.smw.pojo.Sets;

/**
 * 设置数据访问层接口
 * @author suen
 * @date 2016年5月28日-下午3:30:44
 * @version 1.0
 */
public interface SetDao {

	/**
	 * 获取设置信息
	 * @param id 主键ID
	 * @return
	 */
	Sets getSets(String id);

	/**
	 * 保存或者修改设置
	 * @param sets 设置信息
	 * @return
	 */
	void saveOrUpdateSets(Sets sets);

	/**
	 * 根据设置的ID删除设置信息
	 * @param id 设置的ID
	 */
	void deleteSetsById(String id);

}
