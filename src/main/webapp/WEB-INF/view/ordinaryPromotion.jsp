<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<link rel="stylesheet" href="new/css/sale.css">
<link rel="stylesheet" type="text/css" href="resource/My97DatePicker/skin/default/datepicker.css" />
<script src="resource/My97DatePicker/WdatePicker.js"></script>

</head>
<body>
<div id="all">
    <div id="sale">
        <div class="left">
        <%@ include file="public/menu.jsp"%>
        </div>
        <div class="right">
        <%@ include file="public/header.jsp"%>
            <div class="middle">
                <div class="map">
                    <span class="icon position"><i></i></span>
                    <span class="mapName">促销 > 普通顾客促销券设置</span>
                </div>
                <!--除以下content内容，其余地方均为模板，不要改动-->
                <div class="content">
                    <div class="title">
                       	<!-- 搜索 -->
                    	<form class="form-search" action="web/promotion/getOrdinaryPromotion" method="post" id="searchForm">
	                        <div class="search">
	                            <input type="text" value="${requestScope.key==null?null:requestScope.key}" id="key" name="key" placeholder="标题">
	                            <span class="searchBtn icon" id="submitSearchForm"><i></i></span>
	                        </div>
	                    </form>
                        <div class="btn add">添加</div>
                    </div>
                    <div class="tableContainer">
                        <table cellpadding="0" cellspacing="0">
                             <thead>
                            <tr>
                                <th>序号</th>
                                <th>标题</th>
                                <th>说明</th>
                                <th>发放数量</th>
                                <th>发放日期</th>
                                <th>是否只发放给会员</th>
                                <th>最低消费金额</th>
                                <th>优惠金额</th>
                                <th>发放时间段</th>
                                <th>发放范围</th>
                                <th>使用有效期</th>
                                <th>使用范围</th>
                                <th>使用有效时段</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="pr" items="${paging.data}" varStatus="status">
							<tr>
                                <td>${paging.startRowNum+(status.index)}</td>
                                <td>${pr.title }</td>
                                <td>${pr.detailExplain }</td>
                                <td>${pr.number }</td>
                                <td><fmt:formatDate value="${pr.beginIssueDate }" pattern="yyyy.MM.dd" />-<fmt:formatDate value="${pr.endIssueDate }" pattern="yyyy.MM.dd" /></td>
                                <td>${pr.issueVipCondition==true?"是":"否"}</td>
                                <td><fmt:formatNumber value="${pr.lowestConsumptionMoney}" pattern="0.00" /></td>
                                <td><fmt:formatNumber value="${pr.offsetMoney}" pattern="0.00" /></td>
                                <td>${pr.issueBeginDate }-${pr.issueEndDate }</td>
                                <td><span class="btn lookSend" onclick="showSend(this,${pr.id});" >查看</span></td>
                                <td><fmt:formatDate value="${pr.useBeginDate }" pattern="yyyy.MM.dd" />-<fmt:formatDate value="${pr.useEndDate }" pattern="yyyy.MM.dd" /></td>
                                <td><span class="btn lookUse" onclick="showUse(this,${pr.id});">查看</span></td>
                                <td>${pr.useBeginPeriod }-${pr.useEndPeriod}</td>
                                <c:if test="${pr.isEdit == 0}">
                                	<td><span class="btn update " onclick="edit(${pr.id});">修改</span></td>
                                </c:if>
                                <c:if test="${pr.isEdit == 1}">
                                 <td><span class="btn update unable">修改</span></td>
                                </c:if>
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
 
<!--查看发放范围弹窗 -->    
    <div class="shadow" id="lookSendPop">
        <div class="pop look lookSendPop">
            <h4 class="title">查看发放范围</h4>
            <div class="container">
                <div class="storeList" id="roleGroup2">
                </div>
            </div>
            <div class="handle">
                <span class="btn confirm" id='lookSendPopCancel'>确定</span>
                <span class="btn cancel">取消</span>
            </div>
	</div>
	</div>
<!-- 使用范围弹窗弹窗 -->
         <div class="shadow" id="lookUsePop">
        	<div class="pop look lookUsePop">
            <h4 class="title">查看使用范围</h4>
          		<div class="container">
                <div class="storeList" id="roleGroup3">
                </div>
            	</div>
            	<div class="handle">
                <span class="btn confirm" id="lookUsePopCancel">确定</span>
                <span class="btn cancel">取消</span>
            	</div>
        	</div>
         </div>
<!-- 添加弹窗 -->
		<form enctype="multipart/form-data" action="web/promotion/addOr"  id="signupForm1" class="form-horizontal"  method="post">
       		<div class="shadow" id="addSale">
       		<div class="pop addSale">
            	<h4 class="title">添加促销规则</h4>
            	<div class="overflow">
            	<div class="container">
                <div class="formList">
                    <p><span>标题</span><input type="text" name="title" id="title" onblur="isAddOrRepeat();"></p>
                    <p><span>数量</span><input  type="text" name="number" id="number"></p>
                    <p><span>说明</span><input type="text" name="detailExplain" id="detailExplain"  ></p>
                    <p class="small">
                    	<span>发放日期</span>
                    	<input type="date" name="beginIssueDate" id="beginIssueDate" onClick="WdatePicker()" >
                    	<span style="margin: 0 6px;">到</span>
                    	<input type="date"  name="endIssueDate" id="endIssueDate" onClick="WdatePicker()">
                    </p>
                    <p class="small">
						<span>发放时间段</span>
						<input type="text" id="issueBeginDate" name="issueBeginDate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" class="Wdate" readonly="readonly" />
						 <span style="margin: 0 6px;">到</span>
						<input type="text" id="issueEndDate" name="issueEndDate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" class="Wdate" readonly="readonly" />
                    </p>
                    <p><span>最低消费金额</span><input type="text" name="lowestConsumptionMoney" id="lowestConsumptionMoney"></p>
                    <p><span>优惠金额</span><input type="text" name="offsetMoney" id="offsetMoney"></p>
                    <p><span>发放范围</span>
                    	<input type="text" id="issueRanges" class="issueRanges" readonly="ture">
                    	<input type="hidden" name="issueRanges" class="issueRanges1"></p>
                    <p>
                    	<span >使用范围</span>
                    	<input type="text" id="useRanges" class="useRanges" readonly="ture" > 
                        <input type="hidden" name="useRanges" class="useRanges1">
                    </p>
                    <p class="small">
                        <span>使用有效期</span><input type="date" name="useBeginDate" id="useBeginDate" onClick="WdatePicker()" readonly="readonly" >
                        <span style="margin: 0 8px;">到</span><input type="date" id="useEndDate"  onClick="WdatePicker()" name="useEndDate" readonly="readonly">
                    </p>
                    <p class="small ">
                        <span>使用时段</span>
                        <input type="text" name="useBeginPeriod" id="useBeginPeriod" 	onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" class="Wdate" readonly="readonly">
                        <span style="margin: 0 8px;">到</span>
                        <input type="text" name="useEndPeriod" id="useEndPeriod" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" class="Wdate" readonly="readonly">
                    </p>
                </div>
           	    </div>
           	<div class="handle">
               <span class="btn confirm" id="addSubmit">确定</span>
               <span class="btn cancel" id="addCancel">取消</span>
           	</div>
        	</div>
        		</div>
        	</div>
        </form>
        
        
        <!--添加  发放范围弹窗   -->  
	<div class="shadow" id="addlookSendPop">
	    <div class="pop look lookSendPop" >
	        <h4 class="title">增加发放范围</h4>
	        <div class="container">
	         <div class="leftBar">
	        <div class="select_container">
                <input type="hidden" class='zjffsearchIds'>
		        <label style="float: center;">楼宇: </label>
		        <div class="select"  id="zjfflyselectContainer"><div class="selectContainer"  ></div><input type="text" readonly="readonly" id="zjfflyselectContainers"></div>
		         <div class="formList select_ul"  id="zjfflyselect_ul" style="display: none" >
                            <ul>
                                <li>
                                    <input id="zjffsend_allMall" type="checkbox"><label for="send_allMall">所有楼宇</label>
                                    <ul>
                                        <li>
                                            <div id="building" style="float: left"></div>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                </div>
		       	</div>
		       	<div class="select_container">
	            <label style="float: left">业态:</label>
		        <div class="select" id="zjffytselectContainer"><div class="selectContainer"  ></div><input type="text" readonly="readonly" id="zjffytselectContainers"> </div>
		        <div class="formList select_ul" id="zjffytselect_ul" style="display: none">
                            <ul>
                                <li>
                                   <input id="zjffsend_allType" type="checkbox"><label for="send_allType">所有业态</label>
                                    <ul>
                                        <li>
                                           <div id="format" style="float: left;"></div>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                </li>
                            </ul>
                        </div>
          </div>
	       <div class="storeList" >
	               <div id='selectedISSRAN'></div>
	       </div>
	        <div class="addToStore" onclick='queren(this,1);'>+添加到店铺</div>
          </div>
	         <div class="rightBar">
                    <h3>店铺</h3>
                     <div class="storeListt" >
                    <ul > <li id="zjffready"></li></ul>
                     </div>
                    <div class="delete" id="zjffqc">清除</div>
                    <div class="delete deleteAll" id="zjffqcall">清除所有</div>
                </div>
                 </div>
	        <div class="handle">
	            <span class="btn confirm"  onclick='querenadd(this,1)' id='addlookSendPopCancel' >确定</span>
	               <span class="btn cancel">取消</span>
	           </div>
		</div>
	</div>
<!-- 添加   使用范围弹窗弹窗 -->
	<div class="shadow" id="addlookUsePop">
	<div class="pop look lookUsePop">
	    <h4 class="title">增加使用范围</h4>
	    <div class="container">
	    <div class="leftBar">
	        <div class="select_container">
                <input type="hidden" class='zjsysearchIds'>
		        <label style="float: center;">楼宇: </label>
		        <div class="select"  id="zjsylyselectContainer"><div class="selectContainer"  ></div><input type="text" readonly="readonly" id="zjsylyselectContainers"></div>
		         <div class="formList select_ul"  id="zjsylyselect_ul" style="display: none" >
                            <ul>
                                <li>
                                    <input id="zjsysend_allMall" type="checkbox"><label for="send_allMall">所有楼宇</label>
                                    <ul>
                                        <li>
                                          <div id="usebuilding" style="float: left;"></div><br>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                </div>
		       	</div>
		       	<div class="select_container">
	            <label style="float: left">业态:</label>
		        <div class="select" id="zjsyytselectContainer"><div class="selectContainer"  ></div><input type="text" readonly="readonly" id="zjsyytselectContainers"> </div>
		        <div class="formList select_ul" id="zjsyytselect_ul" style="display: none">
                            <ul>
                                <li>
                                   <input id="zjsysend_allType" type="checkbox"><label for="send_allType">所有业态</label>
                                    <ul>
                                        <li>
                                            <div id="useformat" style="float: left;"></div>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                </li>
                            </ul>
                        </div>
          </div>
           
	      <div class="storeList" >
         	 <div id='selectedUR'></div>
        	</div>
	        <div class="addToStore" onclick='queren(this,0);'>+添加到店铺</div>
          </div>
           <div class="rightBar">
                    <h3>店铺</h3>
                     <div class="storeListt" >
                    <ul > <li id="zjsyready"></li></ul>
                     </div>
                    <div class="delete" id="zjsyqc">清除</div>
                    <div class="delete deleteAll" id="zjsyqcall">清除所有</div>
                </div>
	    </div>
	    <div class="handle">
	        <span class="btn confirm" onclick='querenadd(this,0);' id="addlookUsePopCancel">确定</span>
	        <span class="btn cancel">取消</span>
	    </div>
	</div>
	</div> 

	<!-- 修改弹窗 -->
		<form enctype="multipart/form-data" action="web/promotion/updateOr"  id="signupForm2" class="form-horizontal"  method="post">
       		<div class="shadow" id="updateSale">
       		<div class="pop addSale">
            	<h4 class="title">修改促销规则</h4>
            	<div class="overflow">
            	<div class="container">
                <div class="formList">
                    <p><span>标题</span>
                    <input type="hidden" name="PRID" id="PRID"> 
                    <input type="text" name="title" id="title2" onblur="isEditOrRepeat();">
                    </p>
                    <p><span>数量</span><input  type="text" name="number" id="number2"></p>
                    <p><span>说明</span><input type="text" name="detailExplain" id="detailExplain2"  ></p>
                    <p class="small">
                    	<span>发放日期</span>
                    	<input type="date" name="beginIssueDate" id="beginIssueDate2" onClick="WdatePicker()" >
                    	<span style="margin: 0 6px;">到</span>
                        <input type="date"  name="endIssueDate" id="endIssueDate2" onClick="WdatePicker()">
                    </p>
                    <p class="small">
						<span>发放时间段</span>
						<input type="text" id="issueBeginDate2" name="issueBeginDate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" class="Wdate" readonly="readonly" />
						<span style="margin: 0 6px;">到</span>
						<input type="text" id="issueEndDate2" name="issueEndDate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" class="Wdate" readonly="readonly" />
                    </p>
                    <p><span>最低消费金额</span><input type="text" name="lowestConsumptionMoney" id="lowestConsumptionMoney2"></p>
                    <p><span>优惠金额</span><input type="text" name="offsetMoney" id="offsetMoney2"></p>
                    <p>
                    	<span>发放范围</span>
                    	<input type="text" id="issueRanges2" class="issueRanges3" readonly="ture" >
                    	<input type="hidden" name="issueRanges" class="issueRanges2" >
                    </p>
                    <p>
                    	<span >使用范围</span>
                    	<input type="text" id="useRanges2" class="useRanges3" readonly="ture" > 
                        <input type="hidden" name="useRanges" class="useRanges2"></p>
                    <p class="small">
                        <span>使用有效期</span>
                        <input type="date" name="useBeginDate" id="useBeginDate2" onClick="WdatePicker()" readonly="readonly" >
                        <span style="margin: 0 8px;">到</span>
                        <input type="date" id="useEndDate2"  onClick="WdatePicker()" name="useEndDate" readonly="readonly"></p>
                    <p class="small ">
                        <span>使用时段</span>
                        <input type="text" name="useBeginPeriod" id="useBeginPeriod2" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" class="Wdate" readonly="readonly">
                        <span style="margin: 0 8px;">到</span>
                        <input type="text" name="useEndPeriod" id="useEndPeriod2" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" class="Wdate" readonly="readonly">
                    </p>
                </div>
           	    </div>
           	<div class="handle">
               <span class="btn confirm" id="editSubmit">确定</span>
               <span class="btn cancel" id="editCancel">取消</span>
           	</div>
           	</div>
        	</div>
        	</div>
        </form>
	<!--修改发放范围弹窗    -->  
	<div class="shadow" id="editlookSendPop">
	    <div class="pop look lookSendPop" >
	        <h4 class="title">修改发放范围</h4>
	        <div class="container">
	       <div class="leftBar">
	        <div class="select_container">
               <input type="hidden" class='xgffsearchIds'>
		        <label style="float: center;">楼宇: </label>
		        <div class="select"  id="xgfflyselectContainer"><div class="selectContainer"  ></div><input type="text" readonly="readonly" id="xgfflyselectContainers"></div>
		         <div class="formList select_ul"  id="xgfflyselect_ul" style="display: none" >
                            <ul>
                                <li>
                                    <input id="xgffsend_allMall" type="checkbox"><label for="send_allMall">所有楼宇</label>
                                    <ul>
                                        <li>
                                            <div id="building2" style="float: left;"> </div>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                </div>
		       	</div>
		       	<div class="select_container">
	            <label style="float: left">业态:</label>
		        <div class="select" id="xgffytselectContainer"><div class="selectContainer"  ></div><input type="text" readonly="readonly" id="xgffytselectContainers"> </div>
		        <div class="formList select_ul" id="xgffytselect_ul" style="display: none">
                            <ul>
                                <li>
                                   <input id="xgffsend_allType" type="checkbox"><label for="send_allType">所有业态</label>
                                    <ul>
                                        <li>
                                              <div id="format2" style="float: left;"> </div>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                </li>
                            </ul>
                        </div>
          </div>
	       <div class="storeList" >
	               <div id='selectedISSRAN2'></div>
	        </div>
	        <div class="addToStore" onclick='queren2(this,1);'>+添加到店铺</div>
          </div>
	         <div class="rightBar">
                    <h3>店铺</h3>
                     <div class="storeListt" >
                    <ul > <li id="xgffready"></li></ul>
                     </div>
                    <div class="delete" id="xgffqc">清除</div>
                    <div class="delete deleteAll" id="xgffqcall">清除所有</div>
                </div>
                </div>
	        <div class="handle">
	            <span class="btn confirm"  onclick='querenadd2(this,1);' id='addlookSendPopCancel2' >确定</span>
	            <span class="btn cancel">取消</span>
	           </div>
		
	</div>
	</div>
<!-- 修改使用范围弹窗弹窗 -->
	<div class="shadow" id="editlookUsePop">
	<div class="pop look lookUsePop">
	    <h4 class="title">修改使用范围</h4>
	    <div class="container">
	    <div class="leftBar">
	        <div class="select_container">
               <input type="hidden" class='xgsysearchIds'>
		        <label style="float: center;">楼宇: </label>
		        <div class="select"  id="xgsylyselectContainer"><div class="selectContainer"  ></div><input type="text" readonly="readonly" id="xgsylyselectContainers"></div>
		         <div class="formList select_ul"  id="xgsylyselect_ul" style="display: none" >
                            <ul>
                                <li>
                                    <input id="xgsysend_allMall" type="checkbox"><label for="send_allMall">所有楼宇</label>
                                    <ul>
                                        <li>
                                          <div id="usebuilding2" style="float: left;"> </div><br>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                </div>
		       	</div>
		       	<div class="select_container">
	            <label style="float: left">业态:</label>
		        <div class="select" id="xgsyytselectContainer"><div class="selectContainer"  ></div><input type="text" readonly="readonly" id="xgsyytselectContainers"> </div>
		        <div class="formList select_ul" id="xgsyytselect_ul" style="display: none">
                            <ul>
                                <li>
                                   <input id="xgsysend_allType" type="checkbox"><label for="send_allType">所有业态</label>
                                    <ul>
                                        <li>
                                               <div id="useformat2" style="float: left;"> </div>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                </li>
                            </ul>
                        </div>
          </div>
	   <div class="storeList" >
	         	 <div id='selectedUR2'></div>
	        </div>
	        <div class="addToStore" onclick='queren2(this,0);'>+添加到店铺</div>
          </div>
	         <div class="rightBar">
                    <h3>店铺</h3>
                     <div class="storeListt" >
                    <ul > <li id="xgsyready"></li></ul>
                     </div>
                    <div class="delete" id="xgsyqc">清除</div>
                    <div class="delete deleteAll" id="xgsyqcall">清除所有</div>
                </div>
	        
	    </div>
	    <div class="handle">
	        <span class="btn confirm" onclick='querenadd2(this,0);' id="addlookUsePopCancel2">确定</span>
	        <span class="btn cancel">取消</span>
	    </div>
	</div>
	</div> 
</div>
<script type="text/javascript">
$(function (){
	/*添加规则*/
	 $(".content .title .btn.add").click(function () {
	     $("#addSale").show().find(".pop.addSale").show();
	 });
	//查看发放范围
	  $(".right .middle .content .tableContainer td span.btn.lookSend").click(function () {
	        $("#lookSendPop").show().find(".pop.lookSendPop").show();
	    });
	    $("#lookSendPopCancel").click(function () {
	        $("#lookSendPop").hide().find(".pop.lookSendPop").hide();
	    });
   //查看使用范围
   $(".right .middle .content .tableContainer td span.btn.lookUse").click(function () {
       $("#lookUsePop").show().find(".pop.lookUsePop").show();
   });
 $("#lookUsePopCancel").click(function () {
       $("#lookUsePop").hide().find(".pop.lookUsePop").hide();
   });
});
  //分页
 function page(cuPage){
 	var key = $("#key").val();
 	var goPage = $("#goPage").val();
 	if(key == ""){
 		window.location.href = "web/promotion/getOrdinaryPromotion?pageIndex="+cuPage+"&pageSize=10";
 	}else{
 		window.location.href = "web/promotion/getOrdinaryPromotion?key="+key+"&pageIndex="+cuPage+"&pageSize=10";
 	}
 	if(goPage != "" && goPage != null && key == ""){
 		window.location.href = "web/promotion/getOrdinaryPromotion?pageIndex="+goPage+"&pageSize=10";
 	}
 	if(goPage != "" && goPage != null && key != ""){
 		window.location.href = "web/promotion/getOrdinaryPromotion?key="+key+"&pageIndex="+goPage+"&pageSize=10";
 	}
 }
</script>
<script src="new/js/addOrdinaryPromotion.js"></script>
<script src="new/js/editOrdinaryPromotion.js"></script>
</body>
</html>