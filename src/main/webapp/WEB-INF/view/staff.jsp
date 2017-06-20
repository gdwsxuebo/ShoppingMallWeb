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
</head>
<body>
<div id="all">
    <div id="staff">
    	<%@ include file="public/menu.jsp"%>
        <div class="right">
    	<%@ include file="public/header.jsp"%>
            <div class="middle">
                <div class="map">
                    <span class="icon position"><i></i></span>
                    <span class="mapName">员工</span>
                </div>
                <!--除以下content内容，其余地方均为模板，不要改动-->
                <div class="content">
                    <div class="title">
                    	<!-- 搜索 -->
                    	<form class="form-search" action="web/xfStaff/getStaff" method="post" id="searchForm">
	                        <div class="search">
	                            <input type="text" value="${requestScope.key==null?null:requestScope.key}" id="key" name="key" placeholder="店铺编号、员工号、名称">
	                            <span class="searchBtn icon" id="submitSearchForm"><i></i></span>
	                        </div>
	                    </form>
	                    <div class="btn add" onclick="addStaff();"  ${requestScope.misorv61=='MIS'?"":"style='display: none;'"}>添加</div>
	                    <div class="btn syncData" id="tongbu"  ${requestScope.misorv61=='MIS'?"style='display: none;'":""}>同步数据</div>
	                    <div class="dataDetails" ${requestScope.misorv61=='MIS'?"style='display: none;'":""}>同步61-MIS商铺数据</div>
                    </div>
                    <div class="tableContainer">
                        <table cellpadding="0" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>员工编号</th>
                                    <th>员工名称</th>
                                    <th>所属店铺</th>
                                    <th>角色</th>
                                    <th>菜单权限</th>
                                    <th>是否可用</th>
                                    <th  ${requestScope.misorv61=='MIS'?"":"style='display: none;'"}>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var="xfs" items="${paging.data}" varStatus="status">
									<tr>
										<td>${paging.startRowNum+(status.index)}</td>
										<td class="staffCode">${xfs.xfStaffcode}</td>
										<td>${xfs.xfName}</td>
										<td>${xfs.xfIssuestore.xfStorecode}:${xfs.xfIssuestore.xfName}</td>
										<td>${xfs.staffRole.roleName}</td>
										<td>${xfs.staffRole.gwRoleId.cnName}</td>
										<td>${xfs.enabled==true?"是":"否"}</td>
										<td  ${requestScope.misorv61=='MIS'?"":"style='display: none;'"}>
	                                        <span class="btn update" onclick="edit(this,'${xfs.xfStaffcode}');">修改</span>
 	                                        <span class="btn delete" onclick="del('${xfs.xfStaffcode}');">删除</span> 
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
    <!-- 添加 -->
    <form enctype="multipart/form-data" onsubmit="return chkForm1();" id="signupForm1" action="web/xfStaff/addStaff" method="post">
	    <input type="hidden" name="enabled" id="isAddEnabled">
	    <div class="shadow" id="add">
	        <div class="pop">
	            <h4 class="title">添加员工</h4>
	            <div class="container">
	                <div class="formList">
	                    <div>
	                        <span>员工编号</span>
	                        <input type="text" name="xfStaffcode" id="xfStaffcode" onblur="checkXfStaffcode(this);" >
	                    </div>
	                    <div>
	                        <span>员工姓名</span>
	                        <input type="text"  name="xfName" id="xfName" >
	                    </div>
	                    <div>
	                        <span>员工密码</span>
	                        <input type="text" name="xfPassword" id="xfPassword" >
	                    </div>
	                    <div>
	                        <span>是否可用</span>
	                        <span class="icon switch_on" id="isAddEnabledIcon"><i id="addSwitch"></i></span>
	                    </div>
	                    <div>
	                        <span>选择权限</span>
	                        <select name="selectRole" onchange="selectRoleChange(this);">
	                            <option value="1">店铺员工</option>
								<option value="4">店铺营业员</option>
								<option value="2">店铺管理员</option>
								<option value="3">商场管理员</option>
	                        </select>
	                    </div>
	                     <div>
	                        <span>菜单权限</span>
	                        <select id="roleGroup" name="roleGroup">
	                            
	                        </select>
	                    </div>
	                    <div id="addStore">
	                        <span>选择店铺</span>
	                        <select id="selectStore" name="selectStore">
	                            
	                        </select>
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
	<!-- 修改  -->
	<form enctype="multipart/form-data" onsubmit="return chkForm2();" id="signupForm2" action="web/xfStaff/updateStaff" method="post">
		<input type="hidden" name="enabled" id="isEditEnabled"> 
	    <div class="shadow" id="edit">
	        <div class="pop">
	            <h4 class="title">修改员工</h4>
	            <div class="container">
	                <div class="formList">
	                    <div>
	                        <span>员工编号</span>
	                        <input type="text" disabled="disabled" name="xfStaffcode" id="xfStaffcode2" onblur="checkXfStaffcode(this);" >
	                        <input type="hidden" name="xfStaffcode" class="xfStaffcode2" />
	                    </div>
	                    <div>
	                        <span>员工姓名</span>
	                        <input type="text"  name="xfName" id="xfName2" >
	                    </div>
	                    <div>
	                        <span>员工密码</span>
	                        <input type="text" name="xfPassword" id="xfPassword2" >
	                    </div>
	                    <div>
	                        <span>是否可用</span>
	                        <span class="icon switch_on" id="isEditEnabledIcon"><i id="editSwitch"></i></span>
	                    </div>
	                    <div>
	                        <span>选择权限</span>
	                        <select name="selectRole" id="roleSel" onchange="selectRoleChangeEdit(this);">
	                            <option value="1">店铺员工</option>
								<option value="4">店铺营业员</option>
								<option value="2">店铺管理员</option>
								<option value="3">商场管理员</option>
	                        </select>
	                    </div>
	                     <div>
	                        <span>菜单权限</span>
	                        <select id="roleGroup2" name="roleGroup">
	                            
	                        </select>
	                    </div>
	                    <div id="editStore">
	                        <span>选择店铺</span>
	                        <select id="selectStore2" name="selectStore">
	                            
	                        </select>
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
	
	<!-- 加载提示框 -->
    <div class="shadow" id="showstore">
    	 <div class="popstore" >
	          		<span >加载中，请稍后...</span>     
	        </div>
    </div>
	
</div>
<script type="text/javascript">
$(function(){
	//弹框
	 $(".content .title .btn.add").click(function () {
        $("#add").show();
        $("#add").click(function (e) {
            if(!$(e.target).hasClass("pop") && $(e.target).parents(".pop").length === 0){
                $(this).hide();
            }
        });
    });
    $("#addCancel").click(function () {
        window.location.href = "web/xfStaff/getStaff";
    });
    $("#addSwitch").click(function () {
        if($(this).parent().hasClass("switch_on")){
            $(this).parent().removeClass("switch_on").addClass("switch_off");
        }else{
            $(this).parent().removeClass("switch_off").addClass("switch_on");
        }
    });
});

function addStaff(){
    //获取到角色
    var selectRoles;
    $.get("web/xfStaff/getRoles", function(data) {
		if (data.length > 0) {
			for (var int = 0; int < data.length; int++) {
				selectRoles +=  "<option value='" + data[int].id + "'>" + data[int].cnName + "</option>";
				$("#roleGroup").html(selectRoles);
				/*if (int == 0) {
					alert("===0");
					$("<option selected value='" + data[int].id + "'>" + data[int].cnName + "</option>").appendTo("#roleGroup");
				} else { 
 					$("<option value='" + data[int].id + "'>" + data[int].cnName + "</option>").appendTo("#roleGroup");
 				}*/
			}
		}
	});
    //获取员工的角色
	var role = '${sessionScope.XfStaff.staffRole.authority}';
	if ("ROLE_STORE_ADMIN" == role) {
		$("<option value='" + '${sessionScope.XfStaff.xfIssuestore.xfStorecode}' + "' selected>本店</option>").appendTo("#selectStore");
	} else {
		//获取店铺
		$.get("web/xfStaff/getStores", function(data) {
			if (data.length > 0) {
				for (var int = 0; int < data.length; int++) {
					if (int == 0) {
						$("<option selected value='" + data[int].xfStorecode + "'>" + data[int].xfName + "</option>").appendTo("#selectStore");
					} else {
						$("<option value='"+ data[int].xfStorecode + "'>"+ data[int].xfName+ "</option>").appendTo("#selectStore");
					}
				}
			}
		});
	}
}

//如果是商场管理员就隐藏店铺
function selectRoleChange(obj) {
	if (3 == obj.value) {
		$("#addStore").hide();
	} else {
		$("#addStore").show();
	}
}
function selectRoleChangeEdit(obj){
	if (3 == obj.value) {
		$("#editStore").hide();
	} else {
		$("#editStore").show();
	}
}

//验证员工编号是否重复
function checkXfStaffcode(obj) {
	$.get("web/xfStaff/checkXfStaffcode", {xfStaffcode : obj.value}, function(data) {
		if (data) {
			$("#xfStaffcode").focus();
			$("#xfStaffcode").val("");
			$("#xfStaffcode").attr("placeholder", "员工编号重复");
		}
	});
}

//验证
function chkForm1() {
	var xfStaffcode = $("#xfStaffcode").val().trim();
	var xfName = $("#xfName").val().trim();
	var xfPassword = $("#xfPassword").val().trim();
	if (xfStaffcode == "") {
		$("#xfStaffcode").focus();
		$("#xfStaffcode").attr("placeholder", "请输入员工编号");
	} else if (xfStaffcode.length > 36) {
		$("#xfStaffcode").focus();
		$("#xfStaffcode").val("");
		$("#xfStaffcode").attr("placeholder", "员工编号长度不能超过36");
	} else if (xfName.length <1 ) {
		$("#xfName").focus();
		$("#xfName").val("");
		$("#xfName").attr("placeholder", "员工名称长度不能为空");
	}else if (xfName.length > 36) {
		$("#xfName").focus();
		$("#xfName").val("");
		$("#xfName").attr("placeholder", "员工名称长度不能超过36");
	} else if (xfPassword == "") {
		$("#xfPassword").focus();
		$("#xfPassword").attr("placeholder", "请输入员工密码");
	} else if (xfPassword.length > 32) {
		$("#xfPassword").focus();
		$("#xfPassword").val("");
		$("#xfPassword").attr("placeholder", "员工密码长度不能超过32");
	} else {
		return true;
	}
	return false;
}

//删除
function del(obj) {
	if (confirm("确定删除吗？")) {
		$.post("web/xfStaff/deleteXfStaffByCode", {xfStaffCode:obj}, function(data) {
			if (data) {
				window.location.reload();
			} else {
				alert("删除失败！");
			}
		});
	}
}

//编辑
function edit(obj, xfStaffCode) {
	var selectRoles;
	$("#edit").show();
	//取消按钮
	$("#editCancel").click(function () {
		window.location.href = "web/xfStaff/getStaff";
    });
	//是否可用
	 $("#editSwitch").click(function () {
        if($(this).parent().hasClass("switch_on")){
            $(this).parent().removeClass("switch_on").addClass("switch_off");
        }else{
            $(this).parent().removeClass("switch_off").addClass("switch_on");
        }
    })
	
	$.get("web/xfStaff/getXfStaff",{xfStaffCode : xfStaffCode}, function(xfStaff) {
		if (xfStaff != "") {
			console.log(xfStaff);
			$(".xfStaffcode2").val(xfStaff.xfStaffcode);
			$("#xfStaffcode2").val(xfStaff.xfStaffcode);
			$("#xfName2").val(xfStaff.xfName);
			$("#xfPassword2").val(xfStaff.xfPassword);
			$("#roleSel").val(xfStaff.staffRoleInt);
			if (xfStaff.staffRoleInt == 3) {
				$("#editStore").hide();
			}
			if(xfStaff.enabled == 1){
				$("#isEditEnabledIcon").attr("class","icon switch_on");
			}else{
				$("#isEditEnabledIcon").attr("class","icon switch_off");
			}
			$.get("web/xfStaff/getRoles",function(data) {
				if (data.length > 0) {
					for (var int = 0; int < data.length; int++) {
						 if (xfStaff.gwRoleId == data[int].id) {
							$("<option selected value='"+ data[int].id + "'>"+ data[int].cnName+ "</option>").appendTo("#roleGroup2");
						} else {
							$("<option value='"+ data[int].id + "'>"+ data[int].cnName+ "</option>").appendTo("#roleGroup2");
						} 
					}
				}
			});
			var role = '${sessionScope.XfStaff.staffRole.authority}';
			if ("ROLE_STORE_ADMIN" == role) {
				$("<option value='"+ '${sessionScope.XfStaff.xfIssuestore.xfStorecode}'+ "' selected>本店</option>").appendTo("#selectCenterStore");
			} else {
				$.get("web/xfStaff/getStores",function(data) {
					if (data.length > 0) {
						for (var int = 0; int < data.length; int++) {
							if (xfStaff.xfStorecode == data[int].xfStorecode) {
								$("<option selected value='"+ data[int].xfStorecode+ "'>"+ data[int].xfName+ "</option>").appendTo("#selectStore2");
							} else {
								$("<option value='"+ data[int].xfStorecode+ "'>"+ data[int].xfName+ "</option>").appendTo("#selectStore2");
							}
						}
					}
				});
			}
		} else {
			alert("获取员工信息失败！");
		}
	});
}

//编辑验证
function chkForm2() {
	var xfName = $("#xfName2").val().trim();
	var xfPassword = $("#xfPassword2").val().trim();
	if (xfName.length <1) {
		$("#xfName2").focus();
		$("#xfName2").val("");
		$("#xfName2").attr("placeholder", "员工名称长度不能为空");
	} else
	if (xfName.length > 36) {
		$("#xfName2").focus();
		$("#xfName2").val("");
		$("#xfName2").attr("placeholder", "员工名称长度不能超过36");
	} else if (xfPassword == "") {
		$("#xfPassword2").focus();
		$("#xfPassword2").attr("placeholder", "请输入员工密码");
	} else if (xfPassword.length > 32) {
		$("#xfPassword2").focus();
		$("#xfPassword2").val("");
		$("#xfPassword2").attr("placeholder", "员工密码长度不能超过32");
	} else if ($("#roleSel option:selected").val() != "3") {
		if ($("#selectStore2 option:selected").val() == null) {
			alert("请选择关联的商场");
			return false;
		}else {
			return true;
		}
	} else {
		return true;
	}
	return false;
}

//查询
$("#submitSearchForm").click(function() {
	$("#searchForm").submit();
});

//提交
$("#addSubmit").click(function(){
	var c =  $("#isAddEnabledIcon").attr("class");
	if(c == "icon switch_on"){
		$("#isAddEnabled").val("1");
	}else{
		$("#isAddEnabled").val("0");
	}
	$("#signupForm1").submit();
});
$("#editSubmit").click(function(){
	var c = $("#isEditEnabledIcon").attr("class");
	if(c == "icon switch_on"){
		$("#isEditEnabled").val("1");
	}else{
		$("#isEditEnabled").val("0");
	}
	$("#signupForm2").submit();
});

//分页
function page(cuPage){
	var key = $("#key").val();
	var goPage = $("#goPage").val();
	if(key == ""){
		window.location.href = "web/xfStaff/getStaff?pageIndex="+cuPage+"&pageSize=10";
	}else{
		window.location.href = "web/xfStaff/getStaff?key="+key+"&pageIndex="+cuPage+"&pageSize=10";
	}
	if(goPage != "" && goPage != null && key == ""){
		window.location.href = "web/xfStaff/getStaff?pageIndex="+goPage+"&pageSize=10";
	}
	if(goPage != "" && goPage != null && key != ""){
		window.location.href = "web/xfStaff/getStaff?key="+key+"&pageIndex="+goPage+"&pageSize=10";
	}
}


//同步员工数据
$("#tongbu").click(function(){
	if (confirm("确定更新员工吗?")) {
		var z = "<div class='popWarp' id='zccRES' style='width: 1899px; "
				+ "height: 100%; left: 0px; top: 0px; z-index: 999;'><div style='position: absolute;top: 50%;left: 50%;margin: -100px00-150px;width: 300px;height: 200px;z-index: 99;'><h1><i class='icon-spinner icon-spin orange bigger-125'></i></h1></div></div>";
		$(z).appendTo("body");
		/*$.get("web/xfStaff/refreshStaff", function(data) {
			$("#zccRES").remove();
			location.href = "web/xfStaff/getStaff";
		});*/
		
		$.ajax({
			type : "GET",//通常会用到两种：GET,POST。默认是：GET    
			url : "web/xfStaff/refreshStaff",//(默认: 当前页地址) 发送请求的地址    
			dataType : "application/json",//预期服务器返回的数据类型。    
			beforeSend : function() {
				$("#showstore").show();
			}, //发送请求    
			complete : function(data) {
				$("#zccRES").remove();
				location.href = "web/xfStaff/getStaff";
			}
		});
	}
});
</script>
</body>
</html>