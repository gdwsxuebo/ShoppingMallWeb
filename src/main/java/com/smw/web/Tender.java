package com.smw.web;

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
import com.smw.pojo.XfTender;
import com.smw.service.SetService;
import com.smw.service.XfTenderService;

/**
 * 付款方式
 * @author suen
 * @date 2016年5月26日-下午4:52:38
 * @version 1.0
 */
@Controller
@RequestMapping("web/xfTender")
public class Tender {
	Logger logger = LoggerFactory.getLogger(Tender.class);
	
	@Resource
	private XfTenderService xfTenderService;
	
	@Autowired SetService setService;
	
	@Autowired synchv61Service synchv61;
	
	/**
	 * 获取付款方式
	 * @param request
	 * @param pageIndex 页码
	 * @param pageSize 每页显示的行数
	 * @return
	 */
	@RequestMapping("getTender")
	public Object getTender(HttpServletRequest request,Integer pageIndex,Integer pageSize){
		try {
			if (pageIndex == null) {
				pageIndex = Integer.parseInt(SetEnum.pageIndex.getValue());
			}
			if (pageSize==null){
	            pageSize=Integer.parseInt(SetEnum.pageSize.getValue());
	        }
			List<XfTender>  xfTenders=xfTenderService.getXfTenderList(pageIndex,pageSize);
			List<XfTender>  xfTendersAll=xfTenderService.selectTenderList();

			//封装到分页对象中
			Paging<XfTender> paging=new Paging<>(pageIndex, pageSize, xfTendersAll==null?0:xfTendersAll.size(), xfTenders);
			//放在request中
			request.getSession().setAttribute("paging", paging);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取付款方式"+e.getMessage());
		}
		//设置菜单选中项
		request.getSession().setAttribute("select", "tender");
		return "/tender";
	}
	
	@Resource
	private JoinMis joinMis;
	
	/**
	 * 更新付款方式信息
	 * @param request
	 * @return
	 */
	@RequestMapping("refreshTender")
	@ResponseBody
	public Object refreshTender(HttpServletRequest request){
		PropertiesUtil pUtil = new PropertiesUtil("systemConfig.properties");
		String misorv61;
		misorv61 = pUtil.readProperty("gdws.ifs.misorv61");
		Map<String, Object > map=new HashMap<>();
		try {
			
			if(misorv61.equals("MIS")){
				Map<String, Object> ten= (Map<String, Object>) joinMis.tender(request);
				if("1".equals(ten.get("code").toString())){
					map.put("flag", true);
					map.put("msg", "更新付款方式信息成功！请刷新！");
					Sets sets = new Sets();
					sets.setId("updateTime");
					sets.setValue(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
					setService.saveOrUpdateSets(sets);
					return map;
				}
			}else{
				synchv61.savesynchTender();
				logger.info("同步支付方式信息成功----------------");
				map.put("flag", true);
				map.put("msg", "更新付款方式信息成功！请刷新！");
				Sets sets = new Sets();
				sets.setId("updateTime");
				sets.setValue(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
				setService.saveOrUpdateSets(sets);
				return map;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新付款方式"+e.getMessage());
		}
		map.put("flag", false);
		map.put("msg", "更新付款方式信息失败！");
		return map;
	}
	
	/**
	 * 添加或者修改支付方式
	 * @param request
	 * @param pageIndex 页码
	 * @param pageSize 每页显示的行数
	 * @param xfTender 付款方式对象
	 * @return
	 */
	@RequestMapping("addOrUpdeteTender")
	public Object addOrUpdeteTender(HttpServletRequest request,Integer pageIndex,Integer pageSize,String xfTendercode
			,String xfTenderdesc,Boolean xfRefundBo,String xfCode){
		try {
			if (StringUtil.isNUllStr(xfCode) && (StringUtil.isNUllStr(xfTendercode) || StringUtil.isNUllStr(xfTenderdesc) 
					|| xfTendercode.length()>15 || xfTenderdesc.length()>50)) {
				return getTender(request, pageIndex, pageSize);
			}else{
				XfTender tender=new XfTender();
				//设置付款方式编码
				tender.setXfTendercode(xfTendercode==null?xfCode:xfTendercode);
				//设置付款方式描述
				tender.setXfTenderdesc(xfTenderdesc);
				tender.setIsInvalid("1");
				//设置是否可以退款
				if(xfRefundBo!=null && xfRefundBo){
					tender.setXfRefund(xfRefundBo);
				}else{
					tender.setXfRefund(false);
				}
				//保存或者修改
				xfTenderService.saveOrupdateTender(tender);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加或者修改支付方式"+e.getMessage());
		}
		return getTender(request, pageIndex, pageSize);
	}
	
	/**
	 * 删除付款方式
	 * @param xfTendercode 付款方式编号
	 * @return
	 */
	@RequestMapping("deleteXfTenderByXfTendercode")
	@ResponseBody
	public Object deleteXfTenderByXfTendercode(String xfTendercode){
		try {
			if(StringUtil.isNUllStr(xfTendercode)){
				return false;
			}
			//删除
			xfTenderService.deleteXfTenderByXfTendercode(xfTendercode);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取支付方式"+e.getMessage());
		}
		return false;
	}
	
	/**
	 * 获取付款方式
	 * @param xfTenderCode 付款方式编号
	 * @return
	 */
	@RequestMapping("getXfTender")
	@ResponseBody
	public Object getXfTender(String xfTenderCode){
		return xfTenderService.getXfTenderByCode(xfTenderCode);
	}
	
}
