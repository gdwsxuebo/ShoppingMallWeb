package com.smw.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.smw.dao.XfStoreDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.pojo.XfStore;

/**
 * 店铺信息表数据访问层实现接口
 * @author suen
 * @date 2016年5月18日-下午9:09:50
 * @version 1.0
 */

@Repository
public class XfStoreDaoImpl extends BaseDaoSupport<XfStore>  implements XfStoreDao {
	
	/**
	 *  通过楼宇或业态查询店铺
	 */
	public List<XfStore> getStoreByBF(Integer pageIndex, Integer pageSize, String buildings, String formats){
		
		List<XfStore> xfStores;
		if(buildings.equals("") && formats.equals("")){  //都为空
//			xfStores=getList(pageIndex,pageSize, " isInvalid=1 ");
			xfStores = null;
		}else if(!buildings.equals("") && formats.equals("")){  
			xfStores=getList(pageIndex, pageSize, " gwBuildingTreeId in ("+buildings+") and isInvalid=1 ");
		}else if(!formats.equals("") && buildings.equals("")){  
			xfStores=getList(pageIndex, pageSize, " gwFormatsTreeId in ("+formats+") and isInvalid=1 ");
		}else{
			xfStores=getList(pageIndex, pageSize, " gwFormatsTreeId in ("+formats+") and gwBuildingTreeId in ("+buildings+") and isInvalid=1 ");
		}
		return xfStores;
	}
	

	/**
	 * 带条件查询商铺总数
	 * @param storecode 关联的店铺号
	 * @param key 搜索条件
	 * @return
	 */
	@Override
	public Integer getCount(String storecode, String key) {
		Object uniObjHql;
		if (storecode==null) {
			if (key==null) {
				uniObjHql = getUniObjHql("SELECT COUNT(1) FROM XfStore x WHERE x.isInvalid=1");
			}else{
				uniObjHql = getUniObjHql("SELECT COUNT(1) FROM XfStore x WHERE x.isInvalid=1 AND x.xfStorecode like ? OR x.xfName like ?", "%"+key+"%", "%"+key+"%");
			}
			
		}else{
			uniObjHql = getUniObjHql("SELECT COUNT(1) FROM XfStore x WHERE x.isInvalid=1 AND x.xfStorecode=?",storecode);
		}
		return uniObjHql==null?0:Integer.parseInt(uniObjHql.toString());
	}

	/**
	 * 根据店铺编号查询店铺信息
	 * @param storeCode 店铺编号
	 * @return
	 */
	@Override
	public XfStore getXfStoreByStoreCode(String storeCode) {
		return getUniObj("FROM XfStore c WHERE c.isInvalid=1 AND c.xfStorecode=?", storeCode);
	}
	
	@Override
	public List<XfStore> list() {
		Session session = getSession();
		return session.createQuery("from XfStore x WHERE x.isInvalid=1").list();
	}

	@Override
	public void saveOrUpdateXfStores(List<XfStore> stores) {
		for (XfStore xfStore : stores) {
			saveOrUpdate(xfStore);
		}
	}

	/**
	 * 获取
	 * @param pageIndex 页码
	 * @param pageSize 每页显示的行数
	 * @param xfStaffcode 店铺号
	 * @param key 搜索条件
	 * @return
	 */
	@Override
	public List<XfStore> getXfStoreList(Integer pageIndex, Integer pageSize, String xfStaffcode, String key) {
		List<XfStore> xfStores;
		if (xfStaffcode==null) {
			if (key==null) {
				xfStores=getList(pageIndex, pageSize,"isInvalid=1");
			}else{
				xfStores=getList(pageIndex, pageSize, "isInvalid=1 AND ( xfStorecode like ? OR xfName like ? )", "%"+key+"%", "%"+key+"%");
			}
		}else{
			xfStores=getList(pageIndex, pageSize, "isInvalid=1 AND xfStorecode = ?",xfStaffcode);
		}
		return xfStores;
	}

	/**
	 * 保存或者修改单个店铺信息
	 * @param xfStore 店铺对象
	 */
	@Override
	public void saveOrUpdateSingXfStores(XfStore xfStore) {
		saveOrUpdate(xfStore);
	}
	@Override
	public void saveOrUpdateXfStore(XfStore xs) {
		saveOrUpdate(xs);
	}

	/**
	 * 根据店铺编号删除店铺
	 * @param xfStorecode 店铺编号
	 */
	@Override
	public void deleteXfStoreByStCode(String xfStorecode) {
		deleteHQL("UPDATE XfStore x set x.isInvalid='0' WHERE x.xfStorecode=?", xfStorecode);
		//删除所有关联中央店铺的数据
		executeSql("delete FROM xf_store_center where xfCenterstorecode=?", xfStorecode);
	}

	@Override
	public List<XfStore> listAllStore() {
		return getList();
	}

	@Override
	public void deleteXfStoreByStCode(List<String> delCenterStore) {

		StringBuffer buff=new StringBuffer();
		int i=0;
		for(String store:delCenterStore){
			if(i==0)buff.append("'"+store+"'");
			else buff.append(",'"+store+"'");
			i++;
		}
		String sql="DELETE from xf_store_center where xfCenterstorecode in("+buff+")";
		System.out.println(sql);
		Session session=getSession();
		Query query=session.createSQLQuery(sql);
		query.executeUpdate();
	
		
	}

	@Override
	public XfStore getXfStoreByCode(String storeCode) {
		return getUniObj("FROM XfStore c WHERE c.xfStorecode=?", storeCode);
	}


	@Override
	public List<XfStore> getByXfStoreNameORCode(String codeOrName) {
		// TODO Auto-generated method stub
		return getList("isInvalid=1 AND ( xfStorecode like ? OR xfName like ? )", "%"+codeOrName+"%", "%"+codeOrName+"%");
	}
}
