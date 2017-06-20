package com.smw.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smw.dao.XfStaffDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.pojo.XfStaff;

/**
 * 收银员信息表数据访问层实现接口
 * 
 * @author suen
 * @date 2016年5月19日-上午11:47:38
 * @version 1.0
 */
@Repository
public class XfStaffDaoImpl extends BaseDaoSupport<XfStaff> implements XfStaffDao {

	
	/**
	 * 获取可用员工总数
	 */
	public Integer getEnableStaffCount(String key,Integer auth){
		String count = null;
		if(key != null){
			count = getUniObjHql("SELECT COUNT(1) FROM XfStaff x WHERE x.enabled=1 AND (x.xfStaffcode like ? OR x.xfName=?)", "%" + key + "%", "%" + key + "%").toString();
		}
		if(auth != null){
			count = getUniObjHql("SELECT COUNT(1) FROM XfStaff x WHERE x.enabled=1 AND isReturnGoodsAuth=?", auth).toString();
		}
		if(key != null && auth != null){
			count = getUniObjHql("SELECT COUNT(1) FROM XfStaff x WHERE x.enabled=1 AND isReturnGoodsAuth=? AND (x.xfStaffcode like ? OR x.xfName=?)",
					auth,"%" + key + "%", "%" + key + "%").toString();
		}
		if(key == null && auth == null){
			count = getUniObjHql("SELECT COUNT(1) FROM XfStaff x WHERE x.enabled=1").toString();
		}
		return count == null ? 0 : Integer.parseInt(count);
	}
	
	/**
	 * 获取可用员工
	 */
	public List<XfStaff> getEnableStaff(Integer pageIndex, Integer pageSize, String key, Integer auth){
		List<XfStaff> staffs = null;
		if(key != null){
			staffs=getList(pageIndex, pageSize, "enabled=1 AND (xfStaffcode LIKE ? OR xfName LIKE ?)", "%" + key + "%", "%" + key + "%");
		}
		if(auth != null){
			staffs=getList(pageIndex, pageSize, "enabled=1 AND isReturnGoodsAuth=?", auth);
		}
		if(key != null && auth != null){
			staffs=getList(pageIndex, pageSize, "enabled=1 AND isReturnGoodsAuth=? AND (xfStaffcode LIKE ? OR xfName LIKE ?)", auth,"%" + key + "%", "%" + key + "%");
		}
		if(key == null && auth == null){
			staffs=getList(pageIndex,pageSize," enabled=1");
		}
		return staffs;
	}
	
	/**
	 * 查询收银员对象
	 * @param xfStaff  收营员对象
	 */
	@Override
	public XfStaff getXfStaff(XfStaff xfStaff) {
		return getUniObj("FROM XfStaff x WHERE x.xfStaffcode=? AND x.xfPassword=? AND x.enabled=true", xfStaff.getXfStaffcode(),
				xfStaff.getXfPassword());
	}

	/**
	 * 查询收银员总数
	 * @param storecode 店铺编号
	 */
	@Override
	public int getStaffCount(String storecode) {
		String countSQL = getCountSQL("select count(1) from xf_staff x where x.xfIssuestore=? and x.enabled=1 ", storecode);
		return countSQL == null ? 0 : Integer.parseInt(countSQL);
	}

	/**
	 * 根据员工编号获取员工信息
	 * @param staffcode  员工编号
	 */
	@Override
	public XfStaff getXfStaffByStaffCode(String staffcode) {
		return getUniObj("FROM XfStaff x WHERE x.xfStaffcode=?", staffcode);
	}

	/**
	 * 根据店铺编号获取员工集合
	 * @param xfStorecode 店铺编号
	 */
	@Override
	public List<XfStaff> getXfStaffListByStaffCode(String xfStorecode) {
		return getList("xfIssuestore.xfStorecode=? and enabled=1", xfStorecode);
	}

	@Override
	public void saveOrUpdateXfStaff(XfStaff xfStaff) {
		saveOrUpdate(xfStaff);
	}

	/**
	 * 根据店铺或者条件查询员工数
	 * @param xfStorecode  店铺编号
	 * @param key  搜索条件
	 */
	@Override
	public Integer getCount(String xfStorecode, String key) {
		String count;
		if (xfStorecode == null) {
			if (key == null) {
				count = getUniObjHql("SELECT COUNT(1) FROM XfStaff").toString();
			} else {
				count = getUniObjHql(
						"SELECT COUNT(1) FROM XfStaff x WHERE x.xfIssuestore.xfStorecode like ? OR x.xfStaffcode like ? OR x.xfName like ?",
						"%" + key + "%", "%" + key + "%", "%" + key + "%").toString();
			}
		} else {
			if (key == null) {
				count = getUniObjHql("SELECT COUNT(1) FROM XfStaff x WHERE x.xfIssuestore.xfStorecode=?", xfStorecode)
						.toString();
			} else {
				count = getUniObjHql(
						"SELECT COUNT(1) FROM XfStaff x WHERE x.xfIssuestore.xfStorecode=? AND x.xfStaffcode like ? OR x.xfName=?",
						xfStorecode, "%" + key + "%", "%" + key + "%").toString();
			}
		}
		return count == null ? 0 : Integer.parseInt(count);
	}

	/**
	 * 查询员工集合
	 * @param pageIndex  页吗
	 * @param pageSize  每页显示的行数
	 * @param xfStorecode  店铺号
	 * @param key 搜索条件
	 */
	@Override
	public List<XfStaff> getXfStaffList(Integer pageIndex, Integer pageSize, String xfStorecode, String key) {
		List<XfStaff> staffs;
		if (xfStorecode == null) {
			if (key == null) {
				staffs=getList(pageIndex,pageSize);
			} else {
				staffs=getList(pageIndex, pageSize, "xfIssuestore.xfStorecode like ? OR xfStaffcode LIKE ? OR xfName LIKE ?", 
						"%" + key + "%", "%" + key + "%", "%" + key + "%");
			}
		} else {
			if (key == null) {
				staffs=getList("xfIssuestore.xfStorecode=?",xfStorecode);
			} else {
				staffs=getList("xfIssuestore.xfStorecode=? AND xfStaffcode LIKE ? OR xfName LIKE ?", xfStorecode,"%" + key + "%", "%" + key + "%");
			}
		}
		return staffs;
	}

	/**
	 * 根据员工编号删除员工信息
	 * @param xfStaffCode 员工编号
	 */
	@Override
	public void deleteXfStaffBySC(String xfStaffCode) {
		delete(xfStaffCode);
	}

	/**
	 * 根据店铺号删除员工
	 * @param xfStorecode 店铺编号
	 */
	@Override
	public void deleteXfStaffBySTCode(String xfStorecode) {
		List<XfStaff> list = getList("xfIssuestore.xfStorecode=?", xfStorecode);
		if (list!=null && list.size()>0) {
			for (XfStaff xfStaff : list) {
				delete(xfStaff);
			}
		}
	}

	@Override
	public List<XfStaff> getListByRoleId(Integer roleId) {
		return getList("staffRole.gwRoleId.id=?", roleId);
	}
}
