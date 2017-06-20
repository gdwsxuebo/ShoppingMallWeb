package com.smw.dao.impl;

import org.springframework.stereotype.Repository;

import com.smw.dao.StaffRoleDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.pojo.StaffRole;

@Repository
public class StaffRoleDaoImpl extends BaseDaoSupport<StaffRole> implements StaffRoleDao {

	/**
	 * 删除
	 */
	public void deleteStaffRole(String staffCode) {
		delete(staffCode);
	}
}
