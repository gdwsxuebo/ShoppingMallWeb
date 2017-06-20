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
<link rel="stylesheet" href="resource/LigerTree/ligerui-all.css" />
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
                    <span class="mapName">菜单权限</span>
                </div>
                <!--除以下content内容，其余地方均为模板，不要改动-->
                <div class="content">
                    <div class="title">
                       <!-- 搜索 -->
                    	<form class="form-search" action="web/gwRole/getRole" method="post" id="searchForm">
	                        <div class="search">
	                            <input type="text" value="${requestScope.key==null?null:requestScope.key}" id="key" name="key" placeholder="角色名称">
	                            <span class="searchBtn icon" id="submitSearchForm"><i></i></span>
	                        </div>
	                    </form>
                       	<div class="btn add">添加</div>
                    </div>
                    <div class="tableContainer">
                        <table cellpadding="0" cellspacing="0">
                            <thead>
                                <tr>
                                	<th>序号</th>
                                    <th>中文名称</th>
                                    <th>英文名称</th>
                                    <th>类型</th>
                                    <th>描述</th>
                                    <th>排序</th>
                                    <th>状态</th>
                                    <th>修改时间</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var="xfs" items="${paging.data}" varStatus="status">
									<input type="hidden" class="staffCode" disabled="disabled" value="${xfs.id}" >
									<tr>
										<td>${paging.startRowNum+(status.index)}</td>
										<td>${xfs.cnName}</td>
										<td>${xfs.enName}</td>
										<td>${xfs.labelName}</td>
										<td>${xfs.note}</td>
										<td>${xfs.orderNum}</td>
										<td>${xfs.stateName}</td>
										<td>${xfs.time}</td>
										<td>
	                                        <span class="btn update" onclick="edit(this,'${xfs.id}');">修改</span>
	                                        <span class="btn delete" onclick="del('${xfs.id}');">删除</span>
                                   			<span class="btn delete" onclick="set(this,'${xfs.id}');">设置权限</span>
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
    <form enctype="multipart/form-data"  id="signupForm1" action="web/gwRole/addOrUpdateRole" method="post">
	    <div class="shadow" id="add">
	        <div class="pop">
	            <h4 class="title">添加角色</h4>
	            <div class="container">
	                <div class="formList">
	                    <div>
	                        <span>中文名称</span>
	                        <input type="text" name="cnName" id="cnName"  >
	                    </div>
	                    <div>
	                        <span>英文名称</span>
	                        <input type="text"  name="enName" id="enName" >
	                    </div>
	                    <div>
	                        <span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 描述</span>
	                        <input type="text" name="note" id="note" >
	                    </div>
	                    <div>
	                        <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 类型</span>
							    <select name="label" >
	                            <option value="1">server端</option>
								<option value="2">pos端</option>
								<option value="3">server端和pos端</option>
	                        </select>
	                    </div>
	                    <div>
	                        <span>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; 状态</span>
	                        <select name="state">
	                        	<option value="1">启用</option>
	                        	<option value="0">禁用</option>
	                        </select>
	                    </div>
	                       <div>
	                        <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;排序</span>
	                        <input type="text" name="orderNum" id="orderNum" >
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
	<form enctype="multipart/form-data"  id="signupForm2" action="web/gwRole/addOrUpdateRole" method="post">
	    <div class="shadow" id="edit">
	        <div class="pop">
	            <h4 class="title">修改角色</h4>
	            <div class="container">
	                <div class="formList">
	                    <div>
	                        <span>中文名称</span>
	                        <input type="hidden" name="id" class="id2" />
	                        <input type="text"  name="cnName" id="cnName2"  >
	                   
	                    </div>
	                    <div>
	                        <span>英文名称</span>
	                        <input type="text"  name="enName" id="enName2" >
	                    </div>
	                    <div>
	                        <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 描述</span>
	                        <input type="text" name="note" id="note2" >
	                    </div>
	                    <div>
	                        <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 类型</span>
							    <select name="label"  id="roleSel">
	                            <option value="1">server端</option>
								<option value="2">pos端</option>
								<option value="3">server端和pos端</option>
	                        </select>
						</div>
	                     <div>
	                        <span>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; 状态</span>
	                        <select name="state" id="roleSel2">
	                        	<option value="1">启用</option>
	                        	<option value="0">禁用</option>
	                        </select>
	                    </div>
	                     <div>
	                        <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 排序</span>
	                        <input type="text" name="orderNum" id="orderNum2" >
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
	
	<!-- 设置权限  -->
	<div class="shadow" id="set">
       <div class="pop">
           <h4 class="title">设置权限</h4>
           <div class="container">
                  <div class="row">
					<div class="col-sm-6 col-sm-offset-3">
 						<ul id="menuTree" style="height:356px; margin:0px;overflow: auto;"></ul>
						<div align="center"> </div>
					</div>
				</div>	
           </div>
			<div class="handle">
               <span class="btn confirm" onclick="saveRoleGroups();">确定</span>
               <span class="btn cancel" id="authCancel">取消</span>
           </div>
       </div>
   </div>
</div>
<script type="text/javascript">
//设置权限取消
$("#authCancel").click(function (){
	$("#set").hide();
});

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
        $("#add").hide();
    });
});

//删除
function del(obj) {
	if (confirm("确定删除吗？")) {
		$.post("web/gwRole/deleteGwRoleById", {id:obj}, function(data) {
			if (data) {
				window.location.reload();
			} else {
				alert("删除失败！");
			}
		});
	}
}

//编辑
function edit(obj, id) {
	var selectRoles;
	$("#edit").show();
	//取消按钮
	$("#editCancel").click(function () {
        $("#edit").hide();
    });
	$.get("web/gwRole/getRoleById",{id : id}, function(model) {
			$(".id2").val(model.id);
			$("#cnName2").val(model.cnName);
			$("#enName2").val(model.enName);
			$("#orderNum2").val(model.orderNum);
			$("#note2").val(model.note);
			$("#roleSel").val(model.label);
			$("#roleSel2").val(model.state);
	});
}

//查询
$("#submitSearchForm").click(function() {
	$("#searchForm").submit();
});

//提交
$("#addSubmit").click(function(){
    var myReg = /^[\u4e00-\u9fa5]+$/;
    var re=/^[a-zA-Z]+$/;
	if($("#cnName").val() == ""||!myReg.test($("#cnName").val())){
		$("#cnName").focus();
		$("#cnName").val("");
		$("#cnName").attr("placeholder", "中文名称不能为空或有误");
	}else if($("#enName").val() == ""||!re.test($("#enName").val())){
		$("#enName").focus();
		$("#enName").val("");
		$("#enName").attr("placeholder", "英文名称不能为空或有误");
	}
	else if($("#orderNum").val() == ""||isNaN($("#orderNum").val())){
		$("#orderNum").focus();
		$("#orderNum").val("");
		$("#orderNum").attr("placeholder", "排序不能为空且为数字");
	}else{$("#signupForm1").submit();}
	
});
$("#editSubmit").click(function(){
	var myReg = /^[\u4e00-\u9fa5]+$/;
    var re=/^[a-zA-Z]+$/;
	if($("#cnName2").val() == ""||!myReg.test($("#cnName2").val())){
		$("#cnName2").focus();
		$("#cnName2").val("");
		$("#cnName2").attr("placeholder", "中文名称不能为空");
	}else if($("#enName2").val() == ""||!re.test($("#enName2").val())){
		$("#enName2").focus();
		$("#enName2").val("");
		$("#enName2").attr("placeholder", "英文名称不能为空");
	}else if($("#orderNum2").val() == ""||isNaN($("#orderNum2").val())){
		$("#orderNum2").focus();
		$("#orderNum2").val("");
		$("#orderNum2").attr("placeholder", "排序不能为空且为数字");
	}
	else{	$("#signupForm2").submit();}
	

});

var tree=null;
var hasGroups=new Array();
//角色id
var roleId = null;
function set(obj,Id){
	roleId =Id;
	$("#set").show();
	$(function(){
	    tree=$("#menuTree").ligerTree({  
	       idFieldName :'id',
	       parentIDFieldName :'pid',
	       slide: false,
	       enabledCompleteCheckbox:false, 
	       nodeWidth:240
	       }
	   );
	    getRoleGroupsByRoleId(roleId,tree);
	});
}

//角色获取权限
function getRoleGroupsByRoleId(roleId,tree){
	 $.post("web/gwRole/setRoleGroupByRoleId",{roleId:roleId},function(data){
		 if(data.status.code=="10000"){
	               if(data.status.code=="10000"){
	                   var menuData=data.data;
	                   //加载权限
	                   /**
	                   tree=$("#menuTree").ligerTree({  
	                       data:menuData, 
	                       idFieldName :'id',
	                       parentIDFieldName :'pid',
	                       nodeWidth:626
	                       }
	                   );
	                   */
	                   tree.setData(menuData);
	                   //获取已经具有的权限
	                   var checkedData=tree.getChecked();
	                   $.each(checkedData,function(i,n){
	                       hasGroups.push(n.data.id);
	                   });
	                   
	               }
	           }
	   });
}
//更新权限
function saveRoleGroups(){
   var newAddNodes=new Array();
   var delNodes=new Array();
   //获取选择的权限
   var hasSelectData=tree.getChecked();
   //选择的chekedId
   var chekedArr=new Array();
   $.each(hasSelectData,function(i,n){
       chekedArr.push(n.data.id);
   });
   //获得新增的ids
   $.each(chekedArr,function(i,id){
       if(!checkIdIsExistArr(id,hasGroups)){
           newAddNodes.push(id);
       }
   });
   //获得删除的ids
   $.each(hasGroups,function(i,id){
       if(!checkIdIsExistArr(id,chekedArr)){
           delNodes.push(id);
       }
   });
   var addIds=newAddNodes.join(",");
   var delIds=delNodes.join(",");
   var id = $.fsh.requestParam("id");
   $.post("web/gwRole/saveRoleGroups",{roleId:roleId,addIds:addIds,delIds:delIds},function(data){
	   window.location.href="web/gwRole/getRole";
   });
   
   /* $.ajax({ 
       url:'/web/gwRole/saveRoleGroups?roleId='+roleId+'&addIds='+addIds+'&delIds='+delIds,
       method:'POST',
       dataType:'json',
       success:function(data){
           
           if(data.status.code=="10000"){
               $.ligerDialog.alert(data.status.msg, "提示信息", "success",function(){
               
              	 window.location.href="web/gwRole/getRole";
              });
           }else{
               $.ligerDialog.alert(data.status.msg, "提示信息", "error",function(){
                    
                     //parent.closeDialog();
                  });
           }
       }       
   });  */
   
}

//检查id，是否存在于数组arr中
function checkIdIsExistArr(id,arr){
   for (var i = 0; i < arr.length; i++) {
       if(id==arr[i]){
           return true;
       }
   }
   return false;
}

//分页
function page(cuPage){
	var key = $("#key").val();
	var goPage = $("#goPage").val();
	if(key == ""){
		window.location.href = "web/gwRole/getRole?pageIndex="+cuPage+"&pageSize=10";
	}else{
		window.location.href = "web/gwRole/getRole?key="+key+"&pageIndex="+cuPage+"&pageSize=10";
	}
	if(goPage != "" && goPage != null && key == ""){
		window.location.href = "web/gwRole/getRole?pageIndex="+goPage+"&pageSize=10";
	}
	if(goPage != "" && goPage != null && key != ""){
		window.location.href = "web/gwRole/getRole?key="+key+"&pageIndex="+goPage+"&pageSize=10";
	}
}

</script>

<script src="resource/LigerTree/base.js" type="text/javascript"></script>
<script src="resource/LigerTree/ligerTree.js" type="text/javascript"></script>
<script src="resource/LigerTree/ligerDialog.js" type="text/javascript"></script>
<script type="text/javascript" src="resource/LigerTree/sys.common.js"></script>
</body>
</html>