package com.smw.dao;

import java.util.List;

import com.smw.pojo.XfStaff;

/**
 * 收银员信息表数据访问层接口
 * @author suen
 * @date 2016年5月18日-下午4:35:10
 * @version 1.0
 */
public interface XfStaffDao{
	
	/**
	 * 获取可用员工总数
	 */
	Integer getEnableStaffCount(String key, Integer auth);
	
	/**
	 * 获取可用员工
	 */
	List<XfStaff> getEnableStaff(Integer pageIndex, Integer pageSize, String key, Integer auth);
	
	/**
	 * 员工登录
	 */
	public XfStaff getXfStaff(XfStaff xfStaff);

	/**
	 * 查询收银员总数
	 * @param storecode 店铺编号
	 * @return
	 */
	public int getStaffCount(String storecode);

	/**
	 * 根据员工编号获取员工信息
	 * @param staffcode 员工编号
	 * @return
	 */
	public XfStaff getXfStaffByStaffCode(String staffcode);

	/**
	 * 根据店铺编号获取员工集合
	 * @param xfStorecode 店铺编号
	 * @return
	 */
	public List<XfStaff> getXfStaffListByStaffCode(String xfStorecode);

	public void saveOrUpdateXfStaff(XfStaff xfStaff);

	/**
	 * 根据店铺或者条件查询员工数
	 * @param xfStorecode 店铺编号
	 * @param key 搜索条件
	 * @return
	 */
	public Integer getCount(String xfStorecode, String key);

	/**
	 * 查询员工集合
	 * @param pageIndex 页吗
	 * @param pageSize 每页显示的行数
	 * @param xfStorecode 店铺号
	 * @param key 搜索条件
	 * @return
	 */
	public List<XfStaff> getXfStaffList(Integer pageIndex, Integer pageSize, String xfStorecode, String key);

	/**
	 * 根据员工编号删除员工信息
	 * @param xfStaffCode 员工编号
	 */
	public void deleteXfStaffBySC(String xfStaffCode);

	/**
	 * 根据店铺号删除员工
	 * @param xfStorecode 店铺编号
	 */
	public void deleteXfStaffBySTCode(String xfStorecode);

	public List<XfStaff> getListByRoleId(Integer roleId);
}