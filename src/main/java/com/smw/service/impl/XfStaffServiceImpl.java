package com.smw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smw.dao.XfStaffDao;
import com.smw.pojo.XfStaff;
import com.smw.service.XfStaffService;

/**
 * 收银员信息表服务层实现接口
 * @author suen
 * @date 2016年5月19日-上午11:42:39
 * @version 1.0
 */
@Service
public class XfStaffServiceImpl implements XfStaffService {
	
	@Resource
	private XfStaffDao xfStaffDao;
	
	/**
	 * 获取可用员工总数
	 */
	public Integer getEnableStaffCount(String key, Integer auth){
		return xfStaffDao.getEnableStaffCount(key,auth);
	}
	
	
	/**
	 * 获取可用员工
	 */
	public List<XfStaff> getEnableStaff(Integer pageIndex, Integer pageSize, String key, Integer auth){
		return xfStaffDao.getEnableStaff(pageIndex, pageSize, key, auth);
	}
	

	/**
	 * 查询收银员对象
	 * @param xfStaff 收营员对象
	 * @return
	 */
	@Override
	public XfStaff getXfStaff(XfStaff xfStaff) {
		return xfStaffDao.getXfStaff(xfStaff);
	}

	/**
	 * 根据店铺编号获取员工数量
	 */
	@Override
	public int getStaffCount(String storecode) {
		return xfStaffDao.getStaffCount(storecode);
	}

	/**
	 * 根据员工编号获取员工信息
	 * @param staffcode 员工编号
	 * @return
	 */
	@Override
	public XfStaff getXfStaffByStaffCode(String staffcode) {
		return xfStaffDao.getXfStaffByStaffCode(staffcode);
	}

	/**
	 * 根据店铺编号获取员工集合
	 * @param xfStorecode 店铺编号
	 * @return
	 */
	@Override
	public List<XfStaff> getXfStaffListByStaffCode(String xfStorecode) {
		return xfStaffDao.getXfStaffListByStaffCode(xfStorecode);
	}

	/**
	 *修改或者保存员工 
	 */
	@Override
	public void saveOrUpdateXfStaff(XfStaff xfStaff) {
		xfStaffDao.saveOrUpdateXfStaff(xfStaff);
	}

	/**
	 * 根据店铺或者条件查询员工数
	 * @param xfStorecode 店铺编号
	 * @param key 搜索条件
	 * @return
	 */
	@Override
	public Integer getCount(String xfStorecode, String key) {
		return xfStaffDao.getCount(xfStorecode,key);
	}

	/**
	 * 查询员工集合
	 * @param pageIndex 页吗
	 * @param pageSize 每页显示的行数
	 * @param xfStorecode 店铺号
	 * @param key 搜索条件
	 * @return
	 */
	@Override
	public List<XfStaff> getXfStaffList(Integer pageIndex, Integer pageSize, String xfStorecode, String key) {
		return xfStaffDao.getXfStaffList(pageIndex,pageSize,xfStorecode,key);
	}

	/**
	 * 根据员工编号删除员工信息
	 * @param xfStaffCode 员工编号
	 */
	@Override
	public void deleteXfStaffBySC(String xfStaffCode) {
		xfStaffDao.deleteXfStaffBySC(xfStaffCode);
	}

	/**
	 * 根据店铺号删除员工
	 * @param xfStorecode 店铺编号
	 */
	@Override
	public void deleteXfStaffBySTCode(String xfStorecode) {
		xfStaffDao.deleteXfStaffBySTCode(xfStorecode);
	}

	@Override
	public List<XfStaff> getListByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return xfStaffDao.getListByRoleId(roleId);
	}
}
