package com.smw.service;


import org.hibernate.Session;

import com.smw.pojo.XfMall;

/**
 * 商场服务层接口
 * @author suen
 * @date 2016年5月19日-下午5:13:09
 * @version 1.0
 */
public interface XfMallService{

	/**
	 * 查询商场
	 * @return
	 */
	XfMall selectMall();

	/**
	 * 根据商场编号查询商场信息
	 * @param xfMallid 商场编号
	 * @return
	 */
	XfMall selectByStrId(String xfMallid);

	/**
	 * 修改或者保存商场
	 * @param xm
	 */
	void saveOrupdateXfMall(XfMall xm);
	
	/**
	 * 获取唯一的商场
	 * @return
	 */
	public XfMall uniqueResult();
}
