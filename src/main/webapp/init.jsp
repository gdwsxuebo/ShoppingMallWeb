<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>初始化</title>
    <link rel="stylesheet" href="resource/new/css/bootstrap.css">
    <link rel="stylesheet" href="resource/new/css/common.css">
    <link rel="stylesheet" href="resource/new/css/init.css">
</head>
<body>
<div class="container-fluid" id="login">
    <div class="row">
        <div class="loginSec">
            <div class="col-xs-6"></div>
            <div class="col-xs-6 right">
                <div>
                    <img src="resource/new/image/logo.png" alt="logo">
                    <div class="title">
                        <h4>高德睿云云POS收银系统初始设置</h4>
                    </div>
                    <form action="web/admin/login" id="loginForm" method="post" onsubmit="return check();">
	                    <div class="handleSec">
	                       <p>
		                       	<label>是否启用老MIS: </label>
		                       	<span class="icon switch_on" id="isAddEnabledIcon"><i id="addSwitch"></i></span>
	                       </p>
	                        <p>
								<label>商场管理员登录账号：</label> 
								<input name="staffcode" id="staffcode" type="text" class="form-control" placeholder="账  号" />
							</p>
	                        <p>
	                        	<label>商场管理员登录密码：</label> 
	                        	<input id="password" type="password" class="form-control" placeholder="密  码" />
	                        </p>
	                        
	                        <p>
	                        	<label>再一次输入登录密码：</label> 
	                        	<input id="againPassword" type="password" class="form-control" placeholder="密  码" />
	                        </p>
	                        <div class="confirm" onclick="setURL();">设置</div>
	                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="resource/js/jquery.js"></script>
<script type="text/javascript">
	$(function (){
		$("#addSwitch").click(function () {
	        if($(this).parent().hasClass("switch_on")){
	            $(this).parent().removeClass("switch_on").addClass("switch_off");
	        }else{
	            $(this).parent().removeClass("switch_off").addClass("switch_on");
	        }
	    });
	})

	var isOpen=false;
	function setURL() {
		var staffcode=$("#staffcode").val().trim();
		var password=$("#password").val().trim();
		var againPassword=$("#againPassword").val().trim();
		if (staffcode=="") {
			$("#staffcode").focus();
			$("#staffcode").attr("placeholder", "请输入商场管理员登录账号");
		}else if (staffcode.length < 5 || staffcode.length > 32) {
			$("#staffcode").focus();
			$("#staffcode").val("");
			$("#staffcode").attr("placeholder", "登录账号长度必须大于5小于32");
		}else if (password=="") {
			$("#password").focus();
			$("#password").attr("placeholder", "请输入商场管理员登录密码");
		}else if (password.length < 6 || password.length > 32) {
			$("#password").focus();
			$("#password").val("");
			$("#password").attr("placeholder", "登录密码长度必须大于6小于32");
		} else if(password!=againPassword){
			$("#againPassword").focus();
			$("#againPassword").val("");
			$("#againPassword").attr("placeholder", "重复密码与输入密码不一致");
		}else {
			var c =  $("#isAddEnabledIcon").attr("class");
			if(c == "icon switch_on"){
				isOpen=true;
			}else{
				isOpen=false;
			}
			$.post("web/init/init.do", {
				staffcode:staffcode,
				password:password,
				isOpenOldMis:isOpen
			}, function(data) {
				if (data.flag) {
					alert(data.msg);
					location.href = "login.jsp";
				} else {
					alert(data.msg);
				}
			});
		}
	}
	/* function isOpenOldMis(obj) {
		if(obj.checked){
			isOpen=true;
		}else{
			isOpen=false;
		}
	} */
</script>
</body>
</html>
