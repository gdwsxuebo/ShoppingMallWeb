package com.smw.service;

import java.util.List;

import com.smw.pojo.XfTender;

/**
 * 付款方式表服务层接口
 * @author suen
 * @date 2016年5月19日-下午6:04:34
 * @version 1.0
 */
public interface XfTenderService{

	/**
	 * 付款方式集合
	 * @return
	 */
	List<XfTender> selectTenderList();

	/**
	 * 根据付款方式编码获取付款方式
	 * @param tendercode 付款方式编号
	 * @return
	 */
	XfTender getXfTenderByCode(String tendercode);

	/**
	 * 修改或者保存付款方式
	 * @param tenders
	 */
	void saveOrupdateTenderList(List<XfTender> tenders);

	/**
	 * 获取付款方式集合
	 * @param pageSize 每页显示的行数
	 * @param pageIndex 页码
	 * @return
	 */
	List<XfTender> getXfTenderList(Integer pageIndex, Integer pageSize);

	/**
	 * 删除付款方式
	 * @param xfTendercode 付款方式编号
	 */
	void deleteXfTenderByXfTendercode(String xfTendercode);

	/**
	 * 保存或者更新付款方式
	 * @param tender 付款方式对象
	 */
	void saveOrupdateTender(XfTender tender);

	List<XfTender> selectAllTender();
}
