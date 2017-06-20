package com.gws.controller;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.gws.service.impl.synchv61Service;


/**\
 * 
 * @author Administrator
 * 从V61同步数据到POS_Server
 */
@Controller
@RequestMapping("/web")
public class synchfromV61 {
	@Autowired synchv61Service synchv61;
	private Logger logger = LoggerFactory.getLogger(synchfromV61.class);
	@RequestMapping("/fromV61")
	public String synchData(HttpServletRequest request){
		request.getSession().setAttribute("select", "synchv61");
		return "synchv61";
	}
	@RequestMapping(value="/downV61",method=RequestMethod.POST)
	@ResponseBody
	public String downV61(){
		JSONObject json=new JSONObject();
		try {
			logger.info("数据同步开始----------------");
			synchv61.savesynchStore();
			logger.info("同步店铺信息成功----------------");
			synchv61.savesynchStaff();
			logger.info("同步员工信息成功----------------");
			synchv61.savesynchTender();
			logger.info("同步支付方式信息成功----------------");
			synchv61.savesynchItem();
			logger.info("同步商品信息成功----------------");
			json.put("state", 1);
		} catch (Exception e) {
			json.put("state", 0);
			logger.error("同步数据失败:",e);
			e.printStackTrace();
		}
		
		return json.toJSONString();
	}
	
	
}

