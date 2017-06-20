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
</head>
<body>
<div id="all">
    <div id="store">
    	<%@ include file="public/menu.jsp"%>
        <div class="right">
    	<%@ include file="public/header.jsp"%>
            <div class="middle">
                <div class="map">
                    <span class="icon position"><i></i></span>
                    <span class="mapName">收银机</span>
                </div>
                <!--除以下content内容，其余地方均为模板，不要改动-->
                <div class="content">
                    <div class="title">
                        <div class="search">
                            <input type="text" id="tillidkey" name="key" placeholder="店铺号、收银机编号">
                            <span class="searchBtn icon" id="searchTillid"><i></i></span>
                        </div>
                        <div class="dataDetails">
                        	&nbsp;&nbsp;
                        	在线数：<label id="online"></label> &nbsp;
							离线数：<label id="outline"></label>
                        </div>
                    </div>
                    <div class="tableContainer">
                        <table cellpadding="0" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>店铺编号</th>
                                    <th>收银机编号</th>
                                    <th>ip地址</th>
                                    <th>收银机状态</th>
                                </tr>
                            </thead>
                            <tbody id="tillidState">
                            	
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <%@ include file="public/footer.jsp"%>
        </div>
    </div>
</div>
<script type="text/javascript">
$(function(){
	var url="web/xfTillid/getTillidData";
	 $.fn.extend({
		 createGrid:function(url){
			 $.ajax({
			  		url:url,
			  		type:'POST',
			  		dataType:'json',
			  		data:{key:$("#tillidkey").val()},
			  		success:function(data){
			  			if(data.code=="10000"){
			  				var i=0;
			         		 $('#tillidState').empty();
			  				$("#online").html(data.onlineCount);
			  				$("#outline").html(data.outlineCount);
			  				var rows=data.rows;
			  				for(var x in rows){
			  					i++;
			  					var storeinfo=rows[x].xfStorecode.xfStorecode+":"+rows[x].xfStorecode.xfName;
			  					var stateInfo='';
			  					if(rows[x].state==0){
			  						stateInfo='<img src="resource/images/outline.gif" style="vertical-align:middle;" alt="离线"></img>&nbsp;离线';
			  					}
			  					else{
			  						stateInfo='<img src="resource/images/online.gif" style="vertical-align:middle;" alt="在线"></img>&nbsp;在线';
			  					}
			  					$('#tillidState').append('<tr><td>'+i+'</td><td>'+storeinfo+'</td><td>'+rows[x].tillid+'</td><td>'+rows[x].ip+'</td><td>'+stateInfo+'</td></tr>');
			  				}
			  			}else{
			  				alert("网络异常code0x6836");
			  			}			
			  		}
			  	});
		 }
	 });

	 $("#searchTillid").click(function(){
		 $("#tillidState").createGrid(url);
	 });
	 $("#loadTillid").click(function(){
		 $("#tillidState").createGrid(url);
	 });
	 
	 $(document).keypress(function(event){  
		    var keycode = (event.keyCode ? event.keyCode : event.which);  
		    if(keycode == '13'){
				 $("#tillidState").createGrid(url);
		    }  
		}); 
	 
	 $("#tillidState").createGrid(url);
	 setInterval(function(){$("#tillidState").createGrid(url)}, 10000);
});

</script>
</body>
</html>