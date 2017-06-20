package com.smw.dao;

import java.util.List;
import java.util.Map;

import com.smw.pojo.SalesSummary;

/**
 * 销售汇总表数据访问层接口
 * @author suen
 * @date 2016年5月18日-下午5:17:11
 * @version 1.0
 */
public interface SalesSummaryDao{

	/**
	 * 根据店铺编号和指定日期范围查询销售记录
	 * @param map 条件参数集合
	 * @return
	 */
	List<SalesSummary> selectSalesSummaryByXfStorecodeAndDate(Map<String, Object> map);

	/**
	 * 保存销售汇总
	 * @param salesSummary 销售汇总
	 * @return
	 */
	void saveSalesSummary(SalesSummary salesSummary);

	/**
	 * 销售记录总数
	 * @param storeCode 店铺号
	 * @param key 搜索条件
	 * @return
	 */
	Integer getCount(String storeCode, String key);

	/**
	 * 获取销售记录集合
	 * @param pageIndex 页码
	 * @param pageSize 每页显示的行数
	 * @param storeCode 店铺号
	 * @param key 搜索条件
	 * @return
	 */
	List<SalesSummary> getSSList(Integer pageIndex, Integer pageSize, String storeCode, String key);
	/**
	 * 获取所有销售列表
	 * @param type 类型 1老MIS 2新MIS
	 * @return
	 */
	List<SalesSummary> list(Boolean transferEspos, int type);

	void updateSalesSummaryState(SalesSummary ss);

	/**
	 * 根据销售单号查询销售信息
	 * @param txdocno 销售单号
	 * @return
	 */
	SalesSummary getSSByTxdocno(String txdocno);

	/**
	 * 根据销售单号删除销售数据
	 * @param txdocno 销售单号
	 */
	void deleteSSByTXD(String txdocno);

	/**
	 * 根据销售单号查询销售数据
	 * @param txdocno 销售单号
	 * @return
	 */
	SalesSummary getSSByTxd(String txdocno);

	/**
	 * 根据原销售单号查询销售信息
	 * @param txdocno 原销售销售单号
	 * @return
	 */
	SalesSummary getSSByOriginalTxdocno(String txdocno);

	void saveRefundOrder(String originalTxdocno);

	SalesSummary getEnalbleSSByTxd(String txdocno);
	
	/**
	 * 根据日期获取销售总金额
	 */
	String getMoneyByDate(String date);
	
	/**
	 * 清空week_sale_money表
	 */
	public void delWeekSaleMoney();

	SalesSummary getSummaryBycode(String txdocno);
	
}