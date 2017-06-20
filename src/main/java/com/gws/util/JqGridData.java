package com.gws.util;

public class JqGridData {
	private	Object rows;
	private	int	currentPage; 
	private int totalPageSize;
	private int totalRecords;
	private String userdata;
	private int pageSize;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		if(totalRecords<=pageSize) setTotalPageSize(1);
		else {
			int pagesize=(totalRecords/pageSize)+1;
			setTotalPageSize(pagesize);
		}
	}
	public Object getRows() {
		return rows;
	}
	public JqGridData setRows(Object rows) {
		this.rows = rows;return this;
	}

	
	public int getCurrentPage() {
		return currentPage;
	}
	public JqGridData setCurrentPage(int currentPage) {
		this.currentPage = currentPage;return this;
	}
	public int getTotalPageSize() {
		return totalPageSize;
	}
	public JqGridData setTotalPageSize(int totalPageSize) {
		this.totalPageSize = totalPageSize;return this;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public JqGridData setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;return this;
	}
	public String getUserdata() {
		return userdata;
	}
	public JqGridData setUserdata(String userdata) {
		this.userdata = userdata;return this;
	}
	
}
