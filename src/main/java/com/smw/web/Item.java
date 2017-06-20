package com.smw.web;

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

import com.gws.service.impl.synchv61Service;
import com.smw.common.util.DateUtil;
import com.smw.common.util.PropertiesUtil;
import com.smw.common.util.SetEnum;
import com.smw.common.util.StringUtil;
import com.smw.common.util.paging.Paging;
import com.smw.pojo.Sets;
import com.smw.pojo.XfItem;
import com.smw.pojo.XfStaff;
import com.smw.pojo.XfStore;
import com.smw.service.SetService;
import com.smw.service.XfItemService;
import com.smw.service.XfStoreService;

/**
 * 商品
 * @author suen
 * @date 2016年5月26日-下午3:54:14
 * @version 1.0
 */
@Controller
@RequestMapping(value="web/xfItem")
public class Item {
	Logger logger = LoggerFactory.getLogger(Item.class);
	
	@Resource
	private XfItemService xfItemService;
	
	@Resource
	private XfStoreService xfStoreService;
	
	@Autowired SetService setService;
	
	@Autowired synchv61Service synchv61;
	/**
	 * 获取商品
	 * @param request
	 * @param pageIndex 页码
	 * @param pageSize 每页显示的行数
	 * @param key 搜索条件
	 * @return
	 */
	@RequestMapping("getItem")
	private Object getItem(HttpServletRequest request,Integer pageIndex,Integer pageSize,String key){
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
			//商品总数
			Integer totalCount=0;
			//商品集合
			List<XfItem> xfItems;
			//员工
			XfStaff xfStaff=(XfStaff)request.getSession().getAttribute("XfStaff");
			//权限名称
			String authority = xfStaff.getStaffRole().getAuthority();
			if ("ROLE_MALL_ADMIN".equals(authority)) {
				//获取总数
				totalCount = xfItemService.getCount(null, key);
				//获取集合
				xfItems=xfItemService.getXfItemList(pageIndex,pageSize,null,key);
			}else if("ROLE_STORE_ADMIN".equals(authority)){
				//获取总数
				totalCount = xfItemService.getCount(xfStaff.getXfIssuestore().getXfStorecode(), key);
				//获取集合
				xfItems=xfItemService.getXfItemList(pageIndex,pageSize,xfStaff.getXfIssuestore().getXfStorecode(),key);
			}else{
				xfItems=new ArrayList<>();
			}
			//封装到分页对象中
			Paging<XfItem> paging=new Paging<>(pageIndex, pageSize, totalCount, xfItems);
			//放在request中
			request.getSession().setAttribute("paging", paging);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取商品"+e.getMessage());
		}
		//设置菜单选中项
		request.getSession().setAttribute("select", "item");
		//如果是61上传则需要添加组织机构
		if("true".equals(V61.isUploadV61)){
			request.getSession().setAttribute("isUploadV61", true);
		}else{
			request.getSession().setAttribute("isUploadV61", false);
		}
		return "/item";
	}

	/**
	 * 新MIS
	 */
	@Resource
	private JoinMis joinMis;
	
	/**
	 * 更新商品信息
	 * @param request
	 * @return
	 */
	@RequestMapping("refreshItem")
	@ResponseBody
	public Object refreshItem(HttpServletRequest request){
		Map<String, Object> map=new HashMap<>();
		
		PropertiesUtil pUtil = new PropertiesUtil("systemConfig.properties");
		String misorv61;
		misorv61 = pUtil.readProperty("gdws.ifs.misorv61");
		if(misorv61.equals("MIS")){
			try {
				XfStaff xfStaff = (XfStaff) request.getSession().getAttribute("XfStaff");
				if(xfStaff!=null){
					String authority = xfStaff.getStaffRole().getAuthority();
					Map<String, Object> si=new HashMap<>();
					if ("ROLE_MALL_ADMIN".equals(authority)) {
						//更新所有
						List<XfStore> xfStoreList = xfStoreService.selectAllxfStore();
						if (xfStoreList!=null && xfStoreList.size()>0) {
							for (XfStore xfStore : xfStoreList) {
								if("0".equals(xfStore.getIsInvalid())){
									xfItemService.updateXfItemByStoreCode(xfStore.getXfStorecode());
								}
								else si= (Map<String, Object>) joinMis.getSales_item(xfStore.getXfStorecode());
							}
						}
					}else if("ROLE_STORE_ADMIN".equals(authority)){
						//根据店铺编号更新商品
						si= (Map<String, Object>) joinMis.getSales_item(xfStaff.getXfIssuestore().getXfStorecode());
					}
					if("1".equals(si.get("code").toString())){
						map.put("flag", true);
						map.put("msg", "更新商品信息成功！请刷新！");
						Sets sets = new Sets();
						sets.setId("updateTime");
						sets.setValue(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
						setService.saveOrUpdateSets(sets);
						return map;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("更新商品"+e.getMessage());
			}
		}else{
			System.out.println("V61____________________");
			synchv61.savesynchItem();
			System.out.println("V61______________________");
			logger.info("同步商品信息成功----------------");
			map.put("flag", true);
			map.put("msg", "更新商品信息成功！请刷新！");
			Sets sets = new Sets();
			sets.setId("updateTime");
			sets.setValue(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			setService.saveOrUpdateSets(sets);
			return map;
		}
		
	
		map.put("flag", false);
		map.put("msg", "更新商品信息失败！");
		return map;
	} 
	
	/**
	 * 获取商铺集合
	 * @return
	 */
	@RequestMapping("getStores")
	@ResponseBody
	public Object getStores(String codeOrName){
		List<XfStore> xfStoreList = xfStoreService.getXfStoreList(null, null, null, codeOrName);
		/*List<XfStore> sl=new ArrayList<>();
		if (xfStoreList!=null && xfStoreList.size()>0) {
			for (XfStore xfStore : xfStoreList) {
				if (!xfStore.getXfCenter()) {
					sl.add(xfStore);
				}
			}
		}
		return sl;*/
		return xfStoreList;
	}
	
	/**
	 * 添加商品信息
	 * @param request
	 * @param pageIndex 页码
	 * @param pageSize 每页显示的行数
	 * @param key 搜索条件
	 * @param xfItem 商品对象
	 * @param xfStorecode 店铺编号
	 * @return
	 */
	@RequestMapping("addItem")
	public Object addItem(HttpServletRequest request,Integer pageIndex,Integer pageSize,String key,XfItem xfItem,String storeCode){
		if (StringUtil.isNUllStr(key)) {
			key=null;
		}
		try {
			//查询店铺
			XfStore xfStore = xfStoreService.getXfStoreByStoreCode(storeCode);
			if (xfStore==null) {
				return getItem(request, pageIndex, pageSize, key);
			}
			//设置店铺
			xfItem.setXfStorecode(xfStore);
			xfItem.setIsInvalid("1");
			//保存商品信息
			xfItemService.saveOrUpdateXfItem(xfItem);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加商品"+e.getMessage());
		}
		return getItem(request, pageIndex, pageSize, key);
	}
	
	/**
	 * 根据商品号商场商品
	 * @param xfPlus 商品号
	 * @return
	 */
	@RequestMapping("deleteXfItemByXfPlu")
	@ResponseBody
	public Object deleteXfItemByXfPlu(String xfPlus){
		try {
			if(StringUtil.isNUllStr(xfPlus)){
				return false;
			}
			//删除商品
			xfItemService.deleteXfItemByXfPlu(xfPlus);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除商品"+e.getMessage());
		}
		return false;
	}
	
	/**
	 * 检查商品是否存在
	 * @param xfPlus 商品编号
	 * @return
	 */
	@RequestMapping("checkXfPlu")
	@ResponseBody
	public Object checkXfPlu(String xfPlus){
		return xfItemService.getXfItem(xfPlus)!=null;
	}
	
	/**
	 * 获取商品信息
	 * @param xfPlus 商品号
	 * @return
	 */
	@RequestMapping("getXfItemByXfPlu")
	@ResponseBody
	public Object getXfItemByXfPlu(String xfPlus){
		return xfItemService.getXfItem(xfPlus);
	}
	
	/**
	 * 修改商品信息
	 * @param request
	 * @param pageIndex 页码
	 * @param pageSize 每页显示的行数
	 * @param key 搜索条件
	 * @param xfItem 商品对象
	 * @param xfStorecode 店铺编号
	 * @return
	 */
	@RequestMapping("updateItem")
	public Object updateItem(HttpServletRequest request,Integer pageIndex,Integer pageSize,String key,XfItem xfItem,String storeCode){
		if (StringUtil.isNUllStr(key)) {
			key=null;
		}
		try {
			//查询店铺
			XfStore xfStore = xfStoreService.getXfStoreByStoreCode(storeCode);
			if (xfStore!=null) {
				xfItem.setXfStorecode(xfStore);
			}
			//设置店铺
			xfItem.setXfStorecode(xfStore);
			xfItem.setIsInvalid("1");
			//保存商品信息
			xfItemService.saveOrUpdateXfItem(xfItem);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改商品"+e.getMessage());
		}
		return getItem(request, pageIndex, pageSize, key);
	}
}
