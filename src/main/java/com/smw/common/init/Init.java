package com.smw.common.init;

import java.util.List;

import javax.annotation.Resource;

import com.smw.common.util.PropertiesUtil;
import com.smw.pojo.GwMenuTree;
import com.smw.pojo.Sets;
import com.smw.pojo.XfMall;
import com.smw.pojo.XfStore;
import com.smw.service.GwMenuTreeService;
import com.smw.service.SetService;
import com.smw.service.XfMallService;
import com.smw.service.XfStaffService;
import com.smw.service.XfStoreService;
import com.smw.service.XfTillidStateService;
import com.smw.web.JoinMis;

import cn.com.gowins.cpos.netty.NettyStart;
import cn.com.gowins.cpos.netty.PushServer;

/**
 * 初始
 * 
 * @author suen
 * @date 2016年5月28日-下午3:34:55
 * @version 1.0
 */
public class Init {
	/**
	 * 设置
	 */
	@Resource
	private SetService setService;
	
	/**
	 * 新MIS
	 */
	@Resource
	private JoinMis joinMis;
	
	/**
	 * 员工
	 */
	@Resource
	private XfStaffService xfStaffService;
	
	/**
	 * 收银机在线状态
	 */
	@Resource
	private  XfTillidStateService xfTillidStateService;
	
	@Resource
	private GwMenuTreeService gwMenuTreeService;

	/**
	 * 初始
	 */
	public void init() {
		//初始化局域网Netty框架推送服务
		
		List<GwMenuTree> list = gwMenuTreeService.selectAllList();
		if(list.size()==0){
			gwMenuTreeService.saveInitAllMenu();//服务器启动初始化
		}
		xfTillidStateService.delTillidStateTabe();
		NettyStart.start();
		
		PropertiesUtil p = new PropertiesUtil("wsdl-config.properties");
		String readProperty;
		// mis接口访问地址
		Sets sets = setService.getSets("misUrl");
		if (sets != null) {
			com.smw.web.Init.INITURL.put("misUrl", sets.getValue());
		}else{
			sets=new Sets();
			//设置key
			sets.setId("misUrl");
			//设置值
			readProperty = p.readProperty("misUrl");
			sets.setValue(readProperty);
			//保存
			setService.saveOrUpdateSets(sets);
			//保存在内存中
			com.smw.web.Init.INITURL.put("misUrl", readProperty);
		}
		// php接口访问地址
		sets = setService.getSets("phpUrl");
		if (sets != null) {
			com.smw.web.Init.INITURL.put("phpUrl", sets.getValue());
		}else{
			sets=new Sets();
			//设置key
			sets.setId("phpUrl");
			//设置值
			readProperty = p.readProperty("phpUrl");
			sets.setValue(readProperty);
			//保存
			setService.saveOrUpdateSets(sets);
			//保存在内存中
			com.smw.web.Init.INITURL.put("phpUrl", readProperty);
		}
		// 老MIS开关
		sets = setService.getSets("isOpenOldMis");
		if (sets != null) {
			com.smw.web.Init.INITURL.put("isOpenOldMis", sets.getValue());
		}
		
		//新MIS线程时间
		sets = setService.getSets("saleSummaryTime");
		if (sets != null) {
			com.smw.web.Init.INITURL.put("saleSummaryTime", sets.getValue());
		}else{
			sets=new Sets();
			//设置key
			sets.setId("saleSummaryTime");
			//设置时间
			Long lon=10L*60*1000;
			sets.setValue(lon.toString());
			//保存
			setService.saveOrUpdateSets(sets);
			//保存在内存中
			com.smw.web.Init.INITURL.put("saleSummaryTime", lon.toString());
		}
		//判断数据库是否有管理员信息
		Integer count = xfStaffService.getCount(null, null);
		//是否需要初始化  如果没有员工则需要初始化，也就是跳转到初始页面
		com.smw.web.Init.INITURL.put("isToInit",(count==0)+"");
		
		//启动上传销售数据到新MIS线程
		joinMis.setSalesSummary();
		
		//满足多个促销时最多可以选择几个
		sets = setService.getSets("maxSelectPR");
		if (sets != null) {
			com.smw.web.Init.INITURL.put("maxSelectPR", sets.getValue());
		}else{
			sets=new Sets();
			//设置key
			sets.setId("maxSelectPR");
			//设置个数
			sets.setValue("1");
			//保存或者修改
			setService.saveOrUpdateSets(sets);
			com.smw.web.Init.INITURL.put("maxSelectPR", sets.getValue());
		}
	}
	
	
	
}
