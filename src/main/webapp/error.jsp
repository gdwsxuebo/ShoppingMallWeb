<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<title>高德睿云云POS收银系统</title>
<link rel="shortcut icon" type="image/x-icon"
	href="resource/ico/favicon.ico" media="screen">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="resource/404/css/main.css" type="text/css"
	media="screen, projection" />
<!-- main stylesheet -->
<link rel="stylesheet" type="text/css" media="all" href="resource/404/css/tipsy.css" />
<!-- Tipsy implementation -->

<!--[if lt IE 9]>
	<link rel="stylesheet" type="text/css" href="resource/404/css/ie8.css" />
<![endif]-->

<script type="text/javascript" src="resource/404/scripts/jquery-1.7.2.min.js"></script>
<!-- uiToTop implementation -->
<script type="text/javascript" src="resource/404/scripts/custom-scripts.js"></script>
<script type="text/javascript" src="resource/404/scripts/jquery.tipsy.js"></script>
<!-- Tipsy -->

<script type="text/javascript">
	$(document).ready(function() {

		universalPreloader();

	});

	$(window).load(function() {

		//remove Universal Preloader
		universalPreloaderRemove();

		rotate();
		dogRun();
		dogTalk();

		//Tipsy implementation
		$('.with-tooltip').tipsy({
			gravity : $.fn.tipsy.autoNS
		});

	});
</script>


</head>

<body>

	<!-- Universal preloader -->
	<div id="universal-preloader">
		<div class="preloader">
			<img src="resource/404/images/universal-preloader.gif" alt="universal-preloader"
				class="universal-preloader-preloader" />
		</div>
	</div>
	<!-- Universal preloader -->

	<div id="wrapper">
		<!-- 404 graphic -->
		<div class="graphic"></div>
		<!-- 404 graphic -->
		<!-- Not found text -->
		<div class="not-found-text">
			<h1 class="not-found-text">文件未找到!</h1>
		</div>
		<!-- Not found text -->

		<!-- search form -->
		<!-- <div class="search">
			<form name="search" method="get" action="#" />
			<input type="text" name="search" value="Search ..." /> <input
				class="with-tooltip" title="Search!" type="submit" name="submit"
				value="" />
			</form>
		</div> -->
		<!-- search form -->

		<!-- top menu -->
		<!-- <div class="top-menu">
			<a href="#" class="with-tooltip" title="Return to the home page">首页</a>
			| <a href="#" class="with-tooltip"
				title="Navigate through our sitemap">站点地图</a> | <a href="#"
				class="with-tooltip" title="Contact us!">联系</a> | <a href="#"
				class="with-tooltip" title="Request additional help">帮助</a>
		</div> -->
		<!-- top menu -->

		<div class="dog-wrapper">
			<!-- dog running -->
			<div class="dog"></div>
			<!-- dog running -->

			<!-- dog bubble talking -->
			<div class="dog-bubble"></div>

			<!-- The dog bubble rotates these -->
			<div class="bubble-options">
				<p class="dog-bubble">你输了，芽？别担心，我是一个很好的指导！</p>
				<p class="dog-bubble">
					<br /> 汪! 汪!
				</p>
				<p class="dog-bubble">
					<br /> 不用担心！我就可以了！
				</p>
				<p class="dog-bubble">
					我希望我有一个饼干<br />
					<img style="margin-top: 8px" src="resource/404/images/cookie.png" alt="cookie" />
				</p>
				<p class="dog-bubble">
					<br /> 吉兹！这是很烦人！
				</p>
				<p class="dog-bubble">
					<br /> 我在越来越接近？
				</p>
				<p class="dog-bubble">还是我只是要在圈子？罗...</p>
				<p class="dog-bubble">
					<br /> OK，我现在正式失去了...
				</p>
				<p class="dog-bubble">
					I think I saw a <br />
					<img style="margin-top: 8px" src="resource/404/images/cat.png" alt="cat" />
				</p>
				<p class="dog-bubble">那么，我们应该在寻找，到底？ @_@</p>
			</div>
			<!-- The dog bubble rotates these -->
			<!-- dog bubble talking -->
		</div>

		<!-- planet at the bottom -->
		<div class="planet"></div>
		<!-- planet at the bottom -->
	</div>
</body>
</html>
