package com.smw.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.smw.dao.GwRoleGroupDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.dao.BaseDao_suen.Order;
import com.smw.pojo.GwRoleGroup;

/**
 * 
 * 
 * GwRoleDaoImpl:角色数据层实现
 *
 * @author  shengjinpeng
 * @date    2017年2月21日
 * @version jdk1.8
 *
 */
@Repository
public class GwRoleGroupDaoImpl extends BaseDaoSupport<GwRoleGroup> implements GwRoleGroupDao {

	
	public void saveOrUpdateRoleGroup(GwRoleGroup roleGroup) {
		saveOrUpdate(roleGroup);;
	}

	public List<GwRoleGroup> getRoleGroupsByRoleId(Integer roleId) {
		return getList("gwRoleId.id=?", Order.ASC, "gwMenuTreeId.id" , roleId);
	}

	public void deleteListBy(Integer roleId, Integer[] menuId) {
		String hql="delete GwRoleGroup g WHERE g.gwRoleId.id=? and g.gwMenuTreeId.id IN (:alist)";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, roleId);
		query.setParameterList("alist", menuId); 
		query.executeUpdate();
	}

	public void deleteByRoleId(Integer roleId) {
		deleteHQL("delete GwRoleGroup g where g.gwRoleId.id=?",roleId);
	}

	public List<GwRoleGroup> getRoleGroupsByRoleId(Integer roleId, Integer fid) {
		return getList("gwRoleId.id=? and gwMenuTreeId.fid=?", Order.ASC, "gwMenuTreeId.id" , roleId, fid );
	}


}
