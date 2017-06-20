<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/paging" prefix="p"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>高德睿云云POS收银系统</title>
<link rel="shortcut icon" type="image/x-icon" href="resource/ico/favicon.ico" media="screen">
    <link rel="stylesheet" href="new/css/common.css">
    <link rel="stylesheet" href="new/css/tableComm.css">
    <link rel="stylesheet" href="new/css/coupons.css">
</head>
<body>
<div id="all">
    <div id="coupons">
        <%@ include file="public/menu.jsp"%>
        <div class="right">
    	<%@ include file="public/header.jsp"%>
            <div class="middle parent">
                <div class="map">
                    <span class="icon position"><i></i></span>
                    <span class="mapName">促销 > 促销优惠券统计</span>
                </div>
                <!--除以下content内容，其余地方均为模板，不要改动-->
                <div class="content">
                    <div class="title">
                        	<!-- 搜索 -->
                    	<form class="form-search" action="web/promotion/getPsStatistics" method="post" id="searchForm">
	                        <div class="search">
	                            <input type="text" value="${requestScope.key==null?null:requestScope.key}" id="key" name="key" placeholder="标题">
	                            <span class="searchBtn icon" id="submitSearchForm"><i></i></span>
	                        </div>
	                    </form>
                    </div>
                    <div class="tableContainer">
                        <table cellpadding="0" cellspacing="0">
                            <thead>
	                            <tr>
	                                <th>序号</th>
	                                <th>促销规则</th>
	                                <th>发放数量合计</th>
	                                <th>使用数量合计</th>
	                                <th>发放金额统计</th>
	                                <th>使用金额统计</th>
	                                <th>店铺小计</th>
	                            </tr>
                            </thead>
                            <tbody>
	                            <c:forEach var="xfs" items="${paging.data}" varStatus="status">
									<tr>
										<td>${paging.startRowNum+(status.index)}
										<input type="hidden" name="id" value="${psST.promotionRule.id}"></td>
										<td>${xfs.promotionRule.title}</td>
										<td>${xfs.issueNumTotal}</td>
										<td>${xfs.useNumTotal}</td>
										<td>${xfs.issueMoneyTotal}</td>
										<td>${xfs.useMoneyTotal}</td>
										<td><span>店铺共计${xfs.storeNumTotal }家</span><span class="btn lookDetails" onclick="toPost(${xfs.promotionRule.id})">查看详情</span></td>
									</tr>
								</c:forEach>
                            </tbody>
                       
                        </table>
                                <!-- 分页 -->
                        <%@ include file="public/paging.jsp"%>
                    </div>
                </div>
            </div>
            <%@ include file="public/footer.jsp"%>
        </div>
    </div>
</div>
<script>
 function toPost(obj){
	 window.location.href="web/promotion/getPsStatisticsByid?id="+obj;
 }
//查询
 $("#submitSearchForm").click(function() {
 	$("#searchForm").submit();
 });



//分页
function page(cuPage){
	var key = $("#key").val();
	var goPage = $("#goPage").val();
	if(key == ""){
		window.location.href = "web/promotion/getPsStatistics?pageIndex="+cuPage+"&pageSize=10";
	}else{
		window.location.href = "web/promotion/getPsStatistics?key="+key+"&pageIndex="+cuPage+"&pageSize=10";
	}
	if(goPage != "" && goPage != null && key == ""){
		window.location.href = "web/promotion/getPsStatistics?pageIndex="+goPage+"&pageSize=10";
	}
	if(goPage != "" && goPage != null && key != ""){
		window.location.href = "web/promotion/getPsStatistics?key="+key+"&pageIndex="+goPage+"&pageSize=10";
	}
}
</script>
</body>
</html>