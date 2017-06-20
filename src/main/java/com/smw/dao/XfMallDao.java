package com.smw.dao;

import com.smw.pojo.XfMall;

/**
 * 商场信息表数据访问层接口
 * @author suen
 * @date 2016年5月18日-下午4:55:24
 * @version 1.0
 */
public interface XfMallDao{

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

	void saveOrupdateXfMall(XfMall xm);

	XfMall uniqueResult();
    
}