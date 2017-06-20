package com.gws.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.gws.dao.core.BaseHibernateDAO;
import com.smw.pojo.XfStoreCenter;

@Repository
public class GwXfStoreCenterDao extends BaseHibernateDAO<XfStoreCenter>{
	/**
	 * 根据店铺号删除中央店铺
	 */
	public Integer deleteByStoreCode(String storeid) {
		Session session=getSession();
		Query q=session.createSQLQuery("delete from xf_store_center where xfCenterstorecode=?");
		q.setParameter(0, storeid);
		return q.executeUpdate();
	}
	
	/**
	 * 根据店铺号插入中央店铺
	 */

	public Integer insertCenterStore(String storeid, String centerStore) {
		Session session=getSession();
		Query q=session.createSQLQuery("insert into xf_store_center(xfStorecode,xfCenterstorecode) values(?,?)");
		q.setParameter(0, centerStore);
		q.setParameter(1, storeid);
		return q.executeUpdate();
	}
	
	/**查询中央店铺的数量，用于前台页面饼状图显示
	 * @return
	 */
	public Object getAllCount() {
		Session session=getSession();
		Query q=session.createSQLQuery(" SELECT count(*) FROM xf_store where xfCenter=1 and isInvalid=1 ");
		Object ij = q.uniqueResult();
		return ij;
	}

	/**查询非中央店铺的数量，用于前台页面饼状图显示
	 * @return
	 */
	public Object getNoAllCount() {
		Session session=getSession();
		Query q=session.createSQLQuery("SELECT count(*) FROM xf_store where xfCenter=0 and isInvalid=1 ");
		Object ij = q.uniqueResult();
		return ij;
	}
	//没有用虚拟表的sql语句
	//   select  sum(netamount) from sales_summary where date_sub(curdate(), INTERVAL 7 DAY) <= date(txdate) GROUP BY txdate; 
	public List<String> getData() {
		Session session=getSession();
		Query q=session.createSQLQuery(" select  money from week_sale_money where date_sub(curdate(), INTERVAL 7 DAY) <= date(date) GROUP BY date order by date desc; ");
		List<String> ij = q.list();
		
		return ij;
	}
	//
	
	//select  txdate from sales_summary where date_sub(curdate(), INTERVAL 7 DAY) <= date(txdate) GROUP BY txdate;
	public List<String> getCurrentData() {
		Session session=getSession();
		Query q=session.createSQLQuery(" select  date from week_sale_money where date_sub(curdate(), INTERVAL 7 DAY) <= date(date) GROUP BY date order by date desc; ");
		List<String> ij = q.list();
		return ij;
	}

	public List<Map<String, Object>> getTenderby7day() {
		String sql="   SELECT f.xfTenderdesc,IFNULL(mm.money,0) as money from ( select y.tendercode as tendercode,sum(y.payamount) as money  from ( SELECT s.txdocno ,s.txdate from sales_summary s where date_sub(curdate(), INTERVAL 7 DAY) <= date(s.txdate) order BY s.txdate )m,sales_tender y where m.txdocno=y.txdocno  GROUP BY y.tendercode) mm RIGHT JOIN xf_tender f ON f.xfTendercode=mm.tendercode where f.isInvalid=1 ";
		return queryBySqlToMap(sql);
	}

	public List<Map<String, Object>> getCountScale() {
//		/(select COUNT(1) from xf_staff)*100)
		String sql="  SELECT f.authority as role,ROUND(count(f.authority)) as number ,ROUND(count(f.authority)/(select COUNT(1) from xf_staff )*100) as scale from xf_staff x ,xf_staff_role f where x.xfStaffcode=f.xfStaffcode  group by authority  ";
		return queryBySqlToMap(sql);
	}
	
	
		
}	
