package com.smw.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.smw.common.util.BasePageResultVo;
import com.smw.dao.XfTenderDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.pojo.XfTender;
/**
 * 付款方式数据访问层实现接口
 * @author suen
 * @date 2016年5月19日-下午6:06:55
 * @version 1.0
 */
@Repository
public class XfTenderDaoImpl extends BaseDaoSupport<XfTender> implements XfTenderDao {

	/**
	 * 付款方式集合
	 * @return
	 */
	@Override
	public List<XfTender> selectTenderList() {
		return getList(" isInvalid='1' ");
	}

	/**
	 * 根据付款方式编码获取付款方式
	 * @param tendercode 付款方式编号
	 * @return
	 */
	@Override
	public XfTender getXfTenderByCode(String tendercode) {
		return getUniObj("FROM XfTender x WHERE x.xfTendercode=?", tendercode);
	}

	@Override
	public void saveOrupdatesaveOrupdateTenderList(List<XfTender> tenders) {
		for (XfTender xfTender : tenders) {
			saveOrUpdate(xfTender);
		}
	}

	/**
	 * 获取付款方式集合
	 * @param pageSize 每页显示的行数
	 * @param pageIndex 页码
	 * @return
	 */
	@Override
	public List<XfTender> getXfTenderList(Integer pageIndex,Integer pageSize) {
		return getList(pageIndex, pageSize," isInvalid='1' ");
	}

	/**
	 * 删除付款方式
	 * @param xfTendercode 付款方式编号
	 */
	@Override
	public void deleteXfTenderByXfTendercode(String xfTendercode) {
		delete(xfTendercode);
	}

	/**
	 * 保存或者更新付款方式
	 * @param tender 付款方式对象
	 */
	@Override
	public void saveOrupdateTender(XfTender tender) {
		saveOrUpdate(tender);
	}

	@Override
	public List<XfTender> selectAllTender() {
		return getList();
	}
}
