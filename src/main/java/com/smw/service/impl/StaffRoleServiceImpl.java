package com.smw.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smw.dao.StaffRoleDao;
import com.smw.service.StaffRoleService;

@Service
public class StaffRoleServiceImpl implements StaffRoleService{

	@Resource
	private StaffRoleDao staffRoleDao;	
	
	public void deleteStaffRole(String staffCode) {
		staffRoleDao.deleteStaffRole(staffCode);
	}
}
