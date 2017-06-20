package com.smw.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.gws.util.StringUtil;
import com.smw.dao.GwMenuTreeDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.pojo.GwMenuTree;

/**
 * 
 * GwMenuTreeDaoImpl:菜单数据层实现
 *
 * @author  shengjinpeng
 * @date    2017年2月21日
 * @version jdk1.8
 *
 */
@Repository
public class GwMenuTreeDaoImpl extends BaseDaoSupport<GwMenuTree> implements GwMenuTreeDao {

	@Override
	public List<GwMenuTree> selectAllList() {
		// TODO Auto-generated method stub
		return getList();
	}

	@Override
	public List<GwMenuTree> selectListByFid(Integer fid) {
		// TODO Auto-generated method stub
		return getList("fid=?",fid);
	}

	@Override
	public GwMenuTree selectById(Integer id) {
		// TODO Auto-generated method stub
		return getUniObj("FROM GwMenuTree t WHERE t.id=?", id);
	}
	
	@Override
	public void saveInitAllMenu() {
		// TODO Auto-generated method stub
		String sqlMenu = StringUtil.getMenu().getSQL_MENU();// 菜单
		String sqlXF_STAFF = StringUtil.getMenu().getSQL_XF_STAFF();// 员工
		String SQL_GW_ROLE = StringUtil.getMenu().getSQL_GW_ROLE();// 角色
		String SQL_GW_ROLE_GROUP = StringUtil.getMenu().getSQL_GW_ROLE_GROUP();// 角色权限
		String SQL_XF_STAFF_ROLE = StringUtil.getMenu().getSQL_XF_STAFF_ROLE();// 员工权限

		if (sqlMenu != null) {
			System.out.println(sqlMenu);
			SQLQuery createSQLQuery = this.getSession().createSQLQuery(sqlMenu);
			createSQLQuery.executeUpdate();
		}

		if (sqlXF_STAFF != null) {
			SQLQuery createSQLQuery = this.getSession().createSQLQuery(sqlXF_STAFF);
			createSQLQuery.executeUpdate();
		}

		if (SQL_GW_ROLE != null) {
			SQLQuery createSQLQuery = this.getSession().createSQLQuery(SQL_GW_ROLE);
			createSQLQuery.executeUpdate();
		}

		if (SQL_XF_STAFF_ROLE != null) {
			SQLQuery createSQLQuery = this.getSession().createSQLQuery(SQL_XF_STAFF_ROLE);
			createSQLQuery.executeUpdate();
		}

		if (SQL_GW_ROLE_GROUP != null) {
			SQLQuery createSQLQuery = this.getSession().createSQLQuery(SQL_GW_ROLE_GROUP);
			createSQLQuery.executeUpdate();
		}
		    
		  
	}


}
