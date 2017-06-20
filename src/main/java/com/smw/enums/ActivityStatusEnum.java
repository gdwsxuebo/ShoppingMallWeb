package com.smw.enums;

/**
 * 
 * RoleStatusEnum:数据库表记录通用状态枚举
 *
 * @author yumaochun
 * @date 2016年3月3日 下午3:42:58
 */
public enum ActivityStatusEnum implements NameValueEnum{
	
	ENABLE(0,"禁用"),
	FORBIDDEN(1,"启用")
	;
    /**
     * 数据库对应的值
     */
    private Integer value;
	/**
	 * 页面显示的值根据id的值来显示 相应的状态 
	 */
	private String displayName;
	
	public static String getDisplayName(Integer value){
		for (ActivityStatusEnum enums : ActivityStatusEnum.values()) {
			if(enums.value == value){
				return enums.displayName;
			}
		}
		return "";
	}
	
	private ActivityStatusEnum(Integer value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}
	
	@Override
	public String getDisplayName() {
		// TODO Auto-generated method stub
		return displayName;
	}

	@Override
	public Integer getValue() {
		// TODO Auto-generated method stub
		return value;
	}

}
