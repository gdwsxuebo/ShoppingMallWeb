package com.smw.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gws.service.impl.GwXfStoreCenterService;
import com.gws.service.impl.GwXfStoreService;
import com.smw.common.util.PropertiesUtil;
import com.smw.common.util.StringUtil;
import com.smw.pojo.WeekSaleMoney;
import com.smw.pojo.XfMall;
import com.smw.pojo.XfStore;
import com.smw.service.SalesSummaryService;
import com.smw.service.WeekSaleMoneyService;
import com.smw.service.XfMallService;
import com.smw.service.XfStaffService;
import com.smw.service.XfStoreService;

/**
 * 商场
 * @author suen
 * @date 2016年5月22日-下午3:15:46
 * @version 1.0
 */



/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("web/xfmall")
public class Mall {
	Logger logger = LoggerFactory.getLogger(Mall.class);
	/**
	 * 商场
	 */
	@Resource
	private XfMallService xfMallService;
	/**
	 * 用于查询中央店铺和非中央店铺的数量
	 * 
	 */
	@Autowired
	GwXfStoreService gwXfStoreService;
	@Autowired
	GwXfStoreCenterService gwXfStoreCenterService;

	@Resource
	private XfStaffService xfStaffService;
	
	/**
	 * 销售数据统计
	 */
	@Resource
	private WeekSaleMoneyService weekSaleMoneyService;
	
	/**
	 * 销售记录
	 */
	@Resource
	private SalesSummaryService salesSummaryService;
	
	/**
	 * 店铺
	 */
	@Resource
	private XfStoreService xfStoreService;
	
	/**
	 * 获取商场信息
	 */
	@RequestMapping("getXfMall")
	public Object getXfMall(HttpServletRequest request){
		
		try {
			//商场
			XfMall mall=xfMallService.selectMall();
			
			PropertiesUtil pUtil = new PropertiesUtil("systemConfig.properties");
			String misorv61;
			misorv61 = pUtil.readProperty("gdws.ifs.misorv61");
			//系统版本标识，如果为MIS则执行初始化中央店铺操作
			if(misorv61.equals("MIS")){
				
				XfStore miscenterstore = xfStoreService.getXfStoreByStoreCode(pUtil.readProperty("gdws.ifs.miscenterstore"));
				
				if(miscenterstore==null){
					//查询商场
					XfMall selectMall = xfMallService.selectMall();
					if(selectMall!=null){
						//保存店铺
						XfStore xfStore=new XfStore();
						//店铺编号
						xfStore.setXfStorecode(pUtil.readProperty("gdws.ifs.miscenterstore"));
						//店铺名称
						xfStore.setXfName(pUtil.readProperty("gdws.ifs.miscenterstorename"));
						//设置商场
						xfStore.setXfMallid(selectMall);
						xfStore.setXfCenter(true);
						xfStore.setIsInvalid("1");
						//保存
						xfStoreService.saveOrUpdateSingXfStores(xfStore);
					}
				}				
				
			}
			
			
			//商场存入request对象中
			request.setAttribute("xfMall", mall);
			request.getSession().setAttribute("select", "mall");
			return "/mall";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取商场信息"+e.getMessage());
		}
		request.getSession().setAttribute("select", "mall");
		return "/mall";
	}
	
	/**
	 * 保存商场
	 */
	@RequestMapping("/addMall")
	@ResponseBody
	public Object addMall(HttpServletRequest request,XfMall xfMall){
		try {
			String xfMallid = xfMall.getXfMallid();
			String xfMallname = xfMall.getXfMallname();
			System.out.println("商场编号 = "+xfMallid);
			if(StringUtil.isNUllStr(xfMallid) || StringUtil.isNUllStr(xfMallname) || xfMallid.length()>15 || xfMallname.length()>30){
				return getXfMall(request);
			}
			//保存商场
			xfMallService.saveOrupdateXfMall(xfMall);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * 用于echarts 显示中央商铺和非中央商铺
	 */
	@RequestMapping("/getStores")
	@ResponseBody
	public Object getStore(HttpServletRequest request){
		//用来存放map<中央商铺or非中央商铺,数量>
		Map<String,Object> map=new HashMap<>();
		//中央店铺的计数
		Object centerStore;
		centerStore=gwXfStoreCenterService.getAllCount();
		map.put("name", centerStore);
		//非中央店铺
		Object noCenterStore;
		noCenterStore=gwXfStoreCenterService.getNoAllCount();
		map.put("noname", noCenterStore);
		return map;
	}
	
	
	/**
	 * 用于时间段内
	 * SELECT  ISNULL(sum(netamount))=0 as money  from sales_summary where txdate like '%2017-03-15%' 
	 */
	@RequestMapping("getDateData")
	@ResponseBody
	public Map<String,String> getData(HttpServletRequest request) throws ParseException{
		salesSummaryService.delWeekSaleMoney(); // 清空表 
		ArrayList<String> day = StringUtil.test(7);
		for (String s : day) {
			WeekSaleMoney weekSaleMoney = new WeekSaleMoney();
			weekSaleMoney.setDate(s);
			String money = salesSummaryService.getMoneyByDate(s);
			weekSaleMoney.setMoney(money);
			weekSaleMoneyService.saveOrUpdateXfStaff(weekSaleMoney);
		}
		
		//原始sql 取得时间所对应的销售金额
		List<String>	list=gwXfStoreCenterService.getData();
		//获得对应的时间戳
		List<String> datas=gwXfStoreCenterService.getCurrentData();
		//时间戳转化为Sting或Date  
		SimpleDateFormat sdf1 =  new SimpleDateFormat("yyyy-MM-dd"); 
	    SimpleDateFormat sdf =  new SimpleDateFormat("MM.dd"); 
	    Date d = null ;
	    Map<String,String> m=new TreeMap<String,String>();
	    for (int i=0;i<datas.size();i++) {
	    	d=sdf1.parse(datas.get(i));
	    	String s = sdf.format(d);
	    	m.put(s, list.get(i));
		}
		return m;
		
	}
	
	
	/**
	 * 得到支付方式和一周的销售金额
	 */
	@RequestMapping("getTender")
	@ResponseBody
	public List<Map<String, Object>> getTender(HttpServletRequest request,Integer method ){
		List<Map<String, Object>> map=gwXfStoreCenterService.getTenderby7day();
		return map;
	}
	
	/**获取操作员工数量
	 * @param request
	 * @return
	 */
	@RequestMapping("getStaffNum")
	@ResponseBody
	public Integer getStaffNum(HttpServletRequest request){
		Integer totalCount=0;
		totalCount = xfStaffService.getCount(null, null);
		return totalCount;
	}
	
	/**得到员工三种身份的百分比
	 * @param request
	 * @return
	 */
	@RequestMapping("getStaffScale")
	@ResponseBody
	public List<Map<String,Object>> getStaffScale(HttpServletRequest request){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		list = gwXfStoreCenterService.getCountScale();
		return list;
	}
}
