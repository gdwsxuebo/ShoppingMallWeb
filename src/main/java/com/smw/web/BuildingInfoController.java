package com.smw.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smw.common.util.SetEnum;
import com.smw.common.util.paging.Paging;
import com.smw.pojo.BuildingInfo;
import com.smw.service.BuildingInfoService;

@Controller
@RequestMapping("web/building")
public class BuildingInfoController {

	@Resource
	private BuildingInfoService buildingInfoService;

	@Resource
	private JoinMis joinMis;
	
	/**
	 * 楼宇列表
	 */
	@RequestMapping("/getBuilding")
	public Object getBuilding(HttpServletRequest request, Integer pageIndex, Integer pageSize, String key) {
		try {
			if (pageIndex == null) {
				pageIndex = Integer.parseInt(SetEnum.pageIndex.getValue());
			}
			if (pageSize == null) {
				pageSize = Integer.parseInt(SetEnum.pageSize.getValue());
			}
			if (key != null) {
				key= new String(key.getBytes("iso-8859-1"),"utf-8");
				key = key.trim();
				request.setAttribute("key", key);
			}
			Integer totalCount = buildingInfoService.getCount(key);
			List<BuildingInfo> buildings = buildingInfoService.getBuildingList(pageIndex, pageSize, key);
			Paging<BuildingInfo> paging=new Paging<>(pageIndex, pageSize, totalCount, buildings);
			request.setAttribute("paging", paging);
			request.getSession().setAttribute("select", "buildingInfo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "building/building";
	}
	
	/**
	 *获取楼宇
	 */
	@RequestMapping("/getBuildings")
	@ResponseBody
	public Object getBuildings() {
		List<BuildingInfo> buildingList = buildingInfoService.getBuildingList(null, null, null);
		return buildingList;
	}
	
	/**
	 * 同步
	 */
	@RequestMapping("/refreshBuilding")
	@ResponseBody
	public Object refreshBuilding(HttpServletRequest request){
		Map<String, Object > map=new HashMap<>();
		try {
			//先清空表
			buildingInfoService.delBuilding();
			Map<String, Object> ten= (Map<String, Object>) joinMis.buildingInfo();
			if("1".equals(ten.get("code").toString())){
				map.put("flag", true);
				map.put("msg", "更新楼宇成功");
				return map;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("flag", false);
		map.put("msg", "更新失败");
		return map;
	}
	

}
