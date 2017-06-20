<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="handleSec">
	<div class="pageDetails">
		<span>每页${paging.pageSize}条，当前第${paging.pageIndex}/${paging.totalPage}页，一共${paging.totalCount}条数据，跳转到</span>
		<input class="pageToJump" type="text" id="goPage" class="goPage">
		<input  type="hidden" id="totalPage" value="${paging.totalPage}">
		<span class="btn" onclick="page(1);" id="gotopage">Go</span>
	</div>
	<div class="pageContainer">
		<!-- 判断总页数是否超过10页 -->
		<c:if test="${paging.totalPage > 10}">
			<c:choose>
				<c:when test="${paging.pageIndex > 6}" >
					<c:choose>
						<c:when test="${paging.totalPage > (paging.pageIndex+5)}">
							<c:if test="${paging.pageIndex != 1}">
								<span class="btn icon prev" onclick="page(1);"><i></i></span>
							</c:if>
							<span><i>...</i></span>
							<c:forEach begin="${paging.pageIndex - 5}" end="${paging.pageIndex + 5}" var="onlyTen">
								<c:choose>
									<c:when test="${paging.pageIndex == onlyTen}">
										<span class="btn page" style="background-color: rgb(101, 206, 167);" onclick="page(${onlyTen});">${onlyTen}</span>
									</c:when>
									<c:otherwise>
										<span class="btn page" onclick="page(${onlyTen});">${onlyTen}</span>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<span><i>...</i></span>
							<c:if test="${paging.pageIndex != paging.totalPage}">
								<span class="btn icon next" onclick="page(${paging.totalPage});"><i></i></span>
							</c:if>
						</c:when>
						<c:otherwise>
							<c:if test="${paging.pageIndex != 1}">
								<span class="btn icon prev" onclick="page(1);"><i></i></span>
							</c:if>
							<span><i>...</i></span>
							<c:forEach begin="${paging.pageIndex-5}" end="${paging.totalPage}" var="gtsix">
								<c:choose>
									<c:when test="${paging.pageIndex == gtsix}">
										<span class="btn page" style="background-color: rgb(101, 206, 167);" onclick="page(${gtsix});">${gtsix}</span>
									</c:when>
									<c:otherwise>
										<span class="btn page" onclick="page(${gtsix});">${gtsix}</span>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:if test="${paging.pageIndex != 1}">
						<span class="btn icon prev" onclick="page(1);"><i></i></span>
					</c:if>
					<!-- 显示1-10页 -->
					<c:forEach begin="1" end="10" var="ten">
						<c:choose>
							<c:when test="${paging.pageIndex == ten}">
								<span class="btn page" style="background-color: rgb(101, 206, 167);" onclick="page(${ten});">${ten}</span>
							</c:when>
							<c:otherwise>
								<span class="btn page" onclick="page(${ten});">${ten}</span>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<span><i>...</i></span>
					<!-- 如果是最后一页， 不显示 -->
					<c:if test="${paging.pageIndex != paging.totalPage}">
						<span class="btn icon next" onclick="page(${paging.totalPage});"><i></i></span>
					</c:if>
				</c:otherwise>
			</c:choose>
		</c:if>
		<!-- 总页数在1-9 -->
		<c:if test="${paging.totalPage > 1 && paging.totalPage <= 10}">
			<c:if test="${paging.pageIndex != 1}">
				<span class="btn icon prev" onclick="page(1);"><i></i></span>
			</c:if>
			<c:forEach begin="1" end="${paging.totalPage}" var="index">
				<c:choose>
					<c:when test="${paging.pageIndex == index}">
						<span class="btn page" style= " background-color: rgb(101, 206, 167);" onclick="page(${index});">${index}</span>
					</c:when>
					<c:otherwise>
						<span class="btn page" onclick="page(${index});">${index}</span>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${paging.pageIndex != paging.totalPage}">
				<span class="btn icon next" onclick="page(${paging.totalPage});"><i></i></span>
			</c:if>
		</c:if>
	</div>
</div>
<script type="text/javascript">
$(function () {
    $("#goPage").keyup(function () {
        //如果输入非数字，则替换为''，如果输入数字，则在每4位之后添加一个空格分隔
        this.value = this.value.replace(/[^\d]/g, '');
        var goPage = $("#goPage").val();
  		 var totalp=$("#totalPage").val();
  		goPage=Number(goPage);
  		totalp=Number(totalp);
       if(goPage>totalp){
       	$("#goPage").val(totalp);
       }
      
    })
});

</script>