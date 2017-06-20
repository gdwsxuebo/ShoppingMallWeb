<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path1 = request.getContextPath();
	String basePath1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path1 + "/";
%>
<style type="text/css">
.editPwd{
    width: 410px;
    height: 370px;
    position: absolute;
    top:50%;
    left:50%;
    text-align: center;
    border-radius: 5px;
    margin: -200px 0 0 -210px;
    background-color: #ffffff;
    z-index: -1;
}
.editPwd h4.title{
    background-color: #000000;
    font-size: 16px;
    color: #ffffff;
    border-radius: 5px 5px 0 0;
    line-height: 50px;
    font-weight: normal;
}
.editPwd .container{
    padding: 70px 40px;
}
.editPwd .handle .btn{
    width: 72px;
    display: inline-block;
    border-radius: 5px;
    line-height: 40px;
    cursor: pointer;
}
.editPwd .handle .btn.confirm{
    color: #ffffff;
    margin-right:20px;
    background-color: rgb(101,206,167);
}
.editPwd .handle .btn.cancel{
    color: rgb(128,128,128);
    background-color: rgb(227,228,232);
}
.editPwd .handle .btn.confirm:hover{
    background-color: rgb(91,185,150);
}
.editPwd .handle .btn.cancel:hover{
    background-color: rgb(202,203,207);
}
.shadow .editPwd .formList>div{
    line-height: 65px;
}
.shadow .editPwd .formList>div>input{
	padding-left:4px;
    height: 36px;
    width: 240px;
}
</style>

<script type="text/javascript">
	$(function (){
		//弹框
		$(".updatePassword").click(function () {
	       $("#editPwd").show();
	       $("#editPwd").click(function (e) {
	           if(!$(e.target).hasClass("editPwd") && $(e.target).parents(".editPwd").length === 0){
	               $(this).hide();
	           }
	       });
	   });
		//取消
	   $("#editPwdCancel").click(function () {
	       $("#editPwd").hide();
	   });
		
		
	   $("#editPwdSubmit").click(function(){
		   var oldPwd = $("#oldPwd").val().trim();
		   var newPwd = $("#newPwd").val().trim();
		   var againNewPwd = $("#againNewPwd").val().trim();
		   if(oldPwd.length > 32){
			   $("#oldPwd").focus();
			   $("#oldPwd").val("");
			   $("#oldPwd").attr("placeholder", "密码长度不能超过32");
		   }else if(newPwd.length > 32){
			   $("#newPwd").focus();
			   $("#newPwd").val("");
			   $("#newPwd").attr("placeholder", "密码长度不能超过32");
		   }else if(againNewPwd.length > 32){
			   $("#againNewPwd").focus();
			   $("#againNewPwd").val("");
			   $("#againNewPwd").attr("placeholder", "密码长度不能超过32");
		   }else if(newPwd != againNewPwd){
			   alert("输入的密码不一致");
		   }else if($("#newPwd").val() == ""){
			   $("#newPwd").focus();
			   $("#newPwd").val("");
			   $("#newPwd").attr("placeholder", "请输入密码");
		   }else if($("#againNewPwd").val() == ""){
			   $("#againNewPwd").focus();
			   $("#againNewPwd").val("");
			   $("#againNewPwd").attr("placeholder", "请输入密码");
		   }else{
			   $("#editPwdForm").submit();
		   }
	   });
	});
	
	//判断旧密码是否正确
	function isOldPwd(){
		$.post("web/admin/isOldPwd",{oldPwd:$("#oldPwd").val()},function(data){
			if(!data){
				$("#oldPwd").focus();
				$("#oldPwd").val("");
				$("#oldPwd").attr("placeholder", "旧密码错误");
			}
		});
	}
	
	
	
</script>

<div class="title">
	<span style="margin-left: 20px;color: red;line-height: 60px;">${sessionScope.notAfter}</span>
    <div class="admin">
        <span>${sessionScope.XfStaff.xfName}</span>
        <span class="icon slideDown"><i></i></span>
    </div>
    <div class="slideMenu">
        <ul>
            <li class="updatePassword" id="modifyPwd">修改密码</li>
            <li class="quit"><a href="<%=basePath1%>/ssologinout">退出</a></li>
        </ul>
    </div>
</div>

<form enctype="multipart/form-data" id="editPwdForm" action="web/admin/editPwd" method="post">
	<div class="shadow" id="editPwd">
	    <div class="editPwd">
	        <h4 class="title">修改密码</h4>
	        <div class="container">
	            <div class="formList">
	                <div>
	                    <span style="margin-left: 8%">旧密码</span>
	                    <input type="password" name="oldPwd" id="oldPwd" onblur="isOldPwd();">
	                </div>
	                <div>
	                    <span style="margin-left: 8%">新密码</span>
	                    <input type="password" name="newPwd" id="newPwd">
	                </div>
	                <div>
	                    <span>确认新密码</span>
	                    <input type="password" name="againNewPwd" id="againNewPwd">
	                </div>
	            </div>
	            <br>
	            <div class="handle">
	                <span class="btn confirm" id="editPwdSubmit">确定</span>
	                <span class="btn cancel" id="editPwdCancel">取消</span>
	            </div>
	        </div>
	    </div>
	</div>
</form>
