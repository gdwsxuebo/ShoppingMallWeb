package com.gws.dao.impl;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.gws.dao.core.BaseHibernateDAO;
import com.gws.pojo.PosCommonConfigModel;
import com.smw.common.util.DateUtil;
/**
 * 公用配置
 */
@Repository
public class PosCommConfigDao  extends BaseHibernateDAO<PosCommonConfigModel>{
	
	/**
	 * 收银机公用配置查询
	 */
	public PosCommonConfigModel getPosComConfigByConid(String conid) {
		return queryBySqlToUnique("select * from pos_common_conifg where conid= ? ",conid);
	}

	/**
	 * 修改收银机公用配置
	 */
	public int updateposComConfigByConid(PosCommonConfigModel comConfig) {
		int result=-1;
		try {
			Session session=getSession();
			Query query=session.createSQLQuery("update pos_common_conifg set ssl_on =? ,baud_rate=? ,devpath=?,ip_pos=?"
					+ ",port=?,ssl_cert=?,ssl_sn=?,term_info=?,tpdu=?,utime=?  where conid=?");
			query.setParameter(0, comConfig.getSSL_ON());
			query.setParameter(1, comConfig.getBaudRate());
			query.setParameter(2, comConfig.getDevPath());
			query.setParameter(3, comConfig.getIpPos());
			query.setParameter(4, comConfig.getPort());
//			query.setParameter(5, comConfig.getScreenStyle());
			query.setParameter(5, comConfig.getSsl_cert());
			query.setParameter(6, comConfig.getSsl_sn());
			query.setParameter(7, comConfig.getTerm_info());
		    query.setParameter(8, comConfig.getTpdu());
			query.setParameter(9, DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			query.setParameter(10, comConfig.getConid());
			result=query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
