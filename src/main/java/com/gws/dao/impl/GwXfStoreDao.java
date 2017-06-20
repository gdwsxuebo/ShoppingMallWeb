package com.gws.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gws.dao.core.BaseHibernateDAO;
import com.smw.pojo.XfStore;

@Repository
public class GwXfStoreDao extends BaseHibernateDAO<XfStore>{

	//获取所有店铺
	public List<XfStore> getStoreList(){
		String sql = "select * from xf_store where xf_store.isInvalid='1'";
		return queryBySqlToPojo(sql);
	}
	
	/**
	 * 模糊查询
	 */
	public List<Map<String, Object>> getStoreByStorecode(String storeCode){
		String sql = "SELECT xfStorecode,xfName FROM xf_store WHERE xfName LIKE '%"+storeCode+"%'";
		return queryBySqlToMap(sql);
	}
	
	/**
	 * 搜索
	 */
	public List<Map<String, Object>> getStoreByName(String storeName,String storecode){
		String sql = "SELECT A.*,B.* FROM xf_store A LEFT JOIN (SELECT xfStorecode AS ischildren,xfCenterstorecode FROM "
		+" xf_store_center WHERE xfCenterstorecode ='"+storecode+"') B ON A.xfStorecode = B.ischildren where A.xfStorecode not in('"+storecode+"') "
		+" and A.isInvalid='1' and A.xfName LIKE '%"+storeName+"%'";
		return queryBySqlToMap(sql);
	}
	
	//==============================================================
	public List<Map<String, Object>> getAllStoreInfo() {
		return queryBySqlToMap("select xfStorecode,xfName from xf_store");
	}

	public XfStore getStoreInfoByTillid(String tillid) {
		return queryBySqlToUnique("select B.* from xf_store_auth A LEFT JOIN xf_store B ON A.xfStoreCode=B.xfStorecode  where A.tillid=? ", tillid);
	}
	public List<Map<String, Object>> getXfStoreList(int page, int rows){
		//String sql="select a.id as id,a.xfStorecode as storeid,a.auth_id as authid from xf_store_auth as a LEFT JOIN xf_store as B on a.xfStoreCode=B.xfStorecode";
		String sql="select CASE a.auth_id WHEN 1 THEN '离线支付' when 2 then '离线支付与退货' when 0 then '无' END as authid,"
				+ "a.id as id,CONCAT(a.xfStorecode,':',B.xfName) as storeid, "
				+ "a.tillid as tillid,"
				+ "a.v61tillid as v61tillid, "
				+ "a.ctime as ctime, "
				+ "CASE a.screen_style WHEN 1 THEN '双屏' when 0 then '单屏' END as screenStyle "
				+ "from xf_store_auth as a LEFT JOIN xf_store as B on a.xfStoreCode=B.xfStorecode";
		return	queryBySqlAllToMap(sql,page,rows);
	};
	
	public List<Map<String, Object>> getAllStoreTree() {
		return queryBySqlToMap("select xfStorecode,xfName from xf_store where not exists (select 1 from xf_store_auth where xf_store_auth.xfStoreCode=xf_store.xfStorecode)");
	}

	/**
	 * 获取除了中央店铺的其他店铺
	 */
	public List<Map<String, Object>> queryStoreCenterStore(String storecode) {
		String sql="select A.xfStorecode,A.xfName,B.* from xf_store A "
				+" LEFT JOIN (SELECT xfStorecode AS ischildren,xfCenterstorecode from  xf_store_center where xfCenterstorecode=? ) B ON A.xfStorecode=B.ischildren "
				+" where A.xfStorecode not in(?) and A.isInvalid='1'";
		return queryBySqlToMap(sql,storecode,storecode);
	}
	
}
