package com.smw.service.impl;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smw.common.util.DateUtil;
import com.smw.common.util.PropertiesUtil;
import com.smw.common.util.ResponseCode;
import com.smw.common.util.StringUtil;
import com.smw.dao.Old_SalesSummaryDao;
import com.smw.dao.SalesSummaryDao;
import com.smw.pojo.PromotionStamp;
import com.smw.pojo.SalesItem;
import com.smw.pojo.SalesSummary;
import com.smw.pojo.SalesTender;
import com.smw.pojo.oldSalesSummary.OldSalesSummary;
import com.smw.service.Old_SalesSummaryService;
import com.smw.service.PromotionStampService;
import com.smw.service.SalesItemService;
import com.smw.service.SalesSummaryService;
import com.smw.service.SalesTenderService;
import com.smw.service.XfItemService;
import com.smw.service.XfMallService;
import com.smw.service.XfStaffService;
import com.smw.service.XfStoreService;
import com.smw.service.XfTenderService;
import com.smw.utils.MD5;
import com.smw.web.JoinOLDMis;

/**
 * 老销售汇总服务层实现接口
 * 
 * @author suen
 * @date 2016年5月19日-下午3:39:14
 * @version 1.0
 */
@Service
public class Old_SalesSummaryServiceImpl implements Old_SalesSummaryService {
	
	@Resource
	private Old_SalesSummaryDao old_SalesSummaryDao;

	/**
	 * 保存就销售数据
	 * @param oldSalesSummary 旧销售
	 */
	@Override
	public void saveOrUpdateOSS(OldSalesSummary oldSalesSummary) {
		old_SalesSummaryDao.saveOrUpdateOSS(oldSalesSummary);
	}

	/**
	 * 获取老的销售数据
	 * @param txdocno 销售单号
	 * @return
	 */
	@Override
	public OldSalesSummary getOSSByTxdocno(String txdocno) {
		return old_SalesSummaryDao.getOSSByTxdocno(txdocno);
	}
	
}
