<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="shortcut icon" type="image/x-icon" href="resource/ico/favicon.ico" media="screen">
<link rel="stylesheet" href="new/css/common.css">
<link rel="stylesheet" href="new/css/tableComm.css">
<link rel="stylesheet" href="new/css/mall.css">
</head>
<body>
<div id="all">
    <div id="mall">
        <%@ include file="public/menu.jsp"%>
        <div class="right">
    	<%@ include file="public/header.jsp"%>
            <div class="middle">
                <div class="map">
                    <span class="icon position"><i></i></span>
                    <span class="mapName">商场</span>
                </div>
                <!--除以下content内容，其余地方均为模板，不要改动-->
                <div class="content">
                    <div class="container_top">
                    	<!-- 商场信息 -->
                        <div class="item mallInfo">
                            <div class="title"><span>商场信息</span><small class="btn update">修改</small></div>
                            <div><span>商场编号</span><input type="text" name="xfMallid" id="xfMallid" readonly value="${xfMall.xfMallid}"></div>
                            <div><span>商场名称</span><input type="text" name="xfMallname" id="xfMallname" readonly value="${xfMall.xfMallname}"></div>
                            <div class="handle">
                                <span class="btn confirm" id="editSubmit">确认</span>
                                <span class="btn cancel"><a style="text-decoration:none" href="web/xfmall/getXfMall">取消</a></span>
                            </div>
                        </div>
                        <!-- 店铺基本信息 -->
                        <div class="item storeInfo">
                            <div class="title"><span>店铺基本信息</span><small class='store1'></small></div>
                            <div id="mallInfo" style="width: 100%;height: 214px;"></div>
                        </div>
                        <!-- 员工人数统计 -->
                        <div class="item staff">
                            <div class="title"><span>员工人数统计</span><small class='staffnum'></small>
                            <input type="hidden" class='numberStaff' ></div>
                            <div id="storeStaff"><span>店铺员工</span><small><i title="" class="storeStaff"></i></small></div>
                            <div id="storeWaiter"><span>店铺营业员</span><small><i title="" class="storeWaiter"></i></small></div>
                            <div id="storeManage"><span>店铺管理员</span><small><i title="" class="storeManage"></i></small></div>
                            <div id="mallManage"><span>商场管理员</span><small><i title="" class="mallManage"></i></small></div>
                        </div>
                    </div>
                    <div class="container_bottom">
                    	<!-- 销售金额统计 -->
                        <div class="item saleTotal">
                            <div class="title"><span>销售金额统计</span><small>(单位:元)</small></div>
                            <div id="saleTotal" style="width: 100%;height: 356px;"></div>
                        </div>
                        <!-- 各类支付方式总金额 -->
                        <div class="item typesTotal">
                            <div class="title">
                                <span>各类支付方式总金额</span><small>(单位:元)</small>
                                <div class="select_container">
                                    <div class="text">
                                        <div class="selectContainer">最近一周</div>
                                    </div>
                                </div>
                            </div>
                            <div id="typesTotal" style="width: 100%;height: 356px;margin-top: 50px;"></div>
                        </div>
                        
                    </div>
                </div>
            </div>
            <%@ include file="public/footer.jsp"%>
        </div>
    </div>
</div>
<script>
    $(function () {
    	//商铺的修改与确定
    	$("#editSubmit").click(function(){
			$.post("web/xfmall/addMall",{xfMallid:$("#xfMallid").val(),xfMallname:$("#xfMallname").val()},function(data){
				if(data){
					alert("修改成功");
					window.location.reload();
				}else{
					alert("修改失败");
				}
			});
    	});
    	
        $(".item.mallInfo .title .update").click(function () {
        	if($("#xfMallid").val() == "" || $("#xfMallid").val() == null){
		        $(this).parent().next().find("input").removeAttr("readonly").css({"background-color":"white"});
        	}
            $(this).parent().next().next().find("input").removeAttr("readonly").css({"background-color":"white"});
            $(this).parent().siblings(".handle").show();
        });
        
        
        $.post("web/xfmall/getStores", function(data){
           	var echartDom1 =  echarts.init($("#mallInfo")[0]);
			var storenum=data.noname+data.name;
			
			$(".store1").text("（店铺数量大约"+storenum+"家）");
           	var option1 = {
           		  tooltip : { 
           	        trigger: 'item', 
           	        formatter: "{b} : {c} ({d}%)" 
           	    }, 
                    legend:{
                        show:true,
                        data:["中央店铺","非中央店铺"],
                        itemWidth:15,
                        left:"center",
                        top:"bottom"
                    },
                    series:{
                        type:"pie",
                        radius:["20%","65%"],
                        data:[
                            {
                                name:'中央店铺',
                                //传入中央商铺的数量
                                value:data.name,
                                itemStyle:{
                                    normal:{
                                        color:"rgb(163,225,212)",
                                    
                                    }
                                }
                            },
                            {
                                name:'非中央店铺',
                              //传入非中央商铺的数量
                                value:data.noname,
                                itemStyle:{
                                    normal:{
                                        color:"rgb(156,195,218)",
                                        	
                                    }
                                }
                            }
                        ],
                        label:{
                            normal: {
                               show:false
                            }
                        }
                    }
                };
            echartDom1.setOption(option1);
          });
        //图标
       
     //销售金额统计
        var arr = new Array();
        var arrmoney=new Array();
        $.post("web/xfmall/getDateData", function(data){
        	var m=0;
        for(var i in data){
        	arr[m]=i;
        	arrmoney[m]=data[i];
        	m++;
        
        }
        
        
        var echartDom2 =  echarts.init($("#saleTotal")[0]);
        var  option2 = {
                xAxis:{
                    type:"category",
                    boundaryGap: false,
                    name:"日期",
                    min:"dataMin",
                    data:arr
                },
                yAxis:{
                    name:"金额(元)"
                },
                tooltip:{},
                series:{
                    type:"line",
                    data:arrmoney,
                    symbolSize:10,
                    itemStyle:{
                        normal:{
                            color:"rgb(163,225,212)"
                        }
                    },
                    areaStyle:{
                        normal:{
                            color:"rgb(163,225,212)"
                        }
                    }
                }
        };
        echartDom2.setOption(option2);
        });
        //用来存销售额和销售方式名称
        var getmoney = new Array();
        var getmt=new Array();
       
        $.post("web/xfmall/getTender", function(data){
        	var moneynum=0;
        	var mtnum=0;
        	for(var i in data){
        		var m=data[i];
        		for(var q in m){
        			if(q=='money'){
        			getmoney[moneynum]=m[q];
        			moneynum++;	}
        			if(q=='xfTenderdesc'){getmt[mtnum]=m[q];mtnum++;}
        		}
        	var jsonm="";
        	
        	jsonm+="[";
        	jsonm+=""
        	var colocon=0;
        		 for(var num in getmt){
        			 if(colocon==0){ jsonm+="{name:'"+getmt[num]+"',value:"+getmoney[num]+",itemStyle:{normal:{color:'rgb(163,225,212)'}}},";colocon=1;}
        			 else{
        				 jsonm+="{name:'"+getmt[num]+"',value:"+getmoney[num]+",itemStyle:{normal:{color:'rgb(156,195,218)'}}},";colocon=0; 
        			 }
        		 }
        	jsonm+="]";
        	var json=eval(jsonm);
        		 
            echartDom3 =  echarts.init($("#typesTotal")[0]);
            option3 = {
            		
                xAxis:{
                    type:"category",
                    data:getmt
                },
                tooltip:{},
                yAxis:{},
                series:{
                    type:"bar",
                    barGap:100,
                    barMaxWidth:30,
                    label: {
                        normal: {
                            show: true,
                            position: 'top'
                        }
                    },
                    data:json
                }
            };

            
             echartDom3.setOption(option3);
        	
        	}
        });
        //操作员工数量
        var storenum=0;
         $.post("web/xfmall/getStaffNum", function(data){
        	 storenum=data;
 			$(".staffnum").text("（员工总数"+storenum+"人）");
 			$(".numberStaff").val(storenum);
         });
         
         //得到员工三种身份的百分比
         var getStaffff= new Array();
         var getStaffnum= new Array();
         var getscale=new Array();
         var staffff=0;
         var staffnum=0;
         var scale=0;
         $.post("web/xfmall/getStaffScale", function(data){
        	  for(var i in data){
          		var m=data[i];
          		for(var q in m){
          			if(q=='role'){
          				getStaffff[staffff]=m[q];
          				staffff++;	}
          			if(q=='number'){getStaffnum[staffnum]=m[q];staffnum++;}
          			if(q=='scale'){getscale[scale]=m[q];scale++;}
          		}
        	  }
        	  var numberStaff=$(".numberStaff").val();
        	  
	        for(var i in getStaffff){
	        	//店铺员工
		    	 if(getStaffff[i]==='ROLE_STORE_USER'){
		    		 $("#storeStaff i").css("width",getscale[i]+"%");
		    		 $("#storeStaff i").attr("title",getscale[i]+"%,"+getStaffnum[i]+"");
		    	 }	
	        	//店铺营业员
		    	 if(getStaffff[i]==='ROLE_STORE_SHOP_ASSISTANT'){
		    		 $("#storeWaiter i").css("width",getscale[i]+"%");
		    		 $("#storeWaiter i").attr("title",getscale[i]+"%,"+getStaffnum[i]+"");
		    	 }	
	        	//店铺管理员
		    	 if(getStaffff[i]==='ROLE_STORE_ADMIN'){
		    		 $("#storeManage i").css("width",getscale[i]+"%");
		    		 $("#storeManage i").attr("title",getscale[i]+"%,"+getStaffnum[i]+"");
		    	 }
	        	//商场管理员
		       	 if(getStaffff[i]==='ROLE_MALL_ADMIN'){
		       		 $("#mallManage i").css("width",getscale[i]+"%");
		       		 $("#mallManage i").attr("title",getscale[i]+"%,"+getStaffnum[i]+"");
		       	 }
	        }
       })
    
    })
</script>
</body>
</html>