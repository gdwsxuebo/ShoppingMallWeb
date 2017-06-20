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
                 
                    <span class="mapName">促销 > 促销优惠券统计 > 店铺小计</span>
                </div>
                <div class="content">
                    <div class="title">
                          <form class="form-search" action="web/promotion/getPsStatisticsByid" method="post" id="searchForm">
	                        <div class="search">
	                        <input type="hidden" value=${rid } class='rid' name='id'>
	                            <input type="text" value="${requestScope.key==null?null:requestScope.key}" id="key" name="key" placeholder="促销规则">
	                            <span class="searchBtn icon" id="submitSearchForm"><i></i></span>
	                        </div>
	                    </form>
                        <div class="btn back" onclick="toStamp();">返回</div>
                    </div>
                    <div class="tableContainer">
                        <table cellpadding="0" cellspacing="0">
                            <thead>
                            <tr>
                               <th>序号</th>
                                <th>店铺</th>
                                <th>发放数量小计</th>
                                <th>使用数量小计</th>
                                <th>发放金额小计</th>
                                <th>使用金额小计</th>
                                <th>所属促销规则</th>
                            </tr>
                            </thead>
                            <tbody  id="dopost">
                            <tr>
                            
								<c:forEach var="psSST" items="${paging.data}" 	varStatus="status">
																		<tr>
																		<td>${paging.startRowNum+(status.index)}</td>
																			<td>${psSST.xfStore.xfStorecode}:${psSST.xfStore.xfName}</td>
																			<td>${psSST.issueNumSubtotal }</td>
																			<td>${psSST.useNumSubtotal }</td>
																			<td>${psSST.issueMoneySubtotal }</td>
																			<td>${psSST.useMoneySubtotal }</td>
																			<td>${psSST.title}</td>
																		</tr>
																	</c:forEach>
                            </tr>
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

function toStamp(){
	
	window.location.href="web/promotion/getPsStatistics";
}
//查询
$("#submitSearchForm").click(function() {
	$("#searchForm").submit();
});

//分页
function page(cuPage){
	var id = $(".rid").val();
	var key = $("#key").val();
	var goPage = $("#goPage").val();
	if(key == ""){
		window.location.href = "web/promotion/getPsStatisticsByid?id="+id+"&pageIndex="+cuPage+"&pageSize=10";
	}else{
		window.location.href = "web/promotion/getPsStatisticsByid?id="+id+"&key="+key+"&pageIndex="+cuPage+"&pageSize=10";
	}
	if(goPage != "" && goPage != null && key == ""){
		window.location.href = "web/promotion/getPsStatisticsByid?id="+id+"&pageIndex="+goPage+"&pageSize=10";
	}
	if(goPage != "" && goPage != null && key != ""){
		window.location.href = "web/promotion/getPsStatisticsByid?id="+id+"&key="+key+"&pageIndex="+goPage+"&pageSize=10";
	}
}

</script>
</body>
</html>