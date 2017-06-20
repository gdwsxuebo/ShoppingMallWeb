package com.gws.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gws.service.impl.GwXfStoreCenterService;
import com.gws.service.impl.GwXfStoreService;
import com.smw.common.util.BaseResultVo;
import com.smw.common.util.Status;
import com.smw.pojo.XfStore;

/**
 * 
 * 配置中央店铺的控制层
 *
 */
@Controller
@RequestMapping("/web")
public class GwStoreController {
	private Logger log = LoggerFactory.getLogger(GwStoreController.class);
	
	@Autowired
	GwXfStoreService gwXfStoreService;
	@Autowired
	GwXfStoreCenterService gwXfStoreCenterService;

	/**
	 * 获取除了中央店铺的其他店铺
	 */
	@RequestMapping("/getAllStoreCenterStore")
	@ResponseBody
	public BaseResultVo getAllStoreCenterStore(String storecode) {
		List<Map<String, Object>> storeCenterStores = gwXfStoreService.queryStoreCenterStore(storecode);
		BaseResultVo res = new BaseResultVo();
		res.setData(storeCenterStores);
		res.setStatus(new Status().setCode("10000").setMsg("获取店铺和中央店铺的关系成功"));
		return res;
	}

	/**
	 * 给商铺配置中央店铺
	 */
	@RequestMapping("/updateStoreCenterStore")
	@ResponseBody
	public BaseResultVo updateStoreCenterStore(String storeid, String stores) {
		try {
			gwXfStoreCenterService.updateStoreCenterStore(storeid, stores);
			return BaseResultVo.responseSuccess("修改中央店铺信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("修改中央店铺信息失败", e);
			return BaseResultVo.responseFail("修改中央店铺信息失败");
		}
	}
	
	/**
	 * 配置中央店铺时  搜索功能
	 */
	@RequestMapping("/getStoreByName")
	@ResponseBody
	public Object getStoreByName(HttpServletRequest request, String storeName,String storecode){
		List<Map<String, Object>> storeList = gwXfStoreService.getStoreByName(storeName,storecode);
		return storeList;
	}
	
	
	
}
