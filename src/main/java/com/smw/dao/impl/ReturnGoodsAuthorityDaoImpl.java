package com.smw.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smw.common.util.StringUtil;
import com.smw.dao.ReturnGoodsAuthorityDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.pojo.ReturnGoodsAuthority;

/**
 * 退货权限数据访问层实现接口
 * @author suen
 * @date 2016年7月26日-下午5:27:35
 * @version 1.0
 */
@Repository
public class ReturnGoodsAuthorityDaoImpl extends BaseDaoSupport<ReturnGoodsAuthority> implements ReturnGoodsAuthorityDao {

	/**
	 * 获取退货权限的员工
	 * @param staffCodeOrName 员工号与名称
	 * @return
	 */
	@Override
	public List<ReturnGoodsAuthority> getRGA(String staffCodeOrName) {
		if(StringUtil.isNUllStr(staffCodeOrName)){
			return getList();
		}else{
			return getList("staffcode.xfStaffcode like ? or staffcode.xfName like ?", "%"+staffCodeOrName+"%", "%"+staffCodeOrName+"%");
		}
	}

	/**
	 * 保存或者更新退货权限
	 * @param returnGoodsAuthority 退货权限
	 */
	@Override
	public void saveOrUpdateRGA(ReturnGoodsAuthority returnGoodsAuthority) {
		saveOrUpdate(returnGoodsAuthority);
	}

	/**
	 * 根据员工编号获取退货权限
	 * @param xfStaffCode 员工编号
	 * @return
	 */
	@Override
	public ReturnGoodsAuthority getRGAByXfStaffCode(String xfStaffCode) {
		return getUniObj("FROM ReturnGoodsAuthority r WHERE r.staffcode.xfStaffcode=?", xfStaffCode);
	}

	/**
	 * 删除退货权限
	 * @param rga 退货权限
	 */
	@Override
	public void deleteRGA(ReturnGoodsAuthority rga) {
		delete(rga);
	}

}
