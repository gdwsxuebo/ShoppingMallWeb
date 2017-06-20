package com.smw.web;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.gws.service.impl.PosCommConfigService;
import com.gws.service.impl.synchv61Service;
import com.smw.common.util.DateUtil;
import com.smw.common.util.PropertiesUtil;
import com.smw.common.util.SetEnum;
import com.smw.common.util.StringUtil;
import com.smw.common.util.paging.Paging;
import com.smw.pojo.FormatInfo;
import com.smw.pojo.Sets;
import com.smw.pojo.XfMall;
import com.smw.pojo.XfStaff;
import com.smw.pojo.XfStore;
import com.smw.pojo.XfStoreCenter;
import com.smw.service.FormatInfoService;
import com.smw.service.PosPrivateConfigService;
import com.smw.service.SalesItemService;
import com.smw.service.SalesSummaryService;
import com.smw.service.SalesTenderService;
import com.smw.service.SetService;
import com.smw.service.StoreAuthService;
import com.smw.service.XfItemService;
import com.smw.service.XfMallService;
import com.smw.service.XfStaffService;
import com.smw.service.XfStoreCenterService;
import com.smw.service.XfStoreService;
import com.smw.service.XfTillidStateService;

import cn.com.gowins.cpos.netty.NettyStart;
import cn.com.gowins.cpos.netty.PushServer;

/**
 * 商铺
 * @author suen
 * @date 2016年5月22日-下午3:40:18
 * @version 1.0
 */
@Controller
@RequestMapping("web/xfStore")
public class Store {
	Logger logger = LoggerFactory.getLogger(Store.class);
	/**
	 * 店铺
	 */
	@Resource
	private XfStoreService xfStoreService;
	
	/**
	 * 中央店铺
	 */
	@Resource
	private XfStoreCenterService xfStoreCenterService;
	
	/**
	 * 商场
	 */
	@Resource
	private XfMallService xfMallService;
	
	/**
	 * 商品
	 */
	@Resource
	private XfItemService xfItemService;
	
	@Resource XfTillidStateService xfTillidStateService;
	
	@Resource
	private SetService setService;
	
	
	@Resource
	private PosCommConfigService posCommConfigService;
	@Resource
	private  PosPrivateConfigService posPrivateConfigService;
	@Resource
	private StoreAuthService storeAuthService;
	@Autowired synchv61Service synchv61;
	
	/**
	 * 业态
	 */
	@Resource
	private FormatInfoService formatInfoService;
	
	/**
	 * 通过楼宇或业态查询店铺
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getStoreByBF")
	@ResponseBody
	public Object getStoreByBF(HttpServletRequest request, String buildings,Integer pageIndex,Integer pageSize, String formats){
		//获取符合查询要求的业态
		if(formats != null && !"".equals(formats)){
			StringBuffer sb = new StringBuffer();
			List<FormatInfo> formatIds = formatInfoService.getFormatInfoListByIdAndFid(pageIndex, pageSize, formats);
			for (FormatInfo formatInfo : formatIds) {
				sb.append(formatInfo.getId()+",");
			}
			formats = sb.substring(0,sb.length()-1);
		}
		List<XfStore> storeByBF = xfStoreService.getStoreByBF(null, null, buildings, formats);
		return storeByBF;
	}
	/**
	 * 获取商铺
	 * @param request
	 * @param pageIndex 页码
	 * @param pageSize 每页显示的行数
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("getStore")
	public Object getStore(HttpServletRequest request,Integer pageIndex,Integer pageSize,String key) throws ParseException{
		PropertiesUtil pUtil = new PropertiesUtil("systemConfig.properties");
		String misorv61;
		misorv61 = pUtil.readProperty("gdws.ifs.misorv61");
		request.setAttribute("misorv61", misorv61);
		
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
			//商铺总数
			Integer totalCount=0;
			//商铺集合
			List<XfStore> xfStores;
			//员工
			XfStaff xfStaff=(XfStaff)request.getSession().getAttribute("XfStaff");
			//权限名称
			String authority = xfStaff.getStaffRole().getAuthority();
			if ("ROLE_MALL_ADMIN".equals(authority)) {
				//获取总数
				totalCount = xfStoreService.getCount(null, key);
				//获取集合
				xfStores=xfStoreService.getXfStoreList(pageIndex,pageSize,null,key);
			}else if("ROLE_STORE_ADMIN".equals(authority)){
				//获取总数
				totalCount = xfStoreService.getCount(xfStaff.getXfIssuestore().getXfStorecode(), key);
				//获取集合
				xfStores=xfStoreService.getXfStoreList(pageIndex,pageSize,xfStaff.getXfIssuestore().getXfStorecode(),key);
			}else{
				xfStores=new ArrayList<>();
			}
			if (xfStores!=null && xfStores.size()>0) {
				XfStoreCenter xfStoreCenter;
				for (XfStore xfStore : xfStores) {
					String storeid=xfStore.getXfStorecode();
					if (!xfStore.getXfCenter()) {//不是中央店铺去查该店铺下的收银机
						Integer tillidNums=xfTillidStateService.getTillidCountByStoreId(storeid);
						xfStore.setXfTillcount(tillidNums);
						xfStoreCenter= xfStoreCenterService.getStoreCenter(storeid);//根据被关联的店铺查询是哪一个店铺关联的该店铺
						if (xfStoreCenter!=null) {
							xfStore.setXfCenterStore(xfStoreCenter.getXfCenterstorecode());
						}
					}
					else{
						Integer tillidNums=xfTillidStateService.getTillidCountByCoreStoreId(storeid);
						xfStore.setXfTillcount(tillidNums);
						//查询中央店铺和下属该店铺
					}
				}
			}
			//封装到分页对象中
			Paging<XfStore> paging=new Paging<>(pageIndex, pageSize, totalCount, xfStores);
			//放在request中
			request.setAttribute("paging", paging);
		} catch (Exception e) {
			//放在request中
			request.setAttribute("paging", new Paging<>(pageIndex, pageSize, 0, new ArrayList<>()));
			e.printStackTrace();
			logger.error("获取商铺"+e.getMessage());
		}
		//设置选中菜单项
		request.getSession().setAttribute("select", "store");
		return "/store";
	}
	
	/**
	 * 获取中央店铺
	 * @return
	 */
	@RequestMapping("getStoreCenter")
	@ResponseBody
	public Object getStoreCenter(String codeOrName){
		try {
			List<XfStoreCenter> xfStoreCenters= xfStoreCenterService.getStoreCenterList(codeOrName);
			if (xfStoreCenters!=null && xfStoreCenters.size()>0) {
				return JSON.toJSON(xfStoreCenters);
			}
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取中央商铺"+e.getMessage());
		}
		return "";
	}
	
	/**
	 * 设置中央店铺或者取消
	 * @return
	 */
	@RequestMapping("addIsDeleXfStoreCenter")
	@ResponseBody
	public Object addIsDeleXfStoreCenter(String xfStorecode,String xfCenterStorecode){
		try {
			if (StringUtil.isNUllStr(xfStorecode)) {
				return false;
			}
			//获取店铺
			XfStore xfStore = xfStoreService.getXfStoreByStoreCode(xfStorecode);
			if(xfStore==null){
				return false;
			}else if(StringUtil.isNUllStr(xfCenterStorecode)){
				//取消设置中心店铺
				xfStoreCenterService.deleteXfStoreCenter(xfStoreService,xfStore);
				return true;
			}else{
				//如果相等就是设置直接为中央店铺
				if (xfStorecode.equals(xfCenterStorecode)) {
					//如果该店铺下无商品则可以设置为中央店铺
					/*Integer count = xfItemService.getCount(xfStorecode, null);
					if (count>0) {
						return false;
					}*/
					XfStoreCenter center=new XfStoreCenter();
					//中央店铺
					center.setXfCenterstorecode(xfStore);
					//自己关联自己
					center.setXfStorecode(xfStore);
					xfStoreCenterService.saveXfStoreCenter(xfStoreService,xfStore,center);
					return true;
				}else{
					XfStoreCenter center=new XfStoreCenter();
					//中央店铺
					center.setXfCenterstorecode(xfStoreService.getXfStoreByStoreCode(xfCenterStorecode));
					//关联的店铺
					center.setXfStorecode(xfStore);
					xfStoreCenterService.saveXfStoreCenter(xfStoreService,xfStore,center);
					return true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("设置中央商铺或者取消中央商铺"+e.getMessage());
		}
		return false;
	}
	
	/**
	 * 取消关联中央店铺
	 * @param xfStorecode 被关联的店铺
	 * @param xfCenterStorecode 关联的中央店铺
	 * @return
	 */
	@RequestMapping("deleteXFSC")
	@ResponseBody
	public Object deleteXFSC(String xfStorecode,String xfCenterStorecode){
		try {
			if(StringUtil.isNUllStr(xfStorecode) || StringUtil.isNUllStr(xfCenterStorecode)){
				return false;
			}
			//被关联的店铺
			XfStore xfStore = xfStoreService.getXfStoreByStoreCode(xfStorecode);
			//关联的中央店铺
			XfStore xfStoreCenter = xfStoreService.getXfStoreByStoreCode(xfCenterStorecode);
			if(xfStore==null || xfStoreCenter==null){
				return false;
			}else{
				xfStoreCenterService.deleteXFSC(xfStore,xfStoreCenter);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("取消关联的中央商铺"+e.getMessage());
		}
		return false;
	}
	
	/**
	 * 新MIS
	 */
	@Resource
	private JoinMis joinMis;
	
	/**
	 * 更新商铺
	 * @return
	 */
	@RequestMapping("refreshStore")
	@ResponseBody
	public Object refreshStore(HttpServletRequest request){
		Map<String, Object> map=new HashMap<>();
		try {
			/*XfStaff xfStaff = (XfStaff) request.getSession().getAttribute("XfStaff");
			if(xfStaff!=null){
				String authority = xfStaff.getStaffRole().getAuthority();
				if ("ROLE_MALL_ADMIN".equals(authority)) {
					
				}else if("ROLE_STORE_ADMIN".equals(authority)){
					
				}
			}*/
			
			PropertiesUtil pUtil = new PropertiesUtil("systemConfig.properties");
			String misorv61;
			misorv61 = pUtil.readProperty("gdws.ifs.misorv61");
			if(misorv61.equals("MIS")){
				//从新MIS获取商铺
				Map<String, Object> gs= (Map<String, Object>) joinMis.getStores();
				if("1".equals(gs.get("code").toString())){
					map.put("flag", true);
					map.put("msg", "更新商铺信息成功！请刷新！");
					Sets sets = new Sets();
					sets.setId("updateTime");
					sets.setValue(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
					setService.saveOrUpdateSets(sets);
					return map;
				}
			}else{
				//从V61获取商铺数据
				logger.info("数据同步开始----------------");
				synchv61.savesynchStore();
				map.put("flag", true);
				map.put("msg", "更新商铺信息成功！请刷新！");
				Sets sets = new Sets();
				sets.setId("updateTime");
				sets.setValue(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
				setService.saveOrUpdateSets(sets);
				return map;
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新商铺"+e.getMessage());
		}
		map.put("flag", false);
		map.put("msg", "更新商铺失败！");
		return map;
	}
	
	/**
	 * 添加或者修改店铺 
	 * PS:这里没有用事务处理是因为设置中央店铺可以在页面中设置
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("addOrUpdateStore")
	public Object addOrUpdateStore(HttpServletRequest request,Integer pageIndex,Integer pageSize,String key,String xfStorecode,String xfName,String selectCenterStore) throws ParseException{
		if (key==null || "".equals(key.trim())) {
			key=null;
		}
		try {
			if(StringUtil.isNUllStr(xfStorecode) || StringUtil.isNUllStr(xfName) ||StringUtil.isNUllStr(selectCenterStore)
					|| xfStorecode.length()>30 || xfName.length()>30){
				return getStore(request, pageIndex, pageSize, key);
			}else{
				//查询商场
				XfMall selectMall = xfMallService.selectMall();
				if(selectMall!=null){
					//保存店铺
					XfStore xfStore=new XfStore();
					//店铺编号
					xfStore.setXfStorecode(xfStorecode);
					//店铺名称
					xfStore.setXfName(xfName);
					//设置商场
					xfStore.setXfMallid(selectMall);
					xfStore.setIsInvalid("1");
					//保存
					xfStoreService.saveOrUpdateSingXfStores(xfStore);
					if("no".equals(selectCenterStore)){
						
					}
					//如果值为my就表示设置自己为中央店铺
					else if("my".equals(selectCenterStore)){
						//设置中央店铺
						XfStoreCenter xfStoreCenter=new XfStoreCenter();
						xfStoreCenter.setXfCenterstorecode(xfStore);
						xfStoreCenter.setXfStorecode(xfStore);
						//保存中央店铺
						xfStoreCenterService.saveXfStoreCenter(xfStoreService, xfStore, xfStoreCenter);
					}else{
						//设置中央店铺
						XfStoreCenter xfStoreCenter=new XfStoreCenter();
						xfStoreCenter.setXfCenterstorecode(xfStoreService.getXfStoreByStoreCode(selectCenterStore));
						xfStoreCenter.setXfStorecode(xfStore);
						//保存中央店铺
						xfStoreCenterService.saveXfStoreCenter(xfStoreService, xfStore, xfStoreCenter);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加或者修改商铺"+e.getMessage());
		}
		return getStore(request, pageIndex, pageSize, key);
	}
	
	/**
	 * 根据店铺编号验证店铺是否存在
	 * @param storeCode 店铺编号
	 * @return
	 */
	@RequestMapping("checkStoreByStoreCode")
	@ResponseBody
	public Object checkStoreByStoreCode(String storeCode){
		if (StringUtil.isNUllStr(storeCode)) {
			return true;
		}
		XfStore xfStore = xfStoreService.getXfStoreByStoreCode(storeCode);
		return xfStore!=null;
	}
	
	/**
	 * 修改店铺
	 * @param oldXfStorecode 老的店铺编号
	 * @param xfStorecode 店铺编号
	 * @param xfName 店铺名称
	 * @return
	 */
	@RequestMapping("editStoreCode")
	@ResponseBody
	public Object editStoreCode(String xfStorecode,String xfName){
		Map<String, Object> map=new HashMap<>();
		try {
			if (StringUtil.isNUllStr(xfStorecode) || StringUtil.isNUllStr(xfName) || xfStorecode.length()>15 || xfName.length()>30) {
				map.put("flag", false);
				map.put("msg", "参数不合法！");
				return map;
			}else{
				//查询店铺
				XfStore xfStore = xfStoreService.getXfStoreByStoreCode(xfStorecode);
				if(xfStore!=null){
					//修改店铺名称
					xfStore.setXfName(xfName);
					//修改
					xfStoreService.saveOrUpdateSingXfStores(xfStore);
					map.put("flag", true);
					map.put("msg", "修改的店铺名称成功！");
					return map;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改商铺"+e.getMessage());
		}
		map.put("flag", false);
		map.put("msg", "修改失败！");
		return map;
	}
	
	/**
	 * 员工
	 */
	@Resource
	private XfStaffService xfStaffService;
	/**
	 * 销售单货品明细
	 */
	@Resource
	private SalesItemService salesItemService;
	/**
	 * 销售
	 */
	@Resource
	private SalesSummaryService salesSummaryService;
	/**
	 * 销售单付款明细
	 */
	@Resource
	private SalesTenderService salesTenderService;

	/**
	 * 根据店铺编号删除店铺
	 * @param xfStorecode 删除
	 * @return
	 */
	@RequestMapping("deleteXfStoreByCode")
	@ResponseBody
	public Object deleteXfStoreByCode(String xfStorecode){
		try {
			xfStoreService.deleteXfStoreByStCode(xfStorecode);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据店铺编号删除店铺"+e.getMessage());
		}
		return false;
	}
	
	/**
	 * 根据店铺编号或者名称查询店铺
	 * @param codeOrName 店铺编号或者名称
	 * @return
	 */
	@RequestMapping(value="getXfStoreByCodeOrName")
	@ResponseBody
	public Object getXfStoreByCodeOrName(String codeOrName){
		//中央店铺
		List<XfStoreCenter> storeCenterList = xfStoreCenterService.getStoreCenterList(codeOrName);
		return JSON.toJSON(storeCenterList);
	}
	
	
	/**
	 * 根据店铺编号或者名称查询店铺
	 * @param codeOrName 店铺编号或者名称
	 * @return
	 */
	@RequestMapping(value="getByXfStoreNameORCode")
	@ResponseBody
	public Object getByXfStoreNameORCode(String codeOrName){
		//中央店铺
		List<XfStore> storeByBF = xfStoreService.getByXfStoreNameORCode(codeOrName);
		return storeByBF;
	}
	
	/**
	 * 根据店铺编号或者名称查询店铺
	 * @param code 店铺编号
	 * @return
	 */
	@RequestMapping(value="getByXfStoreByCode")
	@ResponseBody
	public Object getgetByXfStoreByCode(String code){
		//查询店铺
		XfStore xfStore = xfStoreService.getXfStoreByStoreCode(code);
		return xfStore;
	}
}
