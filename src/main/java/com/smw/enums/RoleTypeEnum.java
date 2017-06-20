package com.smw.enums;

/**
 * 
 * RoleStatusEnum:数据库表记录通用状态枚举
 *
 * @author yumaochun
 * @date 2016年3月3日 下午3:42:58
 */
public enum RoleTypeEnum implements NameValueEnum{
	
	SERVICE(1,"service端"),
	POS(2,"pos端"),
	DOUBLE(3,"service端和pos端")
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
		for (RoleTypeEnum enums : RoleTypeEnum.values()) {
			if(enums.value == value){
				return enums.displayName;
			}
		}
		return "";
	}
	
	private RoleTypeEnum(Integer value, String displayName) {
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
