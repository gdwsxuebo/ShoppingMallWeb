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
<link rel="stylesheet" href="new/css/returnSet.css">
</head>
<body>
<div id="all">
    <div id="returnSet">
   		<%@ include file="../public/menu.jsp"%>
        <div class="right">
    	<%@ include file="../public/header.jsp"%>
            <div class="middle">
                <div class="map">
                    <span class="icon position"><i></i></span>
                    <span class="mapName">退货权限设置</span>
                </div>
                <!--除以下content内容，其余地方均为模板，不要改动-->
                <div class="content">
                    <div class="title">
                    	<form class="form-search" action="web/returnGoodsAuthority/getReturnGoodsAuthority" method="post" id="searchForm">
	                        <input type="hidden" name="auth" id="auth">
	                        <div class="search">
	                            <div class="selectContainer">
	                                <div class="select">
	                                <c:choose>
	                                	<c:when test="${requestScope.auth == 1}">
	                                		<div class="selectContainer">有权限</div>
	                                	</c:when>
	                                	<c:when test="${requestScope.auth == 0}">
	                                		<div class="selectContainer">未有权限</div>
	                                	</c:when>
	                                	<c:otherwise>
		                                    <div class="selectContainer">全部</div>
	                                	</c:otherwise>
	                                </c:choose>
	                                </div>
	                                <ul class="select_ul">
	                                    <li value="">全部</li>
	                                    <li value="1">有权限</li>
	                                    <li value="0">未有权限</li>
	                                </ul>
	                            </div>
	                            <input type="text" value="${requestScope.key==null?null:requestScope.key}" id="key" name="key" placeholder=" 员工编号、名称">
	                            <span class="searchBtn icon" id="submitSearchForm"><i></i></span>
	                        </div>
                        </form>
                    </div>
                    <div class="tableContainer">
                        <table cellpadding="0" cellspacing="0">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>员工编号</th>
                                <th>员工名称</th>
                                <th>所属店铺</th>
                                <th>是否有退货权限</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var="xfs" items="${paging.data}" varStatus="status">
	                                <tr>
	                                    <td>${paging.startRowNum+(status.index)}</td>
	                                    <td>${xfs.xfStaffcode}</td>
	                                    <td>${xfs.xfName}</td>
	                                    <td>${xfs.xfIssuestore.xfStorecode}:${xfs.xfIssuestore.xfName}</td>
	                                    <td>${xfs.isReturnGoodsAuth==1?"是":"否"}</td>
	                                   	<c:if test="${xfs.isReturnGoodsAuth == 1}">
	                                   		<td>
	                                   			<span class="btn inable" onclick="cancelAuthority('${xfs.xfStaffcode}');">取消授权</span>
	                                   		</td>
	                                   	</c:if>
	                                   	<c:if test="${xfs.isReturnGoodsAuth == 0}">
	                                   		
		                                    <td>
		                                        <span class="btn unable" onclick="authority('${xfs.xfStaffcode}');">授权</span>
		                                    </td>
	                                   	</c:if>
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
<script>
    $(function () {
        /*点击下拉菜单*/
        $(".select").click(function () {
            var select = this;
            $(this).next().show();
            $(this).next().find("li:not(.first)").click(function () {
            	$("#auth").val($(this).attr('value'))
                $(select).find(".selectContainer").html($(this).html());
                $(this).parent().hide();
            });
        });
    });

    //授权
    function authority(val){
    	$.post("web/returnGoodsAuthority/staffAuthority",{staffCode:val},function(data){
    		if(data){
    			alert("授权成功");
    			window.location.reload();
    		}else{
    			alert("授权失败");
    		}
    	})
    }
    
   //取消授权 
   function cancelAuthority(val){
    	$.post("web/returnGoodsAuthority/staffCancelAuthority",{staffCode:val},function(data){
    		if(data){
    			alert("取消授权成功");
    			window.location.reload();
    		}else{
    			alert("取消授权失败");
    		}
    	});
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
  		window.location.href = "web/returnGoodsAuthority/getReturnGoodsAuthority?pageIndex="+cuPage+"&pageSize=10";
  	}else{
  		window.location.href = "web/returnGoodsAuthority/getReturnGoodsAuthority?key="+key+"&pageIndex="+cuPage+"&pageSize=10";
  	}
  	if(goPage != "" && goPage != null && key == ""){
  		window.location.href = "web/returnGoodsAuthority/getReturnGoodsAuthority?pageIndex="+goPage+"&pageSize=10";
  	}
  	if(goPage != "" && goPage != null && key != ""){
  		window.location.href = "web/returnGoodsAuthority/getReturnGoodsAuthority?key="+key+"&pageIndex="+goPage+"&pageSize=10";
  	}
  }
</script>
</body>
</html>