/**发放范围**/
$("#issueRanges").click(function(){
	//显示和隐藏发放范围
 	$("#addlookSendPop").show().find(".pop.lookSendPop").show();
	$("#addlookSendPopCancel").click(function () {
	 	$("#addlookSendPop").hide().find(".pop.lookSendPop").hide();
	});	
	$("#zjffsend_allMall").prop("checked", false);
	$("#zjffsend_allType").prop("checked", false);
	$("#zjfflyselectContainers").val("");
	$("#zjffytselectContainers").val("");
	//楼宇
 	$.post("web/building/getBuildings",function(data){
 		$("#building").empty();
 		$(data).each(function(index, element) {
 			var str = "<input onchange='searchBuildA(this,1);' name='buildings' class='ace ace-checkbox-2' type='checkbox' value="+element.id+"><span class='lbl'>"+element.name+"</span><br>";
 			$(str).appendTo($("#building"));
 		})
 	});
 	//业态
 	$.post("web/format/getFormats",function(data){
 		$("#format").empty();
 		$(data).each(function(index, element) {
 			var str = "<input onchange='searchBuildA(this,1);' name='formats' class='ace ace-checkbox-2' type='checkbox' value="+element.id+"><span class='lbl'>"+element.name+"</span><br>";
 			$(str).appendTo($("#format"));
 		})
 	});
 	//店铺
	/*$.get("web/promotion/getStores",function(data) {
		var is = $("#selectedISSRAN");
		is.empty();
		if (data != null && data.length > 0) {
			$(data).each(function(index, element) {
				var strNAMEXFS = (element.xfStorecode + ":" + element.xfName);
				var bl = true;
				if (bl) {
					var str = "<label><input name='issueRangess' class='ace ace-checkbox-2' type='checkbox' value="+element.xfStorecode+"><span class='lbl'>"+strNAMEXFS+"</span></label></br>";
					bl = false;
					var haveIn=	$(".issueRanges1").val();
					var haveInIds=haveIn.split(",");
					for(var m in haveInIds){
						if(haveInIds[m]===element.xfStorecode){
							bl=true;
						}
					}
					*//**勾选中的排前面**//*
					if (bl) {
						is.prepend($(str))
					} else {
						$(str).appendTo(is);
					}
				}
			});
		}
		if (data != null && data.length > 0) {
			var str = "<label><input name='issueRangessQuanXuan'  class='ace ace-checkbox-2' type='checkbox' ><span class='lbl'>全选</span></label></br>";
			is.prepend($(str));
		}
		$("input[name='issueRangessQuanXuan']").click(function(){
		    if(this.checked){   
		        $("input[name='issueRangess']:checkbox").prop("checked", true);  
		    }else{   
		    	 $("input[name='issueRangess']:checkbox").prop("checked", false); 
		    }   
		});
		*//**选中**//*
		var haveIn=	$(".issueRanges1").val();
		var haveInIds=haveIn.split(",");
		$("input[name='issueRangess']:checkbox").each(function() {
			for (var ids in haveInIds){
				if(haveInIds[ids]==$(this).val()){
					$(this).attr('checked','true');
				}
			};  
		});
	});*/
 	
	/**关闭添加发放范围弹窗**/
	$("#addlookSendPop .handle .cancel").click(function () {
		$("#addlookSendPop").hide().find(".pop").hide();
	});
	/**空白面点击也会导致添加页面隐藏**/
	$("#addlookSendPop").click(function (e) {
		if(!$(e.target).hasClass("pop") && $(e.target).parents(".pop").length === 0){
			$(this).hide().find(".pop").hide();
		}
	});
});

 
/**使用范围**/
$("#useRanges").click(function(){ 
	$("#addlookUsePop").show().find(".pop.lookUsePop").show();
	$("#addlookUsePopCancel").click(function () {
       $("#addlookUsePop").hide().find(".pop.lookUsePop").hide();
   });
	$("#zjsysend_allMall").prop("checked", false);
	$("#zjsysend_allType").prop("checked", false);
	$("#zjsylyselectContainers").val("");
	$("#zjsyytselectContainers").val("");
	//获取楼宇
 	$.post("web/building/getBuildings",function(data){
 		$("#usebuilding").empty();
 		$(data).each(function(index, element) {
 			var str = "<input onchange='searchBuildA(this,0);' name='buildings' class='ace ace-checkbox-2' type='checkbox' value="+element.id+"><span class='lbl'>"+element.name+"</span><br>";
 			$(str).appendTo($("#usebuilding"));
 		})
 	});
 	//获取业态
 	$.post("web/format/getFormats",function(data){
 		$("#useformat").empty();
 		$(data).each(function(index, element) {
 			var str = "<input onchange='searchBuildA(this,0);' name='formats' class='ace ace-checkbox-2' type='checkbox' value="+element.id+"><span class='lbl'>"+element.name+"</span><br>";
 			$(str).appendTo($("#useformat"));
 		})
 	});
	
	var is = $("#selectedUR");
	is.empty();
	/*$.get("web/promotion/getStores",function(data) {
		var is = $("#selectedUR");
		is.empty();
		if (data != null && data.length > 0) {
			$(data).each(function(index, element) {
				var strNAMEXFS = (element.xfStorecode+ ":" + element.xfName);
				var bl = true;
				if (bl) {
					var str = "<label><input name='useRangess' class='ace ace-checkbox-2' type='checkbox' value="+element.xfStorecode+"><span class='lbl'>"+strNAMEXFS+"</span></label></br>";
					bl = false;
					var haveIn=	$(".useRanges1").val();
					var haveInIds=haveIn.split(",");
					for(var m in haveInIds){
						if(haveInIds[m]===element.xfStorecode){
							bl=true;
						}
					}
					if (bl) {
						is.prepend($(str))
					} else {
						$(str).appendTo(is);
					}
				}
			});
		}
		if (data != null && data.length > 0) {
			var str = "<label><input name='useRangessQuanXuan'  class='ace ace-checkbox-2' type='checkbox' ><span class='lbl'>全选</span></label></br>";
		is.prepend($(str));}
		$("input[name='useRangessQuanXuan']").click(function(){
			if(this.checked){   
				$("input[name='useRangess']:checkbox").prop("checked", true);  
			}else{   
				 $("input[name='useRangess']:checkbox").prop("checked", false); 
			}   
		});
		//选中
		var haveIn=	$(".zjsysearchIds").val();
		var haveInIds=haveIn.split(",");
		$("input[name='useRangess']:checkbox").each(function() {
			for (var ids in haveInIds){
				if(haveInIds[ids]==$(this).val()){
					$(this).attr('checked','true');
				}
			};  
		});
	});*/
	 /**关闭添加发放范围弹窗**/
    $("#addlookUsePop .handle .cancel").click(function () {
        $("#addlookUsePop").hide().find(".pop").hide();
    });
    $("#addlookUsePop").click(function (e) {
        if(!$(e.target).hasClass("pop") && $(e.target).parents(".pop").length === 0){
            $(this).hide().find(".pop").hide();
        }
    });
})

/**根据楼宇和业态搜索**/
function searchBuildA(obj,type){
	//发放范围
	var m="";
	var delm=""
	var strId="";
	if(type == 1){
		/**楼宇**/
		var buildStr="";
		var b = "";
		$("#zjfflyselectContainers").val("");
		var len = $("input[name='buildings']:checked").length;
		$("input[name='buildings']:checked").each(function(indIR,elementIR){
			buildStr+=$(elementIR).next().html()+"   ";
			$("#zjfflyselectContainers").val(buildStr);	
			if(indIR===len-1){
				b+=$(elementIR).val();
			}else{
				b+=$(elementIR).val()+",";
			}
		});
		/**业态**/
		var formatStr="";
		var f = "";
		$("#zjffytselectContainers").val("");
		var flen = $("input[name='formats']:checked").length;
		$("input[name='formats']:checked").each(function(indIR,elementIR){
			formatStr+=$(elementIR).next().html()+"   ";
			$("#zjffytselectContainers").val(formatStr);
			if(indIR===flen-1){
				f+=$(elementIR).val();
			}else{
				f+=$(elementIR).val()+",";
			}
		});
		
		/**店铺ID**/
		var str = "";
		var havezjffids=$(".zjffsearchIds").val();
		//将已经选择的ids 存放在搜索框下的text中用于判断
		var len = $("input[name='issueRangess']:checked").length;
		$("input[name='issueRangess']:checked").each(function(indIR,elementIR) {
			str+=$(elementIR).next().html()+"   ";
			if(indIR===len-1){
				m+="++"+$(elementIR).val()+",";
			}else{
				m+="++"+$(elementIR).val()+",";
			}
		});
		//产生bug的所在  字符串替换造成替换错误
		$("input[name='issueRangess']:checkbox").each(function(indIR,elementIR) {
			var check = $(this).not(":checked");
			if(check){
				delm="++"+$(elementIR).val()+",";
				havezjffids=havezjffids.replace(delm,'');//删除取消项
			}
		});
		if (havezjffids !== null || havezjffids !== undefined || havezjffids !== '') {
			m+=","+havezjffids; 
		} 
		//字符串去重复
        var strArr=m.split(",");//把字符串分割成一个数组  
        strArr.sort();//排序  
        var result=new Array();//创建出一个结果数组  
        var tempStr="";  
        for(var i in strArr){  
            if(strArr[i] != tempStr){  
                 result.push(strArr[i]);  
                 tempStr=strArr[i];  
            }else {  
                 continue;  
            }  
        } 
        var asd=result.join(",")+",";
		$(".zjffsearchIds").val(asd);
		var is = $("#selectedISSRAN");
		is.empty();
		$.post("web/xfStore/getStoreByBF",{buildings:b,formats:f},function(data){
			if (data != null && data.length > 0) {
				$(data).each(function(index, element) {
					var strNAMEXFS = (element.xfStorecode + ":" + element.xfName);
					var bl = true;
					if (bl) {
						var str = "<label><input name='issueRangess' class='ace ace-checkbox-2' type='checkbox' value="+element.xfStorecode+"><span class='lbl'>"+strNAMEXFS+"</span></label></br>";
						bl = false;
						var haveIn=	$(".zjffsearchIds").val();
						var haveInIds=haveIn.split(",");
						for(var m in haveInIds){
							if(haveInIds[m]===("++"+element.xfStorecode)){
								bl=true;
							}
						}
						$("input[name='issueRangess']:checked").each(function(indIR,elementIR) {
							if ($(elementIR).next().html() == strNAMEXFS) {
								bl = true;
								return;
							}
						});
						if (bl) {
							is.prepend($(str));
						} else {
							$(str).appendTo(is);
						}
					}
					
				})
			}
			if (data != null && data.length > 0) {
				var str = "<label><input name='issueRangessQuanXuan'  class='ace ace-checkbox-2' type='checkbox' ><span class='lbl'>全选</span></label></br>";
				is.prepend($(str));
			}
			$("input[name='issueRangessQuanXuan']").click(function(){
			    if(this.checked){   
			        $("input[name='issueRangess']:checkbox").prop("checked", true);  
			    }else{   
			    	 $("input[name='issueRangess']:checkbox").prop("checked", false); 
			    }   
			});
			//店铺选中
			var haveIn=	$(".zjffsearchIds").val();
			var haveInIds=haveIn.split(",");
			$("input[name='issueRangess']:checkbox").each(function() {
				for (var ids in haveInIds){
					if(haveInIds[ids]==("++"+$(this).val())){
						$(this).attr('checked','true');
					}
				};  
			});
			
		});
	}
	/**使用范围**/
	else{  
		var m="";
		var delm=""
		var strId="";
		//楼宇
		var buildStr="";
		var b = "";
		$("#zjsylyselectContainers").val("");
		var len = $("input[name='buildings']:checked").length;
		$("input[name='buildings']:checked").each(function(indIR,elementIR){
			buildStr+=$(elementIR).next().html()+"   ";
			$("#zjsylyselectContainers").val(buildStr);	
			if(indIR===len-1){
				b+=$(elementIR).val();
			}else{
				b+=$(elementIR).val()+",";
			}
		});
		/**业态**/
		var formatStr="";
		var f = "";
		$("#zjsyytselectContainers").val("");
		var flen = $("input[name='formats']:checked").length;
		$("input[name='formats']:checked").each(function(indIR,elementIR){
			formatStr+=$(elementIR).next().html()+"   ";
			$("#zjsyytselectContainers").val(formatStr);
			if(indIR===flen-1){
				f+=$(elementIR).val();
			}else{
				f+=$(elementIR).val()+",";
			}
		});
		
		//店铺
		var str = "";
		var havezjffids=$(".zjsysearchIds").val();
		var len = $("input[name='useRangess']:checked").length;
		$("input[name='useRangess']:checked").each(function(indIR,elementIR) {
			str+=$(elementIR).next().html()+"   ";
			if(indIR===len-1){
				m+="++"+$(elementIR).val()+",";
			}else{
				m+="++"+$(elementIR).val()+",";
			}
		});
		$("input[name='useRangess']:checkbox").each(function(indIR,elementIR) {
			var check = $(this).is(":checked");
			if(!check){
				delm="++"+$(elementIR).val()+",";
				havezjffids=havezjffids.replace(delm,'');//删除取消项
			}
		});
		
		if (havezjffids !== null || havezjffids !== undefined || havezjffids !== '') {
			m+=","+havezjffids; 
		} 
		//字符串去重复
        var strArr=m.split(",");//把字符串分割成一个数组  
        strArr.sort();//排序  
        var result=new Array();//创建出一个结果数组  
        var tempStr="";  
        for(var i in strArr){  
            if(strArr[i] != tempStr){  
                 result.push(strArr[i]);  
                 tempStr=strArr[i];  
            }else {  
                 continue;  
            }  
        } 
		$(".zjsysearchIds").val(result.join(",")+",");
		var is = $("#selectedUR");
		is.empty();
		$.post("web/xfStore/getStoreByBF",{buildings:b,formats:f},function(data){
			if (data != null && data.length > 0) {
				$(data).each(function(index, element) {
//					var strNAMEXFS = (element.xfStorecode+ ":" + element.xfName);
//					str += "<label><input name='useRangess' class='ace ace-checkbox-2' type='checkbox' value="+element.xfStorecode+"><span class='lbl'>" +strNAMEXFS+"</span></label></br>";
//					$("#selectedUR").html(str);
					var strNAMEXFS = (element.xfStorecode + ":" + element.xfName);
					var bl = true;
					if (bl) {
						var str = "<label><input name='useRangess'  class='ace ace-checkbox-2' type='checkbox' value=" + element.xfStorecode + "><span class='lbl'>" + strNAMEXFS + "</span><br></label>";
						bl = false;
						var haveIn=	$(".zjsysearchIds").val();
						var haveInIds=haveIn.split(",");
						for(var m in haveInIds){
							if(haveInIds[m]===("++"+element.xfStorecode)){
								bl=true;
							}
						}
						$("input[name='useRangess']:checked").each(function(indIR,elementIR) {
							if ($(elementIR).next().html() == strNAMEXFS) {
								bl = true;
								return;
							}
						});
						if (bl) {
							is.prepend($(str));
						} else {
							$(str).appendTo(is);
						}
					}
					
				})
			}
			if (data != null && data.length > 0) {
				var str = "<label><input name='useRangessQuanXuan'  class='ace ace-checkbox-2' type='checkbox' ><span class='lbl'>全选</span><br></label>";
				is.prepend($(str));
			}
			$("input[name='useRangessQuanXuan']").click(function(){
			    if(this.checked){   
			        $("input[name='useRangess']:checkbox").prop("checked", true);  
			    }else{   
			    	 $("input[name='useRangess']:checkbox").prop("checked", false); 
			    }   
			});
			//店铺选中
			var haveIn=	$(".zjsysearchIds").val();
			var haveInIds=haveIn.split(",");
			$("input[name='useRangess']:checkbox").each(function() {
				for (var ids in haveInIds){
					if(haveInIds[ids]=="++"+$(this).val()){
						$(this).attr('checked','true');
					}
				};  
			});
			
		});
	}
}

/**添加-->使用范围和发放范围-->input**/
function queren(obj,type) {
	var str="";
	var m="";
	var strId="";
	var delm="";
	if (type == 1) {
		var havezjffids=$(".zjffsearchIds").val();
		//将已经选择的ids 存放在搜索框下的text中用于判断
		var len = $("input[name='issueRangess']:checked").length;
		$("input[name='issueRangess']:checked").each(function(indIR,elementIR) {
			str+=$(elementIR).next().html()+"   ";
			if(indIR===len-1){
				m+="++"+$(elementIR).val()+",";
			}else{m+="++"+$(elementIR).val()+",";}
		});
		$("input[name='issueRangess']:checkbox").each(function(indIR,elementIR) {
			var check = $(this).is(":checked");
			if(!check){
				delm="++"+$(elementIR).val()+",";
				havezjffids=havezjffids.replace(delm,'');//删除取消项
			}
		});
		if (havezjffids !== null || havezjffids !== undefined || havezjffids !== '') {
			m+=","+havezjffids; 
		} 
		//字符串去重复
       var strArr=m.split(",");//把字符串分割成一个数组  
       strArr.sort();//排序  
       var result=new Array();//创建出一个结果数组  
       var tempStr="";  
       for(var i in strArr){  
            if(strArr[i] != tempStr){  
               result.push(strArr[i]);  
               tempStr=strArr[i];  
            }else{  
                 continue;  
            }  
       } 
		$(".zjffsearchIds").val(result.join(",")+",");
		//清空已有的能选择的复选框
		var is = $("#selectedISSRAN");
		is.empty();
		//通过条件得到搜索到的商铺
		$.get("web/promotion/getStores",function(data) {
			if (data != null && data.length > 0) {
				$(data).each(function(index, element) {
					var strNAMEXFS = (element.xfStorecode + ":" + element.xfName);
					var bl = true;
					if (bl) {
						var str = "<label><input name='issueRangess'  class='ace ace-checkbox-2' type='checkbox' value=" + element.xfStorecode + "><span class='lbl'>" + strNAMEXFS + "</span></br></label>";
						bl = false;
						var haveIn=	$(".zjffsearchIds").val();
						var haveInIds=haveIn.split(",");
						for(var m in haveInIds){
							if(haveInIds[m]===("++"+element.xfStorecode)){
								bl=true;
							}
						}
						if (bl) {
							is.prepend($(str))
						} else {
							$(str).appendTo(is);
						}
					}
				});
			}
			/**选中**/
			var haveIn=	$(".zjffsearchIds").val();
			var haveInIds=haveIn.split(",");
			var ready=$("#zjffready");//ca
			ready.empty();
			$("input[name='issueRangess']:checkbox").each(function() {
				for (var ids in haveInIds){
					if(haveInIds[ids]==("++"+$(this).val())){
						$(this).attr('name','issueRangessready');
						var q=$(this).parent();
//						q.appendTo(ready);
						ready.prepend(q);
					}
				};  
			});
			searchBuildA(obj,1);
		});	
		
	} else {
		var havezjffids=$(".zjsysearchIds").val();
		//将已经选择的ids 存放在搜索框下的text中用于判断
		 var len = $("input[name='useRangess']:checked").length;
		$("input[name='useRangess']:checked").each(function(indIR,elementIR) {
			str+=$(elementIR).next().html()+"   ";
			if(indIR===len-1){
				m+="++"+$(elementIR).val()+",";
			}else{m+="++"+$(elementIR).val()+",";}
		});
		$("input[name='useRangess']:checkbox").each(function(indIR,elementIR) {
			var check = $(this).is(":checked");
			if(!check){
			delm="++"+$(elementIR).val()+",";
			//删除取消项
			havezjffids=havezjffids.replace(delm,'');
			}
		});
		if (havezjffids !== null || havezjffids !== undefined || havezjffids !== '') {
			m+=","+havezjffids; 
		} 
		//字符串去重复
       var strArr=m.split(",");//把字符串分割成一个数组  
       strArr.sort();//排序  
       var result=new Array();//创建出一个结果数组  
       var tempStr="";  
       for(var i in strArr) {  
           if(strArr[i] != tempStr) {  
              result.push(strArr[i]);  
              tempStr=strArr[i];  
           }  else {  
                continue;  
           }  
       } 
		$(".zjsysearchIds").val(result.join(",")+",");
		//清空已有的能选择的复选框
		var is = $("#selectedUR");
		is.empty();
		//通过条件得到搜索到的商铺
		$.get("web/promotion/getStores",function(data) {
			var c = "";
			if (data != null && data.length > 0) {
				var charXfStore = $("#addISSRAN").children("label");
				var issueRanges = $("#issueRanges").children("label");
				$(data).each(function(index, element) {
					var strNAMEXFS = (element.xfStorecode + ":" + element.xfName);
					var bl = true;
					$(charXfStore).each(function(ind,ele) {
						if (ele.innerText == strNAMEXFS) {
							bl = false;
						}
					});
					if (bl) {
						var str = "<label><input name='useRangess'  class='ace ace-checkbox-2' type='checkbox' value=" + element.xfStorecode + "><span class='lbl'>" + strNAMEXFS + "</span><br></label>";
						bl = false;
						var haveIn=	$(".zjsysearchIds").val();
						var haveInIds=haveIn.split(",");
						for(var m in haveInIds){
							if(haveInIds[m]===("++"+element.xfStorecode)){
								bl=true;
							}
						}
						if (bl) {
							is.prepend($(str));
						} else {
							$(str).appendTo(is);
						}
					}
				});
			}
			var haveIn=	$(".zjsysearchIds").val();
			var haveInIds=haveIn.split(",");
			var ready=$("#zjsyready");//ca
			ready.empty();
			$("input[name='useRangess']:checkbox").each(function() {
				for (var ids in haveInIds){
					if(haveInIds[ids]==("++"+$(this).val())){
						$(this).attr('name','useRangessready');
						var q=$(this).parent();
//						q.appendTo(ready);
						ready.prepend(q);
					}
				}; 
			});
			searchBuildA(obj,0);
		});	
	}
}

/**关闭添加页面**/
$("#addSale .handle .cancel").click(function () {
	$("#selectedISSRAN").empty();
	$(".zjffsearch").val("");
	$("#selectedUR").empty();
    $("#addSale").hide().find(".pop").hide();
});

/**提交并验证**/
$("#addSubmit").click(function(){
	if($("#title").val() == ""){
		$("#title").focus();
		$("#title").val("");
		$("#title").attr("placeholder", "标题不能为空");
	}else if($("#number").val() == ""){
		$("#number").focus();
		$("#number").val("");
		$("#number").attr("placeholder", "数量不能为空");
	}else if($("#beginIssueDate").val() > $("#endIssueDate").val() || $("#beginIssueDate").val() =="" || $("#endIssueDate").val() == ""){
		alert("发放日期有误！");
	}else if($("#issueBeginDate").val() > $("#issueEndDate").val() || $("#issueBeginDate").val() == "" || $("#issueEndDate").val() == ""){
		alert("发放时间段有误!");
	}else if($("#lowestConsumptionMoney").val() == ""){
		$("#lowestConsumptionMoney").focus();
		$("#lowestConsumptionMoney").val("");
		$("#lowestConsumptionMoney").attr("placeholder", "最低销售金额不能为空");
	}else if($("#offsetMoney").val() == ""){
		$("#offsetMoney").focus();
		$("#offsetMoney").val("");
		$("#offsetMoney").attr("placeholder", "优惠金额不能为空");
	}else if(Number($("#lowestConsumptionMoney").val()) < Number($("#offsetMoney").val())){
		alert("销售金额不能小于优惠金额！");
	}else if($("#issueRanges").val == ""){
		alert("请选择发放范围");
	}else if($("#useRanges").val() == ""){
		alert("请选择使用范围");
	}else if($("#useBeginDate").val() > $("#useEndDate").val() || $("#useBeginDate").val() == "" || $("#useEndDate").val() ==""){
		alert("使用有效期选择有误！");	
	}else if($("#useBeginPeriod").val() > $("#useEndPeriod").val() || $("#useBeginPeriod").val() == "" || $("#useEndPeriod").val() == ""){
		alert("使用时段选择有误！")
	}else{
    	$("#signupForm1").submit();
	}
});
function querenadd(obj,type) {
	if(type==1){
		//添加
		str="";
		m="";
		strId="";
		//将已有的
		$(".issueRanges").empty();
		$("#issueRanges").val("");	
		$(".issueRanges1").val("");
		len = $("input[name='issueRangessready']:checkbox").length;
		$("input[name='issueRangessready']:checkbox").each(function(indIR,elementIR) {
			str+=$(elementIR).next().html()+"   ";
			$("#issueRanges").val(str);	
			if(indIR===len-1){
				m+=$(elementIR).val();
			}else{m+=$(elementIR).val()+",";}
			$(".issueRanges1").val(m);
		});
	}else{
		//添加
		str="";
		m="";
		strId="";
		//将已有的
		$(".useRanges").empty();
		$("#useRanges").val("");	
		$(".useRanges1").val("");
		 var len = $("input[name='useRangessready']:checkbox").length;
		$("input[name='useRangessready']:checkbox").each(function(indIR,elementIR) {
			if(indIR===len-1){
				m+=$(elementIR).val();
			}else{
				m+=$(elementIR).val()+",";
			}
			str+=$(elementIR).next().html()+"   ";
			$("#useRanges").val(str);	
			$(".useRanges1").val(m);
		});
	}
}

/**判断标题是否重复**/
function isAddRepeat(){
	var title = $("#title").val();
	$.post("web/promotion/isRepeat",{title:title},function (data){
		if(data){
			$("#title").focus();
			$("#title").val("");
			$("#title").attr("placeholder", "标题重复，请重新输入");
			$("#title").val("");
		}
	});
}
//增加发放

$("#zjfflyselectContainer").click(function(){
	  $("#zjfflyselect_ul").toggle();
});
$("#zjffytselectContainer").click(function(){
	  $("#zjffytselect_ul").toggle();
});

$("#zjffsend_allMall").change(function(){
	 if(this.checked){   
	        $("input[name='buildings']:checkbox").prop("checked", true);  
	    }else{   
	    	 $("input[name='buildings']:checkbox").prop("checked", false);
	    }   
	 searchBuildA(this,1);
});
$("#zjffsend_allType").change(function(){
	 if(this.checked){   
	        $("input[name='formats']:checkbox").prop("checked", true);  
	    }else{   
	    	 $("input[name='formats']:checkbox").prop("checked", false);
	    }  
	 searchBuildA(this,1);
});
$("#zjffqc").click(function(){
	 var zjffsearchIds=$(".zjffsearchIds").val();
	 $("input[name='issueRangessready']:checked").each(function(indIR,elementIR) {
			var delm="++"+($(this).val())+",";
			zjffsearchIds=zjffsearchIds.replace(delm,'');//删除取消项
			var delids=$(this).val();
		$(this).parent().remove();
		 $("input[name='issueRangess']:checked").each(function(indIR,elementIR) {
			 if(delids===($(this).val())){
				 $(this).prop("checked", false);
			 }
			 });
		});
	 $(".zjffsearchIds").val(zjffsearchIds);
})
$("#zjffqcall").click(function(){
	var mterer=$("#zjffready");
	mterer.empty();
	 $(".zjffsearchIds").val("");
		 $("input[name='issueRangess']:checked").each(function(indIR,elementIR) {
		$(this).prop("checked", false);
		});

})

//增加使用

 $("#zjsylyselectContainer").click(function(){
	  $("#zjsylyselect_ul").toggle();
 });
 $("#zjsyytselectContainer").click(function(){
	  $("#zjsyytselect_ul").toggle();
 });
$("#zjsysend_allMall").change(function(){
	 if(this.checked){   
	        $("input[name='buildings']:checkbox").prop("checked", true);  
	    }else{   
	    	 $("input[name='buildings']:checkbox").prop("checked", false);
	    }   
	 searchBuildA(this,0);
});
 $("#zjsysend_allType").change(function(){
	 if(this.checked){   
	        $("input[name='formats']:checkbox").prop("checked", true);  
	    }else{   
	    	 $("input[name='formats']:checkbox").prop("checked", false);
	    }  
	 searchBuildA(this,0);
});
 $("#zjsyqc").click(function(){
	 var zjsysearchIds=$(".zjsysearchIds").val();
	 $("input[name='useRangessready']:checked").each(function(indIR,elementIR) {
			var delm="++"+($(this).val())+",";
			zjsysearchIds=zjsysearchIds.replace(delm,'');//删除取消项
			var delids=$(this).val();
		$(this).parent().remove();
		 $("input[name='useRangess']:checked").each(function(indIR,elementIR) {
			 if(delids===($(this).val())){
				 $(this).prop("checked", false);
			 }
			 });
		});
	 $(".zjsysearchIds").val(zjsysearchIds);
 })
 $("#zjsyqcall").click(function(){
	var mterer=$("#zjsyready");
	mterer.empty();
	 $(".zjsysearchIds").val("");
		 $("input[name='useRangess']:checked").each(function(indIR,elementIR) {
		$(this).prop("checked", false);
		});
 })









