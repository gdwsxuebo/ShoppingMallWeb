package com.smw.service;

import com.smw.pojo.Sets;

/**
 * 设置服务层接口
 * @author suen
 * @date 2016年5月28日-下午3:23:53
 * @version 1.0
 */
public interface SetService {

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
