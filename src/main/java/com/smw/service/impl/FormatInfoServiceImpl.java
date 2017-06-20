package com.smw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smw.dao.FormatInfoDao;
import com.smw.pojo.FormatInfo;
import com.smw.service.FormatInfoService;

@Service
public class FormatInfoServiceImpl implements FormatInfoService{
	
	@Resource
	private FormatInfoDao formatInfoDao;

	public void saveFormatInfo(List<FormatInfo> formatInfo) {
		formatInfoDao.saveFormatInfo(formatInfo);
	}

	public Integer getCount(String key) {
		return formatInfoDao.getCount(key);
	}

	public List<FormatInfo> getFormatInfoList(Integer pageIndex, Integer pageSize, String key) {
		return formatInfoDao.getFormatInfoList(pageIndex, pageSize, key);
	}
	
	public void delFormat(){
		formatInfoDao.delFormat();
	}
	
	/**
	 * 查询符合业态的集合
	 */
	public List<FormatInfo> getFormatInfoListByIdAndFid(Integer pageIndex, Integer pageSize,String id){
		return formatInfoDao.getFormatInfoListByIdAndFid(pageIndex, pageSize,id);
	}


}
