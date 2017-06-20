package com.gws.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gws.pojo.PosCommonConfigModel;
import com.gws.service.impl.PosCommConfigService;
import com.gws.util.commonUtil;
import com.smw.common.util.DateUtil;

/**
 *  收银机公用配置
 */
@Controller
@RequestMapping("/web")
public class PosComConfigController {
	@Autowired 
	PosCommConfigService posComConfigService;
	
	/**
	 * 收银机公用配置
	 */
	@RequestMapping("/getPosComConfig")
	public String getPosComConfig(HttpServletRequest request,Model model){
		PosCommonConfigModel comConfig=posComConfigService.getPosComConfigByConid(commonUtil.ComConfigConid);
		if(comConfig!=null) 	{
			model.addAttribute("config", comConfig);
			request.getSession().setAttribute("select", "posComConfig");
			return "posCommonConfig/posCommonConfig";
		}
		//设置选中菜单项
		request.getSession().setAttribute("select", "posComConfig");
		return "posCommonConfig/posCommonConfig";
	}
	
	/**
	 * 添加收银机公用配置
	 */
	@RequestMapping("/addPosComConfig")
	@ResponseBody
	public Object addPosComConfig(HttpServletRequest request,PosCommonConfigModel comConfig){
		try {
			comConfig.setConid(commonUtil.ComConfigConid);
			comConfig.setUtime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			posComConfigService.savePosComConfig(comConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * 修改收银机公用配置
	 */
	@RequestMapping("/updatePosComConfig")
	@ResponseBody
	public Object updatePosComConfig(HttpServletRequest request,PosCommonConfigModel comConfig){
		try {
			comConfig.setConid(commonUtil.ComConfigConid);
			int result=	posComConfigService.updateposComConfigByConid(comConfig);
			if(result < 0) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
