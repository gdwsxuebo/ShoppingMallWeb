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
    <title>登录</title>
    <link rel="stylesheet" href="resource/new/css/bootstrap.css">
    <link rel="stylesheet" href="resource/new/css/common.css">
    <link rel="stylesheet" href="new/css/login.css">
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
                        <h4>高德睿云云POS收银系统</h4>
                    </div>
                    <form action="web/admin/login" id="loginForm" method="post" onsubmit="return check();">
	                    <div class="handleSec">
	                        <p><input class="userName" name="staffcode" id="staffcode"  autofocus type="text" placeholder="用户名"></p>
	                        <p><input class="password" name="password" id="password" type="password" placeholder="密码"></p>
	                        <div class="confirm" id="loginSubmit">登录</div>
	                    </div>
                    </form>
                   	<c:if test="${sessionScope.msg!=''}">
	                    <br>
                   		<p>
							<font style="color: red; ">${sessionScope.msg}</font>
						</p>
					</c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="resource/new/js/jquery-1.11.2.js"></script>
<script src="resource/new/js/pos.js"></script>
<script type="text/javascript">
	function check() {
		var staffcode = $("#staffcode").val().trim();
		var password = $("#password").val().trim();
		if (staffcode == "") {
			$("#staffcode").focus();
			$("#staffcode").attr("placeholder", "请输入账号");
		} else if (staffcode.length > 15) {
			$("#staffcode").focus();
			$("#staffcode").val("");
			$("#staffcode").attr("placeholder", "账号长度不能超过15");
		} else if (password == "") {
			$("#password").focus();
			$("#password").attr("placeholder", "请输入密码");
		} else if (password.length > 32) {
			$("#password").focus();
			$("#password").val("");
			$("#password").attr("placeholder", "密码长度不能超过32");
		} else {
			return true;
		}
		return false;
	}
	$("#loginSubmit").click(function(){
		$("#loginForm").submit();
	})
</script>
</body>
</html>
