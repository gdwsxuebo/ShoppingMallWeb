package com.smw.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smw.dao.PromotionRangeDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.pojo.PromotionRange;
import com.smw.pojo.PromotionRule;

/**
 * 促销范围数据访问实现接口层
 * @author suen
 * @date 2016年6月21日-下午2:03:23
 * @version  jdk1.8
 */
@Repository
public class PromotionRangeDaoImpl extends BaseDaoSupport<PromotionRange> implements PromotionRangeDao {

	/**
	 * 根据ID删除促销范围
	 * @param id 主键ID
	 */
	@Override
	public void deletePRS(String id) {
		delete(new BigDecimal(id));
	}

	/**
	 * 根据促销规则查询发放店铺
	 */
	public List<PromotionRange> getPRSByPRUId(BigDecimal id){
		return getList("promotionRule.id=?", id);
	}

	

	public Long getcountById(BigDecimal prId) {
		// TODO Auto-generated method stub
		return getCount("promotionRule.id=?", prId);
	}

	public List<PromotionRange> getPRSByPRUId(Integer pageIndex, Integer pageSize, BigDecimal id) {
		// TODO Auto-generated method stub
		return getList(pageIndex,pageSize,"promotionRule.id=? ", id);
	}

	public List<PromotionRange> getPRSByPRUId(Integer pageIndex, Integer pageSize, String key, BigDecimal id) {
		// TODO Auto-generated method stub
		List<PromotionRange> list;
		if (key==null) {
			list = getList(pageIndex, pageSize,"promotionRule.id=?", id);
		}else{
			list=getList(pageIndex, pageSize, " xfStore.xfName like ? and promotionRule.id=?", "%"+key+"%",id);
		}
	
		return list;
	}
}
