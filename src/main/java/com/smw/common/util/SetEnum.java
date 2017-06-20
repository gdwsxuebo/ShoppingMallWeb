package com.smw.common.util;

/**
 * 设置枚举
 * @author suen
 * 2015年11月9日
 * Version: 1.0
 */
public enum SetEnum {
	
	/**
	 * pageIndex 默认页码
	 * pageSize 默认显示数
	 */
	
	pageIndex("1"),pageSize("10");
	private final String value;
	
	SetEnum(String value) { 
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
