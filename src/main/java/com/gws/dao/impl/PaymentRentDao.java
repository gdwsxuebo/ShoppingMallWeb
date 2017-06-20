package com.gws.dao.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.gws.dao.core.BaseHibernateDAO;
import com.gws.pojo.PaymentRent;

@Repository
public class PaymentRentDao extends BaseHibernateDAO<PaymentRent> {

	/** 保存 **/
	public void savePaymentRent(PaymentRent paymentRent) {
		save(paymentRent);
	}
	
	/**列表**/
	public List<PaymentRent> getPaymentRentList(){
		return findAll();
	}
	/**列表**/
	public List<Map<String, Object>> getPaymentRentList(int page, int rows){
		String sql="select * from payment_rent";
		return queryBySqlAllToMap(sql,page,rows);
	};
	/**删除**/
	public void deleteAll(){
		Session session=getSession();
		String sql = "DELETE FROM payment_rent";
		Query query=session.createSQLQuery(sql);
		query.executeUpdate();
	}
	/**查询**/
	public int queryCount(){
		String sql="select count(*) from payment_rent";
		Map<String, Object> map=queryBySqlToUniqueMap(sql);
		  BigInteger a = (BigInteger) map.get("count(*)");
		  int count= a.intValue();	//转换成int类
		return count;
	}
	
}
