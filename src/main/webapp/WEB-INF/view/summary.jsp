<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/paging" prefix="p"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>高德睿云云POS收银系统</title>
<style>
        *{
            margin: 0;
            padding: 0;
        }
        li{
            list-style: none;
        }
        a{
            text-decoration: none;
        }
        .fl{
            float: left;
        }
        .fr{
            float: right;
        }
        .clear{
            clear: both;
        }
        .mengban{
            width: 100%;
            overflow: hidden;
            background-color: rgba(0,0,0,0.2);
        }
        .mengban .saleDetailsBox{
            position: fixed;
            top:20% ;
            left:40.495%;
            width: 21.01%;
            height:500px;
   			overflow: auto;
        }
        .mengban .saleDetailsBox ._saleHeader{
            width:100% ;
            height: 10%;
            text-align: center;
           line-height: 45px;
            border-radius: 4px 4px 0 0;
            background-color: rgb(74,89,111);
            color:#fff;
            font-size: 16px;

        }
        .mengban .saleDetailsBox ._content{
            padding: 1% 1% 1% 1%;
            background-color: #fff;
            border-radius: 0 0 4px 4px;
            position: fixed;
            top:25% ;
            left:40.495%;
            width: 19.01%;
            height:450px;
   			overflow: auto;
        }
        .mengban .saleDetailsBox ._content li{
            width: 100%;
            margin-bottom: 3px;
        }
        .mengban .saleDetailsBox ._content span,.mengban .saleDetailsBox ._content small{
            font-size: 14px;
            color: #000;
        }
        .mengban .saleDetailsBox ._content table{
            width: 100%;
        }
        .mengban .saleDetailsBox ._content table th,.mengban .saleDetailsBox ._content table td{
            text-align: left;
        }
        .mengban .saleDetailsBox ._content table tr th:last-of-type,.mengban .saleDetailsBox ._content table tr td:last-of-type{
            text-align: right;
        }
        .mengban .saleDetailsBox ._content .okBTn{
            text-align: center;

        }
        .mengban .saleDetailsBox ._content .okBTn button{
             height:40px;
            width: 80px;
            margin: 0 auto;
            background-color: #65cea7;
            border-radius: 5px;
            text-align: center;
            line-height: 40px;
            font-size: 14px;
            color: #fff;
            border: none;
            cursor: pointer;

         }
        .mengban .saleDetailsBox ._content .f_ul span.threeWord{
            letter-spacing: 11px;
        }
        .mengban .saleDetailsBox ._content .f_ul span.threeWord+small{
            margin-left: -1px;
        }
        .mengban .saleDetailsBox ._content .f_ul span.fourWord{
            letter-spacing: 5px;
        }
        .mengban .saleDetailsBox ._content .f_ul span.fourWord+small{
            margin-left: 3px;
        }
        .mengban .saleDetailsBox ._content .f_ul span.fiveWord{
            letter-spacing: 1.1px;
        }
        .mengban .saleDetailsBox ._content .f_ul span.fiveWord+small{
            margin-left: 8.3px;
        }
        .mengban .saleDetailsBox ._content .s_ul .paymentWay{
            font-weight: 700;
        }

    </style>
<link rel="shortcut icon" type="image/x-icon" href="resource/ico/favicon.ico" media="screen">
<link rel="stylesheet" href="new/css/common.css">
<link rel="stylesheet" href="new/css/tableComm.css">
<link rel="stylesheet" href="new/css/goods.css">
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
                    <span class="mapName">销售记录</span>
                </div>
                <!--除以下content内容，其余地方均为模板，不要改动-->
                <div class="content">
                    <div class="title">
                    	<form class="form-search" action="web/xfSummary/getSummary" method="post" id="searchForm">
	                        <div class="search">
	                            <input type="text" value="${requestScope.key==null?null:requestScope.key}" id="key" name="key" placeholder="店铺号、销售单号">
	                            <span class="searchBtn icon" id="submitSearchForm"><i></i></span>
	                        </div>
	                    </form>
                        <div class="dataDetails">
                        	设置销售数据上传到新MIS时间间隔：
                        	<select onchange="changeThTime(this);" style="width: 25%;">
								<c:forEach var="s" begin="1" step="9" end="1440">
									<c:choose>
										<c:when test="${s*60*1000==requestScope.saleSummaryTime}">
											<option value="${s*60*1000}" selected="selected">${s}分钟</option>
										</c:when>
										<c:otherwise>
											<option value="${s*60*1000}">${s}分钟</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
                        </div>
                    </div>
                    <div class="tableContainer">
                        <table cellpadding="0" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>销售单号</th>
                                    <th>日期</th>
                                    <th>时间</th>
                                    <th>商场编号</th>
                                    <th>店铺编号</th>
                                    <th>收银店铺</th>
                                    <th>收银员编号</th>
                                    <th>会员号</th>
                                    <th>营业员</th>
                                    <th>销售总数量（±）</th>
                                    <th>销售净金额（±）</th>
                                    <th>退货原销售单</th>
                                    <th>退货授权人编号</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
								<c:forEach var="xfss" items="${paging.data}" varStatus="status">
	                                <tr>
	                                    <td>${paging.startRowNum+(status.index)}</td>
	                                    <td>${xfss.txdocno}</td>
	                                    <td><fmt:formatDate value="${xfss.txdate}" type="both" pattern="yyyy.MM.dd" /></td>
	                                    <td>${xfss.txtime}</td>
	                                    <td>${xfss.mallid.xfMallid}</td>
	                                    <td>${xfss.storecode.xfStorecode}</td>
	                                    <td>${xfss.cashStorecode.xfName}</td>
	                                    <td>${xfss.staffcode.xfStaffcode}</td>
	                                    <td>${xfss.vipcode}</td>
	                                    <td>${xfss.salesman}</td>
	                                    <td>${xfss.netqty}</td>
	                                    <td>${xfss.netamount}</td>
	                                    <td>${xfss.originalTxdocno}</td>
	                                    <td>${xfss.refundStaff}</td>
	                                    <td><span class="btn update" onclick="showSummary('${xfss.txdocno}');">查看</span></td>
	                                </tr>
								</c:forEach>
                            </tbody>
                        </table>
                        <!-- 分页 -->
                        <%@ include file="public/paging.jsp"%>
                    </div>
                </div>
            </div>
           <%@ include file="public/footer.jsp"%>
        </div>
    </div>
    
    <!-- 查看销售详情 -->
    <div class="shadow" id="showSummary"  >
	     <input type="hidden" name="storeCode" id="storeCode">
	        <div >
    	<div class="mengban" >
    	<input type="hidden" name="storeCode" id="storeCode">
    <div class="saleDetailsBox">
        <div class="_saleHeader">查看销售单详情</div>
        <div class="_content">
            <ul class="f_ul">
                <li><span class="fourWord">销售单号:</span><small id="txdocno"></small></li>
                <li><span class="fourWord">交易时间:</span><small id="txdate"></small></li>
                <li><span class="fourWord">商品编号:</span><small id="mallid"></small></li>
                <li><span class="fourWord">店铺编号:</span><small id="storecode"></small></li>
                <li><span class="fourWord">收银店铺:</span><small id="xfName"></small></li>
                <li><span class="fiveWord">收银员编号:</span><small id="staffcode"></small></li>
                <li><span class="threeWord">会员号:</span><small id="vipcode"></small></li>
                <li><span class="threeWord">营业号:</span><small id="salesman"></small></li>
                <li><span class="fiveWord">销售总数量:</span><small id="netqty"></small></li>
                <li><span class="fiveWord">销售净金额:</span><small id="netamount"></small></li>
            </ul>
            <table style="margin-top: 20px;">
                <thead>
                    <tr>
                        <th>货号</th>
                        <th>品牌</th>
                        <th>数量</th>
                        <th>金额</th>
                    </tr>
                </thead>
                <tbody id="siscout">
                    
                </tbody>
            </table>
            <ul class="s_ul">
                <li><span class="fl">应收金额:</span><small class="fr"  id="net"></small><br class="clear"></li>
                <li><span class="fl">已收金额:</span><small class="fr" id="desci"></small><br class="clear"></li>
                <li><span class="fl">找零金额:</span><small class="fr" id="qty"></small><br class="clear"></li>
                <li><span class="fl">合计金额:</span><small class="fr" id="count"></small><br class="clear"></li>
                <li style="margin-top: 20px;"><span class="fl paymentWay">付款方式</span><br class="clear"></li>    
            </ul>
            <div id="sts">
              </div>
            <div class="okBTn"><button>确定</button></div>
        </div>
    </div>
</div>
	     
	            
	        </div>
	    </div>
    
</div>
<script type="text/javascript">

//查询
$("#submitSearchForm").click(function() {
	$("#searchForm").submit();
});

/*关闭弹窗*/
$(".okBTn").click(function () {
    $("#showSummary").hide();
});
function changeThTime(obj) {
	var value = obj.value; // 选中值
	$.post("web/xfSummary/setThTime",{time:value},function(data){
		if(!data){
			alert("设置失败！");
		}
	});
}


//商品详细查看
function showSummary(txdocno){
	$("#sts").empty();
	$("#siscout").empty();
	 $("#showSummary").show();
	$.post("web/xfSummary/getSummaryBycode",{txdocno:txdocno},function(data){
			$("#txdocno").html(data.txdocno);
			$("#mallid").html(data.mallid);
			$("#netamount").html(data.netamount);
			$("#netqty").html(data.netqty);
			$("#salesman").html(data.salesman);
			$("#staffcode").html(data.staffcode);
			$("#storecode").html(data.storecode);
			$("#vipcode").html(data.vipcode);
			$("#xfName").html(data.xfName);
			$("#xfTenderdesc").html(data.xfTenderdesc);
			$("#txdate").html(data.date+" "+data.txtime);
			var listsis = data.listsis;
			
			for(var i=0;i<listsis.length;i++){
				var sis = listsis[i];
				$("#siscout").append('<tr><td>'+sis.xfplu+'</td><td>'+sis.desci+'</td><td>'+sis.qty+'</td><td>'+sis.netamount+'</td></tr>');
			}
			$("#net").html(data.net);
			$("#desci").html(data.desci);
			$("#qty").html(data.qty);
			$("#count").html(data.netamount);
			var liststs = data.liststs;			
			for(var i=0;i<liststs.length;i++){
				var sts = liststs[i];
			$("#sts").append('<li><span class="fl" >'+sts.xfTenderdesc+'</span><small style="margin-left: 25%" >'+sts.transMemo+'</small><small class="fr" >'+sts.payamount+'</small><br class="clear"></li>')
			}

	});
}

//分页
function page(cuPage){
	var key = $("#key").val();
	var goPage = $("#goPage").val();
	
	
	if(key == ""){
		window.location.href = "web/xfSummary/getSummary?pageIndex="+cuPage+"&pageSize=10";
	}else{
		window.location.href = "web/xfSummary/getSummary?key="+key+"&pageIndex="+cuPage+"&pageSize=10";
	}
	if(goPage != "" && goPage != null && key == ""){
		window.location.href = "web/xfSummary/getSummary?pageIndex="+goPage+"&pageSize=10";
	}
	if(goPage != "" && goPage != null && key != ""){
		window.location.href = "web/xfSummary/getSummary?key="+key+"&pageIndex="+goPage+"&pageSize=10";
	}
}


</script>

</body>
</html>