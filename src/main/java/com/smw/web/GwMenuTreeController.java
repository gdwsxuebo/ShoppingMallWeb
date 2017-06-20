package com.smw.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smw.service.XfMallService;

/**
 * 商场
 * @author suen
 * @date 2016年5月22日-下午3:15:46
 * @version 1.0
 */
@Controller
@RequestMapping("web/gwMenuTree")
public class GwMenuTreeController {
	Logger logger = LoggerFactory.getLogger(GwMenuTreeController.class);
	/**
	 * 商场
	 */
	@Resource
	private XfMallService xfMallService;
	
	
	
}
