package com.smw.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smw.common.util.SetEnum;
import com.smw.common.util.paging.Paging;
import com.smw.pojo.FormatInfo;
import com.smw.service.FormatInfoService;

@Controller
@RequestMapping("web/format")
public class FormatInfoController {

	
	@Resource
	private FormatInfoService formatInfoService;
	
	@Resource
	private JoinMis joinMis;
	
	/**
	 * 列表
	 */
	@RequestMapping("/getFormat")
		public Object getFormat(HttpServletRequest request, Integer pageIndex, Integer pageSize, String key) {
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
				Integer totalCount = formatInfoService.getCount(key);
				List<FormatInfo> buildings = formatInfoService.getFormatInfoList(pageIndex, pageSize, key);
				Paging<FormatInfo> paging=new Paging<>(pageIndex, pageSize, totalCount, buildings);
				request.setAttribute("paging", paging);
				request.getSession().setAttribute("select", "formatInfo");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "format/format";
		}
	
	/**
	 * 获取业态
	 */
	@RequestMapping("/getFormats")
	@ResponseBody
	public Object getFormats() {
		List<FormatInfo> formatList = formatInfoService.getFormatInfoList(null, null, null);
		return formatList;
	}
	
	/**
	 * 同步
	 */
	@RequestMapping("/refreshFormat")
	@ResponseBody
	public Object refreshFormat(HttpServletRequest request){
		//清空表
		formatInfoService.delFormat();
		Map<String, Object > map=new HashMap<>();
		try {
			Map<String, Object> ten= (Map<String, Object>) joinMis.formatInfo();
			if("1".equals(ten.get("code").toString())){
				map.put("flag", true);
				map.put("msg", "更新业态成功");
				return map;
			}
			System.out.println("更新业态成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("flag", false);
		map.put("msg", "更新失败");
		return map;
	}
	
}
