package com.smw.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smw.dao.PromotionUseRangeDao;
import com.smw.service.PromotionUseRangeService;

/**
 * 促销使用范围实现接口层
 * @author suen
 * @date 2016年6月21日-下午2:06:07
 * @version  jdk1.8
 */
@Service
public class PromotionUseRangeServiceImpl implements PromotionUseRangeService {

	@Resource
	private PromotionUseRangeDao promotionUseRangeDao;
	
	/**
	 * 根据ID删除促销使用范围
	 * @param id 主键ID
	 */
	@Override
	public void deletePUR(String id) {
		promotionUseRangeDao.deletePUR(id);
	}

}
