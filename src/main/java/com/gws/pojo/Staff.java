package com.gws.pojo;

public class Staff {
	private String staffcode;//员工号
	private String name;//员工名称
	private String password;//员工密码
	private String issuperuser;//是否是超级管理员
	private boolean enable;
	
	public boolean isEnable() {
		return enable;
	}
	public Staff setEnable(boolean enable) {
		this.enable = enable;return this;
	}
	public String getIssuperuser() {
		return issuperuser;
	}
	public Staff setIssuperuser(String issuperuser) {
		this.issuperuser = issuperuser; return this;
	}
	private String issuestore;
	public String getStaffcode() {
		return staffcode;
	}
	public Staff setStaffcode(String staffcode) {
		this.staffcode = staffcode;return this;
	}
	public String getName() {
		return name;
	}
	public Staff setName(String name) {
		this.name = name;return this;
	}
	public String getPassword() {
		return password;
	}
	public Staff setPassword(String password) {
		this.password = password;return this;
	}

	public String getIssuestore() {
		return issuestore;
	}
	public Staff setIssuestore(String issuestore) {
		this.issuestore = issuestore;return this;
	}
	public boolean equals(Object obj) {
		if(!(obj instanceof Staff))  return false;
		Staff staff=(Staff) obj;
		return staff.name.equals(this.name)&&staff.password.equals(this.password)&&staff.issuestore.equals(this.issuestore) ?true:false;
	}

	
	
}
