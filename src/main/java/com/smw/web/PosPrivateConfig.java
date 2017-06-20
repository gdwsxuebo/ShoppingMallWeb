package com.smw.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smw.common.util.BaseResultVo;
import com.smw.common.util.DateUtil;
import com.smw.common.util.SetEnum;
import com.smw.common.util.StringUtil;
import com.smw.common.util.paging.Paging;
import com.smw.pojo.GwRole;
import com.smw.pojo.PosConfigModel;
import com.smw.pojo.StoreAuth;
import com.smw.service.PosPrivateConfigService;
import com.smw.service.StoreAuthService;

@Controller
@RequestMapping(value="web/posPrivateConfig")
public class PosPrivateConfig {

	@Resource
	private PosPrivateConfigService posPrivateConfigService;
	
	
	@Resource
	private StoreAuthService storeAuthService;
	/**
	 * 获取收银机私有配置列表
	 * @param request
	 * @param pageIndex 页码
	 * @param pageSize 每页显示的条数
	 * @param key 搜索关键字
	 * @return
	 */
	@RequestMapping("/getPosPrivateConfig")
	public Object getPosPrivateConfigList(HttpServletRequest request,Integer pageIndex,Integer pageSize,String key){
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
		        request.setAttribute("key", key);
	        }
			//私有配置总数
			Integer totalCount=0;
			//私有配置集合
			List<PosConfigModel> posConfigs;
			totalCount = posPrivateConfigService.getCount(key);
			posConfigs = posPrivateConfigService.getPosPrivateConfigList(pageIndex, pageSize, key);
			Paging<PosConfigModel> paging=new Paging<>(pageIndex, pageSize, totalCount, posConfigs);
			request.getSession().setAttribute("paging", paging);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("select", "posConfig");
		return "posPrivateConfig/posConfigList";
	}
	
	/**
	 * 
	 * 添加或修改
	 * @param request
	 * @param pageIndex
	 * @param pageSize
	 * @param key
	 * @param posConfigModel 当不含id 为添加  含id 为修改
	 * @return
	 */
	@RequestMapping("/addOrUpdatePosPrivateConfig")
	public Object addOrUpdatePosPrivateConfig(HttpServletRequest request, Integer pageIndex, Integer pageSize, String key,PosConfigModel posConfigModel,String id){
		try {
			
			
			String tillid = null;  //收银机编号
			//通过tillid获取 v61tillid 和   店铺code
			tillid=posConfigModel.getTillid();
			StoreAuth storeAuth =storeAuthService.getStoreAuthByHql(tillid);
			posConfigModel.setStoreid(storeAuth.getStoreCode());
			posConfigModel.setV61tillid(storeAuth.getV61tillid());
			
			String time = DateUtil.getCurrentDatetime("-");
			posConfigModel.setUtime(time);
			
			posPrivateConfigService.addOrUpdatePosPrivateConfig(posConfigModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/web/posPrivateConfig/getPosPrivateConfig";
//		return getPosPrivateConfigList(request,pageIndex,pageSize,key);
	}
	
	
	/**
	 * ajax 获得收银机对应的v61 和商铺信息
	 * 
	 * @return
	 */
	@RequestMapping("getTillids")
	@ResponseBody
	public Object getTillids() {
		
		List<String> Tillids =storeAuthService.getStoreAuthsByHql();
		
		
		
		return Tillids;
	}
	
	/**
	 * 根据id获得配置信息
	 * @param id
	 * @return
	 */
	@RequestMapping("getPosPrivateConfigById")
	@ResponseBody
	public Object getPosPrivateConfigById(HttpServletRequest request,Integer id, Model model) {
		PosConfigModel posConfigModel = posPrivateConfigService.getPosPrivateConfigById(id);
		return posConfigModel;
	}
	/**
	 * 根据id删除对应配置信息
	 * @param id 配置id
	 * @return
	 */
	@RequestMapping("deletePosPrivateConfigByid")
	@ResponseBody
	public Object deleteXfStaffByCode(Integer id) {
		// 删除
		posPrivateConfigService.deletePosPrivateConfigByid(id.toString());
		return true;
	}
}
