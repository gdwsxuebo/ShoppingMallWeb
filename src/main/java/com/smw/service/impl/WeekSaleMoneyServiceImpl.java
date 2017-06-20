package com.smw.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smw.dao.WeekSaleMoneyDao;
import com.smw.pojo.WeekSaleMoney;
import com.smw.service.WeekSaleMoneyService;

@Service
public class WeekSaleMoneyServiceImpl implements WeekSaleMoneyService{
	
	@Resource
	private WeekSaleMoneyDao weekSaleMoneyDao;
	
	/**
	 *  保存
	 */
	public void saveOrUpdateXfStaff(WeekSaleMoney weekSaleMoney) {
		weekSaleMoneyDao.saveOrUpdateXfStaff(weekSaleMoney);
	}

}
