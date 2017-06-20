package com.smw.dao;

/**
 * 促销使用范围数据访问层
 * @author suen
 * @date 2016年6月21日-下午2:07:17
 * @version  jdk1.8
 */
public interface PromotionUseRangeDao {

	/**
	 * 根据ID删除促销使用范围
	 * @param id 主键ID
	 */
	void deletePUR(String id);
	
}
