package com.gws.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.gws.dao.impl.GetSynchPosData;
import com.gws.dao.impl.GetSynchV61Data;
import com.gws.pojo.Item;
import com.gws.pojo.Staff;
import com.gws.pojo.Store;
import com.gws.pojo.Tender;
import com.gws.pojo.roleInfo;
/**
 * 
 * @author Administrator
 * V61同步数据到POS_SERVER 服务层
 */
@Service
public class synchv61Service {


	@Resource GetSynchPosData posData;
	@Resource GetSynchV61Data v61Data;
	
	private Logger logger = LoggerFactory.getLogger(synchv61Service.class);
	/**
	 * 同步商品数据
	 */
	public void savesynchItem(){
		List<String> insParam=new ArrayList<>();
		List<String> delParam=new ArrayList<>();
		List<String> updataSql=new ArrayList<>();
		List<Item> v61items=null;
		try {
			 v61items=v61Data.getItem();
			 
		} catch (Exception e) {
			logger.info("from v61 数据失败",e);
			e.printStackTrace();
			throw new RuntimeException("from v61 数据失败");
		}
		if(v61items==null ) return;
		List<Map<String, Object>> items=posData.getItem();


		for(Map<String, Object> posItem:items){
			String itemId=String.valueOf(posItem.get("xfPlu"));boolean delflag=false;
				for(Item v61Item:v61items){
					if(v61Item.getStyle().equals(itemId)) delflag=true;
				}
			if(!delflag) delParam.add(itemId);
		}
		String delSql=createDelSql("update xf_item set isInvalid='0' where xfPlu in", delParam);
		for(Item v61item:v61items){
			String itemId=v61item.getStyle();boolean Installfalg=false;boolean updateflag=true;
			for(Map<String, Object> item:items){
				Item positem=new Item().setDesci(String.valueOf(item.get("xfDesci")))
						.setStorecode(String.valueOf(item.get("xfStorecode")))
						.setStyle(String.valueOf(item.get("xfPlu")))
						.setItemOrgid(String.valueOf(item.get("itemOrgid")));
				if(itemId.equals(String.valueOf(item.get("xfPlu")))){
					if(!v61item.equals(positem)) updateflag=false;
					if("0".equals(String.valueOf(item.get("isInvalid")))) updateflag=false;
						Installfalg=true;
				} 
			}
			Date d=new Date();
			if(!Installfalg) insParam.add("('"+itemId+"','"+v61item.getItemOrgid()+"','"+v61item.getDesci()+"','"+v61item.getStorecode()+"',"+d.getTime()+",0.0,0.0,0.0,'',0.0,0.0,'','1')");

			if(!updateflag)  updataSql.add(" update xf_item set xfDesci='"+v61item.getDesci()+"',xfStorecode ='"+v61item.getStorecode()+"',isInvalid='1' where xfPlu='"+itemId+"';");
  
		}
		String installSql=createInstallSql("insert into xf_item(xfPlu,itemOrgId,xfDesci,xfStorecode,update_date,xfExstk2sales,xfOrguprice,xfOrgwprice,xfSalesunit,xfSeluprice,xfSelwprice,xfStkunit,isInvalid) values", insParam);
		logger.info(installSql+" 插入商品SQL ");
		logger.info(updataSql+" 更新商品SQL");
		logger.info(delSql+"删除商品SQL");

		if(delParam.size()!=0) posData.savedelSql(delSql);
		if(insParam.size()!=0) posData.saveinstallSql(installSql);
		if(updataSql.size()!=0) posData.saveupdateSql(updataSql);
		

	}
	/**
	 * 同步员工数据
	 */
	public void savesynchStaff(){
		List<Map<String, Object>> roles=posData.getRoles();
		List<String> delParam=new ArrayList<>();
		List<String> insParam=new ArrayList<>();
		List<String> updataSql=new ArrayList<>();;
		List<String> insRoleParam=new ArrayList<>();
		List<Staff> v61staffs=null;
		try {
			v61staffs=v61Data.getStaff();
		} catch (Exception e) {
			logger.info("from v61 数据失败",e);
			e.printStackTrace();
			throw new RuntimeException("from v61 数据失败");
		}
		if(v61staffs==null ) return;
		List<Map<String, Object>> posStaffs=posData.getStaff();

		for(Map<String, Object> posStaff:posStaffs){
			String itemId=String.valueOf(posStaff.get("xfStaffcode"));boolean delflag=false;
				for(Staff v61Staff:v61staffs){
					if(v61Staff.getStaffcode().equals(itemId)) delflag=true;
				}
			if(!delflag) delParam.add(itemId);
		}
		String delSql=createDelSql("update xf_staff set enabled=0 where xfStaffcode in", delParam);
		for(Staff v61item:v61staffs){
			String itemId=v61item.getStaffcode();boolean Installfalg=false;boolean updateflag=true;

			for(Map<String, Object> posStaff:posStaffs){
				String authority=String.valueOf(posStaff.get("authority"));
				String authId="0";
				if(authority.equals(roleInfo.adminRole))authId="1";
				Staff staff=new Staff().setStaffcode(String.valueOf(posStaff.get("xfStaffcode"))).setName(String.valueOf(posStaff.get("xfName")))
						.setPassword(String.valueOf(posStaff.get("xfPassword"))).setIssuestore(String.valueOf(posStaff.get("xfIssuestore")))
						.setIssuperuser(authId);
				if(itemId.equals(String.valueOf(posStaff.get("xfStaffcode")))){
					if(!v61item.equals(staff)) updateflag=false;
					if("false".equals(String.valueOf(posStaff.get("enabled"))))  updateflag=false;
						 Installfalg=true;
				} 
			}
			if(!Installfalg) {
				String v61RoleId=v61item.getIssuperuser();
				if("1".equals(v61RoleId)) ;
				else{
					insParam.add("('"+itemId+"',1,'"+v61item.getName()+"','"+v61item.getPassword()+"','"+v61item.getIssuestore()+"')");
					if(!staffRoleHasExits(v61item.getStaffcode(), roles)) insRoleParam.add("('"+roleInfo.userRole+"','"+v61item.getStaffcode()+"')");
				}

			}

			if(!updateflag)  updataSql.add(" update xf_staff set xfName='"+v61item.getName()+"',xfIssuestore='"+v61item.getIssuestore()+"',enabled=1 where xfStaffcode='"+itemId+"';");

		}
		String installSql=createInstallSql(" insert into xf_staff(xfStaffcode,enabled,xfName,xfPassword,xfIssuestore) values", insParam);
		String installRoleSql=createInstallSql("insert into xf_staff_role(authority,xfStaffcode) values", insRoleParam);
		logger.info(installSql+" 插入角色SQL ");
		logger.info(updataSql+" 更新角色SQL");
		logger.info(delSql+"删除角色SQL");
		logger.info(installRoleSql+"插入角色SQL");
		if(delParam.size()!=0) 	posData.savedelSql(delSql);
		if(insParam.size()!=0) {
			posData.saveinstallSql(installSql);
			posData.savedelSql(installRoleSql);
		}
		if(updataSql.size()!=0) posData.saveupdateSql(updataSql);
		

	
	
		
	}
	/**
	 * 同步店铺数据
	 */
	public void savesynchStore(){
		List<String> delParam=new ArrayList<>();
		List<String> insParam=new ArrayList<>();

		List<String> updataSql=new ArrayList<>();

		List<Store> v61stores=null;
		try {
			 v61stores=v61Data.getStore();

		} catch (Exception e) {
			logger.info("from v61 数据失败",e);
			e.printStackTrace();
			throw new RuntimeException("from v61 数据失败");
		}
		if(v61stores==null) return;
		List<Map<String, Object>> posStores=posData.getStore();
		for(Map<String, Object> posStore:posStores){
			String itemId=String.valueOf(posStore.get("xfStorecode"));boolean delflag=false;
				for(Store v61Item:v61stores){
					if(v61Item.getStorecode().equals(itemId)) delflag=true;
				}
			if(!delflag) delParam.add(itemId);
		}
		String delSql=createDelSql("update xf_store set isInvalid='0' where xfStorecode in", delParam);
		for(Store v61Store:v61stores){
			String itemId=v61Store.getStorecode();boolean Installfalg=false;boolean updateflag=true;
			for(Map<String, Object> posStore:posStores){
				Store store=new Store().setMallid(String.valueOf(posStore.get("xfMallid")))
						.setStorecode(String.valueOf(posStore.get("xfStorecode")))
						.setStorename(String.valueOf(posStore.get("xfName")));
				if(itemId.equals(String.valueOf(posStore.get("xfStorecode")))){
					if(!v61Store.equals(store)) updateflag=false;
					if("0".equals(String.valueOf(posStore.get("isInvalid")))) updateflag=false;
						Installfalg=true;
				} 
			}
			
			if(!Installfalg) insParam.add("(\""+itemId+"\",0,\""+v61Store.getStorename()+"\",0,0,\""+v61Store.getMallid()+"\",\"1\")");
				
			if(!updateflag)  updataSql.add(" update xf_store set xfName='"+v61Store.getStorename()+"',xfMallid ='"+v61Store.getMallid()+"',isInvalid='1' where xfStorecode='"+itemId+"';");

		}
		String installSql=createInstallSql("insert into xf_store(xfStorecode,xfCenter,xfName,xfTillcount,xfUpdate,xfMallid,isInvalid) values", insParam);
		logger.info(installSql+" 插入店铺SQL ");
		logger.info(updataSql+" 更新店铺SQL");
		logger.info(delSql+"删除店铺SQL");
		if(delParam.size()!=0) posData.savedelSql(delSql);
		if(insParam.size()!=0) posData.saveinstallSql(installSql);
		if(updataSql.size()!=0) posData.saveupdateSql(updataSql);
		

	
	}
	/**
	 * 同步支付方式
	 */
	public void savesynchTender(){
		List<String> insParam=new ArrayList<>();
		List<String> delParam=new ArrayList<>();
		List<String> updataSql=new ArrayList<>();
		List<Tender> v61tenders=null;
		try {
			 v61tenders=v61Data.getTender();
		} catch (Exception e) {
			logger.info("from v61 支付方式数据失败",e);
			e.printStackTrace();
			throw new RuntimeException("from v61  支付方式数据失败");
		}
		if(v61tenders==null) return;
		List<Map<String, Object>> posTenders=posData.getTender();
		for(Map<String, Object> posTender:posTenders){
			String tenderId=String.valueOf(posTender.get("xfTendercode"));boolean delflag=false;
				for(Tender v61tender:v61tenders){
					if(v61tender.getTendercode().equals(tenderId)) delflag=true;
				}
			if(!delflag) delParam.add(tenderId);	
		}
		String delSql=createDelSql("update xf_tender set isInvalid='0' where xfTendercode in", delParam);
		for(Tender v61tender:v61tenders){
			String itemId=v61tender.getTendercode();boolean Installfalg=false;boolean updateflag=true;
			for(Map<String, Object> tender:posTenders){
				Tender postender=new Tender().setTendercode(String.valueOf(tender.get("xfTendercode")))
						.setTenderdesc(String.valueOf(tender.get("xfTenderdesc")));				
				if(itemId.equals(String.valueOf(tender.get("xfTendercode")))){
					if(!v61tender.equals(postender)) updateflag=false;
					if("0".equals(String.valueOf(tender.get("isInvalid")))) {
						logger.info("撤销后恢复"+String.valueOf(tender.get("xfTendercode")));
						updateflag=false;
					}
						Installfalg=true;
				} 
			}
			if(!Installfalg) insParam.add("('"+itemId+"',1,'"+v61tender.getTenderdesc()+"','1')");
			if(!updateflag)  updataSql.add(" update xf_tender set xfTenderdesc='"+v61tender.getTenderdesc()+"',isInvalid='1' where xfTendercode='"+itemId+"';");

		}
		String installSql=createInstallSql("insert into xf_tender(xfTendercode,xfRefund,xfTenderdesc,isInvalid) values", insParam);
		logger.info(installSql+" 插入支付方式SQL ");
		logger.info(updataSql+" 更新支付方式SQL");
		logger.info(delSql+"删除支付方式SQL");
		if(delParam.size()!=0) posData.savedelSql(delSql);
		if(insParam.size()!=0) posData.saveinstallSql(installSql);
		if(updataSql.size()!=0) posData.saveupdateSql(updataSql);
		

		
	}
	public 	String 	createDelSql(String initSQL,List<String> delParam){
		int flag=0;
		StringBuffer buff=new StringBuffer();
		buff.append(" (");
		for(String param:delParam){
			if(flag==0) buff.append("'"+param+"'");
			else buff.append(",'"+param+"'");
			flag++;
		}
		buff.append(")");
		return initSQL+buff.toString();
	};
	public String createInstallSql(String initSQL,List<String> insParam ){
		int flag=0;
		StringBuffer buff=new StringBuffer();
		for(String param:insParam){
			if(flag==0) buff.append(param);
			else buff.append(","+param);
			flag++;
		}
		return initSQL+buff.toString();
	}
		
	public boolean staffRoleHasExits(String staffCode,List<Map<String, Object>>	roles){
		boolean res=false;
		for(Map<String, Object> staffRole:roles){
				if(String.valueOf(staffRole.get("xfStaffcode")).equals(staffCode)) {res=true;break;}
		}
		return res;
	}
}
