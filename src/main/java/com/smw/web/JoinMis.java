package com.smw.web;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.integrator.spi.Integrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gws.service.impl.GdwsMisService;
import com.smw.common.util.ApiDataMap;
import com.smw.common.util.HttpClientUtil;
import com.smw.common.util.PropertiesUtil;
import com.smw.oldThread.SaleSummaryTask;
import com.smw.pojo.BuildingInfo;
import com.smw.pojo.FormatInfo;
import com.smw.pojo.Sets;
import com.smw.pojo.StoreAuth;
import com.smw.pojo.TillidToStore;
import com.smw.pojo.XfItem;
import com.smw.pojo.XfMall;
import com.smw.pojo.XfStore;
import com.smw.pojo.XfStoreCenter;
import com.smw.pojo.XfTender;
import com.smw.pojo.XfTillidState;
import com.smw.service.BuildingInfoService;
import com.smw.service.FormatInfoService;
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
import com.smw.service.XfTenderService;
import com.smw.service.XfTillidStateService;
import com.smw.utils.SetUpdateTime;
import com.smw.utils.XfStoreToPush;

import cn.com.gowins.cpos.netty.PushServer;

@Controller
@RequestMapping("web/JionMis")
@Service
public class JoinMis extends ApiDataMap {

	Logger logger = LoggerFactory.getLogger(JoinMis.class);
	@Resource GdwsMisService gdwsMisService;
	// MIS系统接口地址
	//private static String MISURL = "http://192.168.1.105:8080/webapi.do";

	/**
	 * 获取参数
	 * 
	 * @param DYLX
	 *            调用接口类型
	 * @param data
	 *            数据
	 * @return
	 */
	private Map<String, Object> getParams(String DYLX, String data) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// 接口用户标识
		paramMap.put("YHBS", "E84E7273764D2D77E9FC4E9C521E8C3750");
		// 调用系统类别
		paramMap.put("XTLB", "suen");
		// 调用接口类型
		paramMap.put("DYLX", DYLX);
		if (data != null) {
			// 数据
			paramMap.put("DATA", data);
		}
		return paramMap;
	}

	@Resource
	BuildingInfoService buildingInfoService;
	@Resource
	FormatInfoService formatInfoService;
	
	/**
	 * 楼宇
	 */
	public Object buildingInfo(){
		JSONObject misJson = (JSONObject)gdwsMisService.getBuildingInfo();
		String misCode=misJson.getJSONObject("status").getString("code");
		String misMsg=misJson.getJSONObject("status").getString("msg");
		JSONArray misBuilding = misJson.getJSONArray("data");
		List<BuildingInfo> insParam=new ArrayList<>();
		Iterator<Object> misTenderItr=misBuilding.iterator();
		while(misTenderItr.hasNext()){
			JSONObject misBuildingObj=(JSONObject) misTenderItr.next();
			BuildingInfo buildingInfo = new BuildingInfo();
			buildingInfo.setId(misBuildingObj.getInteger("id"));
			buildingInfo.setBm(misBuildingObj.getString("bm"));
			buildingInfo.setName(misBuildingObj.getString("name"));
			buildingInfo.setArea(misBuildingObj.getString("area"));
			buildingInfo.setState(misBuildingObj.getInteger("state"));
			buildingInfo.setTime(misBuildingObj.getString("time"));
			insParam.add(buildingInfo);
		}
		buildingInfoService.saveBuildingInfo(insParam);
		return success();
	}
	
	/**
	 * 业态
	 */
	public Object formatInfo(){
		JSONObject misJson = (JSONObject)gdwsMisService.getFormatInfo();
		String misCode=misJson.getJSONObject("status").getString("code");
		String misMsg=misJson.getJSONObject("status").getString("msg");
		JSONArray misFormatInfo = misJson.getJSONArray("data");
		List<FormatInfo> insParam=new ArrayList<>();
		Iterator<Object> misTenderItr=misFormatInfo.iterator();
		while(misTenderItr.hasNext()){
			JSONObject misFormatObj=(JSONObject) misTenderItr.next();
			FormatInfo formatInfo = new FormatInfo();
			formatInfo.setId(misFormatObj.getInteger("id"));
			formatInfo.setBm(misFormatObj.getString("bm"));
			formatInfo.setFid(misFormatObj.getString("fid"));
			formatInfo.setName(misFormatObj.getString("name"));
			formatInfo.setState(misFormatObj.getInteger("state"));
			formatInfo.setTime(misFormatObj.getString("time"));
			insParam.add(formatInfo);
		}
		formatInfoService.saveFormatInfo(insParam);
		return success();
	}
	
	@Resource
	XfMallService xfMallService;

	/**
	 * 获取购物中心信息
	 * @return
	 */
	@RequestMapping("getMall")
	@ResponseBody
	public Object getMall() {
		try {
			String httpPost = HttpClientUtil.httpPost(Init.INITURL.get("misUrl"), getParams("WEB_MALLINFO_LIST", null));
			System.out.println(httpPost + "------");
			JSONObject httpJson = JSONObject.parseObject(httpPost);
			
			JSONObject mallJson = JSONObject.parseObject(httpJson.getString("rows"));
			String code = JSONObject.parseObject(httpJson.getString("status")).getString("code");
			if ("10000".equals(code)) {
				XfMall xfMall = new XfMall();
				xfMall.setXfMallid(mallJson.getString("bm"));
				xfMall.setXfMallname(mallJson.getString("name"));
				xfMallService.saveOrupdateXfMall(xfMall);
				return success(successMsg);
			}else{
				logger.error("新MIS获取商场信息 "+httpPost);
				return unSuccessful(unSuccessfulMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新 MIS 获取购物中心"+e.getMessage());
		}
		return unSuccessful();
	}
	
	@Resource
	XfTenderService xfTenderService;
	
	
	/**
	 * 购物中心获取收款方式
	 * @param request
	 * @return
	 */
	@RequestMapping("getTender")
	@ResponseBody
	public Object tender(HttpServletRequest request) {
		try {
			List<XfTender> posTenders = xfTenderService.selectAllTender();
			XfMall mall=xfMallService.uniqueResult();
			JSONObject misJson=	(JSONObject) gdwsMisService.getMisTender(mall.getXfMallid());
			String misCode=misJson.getJSONObject("status").getString("code");
			String misMsg=misJson.getJSONObject("status").getString("msg");
			// 付款方式集合
			if ("10000".equals(misCode)) {
				List<XfTender> insParam=new ArrayList<>();
				List<XfTender> delParam=new ArrayList<>();
				List<XfTender> updateParam=new ArrayList<>();
				//是否更新标记。sum为0表示未更新，sum大于0表示有更新，删除，新增。
				int sum = 0;
				
				//获取MIS系统中的商铺集合
				JSONArray misTenders = misJson.getJSONArray("data");
				//商铺集合
				/**判断服务端删除数据**/
				for(XfTender posTender:posTenders){
					String tenderId=posTender.getXfTendercode();boolean delflag=false;
					Iterator<Object> itr=misTenders.iterator();
					while(itr.hasNext()){
						JSONObject misTender=(JSONObject) itr.next();
						if(tenderId.equals(misTender.get("code"))) delflag=true;
					}
					if(!delflag){
						posTender.setIsInvalid("0");
						delParam.add(posTender);
						logger.info("服务端删除的支付方式"+tenderId);
						sum++;
					} 
				}

				Iterator<Object> misTenderItr=misTenders.iterator();
				while(misTenderItr.hasNext()){
					JSONObject misTendeObj=(JSONObject) misTenderItr.next();boolean Installfalg=false;boolean updateflag=true;
					System.out.println(misTendeObj);
					XfTender misTender=new XfTender();	
					String tenderId=misTendeObj.getString("code");
					misTender.setXfTendercode(misTendeObj.getString("code"));
					misTender.setXfTenderdesc(misTendeObj.getString("name"));
					misTender.setRate(misTendeObj.getString("rate"));
					misTender.setXfRefund(misTendeObj.getInteger("isRefund")==0?false:true);
					misTender.setIsInvalid(misTendeObj.getInteger("status")==1?"1":"0");
					for(XfTender posTender:posTenders){
						if(misTender.getXfTendercode().equals(tenderId)){
							if(!posTender.equals(misTender))updateflag=false;
							if("0".equals(posTender.getIsInvalid())) updateflag=false;
							Installfalg=true;
						}
					}
					if(!Installfalg){
						insParam.add(misTender);
						logger.info("服务端新增加的支付方式"+tenderId);
						sum++;
					}
					if(!updateflag){
						updateParam.add(misTender);
						logger.info("服务端更新的支付方式"+tenderId);
						sum++;
					}
				}
				
				
				xfTenderService.saveOrupdateTenderList(insParam);
				xfTenderService.saveOrupdateTenderList(delParam);
				xfTenderService.saveOrupdateTenderList(updateParam);
				if(sum>0){
					XfStoreToPush.ToPushAll("tender");//支付方式更新，所有推送信息
				}
				return success(successMsg);
				
				
				
			}else{
				logger.error("新MIS获取付款方式失败 "+misCode+"  "+misMsg);
				return unSuccessful(unSuccessfulMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新 MIS 获取购物中心付款方式",e);
		}
		//把付款方式集合放在session中
		//request.getSession().setAttribute("xfTenders", xfTenders);
		return unSuccessful();
	}

	
	@Resource
	XfStoreService xfStoreService;
	@Resource
	XfStaffService xfStaffService;
	
	
	@Resource 
	XfTillidStateService xfTillidStateService;
	
	
	/**
	 * 中央店铺
	 */
	@Resource
	private XfStoreCenterService xfStoreCenterService;
	/**
	 * 从MIS系统获取商铺
	 * @return
	 */
	@RequestMapping("getStores")
	@ResponseBody
	public Object getStores(){
		
		PropertiesUtil pUtil = new PropertiesUtil("systemConfig.properties");
		JSONObject misJson=(JSONObject) gdwsMisService.getMisValidStore();
		String misCode=misJson.getJSONObject("status").getString("code");
		String misMsg=misJson.getJSONObject("status").getString("msg");
		if ("10000".equals(misCode)) {
			//1 判断删除的店铺数据 2 判断update 的数据 3 插入数据

			List<XfStore> insParam=new ArrayList<>();
			List<XfStore> delParam=new ArrayList<>();
			List<XfStore> updateParam=new ArrayList<>();
			List<String>  delCenterStore=new ArrayList<>();
			//获取MIS系统中的商铺集合
			JSONArray misStores = misJson.getJSONArray("data");
			//商铺集合
			List<XfStore> posStores = xfStoreService.listAllStore();
			/**判断服务端删除数据**/
			for(XfStore posStore:posStores){
				String storeId=posStore.getXfStorecode();boolean delflag=false;
				Iterator<Object> itr=misStores.iterator();
				while(itr.hasNext()){
					JSONObject misStore=(JSONObject) itr.next();
					if(storeId.equals(misStore.get("code"))||storeId.equals(pUtil.readProperty("gdws.ifs.miscenterstore"))) delflag=true;
				}
				if(!delflag){
					posStore.setIsInvalid("0");
					delParam.add(posStore);
					logger.info("服务端删除的店铺"+posStore.getXfStorecode());
				} 
			}
			XfMall mall=xfMallService.uniqueResult();
			Iterator<Object> misStoreItr=misStores.iterator();
			while(misStoreItr.hasNext()){
				JSONObject misStore=(JSONObject) misStoreItr.next();boolean Installfalg=false;boolean updateflag=true;
				XfStore misXfStore=new XfStore();String storeId=misStore.getString("code");
				misXfStore.setXfStorecode(storeId);misXfStore.setXfName(misStore.getString("name"));misXfStore.setXfMallid(mall);
				boolean isCenter=false;if(misStore.getInteger("type")!=null && misStore.getInteger("type")==1) 
				isCenter=true;	 misXfStore.setXfCenter(isCenter);	
				misXfStore.setGwBuildingTreeId(misStore.getString("gwBuildingTreeId"));
				misXfStore.setGwFormatsTreeId(misStore.getString("gwFormatsTreeId"));
				misXfStore.setEffectTime(misStore.getString("effectTime"));

				for(XfStore posStore:posStores){
					if(posStore.getXfStorecode().equals(storeId)){
						if(!posStore.equals(misXfStore))updateflag=false;
						if("0".equals(posStore.getIsInvalid())) updateflag=false;
						if(posStore.getXfCenter()!=misXfStore.getXfCenter()) delCenterStore.add(posStore.getXfStorecode());
						Installfalg=true;
					}
				}
				
				if(!Installfalg){
					misXfStore.setXfUpdate(true);	//更新提醒 
					misXfStore.setXfCenter(false);	//中央收银标记
					misXfStore.setXfTillcount(0);	//有几台收银机
					misXfStore.setIsInvalid("1");
					insParam.add(misXfStore);
					logger.info("服务端新增加的店铺"+storeId);
				}
				if(!updateflag){
					misXfStore.setIsInvalid("1");
					updateParam.add(misXfStore);
					logger.info("服务端更新的店铺"+storeId);
				}
			}

			//添加到数据库中
			xfStoreService.saveOrUpdateXfStores(insParam);
			xfStoreService.saveOrUpdateXfStores(updateParam);
			xfStoreService.saveOrUpdateXfStores(delParam);
			if(delCenterStore.size()!=0)	xfStoreService.deleteXfCenterStore(delCenterStore);
			
			List<XfStore> countList=new ArrayList<>();
			countList.addAll(insParam);
			countList.addAll(updateParam);
			//countList.addAll(delParam);//删除的店铺是否需要推送机制
			
			XfStoreToPush.ToPushByXfStore("store", countList);//商品更新，推送消息
			
			return success(successMsg);
		} else {
			logger.error("新MIS获取商铺  服务端异常: "+misCode+"   "+misMsg);
			return unSuccessful(unSuccessfulMsg);
		}
	
	}
	
	@Resource
	SalesSummaryService salesSummaryService;
	@Resource
	SalesItemService salesItemService;
	@Resource
	private SetService setService;
	@Resource
	private SalesTenderService salesTenderService;
	/**
	 * 
	 * 设置销售数据
	 * @return
	 */
	@RequestMapping("setSalesSummary")
	@ResponseBody
	public void setSalesSummary(){
		SaleSummaryTask task = new SaleSummaryTask();
		Thread thread=new Thread(task);
		task.setSalesSummary(salesSummaryService,salesItemService,setService,salesTenderService);
		thread.start();
	}
	
	@Resource
	XfItemService xfItemService;
	/**
	 * 获取商品明细
	 * @return
	 */
	@RequestMapping("getSales_item")
	@ResponseBody
	public Object getSales_item(String shopsLeaseCode) {
		String storeCode=shopsLeaseCode;
		try {
			JSONObject misJson=(JSONObject) gdwsMisService.getMisItemByStoreCode(shopsLeaseCode);
			String misCode=misJson.getJSONObject("status").getString("code");
			if ("10000".equals(misCode)) {
				
				JSONArray misItems = misJson.getJSONArray("data");
				List<XfItem> posItems=xfItemService.selectAllByStoreCode(storeCode);
				
				List<XfItem> countList=new ArrayList<>();
				/**判断服务端删除数据**/
				for(XfItem posItem:posItems){
					String itemId=posItem.getXfPlu();boolean delflag=false;
					Iterator<Object> itr=misItems.iterator();
					while(itr.hasNext()){
						JSONObject misItem=(JSONObject) itr.next();
						if(itemId.equals(misItem.get("code"))) delflag=true;
					}
					if(!delflag){
						posItem.setIsInvalid("0");
						xfItemService.saveOrUpdateXfItem(posItem);//服务端删除的商品；
						logger.info("服务端删除的商品信息"+posItem.getXfPlu());
						countList.add(posItem);
					} 
				}
				Iterator<Object> misItemItr=misItems.iterator();
				while(misItemItr.hasNext()){
					JSONObject misItem=(JSONObject) misItemItr.next();boolean Installfalg=false;boolean updateflag=true;
					XfItem misXfItem=new XfItem();String itemId=misItem.getString("code");
					misXfItem.setXfPlu(misItem.getString("code"));
					misXfItem.setXfLongdesc(misItem.getString("remark"));
					misXfItem.setXfDesci(misItem.getString("name"));
					misXfItem.setXfSelwprice(new BigDecimal(0));
					misXfItem.setXfSeluprice(new BigDecimal(0));
					misXfItem.setXfOrguprice(new BigDecimal(0));
					misXfItem.setXfOrgwprice(new BigDecimal(0));
					misXfItem.setXfSalesunit("元");
					misXfItem.setXfStkunit("个");
					misXfItem.setIsInvalid("1");
					misXfItem.setXfExstk2sales(new BigDecimal(1));
					XfStore store=null;
					try {
						 store = xfStoreService.getXfStoreByStoreCode(misItem.getString("shopsLeaseCode"));
					} catch (Exception e) {
						logger.info("同步商品数据时 店铺编号 没有同步"+misItem.getString("shopsLeaseCode"));
					}
					misXfItem.setXfStorecode(store);
					for(XfItem xfItem:posItems){
						if(xfItem.getXfPlu().equals(itemId)){
							if(!xfItem.equals(misXfItem))updateflag=false;
							if("0".equals(xfItem.getIsInvalid())) updateflag=false;
							Installfalg=true;
						}
					}
					if(!Installfalg){
						xfItemService.saveOrUpdateXfItem(misXfItem);//服务端插入的商品；
						logger.info("服务端新增加的商品"+itemId);
						countList.add(misXfItem);
					}
					if(!updateflag){
						xfItemService.saveOrUpdateXfItem(misXfItem);//服务端更新的商品；
						logger.info("服务端更新的商品"+itemId);
						countList.add(misXfItem);
					}
					
					
				}
				
				List<XfStore> countListXfstore=new ArrayList<>();
				for(XfItem item:countList){	
					countListXfstore.add(item.getXfStorecode());	
				}

				XfStoreToPush.ToPushByXfStore("item", countListXfstore);//商品更新，推送消息
				
				return success(successMsg);
			}else{
				int result=xfItemService.updateXfItemByStoreCode(storeCode);
				if(result<0) logger.info("新MSI 店铺："+storeCode+"下的商品更新失败");
				logger.error("店铺号"+shopsLeaseCode+ " 新MIS获取商品 服务端异常或者该店铺下没有商品： "+misJson.toJSONString());
				return unSuccessful(unSuccessfulMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("店铺号"+shopsLeaseCode+  "新 MIS 获取商品 服务端异常或者该店铺下没有商品的异常信息 ：",e);
		}
		return unSuccessful();
	}

	@Resource
	private StoreAuthService storeAuthService;
	public Map<String, Object> getTillids() {
		
		JSONObject tillid_store=(JSONObject)gdwsMisService.getTillid_Store();//获取MIS收银机关联店铺
		String misCodetillid_store=tillid_store.getJSONObject("status").getString("code");
		
		if("10000".equals(misCodetillid_store)){
			List<TillidToStore> insParamTillidToStore=new ArrayList<>();
			List<TillidToStore> delParamTillidToStore=new ArrayList<>();
			List<TillidToStore> updateParamTillidToStore=new ArrayList<>();
			JSONArray mistillid_store=tillid_store.getJSONArray("data");//  获取mis收银机店铺关联数据
			List<TillidToStore> listTillidToStore=storeAuthService.getListTillidToStores();//获取本地收银机店铺关联集合
			
			/**判断服务器端删除的收银机店铺关联**/
			for(TillidToStore tillidToStore:listTillidToStore){
				String tillidtoStoreId = tillidToStore.getId();boolean delflag=false;
				Iterator<Object> itrtillidtostore=mistillid_store.iterator();
				while(itrtillidtostore.hasNext()){
					JSONObject mistillidtoStore = (JSONObject) itrtillidtostore.next();
					if(tillidtoStoreId.equals(mistillidtoStore.get("id").toString())){
						delflag=true;
					}
				}
				
				if(!delflag){
					delParamTillidToStore.add(tillidToStore);
					logger.info("服务端删除的收银机店铺关联数据"+tillidToStore.getId());
				}
			}
			
			/**判断服务器端新增或者修改的收银机店铺关联**/
			Iterator<Object> itrtillidtostore=mistillid_store.iterator();
			while(itrtillidtostore.hasNext()){
				JSONObject mistillidtostore = (JSONObject) itrtillidtostore.next();boolean Installfalg=false;boolean updateflag=true;
				TillidToStore tillidToStore = new TillidToStore();
				String tillidToStoreId = mistillidtostore.getString("id");
				tillidToStore.setId(mistillidtostore.getString("id"));
				tillidToStore.setTillid(mistillidtostore.getString("code"));
				tillidToStore.setXfStoreCode(mistillidtostore.getString("shopCode"));
				for(TillidToStore tiToStore:listTillidToStore){
					if(tiToStore.getId().equals(tillidToStoreId)){
							if(!tiToStore.equals(tillidToStore))updateflag=false;
							Installfalg=true;
						
					}
				}
				
				if(!Installfalg){
					insParamTillidToStore.add(tillidToStore);
					logger.info("服务端新增加的收银机店铺关联数据"+tillidToStore.getId());
				}
				if(!updateflag){
					updateParamTillidToStore.add(tillidToStore);
					logger.info("服务端更新的收银机店铺关联数据"+tillidToStore.getId());
				}
			}
			
			//添加到数据库中
			for(TillidToStore sti:delParamTillidToStore){
				System.out.println("删除的店铺关联"+sti.getId());
				storeAuthService.deleteTillidToStoreById(sti.getId());
			}
			storeAuthService.saveOrUpdateTillidToStores(insParamTillidToStore);
			storeAuthService.saveOrUpdateTillidToStores(updateParamTillidToStore);
		}
		
		
		JSONObject misJson = (JSONObject)gdwsMisService.getTillids();//获取MIS收银机
		String misCode=misJson.getJSONObject("status").getString("code");
		
		if("10000".equals(misCode)){
			
			List<StoreAuth> insParamStoreAuth=new ArrayList<>();
			List<StoreAuth> delParamStoreAuth=new ArrayList<>();
			List<StoreAuth> updateParamStoreAuth=new ArrayList<>();
			JSONArray misTillids = misJson.getJSONArray("data");//获取mis收银机集合
			List<StoreAuth> listStoreAuths = storeAuthService.getListsStoreAuth();//获取本地收银机集合
			
			/**判断服务端删除数据收银机**/
			for(StoreAuth storeAuth:listStoreAuths){
				String storeauthId = storeAuth.getId();boolean delflag=false;
				Iterator<Object> itrtillid=misTillids.iterator();
				while(itrtillid.hasNext()){
					JSONObject mistillid = (JSONObject) itrtillid.next();
					if(storeauthId.equals(mistillid.get("id").toString())){
						delflag=true;
					}
	
				}
				if(!delflag){
					delParamStoreAuth.add(storeAuth);
					logger.info("服务端删除的收银机"+storeAuth.getTillid());
				}
				
			}
			
			/**判断服务器端新增或者修改的收银机**/
			Iterator<Object> itrtillid=misTillids.iterator();
			while(itrtillid.hasNext()){
				JSONObject mistillid = (JSONObject) itrtillid.next();boolean Installfalg=false;boolean updateflag=true;
				StoreAuth storeAuth = new StoreAuth();
				String tillidId = mistillid.getString("id");
				storeAuth.setId(mistillid.getString("id"));
				storeAuth.setTillid(mistillid.getString("code"));
				storeAuth.setType(mistillid.getString("type"));
				storeAuth.setCtime(mistillid.getString("createTime"));
				storeAuth.setAuthId("0");
				storeAuth.setScreenStyle("9");
				
				if(mistillid.getString("type").equals("1")){
					TillidToStore tillidToStore = storeAuthService.getTillidToStoreByTillid(mistillid.getString("code"));
					if(tillidToStore!=null){
						XfStore xfStore = xfStoreService.getXfStoreByCode(tillidToStore.getXfStoreCode());
						if(xfStore!=null){
							storeAuth.setStoreCode(xfStore.getXfStorecode());
							storeAuth.setStoreName(xfStore.getXfName());
						}
						
					}	
				}else{
					PropertiesUtil pUtil = new PropertiesUtil("systemConfig.properties");
					storeAuth.setStoreCode(pUtil.readProperty("gdws.ifs.miscenterstore"));
					storeAuth.setStoreName(pUtil.readProperty("gdws.ifs.miscenterstorename"));
				}

				for(StoreAuth auth:listStoreAuths){
					if(auth.getId().equals(tillidId)){
						storeAuth.setV61tillid(auth.getV61tillid());
						storeAuth.setIssueRanges(auth.getIssueRanges());
						storeAuth.setAuthId(auth.getAuthId());
						storeAuth.setScreenStyle(auth.getScreenStyle());
						
						if(!auth.equals(storeAuth))updateflag=false;Installfalg=true;						
					}
				}
				if(!Installfalg){
					insParamStoreAuth.add(storeAuth);
					logger.info("服务端新增加的收银机"+tillidId); 
				}
				if(!updateflag){
					updateParamStoreAuth.add(storeAuth);
					logger.info("服务端更新的收银机"+tillidId);
				}
			}
			
			//添加到数据库中
			for(StoreAuth s:delParamStoreAuth){
				System.out.println(s.getId()+"sssss");
				storeAuthService.deleteStoreAuth(s.getId());//删除收银机
			}
			storeAuthService.saveOrUpdatestoreAuths(insParamStoreAuth);
			storeAuthService.saveOrUpdatestoreAuths(updateParamStoreAuth);
			
		}

		
		return success();
	}

}
