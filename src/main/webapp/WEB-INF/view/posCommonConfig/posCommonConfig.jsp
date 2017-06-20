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
    <link rel="stylesheet" href="new/css/unionPayCommon.css">
</head>
<body>
<div id="all">
    <div id="unionPayCommon">
        <%@ include file="../public/menu.jsp"%>
        <div class="right">
    	<%@ include file="../public/header.jsp"%>
            <div class="middle">
                <div class="map">
                    <span class="icon position"><i></i></span>
                    <span class="mapName">银联公用配置</span>
                </div>
                <!--除以下content内容，其余地方均为模板，不要改动-->
                <c:choose>
                	<c:when test="${config != null}">
		                <div class="content">
		                    <div class="formList">
		                        <div><span>网络</span><span>数字证书路径</span></div>
		                        <div>
		                            <div class="select_container">
		                                <div class="select">
		                                <c:choose>
		                                	<c:when test="${config.SSL_ON == 1}">
			                                    <div class="selectContainer">公网(1)</div>
		                                	</c:when>
		                                	<c:when test="${config.SSL_ON == 0}">
			                                    <div class="selectContainer">专网(0)</div>
		                                	</c:when>
		                                	<c:otherwise>
			                                    <div class="selectContainer">请选择</div>
		                                	</c:otherwise>
		                                </c:choose>
		                                </div>
		                                <ul id="editSSL_ON" class="select_ul">
		                                    <li value="1">公网(1)</li>
		                                    <li value="0">专网(0)</li>
		                                </ul>
		                            </div>
		                            <input type="text" name="ssl_cert" id="editssl_cert" value="${config.ssl_cert}">
		                        </div>
		                        <div><span>POS机IP</span><span>TPDU值</span></div>
		                        <div>
		                            <input type="text" name="ipPos" id="editipPos" value="${config.ipPos}"><input type="text" name="tpdu" id="edittpdu" value="${config.tpdu}">
		                        </div>
		                        <div><span>端口值</span><span>POS串口号</span></div>
		                        <div>
		                            <input type="text" name="port" id="editport" value="${config.port}"><input type="text" name="devPath" id="editdevPath" value="${config.devPath}">
		                        </div>
		                        <div><span>终端机型</span><span>波特率</span></div>
		                        <div>
		                            <input type="text" name="term_info" id="editterm_info" value="${config.term_info}"><input type="text" name="baudRate" id="editbaudRate" value="${config.baudRate}">
		                        </div>
		                        <div><span>设备序列号</span><span></span></div>
		                        <div>
		                        	<input type="text" name="ssl_sn" id="editssl_sn" value="${config.ssl_sn}"><input type="hidden">
		                        </div>
		                    </div>
		                    <div class="handle"><br>
		                        <span class="btn confirm" id="editSubmit">修改</span>
		                       <span class="btn cancel"><a style="text-decoration: none;" href="web/getPosComConfig">取消</a></span>
		                    </div>
		                </div>
                	</c:when>
                	<c:otherwise>
                		<div class="content">
		                    <div class="formList">
		                        <div><span>网络</span><span>数字证书路径</span></div>
		                        <div>
		                            <div class="select_container">
		                                <div class="select">
		                                    <div class="selectContainer">请选择</div>
		                                </div>
		                                <ul id="SSL_ON" class="select_ul">
		                                    <li value="">请选择</li>
		                                    <li value="1">公网(1)</li>
		                                    <li value="0">专网(0)</li>
		                                </ul>
		                            </div>
		                            <input type="text" name="ssl_cert" id="ssl_cert">
		                        </div>
		                        <div><span>POS机IP</span><span>TPDU值</span></div>
		                        <div>
		                            <input type="text" name="ipPos" id="ipPos" ><input type="text" name="tpdu" id="tpdu">
		                        </div>
		                        <div><span>端口值</span><span>POS串口号</span></div>
		                        <div>
		                            <input type="text" name="port" id="port" ><input type="text" name="devPath" id="devPath">
		                        </div>
		                        <div><span>终端机型</span><span>波特率</span></div>
		                        <div>
		                            <input type="text" name="term_info" id="term_info"><input type="text" name="baudRate" id="baudRate">
		                        </div>
		                        <div><span>设备序列号</span><span></span></div>
		                        <div>
		                        	<input type="text" name="ssl_sn" id="ssl_sn"><input type="hidden">
		                        </div>
		                    </div>
		                    <div class="handle"><br>
		                        <span class="btn confirm" id="addSubmit">确认</span>
		                        <span class="btn cancel"><a style="text-decoration: none;" href="web/getPosComConfig">取消</a></span>
		                    </div>
		                </div>
                	</c:otherwise>
                </c:choose>
                
            </div>
            <%@ include file="../public/footer.jsp"%>
        </div>
    </div>
</div>
<script>
    $(function () {
        /*点击下拉菜单*/
        $(".select").click(function () {
            var select = this;
            $(this).next().show();
            $(this).next().find("li:not(.first)").click(function () {
                $(select).find(".selectContainer").html($(this).html());
                $(this).parent().hide();
            });
        });
    });
    
    /* 添加 */
    var SSL_ON;
    $("#SSL_ON li").click(function (){
    	SSL_ON = $(this).attr('value');
    });
    $("#addSubmit").click(function(){
    	var ssl_cert = $("#ssl_cert").val();  //数字证书路径
    	var ipPos = $("#ipPos").val(); //pos机IP
    	var tpdu = $("#tpdu").val(); //tpdu
    	var port = $("#port").val(); //端口
    	var devPath = $("#devPath").val(); //POS串口号
    	var term_info = $("#term_info").val(); //终端机型
    	var baudRate = $("#baudRate").val(); //波特率
    	var ssl_sn = $("#ssl_sn").val(); //序列号
    	$.post("web/addPosComConfig",{ssl_sn:ssl_sn,SSL_ON:SSL_ON,ssl_cert:ssl_cert,ipPos:ipPos,tpdu:tpdu,port:port,devPath:devPath,term_info:term_info,baudRate:baudRate},function(data){
    		if(data){
    			alert("添加成功");
    			window.location.reload();
    		}else{
    			alert("添加失败");
    		}
    	});
    });
    
    /* 修改 */
    var editSSL_ON;
    $("#editSSL_ON li").click(function (){
    	editSSL_ON = $(this).attr('value');
    });
    //修改
    $("#editSubmit").click(function(){
    	var ssl_cert = $("#editssl_cert").val();  //数字证书路径
    	var ipPos = $("#editipPos").val(); //pos机IP
    	var tpdu = $("#edittpdu").val(); //tpdu
    	var port = $("#editport").val(); //端口
    	var devPath = $("#editdevPath").val(); //POS串口号
    	var term_info = $("#editterm_info").val(); //终端机型
    	var baudRate = $("#editbaudRate").val(); //波特率
    	var ssl_sn = $("#editssl_sn").val(); //序列号
    	var c = '${config.SSL_ON}';
    	if(editSSL_ON == null && c == 1){
    		editSSL_ON = 1;
    	}
    	if(editSSL_ON == null && c == 0){
    		editSSL_ON = 0;
    	}
    	$.post("web/updatePosComConfig",{ssl_sn:ssl_sn,SSL_ON:editSSL_ON,ssl_cert:ssl_cert,ipPos:ipPos,tpdu:tpdu,port:port,devPath:devPath,term_info:term_info,baudRate:baudRate},function(data){
    		if(data){
    			alert("修改成功");
    			window.location.reload();
    		}else{
    			alert("修改失败");
    		}
    	});
    });
    
</script>
</body>
</html>