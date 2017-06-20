package com.smw.dao.impl;

import org.springframework.stereotype.Repository;

import com.smw.dao.WeekSaleMoneyDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.pojo.WeekSaleMoney;

@Repository
public class WeekSaleMoneyDaoImpl extends BaseDaoSupport<WeekSaleMoney> implements WeekSaleMoneyDao {

	/**
	 * 保存
	 */
	public void saveOrUpdateXfStaff(WeekSaleMoney weekSaleMoney) {
		saveOrUpdate(weekSaleMoney);
	}

}
