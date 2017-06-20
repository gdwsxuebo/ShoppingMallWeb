package com.smw.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.smw.common.util.DateUtil;
import com.smw.common.util.SetEnum;
import com.smw.common.util.paging.Paging;
import com.smw.pojo.XfStaff;
import com.smw.pojo.XfTillidState;
import com.smw.service.XfTillidStateService;

/**
 * 收银机
 * @author suen
 * @date 2016年5月26日-下午4:52:38
 * @version 1.0
 */
@Controller
@RequestMapping("web/xfTillid")
public class Tillid {
	Logger logger = LoggerFactory.getLogger(Tillid.class);
	
	@Resource
	private XfTillidStateService sfTillidStateService;
	
	@RequestMapping("getTillid")
	public String getTillid(HttpServletRequest request){
		request.getSession().setAttribute("select", "tillid");
		return "/tillid";
	}
	/**
	 * 获取收银机
	 * @param request
	 * @param pageIndex 页码
	 * @param pageSize 每页显示的行数
	 * @return
	 */
	@RequestMapping("getTillidData")
	@ResponseBody
	public Object getTender(HttpServletRequest request,Integer pageIndex,Integer pageSize,String key){
		JSONObject result=new JSONObject();
		try {
			if (pageIndex == null) {
				pageIndex = Integer.parseInt(SetEnum.pageIndex.getValue());
			}
			if (pageSize==null){
	            pageSize=Integer.parseInt(SetEnum.pageSize.getValue());
	        }
			if (key!=null) {
				key= new String(key.getBytes("iso-8859-1"),"utf-8");
		        key=key.trim();
	        }
			//商品总数
			Integer totalCount=0;int onlineCount=0;int outlineCount=0;
			//商品集合
			List<XfTillidState> xfts;
			//员工
			XfStaff xfStaff=(XfStaff)request.getSession().getAttribute("XfStaff");
			//权限名称
			String authority = xfStaff.getStaffRole().getAuthority();
			if ("ROLE_MALL_ADMIN".equals(authority)) {
				//获取总数
				totalCount = sfTillidStateService.getCount(null, key);
				//获取集合
				xfts=sfTillidStateService.getXfTillidList(pageIndex,totalCount,null,key);
			}else if("ROLE_STORE_ADMIN".equals(authority)){
				//获取总数
				totalCount = sfTillidStateService.getCount(xfStaff.getXfIssuestore().getXfStorecode(), key);
				//获取集合
				xfts=sfTillidStateService.getXfTillidList(pageIndex,totalCount,xfStaff.getXfIssuestore().getXfStorecode(),key);
			}else{
				xfts=new ArrayList<>();
			}
			for (XfTillidState xfTillidState : xfts) {
				//判断收银机是否在线。
				/*if(DateUtil.getTilidState(xfTillidState.getVisitTime())){
					xfTillidState.setState(1);onlineCount++;
				} */
				if(xfTillidState.getOnlineType().equals("1")){
					xfTillidState.setState(1);onlineCount++;
				}
				else {
					xfTillidState.setState(0);
					outlineCount++;
				}
			}
			result.put("code", 10000);
			result.put("onlineCount", onlineCount);
			result.put("outlineCount", outlineCount);
			result.put("rows", xfts);
		} catch (Exception e) {
			result.put("code", 10001);
			e.printStackTrace();
			logger.error("获取收银机"+e.getMessage());
		}

		
		return result;
	}
}
