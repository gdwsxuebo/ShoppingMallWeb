package com.smw.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smw.dao.PromotionRangeDao;
import com.smw.service.PromotionRangeService;

/**
 * 促销范围实现接口层
 * @author suen
 * @date 2016年6月21日-下午1:59:37
 * @version  jdk1.8
 */
@Service
public class PromotionRangeServiceImpl implements PromotionRangeService {

	@Resource
	private PromotionRangeDao promotionRangeDao; 
	
	/**
	 * 根据ID删除促销范围
	 * @param id 主键ID
	 */
	@Override
	public void deletePRS(String id) {
		promotionRangeDao.deletePRS(id);
	}

}
