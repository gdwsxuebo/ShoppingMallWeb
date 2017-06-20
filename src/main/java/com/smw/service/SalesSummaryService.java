package com.smw.service;

import java.util.List;
import java.util.Map;


import com.smw.pojo.SalesSummary;
import com.smw.pojo.XfItem;

/**
 * 销售汇总表服务层接口
 * @author suen
 * @date 2016年5月19日-下午3:38:31
 * @version 1.0
 */
public interface SalesSummaryService{

	/**
	 * 根据店铺编号和指定日期范围查询销售记录
	 * @param map 条件参数集合
	 * @return
	 */
	List<SalesSummary> selectSalesSummaryByXfStorecodeAndDate(Map<String, Object> map);

	/**
	 * 保持销售记录
	 * @param salesItemService 销售单货品明细服务层
	 * @param salesTenderService 销售单付款服务层
	 * @param promotionStampService  促销劵
	 * @param data 数据
	 * @return
	 */
	Map saveSalesSummary(XfMallService xfMallService,XfStoreService xfStoreService,XfStaffService xfStaffService,XfItemService xfItemService,XfTenderService xfTenderService,SalesItemService salesItemService, SalesTenderService salesTenderService, PromotionStampService promotionStampService, String data);

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
	List<SalesSummary> getSSList(Integer pageIndex, Integer pageSize, String  storeCode, String key);

	/**
	 * 
	 * 获取销售记录列表
	 * @param transferEspos
	 * @param type 类型 1老MIS字段 2新MIS
	 * @return
	 */
	List<SalesSummary> list(Boolean transferEspos, int type);

	/**
	 * 保存或者修改销售记录
	 * 
	 * @param ss
	 */
	void updateSalesSummaryState(SalesSummary ss);

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

	SalesSummary getEnalbleSSByTxd(String trim);
	
	/**
	 * 根据日期获取销售总金额
	 */
	String getMoneyByDate(String date);
	
	/**
	 * 清空week_sale_money表
	 */
	public void delWeekSaleMoney();
	
	
	void saveSaleSummary(SalesSummary salesSummary);

	SalesSummary getSummaryBycode(String txdocno);

	
}
