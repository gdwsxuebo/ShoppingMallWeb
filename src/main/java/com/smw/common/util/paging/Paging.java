package com.smw.common.util.paging;

import java.util.List;

/**
 * 分页对象
 * @author MrLiu
 * @param <E> 泛型,使用时,传入实际类型
 */
public class Paging<E> {
	private Integer pageIndex=1;		//页码(当前页)
	private Integer pageSize=5;		//页大小
	private Integer totalCount;		//总行数
	private Integer totalPage;		//总页数
	private Integer prevPage;		//上一页
	private Integer nextPage;		//下一页
	private List<E> data;			//集合数据
	private Integer startRowNum;    //每页起始行编号
	
	public Paging(Integer pageIndex, Integer pageSize, Integer totalCount,
			List<E> data) {
		if (pageIndex==null || pageSize==null) {
	        pageIndex=1;
	        pageSize=5;
        }
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.data = data;
		//求总页数
		this.totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		//求上一页
		prevPage = pageIndex<=1?1:pageIndex-1;
		//求下一页
		nextPage = pageIndex>=totalPage?totalPage:pageIndex+1;
		
		//起始行编号
		this.startRowNum=pageSize*(pageIndex-1)+1;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(Integer prevPage) {
		this.prevPage = prevPage;
	}
	public Integer getNextPage() {
		return nextPage;
	}
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}
	public List<E> getData() {
		return data;
	}
	public void setData(List<E> data) {
		this.data = data;
	}
	
	public Integer getStartRowNum() {
		return startRowNum;
	}
	public void setStartRowNum(Integer startRowNum) {
		this.startRowNum = startRowNum;
	}
	
	@Override
	public String toString() {
		return "Paging [pageIndex=" + pageIndex + ", pageSize=" + pageSize
				+ ", totalCount=" + totalCount + ", totalPage=" + totalPage
				+ ", prevPage=" + prevPage + ", nextPage=" + nextPage
				+ ", data=" + data + "]";
	}
}
