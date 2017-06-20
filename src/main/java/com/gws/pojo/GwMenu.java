package com.gws.pojo;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Menu")
public class GwMenu {
	private String SQL_MENU;//菜单
	private String SQL_XF_STAFF;//员工
	private String SQL_GW_ROLE;//角色
	private String SQL_GW_ROLE_GROUP;//角色权限
	private String SQL_XF_STAFF_ROLE;//员工权限
	

	public final String getSQL_MENU() {
		return SQL_MENU;
	}

	public final void setSQL_MENU(String sQL_MENU) {
		SQL_MENU = sQL_MENU;
	}

	public final String getSQL_XF_STAFF() {
		return SQL_XF_STAFF;
	}

	public final void setSQL_XF_STAFF(String sQL_XF_STAFF) {
		SQL_XF_STAFF = sQL_XF_STAFF;
	}

	public final String getSQL_GW_ROLE() {
		return SQL_GW_ROLE;
	}

	public final void setSQL_GW_ROLE(String sQL_GW_ROLE) {
		SQL_GW_ROLE = sQL_GW_ROLE;
	}

	public final String getSQL_GW_ROLE_GROUP() {
		return SQL_GW_ROLE_GROUP;
	}

	public final void setSQL_GW_ROLE_GROUP(String sQL_GW_ROLE_GROUP) {
		SQL_GW_ROLE_GROUP = sQL_GW_ROLE_GROUP;
	}

	public final String getSQL_XF_STAFF_ROLE() {
		return SQL_XF_STAFF_ROLE;
	}

	public final void setSQL_XF_STAFF_ROLE(String sQL_XF_STAFF_ROLE) {
		SQL_XF_STAFF_ROLE = sQL_XF_STAFF_ROLE;
	}
	
	
	
	
}
