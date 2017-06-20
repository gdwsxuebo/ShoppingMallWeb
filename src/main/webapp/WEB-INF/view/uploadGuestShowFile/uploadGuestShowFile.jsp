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
<link rel="stylesheet" href="new/css/customDisplay.css">
</head>
<body>
<div id="all">
    <div id="customDisplay">
        <%@ include file="../public/menu.jsp"%>
        <div class="right">
    	<%@ include file="../public/header.jsp"%>
            <div class="middle">
                <div class="map">
                    <span class="icon position"><i></i></span>
                    <span class="mapName">客显设置</span>
                </div>
                <!--除以下content内容，其余地方均为模板，不要改动-->
                <div class="content">
                    <div class="formList">
                    
                        <!-- ----------------------------------logo--------------------------------------- -->
                    	<form action="web/setings/uploadFile" id="submitLogo" method="post" enctype="multipart/form-data">
                    		<input type="hidden" name="fileType" value="2">
	                        <div>
	                            <span class="btn uploadLogo" id="uploadLogo">LOGO上传</span>
	                            <small>( 格式：xx.jpg 或者 xx.png )</small>
	                        </div>
	                        <input type="file" id="upLogo" name='uploadFile' style="display: none" onchange="changeLogo(this);">
                        </form>
                        <!-- logo展示 -->
                        <div id="erweima">
                            <span class="close">×</span>
                            <img src="${logoUrl}" style="width: 80px; height: 80px">
                        </div>
                        
                       	<!-- --------------------------图片---------------------------------- -->
	                    <form action="web/setings/uploadFile" id="submitJpg" method="post" enctype="multipart/form-data">
	                    	<input type="hidden" name="fileType" value="0">
	                        <div style="height: 40px;">
	                        	<span class="btn uploadLogo" id="uploadJpg">图片上传</span>
	                        	<small>( 格式：xx.jpg 或者 xx.png )</small>
	                        </div>
	                        <!-- 图片展示 -->
	                        <div id="yulan">
	                            <span class="close">×</span>
	                            <img src="${imageUrl}" style="width: 80px; height: 80px">
	                        </div>
	                        <input type="file" id="upJpg" name='uploadFile' style="display: none" onchange="changeJpg(this);">
                        </form>
                        <!-- ----------------------------视频-------------------------------- -->
                        <form action="web/setings/uploadFile" id="submitVideo" method="post" enctype="multipart/form-data">
                        	<input type="hidden" name="fileType" value="1">
	                        <div>
	                        	<span class="btn uploadLogo" id="uploadVideo">视频上传</span>
	                        	<small>( 格式：xx.mp4 )</small>
	                        </div>
	                        <input type="file" id="upVideo" name='uploadFile' style="display: none" onchange="changeVideo(this);">
                        </form>
                        
                      	<br>
<!--                         <div><span class="btn preview">生成预览</span></div> -->
                    </div>
                    <div class="check">
                        <h4>副屏预览区域</h4>
                        <div>
                            <div class="logo"><img src="${logoUrl}" style="width: 25px; height: 25px; background-color: gray"></div>
                            <div class="imgAndMoney">
                                <div class="img" id="imgorvideo">
                                	<c:choose>
                                		<c:when test="${fileType=='0'}">
                                			<img alt="" src="${imageUrl}" style="width: 446px;height: 293px">
                                		</c:when>
                                		<c:otherwise>
                                			<video width="446px" height="293px" controls> 
												<source src="${requestScope.videoUrl}" type="video/mp4"> 
												<source src="${requestScope.videoUrl}" type="video/ogg"> 
												<source src="${requestScope.videoUrl}" type="video/webm"> 
												<object data="${requestScope.videoUrl}" width="820" height="640">
											<embed src="${requestScope.videoUrl}" width="820" height="640">
												</object>
											</video>
                                		</c:otherwise>
                                	</c:choose> 	
                                </div>
                                <div class="money"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@ include file="../public/footer.jsp"%>
        </div>
    </div>
</div>
<script type="text/javascript">
	/* logo上传  */
	$("#uploadLogo").click(function (){
		$("#upLogo").click();
	});
	function changeLogo(file){
		var strSrc = file.value;
		var pos = strSrc.lastIndexOf(".");
		var lastname = strSrc.substring(pos, strSrc.length);
		var strRegex = "(.jpg|.png)$"; // 用于验证图片扩展名的正则表达式
		var re = new RegExp(strRegex);
		if (!re.test(lastname.toLowerCase())) {
			alert("您上传的文件类型为" + lastname + "，图片必须为 JPG类型或者PNG类型");
			return false;
		}
		if (file.files[0].size > 10485760) {
			alert("您上传的文件大小超出了10M限制！");
			return false;
		}
		$("#submitLogo").submit();
	}
	
	/* 图片上传 */
	$("#uploadJpg").click(function (){
		$("#upJpg").click();
	});
	function changeJpg(file){
		var strSrc = file.value;
		var pos = strSrc.lastIndexOf(".");
		var lastname = strSrc.substring(pos, strSrc.length);
		var strRegex = "(.jpg|.png)$"; // 用于验证图片扩展名的正则表达式
		var re = new RegExp(strRegex);
		if (!re.test(lastname.toLowerCase())) {
			alert("您上传的文件类型为" + lastname + "，图片必须为 或者PNG类型");
			return false;
		}
		if (file.files[0].size > 10485760) {
			alert("您上传的文件大小超出了10M限制！");
			return false;
		}
		$("#submitJpg").submit();
	}
	
	/* 视频上传 */
	$("#uploadVideo").click(function (){
		$("#upVideo").click();
	});
	function changeVideo(file){
		var strSrc = file.value;
		var pos = strSrc.lastIndexOf(".");
		var lastname = strSrc.substring(pos, strSrc.length)
		var strRegex = "(.mp4)$"; // 用于验证视频扩展名的正则表达式
		var re = new RegExp(strRegex);
		if (!re.test(lastname.toLowerCase())) {
			alert("您上传的文件类型为" + lastname + "，图片必须为 mp4类型");
			return false;
		}
		if (file.files[0].size > 1048576000) {
			alert("您上传的文件大小超出了100M限制！");
			return false;
		}
		$("#submitVideo").submit();
	}
	
		
 	/*点击下拉菜单*/
	$(function (){
	    $(".formList .select").click(function () {
	        var select = this;
	        $(this).next().show();
	        $(this).next().find("li").click(function () {
	        	$(select).find(".selectContainer").html($(this).html());
	        	$(this).parent().hide();
	        });
	    });
	});
</script>
</body>
</html>