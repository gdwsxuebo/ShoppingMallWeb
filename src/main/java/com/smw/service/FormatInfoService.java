package com.smw.service;

import java.util.List;

import com.smw.pojo.FormatInfo;
import com.sun.glass.ui.Pixels.Format;

public interface FormatInfoService {

	/**
	 * 保存业态信息
	 */
	void saveFormatInfo(List<FormatInfo> formatInfo);

	/**
	 * 获取总数
	 */
	Integer getCount(String key);

	/**
	 * 获取集合
	 */
	List<FormatInfo> getFormatInfoList(Integer pageIndex, Integer pageSize, String key);
	
	/**
	 * 清空表
	 */
	void delFormat();
	
	/**
	 * 查询符合业态的集合
	 */
	List<FormatInfo> getFormatInfoListByIdAndFid(Integer pageIndex, Integer pageSize,String id);
}
