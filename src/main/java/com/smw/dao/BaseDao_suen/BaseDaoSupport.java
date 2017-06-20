package com.smw.dao.BaseDao_suen;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

/**
 * 
 * @author teacher chen and student suen
 * 2015年8月8日
 * Version: 1.0
 */
@SuppressWarnings("unchecked")
public class BaseDaoSupport<T> implements BaseDao<T> {
	private Class<?>		entity	= (Class<?>) (((ParameterizedType) this
											.getClass().getGenericSuperclass())
											.getActualTypeArguments()[0]);

	private SessionFactory	sessionFactory;

	/**
	 * 根据Id删除
	 */
	public void delete(Serializable i) {
		if (i != null) {
			Object object = getSession().get(entity, i);
			if (object!=null) {
				getSession().delete(object);
            }
		}
	}

	/**
	 * 删除一个对象
	 */
	public void delete(T t) {
		getSession().delete(t);
	}

	/**
	 * 
	 * @param hql
	 * @param groupColumns
	 * @return
	 */
	private String formatGroup(String hql, String groupColumns) {
		return hql
				+ (groupColumns == null || groupColumns.trim().length() == 0 ? ""
						: " group by " + groupColumns);
	}
	
	/**
	 * 
	 * @param hql
	 * @param having
	 * @return
	 */
	private String formatHaving(String hql, String having) {
		return hql
				+ (having == null || having.trim().length() == 0 ? ""
						: " having " + having);
	}
	
	/**
	 * 
	 * @param hql
	 * @param order
	 * @param orderColumns
	 * @return
	 */
	private String formatOrder(String hql, Order order, String orderColumns) {
		if (orderColumns != null && orderColumns.trim().length() != 0) {
			hql += " order by " + orderColumns + " " + order.toString();
		}
		return hql;
	}

	/**
	 * 
	 * @param hql
	 * @param where
	 * @return
	 */
	private String formatWhere(String hql, String where) {
		return hql
				+ (where == null || where.trim().length() == 0 ? "" : " where "
						+ where);
	}

	/**
	 * 根据Id获取对象
	 * return T
	 */
	public T get(Serializable i) {
		return (T) getSession().get(entity, i);
	}
	
	/**
	 * 求哪一列(字段)平均值
	 * 例子：avg(sid)
	 * return double类型
	 */
	public Double getAvg(String column) {
		return getAvg(column, null);
	}
	
	/**
	 * 带条件求平均值
	 * 例子：avg(sid) from student sex=? 
	 * return double类型
	 */
	public Double getAvg(String column, String where, Object... params) {
		String hql = formatWhere(
				"select avg(" + column + ") from " + entity.getSimpleName(),
				where);
		Query query = getSession().createQuery(hql);
		query = setParams(query, params);
		return (Double) query.uniqueResult();
	}
	
	/**
	 * 统计行数
	 * return Long类型
	 */
	public Long getCount() {
		return getCount(null);
	}
	
	public String getCountSQL(String sql,Object... params){
		SQLQuery createSQLQuery = getSession().createSQLQuery(sql);
		for (int i = 0,len=params.length; i < len; i++) {
	        createSQLQuery.setParameter(i, params[i]);
        }
		Object uniqueResult = createSQLQuery.uniqueResult();
		return uniqueResult==null?null:uniqueResult.toString();
	}
	
	public Integer getCountBySQL(String sql,Object... params){
		SQLQuery createSQLQuery = getSession().createSQLQuery(sql);
		for (int i = 0,len=params.length; i < len; i++) {
	        createSQLQuery.setParameter(i, params[i]);
        }
		createSQLQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map<String, Object> uniqueResult = (Map<String, Object>) createSQLQuery.uniqueResult();
		String count=String.valueOf(uniqueResult.get("count(*)"));
		return  Integer.parseInt(count) ;
	}
	

	/**
	 * 带条件统计行数
	 * return Long类型 
	 */
	public Long getCount(String where, Object... params) {
		String hql = formatWhere(
				"select count(1) as nums from " + entity.getSimpleName(), where);
		//System.out.println(hql);
		
		Query query = setParams(getSession().createQuery(hql), params);
		return (Long) query.uniqueResult();
	}
	
	
	public List<Object[]> getGroup(String columns, String groupColumns) {
		return getGroup(columns, groupColumns, null, true, new Object[]{});
	}

	public List<Object[]> getGroup(String columns, String groupColumns,
			String where, boolean whereOrHaving, Object... params) {
		return getGroup(columns, groupColumns, where, whereOrHaving, null, null, params);
	}

	public List<Object[]> getGroup(String columns, String groupColumns,
			String where, boolean whereOrHaving, Order order, String orderColumns,
			Object... params) {
		if (whereOrHaving) {
			return getGroup(columns, groupColumns, where, params, null, new Object[]{}, order, orderColumns);
		}
		return getGroup(columns, groupColumns, null, new Object[]{}, where, params, order, orderColumns);
	}

	public List<Object[]> getGroup(String columns, String groupColumns,
			String where, Object[] whereParams, String having,
			Object[] havingParams, Order order, String orderColumns) {
		String hql = "select " + columns + " from " + entity.getSimpleName();
		hql = formatWhere(hql, where);
		hql = formatGroup(hql, groupColumns);
		hql = formatHaving(hql, having);
		hql = formatOrder(hql, order, orderColumns);
		
		Query query = setParams(getSession().createQuery(hql), concat(whereParams,havingParams));
		return (List<Object[]>)query.list();
	}

	private Object[] concat(Object[] whereParams, Object[] havingParams) {
		Object[] objs = new Object[whereParams.length + havingParams.length];
		int idx = 0;
		for (int i = 0,len=whereParams.length; i < len; i++,idx++) {
			objs[idx] = whereParams[i];
		}
		for (int i = 0,len=havingParams.length; i < len; i++,idx++) {
			objs[idx] = havingParams[i];
		}
		return objs;
	}

	/**
	 * 获取一个集合
	 */
	public List<T> getList() {
		return getList(null);
	}

	/**
	 * 分页
	 */
	public List<T> getList(Integer page, Integer rows) {
		return getList(page, rows, null);
	}

	/**
	 * 分页排序
	 */
	public List<T> getList(Integer page, Integer rows, Order order,
			String orderColumns) {
		return getList(page, rows, null, order, orderColumns);
	}
	
	/**
	 * 分页并带条件
	 */
	public List<T> getList(Integer page, Integer rows, String where,
			Object... params) {
		return getList(page, rows, where, null, null, params);
	}
	
	/**
	 * 分页排序带条件并查询哪一列(orderColumns)
	 */
	public List<T> getList(Integer page, Integer rows, String where,
			Order order, String orderColumns, Object... params) {
		System.out.println(formatOrder(
								formatWhere("from " + entity.getSimpleName(),
										where), order, orderColumns));
		Query query = setParams(
				
				getSession().createQuery(
						formatOrder(
								formatWhere("from " + entity.getSimpleName(),
										where), order, orderColumns)), params);
		if (page != null && rows != null) {
			query.setFirstResult((page-1)*rows).setMaxResults(rows);//hql分页
		}
		return (List<T>) query.list();
	}
	
	/**
	 * 查询某一列(字段)并排序
	 */
	public List<T> getList(Order order, String orderColumns) {
		return getList(null, null, order, orderColumns);
	}

	/**
	 * 带条件查询
	 */
	public List<T> getList(String where, Object... params) {
		return getList(null, null, where,null,null, params);
	}

	/**
	 * 获取某一列(字段)的最大值
	 * 例子：获取id的最大的值
	 */
	public Integer getMax(String column) {
		return getMax(column, null);
	}
	
	/**
	 * 带条件查询某一列
	 */
	public Integer getMax(String column, String where, Object... params) {
		String hql = formatWhere(
				"select max(" + column + ") from " + entity.getSimpleName(),
				where);
		Query query = getSession().createQuery(hql);
		query = setParams(query, params);
		return (Integer) query.uniqueResult();
	}

	/**
	 * 获取某一列的最小值
	 */
	public Integer getMin(String column) {
		return getMin(column, null);
	}

	/**
	 * 带条件查询获取某一列的最小值
	 */
	public Integer getMin(String column, String where, Object... params) {
		String hql = formatWhere(
				"select min(" + column + ") from " + entity.getSimpleName(),
				where);
		Query query = getSession().createQuery(hql);
		query = setParams(query, params);
		return (Integer) query.uniqueResult();
	}

	/**
	 * 获取session
	 * @return
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 求和
	 */
	public Long getSum(String column) {
		return getSum(column, null);
	}

	/**
	 * 带条件求和
	 */
	public Long getSum(String column, String where, Object... params) {
		String hql = formatWhere(
				"select sum(" + column + ") from " + entity.getSimpleName(),
				where);
		Query query = getSession().createQuery(hql);
		query = setParams(query, params);
        return (Long)query.uniqueResult();
	}
	
	public T load(Serializable i) {
		return (T) getSession().load(entity, i);
	}
	
	
	public void meager(T t) {
		getSession().merge(t);
	}
	//save()
	public void persist(T t) {
		getSession().persist(t);
	}

	//保存
	public Serializable save(T t) {
		Serializable save = getSession().save(t);
		return save;
	}

	public void saveOrUpdate(T t) {
		getSession().saveOrUpdate(t);
	}
	
	public void saveOrUpdate2(T t){
		getSession().merge(t);
	}

	private Query setParams(Query query, Object... params) {
		for (int i = 0,len=params.length; i < len; i++) {
			query.setParameter(i, params[i]);
		}
		return query;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 更新
	 */
	public void update(T t) {
		getSession().update(t);
	}

	@Override
    public List<T> getList(String where, Order order, String orderColumns,
            Object... params) {
	    return getList(null,null, where, order, orderColumns, params);
    }

	@Override
    public Boolean executeSql(String sql,Object... params) {
		SQLQuery createSQLQuery = getSession().createSQLQuery(sql);
		if (params!=null) {
			for (int i = 0,len=params.length; i <len ; i++) {
			       createSQLQuery.setParameter(i, params[i]);
		    } 
        }
		int executeUpdate = createSQLQuery.executeUpdate();
	    return executeUpdate>0;
    }

	@Override
    public T getUniObj(String hql, Object... params) {
		Query createQuery = getSession().createQuery(hql);
		Integer length=params.length;
		if (length==1) {
			createQuery.setParameter(0, params[0]);
        }else if (length>1) {
        	for (int i = 0,len=length; i <len ; i++) {
    	        createQuery.setParameter(i, params[i]);
            }
        }
	    return (T)createQuery.uniqueResult();
    }

	@Override
    public List<T> getTList(Integer pageIndex,Integer pageSize,String hql, Object... params) {
		Query createQuery = getSession().createQuery(hql);
		for (int i = 0,len=params.length; i < len; i++) {
	        createQuery.setParameter(i, params[i]);
        }
		if (pageIndex != null && pageSize != null) {
			createQuery.setFirstResult((pageIndex-1)*pageSize).setMaxResults(pageSize);
		}
	    return createQuery.list();
    }

	@Override
    public List<Object> getTListSql(Integer pageIndex,Integer pageSize,String sql, Object... params) {
		SQLQuery createSQLQuery = getSession().createSQLQuery(sql);
		for (int i = 0,len=params.length; i < len; i++) {
			createSQLQuery.setParameter(i, params[i]);
        }
		if (pageIndex != null && pageSize != null) {
			createSQLQuery.setFirstResult((pageIndex-1)*pageSize).setMaxResults(pageSize);
		}
	    return createSQLQuery.list();
    }

	/**
	 * HQL获取单个对象
	 * @return
	 */
	@Override
	public Object getUniObjHql(String hql,Object... params) {
		Query createQuery = getSession().createQuery(hql);
		for (int i = 0,len=params.length; i < len; i++) {
			createQuery.setParameter(i, params[i]);
        }
		return createQuery.uniqueResult();
	}

	/**
	 * hql操作 uniqueResult
	 * @param hql hql语句
	 * @param where 条件
	 * @return
	 */
	@Override
	public Object executeHQL(String hql, Object... params) {
		Query createQuery = getSession().createQuery(hql);
		for (int i = 0,len=params.length; i < len; i++) {
			createQuery.setParameter(i, params[i]);
        }
		return createQuery.uniqueResult();
	}

	/**
	 * hql操作 uniqueResult
	 * @param hql hql语句
	 * @param where 条件
	 * @return
	 */
	@Override
	public List<Object> executeHQLTillid(String hql, Object... params) {
		Query createQuery = getSession().createQuery(hql);
		for (int i = 0,len=params.length; i < len; i++) {
			createQuery.setParameter(i, params[i]);
        }
		return createQuery.list();
	}

	/**
	 * 删除HQL
	 * @param hql
	 * @param params 参数
	 */
	@Override
	public void deleteHQL(String hql, Object... params) {
		Query createQuery = getSession().createQuery(hql);
		for (int i = 0,len=params.length; i < len; i++) {
			createQuery.setParameter(i, params[i]);
        }
		createQuery.executeUpdate();
	}
	
	public void	saveRefundOrder(String originalTxdocno,String sql) {
		Session session=getSession();
		Query query=session.createSQLQuery(sql);
		query.setParameter(0, originalTxdocno);
		query.executeUpdate();
}
	
	public List<T> queryBySqlAllToPojo_GWS(String sql,Object... params){
		Query query=getSession().createSQLQuery(sql).addEntity(entity);
		for (int i = 0,len=params.length; i < len; i++) {
			query.setParameter(i, params[i]);
        }
		return 	 query.list();
	}
	public Map<String, Object> queryBySqlToUniqueMap_GWS(String sql,Object... params){
		Query query=getSession().createSQLQuery(sql);
		for (int i = 0,len=params.length; i < len; i++) {
			query.setParameter(i, params[i]);
        }
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return 	 (Map<String, Object>) query.uniqueResult();
	};
}
