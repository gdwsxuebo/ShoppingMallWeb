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
<link rel="stylesheet" href="new/css/store.css">

 <link rel="stylesheet" href="resource/assets/css/storeace.css" />
</head>
<body>
<div id="all">
    <div id="store">
    	<%@ include file="public/menu.jsp"%>
        <div class="right">
    	<%@ include file="public/header.jsp"%>
            <div class="middle">
                <div class="map">
                    <span class="icon position"><i></i></span>
                    <span class="mapName">商铺</span>
                </div>
                <!--除以下content内容，其余地方均为模板，不要改动-->
                <div class="content">
                    <div class="title">
                    	<form class="form-search" action="web/xfStore/getStore" method="post" id="searchForm">
	                        <div class="search">
	                            <input type="text" value="${requestScope.key==null?null:requestScope.key}" id="key" name="key" placeholder="店铺编号、名称">
	                            <span class="searchBtn icon" id="submitSearchForm"><i></i></span>
	                        </div>
	                    </form>
	                    <div class="btn syncData" id="tongbu">同步数据</div>
                        <div class="dataDetails">同步61-MIS商铺数据</div>
                    </div>
                    <div class="tableContainer">
                        <table cellpadding="0" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>商铺编号</th>
                                    <th>商铺名称</th>
                                    <th>所属商场</th>
                                    <th>POS数量</th>
                                    <th>是否中央商铺</th>
                                    <th>所属中央商铺</th>
                                    <th>下属收银机</th>
                                </tr>
                            </thead>
                            <tbody>
								<c:forEach var="xfs" items="${paging.data}" varStatus="status">
	                                <tr>
	                                    <td>${paging.startRowNum+(status.index)}</td>
	                                    <td>${xfs.xfStorecode}</td>
	                                    <c:choose>
											<c:when
												test="${sessionScope.XfStaff.staffRole.authority=='ROLE_MALL_ADMIN'}">
												<c:choose>
													<c:when test="${sessionScope.isOpenOldMis=='true'}">
														<td style="vertical-align: middle;"
															ondblclick=edit(this,2);>${xfs.xfName }</td>
													</c:when>
													<c:otherwise>
														<td style="vertical-align: middle;">${xfs.xfName }</td>
													</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
												<td style="vertical-align: middle;">${xfs.xfName }</td>
											</c:otherwise>
										</c:choose>
	                                    <td>${xfs.xfMallid.xfMallname}</td>
	                                    <td>${xfs.xfTillcount}</td>
	                                    <td><label style="margin-right: 40%;"
														class="pull-right inline">
														<input ${xfs.xfCenter==true?"checked='checked'":""}
															${sessionScope.XfStaff.staffRole.authority=='ROLE_MALL_ADMIN'?"":"disabled='disabled'"}
															${xfs.xfCenterStore!=null?"disabled='disabled'":"" } type="checkbox"
															class="ace ace-switch ace-switch-5"
															onclick="xfStoreCenter(this,'${xfs.xfStorecode }');" ${requestScope.misorv61=='MIS'?"disabled='true'":""}  id="chec${xfs.xfStorecode}">
														<span class="lbl"></span>
													</label></td>
	                                    <td id="xfN${xfs.xfStorecode}">
		                                    ${xfs.xfCenterStore.xfName}
		                                    <c:if test="${sessionScope.XfStaff.staffRole.authority=='ROLE_MALL_ADMIN'}">
												<c:if test="${xfs.xfCenterStore!=null}">
													<button style="border: 1px solid rgb(240,173,78); color: rgb(240,173,78); width: 40px; height: 22px; border-radius: 3px; background-color: white" onclick="deleteXFSC(this,'${xfs.xfStorecode}','${xfs.xfCenterStore.xfStorecode }');">
														取消
													</button>
												</c:if>
											</c:if>
										</td>
										<td>
										<!--  	<c:if test="${xfs.xfCenter==true}">
		                                    	<span class="setCenterStore" onclick="configureCenterStore('${xfs.xfStorecode}')">配置子店铺</span>
		                                    </c:if>-->
		                                   <span style="border: 1px solid rgb(240,173,78);color: rgb(240,173,78);display: inline-block;width: 40px;text-align: center;border-radius: 5px;line-height: 20px;cursor:pointer;" onclick="showtillids('${xfs.xfStorecode}');">查看</span>
	                                    </td>
	                                    
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
    
    <div class="shadow">
        <div class="pop">
            <h4 class="title">配置中央店铺</h4>
            <div class="container">
<!--                 <div class="search"><input type="text" id="searchName"><span class="btn searchBtn" id="cneterStoreSearch">搜索</span></div> -->
                <div class="storeList" id="notUseStore">
                    
                </div>
                <div class="handle">
                    <span class="btn confirm" onclick="subStoreCenterStore();">确定</span>
                    <span class="btn cancel">取消</span>
                </div>
            </div>
        </div>
    </div>
    
    <!-- 查看下属收银机-->
      <form action="web/storeAuth/addOrUpdateStoreAuth" method="post" id="showauths">
    	<input type="hidden" name="storeCode" id="hiddenCode"> 
    	<input type="hidden" name="storeName" id="hiddenName">
	    <div class="shadow" id="showauth">
	    <input type="hidden" name="showauth" id="hiddenId"> 
	        <div class="pop addSale" >
	            <h4 class="title">下属收银机</h4>
	            <div style="height: auto;">
	            <div class="container" id="showauthsstores"> 
	            	
	            </div>
	             <div class="handle" style="margin-bottom: 5px;">   
	                   <span class="btn cancel" id="showauthCancel">取消</span> 
	              </div>
	              
	            </div>
	        </div>
	    </div>
    </form>
    
    <!-- 加载提示框 -->
    <div class="shadow" id="showstore">
    	 <div class="popstore" >
	          		<span >加载中，请稍后...</span> 
	        </div>
    </div>
    
    
</div>
<script type="text/javascript">




//查看下属店铺
function showtillids(xfStorecode){
	
	//获取店铺
	$.get("web/storeAuth/getTillidListBystorecode",{storecode:xfStorecode},function(data){
		console.log(data);
		if(data.length > 0){
			for(var i=0;i<data.length;i++){
				$("#showauthsstores").append('<p style="margin-left: 100px;text-align:left">收银机编号:'+data[i].tillid+'</p>');
			}
		}else{
			$("#showauthsstores").append('<p>该店铺暂无下属收银机</p>');
		}
	});
	
	$("#showauth").show();
	//取消
	$("#showauth").click(function (e) {
        if(!$(e.target).hasClass("pop") && $(e.target).parents(".pop").length === 0){
            $(this).hide();
        }
    });
   $("#showauthCancel").click(function () {
	   $("#showauthsstores").empty();
       $("#showauth").hide();
   });
}


var storeid;
function configureCenterStore(storecode){
	storeid=storecode;
	//弹框
	$(".shadow").show();
   
    $(".shadow .handle .cancel").click(function () {
    	 window.location.href = "web/xfStore/getStore?pageIndex="+${paging.pageIndex}+"&pageSize=10";
    });
    //获取店铺数据  此控制层在  gws -> controller -> GwStoreController
    $.post('web/getAllStoreCenterStore',{'storecode':storecode},function(data){
		var i=0;
		var tempContext="";
		$.each(data.data,function(index,item){
			if(item.ischildren==item.xfStorecode){
				tempContext=tempContext+"<li><input type='checkbox' name='selectStores' value='"+item.xfStorecode+"' checked='checked'/> "+item.xfName+"</li>";
			}else{
				tempContext=tempContext+"<li><input type='checkbox' name='selectStores'  value='"+item.xfStorecode+"' /> "+item.xfName+"</li>";
			}
			i++;
			if(i==2){
				$("#notUseStore").append("<ul>"+tempContext+"</ul>");	
				i=0;
				tempContext="";
			}
		});
		
	    // 搜索
	   /*  $("#cneterStoreSearch").click(function(){
	    	$.post("web/getStoreByName",{storeName:$("#searchName").val(),storecode:storecode},function(data){
	    		var tempContext="";
	    		$.each(data,function(index,item){
	    			tempContext=tempContext+"<li><input type='checkbox' name='selectStores'  value='"+item.xfStorecode+"' /> "+item.xfName+"</li>";
	    			$("#notUseStore").html("<ul>"+tempContext+"</ul>");	
	    		}); 
	    	});
	    }); */
	    
	});
}

//配置中央店铺
function subStoreCenterStore(){
	var stores=new Array();
	$('input:checked').each(function() {
		stores.push($(this).val());
	});
	// 更新店铺所属的中央店铺    此控制层在  gws -> controller -> GwStoreController
	$.post('web/updateStoreCenterStore',{'stores':stores.join(','),'storeid':storeid},function(data){
		$(".shadow").hide();  //更新成功后隐藏弹框
		 if(data.status.code=="10000"){
			 alert("添加成功");
	    	 location.reload();
		 }else{
	    	alert("添加失败");
		} 
	});
}


//查询
$("#submitSearchForm").click(function() {
	$("#searchForm").submit();
});


//同步数据

	$("#tongbu")
			.click(
					function() {
						if (confirm("确定更新商铺吗?")) {
							var z = "<div class='popWarp' id='zccRES' style='width: 1899px; "
									+ "height: 100%; left: 0px; top: 0px; z-index: 999;'><div style='position: absolute;top: 50%;left: 50%;margin: -100px00-150px;width: 300px;height: 200px;z-index: 99;'><h1><i class='icon-spinner icon-spin orange bigger-125'></i></h1></div></div>";
							$(z).appendTo("body");
							/*	$.get("web/xfStore/refreshStore", function(data) {
									$("#zccRES").remove();
									location.href = "web/xfStore/getStore";
								});*/

							$.ajax({
								type : "GET",//通常会用到两种：GET,POST。默认是：GET    
								url : "web/xfStore/refreshStore",//(默认: 当前页地址) 发送请求的地址    
								dataType : "application/json",//预期服务器返回的数据类型。    
								beforeSend : function() {
									$("#showstore").show();
								}, //发送请求    
								complete : function(data) {
									$("#zccRES").remove();
									location.href = "web/xfStore/getStore";
								}
							});
						}
					});

	//取消店铺所属的中央店铺
	function deleteXFSC(obj, xfStorecode, xfCenterStorecode) {
		if (confirm("确定取消关联吗？")) {
			$.post("web/xfStore/deleteXFSC", {
				xfStorecode : xfStorecode,
				xfCenterStorecode : xfCenterStorecode
			}, function(data) {
				if (data == true) {
					$(obj).remove();
					$("#xfN" + xfStorecode).html("");
					$("#chec" + xfStorecode).attr("disabled", false);
					$(OBJ).prop("checked", false);
				} else {
					alert("取消关联店铺失败！");
				}
			});
		}
	}

	//分页
	function page(cuPage) {
		var key = $("#key").val();
		var goPage = $("#goPage").val();
		if (key == "") {
			window.location.href = "web/xfStore/getStore?pageIndex=" + cuPage
					+ "&pageSize=10";
		} else {
			window.location.href = "web/xfStore/getStore?key=" + key
					+ "&pageIndex=" + cuPage + "&pageSize=10";
		}
		if (goPage != "" && goPage != null && key == "") {
			window.location.href = "web/xfStore/getStore?pageIndex=" + goPage
					+ "&pageSize=10";
		}
		if (goPage != "" && goPage != null && key != "") {
			window.location.href = "web/xfStore/getStore?key=" + key
					+ "&pageIndex=" + goPage + "&pageSize=10";
		}
	}

	function xfStoreCenter(obj, xfStorecode) {
		OBJ = obj;
		// alert(xfStorecode);
		XFS = xfStorecode;
		if (obj.checked) {
			$
					.get(
							"web/xfStore/addIsDeleXfStoreCenter",
							function(data) {
								if (data.length > 0) {
									$("#form-field-select-2").empty();
									// 添加
									$(
											"<option value=" + XFS + " selected>本店铺</option>")
											.appendTo("#form-field-select-2");
									for (var int = 0; int < data.length; int++) {
										var xfc = data[int].xfCenterstorecode.xfStorecode;
										if (xfStorecode != xfc) {
											$(
													"<option value=" + xfc + ">"
															+ data[int].xfCenterstorecode.xfName
															+ "</option>")
													.appendTo(
															"#form-field-select-2");
										}
									}

									$('#dialog1').dialog('open');
								} else {
									// 添加本店铺
									addXfc(obj, xfStorecode, xfStorecode);

								}
							});
		} else {
			$.post("web/xfStore/addIsDeleXfStoreCenter", {
				xfStorecode : xfStorecode
			}, function(data) {
				if (data == true) {

				} else {
					alert("取消中央店铺失败！");
					$(obj).prop("checked", true);
				}
			});
		}
	}

	function addXfc(obj, xfStorecode, xfCenterStorecode, xfName) {
		$
				.post(
						"web/xfStore/addIsDeleXfStoreCenter",
						{
							xfStorecode : xfStorecode,
							xfCenterStorecode : xfCenterStorecode
						},
						function(data) {
							if (data == true) {
								if (xfStorecode != xfCenterStorecode) {
									$(obj).attr("disabled", "disabled");
									$(obj).prop("checked", false);
									// alert(xfCenterStorecode+" "+xfName);
									$("#xfN" + xfStorecode).html(xfName);
									var de = "<button class='btn btn-grey btn-xs' onclick=deleteXFSC(this,'"
											+ xfStorecode
											+ "','"
											+ xfCenterStorecode
											+ "');>"
											+ "<i class='icon-trash icon-2x icon-only'></i></button>";
									$(de).appendTo("#xfN" + xfStorecode);
								}
								//$('#dialog1').dialog('close');
							} else {
								alert("设置中央店铺失败或者如果设置中央店铺则该店铺下必须无商品！");
								$(obj).prop("checked", false);
							}
						});
	}
</script>
</body>
</html>