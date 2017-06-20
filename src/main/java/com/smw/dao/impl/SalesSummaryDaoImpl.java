package com.smw.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.smw.common.util.BasePageResultVo;
import com.smw.dao.SalesSummaryDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.dao.BaseDao_suen.Order;
import com.smw.pojo.SalesSummary;

/**
 * 销售汇总数据访问层实现接口
 * 
 * @author suen
 * @date 2016年5月19日-下午3:41:23
 * @version 1.0
 */
@Repository
public class SalesSummaryDaoImpl extends BaseDaoSupport<SalesSummary> implements SalesSummaryDao {
	private Logger log=LoggerFactory.getLogger(SalesSummaryDaoImpl.class);

	@Override
	public List<SalesSummary> selectSalesSummaryByXfStorecodeAndDate(Map<String, Object> map) {
		return getList("isUpload=1 AND storecode.xfStorecode=? AND txdate  BETWEEN ? AND ?",map.get("storecode").toString(),(Date)map.get("bd"),(Date)map.get("ed"));
	}

	/**
	 * 保存销售汇总
	 * 
	 * @param salesSummary
	 *            销售汇总
	 * @return
	 */
	@Override
	public void saveSalesSummary(SalesSummary salesSummary) {
		save(salesSummary);
	}

	/**
	 * 销售记录总数
	 * 
	 * @param storeCode
	 *            店铺号
	 * @param key
	 *            搜索条件
	 * @return
	 */
	@Override
	public Integer getCount(String storeCode, String key) {
		String count;
		if (storeCode == null) {
			if (key == null) {
				count = executeHQL("SELECT COUNT(1) FROM SalesSummary ss WHERE ss.isUpload=1").toString();
			} else {
				count = executeHQL(
						"SELECT COUNT(1) FROM SalesSummary s WHERE s.txdocno like ? OR s.storecode.xfStorecode like ? AND s.isUpload=1",
						"%" + key + "%", "%" + key + "%").toString();
			}
		} else {
			if (key == null) {
				count = executeHQL("SELECT COUNT(1) FROM SalesSummary s WHERE s.isUpload=1 AND s.storecode.xfStorecode=?", storeCode)
						.toString();
			} else {
				count = executeHQL(
						"SELECT COUNT(1) FROM SalesSummary s WHERE s.storecode.xfStorecode=? AND s.txdocno like ? AND s.isUpload=1",
						storeCode, "%" + key + "%").toString();
			}
		}
		return count == null ? 0 : Integer.parseInt(count);
	}

	/**
	 * 获取销售记录集合
	 * 
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            每页显示的行数
	 * @param storeCode
	 *            店铺号
	 * @param key
	 *            搜索条件
	 * @return
	 */
	@Override
	public List<SalesSummary> getSSList(Integer pageIndex, Integer pageSize, String storeCode, String key) {

		List<SalesSummary> salesSummaries;
		if (storeCode == null) {
			if (key == null) {
				salesSummaries=getList(pageIndex,pageSize," 1=1 ",Order.DESC,"txdate DESC,txtime  ");
			} else {
				salesSummaries=getList(pageIndex,pageSize,"isUpload=1 AND txdocno like ? OR storecode.xfStorecode like ? ",Order.DESC," txdate DESC,txtime  ", "%" + key + "%" ,"%" + key + "%");
			}
		} else {
			if (key == null) {
				salesSummaries=getList(pageIndex,pageSize,"isUpload=1 AND storecode.xfStorecode = ? ",Order.DESC," txdate DESC,txtime  ", storeCode);
			} else {
				salesSummaries=getList(pageIndex,pageSize,"isUpload=1 AND storecode.xfStorecode = ? AND txdocno like ? " ,Order.DESC," txdate DESC,txtime  ", storeCode ,"%" + key + "%");
			}
		}
		return salesSummaries;
	
	}
	@Override
	public void updateSalesSummaryState(SalesSummary ss) {
		saveOrUpdate(ss);
	}

	@Override
	public List<SalesSummary> list(Boolean transferEspos, int type){
		if (type==1) {
			return getList("isUpload=1 AND transferEspos=?", transferEspos);
		}else{
			return getList("isUpload=1 AND newTransferEspos=?", transferEspos);
		}
		
	}

	/**
	 * 根据销售单号查询销售信息
	 * @param txdocno 销售单号
	 * @return
	 */
	@Override
	public SalesSummary getSSByTxdocno(String txdocno) {
		return get(txdocno);
	}

	/**
	 * 根据销售单号删除销售数据
	 * @param txdocno 销售单号
	 */
	@Override
	public void deleteSSByTXD(String txdocno) {
		delete(txdocno);
	}

	/**
	 * 根据销售单号查询销售数据
	 * @param txdocno 销售单号
	 * @return
	 */
	@Override
	public SalesSummary getSSByTxd(String txdocno) {
		return get(txdocno);
	}

	/**
	 * 根据原销售单号查询销售信息
	 * @param txdocno 原销售销售单号
	 * @return
	 */
	@Override
	public SalesSummary getSSByOriginalTxdocno(String txdocno) {
		return getUniObj("FROM SalesSummary s WHERE s.originalTxdocno=?", txdocno);
	}

	@Override
	public void saveRefundOrder(String originalTxdocno) {try {
		if("OFFLINE".equals(originalTxdocno) || "return".equals(originalTxdocno)) return;
		SalesSummary sales=	get(originalTxdocno);
		if(sales==null ) throw new RuntimeException("退货失败，原始单号异常");
		if("1".equals(sales.getState())) throw new RuntimeException("退货失败，重复退货");
		sales.setState("1");
		saveOrUpdate(sales);
	} catch (Exception e) {
		log.error("退货失败，原始单号异常:"+originalTxdocno+" "+e.getMessage());
		throw new RuntimeException("退货失败，原始单号异常");
	}
}

	@Override
	public SalesSummary getEnalbleSSByTxd(String txdocno) {
		return getUniObj("FROM SalesSummary s WHERE s.txdocno=? and s.isUpload !='2' ", txdocno);
	}

	/**
	 * 根据日期获取销售总金额
	 */
	public String getMoneyByDate(String date) {
		String money = null;
		String sql ="SELECT sum(netamount) from sales_summary where txdate like '%"+date+"%'";
		Object obj = getSession().createSQLQuery(sql).uniqueResult();
		if(obj == null){
			return money = "0";
		}else{
			return obj.toString();
		}
	}
	
	/**
	 * 清空week_sale_money表
	 */
	public void delWeekSaleMoney(){
		String sql = "DELETE FROM week_sale_money";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.executeUpdate();
	}

	@Override
	public SalesSummary getSummaryBycode(String txdocno) {
		// TODO Auto-generated method stub
		return getUniObj("FROM SalesSummary s WHERE s.txdocno=?", txdocno);
	}
	
	
	
}	
