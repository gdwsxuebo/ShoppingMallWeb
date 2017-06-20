package com.smw.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smw.dao.XfTenderDao;
import com.smw.pojo.XfTender;
import com.smw.service.XfTenderService;

/**
 * 付款方式服务层实现接口
 * @author suen
 * @date 2016年5月19日-下午6:05:27
 * @version 1.0
 */
@Service
public class XfTenderServiceImpl implements XfTenderService {

	@Resource
	private XfTenderDao xfTenderDao;
	
	/**
	 * 付款方式集合
	 * @return
	 */
	@Override
	public List<XfTender> selectTenderList() {
		return xfTenderDao.selectTenderList();
	}

	/**
	 * 根据付款方式编码获取付款方式
	 * @param tendercode 付款方式编号
	 * @return
	 */
	@Override
	public XfTender getXfTenderByCode(String tendercode) {
		return xfTenderDao.getXfTenderByCode(tendercode);
	}

	/**
	 * 修改或者保存付款方式
	 * @param tenders
	 */
	@Override
	public void saveOrupdateTenderList(List<XfTender> tenders) {
		xfTenderDao.saveOrupdatesaveOrupdateTenderList(tenders);
		
	}

	/**
	 * 获取付款方式集合
	 * @param pageSize 每页显示的行数
	 * @param pageIndex 页码
	 * @return
	 */
	@Override
	public List<XfTender> getXfTenderList(Integer pageIndex,Integer pageSize) {
		return xfTenderDao.getXfTenderList(pageIndex,pageSize);
	}

	/**
	 * 删除付款方式
	 * @param xfTendercode 付款方式编号
	 */
	@Override
	public void deleteXfTenderByXfTendercode(String xfTendercode) {
		xfTenderDao.deleteXfTenderByXfTendercode(xfTendercode);
	}

	/**
	 * 保存或者更新付款方式
	 * @param tender 付款方式对象
	 */
	@Override
	public void saveOrupdateTender(XfTender tender) {
		xfTenderDao.saveOrupdateTender(tender);
	}

	@Override
	public List<XfTender> selectAllTender() {

		return xfTenderDao.selectAllTender();
	}
}
