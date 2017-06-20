package com.smw.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.smw.common.util.BasePageResultVo;
import com.smw.dao.XfMallDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.pojo.XfMall;

/**
 * 商场数据访问层实现接口
 * @author suen
 * @date 2016年5月19日-下午5:15:03
 * @version 1.0
 */
@Repository
public class XfMallDaoImpl extends BaseDaoSupport<XfMall> implements XfMallDao {

	/**
	 * 查询商场
	 * @return
	 */
	@Override
	public XfMall selectMall() {
		List<XfMall> list = getList();
		return list!=null && list.size()>0?list.get(0):null;
	}

	/**
	 * 根据商场编号查询商场信息
	 * @param xfMallid 商场编号
	 * @return
	 */
	@Override
	public XfMall selectByStrId(String xfMallid) {
		return getUniObj("FROM XfMall x WHERE x.xfMallid=?", xfMallid);
	}

	@Override
	public void saveOrupdateXfMall(XfMall xm) {
		saveOrUpdate(xm);
	}

	@Override
	public XfMall uniqueResult() {
		Session session = getSession();
		return  (XfMall) session.createQuery("from XfMall").uniqueResult();
	}
}
