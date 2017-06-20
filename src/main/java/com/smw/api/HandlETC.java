package com.smw.api;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gws.pojo.PaymentRent;
import com.gws.pojo.PosCommonConfigModel;
import com.gws.service.impl.CrmService;
import com.gws.service.impl.GdwsGdpsService;
import com.gws.service.impl.GwUpdatePosService;
import com.gws.service.impl.GwXfStoreService;
import com.gws.service.impl.PaymentRentService;
import com.gws.service.impl.PosCommConfigService;
import com.gws.util.commonUtil;
import com.smw.bestoay.BestoayHandle;
import com.smw.common.util.ApiDataMap;
import com.smw.common.util.BasePageResultVo;
import com.smw.common.util.DateUtil;
import com.smw.common.util.IpUtil;
import com.smw.common.util.JsonMapper;
import com.smw.common.util.PropertiesUtil;
import com.smw.common.util.ResponseCode;
import com.smw.common.util.StringUtil;
import com.smw.common.util.StructureUtil;
import com.smw.license.LicenseVerify_val;
import com.smw.pojo.Bestoay;
import com.smw.pojo.PosConfigModel;
import com.smw.pojo.PromotionRule;
import com.smw.pojo.PromotionStamp;
import com.smw.pojo.PromotionUseRange;
import com.smw.pojo.ReturnGoodsAuthority;
import com.smw.pojo.SalesItem;
import com.smw.pojo.SalesSummary;
import com.smw.pojo.SalesTender;
import com.smw.pojo.Sets;
import com.smw.pojo.StoreAuth;
import com.smw.pojo.XfItem;
import com.smw.pojo.XfMall;
import com.smw.pojo.XfStaff;
import com.smw.pojo.XfStore;
import com.smw.pojo.XfStoreCenter;
import com.smw.pojo.XfTender;
import com.smw.pojo.XfTillidState;
import com.smw.pojo.oldSalesSummary.OldSalesItem;
import com.smw.pojo.oldSalesSummary.OldSalesSummary;
import com.smw.pojo.oldSalesSummary.OldSalesTender;
import com.smw.service.BestoayService;
import com.smw.service.Old_SalesItemService;
import com.smw.service.Old_SalesSummaryService;
import com.smw.service.Old_SalesTenderService;
import com.smw.service.PosPrivateConfigService;
import com.smw.service.PromotionRuleService;
import com.smw.service.PromotionStampService;
import com.smw.service.ReturnGoodsAuthorityService;
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
import com.smw.utils.MD5;
import com.smw.web.JionPHP;
import com.smw.web.JoinOLDMis;

/**
 * @project: GDWS
 * @author:文豪 E-mail:wenhaoboy@gmail.com
 * @version QQ:403665586 2016-04-16 调用方法处理
 */
@Service("webapiServiceManage")
public class HandlETC extends ApiDataMap {
	
	private Logger logger = LoggerFactory.getLogger(HandlETC.class);
	
	/**
	 * crm秘钥
	 */
	private static String crmMiyao;
	/**
	 * crm用户
	 */
	private static String crmUser;

	
	static {
		PropertiesUtil p = new PropertiesUtil("systemConfig.properties");
		crmMiyao = p.readProperty("crmMiyao");
		crmUser = p.readProperty("crmUser");
	}
	/**
	 * 店铺
	 */
	@Resource
	private XfStoreService xfStoreService;

	/**
	 * 收银员
	 */
	@Resource
	private XfStaffService xfStaffService;

	/**
	 * 平板收银机在线
	 */
	@Resource
	private XfTillidStateService xfTillidStateService;

	/**
	 * 商场
	 */
	@Resource
	private XfMallService xfMallService;

	/**
	 * 销售汇总
	 */
	@Resource
	private SalesSummaryService salesSummaryService;

	/**
	 * 付款方式
	 */
	@Resource
	private XfTenderService xfTenderService;

	/**
	 * 商品信息
	 */
	@Resource
	private XfItemService xfItemService;

	/**
	 * 销售付款明细
	 */
	@Resource
	private SalesTenderService salesTenderService;

	/**
	 * 销售单货品明细表
	 */
	@Resource
	private SalesItemService salesItemService;

	/**
	 * 中心店铺
	 */
	@Resource
	private XfStoreCenterService xfStoreCenterService;

	/**
	 * 促销
	 */
	@Resource
	private PromotionRuleService promotionRuleService;

	/**
	 * 促销劵
	 */
	@Resource
	private PromotionStampService promotionStampService;

	/**
	 * 翼支付
	 */
	@Resource
	private BestoayService bestoayService;

	/**
	 * 设置
	 */
	@Resource
	private SetService setService;

	/**
	 * 退货权限
	 */
	@Resource
	private ReturnGoodsAuthorityService returnGoodsAuthorityService;

	/**
	 * 旧销售
	 */
	@Resource
	private Old_SalesSummaryService old_salesSummaryService;

	/**
	 * 旧销售支付方式
	 */
	@Resource
	private Old_SalesTenderService old_salesTenderService;

	/**
	 * 旧销售商品
	 */
	@Resource
	private Old_SalesItemService old_SalesItemService;
	/**新的店铺查询*/
	@Resource GwXfStoreService gwXfStoreService;
	/**
	 * 获取收银机私有配置信息
	 */
	@Autowired 
	private PosPrivateConfigService posPrivateConfigService;
	/**
	 * 获取收银机公有配置信息
	 */
	@Autowired PosCommConfigService posCommConfigService;
	/**
	 * 收银机权限
	 */
	@Resource
	private StoreAuthService storeAuthService;
	
	
	
	@Resource CrmService crmService;//GDWS CRM
	
	/***
	 * 支付方式手续费率
	 */
	@Resource
	private PaymentRentService paymentRentService;
	@Autowired private GwUpdatePosService gwUpdatePosService;
	
	/**
	 * gdps促销平台连接
	 * **/
	@Autowired private GdwsGdpsService gdwsGdpsService;
	/**
	 * 
	 * verification:接口数据的校验
	 *
	 * @author yumaochun
	 * @date 2016年4月27日
	 * @param DYLX
	 * @param DATA
	 *            接口数据（json）
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	public BasePageResultVo verification(String DYLX, String DATA, HttpServletRequest request) throws Exception {
		BasePageResultVo remes = null;
		String[] status = null;
		// 验证许可证
		if (DYLX.equals(CallParameters.VERIFY_LICENSE)) {
			// 判断许可证是否过时
			Boolean verifyLicense = LicenseVerify_val.verifyLicense();
			if (verifyLicense) {
				status = new String[] { ResponseCode.OPR_SUCCESS, "许可证可用！", "", "" };
			} else {
				status = new String[] { ResponseCode.NO_RESULT, "POS Server许可证已失效！", "", "" };
			}
		}
		// 验证店铺
		else if (DYLX.equals(CallParameters.VALID_STORE)) {
			// 根据传入的值构建赋值对象
			JSONObject parseObject = JSONObject.parseObject(DATA);
			// 店铺编号
			String storeCode = parseObject.getString("storeCode");
			if (StringUtil.isNUllStr(storeCode)) {
				status = new String[] { ResponseCode.PARAM_ERROR, "参数错误！", "", "" };
			} else {
				// 查询店铺
				XfStore store = xfStoreService.getXfStoreByCode(storeCode);
				if (store != null && "1".equals(store.getIsInvalid())) {
					status = new String[] { ResponseCode.OPR_SUCCESS, "该店铺有效！", "", "" };
	
				} else {
					status = new String[] { ResponseCode.NO_RESULT, "无该店铺信息！", "", "" };
				}
			}
		}
		// 用户登录
		else if (DYLX.equals(CallParameters.XFSTAFF_LOGIN)) {
			Map<?, ?> jsonConvertMap = JsonMapper.jsonConvertMap(DATA);
			// 根据传入的值构建赋值对象
			// JSONObject parseObject = JSONObject.parseObject(DATA);
			// 员工编号
			String staffCode = jsonConvertMap.get("staffCode").toString();
			// 员工登录密码
			String password = jsonConvertMap.get("password").toString();
			String xfIssuestore=jsonConvertMap.get("xfIssuestore").toString();
			if (StringUtil.isNUllStr(staffCode) || StringUtil.isNUllStr(password)|| StringUtil.isNUllStr(xfIssuestore)) {
				status = new String[] { ResponseCode.PARAM_ERROR, "参数错误！", "", "" };
			} else {
				XfStaff xfStaff = new XfStaff();
				xfStaff.setXfStaffcode(staffCode);
				xfStaff.setXfPassword(password);
				// 收银员登录
				xfStaff = xfStaffService.getXfStaff(xfStaff);
				
				//判断员工是否已登录到收银机
				XfTillidState xfTillidState=xfTillidStateService.getXfTillidStateBystaffCode(staffCode);
				
				if(xfTillidState!=null){
					status = new String[] { ResponseCode.PARAM_ERROR, "该员工已登录到收银机！", "", "" };
				}else{
					if (xfStaff != null) {
						if(!xfStaff.getXfIssuestore().getXfStorecode().equals(xfIssuestore)){
							status = new String[] { ResponseCode.PARAM_ERROR, "该员工不属于该店铺！", "", "" };
						}else{
							status = new String[] { ResponseCode.OPR_SUCCESS, "操作成功！", "", "" };
						}
						remes = new BasePageResultVo();
						// 封装收银员
						Map<String, String> singleDataMap = getSingleDataMap();
						// 员工号(全系统唯一，编码规则：店铺号-人员流水号)
						singleDataMap.put("xfStaffcode", cleanObjNull(xfStaff.getXfStaffcode()));
						// 描述（姓名）
						singleDataMap.put("xfName", cleanObjNull(xfStaff.getXfName()));
						// 密码
						singleDataMap.put("xfPassword", cleanObjNull(xfStaff.getXfPassword()));
						// 关联店铺编码(外键：xf_store.xf_storecode)
						singleDataMap.put("xfIssuestore", cleanObjNull(
								xfStaff.getXfIssuestore() == null ? "" : xfStaff.getXfIssuestore().getXfStorecode()));
						//判断是否后台或者V60或V61有变化的时间戳
						remes.setRows(singleDataMap);
					} else {
						status = new String[] { ResponseCode.NO_RESULT, "无该员工！", "", "" };
					}
				}	
			}
		}
		// 平板更新状态
		else if (DYLX.equals(CallParameters.XFTILLID_STATE)) {
			remes=new BasePageResultVo();
			JSONObject tillidInfo=JSONObject.parseObject(DATA);
			String xfStorecode = tillidInfo.getString("xfStorecode");			// 关联的店铺编码
			String tillid = tillidInfo.getString("tillid");			// 收银机号
			String deviceInfo = " ";			// 设备信息（设备标识）
			if (StringUtil.isNUllStr(xfStorecode) || StringUtil.isNUllStr(tillid) ) {
				status = new String[] { ResponseCode.PARAM_ERROR, "参数错误！", "", "" };
			} else {
				// 商铺
				XfStore xfStore = xfStoreService.getXfStoreByStoreCode(xfStorecode);
				//收银机私有配置
				//PosConfigModel pos_config=posConfigService.getPosConfigModelByTillId(tillid);
				//收银机公有配置
				//PosCommonConfigModel pos_comfig=posCommConfigService.getPosComConfigByConid("1");
				// 商铺是否存在
				if (xfStore == null) {
					status = new String[] { ResponseCode.NO_RESULT, "无关联的商铺！", "", "" };
				} else {
					XfTillidState xfTillidState = new XfTillidState();// 设置商铺
					xfTillidState.setXfStorecode(xfStore);// 设置访问者ip
					if (xfTillidState.getIp() == null) {
						xfTillidState.setIp(IpUtil.getRemoteHost(request));
					}
					// 设置访问时间
					if (xfTillidState.getVisitTime() == null) {
						xfTillidState.setVisitTime(new Date());
					}
					xfTillidState.setXfStorecode(xfStore);
					xfTillidState.setDeviceInfo(deviceInfo);
					xfTillidState.setTillid(tillid);
					xfTillidState.setXfUpdate(false);
					// 如果在数据库中为空则新增否则修改
					try {
						xfTillidStateService.saveOrUpdate(xfTillidState);
					} catch (Exception e) {
						logger.info("收银机录入失败! 店铺号:"+xfStorecode+"收银机编号:"+tillid);
						throw new RuntimeException("收银机录入失败! 店铺号:"+xfStorecode+"收银机编号:"+tillid+"\n"+e.getMessage());
					}
					status = new String[] { ResponseCode.OPR_SUCCESS, "操作成功！", "", "" };
				}
			}
		}
		// 销售总结
		else if (DYLX.equals(CallParameters.SALES_SUMMARY)) {
			Map<String, String> jsonConvertMap = (Map<String, String>) JsonMapper.jsonConvertMap(DATA);
			// 店铺编码
			String xfStorecode = jsonConvertMap.get("xfStorecode");
			// 起始日期 时间戳 例如：2016-01-01的时间戳
			String beginDate = jsonConvertMap.get("beginDate");
			// 结束日期 时间戳 例如：2016-02-01的时间戳
			String endDate = jsonConvertMap.get("endDate");
			if (StringUtil.isNUllStr(xfStorecode) || StringUtil.isNUllStr(beginDate) || StringUtil.isNUllStr(endDate)) {
				status = new String[] { ResponseCode.PARAM_ERROR, "参数错误！", "", "" };
			} else {
				// 把日期时间戳转为日期
				Date bd = DateUtil.getDateByDateLong(Long.parseLong(beginDate), "yyyy-MM-dd 00:00:00"),
						ed = DateUtil.getDateByDateLong(Long.parseLong(endDate), "yyyy-MM-dd 00:00:00");
				// 封装参数
				Map<String, Object> map = new HashMap<>();
				map.put("storecode", xfStorecode);
				map.put("bd", bd);
				map.put("ed", ed);
				List<SalesSummary> list = salesSummaryService.selectSalesSummaryByXfStorecodeAndDate(map);
				if (list != null && list.size() > 0) {
					List<Map<String, Object>> maps = new ArrayList<>();
					Map<String, Object> map2;
					for (SalesSummary salesSummary : list) {
						List<SalesTender> salesTenders = salesTenderService
								.getSalesTenderListByTxdocno(salesSummary.getTxdocno());
						for (SalesTender salesTender : salesTenders) {
							map2 = new HashMap<>();
							// 支付金额
							map2.put("payamount", cleanObjNull(salesTender.getPayamount()));
							// 支付方式
							map2.put("tendercode", cleanObjNull(salesTender.getTendercode().getXfTendercode()));
							// 支付日期（返回的是时间戳）
							map2.put("txdate", cleanObjNull(salesSummary.getTxdate().getTime()));
							// 支付方式名称
							map2.put("xfTenderdesc", cleanObjNull(salesTender.getTendercode().getXfTenderdesc()));
							maps.add(map2);
						}
					}
					remes = new BasePageResultVo();
					remes.setRows(maps);
					remes.setTotal(maps.size());
					status = new String[] { ResponseCode.OPR_SUCCESS, "操作成功！", "", "" };
				} else {
					status = new String[] { ResponseCode.NO_RESULT, "无销售总结！", "", "" };
				}
			}

		}
		// 获取商场信息
		else if (DYLX.equals(CallParameters.GET_MALL)) {
			// 查询商场信息
			XfMall xfMall = xfMallService.selectMall();
			PropertiesUtil pUtil = new PropertiesUtil("systemConfig.properties");
			int port=Integer.parseInt(pUtil.readProperty("gdws.push.port"));
			JSONObject json = new JSONObject();
			json.put("port", port);
			if (xfMall != null) {
				remes = new BasePageResultVo();
				remes.setRows(xfMall);
				remes.setExtendData(json);
				status = new String[] { ResponseCode.OPR_SUCCESS, "操作成功！", "", "" };
			} else {
				status = new String[] { ResponseCode.NO_RESULT, "无商场信息！", "", "" };
			}
		}
		// 获取店铺信息
		else if (DYLX.equals(CallParameters.GET_STORE)) {
			Map<String, String> jsonConvertMap = (Map<String, String>) JsonMapper.jsonConvertMap(DATA);
			// 店铺编码
			String xfStorecode = jsonConvertMap.get("xfStorecode");
			if (StringUtil.isNUllStr(xfStorecode)) {
				status = new String[] { ResponseCode.PARAM_ERROR, "参数错误！", "", "" };
			} else {
				// 查询店铺
				XfStore store = xfStoreService.getXfStoreByStoreCode(xfStorecode);
				if (store != null) {
					remes = new BasePageResultVo();
					remes.setRows(store);
					status = new String[] { ResponseCode.OPR_SUCCESS, "操作成功！", "", "" };
				} else {
					status = new String[] { ResponseCode.NO_RESULT, "无商铺信息！", "", "" };
				}
			}
		}
		// 获取付款方式
		else if (DYLX.equals(CallParameters.GET_TENDER)) {
			// 查询付款方式
			List<XfTender> xfTender = xfTenderService.selectTenderList();
			if (xfTender != null && xfTender.size() > 0) {
				remes = new BasePageResultVo();
				remes.setRows(xfTender);
				remes.setTotal(xfTender.size());
				status = new String[] { ResponseCode.OPR_SUCCESS, "操作成功！", "", "" };
			} else {
				status = new String[] { ResponseCode.NO_RESULT, "无付款方式！", "", "" };
			}
		}
		// 获取员工数量
		else if (DYLX.equals(CallParameters.GET_STAFF_COUNT)) {
			Map<String, String> jsonConvertMap = (Map<String, String>) JsonMapper.jsonConvertMap(DATA);
			// 店铺编码
			String storecode = jsonConvertMap.get("storecode");
			if (StringUtil.isNUllStr(storecode)) {
				status = new String[] { ResponseCode.PARAM_ERROR, "参数错误！", "", "" };
			} else {
				// 员工数量
				int count = xfStaffService.getStaffCount(storecode);
				remes = new BasePageResultVo();
				remes.setTotal(count);
				status = new String[] { ResponseCode.OPR_SUCCESS, "操作成功！", "", "" };
			}
		}
		// 获取店铺下的商品
		else if (DYLX.equals(CallParameters.GET_XFITEM)) {
			Map<String, String> jsonConvertMap = (Map<String, String>) JsonMapper.jsonConvertMap(DATA);
			// 店铺编码
			String storeCode = jsonConvertMap.get("storeCode");
			if (StringUtil.isNUllStr(storeCode)) {
				status = new String[] { ResponseCode.PARAM_ERROR, "参数错误！", "", "" };
			} else {
				// 根据商铺编号获取商品信息集合
				List<XfItem> xfItems = xfItemService.selectListByStoreCode(storeCode);
				// 查询与之关联的店铺，也就是假设该店铺为中心店铺，看是否有子店铺，如果有则把子店铺的商品查询出来
				List<XfStoreCenter> storeCenters = xfStoreCenterService.getStoreCenterListByStoreCode(storeCode);
				if (storeCenters != null && storeCenters.size() > 0) {
					// 循环查商品
					List<XfItem> xfs;
					for (XfStoreCenter xfStoreCenter : storeCenters) {
						// 查询子店铺的商品
						xfs = xfItemService.selectListByStoreCode(xfStoreCenter.getXfStorecode().getXfStorecode());
						if (xfs != null && xfs.size() > 0) {
							for (XfItem xfItem : xfs) {
								// 放进中央店铺的商铺集合中
								xfItems.add(xfItem);
							}
						}
					}
				}
				if (xfItems != null && xfItems.size() > 0) {
					remes = new BasePageResultVo();
					remes.setRows(xfItems);
					remes.setTotal(xfItems.size());
					status = new String[] { ResponseCode.OPR_SUCCESS, "操作成功！", "", "" };
				} else {
					status = new String[] { ResponseCode.NO_RESULT, "无商品！", "", "" };
				}
			}
		}
		// 保存销售记录
		else if (DYLX.equals(CallParameters.SAVE_SALES_SUMMARY)) {
			try {
				// 解密
				DATA = URLDecoder.decode(DATA, "UTF-8");
				// 保存数据
				JSONObject parseObject = JSON.parseObject(DATA);
				String isUpload = parseObject.getString("isUpload");
				if("2".equals(isUpload)){
					status = new String[] { ResponseCode.OPR_SUCCESS, "操作成功！", "", "" };
				} else{
					Object map = salesSummaryService.saveSalesSummary(xfMallService, xfStoreService, xfStaffService,
							xfItemService, xfTenderService, salesItemService, salesTenderService, promotionStampService,DATA);
					if(map==null){
						status = new String[] { ResponseCode.BASE_ERROR, "保存数据异常！", "", "" };
					}else{
						JSONObject jsonFlag=(JSONObject) map;
						String flag=jsonFlag.getString("flag");
						String sflag=jsonFlag.getString("sflag");
						if("1".equals(flag)&&sflag!=null&&"1".equals(sflag)){
							remes = new BasePageResultVo();
							JSONObject jsonData=jsonFlag.getJSONObject("data");
							remes.setRows(com.gws.util.StringUtil.createGwIntegral(jsonData));
							remes.setTotal(1);
							JSONObject statusJson=jsonFlag.getJSONObject("status");
							if(statusJson==null)status = new String[] { ResponseCode.OPR_SUCCESS, "保存数据成功！", "", "" };
							else status = new String[] { statusJson.getString("code"), statusJson.getString("msg"), "", "" };
						}else status = new String[] { ResponseCode.OPR_SUCCESS, "保存数据正常获取积分失败！", "", "" };
					}
				}
			} catch (Exception e) {
				status = new String[] { ResponseCode.BASE_ERROR, "保存数据异常！", "", "" };
				e.printStackTrace();
			}
		}
		// 获取店铺下的员工信息集合
		else if (DYLX.equals(CallParameters.GET_XFSTAFF_BY_STORECODE)) {
			Map<String, String> jsonConvertMap = (Map<String, String>) JsonMapper.jsonConvertMap(DATA); // 店铺编码
			String xfStorecode = jsonConvertMap.get("xfStorecode");
			if (StringUtil.isNUllStr(xfStorecode)) {
				status = new String[] { ResponseCode.PARAM_ERROR, "参数错误！", "", "" };
			} else {
				List<XfStaff> staffs = xfStaffService.getXfStaffListByStaffCode(xfStorecode);
				if (staffs != null && staffs.size() > 0) {
					List<Map<String, String>> list = new ArrayList<>();
					Map<String, String> singleDataMap;
					for (XfStaff xfStaff : staffs) {
						singleDataMap = getSingleDataMap();
						// 员工编号
						singleDataMap.put("xfStaffcode", cleanObjNull(xfStaff.getXfStaffcode()));
						// 员工姓名
						singleDataMap.put("xfName", cleanObjNull(xfStaff.getXfName()));
						// 是否激活
						singleDataMap.put("enabled", cleanObjNull(xfStaff.getEnabled()));
						// 权限名称
						singleDataMap.put("staffRole", cleanObjNull(xfStaff.getStaffRole().getAuthority()));
						// 登录密码
						singleDataMap.put("password", cleanObjNull(xfStaff.getXfPassword()));
						// 添加
						list.add(singleDataMap);
					}
					remes = new BasePageResultVo();
					remes.setRows(list);
					remes.setTotal(list.size());
					status = new String[] { ResponseCode.OPR_SUCCESS, "操作成功！", "", "" };
				} else {
					status = new String[] { ResponseCode.NO_RESULT, "无员工信息！", "", "" };
				}
			}
			//获取销售数据
		} else if (DYLX.equals(CallParameters.GET_SALES_CONSUME)) {
			Map<String, String> jsonConvertMap = (Map<String, String>) JsonMapper.jsonConvertMap(DATA);
			Date date = new Date();
			String REQDATE = new SimpleDateFormat("yyyyMMdd").format(date);
			String REQTIME = new SimpleDateFormat("HHmmss").format(date);
			String miyao = "201511061724";
			String storeCode = "GYHTCFKF000349";
			String sign = REQDATE + REQTIME + storeCode + miyao;
			String SIGN = MD5.getMD5(sign);
			String USER = "TEST";
			String VIPCODE = "000000";
			String ISVIP = "0";
			String xmlData = "<?xml version=\"1.0\" encoding=\"utf-16\"?>"
					+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"
					+ " <soap:Body>" + "  <Consume xmlns=\"http://www.tech-trans.com.cn/\">" + "     <request>"
					+ "       <Header>" + "         <REQDATE>" + REQDATE + "</REQDATE>" + "         <REQTIME>" + REQTIME
					+ "</REQTIME>" + "         <SIGN>" + SIGN + "</SIGN>" + "        <USER>" + USER + "</USER>"
					+ "       </Header>" + "       <Data>" + "         <salestotal>" + "            <ExtensionData />"
					+ "            <dec_changeamount>"
					+ cleanObject2Number(jsonConvertMap.get("dec_changeamount")).equals("") + "</dec_changeamount>"
					+ "           <dec_discountamount>" + cleanObject2Number(jsonConvertMap.get("dec_discountamount"))
					+ "</dec_discountamount>" + "           <dec_netamount>"
					+ cleanObject2Number(jsonConvertMap.get("dec_netamount")) + "</dec_netamount>"
					+ "           <dec_netqty>" + cleanObject2Number(jsonConvertMap.get("dec_netqty")) + "</dec_netqty>"
					+ "           <dec_originalamount>" + cleanObject2Number(jsonConvertMap.get("dec_originalamount"))
					+ "</dec_originalamount>" + "           <dec_paidamount>"
					+ cleanObject2Number(jsonConvertMap.get("dec_paidamount")) + "</dec_paidamount>"
					+ "           <dec_sellingamount>" + cleanObject2Number(jsonConvertMap.get("dec_sellingamount"))
					+ "</dec_sellingamount>" + "            <dec_taxamount>"
					+ cleanObject2Number(jsonConvertMap.get("dec_taxamount")) + "</dec_taxamount>"
					+ "            <dt_reftxdate>" + cleanObject2Number(jsonConvertMap.get("dt_reftxdate"))
					+ "</dt_reftxdate>" + "            <dt_txdate>"
					+ cleanObject2Number(jsonConvertMap.get("dt_txdate")) + "</dt_txdate>" + "            <i_numofitem>"
					+ cleanObject2Number(jsonConvertMap.get("i_numofitem")) + "</i_numofitem>"
					+ "            <i_numoftender>" + cleanObject2Number(jsonConvertMap.get("i_numoftender"))
					+ "</i_numoftender>" + "            <i_purchasedependent>"
					+ cleanObject2Number(jsonConvertMap.get("i_purchasedependent")) + "</i_purchasedependent>"
					+ "            <s_cashier>" + cleanObject2Number(jsonConvertMap.get("s_cashier")) + "</s_cashier>"
					+ "           <s_clientcode>" + cleanObject2Number(jsonConvertMap.get("s_clientcode"))
					+ "</s_clientcode>" + "            <s_defaulttender>"
					+ cleanObject2Number(jsonConvertMap.get("s_defaulttender")) + "</s_defaulttender>"
					+ "            <s_demographiccode>" + cleanObject2Number(jsonConvertMap.get("s_demographiccode"))
					+ "</s_demographiccode>" + "            <s_demographicdata>"
					+ cleanObject2Number(jsonConvertMap.get("s_demographicdata")) + "</s_demographicdata>"
					+ "            <s_discountapprove>" + cleanObject2Number(jsonConvertMap.get("s_discountapprove"))
					+ "</s_discountapprove>" + "            <s_docno>"
					+ cleanObject2Number(jsonConvertMap.get("s_docno")) + "</s_docno>" + "            <s_extendparam>"
					+ cleanObject2Number(jsonConvertMap.get("s_extendparam")) + "</s_extendparam>"
					+ "            <s_purchasestaffcode>"
					+ cleanObject2Number(jsonConvertMap.get("s_purchasestaffcode")) + "</s_purchasestaffcode>"
					+ "            <s_refdocno>" + cleanObject2Number(jsonConvertMap.get("s_refdocno"))
					+ "</s_refdocno>" + "          <s_refstorecode>"
					+ cleanObject2Number(jsonConvertMap.get("s_refstorecode")) + "</s_refstorecode>"
					+ "           <s_reftillid>" + cleanObject2Number(jsonConvertMap.get("s_reftillid"))
					+ "</s_reftillid>" + "            <s_salesman>"
					+ cleanObject2Number(jsonConvertMap.get("s_salesman")) + "</s_salesman>"
					+ "            <s_storecode>" + storeCode + "</s_storecode>" + "            <s_tillid>"
					+ cleanObject2Number(jsonConvertMap.get("s_tillid")) + "</s_tillid>" + "            <s_vipcode>"
					+ VIPCODE + "</s_vipcode>" + "          </salestotal>" + "          <tender>"
					+ "           <txsalestender>" + "             <ExtensionData />" + "             <dec_baseamount>"
					+ cleanObject2Number(jsonConvertMap.get("dec_baseamount")) + "</dec_baseamount>"
					+ "             <dec_payamount>" + cleanObject2Number(jsonConvertMap.get("dec_payamount"))
					+ "</dec_payamount>" + "              <i_specificedtype>"
					+ cleanObject2Number(jsonConvertMap.get("i_specificedtype")) + "</i_specificedtype>"
					+ "              <s_cashier>" + cleanObject2Number(jsonConvertMap.get("s_cashier")) + "</s_cashier>"
					+ "              <s_extendparam>" + cleanObject2Number(jsonConvertMap.get("s_extendparam"))
					+ "</s_extendparam>" + "             <s_tendercode>"
					+ cleanObject2Number(jsonConvertMap.get("s_tendercode")) + "</s_tendercode>"
					+ "          </txsalestender>" + "        </tender>" + "         <items>"
					+ "           <txsalesitem>" + "             <ExtensionData />"
					+ "             <dec_discountamount>" + cleanObject2Number(jsonConvertMap.get("dec_discountamount"))
					+ "</dec_discountamount>" + "             <dec_exstk2sales>"
					+ cleanObject2Number(jsonConvertMap.get("dec_exstk2sales")) + "</dec_exstk2sales>"
					+ "            <dec_netamount>" + cleanObject2Number(jsonConvertMap.get("dec_netamount"))
					+ "</dec_netamount>" + "            <dec_originalprice>"
					+ cleanObject2Number(jsonConvertMap.get("dec_originalprice")) + "</dec_originalprice>"
					+ "            <dec_promotionless>" + cleanObject2Number(jsonConvertMap.get("dec_promotionless"))
					+ "</dec_promotionless>" + "            <dec_promotionuseqty>"
					+ cleanObject2Number(jsonConvertMap.get("dec_promotionuseqty")) + "</dec_promotionuseqty>"
					+ "             <dec_qty>" + cleanObject2Number(jsonConvertMap.get("dec_qty")) + "</dec_qty>"
					+ "             <dec_taxamount>" + cleanObject2Number(jsonConvertMap.get("dec_taxamount"))
					+ "</dec_taxamount>" + "             <dec_totaldiscountless>"
					+ cleanObject2Number(jsonConvertMap.get("dec_totaldiscountless")) + "</dec_totaldiscountless>"
					+ "              <s_cashier>" + cleanObject2Number(jsonConvertMap.get("s_cashier")) + "</s_cashier>"
					+ "              <s_clientcode>" + cleanObject2Number(jsonConvertMap.get("s_clientcode"))
					+ "</s_clientcode>" + "             <s_colorcode>"
					+ cleanObject2Number(jsonConvertMap.get("s_colorcode")) + "</s_colorcode>"
					+ "              <s_couponnumber>" + cleanObject2Number(jsonConvertMap.get("s_couponnumber"))
					+ "</s_couponnumber>" + "             <s_demographiccode>"
					+ cleanObject2Number(jsonConvertMap.get("s_demographiccode")) + "</s_demographiccode>"
					+ "              <s_demographicdata>" + cleanObject2Number(jsonConvertMap.get("s_demographicdata"))
					+ "</s_demographicdata>" + "             <s_discountapprove>"
					+ cleanObject2Number(jsonConvertMap.get("s_discountapprove")) + "</s_discountapprove>"
					+ "             <s_extendparam>" + cleanObject2Number(jsonConvertMap.get("s_extendparam"))
					+ "</s_extendparam>" + "              <s_isdeposit>"
					+ cleanObject2Number(jsonConvertMap.get("s_isdeposit")) + "</s_isdeposit>"
					+ "              <s_isnewitem>" + cleanObject2Number(jsonConvertMap.get("s_isnewitem"))
					+ "</s_isnewitem>" + "              <s_ispricealternate>"
					+ cleanObject2Number(jsonConvertMap.get("s_ispricealternate")) + "</s_ispricealternate>"
					+ "              <s_ispriceoverride>" + cleanObject2Number(jsonConvertMap.get("s_ispriceoverride"))
					+ "</s_ispriceoverride>" + "              <s_iswholesale>"
					+ cleanObject2Number(jsonConvertMap.get("s_iswholesale")) + "</s_iswholesale>"
					+ "              <s_itemlotnum>" + cleanObject2Number(jsonConvertMap.get("s_itemlotnum"))
					+ "</s_itemlotnum>" + "             <s_plucode>"
					+ cleanObject2Number(jsonConvertMap.get("s_plucode")) + "</s_plucode>"
					+ "              <s_priceapprove>" + cleanObject2Number(jsonConvertMap.get("s_priceapprove"))
					+ "</s_priceapprove>" + "              <s_promotionid>"
					+ cleanObject2Number(jsonConvertMap.get("s_promotionid")) + "</s_promotionid>"
					+ "              <s_salesitemremark>" + cleanObject2Number(jsonConvertMap.get("s_salesitemremark"))
					+ "</s_salesitemremark>" + "             <s_salesman>"
					+ cleanObject2Number(jsonConvertMap.get("s_salesman")) + "</s_salesman>"
					+ "             <s_sizecode>" + cleanObject2Number(jsonConvertMap.get("s_sizecode"))
					+ "</s_sizecode>" + "           </txsalesitem>" + "          </items>" + "          <isvip>" + ISVIP
					+ "</isvip>" + "        </Data>" + "      </request>" + "    </Consume>" + "  </soap:Body>"
					+ "</soap:Envelope>";
			System.out.println(xmlData);
			Map<String, String> map = JoinOLDMis.setConsumeInfo(xmlData);
			if (map != null) {
				// 添加
				remes = new BasePageResultVo();
				remes.setRows(map);
				remes.setTotal(1);
				status = new String[] { ResponseCode.OPR_SUCCESS, "操作成功！", "", "" };
			} else {
				status = new String[] { ResponseCode.NO_RESULT, "无销售信息！", "", "" };
			}
			//获取会员信息
		} else if (DYLX.equals(CallParameters.GET_VIP_INFO)) {
			try {
				JSONObject json=JSONObject.parseObject(DATA);
				String vipcode=json.getString("vipcode");
				String store=json.getString("store");	//店铺编号
				JSONObject jsonRes=(JSONObject) crmService.getVipInfoService(vipcode);
				if("10000".equals(jsonRes.getJSONObject("status").getString("code"))){
					remes = new BasePageResultVo();
					remes.setRows(com.gws.util.StringUtil.CreateVipInfoPojo(jsonRes));
					remes.setTotal(1);
					status = new String[] { ResponseCode.OPR_SUCCESS, "操作成功！", "", "" };
				}
				else status = new String[] { ResponseCode.NO_RESULT, "会员不存在", "", "" };
			} catch (Exception e) {
				status = new String[] { ResponseCode.NO_RESULT, "获取会员失败！"+e.getMessage(), "", "" };
			}
			
		}
		// 验证促销
		else if (DYLX.equals(CallParameters.VERIFICATION_PROMOTIONRULE)) {
			PropertiesUtil p=new PropertiesUtil("systemConfig.properties");
			String gdpstype=p.readProperty("gdws.gdps.type");
			
			if(gdpstype.equals("gdps")){
				Map<String, String> jsonConvertMap = (Map<String, String>) JsonMapper.jsonConvertMap(DATA);
				// 销售单号
				String txdocno = jsonConvertMap.get("txdocno");
				if (StringUtil.isNUllStr(txdocno)) {
					status = new String[] { ResponseCode.PARAM_ERROR, "参数错误！", "", "" };
				} else {
					// 根据销售单号查询销售
					SalesSummary salesSummary = salesSummaryService.getSSByTxd(txdocno);
					if (salesSummary == null) {
						status = new String[] { ResponseCode.NO_RESULT, "销售单不存在！", "", "" };
					} else {
						BigDecimal lowestConsumptionMoney = salesSummary.getNetamount();// 消费金额
						int isVipUse=0;	if(!StringUtils.isEmpty(salesSummary.getVipcode())) isVipUse=1;// 是否是会员
						
						List<SalesTender> sts = salesTenderService.getSTSByTxdocno(txdocno);
						
						Set<String> tenders=new HashSet<String>();
						for(SalesTender st :sts){
							tenders.add(st.getTendercode().getXfTendercode());
						}
						String paymenttender=new String();
						for(String tender:tenders){
							paymenttender=tender+"#"+paymenttender;
						}
						String storeCode = salesSummary.getStorecode().getXfStorecode();// 店铺号
						JSONObject result = new JSONObject();
						if(isVipUse==0){
							 result=(JSONObject) gdwsGdpsService.getGdpsCoupon("", lowestConsumptionMoney.toString(), paymenttender, storeCode, "A");//A都是先消费 后补票B - 先发卷C - 线下卷
						}else{
							 result = (JSONObject) gdwsGdpsService.getGdpsCoupon(salesSummary.getVipcode(), lowestConsumptionMoney.toString(), paymenttender, storeCode, "A");//A都是先消费 后补票B - 先发卷C - 线下卷
						}
						
						String gdpsCode=result.getJSONObject("status").getString("code");
						
						
						if("10000".equals(gdpsCode)){
							JSONArray gdpsCoupons = result.getJSONArray("data");
							
							Iterator<Object> gdpsCouponsIts=gdpsCoupons.iterator();
							List<Map<String, String>> maps = new ArrayList<>();
							Map<String, String> map;
							while(gdpsCouponsIts.hasNext()){
								JSONObject gdpsCouponsIt=(JSONObject) gdpsCouponsIts.next();
							/*	promotionStamp.setSalesSummary(salesSummary);
								promotionStamp.setStorecode(storeCode);
								promotionStamp.setId(gdpsCouponsIt.getString("code"));
								promotionStamp.setMinimum(gdpsCouponsIt.getString("minimum"));
								promotionStamp.setTime(gdpsCouponsIt.getString("time"));
								promotionStamp.setNote(gdpsCouponsIt.getString("note"));
								promotionStamp.setTitle(gdpsCouponsIt.getString("title"));
								promotionStamp.setValue(gdpsCouponsIt.getString("value"));*/
								map = new HashMap<>();
								map.put("id", gdpsCouponsIt.getString("code"));	// 劵编号
								map.put("title", "优惠券名:"+gdpsCouponsIt.getString("title"));	// 促销规则名称
								if(!StringUtil.isNUllStr(gdpsCouponsIt.getString("img"))){
									map.put("img",gdpsCouponsIt.getString("img") );
								}else{
									map.put("img","" );
								}
								
								// 促销劵使用有效期
								String time2=gdpsCouponsIt.getString("time");
								String begintime = time2.split("到")[0];
								String endtime = time2.split("到")[1];
								map.put("useDate", "使用有效日期:"+DateUtil.format(DateUtil.getDate(begintime, "yyyy-MM-dd"), "yyyy/MM/dd") + " - " + DateUtil.format(DateUtil.getDate(endtime, "yyyy-MM-dd"), "yyyy/MM/dd"));
								
								map.put("usetime", "使用有效时间:"+DateUtil.format(DateUtil.getDate(begintime, "yyyy-MM-dd HH:mm"), "HH:mm") + " - " + DateUtil.format(DateUtil.getDate(endtime, "yyyy-MM-dd HH:mm"), "HH:mm"));
								map.put("detailExplain",gdpsCouponsIt.getString("note") == null ? "无" : gdpsCouponsIt.getString("note"));// 描述说明
								maps.add(map);
								if (maps.size() > 0) {
									// 添加
									remes = new BasePageResultVo();
									remes.setRows(maps);
									remes.setTotal(maps.size());
									status = new String[] { ResponseCode.OPR_SUCCESS, "操作成功！", "", "" };
								} else {
									status = new String[] { ResponseCode.NO_RESULT, "不满足促销规则！", "", "" };
								}
							}
						}else{
							status = new String[] { ResponseCode.NO_RESULT, "不满足促销规则！", "", "" };
						}

						
					}
				}
			}else{
				Map<String, String> jsonConvertMap = (Map<String, String>) JsonMapper.jsonConvertMap(DATA);
				// 销售单号
				String txdocno = jsonConvertMap.get("txdocno");
				if (StringUtil.isNUllStr(txdocno)) {
					status = new String[] { ResponseCode.PARAM_ERROR, "参数错误！", "", "" };
				} else {
					// 根据销售单号查询销售
					SalesSummary salesSummary = salesSummaryService.getSSByTxd(txdocno);
					if (salesSummary == null) {
						status = new String[] { ResponseCode.NO_RESULT, "销售单不存在！", "", "" };
					} else {
						BigDecimal lowestConsumptionMoney = salesSummary.getNetamount();// 消费金额
						int isVipUse=0;	if(!StringUtils.isEmpty(salesSummary.getVipcode())) isVipUse=1;// 是否是会员
						String curY = DateUtil.format(new Date(), "yyyy-MM-dd");// 发放时间段
						String curD = DateUtil.format(new Date(), "HH:mm:ss");// 发放时间段
						String storeCode = salesSummary.getStorecode().getXfStorecode();// 店铺号
						List<PromotionRule> promotionRules = promotionRuleService.getValidPromotionRule(storeCode,curY,curD, lowestConsumptionMoney,isVipUse);
						if (promotionRules != null && promotionRules.size() > 0) {
							List<Map<String, String>> maps = new ArrayList<>();
							Map<String, String> map;

							for (PromotionRule promotionRule : promotionRules) {
								PromotionStamp promotionStamp = new PromotionStamp();// 添加一条发放劵
								promotionStamp.setPromotionRule(promotionRule);// 设置关联的促销规则
								promotionStamp.setSalesSummary(salesSummary);
								promotionStamp.setStorecode(storeCode);
								promotionStampService.saveOrUpdatePS(promotionStamp);// 设置关联的销售
								map = new HashMap<>();
								map.put("id", promotionStamp.getId().toString());	// 劵编号
								map.put("title", "优惠券名:"+promotionRule.getTitle());	// 促销规则名称
								// 促销劵使用有效期
								map.put("useDate", "使用有效期:"+DateUtil.format(promotionRule.getUseBeginDate(), "yyyy/MM/dd") + " - " + DateUtil.format(promotionRule.getUseEndDate(), "yyyy/MM/dd"));
								map.put("usetime", "使用有效时间:"+DateUtil.format(promotionRule.getUseBeginPeriod(), "HH:mm") + " - " + DateUtil.format(promotionRule.getUseEndPeriod(), "HH:mm"));
								map.put("detailExplain",promotionRule.getDetailExplain() == null ? "无" : promotionRule.getDetailExplain());// 描述说明
								maps.add(map);
							}
							if (maps.size() > 0) {
								// 添加
								remes = new BasePageResultVo();
								remes.setRows(maps);
								remes.setTotal(maps.size());
								status = new String[] { ResponseCode.OPR_SUCCESS, "操作成功！", "", "" };
							} else {
								status = new String[] { ResponseCode.NO_RESULT, "不满足促销规则！", "", "" };
							}
						} else {
							status = new String[] { ResponseCode.NO_RESULT, "不满足促销规则！", "", "" };
						}
					}
				}
			}
			
			
			
		}
		// 判断促销优惠劵
		else if (DYLX.equals(CallParameters.VERIFICATION_PROMOTIONSTAMP)) {
			
			
			PropertiesUtil p=new PropertiesUtil("systemConfig.properties");
			String gdpstype=p.readProperty("gdws.gdps.type");
			
			if(gdpstype.equals("gdps")){
				
				Map<String, String> jsonConvertMap = (Map<String, String>) JsonMapper.jsonConvertMap(DATA);
				// 促销劵码
				String id = jsonConvertMap.get("id");
				// 现在时刻所在的店铺号
				String storeCode = jsonConvertMap.get("storeCode");
				String vipCode=jsonConvertMap.get("vipCode");
				String money=jsonConvertMap.get("money");
				
				if (StringUtil.isNUllStr(id) || StringUtil.isNUllStr(storeCode)||StringUtil.isNUllStr(money)) {
					status = new String[] { ResponseCode.PARAM_ERROR, "参数错误！", "", "" };
				} else {
					
					
					JSONObject result = new JSONObject();
					
					if (StringUtil.isNUllStr(vipCode)) {
						result = (JSONObject) gdwsGdpsService.getGdpsCheckCoupon(" ", money, id, storeCode);
					} else {
						result = (JSONObject) gdwsGdpsService.getGdpsCheckCoupon(vipCode, money, id, storeCode);
					}
					System.out.println(result);
					String gdpsCode=result.getJSONObject("status").getString("code");
					if("10000".equals(gdpsCode)){
						String money2=result.getJSONObject("data").getString("value");
						Map<String, String> map = new HashMap<>();
						map.put("offsetMoney", money2);
						// 添加
						remes = new BasePageResultVo();
						remes.setRows(map);
						remes.setTotal(1);
						status = new String[] { ResponseCode.OPR_SUCCESS, "操作成功！", "", "" };
					}else{
						String msg=result.getJSONObject("status").getString("msg");
						status = new String[] { ResponseCode.NO_RESULT, msg, "", "" };
					}
					
					
					// 促销劵
				/*	PromotionStamp promotionStamp = promotionStampService.getPSById(id);
					if (promotionStamp != null) {
						if (!promotionStamp.getIsUse()) {
							// 关联的促销规则
							PromotionRule promotionRule = promotionStamp.getPromotionRule();
							// 促销类型 1操作有 2只能用劵 3停用
							String type = promotionRule.getType();
							if ("3".equals(type)) {
								status = new String[] { ResponseCode.NO_RESULT, "优惠劵无效！", "", "" };
							} else {
								// 兑换范围
								List<PromotionUseRange> useRanges = promotionRule.getUseRanges();
								for (int i = 0, len = useRanges.size(); i < len; i++) {
									// 判断是否满足兑换的店铺
									if (storeCode.equals(useRanges.get(i).getXfStore().getXfStorecode())) {
										DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
										// 现在时间
										Date date = new Date();
										
										// 标识兑换时是否在有效期内
										Boolean flag = false;
										// 判断使用的有效天数是否为空
										Integer useValidNum = promotionRule.getUseValidNum();
										// 如果有效天数不为空则比对现在时间是否满足有效天数
										if (useValidNum != null) {
											// 验证有效日期到现在的天数
											long distanceDays = DateUtil.getDistanceDays(date,
													promotionStamp.getCreationDate());
											if (distanceDays >= 0 && distanceDays <= useValidNum) {
												flag = true;
											}
										} else {
											// 使用有效日期
											Date useBeginDate = promotionRule.getUseBeginDate();
											// 使用有效结束日期
											Date useEndDate = promotionRule.getUseEndDate();
											
											if (df.parse(df.format(date)).compareTo(df.parse(df.format(useBeginDate))) != -1 && 
												df.parse(df.format(useEndDate)).compareTo(df.parse(df.format(date))) != -1) {
												
												flag = true;
											}
										}
										if (flag) {
											Time time = DateUtil.getSqlTime(DateUtil.format(date, "HH:mm:ss"), "HH:mm:ss");
											if (DateUtil.compareTime(time, promotionRule.getUseBeginPeriod()) != -1
													&& DateUtil.compareTime(promotionRule.getUseEndPeriod(), time) != -1) {
												Map<String, String> map = new HashMap<>();
												map.put("offsetMoney", promotionRule.getOffsetMoney().toString());
												// 添加
												remes = new BasePageResultVo();
												remes.setRows(map);
												remes.setTotal(1);
												status = new String[] { ResponseCode.OPR_SUCCESS, "操作成功！", "", "" };
												break;
											} else {
												status = new String[] { ResponseCode.NO_RESULT, "优惠劵无效！", "", "" };
											}
										} else {
											status = new String[] { ResponseCode.NO_RESULT, "优惠劵无效！", "", "" };
										}
										break;
									} else if (i == len - 1) {
										status = new String[] { ResponseCode.NO_RESULT, "优惠劵无效！", "", "" };
									}
								}
							}
						} else {
							status = new String[] { ResponseCode.NO_RESULT, "优惠劵无效！", "", "" };
						}
					} else {
						status = new String[] { ResponseCode.NO_RESULT, "优惠劵无效！", "", "" };
					} */
				}
				
			}else{
				Map<String, String> jsonConvertMap = (Map<String, String>) JsonMapper.jsonConvertMap(DATA);
				// 促销劵码
				String id = jsonConvertMap.get("id");
				// 现在时刻所在的店铺号
				String storeCode = jsonConvertMap.get("storeCode");
				if (StringUtil.isNUllStr(id) || StringUtil.isNUllStr(storeCode)) {
					status = new String[] { ResponseCode.PARAM_ERROR, "参数错误！", "", "" };
				} else {
					// 促销劵
					PromotionStamp promotionStamp = promotionStampService.getPSById(id);
					if (promotionStamp != null) {
						if (!promotionStamp.getIsUse()) {
							// 关联的促销规则
							PromotionRule promotionRule = promotionStamp.getPromotionRule();
							// 促销类型 1操作有 2只能用劵 3停用
							String type = promotionRule.getType();
							if ("3".equals(type)) {
								status = new String[] { ResponseCode.NO_RESULT, "优惠劵无效！", "", "" };
							} else {
								// 兑换范围
								List<PromotionUseRange> useRanges = promotionRule.getUseRanges();
								for (int i = 0, len = useRanges.size(); i < len; i++) {
									// 判断是否满足兑换的店铺
									if (storeCode.equals(useRanges.get(i).getXfStore().getXfStorecode())) {
										DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
										// 现在时间
										Date date = new Date();
										
										// 标识兑换时是否在有效期内
										Boolean flag = false;
										// 判断使用的有效天数是否为空
										Integer useValidNum = promotionRule.getUseValidNum();
										// 如果有效天数不为空则比对现在时间是否满足有效天数
										if (useValidNum != null) {
											// 验证有效日期到现在的天数
											long distanceDays = DateUtil.getDistanceDays(date,
													promotionStamp.getCreationDate());
											if (distanceDays >= 0 && distanceDays <= useValidNum) {
												flag = true;
											}
										} else {
											// 使用有效日期
											Date useBeginDate = promotionRule.getUseBeginDate();
											// 使用有效结束日期
											Date useEndDate = promotionRule.getUseEndDate();
											
											if (df.parse(df.format(date)).compareTo(df.parse(df.format(useBeginDate))) != -1 && 
												df.parse(df.format(useEndDate)).compareTo(df.parse(df.format(date))) != -1) {
												
												flag = true;
											}
										}
										if (flag) {
											Time time = DateUtil.getSqlTime(DateUtil.format(date, "HH:mm:ss"), "HH:mm:ss");
											if (DateUtil.compareTime(time, promotionRule.getUseBeginPeriod()) != -1
													&& DateUtil.compareTime(promotionRule.getUseEndPeriod(), time) != -1) {
												Map<String, String> map = new HashMap<>();
												map.put("offsetMoney", promotionRule.getOffsetMoney().toString());
												// 添加
												remes = new BasePageResultVo();
												remes.setRows(map);
												remes.setTotal(1);
												status = new String[] { ResponseCode.OPR_SUCCESS, "操作成功！", "", "" };
												break;
											} else {
												status = new String[] { ResponseCode.NO_RESULT, "优惠劵无效！", "", "" };
											}
										} else {
											status = new String[] { ResponseCode.NO_RESULT, "优惠劵无效！", "", "" };
										}
										break;
									} else if (i == len - 1) {
										status = new String[] { ResponseCode.NO_RESULT, "优惠劵无效！", "", "" };
									}
								}
							}
						} else {
							status = new String[] { ResponseCode.NO_RESULT, "优惠劵无效！", "", "" };
						}
					} else {
						status = new String[] { ResponseCode.NO_RESULT, "优惠劵无效！", "", "" };
					}
				}
			}
			
			
			
			
		}
		// 翼支付 统一下单
		else if (DYLX.equals(CallParameters.BESTOAY_PLACEORDER)) {
			try {
				DATA = URLDecoder.decode(DATA, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Map<String, String> jsonConvertMap = (Map<String, String>) JsonMapper.jsonConvertMap(DATA);
			// 条形码号
			String barcode = jsonConvertMap.get("barcode");
			// 订单总金额
			String orderAmt = jsonConvertMap.get("orderAmt");
			// 产品金额 单位：分
			String productAmt = jsonConvertMap.get("productAmt");
			// 附加金额 单位：分
			String attachAmt = jsonConvertMap.get("attachAmt");
			// 商品名称多个商铺以“、”隔开
			String xfItemNames = jsonConvertMap.get("xfItemNames");
			// 店铺号
			String storeCode = jsonConvertMap.get("storeCode");
			if (StringUtil.isNUllStr(barcode) || StringUtil.isNUllStr(orderAmt) || StringUtil.isNUllStr(productAmt)
					|| StringUtil.isNUllStr(attachAmt) || StringUtil.isNUllStr(xfItemNames)
					|| StringUtil.isNUllStr(storeCode)) {
				status = new String[] { ResponseCode.PARAM_ERROR, "参数错误！", "", "" };
			} else {
				// 店铺
				XfStore xfStore = xfStoreService.getXfStoreByStoreCode(storeCode);
				if (xfStore != null) {
					// 翼支付实体类对象
					Bestoay bestoay = new Bestoay();
					// 客户端条形码
					bestoay.setBarcode(new BigDecimal(barcode));
					// 订单总金额
					bestoay.setOrderAmt(new BigDecimal(orderAmt));
					// 产品金额
					bestoay.setProductAmt(new BigDecimal(productAmt));
					// 附加金额
					bestoay.setAttachAmt(new BigDecimal(attachAmt));
					// 商品名称
					bestoay.setXfItemNames(xfItemNames);
					// 商铺号
					bestoay.setXfStoreCode(storeCode);
					// 保存
					bestoayService.saveOrUpdateBestoay(bestoay);

					String plo = BestoayHandle.placeOrder(bestoay.getOrderNo(), bestoay.getOrderReqNo(), barcode,
							orderAmt, productAmt, attachAmt, xfItemNames, storeCode, null);
					JSONObject parseObject;
					if (plo != null && "true".equals((parseObject = JSON.parseObject(plo)).getString("success"))) {
						String result = parseObject.getString("result");
						jsonConvertMap = (Map<String, String>) JsonMapper.jsonConvertMap(result);
						// 商户总订单号
						String orderNo = jsonConvertMap.get("orderNo");
						// 商户总订单请求流水号
						String orderReqNo = jsonConvertMap.get("orderReqNo");
						// 预留字段
						String orderDate = jsonConvertMap.get("orderDate");
						// 翼支付交易号
						String ourTransNo = jsonConvertMap.get("ourTransNo");
						// 订单金额
						String transAmt = jsonConvertMap.get("transAmt");
						// 交易状态
						String transStatus = jsonConvertMap.get("transStatus");
						// 签名方式
						String encodeType = jsonConvertMap.get("encodeType");
						// md5签名
						String signPo = BestoayHandle.getSign(orderNo, orderReqNo, orderDate, ourTransNo, transAmt,
								transStatus, encodeType);
						// 验证签名
						if (signPo.equalsIgnoreCase(jsonConvertMap.get("sign"))) {
							// 翼支付交易号
							bestoay.setOurTransNo(jsonConvertMap.get("ourTransNo"));
							// 交易状态
							bestoay.setTransStatus(transStatus.charAt(0));
							// 更新翼支付
							bestoayService.saveOrUpdateBestoay(bestoay);
							if ("A".equals(transStatus)) {
								status = new String[] { ResponseCode.OPR_SUCCESS, "支付中！", "", "" };
							} else if ("B".equals(transStatus)) {
								Map<String, String> map = new HashMap<>();
								// 签名
								String md5Str = barcode + orderAmt + productAmt + attachAmt + storeCode;
								map.put("sign", MD5.getMD5(md5Str));
								// 添加
								remes = new BasePageResultVo();
								remes.setRows(map);
								remes.setTotal(1);
								status = new String[] { ResponseCode.OPR_SUCCESS, "支付成功！", "", "" };
							} else {
								status = new String[] { ResponseCode.NO_RESULT, "支付失败！", "", "" };
							}
						} else {
							status = new String[] { ResponseCode.NO_RESULT, "翼支付失败！", "", "" };
						}
					} else {
						status = new String[] { ResponseCode.NO_RESULT, "翼支付失败！", "", "" };
					}
				} else {
					status = new String[] { ResponseCode.NO_RESULT, "商铺或者商品无效！", "", "" };
				}
			}
		}
		// 翼支付查询
		else if (DYLX.equals(CallParameters.BESTOAY_QUERYORDER)) {
			Map<String, String> jsonConvertMap = (Map<String, String>) JsonMapper.jsonConvertMap(DATA);
			// 支付码（条形码）
			String barcode = jsonConvertMap.get("barcode");
			if (StringUtil.isNUllStr(barcode)) {
				status = new String[] { ResponseCode.PARAM_ERROR, "参数错误！", "", "" };
			} else {
				// 根据条形码查询翼支付信息
				Bestoay bestoay = bestoayService.getBestoayByBarcode(barcode);
				if (bestoay != null) {
					// 退款标识
					String refundFlag = bestoay.getRefundFlag();
					if (!"0".equals(refundFlag)) {
						if ("1".equals(refundFlag)) {
							refundFlag = "已退款";
						} else if ("2".equals(refundFlag)) {
							refundFlag = "部分退款";
						} else {
							refundFlag = "已冲正";
						}
						status = new String[] { ResponseCode.NO_RESULT, refundFlag, "", "" };
					} else {
						String queryOrder = BestoayHandle.queryOrder(bestoay.getOrderNo(), bestoay.getOrderReqNo(),
								DateUtil.format(bestoay.getCreateDate(), "yyyyMMddhhmmss"));
						JSONObject parseObject;
						if (queryOrder != null
								&& "true".equals((parseObject = JSON.parseObject(queryOrder)).getString("success"))) {
							String result = parseObject.getString("result");
							jsonConvertMap = (Map<String, String>) JsonMapper.jsonConvertMap(result);
							// 商户总订单号
							String orderNo = jsonConvertMap.get("orderNo");
							// 商户总订单请求流水号
							String orderReqNo = jsonConvertMap.get("orderReqNo");
							// 预留字段
							String orderDate = jsonConvertMap.get("orderDate");
							// 翼支付交易号
							String ourTransNo = jsonConvertMap.get("ourTransNo");
							// 订单金额
							String transAmt = jsonConvertMap.get("transAmt");
							// 交易状态
							String transStatus = jsonConvertMap.get("transStatus");
							// 签名方式
							String encodeType = jsonConvertMap.get("encodeType");
							// 退款标识
							refundFlag = jsonConvertMap.get("refundFlag");
							// 客户条码消费时的支付手机号
							String customerId = jsonConvertMap.get("customerId");
							// md5签名
							String signPo = BestoayHandle.getSign(orderNo, orderReqNo, orderDate, ourTransNo, transAmt,
									transStatus, encodeType);
							// 验证签名
							if (signPo.equalsIgnoreCase(jsonConvertMap.get("sign"))) {
								// 交易状态
								bestoay.setTransStatus(transStatus.charAt(0));
								// 退款状态
								bestoay.setRefundFlag(refundFlag);
								// 客户手机号
								bestoay.setCustomerId(customerId);
								// A：请求（支付中） B：成功（支付成功）C：失败 G：订单作废
								if ("A".equals(transStatus)) {
									status = new String[] { ResponseCode.NO_RESULT, "支付中！", "", "" };
								} else if ("B".equals(transStatus)) {
									status = new String[] { ResponseCode.OPR_SUCCESS, "支付成功！", "", "" };
								} else if ("C".equals(transStatus)) {
									status = new String[] { ResponseCode.NO_RESULT, "支付失败！", "", "" };
								} else {
									status = new String[] { ResponseCode.NO_RESULT, "订单作废！", "", "" };
								}
								// 保存或者更新
								bestoayService.saveOrUpdateBestoay(bestoay);
							} else {
								status = new String[] { ResponseCode.NO_RESULT, "查询失败！", "", "" };
							}
						} else {
							status = new String[] { ResponseCode.NO_RESULT, "查询失败！", "", "" };
						}
					}
				} else {
					status = new String[] { ResponseCode.NO_RESULT, "无翼支付信息！", "", "" };
				}
			}
		}
		// 翼支付退款
		else if (DYLX.equals(CallParameters.BESTOAY_COMMONREFUND)) {
			Map<String, String> jsonConvertMap = (Map<String, String>) JsonMapper.jsonConvertMap(DATA);
			// 类型 1销售编号 2翼支付码
			String type = jsonConvertMap.get("type");
			// 编号
			String number = jsonConvertMap.get("numbers");
			if (StringUtil.isNUllStr(type) || StringUtil.isNUllStr(number)) {
				status = new String[] { ResponseCode.PARAM_ERROR, "参数错误！", "", "" };
			} else {
				Bestoay bestoay = null;
				// 销售
				if ("1".equals(type)) {
					// 销售信息
					SalesSummary salesSummary = salesSummaryService.getSSByTxd(number);
					if (salesSummary != null && !StringUtil.isNUllStr(salesSummary.getBarcode())) {
						// 翼支付
						bestoay = bestoayService.getBestoayByBarcode(salesSummary.getBarcode());
					} else {
						status = new String[] { ResponseCode.NO_RESULT, "退款失败！", "", "" };
					}
				}
				// 翼支付码
				else {
					bestoay = bestoayService.getBestoayByBarcode(number);
				}
				if (bestoay != null) {
					// 原扣款订单号
					String oldOrderNo = bestoay.getOrderNo();
					// 原订单请求支付流水号
					String oldOrderReqNo = bestoay.getOrderReqNo();
					// 退款流水号
					String refundReqNo = bestoay.getOurTransNo();
					// 退款交易金额 单位为分，小于等于原订单金额
					String transAmt = bestoay.getProductAmt().toPlainString();
					// 退款操作
					String commonRefund = BestoayHandle.commonRefund(oldOrderNo, oldOrderReqNo, refundReqNo,
							transAmt.substring(0, transAmt.lastIndexOf(".")), null);
					JSONObject parseObject = null;
					if (commonRefund != null
							&& "true".equals((parseObject = JSON.parseObject(commonRefund)).getString("success"))) {
						String result = parseObject.getString("result");
						jsonConvertMap = (Map<String, String>) JsonMapper.jsonConvertMap(result);
						status = new String[] { ResponseCode.OPR_SUCCESS, "退款成功！", "", "" };

					} else {
						String errorMsg = null;
						if (parseObject != null) {
							errorMsg = parseObject.getString("errorMsg");
						}
						status = new String[] { ResponseCode.NO_RESULT, errorMsg == null ? "退款失败！" : errorMsg, "", "" };
					}
				} else {
					status = new String[] { ResponseCode.NO_RESULT, "退款失败！", "", "" };
				}
			}
		}
		// 获取打印小票设置信息
		else if (DYLX.equals(CallParameters.GET_PRINTRECEIPT)) {
			Map<String, Object> map = new HashMap<>();
			// 获取打印数量
			Sets sets = setService.getSets("printReceiptCount");
			map.put("printReceiptCount", sets == null ? 1 : sets.getValue());
			// 小尾巴
			sets = setService.getSets("printReceiptTail");
			map.put("printReceiptTail", sets != null ? sets.getValue() : "");
			// 添加
			remes = new BasePageResultVo();
			remes.setRows(map);
			remes.setTotal(1);
			status = new String[] { ResponseCode.OPR_SUCCESS, "获取成功！", "", "" };
		}
		// 补齐销售信息（这里指补齐付款方式）
		else if (DYLX.equals(CallParameters.SALESSUMMARY_SALESTEND_COMPLETE)) {
			Map<String, Object> parseObject = (Map<String, Object>) JsonMapper.jsonConvertMap(DATA);
			// 销售单号
			String txDocno = parseObject.get("txDocno").toString();
			// 付款方式
			List<SalesTender> salesTenders = (List<SalesTender>) parseObject.get("salesTenders");
			// 销售
			SalesSummary salesSummary;
			if (StringUtil.isNUllStr(txDocno) || salesTenders == null
					|| (salesSummary = salesSummaryService.getSSByTxd(txDocno)) == null) {
				status = new String[] { ResponseCode.PARAM_ERROR, "参数错误！", "", "" };
			} else {
				for (SalesTender salesTender : salesTenders) {
					// 销售单号
					salesTender.setTxdocno(salesSummary);
					// 支付方式
					salesTender
							.setTendercode(xfTenderService.getXfTenderByCode(salesTender.getTendercode().toString()));
					// 保存支付方式
					salesTenderService.saveSalesTender(salesTender);
				}
				// 原销售单号
				String originalTxdocno = salesSummary.getOriginalTxdocno();
				if (!StringUtil.isNUllStr(originalTxdocno)) {
					// 原付款方式集合
					List<SalesTender> otst = salesTenderService.getSTSByTxdocno(originalTxdocno);
					// 现在付款方式集合
					List<SalesTender> txst = salesTenderService.getSTSByTxdocno(txDocno);
					if (otst != null && txst != null && otst.size() == salesTenders.size() + txst.size()) {
						// 设置为上传
						salesSummary.setIsUpload("1");
						// 更新
						salesSummaryService.updateSalesSummaryState(salesSummary);
					}
				}
				Map map = null;
				// 不是退货并且有会员号
				if (StringUtil.isNUllStr(originalTxdocno) && salesSummary.getVipcode() != null) {
					// 商品集合
					List<SalesItem> sis = salesItemService.getXfItemByDocCode(txDocno);
					// 支付方式
					List<SalesTender> sts = salesTenderService.getSTSByTxdocno(txDocno);

					// 调用接口兑换会员积分
					Date date = new Date();
					String REQDATE = new SimpleDateFormat("yyyyMMdd").format(date);
					String REQTIME = new SimpleDateFormat("HHmmss").format(date);
					String storeCode = salesSummary.getStorecode().getXfStorecode();
					String sign = REQDATE + REQTIME + storeCode + crmMiyao;
					String SIGN = MD5.getMD5(sign);
					String VIPCODE = salesSummary.getVipcode();
					String ISVIP = "1";

					// 商品集合
					String items = "";
					for (SalesItem salesItem : sis) {
						items += "<txsalesitem>" + "<ExtensionData />" + "<dec_discountamount></dec_discountamount>"
								+ " <dec_exstk2sales></dec_exstk2sales>" + " <dec_netamount></dec_netamount>"
								+ " <dec_originalprice></dec_originalprice>"
								+ " <dec_promotionless></dec_promotionless>"
								+ " <dec_promotionuseqty></dec_promotionuseqty>" + " <dec_qty>"
								+ salesSummary.getNetqty() + "</dec_qty>" + "  <dec_taxamount></dec_taxamount>"
								+ "  <dec_totaldiscountless></dec_totaldiscountless>" + "  <s_cashier />"
								+ "<s_clientcode />" + " <s_colorcode />" + "  <s_couponnumber />"
								+ "  <s_demographiccode />" + "  <s_demographicdata />" + "   <s_discountapprove />"
								+ "   <s_extendparam />" + "   <s_isdeposit />" + "   <s_isnewitem />"
								+ "   <s_ispricealternate />" + "   <s_ispriceoverride />" + "   <s_iswholesale />"
								+ "   <s_itemlotnum />" + "   <s_plucode>" + salesItem.getPlu().getXfPlu()
								+ "</s_plucode>" + "   <s_priceapprove />" + "   <s_promotionid />"
								+ "  <s_salesitemremark />" + "  <s_salesman />" + "  <s_sizecode />"
								+ "</txsalesitem>";
					}
					// 支付方式集合
					String tenderStr = "";
					for (SalesTender salesTender : sts) {
						tenderStr += "                  <txsalestender>" + "                      <ExtensionData />"
								+ "                     <dec_baseamount>" + salesSummary.getNetamount()
								+ "</dec_baseamount>" + "                       <dec_payamount>"
								+ salesSummary.getNetamount() + "</dec_payamount>"
								+ "                      <i_specificedtype>0</i_specificedtype>"
								+ "                     <s_cashier />" + "                     <s_extendparam />"
								+ "                     <s_tendercode>" + salesTender.getTendercode()
								+ "</s_tendercode>" + "                 </txsalestender>";
					}
					String xmlData = "";
					String txDateStr = "";
					try {
						txDateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
					} catch (Exception e) {
						e.printStackTrace();
					}
					xmlData = "<?xml version=\"1.0\" encoding=\"utf-16\"?>"
							+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"
							+ " <soap:Body>" + "     <Consume xmlns=\"http://www.tech-trans.com.cn/\">"
							+ "          <request>" + "       <Header>" + "         <REQDATE>" + REQDATE + "</REQDATE>"
							+ "         <REQTIME>" + REQTIME + "</REQTIME>" + "         <SIGN>" + SIGN + "</SIGN>"
							+ "        <USER>" + crmUser + "</USER>" + "       </Header>" + "             <Data>"
							+ "         <salestotal>" + "            <ExtensionData />"
							+ "            <dec_changeamount />" + "           <dec_discountamount />"
							+ "           <dec_netamount>" + salesSummary.getNetamount() + "</dec_netamount>"
							+ "           <dec_netqty />" + "           <dec_originalamount />"
							+ "           <dec_paidamount />" + "           <dec_sellingamount />"
							+ "            <dec_taxamount />" + "            <dt_reftxdate />"
							+ "            <dt_txdate>" + cleanObject2Number(txDateStr) + "</dt_txdate>"
							+ "            <i_numofitem />" + "            <i_numoftender />"
							+ "            <i_purchasedependent />" + "            <s_cashier>"
							+ salesSummary.getSalesman() + "</s_cashier>" + "           <s_clientcode />"
							+ "            <s_defaulttender />" + "            <s_demographiccode />"
							+ "            <s_demographicdata />" + "            <s_discountapprove />"
							+ "            <s_docno>" + txDocno + "</s_docno>" + "            <s_extendparam />"
							+ "            <s_purchasestaffcode />" + "            <s_refdocno />"
							+ "          <s_refstorecode />" + "           <s_reftillid />"
							+ "            <s_salesman />" + "            <s_storecode>" + storeCode + "</s_storecode>"
							+ "            <s_tillid>" + salesSummary.getTillid() + "</s_tillid>"
							+ "            <s_vipcode>" + cleanObject2Number(VIPCODE) + "</s_vipcode>"
							+ "          </salestotal>" + "              <tender>" + tenderStr
							+ "             </tender>" + "             <items>" + items + "          </items>"
							+ "           <isvip>" + ISVIP + "</isvip>" + "        </Data>" + "     </request>"
							+ " </Consume>" + "</soap:Body>" + "</soap:Envelope>";
					map = JoinOLDMis.setConsumeInfo(xmlData);
				}
				remes = new BasePageResultVo();
				remes.setRows(map == null ? "" : map);
				remes.setTotal(1);
				status = new String[] { ResponseCode.OPR_SUCCESS, "操作成功！", "", "" };
			}
		}
		// 退货权限
		else if (DYLX.equals(CallParameters.RETURN_GOODS_AUTHORITY)) {

			
			Map<String, String> jsonConvertMap = (Map<String, String>) JsonMapper.jsonConvertMap(DATA);
			// 员工编号
			String xfStaffcode = jsonConvertMap.get("xfStaffcode");
			// 员工密码
			String xfPassword = jsonConvertMap.get("xfPassword");
			//店铺编号  上传字段未知
			String xfStorecode= jsonConvertMap.get("xfStorecode");
			if (StringUtil.isNUllStr(xfStaffcode) || StringUtil.isNUllStr(xfPassword)||StringUtil.isNUllStr(xfStorecode)) {
				status = new String[] { ResponseCode.PARAM_ERROR, "参数错误！", "", "" };
			} else {
				//获取员工信息
				XfStaff xfStaff=xfStaffService.getXfStaffByStaffCode(xfStaffcode);
				boolean superAdmin=false;boolean store=false;
				/*if("ROLE_MALL_ADMIN".equals(xfStaff.getStaffRole().getAuthority())) {
					superAdmin=true;
				}*/
				if(xfStaff.getIsReturnGoodsAuth() == 1 ){
					superAdmin=true;
				}

				//判断上传密码与员工编号属于同一个人
				Boolean pwd=xfPassword.equals(xfStaff.getXfPassword());
				//员工是否激活
				Boolean enable=xfStaff.getEnabled();
				//判断员工所在店铺的编号是否和上传店铺编号一致
				 if(!superAdmin) store=xfStaff.getXfIssuestore().getXfStorecode().equals(xfStorecode);
				
//				ReturnGoodsAuthority returnGoodsAuthority = returnGoodsAuthorityService.getRGAByXfStaffCode(xfStaffcode);
				//获取退货权
				// 判断退货权限不为空并且密码相等
				if (pwd&& enable&&superAdmin) {
					status = new String[] { ResponseCode.OPR_SUCCESS, "授权成功！", "", "" };
				}
				/*else if (pwd&& enable&&store) {
					status = new String[] { ResponseCode.OPR_SUCCESS, "授权成功！", "", "" };
				}*/ else {
					status = new String[] { ResponseCode.NO_RESULT, "授权失败！", "", "" };
				}
			}
		
		
		}
		// 获取老的销售数据
		else if (DYLX.equals(CallParameters.GET_OLD_SALESSUMMARY)) {
			Map<String, String> map = (Map<String, String>) JsonMapper.jsonConvertMap(DATA);
			// 获取销售单号
			String txdocno = map.get("txDocno");
			if (StringUtil.isNUllStr(txdocno)) {
				status = new String[] { ResponseCode.PARAM_ERROR, "参数错误！", "", "" };
			} else {
				// 获取老的销售数据
				OldSalesSummary oldSalesSummary = old_salesSummaryService.getOSSByTxdocno(txdocno);
				if (oldSalesSummary != null) {
					// 获取销售的商品
					List<OldSalesItem> salesItems = old_SalesItemService.getOSIListByTxDocno(txdocno);
					oldSalesSummary.setSis(salesItems);
					// 获取销售的支付方式
					List<OldSalesTender> salesTenders = old_salesTenderService.getOSTByTxDocno(txdocno);
					oldSalesSummary.setSts(salesTenders);
					Map<String, Object> ossMap = new HashMap<>();
					// 交易日期(时间戳yyyy-MM-dd)
					ossMap.put("txdate", cleanObjNull(oldSalesSummary.getTxdate().getTime()));
					// 交易时间
					ossMap.put("txtime", cleanObjNull(oldSalesSummary.getTxtime()));
					// 商场名称
					ossMap.put("mallName", cleanObjNull(xfMallService.selectMall().getXfMallname()));
					// 商铺
					ossMap.put("storeName",
							cleanObjNull(xfStoreService.getXfStoreByStoreCode(oldSalesSummary.getStorecode())));
					// 销售单号
					ossMap.put("txdocno", cleanObjNull(oldSalesSummary.getTxdocno()));
					// 收银员账号
					ossMap.put("cashierId", cleanObjNull(oldSalesSummary.getCashierId()));
					// 收银机号
					ossMap.put("tillid", cleanObjNull(oldSalesSummary.getTillid()));
					// 销售净金额
					ossMap.put("netamount", cleanObjNull(oldSalesSummary.getNetamount()));
					// 销售总数量
					ossMap.put("netqty", cleanObjNull(oldSalesSummary.getNetqty()));
					// 商品
					List<Map<String, String>> ssiList = new ArrayList<>();
					Map<String, String> ssiMap;
					if (salesItems != null && salesItems.size() > 0) {
						for (OldSalesItem oldSalesItem : salesItems) {
							ssiMap = new HashMap<>();
							// 商品号
							ssiMap.put("plu", cleanObjNull(oldSalesItem.getPlu()));
							// 商品名称
							ssiMap.put("itemName",
									cleanObjNull(xfItemService.getXfItem(oldSalesItem.getPlu()).getXfDesci()));
							// 数量
							ssiMap.put("qty", cleanObjNull(oldSalesItem.getQty()));
							// 价格
							ssiMap.put("netamount", cleanObjNull(oldSalesItem.getNetamount()));
							ssiList.add(ssiMap);
						}
					}
					// 保存
					ossMap.put("sis", ssiList);
					// 支付方式
					List<Map<String, String>> sstList = new ArrayList<>();
					Map<String, String> sstMap;
					if (salesTenders != null && salesTenders.size() > 0) {
						for (OldSalesTender oldSalesTender : salesTenders) {
							sstMap = new HashMap<>();
							// 行号
							sstMap.put("lineno", cleanObjNull(oldSalesTender.getLineno()));
							// 付款方式编码
							sstMap.put("tendercode", cleanObjNull(oldSalesTender.getTendercode()));
							// 付款金额
							sstMap.put("payamount", cleanObjNull(oldSalesTender.getPayamount()));
							// 本位币金额,即人民币金额
							sstMap.put("baseamount", cleanObjNull(oldSalesTender.getBaseamount()));
							// 超额金额
							sstMap.put("excessamount", cleanObjNull(oldSalesTender.getExcessamount()));
							sstList.add(sstMap);
						}
					}
					// 保存
					ossMap.put("sts", sstList);
					// 数据
					remes = new BasePageResultVo();
					remes.setRows(ossMap);
					remes.setTotal(1);
					status = new String[] { ResponseCode.OPR_SUCCESS, "获取销售数据成功！", "", "" };
				} else {
					status = new String[] { ResponseCode.NO_RESULT, "获取销售数据失败！", "", "" };
				}
			}
		}
		// 获取新版本的销售数据
		else if (DYLX.equals(CallParameters.GET_NEW_SALESSUMMARY)) {

			Map<String, String> map = (Map<String, String>) JsonMapper.jsonConvertMap(DATA);
			// 获取销售单号
			String txdocno = map.get("txDocno");
			if (StringUtil.isNUllStr(txdocno)) {
				status = new String[] { ResponseCode.PARAM_ERROR, "参数错误！", "", "" };
			} else {
				// 获取销售数据
				SalesSummary	salesSummary = salesSummaryService.getEnalbleSSByTxd(txdocno.trim());
				if (salesSummary != null) {
					// 销售信息
					Map<String, Object> ssMap = new HashMap<>();
					// 销售单号
					ssMap.put("txdocno", cleanObjNull(salesSummary.getTxdocno()));
					// 交易日期
					ssMap.put("txdate", cleanObjNull(salesSummary.getTxdate()));
					// 交易时间
					ssMap.put("txtime", cleanObjNull(salesSummary.getTxtime()));
					// 商场编号
					ssMap.put("mallid", cleanObjNull(salesSummary.getMallid().getXfMallid()));
					// 商场名称
					ssMap.put("mallName", cleanObjNull(salesSummary.getMallid().getXfMallname()));
					// 店铺号
					ssMap.put("storecode", cleanObjNull(salesSummary.getStorecode().getXfStorecode()));
					// 店铺名
					ssMap.put("storeName", cleanObjNull(salesSummary.getStorecode().getXfStorecode()));
					// 收银机号
					ssMap.put("tillid", cleanObjNull(salesSummary.getTillid()));
					// 收银员编号
					ssMap.put("staffcode", cleanObjNull(salesSummary.getStaffcode().getXfStaffcode()));
					// 会员号
					ssMap.put("vipcode", cleanObjNull(salesSummary.getVipcode()));
					// 销售员
					ssMap.put("salesman", cleanObjNull(salesSummary.getSalesman()));
					// 销售总数量
					ssMap.put("netqty", cleanObjNull(salesSummary.getNetqty().intValue()));
					// 销售净金额
					ssMap.put("netamount", cleanObjNull(salesSummary.getNetamount()));
					// 原销售单号
					ssMap.put("originalTxdocno", cleanObjNull(salesSummary.getOriginalTxdocno()));
					// 收银店铺号
					ssMap.put("cashStorecode", cleanObjNull(salesSummary.getCashStorecode().getXfStorecode()));
					// 关联的促销劵
					ssMap.put("promotionStampId", cleanObjNull(salesSummary.getPromotionStampId()));
					// 翼支付支付码
					ssMap.put("barcode", cleanObject2Number(salesSummary.getBarcode()));
					//订单状态
					ssMap.put("state", cleanObjNull(salesSummary.getState()));
					//超额金额
					ssMap.put("change", cleanObjNull(salesSummary.getChangemount()));
					// 销售的商品
					List<Map<String, String>> siList = new ArrayList<>();
					Map<String, String> siMap;
					List<SalesItem> salesItems = salesItemService.getXfItemByDocCode(txdocno);
					for (SalesItem salesItem : salesItems) {
						siMap = new HashMap<>();
						// 销售单号
						siMap.put("txdocno", txdocno);
						// 行号
						siMap.put("lineno", cleanObjNull(salesItem.getLineno()));
						// 商品编号
						siMap.put("plu", cleanObjNull(salesItem.getPlu().getXfPlu()));
						// 商品货品描述
						siMap.put("xfDesci", cleanObjNull(salesItem.getPlu().getXfDesci()));
						// 数量
						siMap.put("qty", cleanObjNull(salesItem.getQty().intValue()));
						// 折扣金额
						siMap.put("discountamoun", cleanObjNull(salesItem.getDiscountamount()));
						// 净金额
						siMap.put("netamount", cleanObjNull(salesItem.getNetamount()));
						// 获得积分
						siMap.put("bonusearn", cleanObjNull(salesItem.getBonusearn()));
						//获得重量weight
						siMap.put("weight", cleanObjNull(salesItem.getWeight()));
						
						siList.add(siMap);
					}
					// 商品添加到销售
					ssMap.put("sis", siList);
					// 销售的支付方式
					List<Map<String, String>> stList = new ArrayList<>();
					Map<String, String> stMap;
					List<SalesTender> salesTenders = salesTenderService.getSTSByTxdocno(txdocno);
					for (SalesTender salesTender : salesTenders) {
						stMap = new HashMap<>();
						// 销售单号
						stMap.put("txdocno", txdocno);
						// 行号
						stMap.put("lineno", cleanObjNull(salesTender.getLineno()));
						// 付款方式编码
						stMap.put("tendercode", cleanObjNull(salesTender.getTendercode().getXfTendercode()));
						// 付款方式描述
						stMap.put("xfTenderdesc", cleanObjNull(salesTender.getTendercode().getXfTenderdesc()));
						// 退款控制开关(控制前端收银是否可以退款)
						stMap.put("xfRefund", cleanObjNull(salesTender.getTendercode().getXfRefund()));
						// 付款金额
						stMap.put("payamount", cleanObjNull(salesTender.getPayamount()));
						// 本位币金额,即人民币金额
						stMap.put("baseamount", cleanObjNull(salesTender.getBaseamount()));
						// 超额金额
						stMap.put("excessamount", cleanObjNull(salesTender.getExcessamount()));
						// 交易附加域,主要用于银行卡支付保存信息，格式为：“时间&凭证号&系统参考号”，
						stMap.put("transMemo", cleanObjNull(salesTender.getTransMemo()));
						// 支付的账户号,没有则为空
						stMap.put("accountNo", cleanObjNull(salesTender.getAccountNo()));
						stList.add(stMap);
					}
					// 支付方式添加到销售
					ssMap.put("sts", stList);
					// 数据
					remes = new BasePageResultVo();
					remes.setRows(ssMap);
					remes.setTotal(1);
					status = new String[] { ResponseCode.OPR_SUCCESS, "获取销售数据成功！", "", "" };
				} else {
					status = new String[] { ResponseCode.NO_RESULT, "获取销售数据失败！", "", "" };
				}
			
			}
		
		}
		// 获取客显文件
		else if (DYLX.equals(CallParameters.GET_GUEST_SHOWFILE)) {
			Map<String, String> map = new HashMap<>();
			// 相文件保存的相对路径
			String realPath = request.getSession().getServletContext().getRealPath("/");
			File file;
			// 获取图片
			Sets sets = setService.getSets("imageUrl");
			// 图片路径
			map.put("imageUrl", sets!=null?sets.getValue():"");
			// 获取视频地址
			sets = setService.getSets("videoUrl");
			// 视频路径
			map.put("videoUrl", sets!=null?sets.getValue():"");
			// 获取logo地址
			sets = setService.getSets("logoUrl");
			// logo路径
			map.put("logoUrl", sets!=null?sets.getValue():"");
			// 数据
			remes = new BasePageResultVo();
			remes.setRows(map);
			remes.setTotal(1);
			status = new String[] { ResponseCode.OPR_SUCCESS, "获取客显文件成功！", "", "" };
		}
		//获取最新APK
		else if(DYLX.equals(CallParameters.GET_CPOS_APK)){
			XfMall mall = xfMallService.selectMall();
			if (mall!=null) {
				Map<String, Object> cposapk = JionPHP.getCPOSAPK(setService,request.getSession().getServletContext().getRealPath("/"),mall.getXfMallid());
				if (true==((boolean)cposapk.get("flag"))) {
					// 数据
					remes = new BasePageResultVo();
					remes.setRows(cposapk.get("data"));
					remes.setTotal(1);
					status = new String[] { ResponseCode.OPR_SUCCESS, "获取apk成功！", "", "" };
				}else{
					status = new String[] { ResponseCode.NO_RESULT, "获取apk失败！", "", "" };
				}
			}else{
				status = new String[] { ResponseCode.NO_RESULT, "获取apk失败！", "", "" };
			}
		}
		//员工修改密码
		else if(DYLX.equals(CallParameters.STAFF_EDIT_PWD)){
			Map<String, String> jsonConvertMap = (Map<String, String>) JsonMapper.jsonConvertMap(DATA);
			//店铺号
			String xfIssuestore=jsonConvertMap.get("xfIssuestore");
			//员工号
			String xfStaffcode=jsonConvertMap.get("xfStaffcode");
			//员工密码
			String xfPassword=jsonConvertMap.get("xfPassword");
			//员工新密码
			String xfPasswordNew=jsonConvertMap.get("xfPasswordNew");
			if (StringUtil.isNUllStr(xfIssuestore) || StringUtil.isNUllStr(xfStaffcode) 
					|| StringUtil.isNUllStr(xfPassword)
					|| StringUtil.isNUllStr(xfPasswordNew)) {
				status = new String[] { ResponseCode.PARAM_ERROR, "参数错误！", "", "" };
			}else{
				//员工对象
				XfStaff xfStaff=new XfStaff();
				xfStaff.setXfStaffcode(xfStaffcode);
				xfStaff.setXfPassword(xfPassword);
				xfStaff = xfStaffService.getXfStaff(xfStaff);
				//判断员工是否为空并且是否在所属店铺内
				if (xfStaff!=null && xfStaff.getXfIssuestore().getXfStorecode().equals(xfIssuestore)) {
					//新密码
					xfStaff.setXfPassword(xfPasswordNew);
					xfStaffService.saveOrUpdateXfStaff(xfStaff);
					status = new String[] { ResponseCode.OPR_SUCCESS, "修改员工密码成功！", "", "" };
				}else{
					status = new String[] { ResponseCode.NO_RESULT, "修改员工密码失败！", "", "" };
				}
			}
		}
		//通过收银机ID获取店铺
		else if(DYLX.equals(CallParameters.GET_STORE_TILLID)){
			
		 		JSONObject json=JSONObject.parseObject(DATA);
		 		String tillid=json.getString("tillid");
		 		
		 		
		 		XfTillidState temp = xfTillidStateService.getXfTillidStateBytillid(tillid);
		 		
		 		if(temp!=null){
		 			if(temp.getOnlineType().equals("1")){
			 			status = new String[] { ResponseCode.NO_RESULT, "该收银机编号已配置！", "", "" };
			 			remes = StructureUtil.returnObject(remes, status);
			 			return remes;
			 		}
		 		}
		 		
		 		
		 		
		 		if(StringUtils.isEmpty(tillid)){
		 			status = new String[] { ResponseCode.NO_RESULT, "收银机编号不存在！", "", "" };
		 			remes = StructureUtil.returnObject(remes, status);
		 			return remes;
		 		}
		 		try {
		 			XfStore store=gwXfStoreService.getStoreInfoByTillid(tillid);
		 			if(store==null){
		 				status = new String[] { ResponseCode.NO_RESULT, "无店铺信息！", "", DATA };
			 			remes = StructureUtil.returnObject(remes, status);
			 			return remes;
		 			}
		 			remes = new BasePageResultVo();
		 			StoreAuth storeAuth = storeAuthService.getStoreAuthByHql(tillid);
		 			String screenStyle = storeAuth.getScreenStyle();  //单双屏
		 			store.setScreenStyle(screenStyle);
		 			
//		 			jsonData.put("screenStyle", screenStyle);
					remes.setRows(store);
					status = new String[] { ResponseCode.OPR_SUCCESS, "成功！", "", "" };
					remes = StructureUtil.returnObject(remes, status);
					return remes;
				} catch (Exception e) {
					status = new String[] { ResponseCode.NO_RESULT, "通过收银机获取店铺失败！", "", e.getMessage() };
				}
		}
		//wifi 探针
		else if(DYLX.equals(CallParameters.WIFI_PROBE)){
			try {
				PropertiesUtil p=new PropertiesUtil("systemConfig.properties");
				String wifiProbe=p.readProperty("wifiProbe");
				JSONObject json=new JSONObject();
				json.put("wifiProbe", wifiProbe);
				remes = new BasePageResultVo();// 数据
				remes.setRows(json);
				remes.setTotal(1);
				status = new String[] { ResponseCode.OPR_SUCCESS, "获取探针IP成功！", "", "" };
			} catch (Exception e) {
				throw new RuntimeException("获取探针IP失败"+e.getMessage());
			}
		}
		//收银机配置信息
		else if(DYLX.equals(CallParameters.GET_POS_CONFIG)){
			remes = new BasePageResultVo();// 数据
			JSONObject  posConfig= new JSONObject();
			JSONObject parseObject = JSONObject.parseObject(DATA);
			// 收银机编号
			String tillid = parseObject.getString("tillid");
			if(StringUtils.isEmpty(tillid)){
				status = new String[] { ResponseCode.NO_RESULT, "收银机编号不存在！", "", "" };
	 			remes = StructureUtil.returnObject(remes, status);
	 			return remes;
			}
			try {
				//收银机公用配置
				PosCommonConfigModel pos_comfig=posCommConfigService.getPosComConfigByConid(commonUtil.ComConfigConid);
				//收银机私有配置
				PosConfigModel pos_config = posPrivateConfigService.getPosConfigByTillid(tillid);
				if(pos_config==null||pos_comfig==null){
					status = new String[] { ResponseCode.NO_RESULT, "获取失败！", "", "" };
					remes = StructureUtil.returnObject(remes, status);
			 	    return remes;
				}else{
					posConfig.put("pos_comfig", JSONObject.toJSON(pos_comfig));
					posConfig.put("pos_config",JSONObject.toJSON(pos_config));
					remes.setRows(posConfig);
					remes.setTotal(1);
					status = new String[] { ResponseCode.OPR_SUCCESS, "获取成功！", "", "" };
				}
			} catch (Exception e) {
				e.printStackTrace();
				status = new String[] { ResponseCode.NO_RESULT, "通过收银机获取配置信息失败！", "", e.getMessage() };
			}
		}
		//员工、支付方式、商品、收银机配置信息数据跟新状态
		else if(DYLX.equals(CallParameters.DATA_REVISION)){
			remes = new BasePageResultVo();// 数据
			JSONObject  updateTime= new JSONObject();
			JSONObject parseObject = JSONObject.parseObject(DATA);
			Map<String,String>time=new HashMap<>();
			// 收银机编号
			String tillid = parseObject.getString("tillid");
			//私有配置和公有配置修改时间
			String privateconfig=posPrivateConfigService.getPosConfigByTillid(tillid).getUtime();
			String publicconfig=posCommConfigService.getPosComConfigByConid(commonUtil.ComConfigConid).getUtime();
			//员工、商品、支付方式同步修改时间
			String setTimes=setService.getSets("updateTime").getValue().trim();
			//当有一个参数为空返回信息
			if(privateconfig==""||privateconfig==null||publicconfig==""||publicconfig==null||setTimes==null||setTimes==""){
				status = new String[] { ResponseCode.NO_RESULT, "通讯成功！", "", "" };
				remes = StructureUtil.returnObject(remes, status);
		 	    return remes;
			}
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int compareDate = DateUtil.compareDate(sdf.parse(privateconfig), sdf.parse(publicconfig));
			if(compareDate == -1){
				time.put("configTime", publicconfig);
			}else{
				time.put("configTime", privateconfig);
			}
			time.put("setTimes", setTimes);
			//更新小票的logo或二维码的时间
			if(setService.getSets("updatePrintReceiptTime") == null){
				time.put("updatePrintReceiptTime", "");  // 更新小票的logo和二维码时间
			}else{
				time.put("updatePrintReceiptTime", setService.getSets("updatePrintReceiptTime").getValue().trim());  // 更新小票的logo和二维码时间
			}
			//更新客显的时间
			if(setService.getSets("updateGuestTime") == null){
				time.put("updateGuestTime", "");
			}else{
				time.put("updateGuestTime", setService.getSets("updateGuestTime").getValue().trim());
			}
			
			updateTime.put("updateTime", JSONObject.toJSON(time));
			StoreAuth storeAuth = storeAuthService.getStoreAuthByHql(tillid);
			XfStore xfStore = xfStoreService.getXfStoreByStoreCode(storeAuth.getStoreCode());
			XfTillidState xfTillidState = new XfTillidState();// 设置商铺
			xfTillidState.setXfStorecode(xfStore);// 设置访问者ip
			if (xfTillidState.getIp() == null) {
				xfTillidState.setIp(IpUtil.getRemoteHost(request));
			}
			// 设置访问时间
			if (xfTillidState.getVisitTime() == null) {
				xfTillidState.setVisitTime(new Date());
			}
			xfTillidState.setXfStorecode(xfStore);
			xfTillidState.setDeviceInfo("  ");
			xfTillidState.setTillid(tillid);
			xfTillidState.setXfUpdate(false);
			// 如果在数据库中为空则新增否则修改
			try {
				xfTillidStateService.saveOrUpdate(xfTillidState);
			} catch (Exception e) {
				logger.info("收银机录入失败! 店铺号:"+xfStore.getXfStorecode()+"收银机编号:"+tillid);
				throw new RuntimeException("收银机录入失败! 店铺号:"+xfStore.getXfStorecode()+"收银机编号:"+tillid+"\n"+e.getMessage());
			}
			
			status = new String[] { ResponseCode.OPR_SUCCESS, "成功！", "", "" };
			remes.setRows(updateTime);
			remes.setTotal(1);
		}
		//收银机权限
		else if(DYLX.equals(CallParameters.TILL_AUTH)){
			try {
				JSONObject parseObject = JSONObject.parseObject(DATA);
				String tillid = parseObject.getString("tillid");
				StoreAuth storeAuth = storeAuthService.getStoreAuthByHql(tillid);
				if(storeAuth==null) {
					storeAuth=new StoreAuth();
					storeAuth.setAuthId("0");
					storeAuth.setTillid(tillid);
				}
				remes = new BasePageResultVo();// 数据
				remes.setRows(storeAuth);
				remes.setTotal(1);
				status = new String[] { ResponseCode.OPR_SUCCESS, "获取收银机权限成功！", "", "" };
			} catch (Exception e) {
				status = new String[] { ResponseCode.OPR_FAIL, "获取收银机权限失败!"+e.getMessage(), "", "" };
			}
		}
		//支付方式手续费率
		else if(DYLX.equals(CallParameters.PAYMENT_RENT)){
			try {
				List<PaymentRent> paymentRentList = paymentRentService.getPaymentRentList();
				remes = new BasePageResultVo();// 数据
				remes.setRows(paymentRentList);
				remes.setTotal(1);
				status = new String[] { ResponseCode.OPR_SUCCESS, "获取支付方式手续费率成功！", "", "" };
			} catch (Exception e) {
				status = new String[] { ResponseCode.OPR_FAIL, "获取支付方式手续费率失败!"+e.getMessage(), "", "" };
			}
		}
		//
		else if(DYLX.equals(CallParameters.GET_CPOS_UPDATE)){
			try {
				JSONObject json=gwUpdatePosService.getUpdateInfo(request);
				remes = new BasePageResultVo();// 数据
				remes.setRows(json);
				remes.setTotal(1);
				status = new String[] { ResponseCode.OPR_SUCCESS, "获取更新成功！", "", "" };
			} catch (Exception e) {
				status = new String[] { ResponseCode.OPR_FAIL, "获取更新失败,系统异常!"+e.getMessage(), "", "" };
			}
		}
		//获取小票的logo和二维码
		else if (DYLX.equals(CallParameters.GET_PRINTRECEIPT_LOGOEWM)){
			try {
				Sets printLogo = setService.getSets("printLogo");  //小票logo
				Sets printEWM = setService.getSets("printEWM");  //小票二维码
				remes = new BasePageResultVo();
				JSONObject json = new JSONObject();
				if(printLogo == null){
					json.put("printLogo", "");
				}else{
					json.put("printLogo", printLogo.getValue());
				}
				if(printEWM == null){
					json.put("printEWM", "");
				}else{
					json.put("printEWM", printEWM.getValue());
				}
				remes.setRows(json);
				remes.setTotal(1);
				status = new String[] { ResponseCode.OPR_SUCCESS, "获取小票的logo和二维码成功", "", "" };
			} catch (Exception e) {
				status = new String[] { ResponseCode.OPR_FAIL, "获取小票的logo和二维码失败"+e.getMessage(), "", "" };
			}
		}//获取支付平台地址
		else if(DYLX.equals(CallParameters.GET_GDWS_PAYURL)){
			Map<String, String> jsonConvertMap = (Map<String, String>) JsonMapper.jsonConvertMap(DATA);
			// 收银机号
			String tillid = jsonConvertMap.get("tillid");
			//XfTillidState tillidState = xfTillidStateService.selectByStrId(tillid);
			StoreAuth storeAuth = storeAuthService.getStoreAuthByHql(tillid);
			String gwspayUrl;
			JSONObject json = new JSONObject();
			if(storeAuth!=null){
				PropertiesUtil p = new PropertiesUtil("wsdl-config.properties");
				if(p.readProperty("gwspayUrl")!=null){
					 gwspayUrl = p.readProperty("gwspayUrl");
				}else{
					 gwspayUrl = "";
				}
				json.put("gwspayUrl", gwspayUrl);
				
				remes = new BasePageResultVo();
				remes.setRows(json);
				remes.setTotal(1);
				status = new String[] { ResponseCode.OPR_SUCCESS, "获取支付平台地址成功！", "", "" };
			}else{
				status = new String[] { ResponseCode.OPR_SUCCESS, "获取支付平台地址失败！", "", "" };
			}
		}
		
		remes = StructureUtil.returnObject(remes, status);
		return remes;
	}
	

	
}
