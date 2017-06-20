package com.smw.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smw.dao.GwRoleDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.dao.BaseDao_suen.Order;
import com.smw.pojo.GwRole;
import com.smw.pojo.PosConfigModel;

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
public class GwRoleDaoImpl extends BaseDaoSupport<GwRole> implements GwRoleDao {

	public GwRole selectById(Integer id) {
		return getUniObj("FROM GwRole r WHERE r.id=?", id);
	}

	public void saveOrupdateGwRole(GwRole role) {
		saveOrUpdate(role);
	}

	public void deleteById(Integer id) {
		delete(id);
	}

	public List<GwRole> selectAllList() {
		return getList("state=?",1);
	}

	public List<GwRole> selectPageList(Integer pageIndex, Integer pageSize) {
		return getList(pageIndex, pageSize,Order.ASC,"orderNum");
	}

	public Object getCount(String key) {
		String count;
		if(key == null){
			count = executeHQL("select count(1) from GwRole").toString();
		}else{
			count = executeHQL("select count(1) from GwRole x where x.cnName like ? or x.enName like ? ", "%" + key + "%","%" + key + "%").toString();
		}
		return count == null ? 0 : Integer.parseInt(count);
	}

	public List<GwRole> selectPageList(Integer pageIndex, Integer pageSize, String key) {
		List<GwRole> gwRoles;
		if(key == null){
			gwRoles=getList(pageIndex,pageSize);
		}else{
			gwRoles=getList(pageIndex, pageSize, " cnName like ? ", "%" + key + "%");
		}
		return gwRoles;
	}
}
