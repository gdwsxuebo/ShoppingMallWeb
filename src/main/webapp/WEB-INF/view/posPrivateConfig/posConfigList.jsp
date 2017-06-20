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
<link rel="stylesheet" href="new/css/staff.css">
<script src="resource/assets/js/privateConfig.js"></script>
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
                    <span class="mapName">银联私有配置</span>
                </div>
                <!--除以下content内容，其余地方均为模板，不要改动-->
                <div class="content">
                    <div class="title">
                   		<form class="form-search" action="web/posPrivateConfig/getPosPrivateConfig" method="post" id="searchForm">
	                        <div class="search">
	                            <input type="text" value="${requestScope.key==null?null:requestScope.key}" id="key" name="key" placeholder=" 收银机编号">
	                            <span class="searchBtn icon" id="submitSearchForm"><i></i></span>
	                        </div>
                        </form>
                       	<div class="btn add"  onclick="addStaff();" >添加</div>
                    </div>
                    <div class="tableContainer">
                        <table cellpadding="0" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>所属店铺</th>
                                    <th>收银机编号</th>
                                    <th>V61收银机编号</th>
                                    <th>商户号</th>
                                    <th>终端号</th>
                                    <th>秘钥</th>
                                    <th>pos机号</th>
<!--                                     <th>离线是否可用</th> -->
                                    <th>上一次修改时间</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${paging.data}" var="privateConfig" varStatus="status">
	                            	<tr>
	                            		<td>${paging.startRowNum+(status.index)}</td>
	                            		<td>${privateConfig.storeid}</td>
	                            		<td>${privateConfig.tillid}</td>
	                            		<td>${privateConfig.v61tillid}</td>
	                            		<td>${privateConfig.mchtId}</td>
	                            		<td>${privateConfig.termId}</td>
	                            		<td>${privateConfig.authSN}</td>
	                            		<td>${privateConfig.posid}</td>
	                            		<%-- <c:if test="${privateConfig.outlinepay=='1'}"><td>离线可用</td></c:if>
	                            		<c:if test="${privateConfig.outlinepay=='0'}"><td>未选择</td></c:if>
	                            		<c:if test="${privateConfig.outlinepay=='2'}"><td>离线不可用</td></c:if> --%>
	                            		
	                            		<td>${privateConfig.utime}</td>
	                            		<td>
	                                        <span class="btn update" onclick="edit(this,'${privateConfig.id}');">修改</span>
	                                        <span class="btn delete" onclick="del('${privateConfig.id}');">删除</span>
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
    <form enctype="multipart/form-data"  id="signupForm1" action="web/posPrivateConfig/addOrUpdatePosPrivateConfig?pageIndex=${paging.pageIndex==null?1:paging.pageIndex }&pageSize=${paging.pageSize==null?15:paging.pageSize}&key=${requestScope.key}" method="post">
	    <div class="shadow" id="add">
	        <div class="pop" style="height: 450px;">
	            <h4 class="title">添加收银机配置</h4>
	            <div class="container">
	                <div class="formList">
	                    <div>
	                        <span>收银机编号</span>
	                        <select name="tillid" id="tillid">
                             </select>
	                    </div>
	                    <div style="margin-left: 5%">
	                        <span>Pos机号</span>
	                          <input type="text" name="posid" id="posid"  />
	                    </div>
	                    <div style="margin-left: 8%">
	                        <span>商户号</span>
	                        <input type="text" name="mchtId" id="mchtId"  />
	                    </div>
	                    <div style="margin-left: 8%">
	                        <span>终端号</span>
							<input type="text" name="termId" id="termId"  />
	                    </div>
	                    <div style="margin-left: 14%">
	                       <span style="float:left;">密钥</span>
	                        <textarea name="authSN" id="authSN" style="resize:none;" ></textarea>
	                    </div>
	                    <!-- <div>
	                        <span>Pos机离线</span>
	                       <select id="outlinepay" name="outlinepay"  >
	                       		
						         <option value="0">--请选择--</option>
						         <option value="1">离线可用</option>
						         <option value="2">离线不可用</option>
						    </select>
	                    </div> -->
	                  
	                </div>
	                <div class="handle">
	                    <span class="btn confirm" id="addSubmit">确定</span>
	                    <span class="btn cancel" id="addCancel">取消</span>
	                </div>
	            </div>
	        </div>
	    </div>
	</form>
    <!-- 修改  -->
	<form enctype="multipart/form-data"  id="signupForm2" action="web/posPrivateConfig/addOrUpdatePosPrivateConfig?pageIndex=${paging.pageIndex==null?1:paging.pageIndex }&pageSize=${paging.pageSize==null?15:paging.pageSize}&key=${requestScope.key}" method="post">
	    <div class="shadow" id="edit">
	        <div class="pop" style="height: 450px;">
	            <h4 class="title">修改收银机配置</h4>
	            <div class="container">
	                <div class="formList">
	                    <div>
	                        <span>收银机编号</span>
	                         <input type="hidden" name="id" class="id2" />
	                       
                             <input type="text" name="tillid" class="tillid2"  disabled="disabled"/>
	                    	<input type="hidden" name="tillid" class="tillid2"  />
	                    
	                    </div>
	                    <div style="margin-left: 5%">
	                        <span>Pos机号</span>
	                        <input type="text" name="posid" id="posid2"  />
	                           
	                    </div>
	                    <div style="margin-left: 8%">
	                        <span>商户号</span>
	                        <input type="text" name="mchtId" id="mchtId2"  />
	                    </div>
	                    <div style="margin-left: 8%">
	                        <span>终端号</span>
							<input type="text" name="termId" id="termId2"  />
	                    </div>
	                    <div style="margin-left: 14%">
	                        <span style="float:left;" >密钥</span>
	                          <textarea name="authSN" id="authSN2" style="resize:none;" ></textarea>
	                    </div>
	                    </div>
	                    <!-- <div>
	                        <span>Pos机离线</span>
	                       <select id="outlinepay2" name="outlinepay"  >
	                       		
						         <option value="0">--请选择--</option>
						         <option value="1">离线可用</option>
						         <option value="2">离线不可用</option>
						    </select>
	                    </div> -->
	                    <div class="handle">
	                    <span class="btn confirm" id="editSubmit">确定</span>
	                    <span class="btn cancel" id="editCancel">取消</span>
	                </div>
	                </div>
	              
	            </div>
	        </div>
	    </div>
	</form>
    
</div>
<script type="text/javascript">
$(function(){
	//弹框
	 $(".content .title .btn.add").click(function () {
        
        $("#add").click(function (e) {
            if(!$(e.target).hasClass("pop") && $(e.target).parents(".pop").length === 0){
                $(this).hide();
            }
        });
    });
    $("#addCancel").click(function () {
        $("#add").hide();
    });
});


function addStaff(){
 
    //获取到角色
    var selectRoles;
    var selectRoles2;
    $.get("web/posPrivateConfig/getTillids", function(data) {
    	
		if (data.length > 0) {
			for (var int = 0; int < data.length; int++) {
				selectRoles +=  "<option value='" + data[int] + "'>" + data[int] + "</option>";
				$("#tillid").html(selectRoles);
			}
			$("#add").show();	
		}else{
			alert("无可配置的收银机")
		}
	});
	}


//编辑
function edit(obj, id) {
	var selectRoles;
	$("#edit").show();
	//取消按钮
	$("#editCancel").click(function () {
      $("#edit").hide();
  });
	
	  //获取到角色
    var selectRoles;
    var selectRoles2;
 
	$.get("web/posPrivateConfig/getPosPrivateConfigById",{id : id}, function(model) {
		$(".id2").val(model.id);
		$(".tillid2").val(model.tillid);
		$("#posid2").val(model.posid);
		$("#mchtId2").val(model.mchtId);
		$("#termId2").val(model.termId);
		$("#authSN2").val(model.authSN);
		$("#outlinepay2").val(model.outlinepay);
	});
}

//查询
$("#submitSearchForm").click(function() {
	$("#searchForm").submit();
});

//删除
function del(obj) {
	if (confirm("确定删除吗？")) {
		$.post("web/posPrivateConfig/deletePosPrivateConfigByid", {id:obj}, function(data) {
			if (data) {
				window.location.reload();
			} else {
				alert("删除失败！");
			}
		});
	}
}
//提交
$("#addSubmit").click(function(){
	 if($("#tillid").val()==""){
		$("#tillid").focus();
		$("#tillid").val("");
		alert("收银机编号不能为空") ;
	}
	else if($("#posid").val()==""){
		$("#posid").focus();
		$("#posid").val("");
		$("#posid").attr("placeholder", "pos机号不能为空");
	}else if($("#mchtId").val()==""){
		$("#mchtId").focus();
		$("#mchtId").val("");
		$("#mchtId").attr("placeholder", "商户号不能为空");
	}
	else if($("#termId").val()==""){
		$("#termId").focus();
		$("#termId").val("");
		$("#termId").attr("placeholder", "终端机号不能为空");
	}
	else if($("#authSN").val()==""){
		$("#authSN").focus();
		$("#authSN").val("");
		$("#authSN").attr("placeholder", "密钥不能为空");
	}else {$("#signupForm1").submit();}
	
});


$("#editSubmit").click(function(){
		 if($("#posid2").val()==""){
			$("#posid2").focus();
			$("#posid2").val("");
			$("#posid2").attr("placeholder", "pos机号不能为空");
		}else if($("#mchtId2").val()==""){
			$("#mchtId2").focus();
			$("#mchtId2").val("");
			$("#mchtId2").attr("placeholder", "商户号不能为空");
		}
		else if($("#termId2").val()==""){
			$("#termId2").focus();
			$("#termId2").val("");
			$("#termId2").attr("placeholder", "终端机号不能为空");
		}
		else if($("#authSN2").val()==""){
			$("#authSN2").focus();
			$("#authSN2").val("");
			$("#authSN2").attr("placeholder", "密钥不能为空");
		}else {$("#signupForm2").submit();}
		
	
});


//分页
function page(cuPage){
	var key = $("#key").val();
	var goPage = $("#goPage").val();
	if(key == ""){
		window.location.href = "web/posPrivateConfig/getPosPrivateConfig?pageIndex="+cuPage+"&pageSize=10";
	}else{
		window.location.href = "web/posPrivateConfig/getPosPrivateConfig?key="+key+"&pageIndex="+cuPage+"&pageSize=10";
	}
	if(goPage != "" && goPage != null && key == ""){
		window.location.href = "web/posPrivateConfig/getPosPrivateConfig?pageIndex="+goPage+"&pageSize=10";
	}
	if(goPage != "" && goPage != null && key != ""){
		window.location.href = "web/posPrivateConfig/getPosPrivateConfig?key="+key+"&pageIndex="+goPage+"&pageSize=10";
	}
}

</script>
</body>
</html>