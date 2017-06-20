package com.smw.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smw.dao.XfMallDao;
import com.smw.pojo.XfMall;
import com.smw.service.XfMallService;

/**
 * 商场服务层实现接口
 * @author suen
 * @date 2016年5月19日-下午5:13:38
 * @version 1.0
 */
@Service
public class XfMallServiceImpl implements XfMallService {

	@Resource
	private XfMallDao xfMallDao;
	
	/**
	 * 查询商场
	 * @return
	 */
	@Override
	public XfMall selectMall() {
		return xfMallDao.selectMall();
	}

	/**
	 * 根据商场编号查询商场信息
	 * @param xfMallid 商场编号
	 * @return
	 */
	@Override
	public XfMall selectByStrId(String xfMallid) {
		return xfMallDao.selectByStrId(xfMallid);
	}

	/**
	 * 修改或者保存商场
	 * @param xm
	 */
	public void saveOrupdateXfMall(XfMall xm){
		xfMallDao.saveOrupdateXfMall(xm);
	}

	/**
	 * 获取唯一的商场
	 * @return
	 */
	@Override
	public XfMall uniqueResult() {
		return xfMallDao.uniqueResult();
	}
}
