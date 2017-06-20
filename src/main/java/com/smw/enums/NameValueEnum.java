package com.smw.enums;

/**
 * 
 * NameValueEnum:枚举基本接口
 *
 * @author yumaochun
 * @date 2016年3月3日 下午3:23:53
 */
public interface NameValueEnum {

	/**
	 * 
	 * @func getDisplayName：得到名字,用于页面数据库显示
	 *
	 * @date 2016年3月3日
	 * @return
	 */
	public String getDisplayName();

	/**
	 * 
	 * @func getValue：得到值,该值对应数据库的值
	 *
	 * @date 2016年3月3日
	 * @return
	 */
	public Integer getValue();
}
