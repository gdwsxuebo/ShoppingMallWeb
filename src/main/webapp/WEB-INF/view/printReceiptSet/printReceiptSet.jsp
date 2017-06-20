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
<link rel="stylesheet" href="new/css/receiptsSet.css">
</head>
<body>

<body>
<div id="all">
    <div id="receiptsSet">
		<%@ include file="../public/menu.jsp"%>
        <div class="right">
    	<%@ include file="../public/header.jsp"%>
            <div class="middle">
                <div class="map">
                    <span class="icon position"><i></i></span>
                    <span class="mapName">打印小票设置</span>
                </div>
                <!--除以下content内容，其余地方均为模板，不要改动-->
                <div class="content">
                    <div class="formList">
                        <p>打印数量设置</p>
                        <p><input type="text" value="${printReceiptCount}" name="printReceiptCount" id="printReceiptCount" placeholder="请输入1-5的数字"></p>
                        <p>打印小票结尾设置</p><br>
                        <p>
<%--                         <input type="text" value="${printReceiptTail}" name="printReceiptTail" id="printReceiptTail"> --%>
                        	<textarea rows="2" cols="32" name="printReceiptTail" id="printReceiptTail" style="padding: 10px;resize:none" >${printReceiptTail}</textarea>
                        </p>
                        <br>
                        <p><span class="btn" id="upSet">设置</span></p><br/>
                        
                       	<!-- logo -->
                        <form action="web/setings/uploadFile" id="submitLogo" method="post" enctype="multipart/form-data">
	                        <p><span class="btn" id="uploadLogo">上传小票LOGO</span><small>( 格式：xx.png )</small></p>
	                        <input type="hidden" name="fileType" value="3">
	                        <p id="erweima"> 
	                            <span class="close">×</span>
	                            <img src="${printLogo}" id="previewLogo" style="width: 80px; height: 80px">
	                        </p>
	                        <input type="file" id="upLogo" name='uploadFile' style="display: none" onchange="changeLogo(this);">
                        </form>
                        
	                   	<!-- 二维码 -->
                        <form action="web/setings/uploadFile" id="submitEWM" method="post" enctype="multipart/form-data">
	                        <p><span class="btn" id="uploadEwm">上传二维码</span><small>( 格式：xx.png)</small></p>
	                        <input type="hidden" name="fileType" value="4">
	                        <p id="yulan">
	                            <span class="close">×</span>
	                            <img src="${printEWM}" id="previewEWM" style="width: 80px; height: 80px">
	                        </p>
	                        <input type="file" id="upEwm" name="uploadFile" style="display: none" onchange="changeEWM(this);">
	                    </form>
                       
                      	<p style="display:none"><span class="btn" id="toReceipts">生成预览</span></p>
                    </div>
                    <div class="check">
                        <h3>收银小票预览区</h3>
                        <div class="receipts">
                            <h6>第一联 收银联</h6>
                            <p class="receiptsLogo"><img src="${printLogo}" alt="" style="width: 55px ; height: 55px" id="receiptsLogo"></p>
                            <h5>销售单</h5>
                            <div>
                                <p>店铺编号 : GWS711</p>
                                <p>收银店铺名称 : 高德唯斯001</p>
                                <p>收银机编号 : P001</p>
                                <p>流水号 : 123456789</p>
                                <p>交易时间 : 2017-02-20 09:00:00</p>
                            </div>
                            <table>
                                <thead>
                                    <tr>
                                        <th>货号</th>
                                        <th>品牌</th>
                                        <th>数量</th>
                                        <th>金额</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>11105</td>
                                        <td>NIKE</td>
                                        <td>x1</td>
                                        <td>599.00</td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td>应付金额 : </td>
                                        <td></td>
                                        <td colspan="2">599.00</td>
                                    </tr>
                                    <tr>
                                        <td>已收金额 : </td>
                                        <td></td>
                                        <td colspan="2">599.00</td>
                                    </tr>
                                    <tr>
                                        <td>找零金额 : </td>
                                        <td></td>
                                        <td colspan="2">0.00</td>
                                    </tr>
                                    <tr>
                                        <td>合计金额 : </td>
                                        <td></td>
                                        <td colspan="2">599.00</td>
                                    </tr>
                                    <tr>
                                        <td><h3>付款方式</h3></td>
                                    </tr>
                                    <tr>
                                        <td>现金</td>
                                        <td></td>
                                        <td colspan="2">599.00</td>
                                    </tr>
                                </tfoot>
                            </table>
                            <div>
                                <h5><span id="welcome" style="float: left;">欢迎光临！</span></h5><br>	
                                <h5><img src="${printEWM}" style="width: 80px; height: 80px" id="receiptsEWM"></h5>
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
	$("#upSet").click(function(){
		var printReceiptCount = $("#printReceiptCount").val();
		
		 if(isNaN(printReceiptCount) || printReceiptCount > 5){
			alert("打印数量设置请输入1-5的数字");
			$("#printReceiptCount").val("");
			return;
		}else{
			$.post("web/setings/setPrintReceipt",{printReceiptCount:printReceiptCount,printReceiptTail:$("#printReceiptTail").val()},function(data){
				if(data){
					var title = $("#printReceiptTail").val();
					if(title.length<34){
					$("#welcome").text(title);
					}else{
					    var strlen = 0;  
					    var s = "";  
					    for (var i = 0; i < title.length; i++) {  
					        if (title.charCodeAt(i) > 128) {  
					            strlen += 2;  
					        } else {  
					            strlen++;  
					        }  
					        s += title.charAt(i);  
					        if (strlen >= 34) {  
					        	$("#welcome").text(s+"...");
					            return ;  
					        }  
					    }  
					 
						}
					alert("设置成功");
				}else{
					alert("设置失败");
				}
			});
		}
	});
	
	$(function () {
		//选择logo
		$("#uploadLogo").click(function (){
			$("#upLogo").click();
		});
		//选择二维码
		$("#uploadEwm").click(function(){
			$("#upEwm").click();
		});
	});
	
	function changeLogo(file){
		var strSrc = file.value;
		var pos = strSrc.lastIndexOf(".");
		var lastname = strSrc.substring(pos, strSrc.length);
		var strRegex = "(.png)$"; // 用于验证图片扩展名的正则表达式
		var re = new RegExp(strRegex);
		if (!re.test(lastname.toLowerCase())) {
			alert("您上传的文件类型为" + lastname + "，图片必须为 PNG类型");
			return false;
		}
		if (file.files[0].size > 10485760) {
			alert("您上传的文件大小超出了10M限制！");
			return false;
		}
		var prevDiv = document.getElementById('previewLogo');
		if (file.files && file.files[0]) {
			var reader = new FileReader();
			reader.onload = function(evt) {
				prevDiv.attr("src",evt.target.result);
			}
			reader.readAsDataURL(file.files[0]);
		}
		$("#submitLogo").submit();
	}
	
	function changeEWM(file){
		var strSrc = file.value;
		var pos = strSrc.lastIndexOf(".");
		var lastname = strSrc.substring(pos, strSrc.length);
		//var strRegex = "(.jpg|.png)$"; // 用于验证图片扩展名的正则表达式
		var strRegex = "(.png)$";
		var re = new RegExp(strRegex);
		if (!re.test(lastname.toLowerCase())) {
			alert("您上传的文件类型为" + lastname + "，图片必须为 PNG类型");
			return false;
		}
		if (file.files[0].size > 10485760) {
			alert("您上传的文件大小超出了10M限制！");
			return false;
		}
		var prevDiv = document.getElementById('previewEWM');
		if (file.files && file.files[0]) {
			var reader = new FileReader();
			reader.onload = function(evt) {
				prevDiv.attr("src",evt.target.result);
			}
			reader.readAsDataURL(file.files[0]);
		}
		$("#submitEWM").submit();
	}
	
	$("#toReceipts").click(function(){
		//logo
		$("#receiptsLogo").attr("src",$("#previewLogo")[0].src);
		//EWM
		$("#receiptsEWM").attr("src",$("#previewEWM")[0].src);
		
		var title = $("#printReceiptTail").val();
		if(title.length<34){
		$("#welcome").text(title);
		}else{
		    var strlen = 0;  
		    var s = "";  
		    for (var i = 0; i < title.length; i++) {  
		        if (title.charCodeAt(i) > 128) {  
		            strlen += 2;  
		        } else {  
		            strlen++;  
		        }  
		        s += title.charAt(i);  
		        if (strlen >= 34) {  
		        	$("#welcome").text(s+"...");
		            return ;  
		        }  
		    }  
		 
			}
		
	
	});	
	
</script>
</body>
</html>