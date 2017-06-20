package com.smw.dao;

/**
 * 促销范围数据访问层接口
 * @author suen
 * @date 2016年6月21日-下午2:01:49
 * @version  jdk1.8
 */
public interface PromotionRangeDao {

	/**
	 * 根据ID删除促销范围
	 * @param id 主键ID
	 */
	void deletePRS(String id);
	
}
