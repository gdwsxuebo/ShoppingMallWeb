package com.gws.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.gws.dao.core.BaseHibernateDAO;
/**
 * 
 * @author Administrator
 *获取POS_Server 本地数据
 */
@Repository
public  class GetSynchPosData extends BaseHibernateDAO<Serializable>{

	/**
	 * 
	 * @return 获取POS_Server 员工数据
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getStaff(){
		Session session=getSession();	
		Query query=session.createSQLQuery("select A.xfStaffcode,A.enabled,A.xfName,A.xfPassword,A.xfIssuestore,B.authority from xf_staff A left join xf_staff_role B ON A.xfStaffcode=B.xfStaffcode WHERE  B.authority!='ROLE_MALL_ADMIN'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
	/**
	 * 
	 * @return 获取POS_Server 商品数据
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getItem(){	
		Session session=getSession();
		Query query=session.createSQLQuery("select xfPlu,xfDesci,xfStorecode,isInvalid,itemOrgid from xf_item");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
	/**
	 * 
	 * @return 获取POS_Server 店铺数据
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getStore(){
		Session session=getSession();
		Query query=session.createSQLQuery("select xfStorecode,xfName,xfMallid,isInvalid from xf_store ");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
	/**
	 * 
	 * @return 获取POS_Server 支付方式数据
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTender(){
		Session session=getSession();
		Query query=session.createSQLQuery("select xfTendercode,xfTenderdesc,isInvalid from xf_tender");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
	/**
	 * 
	 * @return 获取角色信息
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getRoles(){
		Session session=getSession();
		Query query=session.createSQLQuery("select authority,xfStaffcode from xf_staff_role");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
	public int saveinstallSql(String sql){
		Session session=getSession();
		Query query=session.createSQLQuery(sql);
		query.executeUpdate();
		return 3;
	};
	public int saveupdateSql(List<String> updateSql){
		for(String sql:updateSql){
			Session session=getSession();
			Query query=session.createSQLQuery(sql);
			query.executeUpdate();
		}
		return 3;
	};
	public int savedelSql(String sql){
		Session session=getSession();
		Query query=session.createSQLQuery(sql);
		query.executeUpdate();
		return 3;
	};
}
