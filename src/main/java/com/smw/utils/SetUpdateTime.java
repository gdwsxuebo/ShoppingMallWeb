package com.smw.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.gws.service.impl.PosCommConfigService;
import com.gws.util.commonUtil;
import com.smw.common.util.BasePageResultVo;
import com.smw.common.util.DateUtil;
import com.smw.common.util.ResponseCode;
import com.smw.common.util.StringUtil;
import com.smw.common.util.StructureUtil;
import com.smw.pojo.Sets;
import com.smw.pojo.StoreAuth;
import com.smw.pojo.XfStore;
import com.smw.pojo.XfTillidState;
import com.smw.service.PosPrivateConfigService;
import com.smw.service.SetService;
import com.smw.service.StoreAuthService;
import com.smw.service.XfStoreService;
import com.smw.service.XfTillidStateService;

import cn.com.gowins.cpos.netty.PushServer;
import cn.com.gowins.cpos.netty.module.SendMsg;


@Component
public class SetUpdateTime {
	
	private static Logger logger = LoggerFactory.getLogger(SetUpdateTime.class);
	
	
	private static SetService setService;
	private static PosCommConfigService posCommConfigService;
	private static PosPrivateConfigService posPrivateConfigService;
	private static	StoreAuthService storeAuthService;
	private static XfTillidStateService xfTillidStateService;
	private static XfStoreService xfStoreService;
	
	@Autowired
	public void setUserInfo(SetService setService,PosCommConfigService posCommConfigService,PosPrivateConfigService posPrivateConfigService,StoreAuthService storeAuthService,XfTillidStateService xfTillidStateService,XfStoreService xfStoreService){
		SetUpdateTime.setService=setService;
		SetUpdateTime.posCommConfigService=posCommConfigService;
		SetUpdateTime.posPrivateConfigService=posPrivateConfigService;
		SetUpdateTime.storeAuthService=storeAuthService;
		SetUpdateTime.xfTillidStateService=xfTillidStateService;
		SetUpdateTime.xfStoreService=xfStoreService;
	}

	public static void	set() throws ParseException {
		Sets sets = new Sets();
		sets.setId("updateTime");
		sets.setValue(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		setService.saveOrUpdateSets(sets);
		//PushServer.push("item","");//推送信息更新。
		System.out.println("设置更新!");
		
	}

	/**
	 * @param tillids
	 * @param ip
	 * @return
	 */
	//更新收银机状态
	public static BasePageResultVo UpdateTillid(SendMsg loginMsg,String type) {
		BasePageResultVo remes = null;
		String[] status = null;
		remes = new BasePageResultVo();
		String xfStorecode = storeAuthService.getStoreAuthByHql(loginMsg.getTillId()).getStoreCode(); // 关联的店铺编码
		
		if(StringUtil.isNUllStr(loginMsg.getXfStaffcode())){
			loginMsg.setXfStaffcode(xfStorecode);
		}
		
		String tillid = loginMsg.getTillId(); // 收银机号
		String deviceInfo = " "; // 设备信息（设备标识）
		if (StringUtil.isNUllStr(xfStorecode) || StringUtil.isNUllStr(tillid)) {
			status = new String[] { ResponseCode.PARAM_ERROR, "参数错误！", "", "" };
		} else {
			// 商铺
			XfStore xfStore = xfStoreService.getXfStoreByStoreCode(xfStorecode);
			// 商铺是否存在
			if (xfStore == null) {
				status = new String[] { ResponseCode.NO_RESULT, "无关联的商铺！", "", "" };
			} else {
				XfTillidState xfTillidState = new XfTillidState();// 设置商铺
				xfTillidState.setXfStorecode(xfStore);// 设置访问者ip
				if (!StringUtil.isNUllStr(loginMsg.getIp())) {
					xfTillidState.setIp(loginMsg.getIp());
				}else{
					XfTillidState temp = xfTillidStateService.getXfTillidStateBytillid(loginMsg.getTillId());
					System.out.println(temp.getIp());
					xfTillidState.setIp(temp.getIp());
				}
				// 设置访问时间
				if (xfTillidState.getVisitTime() == null) {
					xfTillidState.setVisitTime(new Date());
				}
				xfTillidState.setXfStorecode(xfStore);
				xfTillidState.setDeviceInfo(deviceInfo);
				xfTillidState.setTillid(tillid);
				xfTillidState.setOnlineType(type);
				xfTillidState.setXfUpdate(false);
				xfTillidState.setXfStaffcode(loginMsg.getXfStaffcode());
				System.out.println(loginMsg.getXfStaffcode());
				// 如果在数据库中为空则新增否则修改
				try {
					xfTillidStateService.saveOrUpdate(xfTillidState);
				} catch (Exception e) {
					logger.info("收银机录入失败! 店铺号:" + xfStorecode + "收银机编号:" + tillid);
					throw new RuntimeException(
							"收银机录入失败! 店铺号:" + xfStorecode + "收银机编号:" + tillid + "\n" + e.getMessage());
				}
				status = new String[] { ResponseCode.OPR_SUCCESS, "操作成功！", "", "" };
			}
		}
		remes = StructureUtil.returnObject(remes, status);
		return remes;

	}
	
	
	

	/**
	 * 获取相关收银机数据更新
	 * @param tillids
	 * @param ip
	 * @return
	 * @throws ParseException
	 */
	public static BasePageResultVo getBasePageResultVo(SendMsg loginMsg) throws ParseException {

		BasePageResultVo remes = null;
		String[] status = null;
		remes = new BasePageResultVo();// 数据
		JSONObject updateTime = new JSONObject();
		// JSONObject parseObject = JSONObject.parseObject(DATA);
		Map<String, String> time = new HashMap<>();
		// 收银机编号
		String tillid = loginMsg.getTillId();
		// 员工、商品、支付方式同步修改时间
		String setTimes = setService.getSets("updateTime").getValue().trim();
		// 私有配置和公有配置修改时间
		String publicconfig ="2017-04-20 12:58:39";// posCommConfigService.getPosComConfigByConid(commonUtil.ComConfigConid).getUtime();
		String privateconfig = "2017-04-20 12:58:39";//posPrivateConfigService.getPosConfigByTillid(tillid).getUtime();
		// 当有一个参数为空返回信息
		if (privateconfig == "" || privateconfig == null || publicconfig == "" || publicconfig == null
				|| setTimes == null || setTimes == "") {
			status = new String[] { ResponseCode.NO_RESULT, "通讯成功！", "", "" };
			remes = StructureUtil.returnObject(remes, status);
			return remes;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int compareDate = DateUtil.compareDate(sdf.parse(privateconfig), sdf.parse(publicconfig));
		if (compareDate == -1) {
			time.put("configTime", publicconfig);
		} else {
			time.put("configTime", privateconfig);
		}
		time.put("setTimes", setTimes);
		// 更新小票的logo或二维码的时间
		if (setService.getSets("updatePrintReceiptTime") == null) {
			time.put("updatePrintReceiptTime", ""); // 更新小票的logo和二维码时间
		} else {
			time.put("updatePrintReceiptTime", setService.getSets("updatePrintReceiptTime").getValue().trim()); // 更新小票的logo和二维码时间
		}
		// 更新客显的时间
		if (setService.getSets("updateGuestTime") == null) {
			time.put("updateGuestTime", "");
		} else {
			time.put("updateGuestTime", setService.getSets("updateGuestTime").getValue().trim());
		}

		updateTime.put("updateTime", JSONObject.toJSON(time));
		StoreAuth storeAuth = storeAuthService.getStoreAuthByHql(tillid);
		XfStore xfStore = xfStoreService.getXfStoreByStoreCode(storeAuth.getStoreCode());
		XfTillidState xfTillidState = new XfTillidState();// 设置商铺
		xfTillidState.setXfStorecode(xfStore);// 设置访问者ip
		if (xfTillidState.getIp() == null) {
			xfTillidState.setIp(loginMsg.getIp());
		}
		// 设置访问时间
		if (xfTillidState.getVisitTime() == null) {
			xfTillidState.setVisitTime(new Date());
		}
		xfTillidState.setXfStorecode(xfStore);
		xfTillidState.setDeviceInfo("  ");
		xfTillidState.setTillid(tillid);
		xfTillidState.setOnlineType("1");
		xfTillidState.setXfUpdate(false);
		xfTillidState.setXfStaffcode(loginMsg.getXfStaffcode());
		// 如果在数据库中为空则新增否则修改
		try {
			xfTillidStateService.saveOrUpdate(xfTillidState);
		} catch (Exception e) {
			logger.info("收银机录入失败! 店铺号:" + xfStore.getXfStorecode() + "收银机编号:" + tillid);
			throw new RuntimeException(
					"收银机录入失败! 店铺号:" + xfStore.getXfStorecode() + "收银机编号:" + tillid + "\n" + e.getMessage());
		}

		status = new String[] { ResponseCode.OPR_SUCCESS, "成功！", "", "" };
		remes.setRows(updateTime);
		remes.setTotal(1);
		remes = StructureUtil.returnObject(remes, status);
		return remes;

	}

}
