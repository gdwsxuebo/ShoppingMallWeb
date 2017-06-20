package com.smw.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gws.service.impl.PosCommConfigService;
import com.smw.common.util.DateUtil;
import com.smw.pojo.XfStore;
import com.smw.pojo.XfStoreCenter;
import com.smw.pojo.XfTillidState;
import com.smw.service.PosPrivateConfigService;
import com.smw.service.SetService;
import com.smw.service.StoreAuthService;
import com.smw.service.XfStoreCenterService;
import com.smw.service.XfStoreService;
import com.smw.service.XfTillidStateService;

import cn.com.gowins.cpos.netty.PushServer;
import cn.com.gowins.cpos.netty.module.SendMsg;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
@Component
public class XfStoreToPush {
	
	private static XfTillidStateService xfTillidStateService;
	private static XfStoreCenterService xfStoreCenterService;
	
	
	@Autowired
	public void setUserInfo(XfTillidStateService xfTillidStateService,XfStoreCenterService xfStoreCenterService){

		XfStoreToPush.xfTillidStateService=xfTillidStateService;
		XfStoreToPush.xfStoreCenterService=xfStoreCenterService;
	}
	
	
	/**通过店铺列表推送收银机
	 * @param name
	 * @param countList
	 */
	@SuppressWarnings("null")
	
	public static void ToPushByXfStore(String name, List<XfStore> countList) {
		
		if(countList.size()>0){
			try {
				SetUpdateTime.set();//设置更新
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		for (XfStore xfStore : countList) {
			String storeid = xfStore.getXfStorecode();
			if (!xfStore.getXfCenter()) {// 不是中央店铺，直接查询该店铺下收银机数量
				Integer tillidNums = xfTillidStateService.getTillidCountByStoreId(storeid);
				System.out.println(storeid + "非中央店铺====>>>>>" + tillidNums);
				if (tillidNums > 0) {
					List<XfTillidState> list = xfTillidStateService.getTillidListBy(storeid);
					Iterator<XfTillidState> itr = list.iterator();
					while (itr.hasNext()) {
						XfTillidState xfTillidState = itr.next();
							try {
								SendMsg loginMsg = new SendMsg();
								loginMsg.setClientId(xfTillidState.getTillid());
								loginMsg.setIp(xfTillidState.getIp());
								loginMsg.setTillId(xfTillidState.getTillid());
								loginMsg.setXfStaffcode(xfTillidState.getXfStaffcode());
								PushServer.push(name, loginMsg);
							} catch (ParseException e) {
								e.printStackTrace();
							}
					}
				}
			} else {// 中央店铺推送
				Integer tillidNums = xfTillidStateService.getTillidCountByCoreStoreId(storeid);
				System.out.println(storeid + "中央店铺====>>>>>" + tillidNums);
				if (tillidNums > 0) {
					List<XfStoreCenter> xfStoreCenters = xfStoreCenterService.getStoreCenterListByStoreCode(storeid);// 中央店铺下的所有子店铺
					List<XfTillidState> list = xfTillidStateService.getTillidListBy(storeid);
					for (XfStoreCenter center : xfStoreCenters) {
						List<XfTillidState> listcenter = xfTillidStateService.getTillidListBy(center.getXfStorecode().getXfStorecode());
						list.addAll(listcenter);
					}
					Iterator<XfTillidState> itr = list.iterator();
					while (itr.hasNext()) {
						XfTillidState xfTillidState = itr.next();
							try {
								SendMsg loginMsg = new SendMsg();
								loginMsg.setClientId(xfTillidState.getTillid());
								loginMsg.setIp(xfTillidState.getIp());
								loginMsg.setTillId(xfTillidState.getTillid());
								loginMsg.setXfStaffcode(xfTillidState.getXfStaffcode());
								PushServer.push(name, loginMsg);
							} catch (ParseException e) {
								e.printStackTrace();
							}
						//}

					}
				}
			}
		}

	}
	
	/**推送给所有在线收银机
	 * @param name
	 */
	public static void ToPushAll(String name) {
		List<XfTillidState> list = xfTillidStateService.getAllTillids();
		
		if(!name.equals("PrintReceipt")){
			try {
				SetUpdateTime.set();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		Iterator<XfTillidState> itr = list.iterator();
		while (itr.hasNext()) {
			XfTillidState xfTillidState = itr.next();
				try {
					SendMsg loginMsg = new SendMsg();
					loginMsg.setClientId(xfTillidState.getTillid());
					loginMsg.setIp(xfTillidState.getIp());
					loginMsg.setTillId(xfTillidState.getTillid());
					loginMsg.setXfStaffcode(xfTillidState.getXfStaffcode());
					PushServer.push(name,loginMsg);
				} catch (ParseException e) {
					e.printStackTrace();
				}

		}
	}
	

}
