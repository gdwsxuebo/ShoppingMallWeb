package com.smw.common.util.paging;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;


/**
 * 分页标签实现类
 * @author MrLiu
 * @since 2014-12-16-下午3:00:56
 *
 */
@SuppressWarnings("rawtypes")
public class PagingTag extends SimpleTagSupport{
	private String url;		//路径
	private Paging data;	//分页对象
	private static final String SPACE = "&nbsp;";
	private Integer step=5;	//下拉列表框显示值的递增量,默认为3
	private Integer maxPageSize = 20;	//配置下拉列表框中最多显示的值为多少,默认最大值为20行
	@Override
	public void doTag() throws JspException, IOException {
		/*System.out.println("url:"+url);
		System.out.println("data:"+data);
		System.out.println("step:"+step);*/
		/**
	
		<c:if test="${bookPaging.pageIndex==1}">
			首页
			上一页
		</c:if>
		<c:if test="${bookPaging.pageIndex!=1}">
			<a href="index.jsp?pageIndex=1&pageSize=3">首页</a>
			<a href="index.jsp?pageIndex=${bookPaging.prevPage}&pageSize=3">上一页</a>
		</c:if>
		
		<!-- 如果当前页==尾页,就没有下一页和尾页,直接显示文本 -->
		<c:if test="${bookPaging.pageIndex==bookPaging.totalPage}">
			下一页
			尾页
		</c:if>
		<c:if test="${bookPaging.pageIndex!=bookPaging.totalPage}">
			<a href="index.jsp?pageIndex=${bookPaging.nextPage}&pageSize=3">下一页</a>
			<a href="index.jsp?pageIndex=${bookPaging.totalPage}&pageSize=3">尾页</a>
		</c:if>
		
		<select onchange="changePageSize(this)">
			<option ${bookPaging.pageSize == 5?'selected':''}>5</option>
			<option ${bookPaging.pageSize == 10?'selected':''}>10</option>
			<option ${bookPaging.pageSize == 15?'selected':''}>15</option>
			<option ${bookPaging.pageSize == 20?'selected':''}>20</option>
		</select>
		 * 
		 */
		
		
		/**
		<c:if test="${bookPaging.pageIndex==1}">
			首页
			上一页
		</c:if>
		 * 
		 */
		
		/**
		 * 1.将JSP代码转换为JAVA代码
		 * 2.将所有需要输出的内容存储到临时容器中(StringBuffer)
		 * 3.输出到页面
		 */
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<div class='paging'>");
		if (data.getPageIndex()==1) {
			buffer.append("首页"+SPACE);
			buffer.append("上一页"+SPACE);
		}else {
			/**
			<a href=\"index.jsp?pageIndex=1&pageSize=3\">首页</a>
			<a href=\"index.jsp?pageIndex=${bookPaging.prevPage}&pageSize=3\">上一页</a>
			 */
			buffer.append("<a href=\""+url+"pageIndex=1&pageSize="+data.getPageSize()+"\">首页</a>"+SPACE);
			buffer.append("<a href=\""+url+"pageIndex="+data.getPrevPage()+"&pageSize="+data.getPageSize()+"\">上一页</a>"+SPACE);
		}
		
		//下一页和尾页
		//如果当前页==尾页,就没有下一页和尾页,直接显示文本
		if (data.getPageIndex()==data.getTotalPage() || data.getPageIndex().toString().equals(data.getTotalPage().toString())) {
			buffer.append("下一页"+SPACE);
			buffer.append("尾页"+SPACE);
		}else {
			/**
			<a href=\"index.jsp?pageIndex=${bookPaging.nextPage}&pageSize=3\">下一页</a>
			<a href=\"index.jsp?pageIndex=${bookPaging.totalPage}&pageSize=3\">尾页</a>
			 */
			buffer.append("<a href=\""+url+"pageIndex="+data.getNextPage()+"&pageSize="+data.getPageSize()+"\">下一页</a>"+SPACE);
			buffer.append("<a href=\""+url+"pageIndex="+data.getTotalPage()+"&pageSize="+data.getPageSize()+"\">尾页</a>"+SPACE);
		}
		
		
		//下拉列表框选择
		/**
		<select onchange=\"changePageSize(this)\">
			<option ${bookPaging.pageSize == 5?'selected':''}>5</option>
			<option ${bookPaging.pageSize == 10?'selected':''}>10</option>
			<option ${bookPaging.pageSize == 15?'selected':''}>15</option>
			<option ${bookPaging.pageSize == 20?'selected':''}>20</option>
		</select>
		 */
		buffer.append("每页显示：<select onchange=\"javascript:location.href='"+url+"pageIndex=1&pageSize='+this.value;\">");
		//location.href='index.jsp?pageIndex=1&pageSize='+obj.value;
		//System.out.println(data.getTotalCount());
		if (step>data.getTotalCount()) {
			buffer.append("<option>");
			buffer.append(step);
			buffer.append("</option>");
        }
		for (int i = step; i <= data.getTotalCount(); i+=step) {//i++ i+=1 i+=3
			//System.out.println("i:"+i+"\ttotalCount:"+data.getTotalPage());
			
			//如果每页显示的行数大于最大值,则跳出循环
			if (i>maxPageSize) {
				break;
			}
			//如果每页显示的行数大于总行数,则跳出循环
			if (i>data.getTotalCount()) {
				break;
			}
			//5,10,15,20
			/**
			i:5
			i:10
			i:15
			i:20
			for(int i=5;i<=20;i+=5){
				<option>5</option>
			
			}
			
			
			**/
			buffer.append("<option");
			if (data.getPageSize()==i) {
				buffer.append(" selected");
			}
			//System.out.println("-------------");
			buffer.append(">");
			buffer.append(i);
			buffer.append("</option>");
		}
		buffer.append("</select> 行");
		
		buffer.append(SPACE+SPACE);
		
		buffer.append("<span class='tip'>");
		//当前第几页
		buffer.append("当前第"+data.getPageIndex()+"页"+SPACE);
		//每页显示多少行数据
		buffer.append("每页显示"+data.getPageSize()+"行"+SPACE);
		//总共多少行数据
		buffer.append("总共"+data.getTotalCount()+"行数据"+SPACE);
		//总共多少页
		buffer.append("分"+data.getTotalPage()+"页显示"+SPACE);
		buffer.append("</span>");
		
		buffer.append("</div>");
		
		
		
		
		//将数据输出到页面
		this.getJspContext().getOut().print(buffer);
		
		
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Paging getData() {
		return data;
	}
	public void setData(Paging data) {
		this.data = data;
	}
	public Integer getStep() {
		return step;
	}
	public void setStep(Integer step) {
		this.step = step;
	}
	public Integer getMaxPageSize() {
		return maxPageSize;
	}
	public void setMaxPageSize(Integer maxPageSize) {
		this.maxPageSize = maxPageSize;
	}
}
