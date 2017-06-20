package com.gws.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gws.dao.impl.GwXfStoreCenterDao;
import com.gws.util.StringUtil;

@Service
public class GwXfStoreCenterService {
	@Autowired GwXfStoreCenterDao XfStoreCenterDao;
	
	/**
	 * 给商铺配置中央店铺
	 */
	public void updateStoreCenterStore(String storeid, String stores) {
		Integer re=	XfStoreCenterDao.deleteByStoreCode(storeid);
		if(re==null ||re<0) throw new RuntimeException("更具店铺号删除中央店铺失败");
		String[] storeArr=stores.split(",");
		if(StringUtil.isNull(storeArr[0])) return;
		for(int i=0;i<storeArr.length;i++){
			Integer res=XfStoreCenterDao.insertCenterStore(storeid,storeArr[i]);
			if(res==null ||res<0) throw new RuntimeException("插入店铺和中央店铺失败");
		}
	}
	//中央店铺的数量
	public Object getAllCount() {
		// TODO Auto-generated method stub
		
		return XfStoreCenterDao.getAllCount();
	}
	//非中央店铺的数量
	public Object getNoAllCount() {
		// TODO Auto-generated method stub
		return XfStoreCenterDao.getNoAllCount();
	}
	public List<String> getData() {
		// TODO Auto-generated method stub
		return XfStoreCenterDao.getData();
	}
	public List<String> getCurrentData() {
		// TODO Auto-generated method stub
		return XfStoreCenterDao.getCurrentData();
	}
	public List<Map<String, Object>> getTenderby7day() {
		// TODO Auto-generated method stub
		return XfStoreCenterDao.getTenderby7day();
	}
	public List<Map<String, Object>> getCountScale() {
		return XfStoreCenterDao.getCountScale();
	}
}
