package com.gws.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gws.pojo.PaymentRent;
import com.gws.service.impl.GdwsMisService;
import com.gws.service.impl.PaymentRentService;
import com.gws.util.JqGridData;
import com.smw.common.util.BaseResultVo;

@Controller
@RequestMapping("/web")
public class PaymentRentController {
	
	@Autowired
	private GdwsMisService gdwsMisService;
	@Autowired
	private PaymentRentService paymentRentService;
	
	@RequestMapping("/paymentRent")
	public String paymentRent(HttpServletRequest request, Integer pageIndex, Integer pageSize, String key){
		request.getSession().setAttribute("select", "rent");
		return "/rent";
	}
	
	/**列表**/
	@RequestMapping("/paymentRentList")
	@ResponseBody
	public JqGridData paymentRentList(HttpServletRequest request,Integer page,Integer rows){
		JqGridData jqGrid=new JqGridData();
		List<Map<String, Object>> paymentRentList = paymentRentService.getPaymentRentList(page, rows);
		int count = paymentRentService.getCount();
		jqGrid.setRows(paymentRentList);
		jqGrid.setTotalRecords(count);
		jqGrid.setPageSize(rows);
		return jqGrid;
	}
	
	/**保存**/
	@RequestMapping("/savePaymentRent")
	@ResponseBody
	public BaseResultVo savePaymentRent(HttpServletRequest request){
		//清空表
		paymentRentService.deleteAll();
		
		String fullFileName = "E:/rent.json";
        File file = new File(fullFileName);
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
         
		PaymentRent pr = new PaymentRent();
		//String paymentRent = gdwsMisService.getPaymentRent();
		JSONObject obj=JSONObject.parseObject(buffer.toString());
		JSONObject status = obj.getJSONObject("status");
		if(status != null && status.getString("code").equals("10000")){
			JSONArray arr =	obj.getJSONArray("data");
			Iterator<Object> itr = arr.iterator();
			while(itr.hasNext()){
				JSONObject js=	(JSONObject) itr.next();
				pr.setRentId(Integer.valueOf(js.getString("id")));
				pr.setCode(js.getString("code"));
				pr.setName(js.getString("name"));
				pr.setRate(js.getString("rate"));
				pr.setNote(js.getString("note"));
				if(js.getString("orderid") != null && js.getString("orderid") != ""){
					pr.setOrderid(Integer.valueOf(js.getString("orderid")));
				}
				pr.setCreateTime(js.getString("createTime"));
				if(js.getString("createUserId") != null && js.getString("createUserId") != ""){
					pr.setCreateUserId(Integer.valueOf(js.getString("createUserId")));
				}
				pr.setUpdateTime(js.getString("updateTime"));
				if(js.getString("updateUserId") != null && js.getString("updateUserId") != ""){
					pr.setUpdateUserId(Integer.valueOf(js.getString("updateUserId")));
				}
				pr.setCreateUserName(js.getString("createUserName"));
				pr.setUpdateUserName(js.getString("updateUserName"));
				paymentRentService.savePaymentRent(pr);
			}
		}else{
			return BaseResultVo.responseFail("失败");
		}
		return BaseResultVo.responseSuccess("成功");
	}

}
