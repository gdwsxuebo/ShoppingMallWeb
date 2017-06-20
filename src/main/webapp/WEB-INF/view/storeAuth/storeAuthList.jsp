<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> 
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
<link rel="stylesheet" href="new/css/storeauth.css">
<link rel="stylesheet" href="new/css/common.css">
<link rel="stylesheet" href="new/css/tableComm.css">
<link rel="stylesheet" href="new/css/staff.css">


<link rel="stylesheet" type="text/css" href="resource/My97DatePicker/skin/default/datepicker.css" />
<script src="resource/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
.pop{
    width: 440px;
    height: 490px;
    position: absolute;
    top:40%;
    left:50%;
    text-align: center;
    border-radius: 5px;
    margin: -200px 0 0 -210px;
    background-color: #ffffff;
} 
 .title .search{
    height: 40px;
    width: 277px;
    border: 1px solid rgb(204,204,204);
    border-radius: 5px;
    overflow: hidden;
}
 .title .search>*{
    height:100%;
    float: left;
}
 .title .search>input{
	padding-left:4px;
    width: 225px;
    border: none;
    border-right: 1px solid rgb(204,204,204);
}
 .title .search .searchBtn{
    cursor: pointer;
}
 .title .search .searchBtn i{
    width: 44px;
    height: 40px;
    top:0;
    margin: 0;
    background-position: 5px -75px;
}
</style>
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
                    <span class="mapName">收银机权限及配置</span>
                </div>
                <!--除以下content内容，其余地方均为模板，不要改动-->
                <div class="content">
                    <div class="title">
                    	<form class="form-search" action="web/storeAuth/getStore" method="post" id="searchForm">
	                        <div class="search">
	                            <input type="text" value="${requestScope.key==null?null:requestScope.key}" id="key" name="key" placeholder=" 收银机编号、店铺号">
	                            <span class="searchBtn icon" id="submitSearchForm"><i></i></span>
	                        </div>
                        </form>
                       	<div class="btn add" onclick="addStoreAuth();"  ${requestScope.misorv61=='MIS'?"style='display: none;'":""}>添加</div>
	                    <div class="btn syncData" id="tongbu"  ${requestScope.misorv61=='MIS'?"":"style='display: none;'"}>同步数据</div>
	                    <div class="dataDetails" ${requestScope.misorv61=='MIS'?"":"style='display: none;'"}>同步61-MIS商铺数据</div>
                    </div>
                    <div class="tableContainer">
                        <table cellpadding="0" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>收银机编号</th>
                                    <th  ${requestScope.misorv61=='MIS'?"style='display: none;'":""}>v61收银机编号</th>
                                    <th>所属店铺编号</th>
                                    <th>所属店铺名称</th>
                                    <th>下属店铺</th>
                                    <th>权限</th>
                                    <th>收银机型号</th>
                                    <th>时间</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${paging.data}" var="storeAuth" varStatus="status">
                            		<tr>
                            			<td>${storeAuth.tillid}</td>
                            			<td  ${requestScope.misorv61=='MIS'?"style='display: none;'":""}>${storeAuth.v61tillid}</td>
                            			<td>${storeAuth.storeCode}</td>
                            			<td>${storeAuth.storeName}</td>
                            			<td ><span class="btn update" onclick="showauth('${storeAuth.tillid}');">查看</span></td>
                            			<c:if test="${storeAuth.authId == 0}"><td>无</td></c:if>
                            			<c:if test="${storeAuth.authId == 1}"><td>离线支付</td></c:if>
                            			<c:if test="${storeAuth.authId == 2}"><td>离线支付与退货</td></c:if>
                            			<td>
                            				<c:if test="${storeAuth.screenStyle == 0}">AD-宝盈单屏</c:if>
                            				<c:if test="${storeAuth.screenStyle == 1}">AD-宝盈双屏</c:if>
                            				<c:if test="${storeAuth.screenStyle == 2}">AD-elo双屏</c:if>
                            				<c:if test="${storeAuth.screenStyle == 5}">AD-手持</c:if>
                            				<c:if test="${storeAuth.screenStyle == 3}">win-单屏</c:if>
                            				<c:if test="${storeAuth.screenStyle == 4}">win-双屏</c:if>
                            				<c:if test="${storeAuth.screenStyle == 9}">未选择</c:if>
                            			</td>
                            			<td>${storeAuth.ctime}</td>
                            			<td >
	                                        <span class="btn update" onclick="editauth('${storeAuth.tillid}');">修改</span>
	                                        <span class="btn delete" onclick="del('${storeAuth.id}');">删除</span>
                                   		</td>
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
    <!-- 添加 -->
    <form action="web/storeAuth/addOrUpdateStoreAuth" method="post" id="addForm">
    	<input type="hidden" name="storeName" id="storeName" value="">
	    <div class="shadow" id="add">
	        <div class="pop">
	            <h4 class="title">添加收银机配置</h4>
	            <div class="container">
	                <div class="formList">
	                    <div style="margin-left: 19%">
	                        <span>店铺</span>
	                        <select id="selectStore" name="storeCode" onchange="funselectStore()">
	                        </select>
	                    </div>
	                    <div style="margin-left: 7%">
	                        <span>收银机编号</span>
	                        <input type="text" name="tillid" id="isTillid" onblur="isTillided();" >
	                    </div>
	                    <div style="margin-left: -10%">
	                        <span>V61/MISS收银机编号</span>
	                        <input type="text" name="v61tillid" id="addv61tillid">
	                    </div>
	                    <div style="margin-left: 19%">
	                        <span>权限</span>
	                        <select name="authId" id="authId">
	                       	  	<option value="0">无</option>
								<option value="1">离线支付</option>
								<option value="2">离线支付与退货</option>
	                        </select>
	                    </div>
	                    <div style="margin-left: 8%">
	                        <span>收银机型号</span>
	                        <select name="screenStyle" id="addscreenStyle">
	                        	<option value="9">--请选择--</option>
	                        	<option value="0">AD-宝盈单屏</option>
	                        	<option value="1">AD-宝盈双屏</option>
	                        	<option value="2">AD-elo双屏</option>
	                        	<option value="5">AD-手持</option>
	                        	<option value="3">win-单屏</option>
	                        	<option value="4">win-双屏</option>
	                        </select>
	                        
	                    </div>
	                    <div ${requestScope.misorv61=='MIS'?"style='display: none;margin-left:3%;'":"style='margin-left:3%;'"} id="xfcenter">
	                    	<span>添加下属店铺</span>
                    		<input type="text" id="issueRanges"  class="issueRanges3" name="tillidandxfstore" readonly="ture">
                    		<input type="hidden" name="issueRanges" class="issueRanges1" />
                   			 
	                    </div>
	                </div>
	                <div class="handle">
	                    <span class="btn confirm" id="addSubmit">确定</span>
	                    <span class="btn cancel" id="addCancel">取消</span>
	                </div>
	            </div>
	        </div>
	    </div>
    </form>
    <!-- 修改 -->
    <form action="web/storeAuth/addOrUpdateStoreAuth" method="post" id="editForm">
    	<input type="hidden" name="storeCode" id="hiddenCode"> 
    	<input type="hidden" name="storeName" id="hiddenName">
	    <div class="shadow" id="edit">
	    <input type="hidden" name="id" id="hiddenId"> 
	        <div class="pop">
	            <h4 class="title">修改收银机配置</h4>
	            <div class="container">
	                <div class="formList">
	                    <div style="display: none;">
	                        <span style="margin-left: 12%">店铺编号</span>
	                        <input type="text" disabled="disabled" name="storeCode" id="selectStore2" >
	                    </div>
	                     <div>
	                        <span style="margin-left: 12%">店铺名称</span>
	                        <input type="text" disabled="disabled" name="storeName" id="storeName2" >
	                    </div>
	                    
	                    <div>
	                        <span style="margin-left: 7%">收银机编号</span>
	                        <input type="text" name="tillid" id="tillid" onblur="editTillided();">
	                    </div>
	                    <div ${requestScope.misorv61=='MIS'?"style='display: none;'":""}>
	                        <span>V61收银机编号</span>
	                        <input type="text" name="v61tillid" id="v61tillid2">
	                    </div>
	                    <div style="margin-left: 19%">
	                        <span>权限</span>
	                        <select name="authId" id="selectOp">
	                       	  
	                        </select>
	                    </div>
	                    <div>
	                        <span style="margin-left: 7%">收银机型号</span>
	                        <select name="screenStyle" id="screenStyle">
	                        	<option value="9">--请选择--</option>
	                        	<option value="0">AD-宝盈单屏</option>
	                        	<option value="1">AD-宝盈双屏</option>
	                        	<option value="2">AD-elo双屏</option>
	                        	<option value="5">AD-手持</option>
	                        	<option value="3">win-单屏</option>
	                        	<option value="4">win-双屏</option>
	                        </select>
	                    </div>
	                    <div   ${requestScope.misorv61=='MIS'?"style='display: none;margin-left:11%;'":"style='margin-left:11%;'id='updatexfCenter'"} >
	                    	<span>下属店铺</span>
                    		<input type="text" id="issueRanges2" class="issueRanges3" name="tillidandxfstore" readonly="ture" >
                    		<input type="hidden" name="issueRanges" class="issueRanges2" > 
	                    </div>
	                </div>
	                <div class="handle">
	                    <span class="btn confirm" id="editSubmit">确定</span>
	                    <span class="btn cancel" id="editCancel">取消</span>
	                </div>
	            </div>
	        </div>
	    </div>
    </form>
    
    <!--添加  发放范围弹窗   -->  
	<div class="shadow" id="addlookSendPop">
	    <div class="pop look lookSendPop" >
	        <h4 class="title">收银机关联店铺</h4>
	        <div class="container">
	         <div class="leftBar">
				
	         		<div class="title">
	         		<div class="search">
	         					<input type="hidden" class='zjffsearchIds'>
	                            <input type="text"  id="searchByXfStore" name="key" placeholder=" 收银机编号、店铺号">
	                            <span class="searchBtn icon" id="searchByXfStoreNname"><i></i></span>
	                             <div class="select"  id="zjfflyselectContainer" style="display: none;"><input type="text" ></div>	
	                 </div></div>
	         		
	         
	       <!--   <div class="select_container title" >
	        	<input type="hidden" class='zjffsearchIds'>
                <input id="searchByXfStore" placeholder="店铺编号、名称" style="clear: both;padding: 2px 39px 2px 1px;"><span style="float: center;padding: 2px 24px;line-height: 20px;border: 1px;" id="searchByXfStoreNname">搜索</span>  
 	       		<div class="select"  id="zjfflyselectContainer" style="display: none;"><input type="text" ></div>	        
		       	</div> -->
		       
	       <div class="storeList" >
	               <div id='selectedISSRAN'></div>
	       </div>
	        <div class="addToStore" onclick='queren(this,1);'>+添加到店铺</div>
          </div>
	         <div class="rightBar">
                    <h3>店铺</h3>
                     <div class="storeListt" >
                    <ul > <li id="zjffready"></li></ul>
                     </div>
                    <div class="delete" id="zjffqc">清除</div>
                    <div class="delete deleteAll" id="zjffqcall">清除所有</div>
                </div>
                 </div>
	        <div class="handle">
	            <span class="btn confirm"  onclick='querenadd(this,1)' id='addlookSendPopCancel' >确定</span>
	               <span class="btn cancel">取消</span>
	           </div>
		</div>
	</div>
	
	<!--修改发放范围弹窗    -->  
	<div class="shadow" id="editlookSendPop">
	    <div class="pop look lookSendPop" >
	        <h4 class="title">修改发放范围</h4>
	        <div class="container">
	       <div class="leftBar">
	       
	       	<div class="title">
	         		<div class="search">
	         					<input type="hidden" class='xgffsearchIds'>
	                            <input type="text"  id="searchByXfStoreedit" name="key" placeholder=" 收银机编号、店铺号">
	                            <span class="searchBtn icon" id="searchByXfStoreNnameedit"><i></i></span>
	                            <div class="select"  id="xgfflyselectContainer" style="display: none;"><input type="text" ></div>	
	          </div></div>
	       
<!--          <div class="select_container">
               <input type="hidden" class='xgffsearchIds'>
		        <input id="searchByXfStoreedit" style="clear: both;padding: 2px 39px 2px 1px;" ><button style="float: center;padding: 2px 24px;line-height: 20px;" id="searchByXfStoreNnameedit">搜索</button>
		         <div class="select"  id="xgfflyselectContainer" style="display: none;"><input type="text" ></div>	  		         
		    </div> -->	
		       	
		      
	       <div class="storeList" >
	               <div id='selectedISSRAN2'></div>
	        </div>
	        <div class="addToStore" onclick='queren2(this,1);'>+添加到店铺</div>
          </div>
	         <div class="rightBar">
                    <h3>店铺</h3>
                     <div class="storeListt" >
                    <ul > <li id="xgffready"></li></ul>
                     </div>
                    <div class="delete" id="xgffqc">清除</div>
                    <div class="delete deleteAll" id="xgffqcall">清除所有</div>
                </div>
                </div>
	        <div class="handle">
	            <span class="btn confirm"  onclick='querenadd2(this,1);' id='addlookSendPopCancel2' >确定</span>
	            <span class="btn cancel">取消</span>
	           </div>
		
	</div>
	</div>
	
	<!-- 查看下属收银机-->
    <form action="web/storeAuth/addOrUpdateStoreAuth" method="post" id="showauths">
    	<input type="hidden" name="storeCode" id="hiddenCode"> 
    	<input type="hidden" name="storeName" id="hiddenName">
	    <div class="shadow" id="showauth">
	    <input type="hidden" name="showauth" id="hiddenId"> 
	        <div class="pop" style="height: auto;">
	            <h4 class="title">下属店铺</h4>
	            <div class="container">
	                
	                <div class="handle" >
	                    <div id="showauthsstores">
	                    	
	                    </div>
	                   <span class="btn cancel" style="margin-top: 20px;" id="showauthCancel">取消</span> 
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
$(function (){
	//弹框
	$(".content .title .btn.add").click(function () {
       $("#add").show();
       $("#add").click(function (e) {
           if(!$(e.target).hasClass("pop") && $(e.target).parents(".pop").length === 0){
               $(this).hide();
           }
       });
   });
	//取消
   $("#addCancel").click(function () {
       $("#add").hide();
   });
	
	
	
});

	
	


//添加
function addStoreAuth(){
	//获取店铺
	$.get("web/storeAuth/getStoreList",function(data){
		if(data.length > 0){
			for (var int = 0; int < data.length; int++) {
				if (int == 0) {
					
					if(data[int].xfCenter==false){
						$("<option selected value='" + data[int].xfStorecode + "'>" + data[int].xfName + "</option>").appendTo("#selectStore");
						$("#xfcenter").css('display','none');
					}else{
						$("<option selected value='" + data[int].xfStorecode + "'>" + data[int].xfName + "</option>").appendTo("#selectStore");
						$("#xfcenter").css('display','block');
					}
					
					
				} else {
					$("<option value='"+ data[int].xfStorecode + "'>"+ data[int].xfName+ "</option>").appendTo("#selectStore");
				}
			}
		}
	});
}


//查看下属店铺
function showauth(tillid){
	
	//获取店铺
	$.get("web/storeAuth/getStoreListBytillid",{tillid:tillid},function(data){
		if(data.length > 0){
			for(var i=0;i<data.length;i++){
				$("#showauthsstores").append('<p style="margin-left: 100px;text-align:left">'+data[i].xfStorecode+':'+data[i].xfName+'</p>');
			}
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

//编辑
function editauth(tillid){
	$("#edit").show();
	//取消
	$("#edit").click(function (e) {
        if(!$(e.target).hasClass("pop") && $(e.target).parents(".pop").length === 0){
            $(this).hide();
        }
    });
   $("#editCancel").click(function () {
       $("#edit").hide();
   });
	$.get("web/storeAuth/getStoreAuthByTillid",{tillid:tillid},function(storeAuth){
		//console.log(storeAuth);
		
		//获取店铺
		$.get("web/xfStore/getByXfStoreByCode",{code:storeAuth.storeCode},function(data){
			console.log(data);
			if(data.xfCenter==false){
				$("#updatexfCenter").css('display','none');
			}else{
				$("#updatexfCenter").css('display','block');
			}
			
		});
		
		if(storeAuth != ""){
			$("#tillid").val(storeAuth.tillid);
			$("#v61tillid2").val(storeAuth.v61tillid);
			$("#screenStyle").val(storeAuth.screenStyle);
			$("#hiddenId").val(storeAuth.id);
			$("#selectStore2").val(storeAuth.storeCode);
			$("#storeName2").val(storeAuth.storeName);
			$("#issueRanges2").val(storeAuth.issueRanges);
			console.log(storeAuth.issueRanges);
			
			if(storeAuth.issueRanges!=null){
				arr=storeAuth.issueRanges.replace(/[ ]/g,":");
				arr=arr.split(":");
				for(var i=0;i<arr.length;i++){
					if(arr[i] == "" || typeof(arr[i]) == "undefined"){
						arr.splice(i,1);
						i=i-1;
					}
				}
				var message="";
				for(var m=0;m<arr.length;m++){
					message = message+arr[m]+",";
					m=m+1;
				}			
				$(".issueRanges2").val(message);
			}
			
			/* //编辑的时候店铺选中
			$.get("web/storeAuth/getStoreList",function(data){
				if(data.length > 0){
					for (var int = 0; int < data.length; int++) {
						if (storeAuth.storeCode == data[int].xfStorecode) {
							$("<option selected value='" + data[int].xfStorecode + "'>" + data[int].xfName + "</option>").appendTo("#selectStore2");
						} else {
							$("<option value='"+ data[int].xfStorecode + "'>"+ data[int].xfName+ "</option>").appendTo("#selectStore2");
						}
					}
				}
			}); */
			//权限选中
			$.getJSON("resource/js/storeAuth.json",function(data){ 
				var $jsontip = $("#selectOp");
				$jsontip.empty();
				 for(x in data){
					 var au = storeAuth.authId;
					 if(data[x].additionalParameters.id == au){
						$jsontip.append("<option selected='selected' value="+data[x].additionalParameters.id+">"+x+"</option>");
					 }else{
						$jsontip.append("<option value="+data[x].additionalParameters.id+">"+x+"</option>");
					 }
				} 
			});
		}
	});
}



//删除
function del(id){
	if (confirm("确定删除吗？")) {
		$.post("web/storeAuth/deleteStoreAuth",{id:id},function(data){
			if(data){
				window.location.reload();
			}else{
				alert("删除失败");
			}
		});
	}
}
//判断收银机编号是否已经存在
function isTillided(){
	var tillid = $("#isTillid").val();
	$.post('web/storeAuth/isTillided',{tillid:tillid},function(data){
		if(data){
			$("#isTillid").focus();
			   $("#isTillid").val("");
			   $("#isTillid").attr("placeholder", "收银机编号重复，请重新输入");
			$("#isTillid").val("");
		}
	});
}
function editTillided(){
	var tillid = $("#tillid").val();
	$.post('web/storeAuth/isTillided',{tillid:tillid},function(data){
		if(data){
			 $("#tillid").focus();
			   $("#tillid").val("");
			   $("#tillid").attr("placeholder", "收银机编号重复，请重新输入");
			$("#tillid").val("");
		}
	});
}

//查询
$("#submitSearchForm").click(function() {
	$("#searchForm").submit();
});

//添加
$("#addSubmit").click(function(){
	var s = $("#selectStore").find("option:selected").text();
	console.log(s);
	$("#storeName").val(s);
	if($("#isTillid").val() == ""){
	   $("#isTillid").focus();
	   $("#isTillid").val("");
	   $("#isTillid").attr("placeholder", "请输入收银机编号");
	}else if($("#addv61tillid").val() == ""){
		$("#addv61tillid").focus();
		   $("#addv61tillid").val("");
		   $("#addv61tillid").attr("placeholder", "请输入V61收银机编号");
	}else if($("#addscreenStyle").val() == 9){
		alert("请选择单屏双屏");
	}else{
		$("#addForm").submit();
	}
});

//修改
$("#editSubmit").click(function(){
	var c = $("#selectStore2").val();
	$("#hiddenCode").val(c);
	var s = $("#storeName2").val();
	$("#hiddenName").val(s);
	if($("#tillid").val() == ""){
		 $("#tillid").focus();
		 $("#tillid").val("");
		 $("#tillid").attr("placeholder", "请输入收银机编号");
	}else if($("#v61tillid2").val() == ""&&'${requestScope.misorv61}'!="MIS"){
		 $("#v61tillid2").focus();
		 $("#v61tillid2").val("");
		 $("#v61tillid2").attr("placeholder", "请输入V61收银机编号");
	}else if($("#screenStyle").val() == 9){
		alert("请选择单屏双屏");
	}else{
		$("#editForm").submit();
	}
})
//分页
function page(cuPage){
	var key = $("#key").val();
	var goPage = $("#goPage").val();
	if(key == ""){
		window.location.href = "web/storeAuth/getStore?pageIndex="+cuPage+"&pageSize=10";
	}else{
		window.location.href = "web/storeAuth/getStore?key="+key+"&pageIndex="+cuPage+"&pageSize=10";
	}
	if(goPage != "" && goPage != null && key == ""){
		window.location.href = "web/storeAuth/getStore?pageIndex="+goPage+"&pageSize=10";
	}
	if(goPage != "" && goPage != null && key != ""){
		window.location.href = "web/storeAuth/getStore?key="+key+"&pageIndex="+goPage+"&pageSize=10";
	}
}

$("#searchByXfStoreNname").click(function () {
	var xfstorename= $("#searchByXfStore").val();
	searchXfStoreByName(xfstorename);
})

$("#searchByXfStoreNnameedit").click(function () {
	var xfstorename= $("#searchByXfStoreedit").val();
	searchXfStoreByNameedit(xfstorename);
})

/**根据店铺名称搜索**/
function searchXfStoreByName(xfstorename){
		var is = $("#selectedISSRAN");
		is.empty();
		$.post("web/xfStore/getByXfStoreNameORCode",{codeOrName:xfstorename},function(data){
			if (data != null && data.length > 0) {
				$(data).each(function(index, element) {
					var strNAMEXFS = (element.xfStorecode + ":" + element.xfName);
					var bl = true;
					if (bl) {
						var str = "<label><input name='issueRangess' class='ace ace-checkbox-2' type='checkbox' value="+element.xfStorecode+"><span class='lbl'>"+strNAMEXFS+"</span></label></br>";
						bl = false;
						var haveIn=	$(".zjffsearchIds").val();
						var haveInIds=haveIn.split(",");
						for(var m in haveInIds){
							if(haveInIds[m]===("++"+element.xfStorecode)){
								bl=true;
							}
						}
						$("input[name='issueRangess']:checked").each(function(indIR,elementIR) {
							if ($(elementIR).next().html() == strNAMEXFS) {
								bl = true;
								return;
							}
						});
						if (bl) {
							is.prepend($(str));
						} else {
							$(str).appendTo(is);
						}
					}
					
				})
			}
			if (data != null && data.length > 0) {
				var str = "<label><input name='issueRangessQuanXuan'  class='ace ace-checkbox-2' type='checkbox' ><span class='lbl'>全选</span></label></br>";
				is.prepend($(str));
			}
			$("input[name='issueRangessQuanXuan']").click(function(){
			    if(this.checked){   
			        $("input[name='issueRangess']:checkbox").prop("checked", true);  
			    }else{   
			    	 $("input[name='issueRangess']:checkbox").prop("checked", false); 
			    }   
			});
			//店铺选中
			var haveIn=	$(".zjffsearchIds").val();
			var haveInIds=haveIn.split(",");
			$("input[name='issueRangess']:checkbox").each(function() {
				for (var ids in haveInIds){
					if(haveInIds[ids]==("++"+$(this).val())){
						$(this).attr('checked','true');
					}
				};  
			});
			
		});
	}
	
	
/**根据店铺名称搜索编辑**/
function searchXfStoreByNameedit(xfstorename){
		var is = $("#selectedISSRAN2");
		is.empty();
		$.post("web/xfStore/getByXfStoreNameORCode",{codeOrName:xfstorename},function(data){
			if (data != null && data.length > 0) {
				$(data).each(function(index, element) {
					var strNAMEXFS = (element.xfStorecode + ":" + element.xfName);
					var bl = true;
					if (bl) {
						var str = "<label><input name='issueRangesss'  class='ace ace-checkbox-2' type='checkbox' value=" + element.xfStorecode + "><span class='lbl'>" + strNAMEXFS + "</span></label></br>";
						bl = false;
						var haveIn=	$(".xgffsearchIds").val();
						var haveInIds=haveIn.split(",");
						for(var m in haveInIds){
							if(haveInIds[m]===("++"+element.xfStorecode)){
								bl=true;
							}
						}
						if (bl) {
							is.prepend($(str));
						} else {
							$(str).appendTo(is);
						}
					}
					
				})
			}
			if (data != null && data.length > 0) {
				var str = "<label><input name='issueRangesssQuanXuan'  class='ace ace-checkbox-2' type='checkbox' ><span class='lbl'>全选</span></label></br>";
				is.prepend($(str));
			}
			$("input[name='issueRangesssQuanXuan']").click(function(){
			    if(this.checked){   
			        $("input[name='issueRangesss']:checkbox").prop("checked", true);  
			    }else{   
			    	 $("input[name='issueRangesss']:checkbox").prop("checked", false); 
			    }   
			});
			//店铺选中
			var haveIn=	$(".xgffsearchIds").val();
			var haveInIds=haveIn.split(",");
			$("input[name='issueRangesss']:checkbox").each(function() {
				for (var ids in haveInIds){
					if(haveInIds[ids]==("++"+$(this).val())){
						$(this).attr('checked','true');
					}
				};  
			});
			
		});
	}
	
//同步员工数据
$("#tongbu").click(function(){
	if (confirm("确定更新收银机吗?")) {
		var z = "<div class='popWarp' id='zccRES' style='width: 1899px; "
				+ "height: 100%; left: 0px; top: 0px; z-index: 999;'><div style='position: absolute;top: 50%;left: 50%;margin: -100px00-150px;width: 300px;height: 200px;z-index: 99;'><h1><i class='icon-spinner icon-spin orange bigger-125'></i></h1></div></div>";
		$(z).appendTo("body");
		/*$.get("web/storeAuth/refreshTillid", function(data) {
			$("#zccRES").remove();
			location.href = "web/storeAuth/getStore";
		});*/
		
		$.ajax({
			type : "GET",//通常会用到两种：GET,POST。默认是：GET    
			url : "web/storeAuth/refreshTillid",//(默认: 当前页地址) 发送请求的地址    
			dataType : "application/json",//预期服务器返回的数据类型。    
			beforeSend : function() {
				$("#showstore").show();
			}, //发送请求    
			complete : function(data) {
				$("#zccRES").remove();
				location.href = "web/storeAuth/getStore";
			}
		});
	}
});


function funselectStore(){
	//获取店铺
	$.get("web/xfStore/getByXfStoreByCode",{code:$("#selectStore").val()},function(data){
		if(data.xfCenter==false){
			$("#xfcenter").css('display','none');
		}else{
			$("#xfcenter").css('display','block');
		}
		
	});
}


</script>
<script src="new/js/addPromotion.js"></script>
<script src="new/js/editPromotion.js"></script>
</body>
</html>