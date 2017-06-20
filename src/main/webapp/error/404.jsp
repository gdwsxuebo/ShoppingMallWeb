<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>页面错误</title>
<style type="text/css">
body {
	padding:0;
	margin:0;
	background:url(/error/bg.jpg);
}
div {
	width:502px;
	height:343px;
	margin:0 auto;
	overflow:hidden;
	padding-top:120px;
}
</style>
</head>
<body>

<div><img src="/error/404.jpg" border="0" usemap="#Map" />
  <map name="Map" id="Map">
    <area shape="rect" coords="177,279,361,310" href="<%=basePath %>" />
  </map>
</div>
</body>
</html>
