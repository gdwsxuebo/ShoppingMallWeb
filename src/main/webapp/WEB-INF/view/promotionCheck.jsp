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
    <link rel="stylesheet" href="new/css/coupons.css">
</head>
<body>
<div id="all">
    <div id="coupons">
        <%@ include file="public/menu.jsp"%>
        <div class="right">
    	<%@ include file="public/header.jsp"%>
            <div class="middle parent">
                <div class="map">
                    <span class="icon position"><i></i></span>
                    <span class="mapName">促销 > 促销平台验证</span>
                </div>
                <!--除以下content内容，其余地方均为模板，不要改动-->
                <div class="content">
                    
                    <div class="tableContainer" >
                      
                      <div style="line-height: 30px;">
                      <div class="title">
                      	 <h1 style="margin-left: 44%;font-size: 20px;">请输入优惠券编号 </h1>
                      </div>
                     
                      
                      <div class="title" >
                    	<form class="form-search" >
	                        <div class="search" style="margin-left:10px; ">
	                            <input style="width: 223px" id="code" placeholder="优惠券编号" />
	                        </div>
	                    </form>
	                    <div class="btn syncData" id="checkCode">查询</div>
                        <div class="dataDetails"> <input id="jzcode" style="margin-left: 20px;" name="codetype" type="radio" value="1"  checked="checked"/><span style="color: red;" >集字送礼券</span><input id="ptcode" style="margin-left: 20px;" name="codetype" type="radio" value="2"   /><span style="color: red;">普通礼品券</span></div>
                       </div>
                     
                      </div>
                      <div class="tableContainer" style="height: 400px;" id="showImages">

                      	 
                      </div>
                      
                      
                      
                      <div class="tableContainer" style="height: 100px;">
                      	<div class="pop" style="margin-top: 150px;" >
                      	<div class="handle">
	                    <span class="btn confirm" id="editSubmit">验证</span>
	                    <span class="btn cancel" id="checkClear">重置</span>
	                  	</div>
	                  	</div>
	                  </div>
                      
                      
	                  
	                  <div class="tableContainer" style="height: 200px;" id="showdata" >
                      	<span style="margin-left: 20%;float: left;margin-top: 50px;font-size: 20px;">礼品编号:<span id="showdatacode" style="font-size: 20px;color: red;"></span></span> <span style="margin-right:30%;margin-top: 50px;float: right;font-size: 20px;">礼品名称:<span id="showdatacodeName" style="font-size: 20px;color: red;"></span></span>
                      </div>
                      
                      
                    </div>
                </div>
            </div>
            <%@ include file="public/footer.jsp"%>
        </div>
    </div>
</div>
<script>
 function toPost(obj){
	 window.location.href="web/promotion/getPsStatisticsByid?id="+obj;
 }
//查询BASE64图片
 $("#checkCode").click(function() {
	 
	if($("#code").val()==""||$("#code").val()==null){
		alert("礼品券编号不能为空!");
	}else{
		$.get("web/promotion/getImageBase",{code : $("#code").val()}, function(data) {
			if (data!= "") {
				
				if(data.msg==""){
					 $("#showImages").append('<div style="width: 200px;height: 100px;float: left;margin-left: 50px;margin-top: 20px;"><p>优惠券编号:'+data.code+'</p><img alt="'+data.code+'" src="data:image/png;base64,'+data.base64+'"  style="width: 200px;height: 80px" ondblclick="removeimage(this)" ></div>');
				}else{
					alert(data.msg);			
				}
			}
			$("#code").val("");
	 	});
	}
 
 	
 });


$("#ptcode").click(function(){
	//alert("ssss");
	$("#checkCode").hide();
});

$("#jzcode").click(function(){
	$("#checkCode").show();
});

//平台验证促销券
 $("#editSubmit").click(function(){
	
	var type=$("input[name='codetype']:checked").val();
 	$("#showdatacode").html("");
	$("#showdatacodeName").html("");
	
	if(type==1){
		var alts="#";//所有的alt;
	 	var imglist=$("#showImages>div>img");
	    
	 	for(var i=0;i<imglist.length;i++){
	 		alts+=imglist[i].alt+"#";
	 	}
	 	
	 	alert(alts);
	 	$.get("web/promotion/checkPromotionCodes",{codes : alts,type:type}, function(data) {
	 		console.log(data);
			if (data!= "") {
				$("#showdatacode").html(data.value);
				$("#showdatacodeName").html(data.valueName);
			}else{
				$("#showdatacode").html(data.value);
				$("#showdatacodeName").html(data.valueName);
			}
	 	});
	}else{
	
	 	$.get("web/promotion/checkPromotionCodes",{codes : $("#code").val(),type:type}, function(data) {
	 		console.log(data);
			if (data!= "") {
				$("#showdatacode").html(data.value);
				$("#showdatacodeName").html(data.valueName);
			}else{
				$("#showdatacode").html(data.value);
				$("#showdatacodeName").html(data.valueName);
			}
			$("#code").val("");
	 	});
	}
	
 	
 });
 
 $("#checkClear").click(function(){
	 $("#code").val("");
	 $("#showImages").empty();
	 $("#showdatacode").html("");
	 $("#showdatacodeName").html("");
 });
 

 function removeimage(obj){
	 $(obj).parent().remove(); 
    }
 



</script>
</body>
</html>