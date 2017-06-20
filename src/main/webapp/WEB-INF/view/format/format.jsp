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
</head>
<body>
<div id="all">
    <div id="store">
    	<%@ include file="../public/menu.jsp"%>
        <div class="right">
    	<%@ include file="../public/header.jsp"%>
            <div class="middle">
                <div class="map">
                    <span class="icon position"><i></i></span>
                    <span class="mapName">业态信息</span>
                </div>
                <!--除以下content内容，其余地方均为模板，不要改动-->
                <div class="content">
                    <div class="title">
                       	<div class="btn syncData" id="tongbu">同步数据</div>
                        <div class="dataDetails">同步61-MIS业态信息数据</div>
                    </div>
                    <div class="tableContainer">
                        <table cellpadding="0" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>编号</th>
                                    <th>名称</th>
                                    <th>状态</th>
                                    <th>时间</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var="bi" items="${paging.data}" varStatus="status">
									<tr>
										<td>${paging.startRowNum+(status.index)}</td>
										<td>${bi.bm}</td>
										<td>${bi.name}</td>
										<td>${bi.state==1?"正常":"删除"}</td>
										<td>${bi.time}</td>
									</tr>
								</c:forEach>
                            </tbody>
                        </table>
                         <!-- 分页 -->
                        <%@ include file="../public/paging.jsp"%>
                    </div>
                </div>
            </div>
            <%@ include file="../public/footer.jsp"%>
        </div>
    </div>
</div>
<script type="text/javascript">
//查询
$("#submitSearchForm").click(function() {
	$("#searchForm").submit();
});
//同步数据
$("#tongbu").click(function(){
	if (confirm("确定更新业态信息吗?")) {
		var z = "<div class='popWarp' id='zccRES' style='width: 1899px; "
				+ "height: 100%; left: 0px; top: 0px; z-index: 999;'><div style='position: absolute;top: 50%;left: 50%;margin: -100px00-150px;width: 300px;height: 200px;z-index: 99;'><h1><i class='icon-spinner icon-spin orange bigger-125'></i></h1></div></div>";
		$(z).appendTo("body");
		$.get("web/format/refreshFormat", function(data) {
			//alert(data.msg);
			$("#zccRES").remove();
			location.href="web/format/getFormat";
		});
	}
});

//分页
function page(cuPage){
	var goPage = $("#goPage").val();
	window.location.href = "web/format/getFormat?pageIndex="+cuPage+"&pageSize=10";
	if(goPage != "" && goPage != null){
		window.location.href = "web/format/getFormat?pageIndex="+goPage+"&pageSize=10";
	}
}
</script>
</body>
</html>