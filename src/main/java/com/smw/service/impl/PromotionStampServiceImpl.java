package com.smw.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smw.dao.PromotionStampDao;
import com.smw.pojo.PromotionStamp;
import com.smw.pojo.temporary.PsStatisticsSubtotal;
import com.smw.pojo.temporary.PsStatisticsTotal;
import com.smw.pojo.temporary.PsStatisticsTotalAll;
import com.smw.service.PromotionStampService;

/**
 * 促销劵服务层实现接口
 * @author suen
 * @date 2016年6月22日-上午10:58:30
 * @version  jdk1.8
 */
@Service
public class PromotionStampServiceImpl implements PromotionStampService {
	@Resource
	private PromotionStampDao promotionStampDao;

	/**
	 * 根据促销规则ID统计促销劵数量
	 * @param id 促销规则ID
	 * @return
	 */
	@Override
	public Integer getCountByPRId(BigDecimal id) {
		synchronized (this) {
			return promotionStampDao.getCountByPRId(id);
		}
	}

	/**
	 * 保存或者更新促销劵
	 * @param promotionStamp 促销劵
	 * @return
	 */
	@Override
	public void saveOrUpdatePS(PromotionStamp promotionStamp) {
		promotionStampDao.saveOrUpdatePS(promotionStamp);
	}

	/**
	 * 根据促销劵ID查询促销劵信息
	 * @param id 促销劵ID
	 * @return
	 */
	@Override
	public PromotionStamp getPSById(String id) {
		return promotionStampDao.getPSById(id);
	}

	/**
	 * 根据条件参数查询促销劵统计信息
	 * @param beginDate 统计起始时间
	 * @param endDate 统计结束日期
	 * @param pss 促销优惠规则
	 * @param issueRanges 发放店铺
	 * @param useRanges 使用店铺
	 * @return
	 */
	@Override
	public List<PsStatisticsTotal> getListByParams(String beginDate, String endDate, String pss, String issueRanges,
			String useRanges) {
		return promotionStampDao.getListByParams(beginDate,endDate,pss,issueRanges,useRanges);
	}

	@Override
	public List<PsStatisticsTotalAll> getList(Integer pageIndex, Integer pageSize, String key) {
		return promotionStampDao.getList(pageIndex, pageSize,key);
	}

	@Override
	public List<PsStatisticsSubtotal> getSubtotalList(Integer pageIndex, Integer pageSize, String key, BigDecimal id) {
		return promotionStampDao.getSubtotalList(pageIndex, pageSize,key,id);
	}

	@Override
	public Integer getSubtotalCountByPRId(String key, BigDecimal id) {
		return promotionStampDao.getSubtotalCountByPRId(key,id);
	}
}
