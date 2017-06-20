package com.gws.dao.core;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;



public interface IBaseDAO<T extends Serializable> extends IOperations<T> {
	/**
	 * @param dc:查询条件，page:页码
	 * @return 分页数据列表
	 */
	public List<T> findPager(DetachedCriteria dc,int page, int numPerPage);
	
	/**
	 * 根据某种条件，查找所有符合条件的个数
	 * @param dc
	 * @return 符合条件的个数
	 */
	public long getCount(DetachedCriteria dc);
	
	/**
	 * 根据离线查询条件查询结果集
	 * @param dc
	 * @return
	 */
	public List<T> findByDC(DetachedCriteria dc);
	
}
