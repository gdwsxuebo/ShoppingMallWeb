package com.smw.dao;

import java.math.BigDecimal;
import java.util.List;

import com.smw.pojo.PromotionStamp;
import com.smw.pojo.temporary.PsStatisticsSubtotal;
import com.smw.pojo.temporary.PsStatisticsTotal;
import com.smw.pojo.temporary.PsStatisticsTotalAll;

/**
 * 促销劵数据访问层接口
 * @author suen
 * @date 2016年6月22日-上午10:59:17
 * @version  jdk1.8
 */
public interface PromotionStampDao {

	/**
	 * 根据促销规则ID统计促销劵数量
	 * @param id 促销规则ID
	 * @return
	 */
	Integer getCountByPRId(BigDecimal id);

	/**
	 * 保存或者更新促销劵
	 * @param promotionStamp 促销劵
	 * @return
	 */
	void saveOrUpdatePS(PromotionStamp promotionStamp);

	/**
	 * 根据促销劵ID查询促销劵信息
	 * @param id 促销劵ID
	 * @return
	 */
	PromotionStamp getPSById(String id);

	/**
	 * 根据条件参数查询促销劵统计信息
	 * @param beginDate 统计起始时间
	 * @param endDate 统计结束日期
	 * @param pss 促销优惠规则
	 * @param issueRanges 发放店铺
	 * @param useRanges 使用店铺
	 * @return
	 */
	List<PsStatisticsTotal> getListByParams(String beginDate, String endDate, String pss, String issueRanges,
			String useRanges);

	List<PsStatisticsTotalAll> getList(Integer pageIndex, Integer pageSize, String key);

	List<PsStatisticsSubtotal> getSubtotalList(Integer pageIndex, Integer pageSize, String key, BigDecimal id);

	Integer getSubtotalCountByPRId(String key, BigDecimal id);

}
