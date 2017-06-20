package com.gws.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gws.dao.impl.PaymentRentDao;
import com.gws.pojo.PaymentRent;

@Service
public class PaymentRentService {

	@Resource
	private PaymentRentDao paymentRentDao;
	
	/**保存**/
	public void savePaymentRent(PaymentRent paymentRent){
		paymentRentDao.savePaymentRent(paymentRent);
	}
	
	/**列表**/
	public List<PaymentRent> getPaymentRentList(){
		return paymentRentDao.getPaymentRentList();
	}
	/**列表**/
	public List<Map<String, Object>> getPaymentRentList(int page, int rows){
		return	paymentRentDao.getPaymentRentList(page,rows);
	};
	
	/**清空表**/
	public void deleteAll(){
		paymentRentDao.deleteAll();
	}
	
	/**总记录数**/
	public int getCount(){
		return paymentRentDao.queryCount();
	}
	
}
