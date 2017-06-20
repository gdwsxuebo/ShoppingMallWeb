package com.gws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gws.dao.impl.PosCommConfigDao;
import com.gws.pojo.PosCommonConfigModel;
/**
 * 公用配置 
 */
@Service
public class PosCommConfigService {
	@Resource 
	PosCommConfigDao posCommConfigDao;
	/**
	 * 添加收银机公用配置
	 */
	public void savePosComConfig(PosCommonConfigModel comConfig){
		posCommConfigDao.save(comConfig);	
	}
	
	/**
	 * 修改收银机公用配置
	 */
	public int updateposComConfigByConid(PosCommonConfigModel comConfig) {
		return	posCommConfigDao.updateposComConfigByConid(comConfig);
	}
	
	//是否配有公用配置
	public PosCommonConfigModel getPosComConfigByConid(String conid){
		return posCommConfigDao.getPosComConfigByConid(conid);
	}
	
	
	
}
