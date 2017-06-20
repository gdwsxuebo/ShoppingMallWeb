package com.smw.common.util;

import java.io.Serializable;
/**
 * 
 * PageInfo:分页基本信息公用类
 *
 * @author yumaochun
 * @date 2016年3月3日 下午4:20:12
 */
public class PageInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7675476775537770054L;
	
	/**
	 * 总记录数
	 */
	private Integer totalRecord;
	/**
	 * 每页显示多少条记录数
	 */
	private Integer pageSize;
	/**
	 * 总共有多少页
	 */
	private Integer totalPage;
	/**
	 * 当前是第几页
	 */
	private Integer currentPage;
	/**
	 * 是否还有下一页，0-没有 1-有
	 */
	private String hasMore;
	public Integer getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public String getHasMore() {
		return hasMore;
	}
	public void setHasMore(String hasMore) {
		this.hasMore = hasMore;
	}
	
	
}
