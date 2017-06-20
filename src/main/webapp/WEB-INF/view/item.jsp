<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/paging" prefix="p"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>高德睿云云POS收银系统</title>
<link rel="shortcut icon" type="image/x-icon" href="resource/ico/favicon.ico" media="screen">
<link rel="stylesheet" href="new/css/common.css">
<link rel="stylesheet" href="new/css/tableComm.css">
<link rel="stylesheet" href="new/css/goods.css">
</head>
<body>
<div id="all">
    <div id="goods">
    	<%@ include file="public/menu.jsp"%>
        <div class="right">
    	<%@ include file="public/header.jsp"%>
            <div class="middle">
                <div class="map">
                    <span class="icon position"><i></i></span>
                    <span class="mapName">商品</span>
                </div>
                <!--除以下content内容，其余地方均为模板，不要改动-->
                <div class="content">
                    <div class="title">
                    	<form class="form-search" action="web/xfItem/getItem" method="post" id="searchForm">
	                        <div class="search">
	                            <input type="text" value="${requestScope.key==null?null:requestScope.key}" id="key" name="key" placeholder="店铺、商品号、名称">
	                            <span class="searchBtn icon" id="submitSearchForm"><i></i></span>
	                        </div>
	                    </form>
	                    <div class="btn syncData" id="tongbu">同步数据</div>
                        <div class="dataDetails">同步61-MIS商品数据</div>
                    </div>
                    <div class="tableContainer">
                        <table cellpadding="0" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>货号</th>
                                    <th>店铺号</th>
                                    <th>货品描述</th>
                                    <th>货品详细描述</th>
                                    <th>库存单位</th>
                                    <th>销售单位</th>
                                    <th>实际销售</th>
                                    <th>最初零售价</th>
                                    <th>最初批发价</th>
                                    <th>当前零售价</th>
                                    <th>当前批发价</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var="xfi" items="${paging.data}" varStatus="status">
									<tr>
										<td>${paging.startRowNum+(status.index)}</td>
										<td>${xfi.xfPlu}</td>
										<td>${xfi.xfStorecode.xfStorecode}:${xfi.xfStorecode.xfName}</td>
										<td>${xfi.xfDesci}</td>
										<td>${xfi.xfLongdesc}</td>
										<td>${xfi.xfStkunit}</td>
										<td>${xfi.xfSalesunit}</td>
										<td>${xfi.xfExstk2sales}</td>
										<td>${xfi.xfOrguprice}</td>
										<td>${xfi.xfOrgwprice}</td>
										<td>${xfi.xfSeluprice}</td>
										<td>${xfi.xfSelwprice}</td>
										<td><span class="btn update" onclick="editItem('${xfi.xfPlu}');">修改</span></td>
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
    <!-- 修改 -->
    <form enctype="multipart/form-data" id="signupForm1" method="post">
	     <div class="shadow">
	     <input type="hidden" name="storeCode" id="storeCode">
	        <div class="pop">
	            <h4 class="title">修改商品信息</h4>
	            <div class="container">
	                <div class="formList">
	                    <div>
	                        <span>货号</span>
	                        <input type="text" id="xfPlu" name="xfPlu">
	                    </div>
	                    <div>
	                        <span>货品描述</span>
	                        <input type="text" id="xfDesci" name="xfDesci">
	                    </div>
	                    <div>
	                        <span>货品详细描述</span>
	                        <input type="text" class="xfLongdesc" name="xfLongdesc">
	                    </div>
	                    <div class="small">
	                        <span>库存单位</span>
	                        <input type="text" id="xfStkunit" name="xfStkunit">
	                        <span>销售单位</span>
	                        <input type="text" id="xfSalesunit" name="xfSalesunit">
	                    </div>
	                    <div>
	                        <span>实际销售数</span>
	                        <input type="text" id="xfExstk2sales" name="xfExstk2sales">
	                    </div>
	                    <div>
	                        <span>最初零售价</span>
	                        <input type="text" id="xfOrguprice" name="xfOrguprice">
	                    </div>
	                    <div>
	                        <span>最初批发价</span>
	                        <input type="text" id="xfOrgwprice" name="xfOrgwprice">
	                    </div>
	                    <div>
	                        <span>当前零售价</span>
	                        <input type="text" id="xfSeluprice" name="xfSeluprice">
	                    </div>
	                    <div>
	                        <span>当前批发价</span>
	                        <input type="text" id="xfSelwprice" name="xfSelwprice">
	                    </div>
	                    <div class="last">
	                        <span>选择店铺&nbsp;</span>
	                        <div class="select">
	                            <div class="selectContainer" id="selectValue"></div>
	                        </div>
	                        <ul id="selectUl">
	                            <li class="first">
	                                <input type="text" class="storeNameOrCode"><span class="icon search" onclick="searchStore();" >搜索</span>
	                            </li>
	                        </ul>
	                    </div>
	                </div>
	                <div class="handle">
	                    <span class="btn confirm" id="editSubmit">确定</span>
	                    <span class="btn cancel">取消</span>
	                </div>
	            </div>
	        </div>
	    </div>
    </form>
    <!-- 加载提示框 -->
    <div class="shadowstore" id="showstore">
    	 <div class="popstore" >
	          		<span >加载中，请稍后...</span>     
	        </div>
    </div>
</div>
<script type="text/javascript">
//搜索
function searchStore(){
	var storeNameOrCode = $(".storeNameOrCode").val();
	var selectUl ="<li class='first'><input type='text' class='storeNameOrCode'><span class='icon search' onclick='searchStore();'>搜索</span></li>";
	$.get("web/xfItem/getStores",{codeOrName:storeNameOrCode},function(data){
		for (var int = 0; int < data.length; int++) {
			selectUl += "<li value='"+ data[int].xfStorecode + "'>"+ data[int].xfName+ "</li>";
			$("#selectUl").html(selectUl);
		}
		//选择搜索到的店铺
		$("li").click(function (){
			$("#selectValue").html($(this).text());
			$("#storeCode").val($(this).attr('value'));
            $(this).parent().hide();
		});
	});
}

//编辑
function editItem(val){
	 $(".shadow").show();
	$.post("web/xfItem/getXfItemByXfPlu",{xfPlus : val},function(item) {
		if (item != "") {
			var action = "web/xfItem/updateItem?xfPlu="+ val;
			$("#signupForm1").attr("action", action);
			
			$("#xfPlu").val(item.xfPlu);
			$("#xfPlu").attr("disabled", true);
			$("#xfDesci").val(item.xfDesci);
			$(".xfLongdesc").val(item.xfLongdesc);
			$("#xfStkunit").val(item.xfStkunit);
			$("#xfSalesunit").val(item.xfSalesunit);
			$("#xfExstk2sales").val(item.xfExstk2sales);
			$("#xfOrguprice").val(item.xfOrguprice);
			$("#xfOrgwprice").val(item.xfOrgwprice);
			$("#xfSeluprice").val(item.xfSeluprice);
			$("#xfSelwprice").val(item.xfSelwprice);
			
			$.get("web/xfItem/getStores",function(data) {
				if (data.length > 0) {
					for (var int = 0; int < data.length; int++) {
						if (item.xfStorecode.xfStorecode == data[int].xfStorecode) {
							$("#selectValue").html(data[int].xfName);
							$("#storeCode").val(data[int].xfStorecode);
							$("<li value='"+ data[int].xfStorecode+ "'>"+ data[int].xfName+ "</li>").appendTo("#selectUl");
						} else {
							$("<li value='"+ data[int].xfStorecode+ "'>"+ data[int].xfName+ "</li>").appendTo("#selectUl");
						}
					}
				}
			});
		} else {
			alert("获取商品信息失败！");
		}
	});
}

$(function () {
    /*关闭弹窗*/
    $(".shadow .handle .cancel").click(function () {
        $(".shadow").hide();
    });

    /*点击下拉菜单*/
    $(".shadow .formList .select").click(function () {
        var select = this;
        $(this).next().show();
        $(this).next().find("li:not(.first)").click(function () {
        	$(select).find(".selectContainer").html($(this).html());
        	$("#storeCode").val($(this).attr('value'));
            $(this).parent().hide();
        });
    });
});

//查询
$("#submitSearchForm").click(function() {
	$("#searchForm").submit();
});
//同步数据
$("#tongbu").click(function(){
	if (confirm("确定更新商品信息吗?")) {
		var z = "<div class='popWarp' id='zccRES' style='width: 1899px; "
				+ "height: 100%; left: 0px; top: 0px; z-index: 999;'><div style='position: absolute;top: 50%;left: 50%;margin: -100px00-150px;width: 300px;height: 200px;z-index: 99;'><h1><i class='icon-spinner icon-spin orange bigger-125'></i></h1></div></div>";
		$(z).appendTo("body");
		/*$.get("web/xfItem/refreshItem", function(data) {
			//alert(data.msg);
			$("#zccRES").remove();
			location.href="web/xfItem/getItem";
		});*/
		
		$.ajax({
			type : "GET",//通常会用到两种：GET,POST。默认是：GET    
			url : "web/xfItem/refreshItem",//(默认: 当前页地址) 发送请求的地址    
			dataType : "application/json",//预期服务器返回的数据类型。    
			beforeSend : function() {
				$("#showstore").show();
			}, //发送请求    
			complete : function(data) {
				$("#zccRES").remove();
				location.href="web/xfItem/getItem";
			}
		});
	}
});
//分页
function page(cuPage){
	var key = $("#key").val();
	var goPage = $("#goPage").val();
	if(key == ""){
		window.location.href = "web/xfItem/getItem?pageIndex="+cuPage+"&pageSize=10";
	}else{
		window.location.href = "web/xfItem/getItem?key="+key+"&pageIndex="+cuPage+"&pageSize=10";
	}
	if(goPage != "" && goPage != null && key == ""){
		window.location.href = "web/xfItem/getItem?pageIndex="+goPage+"&pageSize=10";
	}
	if(goPage != "" && goPage != null && key != ""){
		window.location.href = "web/xfItem/getItem?key="+key+"&pageIndex="+goPage+"&pageSize=10";
	}
}

//提交修改
$("#editSubmit").click(function(){
	if($("#xfDesci").val()==""){
		$("#xfDesci").focus();
		$("#xfDesci").val("");
		$("#xfDesci").attr("placeholder", "货品描述不能为空");
	}else if($("#xfStkunit").val()==""){
		$("#xfDesci").focus();
		$("#xfDesci").val("");
		$("#xfDesci").attr("placeholder", "库存单位不能为空");
	}else if($("#xfSalesunit").val()==""){
		$("#xfSalesunit").focus();
		$("#xfSalesunit").val("");
		$("#xfSalesunit").attr("placeholder", "销售单位不能为空");
	}else if($("#xfExstk2sales").val()==""||isNaN(Number($("#xfExstk2sales").val()))){
		$("#xfExstk2sales").focus();
		$("#xfExstk2sales").val("");
		$("#xfExstk2sales").attr("placeholder", "实际销售数不能为空或有误");
	}else if($("#xfOrguprice").val()==""||isNaN($("#xfOrguprice").val())){
		$("#xfOrguprice").focus();
		$("#xfOrguprice").val("");
		$("#xfOrguprice").attr("placeholder", "最初零售价不能为空或有误");
	}else if($("#xfOrgwprice").val()==""||isNaN($("#xfOrgwprice").val())){
		$("#xfOrgwprice").focus();
		$("#xfOrgwprice").val("");
		$("#xfOrgwprice").attr("placeholder", "最初批发价不能为空或有误");
	}else if($("#xfSeluprice").val()==""||isNaN($("#xfSeluprice").val())){
		$("#xfSeluprice").focus();
		$("#xfSeluprice").val("");
		$("#xfSeluprice").attr("placeholder", "当前零售价不能为空或有误");
	}else if($("#xfSelwprice").val()==""||isNaN($("#xfSelwprice").val())){
		$("#xfSelwprice").focus();
		$("#xfSelwprice").val("");
		$("#xfSelwprice").attr("placeholder", "当前批发价不能为空或有误");
	}
	else{$("#signupForm1").submit();}
});

</script>
</body>
</html>