package com.smw.dao.BaseDao_suen;
import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	
	/**
	 * 删除
	 * @param i 序列化标识
	 */
	public void delete(Serializable i);
	
	/**
	 * 删除对象
	 * @param t 对象
	 */
	public void delete(T t);
	
	/**
	 * 根据序列化标识获取对象
	 * @param i 序列化标识
	 * @return
	 */
	public T get(Serializable i);
	
	/**
	 * 获取平均值
	 * @param column 平均列
	 * @return
	 */
	public Double getAvg(String column);
	
	/**
	 * 带条件获取平均值
	 * @param column 平均的列
	 * @param where 条件
	 * @param params 参数
	 * @return
	 */
	public Double getAvg(String column,String where, Object ... params);
	
	/**
	 * 获取总数
	 * @return
	 */
	public Long getCount();
	
	/**
	 * 带条件获取总数
	 * @param where 条件
	 * @param params 参数
	 * @return
	 */
	public Long getCount(String where,Object ... params);
	
	/**
	 * 分组
	 * @param columns 列
	 * @param groupColumn 分组列
	 * @return
	 */
	public List<Object[]> getGroup(String columns,String groupColumn);
	
	public List<Object[]> getGroup(String columns,String groupColumn,String where,boolean whereOrHaving,Object ... params);
	
	public List<Object[]> getGroup(String columns,String groupColumn,String where,boolean whereOrHaving,Order order,String orderColumns,Object ... params);
	
	public List<Object[]> getGroup(String columns,String groupColumn,String where,Object[] whereParams,String having,Object[] haivingParams,Order order,String orderColumns);
	
	/**
	 * 获取集合
	 * @return
	 */
	public List<T> getList();
	
	/**
	 * 分页集合
	 * @param page 页码
	 * @param rows 每页显示的行数
	 * @return
	 */
	public List<T> getList(Integer page,Integer rows);
	
	/**
	 * 分页排序获取集合
	 * @param page 页码
	 * @param rows 每页显示的行数
	 * @param order 排列顺序
	 * @param orderColumns 排列的列
	 * @return
	 */
	public List<T> getList(Integer page,Integer rows,Order order,String orderColumns);
	
	public List<T> getList(Integer page,Integer rows,String where,Object ... params);
	
	public List<T> getList(Integer page,Integer rows,String where,Order order, String orderColumns,Object ... params);
	
	public List<T> getList(String where,Order order,String orderColumns,Object ... params);
	
	public List<T> getList(Order order,String orderColumns);
	
	public List<T> getList(String where,Object ... params);
	
	/**
	 * 获取最大值
	 * @param column 列
	 * @return
	 */
	public Integer getMax(String column);
	
	public Integer getMax(String column,String where, Object ... params);
	
	/**
	 * 获取最小值
	 * @param column 列
	 * @return
	 */
	public Integer getMin(String column);

	public Integer getMin(String column,String where, Object ... params);
	
	/**
	 * 求和
	 * @param column 求和列
	 * @return
	 */
	public Long getSum(String column);
	
	public Long getSum(String column,String where, Object ... params);
	
	public T load(Serializable i);
	
	public void meager(T t);
	
	public void persist(T t);
	
	/**
	 * 保存
	 * @param t 保存对象
	 * @return
	 */
	public Serializable save(T t);
	
	/**
	 * 保存或者更新
	 * @param t 对象
	 */
	public void saveOrUpdate(T t);
	
	/**
	 * 更新
	 * @param t 更新对象
	 */
	public void update(T t);
	
	/**
	 * sql带条件或者不带统计总数
	 * @param sql sql语句
	 * @param params 参数
	 * @return
	 */
	public String  getCountSQL(String sql,Object... params);
	
	/**
	 * 执行sql语句
	 * @param sql
	 * @param params
	 * @return
	 */
	public Boolean executeSql(String sql,Object... params);
	
	/**
	 * 根据条件查询单个对象
	 * @param hql hql语句
	 * @param params  参数
	 * @return
	 */
	public T getUniObj(String hql,Object... params);
	
	/**
	 * 根据hql查询集合对象
	 * @param hql hql语句
	 * @param params 参数
	 * @return
	 */
	public List<T> getTList(Integer pageIndex,Integer pageSize,String hql,Object... params);
	
	/**
	 * 根据sql语句查询集合对象
	 * @param sql sql语句
	 * @param params 参数
	 * @return
	 */
	public List<Object> getTListSql(Integer pageIndex,Integer pageSize,String sql,Object... params);
	
	/**
	 * HQL获取单个对象
	 * @return
	 */
	public Object getUniObjHql(String hql,Object... params);
	
	/**
	 * hql操作
	 * @param hql hql语句
	 * @param params 参数
	 * @return
	 */
	public Object executeHQL(String hql,Object... params);
	
	/**
	 * 删除HQL
	 * @param hql
	 * @param params 参数
	 */
	public void deleteHQL(String hql,Object... params);

	Object executeHQLTillid(String hql, Object[] params);
}
