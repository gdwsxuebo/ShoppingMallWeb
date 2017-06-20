package com.smw.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gws.service.impl.GwXfStoreService;
import com.smw.common.util.DateUtil;
import com.smw.common.util.PropertiesUtil;
import com.smw.common.util.SetEnum;
import com.smw.common.util.StringUtil;
import com.smw.common.util.paging.Paging;
import com.smw.pojo.Sets;
import com.smw.pojo.StoreAuth;
import com.smw.pojo.TillidToStore;
import com.smw.pojo.XfStore;
import com.smw.service.SetService;
import com.smw.service.StoreAuthService;
import com.smw.service.XfStoreService;

@Controller
@RequestMapping(value = "web/storeAuth")
public class StoreAuthController {
	Logger logger = LoggerFactory.getLogger(StoreAuthController.class);
	@Resource
	private StoreAuthService storeAuthService;

	@Resource
	private GwXfStoreService storeService;
	@Autowired SetService setService;
	/**
	 * 新MIS
	 */
	@Resource
	private JoinMis joinMis;
	/**
	 * 收银机权限列表
	 * 
	 * @param pageIndex  页码
	 * @param pageSize 每页显示条数
	 * @param key  搜索关键字
	 */
	@RequestMapping("/getStore")
	public Object getStoreAuth(HttpServletRequest request, Integer pageIndex, Integer pageSize, String key) {
		PropertiesUtil pUtil = new PropertiesUtil("systemConfig.properties");
		request.setAttribute("misorv61", pUtil.readProperty("gdws.ifs.misorv61"));
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
			// 权限总数
			Integer totalCount = 0;
			// 权限集合
			List<StoreAuth> storeAuths;
			totalCount = storeAuthService.getCount(key);
			storeAuths = storeAuthService.getStoreAuthList(pageIndex, pageSize, key);
			Paging<StoreAuth> paging = new Paging<>(pageIndex, pageSize, totalCount, storeAuths);
			request.getSession().setAttribute("paging", paging);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("select", "storeAuth");
		return "storeAuth/storeAuthList";
	}

	/**
	 * 获取店铺
	 */
	 @RequestMapping("/getStoreList")
	 @ResponseBody
	public Object getStoreList() {
		List<XfStore> storeList = storeService.getStoreList();
		return storeList;
	}
	 
	 /**
		 * 店铺
		 */
		@Resource
		private XfStoreService xfStoreService;
	 
	 /**
	  * 添加或修改
	  */
	 @RequestMapping("/addOrUpdateStoreAuth")
	 public Object addOrUpdateStoreAuth(HttpServletRequest request, StoreAuth storeAuth,Integer pageIndex, Integer pageSize, String key,String issueRanges,String tillidandxfstore ){
		 PropertiesUtil pUtil = new PropertiesUtil("systemConfig.properties");
		request.setAttribute("misorv61", pUtil.readProperty("gdws.ifs.misorv61"));
		 try {
			 if(!pUtil.readProperty("gdws.ifs.misorv61").equals("MIS")){
				 String[] xfStoreCode = issueRanges.split(",");
				 storeAuthService.deleteTillidToStoreByTillid(storeAuth.getTillid());
				 for(int i=0;i<xfStoreCode.length;i++){
					 TillidToStore tillidToStore = new TillidToStore();
					 tillidToStore.setId(UUID.randomUUID().toString());
					 tillidToStore.setTillid(storeAuth.getTillid());
					 if(xfStoreCode[i].length()>1){
						 tillidToStore.setXfStoreCode(xfStoreCode[i]); 
					 }else{
						 
						 tillidToStore.setXfStoreCode(storeAuth.getStoreCode()); 
					 }
					 
					 storeAuthService.addOrUpdateTillidToStore(tillidToStore);
				 }  
			 }
				 if(storeAuth.getId()==null){
					 storeAuth.setId(storeAuth.getTillid());
				 }
				 
				 if(!StringUtil.isNUllStr(storeAuth.getStoreCode())){
					 XfStore xfStore = xfStoreService.getXfStoreByStoreCode(storeAuth.getStoreCode());
					 if(xfStore.getXfCenter()==false){
						 storeAuth.setType("1");
					 }else{
						 storeAuth.setType("2");
					 } 
				 }

				 String time = DateUtil.getCurrentDatetime("-");
				 storeAuth.setCtime(time);
				 storeAuth.setStoreName(new String(storeAuth.getStoreName().getBytes("iso-8859-1"),"utf-8"));
				 storeAuth.setIssueRanges(new String(tillidandxfstore.getBytes("iso-8859-1"),"utf-8"));
				 storeAuthService.addOrUpdateStoreAuth(storeAuth);
			 

			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return "redirect:/web/storeAuth/getStore";
	 }
	 
	 /**
	  * 通过收银机编号获取到收银机的权限
	  * @param tillid	收银机编号
	  */
	 @RequestMapping("/getStoreAuthByTillid")
	 @ResponseBody
	 public Object getStoreAuthByTillid(HttpServletRequest request,String tillid){
		 StoreAuth storeAuth = storeAuthService.getStoreAuthByHql(tillid);
		 return storeAuth;
	 }
	 
	 
	 
	 /**
	  * 通过收银机号获取下属店铺
	  * @param tillid	收银机编号
	  */
	 @RequestMapping("/getStoreListBytillid")
	 @ResponseBody
	 public Object getStoreListBytillid(String tillid){
		 List<TillidToStore> liTillidToStores =storeAuthService.getListTillidToStoresByTillid(tillid);
		 List<XfStore> listxfstore = new ArrayList<>();
		 for(TillidToStore tillidToStore:liTillidToStores){
			 String xfStoreCode = tillidToStore.getXfStoreCode();
			 XfStore xfStore = xfStoreService.getXfStoreByStoreCode(xfStoreCode);
			 listxfstore.add(xfStore);
		 }
		 return listxfstore;
	 }
	 
	
	 
	 /**
	  * 通过店铺获取下属收银机
	  * @param storecode	店铺编号
	  */
	 @RequestMapping("/getTillidListBystorecode")
	 @ResponseBody
	 public Object getTillidListBystorecode(String storecode){
		 List<StoreAuth> liTillidToStores =storeAuthService.getStoreAuthListBystorecode(storecode);
		 return liTillidToStores;
	 }
	 
	 /**
	  *  删除权限配置
	  */
	 @RequestMapping("/deleteStoreAuth")
	 @ResponseBody
	 public Object deleteStoreAuth(HttpServletRequest request,String id){
		 try {
			storeAuthService.deleteStoreAuth(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return true;
	 }
	 
	 /**
	  *  判断收银机编号是否已经存在
	  * @param tillid 收银机编号
	  */
	 @RequestMapping("/isTillided")
	 @ResponseBody
	 public Object isTillided(HttpServletRequest request,String tillid){
		 StoreAuth storeAuth = storeAuthService.getStoreAuthByHql(tillid);
		 if(storeAuth != null){  //已经存在
			 return true;
		 }
		 return false;
	 }
	 
	/**
	 * 更新收银机
	 * 
	 * @return
	 */
	@RequestMapping("refreshTillid")
	@ResponseBody
	public Object refreshStore(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		try {
			// 从ＭＩＳ获取收银机
			Map<String, Object> si = new HashMap<>();
			si = (Map<String, Object>) joinMis.getTillids();
			if ("1".equals(si.get("code").toString())) {
				map.put("flag", true);
				map.put("msg", "更新收银机信息成功！请刷新！");
				Sets sets = new Sets();
				sets.setId("updateTime");
				sets.setValue(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
				setService.saveOrUpdateSets(sets);
				return map;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新收银机" + e.getMessage());
		}
		map.put("flag", false);
		map.put("msg", "更新收银机失败！");
		return map;
	}
	 
	 
}
