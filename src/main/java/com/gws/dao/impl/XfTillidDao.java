package com.gws.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gws.dao.core.BaseHibernateDAO;
import com.smw.pojo.XfTillidState;
@Repository
public class XfTillidDao extends BaseHibernateDAO<XfTillidState>{
	
		public List<Map<String, Object>> getAllXfTillid(){
			return 	queryBySqlToMap("select a.xfStorecode,b.xfName,a.tillid,a.ip from xf_tillid_state  a left join xf_store  b on  a.xfStorecode=b.xfStorecode");
		}
			
}	
