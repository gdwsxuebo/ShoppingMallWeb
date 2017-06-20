package com.gws.dao.core;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 *  公用HibernateDAO，封装常用方法
 *	@author newflypig
 *	time：2015年11月23日
 *
 */


@SuppressWarnings({ "unchecked"})
public abstract class BaseHibernateDAO<T extends Serializable> implements IBaseDAO<T>  {

	@Autowired public	 SessionFactory sessionFactory;
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	//子类实际类
	protected Class<T> entityClazz;
	private Logger log=LoggerFactory.getLogger(BaseHibernateDAO.class);
	
	public BaseHibernateDAO(){
		this.resovleClazzInfo();
	}
	
	private void resovleClazzInfo() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClazz = (Class<T>) params[0];
    }
	

	public Serializable save(T entity){
		Serializable keyValue = null;
		if(entity==null){
			log.info("None "+entityClazz.getSimpleName()+" object saved,it is NULL");
			return null;
		}

		keyValue = this.getSession().save(entity);
		log.info("Insert a " +entityClazz.getSimpleName()+ " object successful");
		
		return keyValue;
	}
	
	public void delete(T entity){
		this.getSession().delete(entity);
		log.info("Delete a " +entityClazz.getSimpleName()+ " object successful");
	}
	
	public void deleteById(Integer id){
		T entity=this.findById(id);
		if(entity!=null){
			this.delete(entity);
			log.info("Delete a " +entityClazz.getSimpleName()+ " object which id="+id+" successful");
		}else{
			log.info("Can't find any " +entityClazz.getSimpleName()+ " object which id="+id);
		}
	}
	
	public T findById(Integer id){
		T entity=(T)this.getSession().get(entityClazz, id);
		if(entity!=null)
			log.info("Find a " +entityClazz.getSimpleName()+ " object successful");
		else
			log.info("Find none " +entityClazz.getSimpleName()+ " object where id is:"+id);
		return entity;
	}
	
	public List<T> findAll(){
		String queryString = "from " +entityClazz.getSimpleName();
		List<T> list = this.getSession().createQuery(queryString).list();
		if(list.size()!=0)
			log.info("Find All " +entityClazz.getSimpleName()+ " objects");
		else
			log.info("Find none " +entityClazz.getSimpleName()+ " object");
		return list;
	}
	
	public void update(T entity){
		if(entity==null){
			log.info("None "+entityClazz.getSimpleName()+" object updated,it is NULL");
			return;
		}
		this.getSession().update(entity);
		log.info("Update a " +entityClazz.getSimpleName()+ " object successful");
	}
	
	@Override
	public List<T> findPager(DetachedCriteria dc, int page, int numPerPage){
		List<T> list=dc.getExecutableCriteria(this.getSession())
			.setFirstResult( (page - 1 ) * numPerPage )
			.setMaxResults( numPerPage ).list();
		if(list.size() != 0)
			log.info("Find " +entityClazz.getSimpleName()+ " objects at page "+page);
		else
			log.info("Find none " +entityClazz.getSimpleName()+ " object at page "+page);
		return list;
	}
	
	@Override
	public long getCount(DetachedCriteria dc) {
		dc.setProjection(Projections.rowCount());
		long count=(long)(dc.getExecutableCriteria(this.getSession()).uniqueResult());
		return count;
	}

	@Override
	public List<T> findByDC(DetachedCriteria dc) {
		return dc.getExecutableCriteria(this.getSession()).list();
	}
	
	
	public Map<String, Object> queryBySqlToUniqueMap(String sql,Object... params){
		Query query=getSession().createSQLQuery(sql);
		for (int i = 0,len=params.length; i < len; i++) {
			query.setParameter(i, params[i]);
        }
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return 	 (Map<String, Object>) query.uniqueResult();
	};
	public T queryBySqlToUnique(String sql,Object... params){
		Query query=getSession().createSQLQuery(sql).addEntity(entityClazz);
		for (int i = 0,len=params.length; i < len; i++) {
			query.setParameter(i, params[i]);
        }
		return 	 (T) query.uniqueResult();
	};
	
	public List<T> queryBySqlAllToPojo(String sql,int page,int rows,Object... params){
		Query query=getSession().createSQLQuery(sql).addEntity(entityClazz)
				.setFirstResult(page*rows-rows).setMaxResults(page*rows);
		for (int i = 0,len=params.length; i < len; i++) {
			query.setParameter(i, params[i]);
        }
		return 	 query.list();
	};
	public List<Map<String, Object>> queryBySqlAllToMap(String sql,int page,int rows,Object... params){
		Query query=getSession().createSQLQuery(sql)
				.setFirstResult(page*rows-rows).setMaxResults(page*rows);
		for (int i = 0,len=params.length; i < len; i++) {
			query.setParameter(i, params[i]);
        }
		
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return 	 query.list();
	};
	
	
	public List<Map<String, Object>> queryBySqlToMap(String sql,Object... params){
		Query query=getSession().createSQLQuery(sql);
		for (int i = 0,len=params.length; i < len; i++) {
			query.setParameter(i, params[i]);
        }
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
	public List<T> queryBySqlToPojo(String sql,Object... params){
		Query query=getSession().createSQLQuery(sql).addEntity(entityClazz);
		for (int i = 0,len=params.length; i < len; i++) {
			query.setParameter(i, params[i]);
        }
		return 	 query.list();
	};
}