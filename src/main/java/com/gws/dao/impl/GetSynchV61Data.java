package com.gws.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gws.pojo.Item;
import com.gws.pojo.Staff;
import com.gws.pojo.Store;
import com.gws.pojo.Tender;
import com.gws.util.StringUtil;
import com.gws.util.commonUtil;
/**
 * 
 * @author Administrator
 *从V61获取服务端数据
 */
@Repository
public class GetSynchV61Data {
	private Logger logger = LoggerFactory.getLogger(GetSynchV61Data.class);
	/**
	 * 
	 * @return  V61服务端员工数据
	 */
	public List<Staff> getStaff(){
		List<Staff> staffs=new ArrayList<>();
		String sql=StringUtil.getV61Sql().getXF_STAFF();
		
		String filter_staff=StringUtil.getV61Sql().getFILTER_STAFF().trim();
		if(!StringUtils.isEmpty(filter_staff)) sql=FilterStaff(sql,filter_staff); 
		logger.info("htc 同步员工SQL"+sql);
		try (Connection con=DataSouceUtil.getInstance().openConnection();PreparedStatement pre=con.prepareStatement(sql);
				ResultSet rs=pre.executeQuery();){
			while(rs.next()){
				Staff staff=new Staff().setStaffcode(rs.getString(1)).setName(rs.getString(2))
						.setPassword(rs.getString(3)).setIssuestore(rs.getString(4)).setIssuperuser("0");
				staffs.add(staff);
			}
		} catch (Exception e) {
			logger.error("从v61获取员工数据失败：",e);
			e.printStackTrace();
		}
		return staffs;
	}
	private String FilterStaff(String sql, String filter_staff) {
		int i=0;
		StringBuffer buff=new StringBuffer();
		String[] filters=filter_staff.split(",");
		for(String fiter:filters){
			if(i==0) buff.append("'"+fiter+"'");
			else buff.append(",'"+fiter+"'");
			i++;
		}
		sql=sql+" and  xf_staffcode not in ("+buff+")";
		return sql;
	}
	/**
	 * 
	 * @return V61服务端支付方式数据
	 */
	public List<Tender> getTender(){
		List<Tender> tenders=new ArrayList<>();
		String sql=StringUtil.getV61Sql().getXF_TENDER();
		try(Connection con=DataSouceUtil.getInstance().openConnection();PreparedStatement pre=con.prepareStatement(sql);
				ResultSet rs=pre.executeQuery();) {
			while(rs.next()){
				Tender tender=new Tender().setTendercode(rs.getString(1)).setTenderdesc(rs.getString(2));
				tenders.add(tender);
			}
		} catch (Exception e) {
			logger.error("从v61获取支付方式数据失败：",e);
			e.printStackTrace();
		}
		return tenders;
		
	}
	/**
	 * 
	 * @return V61服务端商品数据
	 */
	public List<Item> getItem(){
		List<Item> items=new ArrayList<>();
		String sql=StringUtil.getV61Sql().getXF_ITEMMAS();
		try(Connection con=DataSouceUtil.getInstance().openConnection();PreparedStatement pre=con.prepareStatement(sql);
				ResultSet rs=pre.executeQuery();) {			
			while (rs.next()) {
				Item item=new Item().setStyle(rs.getString(1)).setDesci(rs.getString(2).replace("'", "''")).setStorecode(rs.getString(3)).setItemOrgid(rs.getString(4));
				items.add(item);
			}			
		} catch (Exception e) {
			logger.error("从v61获取商品信息数据失败：",e);
			e.printStackTrace();
		}
		return items;
	}
	/**
	 * 
	 * @return V61服务端店铺数据
	 */
	public List<Store> getStore(){
		List<Store> stores=new ArrayList<>();
		String sql=StringUtil.getV61Sql().getXF_STORE();
		try(Connection con=DataSouceUtil.getInstance().openConnection();PreparedStatement pre=con.prepareStatement(sql);
				ResultSet rs=pre.executeQuery();) {
			while(rs.next()){
				Store store=new Store().setMallid(StringUtil.getNsyhInfo("maillid")).setStorename(rs.getString(2).replace("'", "''")).setStorecode(rs.getString(1));
				stores.add(store);	
			}
		} catch (Exception e) {
			logger.error("从v61获取店铺信息数据失败：",e);
			e.printStackTrace();
		}
		return stores;
		
	}

}
