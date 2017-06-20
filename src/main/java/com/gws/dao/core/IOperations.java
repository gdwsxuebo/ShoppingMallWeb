package com.gws.dao.core;

import java.io.Serializable;
import java.util.List;

/**
 *  通用DAO接口
 *	@author newflypig
 *	time：2015年11月23日
 *
 */
public interface IOperations<T extends Serializable> {
	public Serializable save(T entity);
	
	public void delete(T entity);
	
	public void deleteById(Integer id);
	
	public List<T> findAll();
	
	public T findById(Integer id);
	
	public void update(T entity);	
}