/*************************修改*************************/
function edit(id){
	var getid;//已经勾选的id
	$("#updateSale").show().find(".pop.addSale").show(); //修改弹窗弹出
	//得到要修改的信息
	var is = $("#selectedISSRAN2");
	is.empty();
	var isu = $("#selectedUR2");
	isu.empty();
	/**回显**/
	$.get("web/promotion/getPR",{id : id},function(data) {  
		if (data != null) {
			$("#PRID").empty();
			$("#title2").empty();
			$("#detailExplain2").empty();
			$("#number2").empty();
			$("#beginIssueDate2").empty();
			$("#endIssueDate2").empty();
			$("#lowestConsumptionMoney2").empty();
			$("#issueBeginDate2").empty();
			$("#issueEndDate2").empty();
			$("#useBeginDate2").empty();
			$("#useEndDate2").empty();
			$(".issueRanges2").empty();
			$(".issueRanges3").empty();
			$(".useRanges2").empty();
			$(".useRanges3").empty();
			$("#useBeginPeriod2").empty();
			$("#useEndPeriod2").empty();
			$("#offsetMoney2").empty();
			//赋值给修改弹窗
			$("#PRID").val(data.id);
			$("#title2").val(data.title);
			$("#detailExplain2").val(data.detailExplain);
			$("#number2").val(data.number);
			$("#beginIssueDate2").val(data.beginIssueDate);
			$("#endIssueDate2").val(data.endIssueDate);
			$("#issueVipCondition2").val(data.issueVipCondition==true?1:0);
			$("#lowestConsumptionMoney2").val(data.lowestConsumptionMoney);
			$("#issueBeginDate2").val(data.issueBeginDate);
			$("#issueEndDate2").val(data.issueEndDate);
			//issueRanges(data.issueRanges);
			var strSenddata="";
			var strSendIddata="";
			if (data.issueRanges.length > 0) {
			for (var int = 0; int < data.issueRanges.length; int++) {
				strSenddata+=data.issueRanges[int].xfStorecode +":"+ data.issueRanges[int].XfName+"   ";
				strSendIddata+=data.issueRanges[int].xfStorecode+',';
			}
			$(".issueRanges3").val(strSenddata);
			$(".issueRanges2").val(strSendIddata);
			}
			
			$("#useBeginDate2").val(data.useBeginDate);
			$("#useEndDate2").val(data.useEndDate);
			//useRanges(data.useRanges);
			var strUsedata="";
			var strUseIddata="";
			if (data.useRanges.length > 0) {
			for (var int = 0; int < data.useRanges.length; int++) {
			strUsedata+=data.useRanges[int].xfStorecode +":"+ data.useRanges[int].XfName+"   ";
				strUseIddata+=data.useRanges[int].xfStorecode+',';
			}
			$(".useRanges2").val(strUseIddata);
			$(".useRanges3").val(strUsedata);
			}
			$("#useBeginPeriod2").val(data.useBeginPeriod);
			$("#useEndPeriod2").val(data.useEndPeriod);
			$("#offsetMoney2").val(data.offsetMoney);
		}
	 });
}	
	 /**修改--> 发放范围**/
	 $("#issueRanges2").click(function(){
		 $("#editlookSendPop").show().find(".pop.lookSendPop").show();
		 $("#addlookSendPopCancel2").click(function () {
	        $("#editlookSendPop").hide().find(".pop.lookSendPop").hide();
	     });
		 $("#xgffsend_allMall").prop("checked", false);
		$("#xgffsend_allType").prop("checked", false);
		$("#xgfflyselectContainers").val("");
		$("#xgffytselectContainers").val("");
		 sstrUseIddata="";
		 var mtet=$(".issueRanges2").val();
		 var tte=mtet.split(",");
		 for (var int = 0; int < tte.length; int++) {
			 sstrUseIddata+="++"+tte[int]+",";
				}
		 $(".xgffsearchIds").val(sstrUseIddata);
		
		 //楼宇
	 	$.post("web/building/getBuildings",function(data){
	 		$("#building2").empty();
	 		$(data).each(function(index, element) {
	 			var str = "<input onchange='searchBuildB(this,1);' name='buildings2' class='ace ace-checkbox-2' type='checkbox' value="+element.id+"><span class='lbl'>"+element.name+"</span><br>";
	 			$(str).appendTo($("#building2"));
	 		})
	 	});
	 	//业态
	 	$.post("web/format/getFormats",function(data){
	 		$("#format2").empty();
	 		$(data).each(function(index, element) {
	 			var str = "<input onchange='searchBuildB(this,1);' name='formats2' class='ace ace-checkbox-2' type='checkbox' value="+element.id+"><span class='lbl'>"+element.name+"</span><br>";
	 			$(str).appendTo($("#format2"));
	 		})
	 	});
	 	queren2(this,1);
		/* $.get("web/promotion/getStores",function(data) {
			var is = $("#selectedISSRAN2");
			is.empty();
			if (data != null && data.length > 0) {
				$(data).each(function(index, element) {
					var strNAMEXFS = (element.xfStorecode + ":" + element.xfName);
					var bl = true;
					if (bl) {
						var str = "<label><input name='issueRangesss'  class='ace ace-checkbox-2' type='checkbox' value=" + element.xfStorecode + "><span class='lbl'>" + strNAMEXFS + "</span></label></br>";
						bl = false;
						var haveIn=	$(".issueRanges2").val();
						var haveInIds=haveIn.split(",");
						for(var m in haveInIds){
							if(haveInIds[m]===element.xfStorecode){
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
			};
			if (data != null && data.length > 0) {
				var str = "<label><input name='issueRangesssQuanXuan'  class='ace ace-checkbox-2' type='checkbox' ><span class='lbl'>全选</span></label></br>";
				is.prepend($(str));
			}
			$("input[name='issueRangesssQuanXuan']").click(function(){
			    if(this.checked){   
			        $("input[name='issueRangesss']:checkbox").prop("checked", true);  
			    }else{   
			    	 $("input[name='issueRangesss']:checkbox").prop("checked", false); 
			    }   
			});
			//修改窗口发放范围弹窗 的已有数据的填充。。
			var haveIn=	$(".issueRanges2").val();
			var haveInIds=haveIn.split(",");
			$("input[name='issueRangesss']:checkbox").each(function() {
				for (var ids in haveInIds){
					if(haveInIds[ids]==$(this).val()){
						$(this).attr('checked','true');
					}
				};  
			});
		});*/
		 /*关闭修改发放范围弹窗*/
	    $("#editlookSendPop .handle .cancel").click(function () {
	        $("#editlookSendPop").hide().find(".pop").hide();
	    });
	    $("#editlookSendPop").click(function (e) {
	        if(!$(e.target).hasClass("pop") && $(e.target).parents(".pop").length === 0){
	            $(this).hide().find(".pop").hide();
	        }
	    });
	});
	/**修改-->使用范围**/
	$("#useRanges2").click(function(){ 
		$("#editlookUsePop").show().find(".pop.lookUsePop").show();
		$("#addlookUsePopCancel2").click(function () {
	       $("#editlookUsePop").hide().find(".pop.lookUsePop").hide();
	    });
		 $("#xgsysend_allMall").prop("checked", false);
		$("#xgsysend_allType").prop("checked", false);
		$("#xgsylyselectContainers").val("");
		$("#xgsyytselectContainers").val("");
		 sstrUseIddata="";
		 var mtet=$(".useRanges2").val();
		 var tte=mtet.split(",");
		 for (var int = 0; int < tte.length; int++) {
			 sstrUseIddata+="++"+tte[int]+",";
				}
		 $(".xgsysearchIds").val(sstrUseIddata);
		//楼宇
	 	$.post("web/building/getBuildings",function(data){
	 		$("#usebuilding2").empty();
	 		$(data).each(function(index, element) {
	 			var str = "<input onchange='searchBuildB(this,0);' name='buildings2' class='ace ace-checkbox-2' type='checkbox' value="+element.id+"><span class='lbl'>"+element.name+"</span><br>";
	 			$(str).appendTo($("#usebuilding2"));
	 		})
	 	});
	 	//业态
	 	$.post("web/format/getFormats",function(data){
	 		$("#useformat2").empty();
	 		$(data).each(function(index, element) {
	 			var str = "<input onchange='searchBuildB(this,0);' name='formats2' class='ace ace-checkbox-2' type='checkbox' value="+element.id+"><span class='lbl'>"+element.name+"</span><br>";
	 			$(str).appendTo($("#useformat2"));
	 		})
	 	});
	 	queren2(this,0);
		/*$.get("web/promotion/getStores",function(data) {
			var is = $("#selectedUR2");
			is.empty();
			if (data != null && data.length > 0) {
				$(data).each(	function(index, element) {
					var strNAMEXFS = (element.xfStorecode+ ":" + element.xfName);
					var bl = true;
					if (bl) {
						var str = "<label><input name='useRangesss'  class='ace ace-checkbox-2' type='checkbox' value="+ element.xfStorecode+ "><span class='lbl'>"+ strNAMEXFS+ "</span></label></br>";
						bl = false;
						var haveIn=	$(".useRanges2").val();
						var haveInIds=haveIn.split(",");
						for(var m in haveInIds){
							if(haveInIds[m]===element.xfStorecode){
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
			if (data != null && data.length > 0) {
				var str = "<label><input name='useRangesssQuanXuan'  class='ace ace-checkbox-2' type='checkbox' ><span class='lbl'>全选</span></label></br>";
				is.prepend($(str));
			}
			$("input[name='useRangesssQuanXuan']").click(function(){
			    if(this.checked){   
			        $("input[name='useRangesss']:checkbox").prop("checked", true);  
			    }else{   
			    	 $("input[name='useRangesss']:checkbox").prop("checked", false); 
			    }   
			});
			//修改窗口发放范围弹窗 的已有数据的填充。。
			var haveIn=	$(".useRanges2").val();
			var haveInIds=haveIn.split(",");
			$("input[name='useRangesss']:checkbox").each(function() {
				for (var ids in haveInIds){
					if(haveInIds[ids]==$(this).val()){
						$(this).attr('checked','true');
					}
				};  
			});
		});*/
		/*关闭添加发放范围弹窗*/
	    $("#editlookUsePop .handle .cancel").click(function () {
	        $("#editlookUsePop").hide().find(".pop").hide();
	    });
	    $("#editlookUsePop").click(function (e) {
	        if(!$(e.target).hasClass("pop") && $(e.target).parents(".pop").length === 0){
	            $(this).hide().find(".pop").hide();
	        }
	    });
	});
    /*关闭修改页面弹窗*/
    $("#updateSale .handle .cancel").click(function () {
        $("#updateSale").hide().find(".pop").hide();
    });
    $("#updateSale").click(function (e) {
        if(!$(e.target).hasClass("pop") && $(e.target).parents(".pop").length === 0){
            $(this).hide().find(".pop").hide();
        }
    });

//edit 结束
/**根据楼宇和业态搜索**/
function searchBuildB(obj,type){
	//发放范围
	var m="";
	var delm=""
	var strId="";
	if(type == 1){
		/**楼宇**/
		var buildStr="";
		var b = "";
		$("#xgfflyselectContainers").val("");
		var len = $("input[name='buildings2']:checked").length;
		$("input[name='buildings2']:checked").each(function(indIR,elementIR){
			buildStr+=$(elementIR).next().html()+"   ";
			$("#xgfflyselectContainers").val(buildStr);	
			if(indIR===len-1){
				b+=$(elementIR).val();
			}else{
				b+=$(elementIR).val()+",";
			}
		});
		/**业态**/
		var formatStr="";
		var f = "";
		$("#xgffytselectContainers").val("");
		var flen = $("input[name='formats2']:checked").length;
		$("input[name='formats2']:checked").each(function(indIR,elementIR){
			formatStr+=$(elementIR).next().html()+"   ";
			$("#xgffytselectContainers").val(formatStr);
			if(indIR===flen-1){
				f+=$(elementIR).val();
			}else{
				f+=$(elementIR).val()+",";
			}
		});
		
		/**店铺ID**/
		var str = "";
		var havezjffids=$(".xgffsearchIds").val();
		//将已经选择的ids 存放在搜索框下的text中用于判断
		var len = $("input[name='issueRangesss']:checked").length;
		$("input[name='issueRangesss']:checked").each(function(indIR,elementIR) {
			str+=$(elementIR).next().html()+"   ";
			if(indIR===len-1){
				m+="++"+$(elementIR).val()+",";
			}else{
				m+="++"+$(elementIR).val()+",";
			}
		});
		$("input[name='issueRangesss']:checkbox").each(function(indIR,elementIR) {
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
		$(".xgffsearchIds").val(result.join(",")+",");
		var is = $("#selectedISSRAN2");
		is.empty();
		$.post("web/xfStore/getStoreByBF",{buildings:b,formats:f},function(data){
			if (data != null && data.length > 0) {
				$(data).each(function(index, element) {
					var strNAMEXFS = (element.xfStorecode + ":" + element.xfName);
					var bl = true;
					if (bl) {
						var str = "<label><input name='issueRangesss'  class='ace ace-checkbox-2' type='checkbox' value=" + element.xfStorecode + "><span class='lbl'>" + strNAMEXFS + "</span></label></br>";
						bl = false;
						var haveIn=	$(".xgffsearchIds").val();
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
					
				})
			}
			if (data != null && data.length > 0) {
				var str = "<label><input name='issueRangesssQuanXuan'  class='ace ace-checkbox-2' type='checkbox' ><span class='lbl'>全选</span></label></br>";
				is.prepend($(str));
			}
			$("input[name='issueRangesssQuanXuan']").click(function(){
			    if(this.checked){   
			        $("input[name='issueRangesss']:checkbox").prop("checked", true);  
			    }else{   
			    	 $("input[name='issueRangesss']:checkbox").prop("checked", false); 
			    }   
			});
			//店铺选中
			var haveIn=	$(".xgffsearchIds").val();
			var haveInIds=haveIn.split(",");
			$("input[name='issueRangesss']:checkbox").each(function() {
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
		$("#xgsylyselectContainers").val("");
		var len = $("input[name='buildings2']:checked").length;
		$("input[name='buildings2']:checked").each(function(indIR,elementIR){
			buildStr+=$(elementIR).next().html()+"   ";
			$("#xgsylyselectContainers").val(buildStr);	
			if(indIR===len-1){
				b+=$(elementIR).val();
			}else{
				b+=$(elementIR).val()+",";
			}
		});
		//业态
		var formatStr="";
		var f = "";
		$("#xgsyytselectContainers").val("");
		var flen = $("input[name='formats2']:checked").length;
		$("input[name='formats2']:checked").each(function(indIR,elementIR){
			formatStr+=$(elementIR).next().html()+"   ";
			$("#xgsyytselectContainers").val(formatStr);
			if(indIR===flen-1){
				f+=$(elementIR).val();
			}else{
				f+=$(elementIR).val()+",";
			}
		});
		
		//店铺
		var str = "";
		var havezjffids=$(".xgsysearchIds").val();
		var len = $("input[name='useRangesss']:checked").length;
		$("input[name='useRangesss']:checked").each(function(indIR,elementIR) {
			str+=$(elementIR).next().html()+"   ";
			if(indIR===len-1){
				m+="++"+$(elementIR).val()+",";
			}else{
				m+="++"+$(elementIR).val()+",";
			}
		});
		$("input[name='useRangesss']:checkbox").each(function(indIR,elementIR) {
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
		$(".xgsysearchIds").val(result.join(",")+",");
		var is = $("#selectedUR2");
		is.empty();
		$.post("web/xfStore/getStoreByBF",{buildings:b,formats:f},function(data){
			if (data != null && data.length > 0) {
				$(data).each(function(index, element) {
					var strNAMEXFS = (element.xfStorecode + ":" + element.xfName);
					var bl = true;
					if (bl) {
						var str = "<label><input name='useRangesss'  class='ace ace-checkbox-2' type='checkbox' value=" + element.xfStorecode + "><span class='lbl'>" + strNAMEXFS + "</span></label></br>";
						bl = false;
						var haveIn=	$(".xgsysearchIds").val();
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
					
				})
			}
			if (data != null && data.length > 0) {
				var str = "<label><input name='useRangesssQuanXuan'  class='ace ace-checkbox-2' type='checkbox' ><span class='lbl'>全选</span></label></br>";
				is.prepend($(str));
			}
			$("input[name='useRangesssQuanXuan']").click(function(){
			    if(this.checked){   
			        $("input[name='useRangesss']:checkbox").prop("checked", true);  
			    }else{   
			    	 $("input[name='useRangesss']:checkbox").prop("checked", false); 
			    }   
			});
			//店铺选中
			var haveIn=	$(".xgsysearchIds").val();
			var haveInIds=haveIn.split(",");
			$("input[name='useRangesss']:checkbox").each(function() {
				for (var ids in haveInIds){
					if(haveInIds[ids]==("++"+$(this).val())){
						$(this).attr('checked','true');
					}
				};  
			});
			
		});
	}
}

/**修改-->使用范围和发放范围-->input**/
function queren2(obj,type) {
	var str="";
	var m="";
	var strId="";
	if (type == 1) {
		var havezjffids=$(".xgffsearchIds").val();
		//将已经选择的ids 存放在搜索框下的text中用于判断
		var len = $("input[name='issueRangesss']:checked").length;
		$("input[name='issueRangesss']:checked").each(function(indIR,elementIR) {
			str+=$(elementIR).next().html()+"   ";
			if(indIR===len-1){
				m+="++"+$(elementIR).val()+",";
			}else{
				m+="++"+$(elementIR).val()+",";
			}
		});
		$("input[name='issueRangesss']:checkbox").each(function(indIR,elementIR) {
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
         for(var i in strArr){  
        	 if(strArr[i] != tempStr){  
        		 result.push(strArr[i]);  
                 tempStr=strArr[i];  
        	 }else{  
                 continue;  
        	 }  
         } 
		$(".xgffsearchIds").val(result.join(",")+",");
		//清空已有的能选择的复选框
		var is = $("#selectedISSRAN2");
		is.empty();
		//通过条件得到搜索到的商铺
		$.get("web/promotion/getStores",function(data) {
			if (data != null && data.length > 0) {
				$(data).each(function(index, element) {
					var strNAMEXFS = (element.xfStorecode + ":" + element.xfName);
					var bl = true;
					if (bl) {
						var str = "<label><input name='issueRangesss'  class='ace ace-checkbox-2' type='checkbox' value=" + element.xfStorecode + "><span class='lbl'>" + strNAMEXFS + "</span><br></label>";
						bl = false;
						$("input[name='issueRangesss']:checked").each(
							function(indIR,elementIR) {
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
				});
			}
			var haveIn=	$(".xgffsearchIds").val();
			var haveInIds=haveIn.split(",");
			var ready=$("#xgffready");//ca
			ready.empty();
			$("input[name='issueRangesss']:checkbox").each(function() {
				for (var ids in haveInIds){
					if(haveInIds[ids]==("++"+$(this).val())){
						$(this).attr('name','issueRangesssready');
						var q=$(this).parent();
//						q.appendTo(ready);
						ready.prepend(q);
					}
				};  
			});
			searchBuildB(obj,1);
		});	
	} else {
		var havezjffids=$(".xgsysearchIds").val();
		//将已经选择的ids 存放在搜索框下的text中用于判断
		 var len = $("input[name='useRangesss']:checked").length;
		$("input[name='useRangesss']:checked").each(function(indIR,elementIR) {
			str+=$(elementIR).next().html()+"   ";
			if(indIR===len-1){
				m+="++"+$(elementIR).val()+",";
			}else{
				m+="++"+$(elementIR).val()+",";
			}
		});
		$("input[name='useRangesss']:checkbox").each(function(indIR,elementIR) {
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
		$(".xgsysearchIds").val(result.join(",")+",");
		//清空已有的能选择的复选框
		var is = $("#selectedUR2");
		is.empty();
		//通过条件得到搜索到的商铺
		$.get("web/promotion/getStores",function(data) {
			if (data != null && data.length > 0) {
				$(data).each(function(index, element) {
					var strNAMEXFS = (element.xfStorecode + ":" + element.xfName);
					var bl = true;
					if (bl) {
						var str = "<label><input name='useRangesss'  class='ace ace-checkbox-2' type='checkbox' value=" + element.xfStorecode + "><span class='lbl'>" + strNAMEXFS + "</span><br></label>";
						bl = false;
						$("input[name='useRangesss']:checked").each(function(indIR,elementIR) {
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
				});
			}
			var haveIn=	$(".xgsysearchIds").val();
			var haveInIds=haveIn.split(",");
			var ready=$("#xgsyready");//ca
			ready.empty();
			$("input[name='useRangesss']:checkbox").each(function() {
				for (var ids in haveInIds){
					if(haveInIds[ids]==("++"+$(this).val())){
						$(this).attr('name','useRangesssready');
						var q=$(this).parent();
//						q.appendTo(ready);
						ready.prepend(q);
					}
				};  
			});
			searchBuildB(obj,0);
		});	
	}
}


/********************查看发放范围********************/
function showSend(obj,id){
	$("#roleGroup2").empty();
    var selectRoles="<ul>";
    $.post("web/promotion/getPR",{id:id},function(data){
		if (data.issueRanges.length > 0) {
			for (var int = 0; int < data.issueRanges.length; int++) {
				selectRoles+= "<li>"+data.issueRanges[int].xfStorecode+":"+data.issueRanges[int].XfName+"</li>";
			}
			selectRoles+="</ul>"
			$("#roleGroup2").html(selectRoles);
		}
    });
   /*关闭弹窗*/
   $("#lookSendPop .handle .cancel").click(function () {
       $("#lookSendPop").hide().find(".pop").hide();
   });
   $("#lookSendPop").click(function (e) {
       if(!$(e.target).hasClass("pop") && $(e.target).parents(".pop").length === 0){
           $(this).hide().find(".pop").hide();
       }
   });
}

/********************查看使用范围********************/
function showUse(obj,id){
	$("#roleGroup3").empty();
	var selectRoles2="<ul>   ";
	$.post("web/promotion/getPR",{id:id},function(data){
		if (data.useRanges.length > 0) {
			for (var int = 0; int < data.useRanges.length; int++) {
				selectRoles2+= "<li> "+ data.useRanges[int].xfStorecode +":"+ data.useRanges[int].XfName + "  </li>";
			}
			selectRoles2+="   </ul>"
			$("#roleGroup3").html(selectRoles2);
		}
	});
    /*关闭弹窗*/
    $("#lookUsePop .handle .cancel").click(function () {
        $("#lookUsePop").hide().find(".pop").hide();
    });
    $("#lookUsePop").click(function (e) {
        if(!$(e.target).hasClass("pop") && $(e.target).parents(".pop").length === 0){
            $(this).hide().find(".pop").hide();
        }
    });
}

/********************验证并提交修改********************/
$("#editSubmit").click(function(){
	if($("#title2").val() == ""){
		$("#title2").focus();
		$("#title2").val("");
		$("#title2").attr("placeholder", "标题不能为空");
	}else if($("#number2").val() == ""){
		$("#number2").focus();
		$("#number2").val("");
		$("#number2").attr("placeholder", "数量不能为空");
	}else if($("#beginIssueDate2").val() > $("#endIssueDate2").val() || $("#beginIssueDate2").val() =="" || $("#endIssueDate2").val() == ""){
		alert("发放日期有误！");
	}else if($("#issueBeginDate2").val() > $("#issueEndDate2").val() || $("#issueBeginDate2").val() == "" || $("#issueEndDate2").val() == ""){
		alert("发放时间段有误!");
	}else if($("#lowestConsumptionMoney2").val() == ""){
		$("#lowestConsumptionMoney2").focus();
		$("#lowestConsumptionMoney2").val("");
		$("#lowestConsumptionMoney2").attr("placeholder", "最低销售金额不能为空");
	}else if($("#offsetMoney2").val() == ""){
		$("#offsetMoney2").focus();
		$("#offsetMoney2").val("");
		$("#offsetMoney2").attr("placeholder", "优惠金额不能为空");
	}else if(Number($("#lowestConsumptionMoney2").val()) < Number($("#offsetMoney2").val())){
		alert("销售金额不能小于优惠金额！");
	}else if($("#issueRanges2").val == ""){
		alert("请选择发放范围");
	}else if($("#useRanges2").val() == ""){
		alert("请选择使用范围");
	}else if($("#useBeginDate2").val() > $("#useEndDate2").val() || $("#useBeginDate2").val() == "" || $("#useEndDate2").val() ==""){
		alert("使用有效期选择有误！");	
	}else if($("#useBeginPeriod2").val() > $("#useEndPeriod2").val() || $("#useBeginPeriod2").val() == "" || $("#useEndPeriod2").val() == ""){
		alert("使用时段选择有误！")
	}else{
		$("#signupForm2").submit();
	}
});

/**查询**/
$("#submitSearchForm").click(function() {
	$("#searchForm").submit();
});

/**判断标题是否重复**/
function isEditRepeat(){
	var title = $("#title2").val();
	$.post("web/promotion/isRepeat",{title:title},function (data){
		if(data){
			$("#title2").focus();
			$("#title2").val("");
			$("#title2").attr("placeholder", "标题重复，请重新输入");
			$("#title2").val("");
		}
	});
}

function querenadd2(obj,type){
	if(type==1){
		//添加
		str="";
		m="";
		strId="";
		//将已有的
		$(".issueRanges3").empty();
		$("#issueRanges2").val("");	
		$(".issueRanges2").val("");
		 var len = $("input[name='issueRangesssready']:checkbox").length;
		$("input[name='issueRangesssready']:checkbox").each(function(indIR,elementIR) {
			str+=$(elementIR).next().html()+"   ";
			$("#issueRanges2").val(str);	
				if(indIR===len-1){
					m+=$(elementIR).val();
				}else{m+=$(elementIR).val()+",";}
			$(".issueRanges2").val(m);
		});
	}
	else{
		//添加
		str="";
		m="";
		strId="";
		//将已有的
		$(".useRanges3").empty();
		$("#useRanges2").val("");	
		$(".useRanges2").val("");
		 var len = $("input[name='useRangesssready']:checkbox").length;
		$("input[name='useRangesssready']:checkbox").each(function(indIR,elementIR) {
			if(indIR===len-1){
				m+=$(elementIR).val();
			}else{m+=$(elementIR).val()+",";}
			str+=$(elementIR).next().html()+"   ";
			$("#useRanges2").val(str);	
			$(".useRanges2").val(m);
		});
	}
}



//修改发放

$("#xgfflyselectContainer").click(function(){
	  $("#xgfflyselect_ul").toggle();
});
$("#xgffytselectContainer").click(function(){
	  $("#xgffytselect_ul").toggle();
});
$("#xgffsend_allMall").change(function(){
	 if(this.checked){   
	        $("input[name='buildings2']:checkbox").prop("checked", true);  
	    }else{   
	    	 $("input[name='buildings2']:checkbox").prop("checked", false);
	    }   
	 searchBuildB(this,1);
});
$("#xgffsend_allType").change(function(){
	 if(this.checked){   
	        $("input[name='formats2']:checkbox").prop("checked", true);  
	    }else{   
	    	 $("input[name='formats2']:checkbox").prop("checked", false);
	    }  
	 searchBuildB(this,1);
});
$("#xgffqc").click(function(){
	 var xgffsearchIds=$(".xgffsearchIds").val();
	 $("input[name='issueRangesssready']:checked").each(function(indIR,elementIR) {
			var delm="++"+($(this).val())+",";
			xgffsearchIds=xgffsearchIds.replace(delm,'');//删除取消项
			var delids=$(this).val();
		$(this).parent().remove();
		 $("input[name='issueRangesss']:checked").each(function(indIR,elementIR) {
			 if(delids===($(this).val())){
				 $(this).prop("checked", false);
			 }
			 });
		});
	 $(".xgffsearchIds").val(xgffsearchIds);
})
$("#xgffqcall").click(function(){
	var mterer=$("#xgffready");
	mterer.empty();
	 $(".xgffsearchIds").val("");
		 $("input[name='issueRangesss']:checked").each(function(indIR,elementIR) {
		$(this).prop("checked", false);
		});
})

//修改使用

$("#xgsylyselectContainer").click(function(){
	  $("#xgsylyselect_ul").toggle();
});
$("#xgsyytselectContainer").click(function(){
	  $("#xgsyytselect_ul").toggle();
});
$("#xgsysend_allMall").change(function(){
	 if(this.checked){   
	        $("input[name='buildings2']:checkbox").prop("checked", true);  
	    }else{   
	    	 $("input[name='buildings2']:checkbox").prop("checked", false);
	    }   
	 searchBuildB(this,0);
});
$("#xgsysend_allType").change(function(){
	 if(this.checked){   
	        $("input[name='formats2']:checkbox").prop("checked", true);  
	    }else{   
	    	 $("input[name='formats2']:checkbox").prop("checked", false);
	    }  
	 searchBuildB(this,0);
});
$("#xgsyqc").click(function(){
	var xgsysearchIds=$(".xgsysearchIds").val();
	 $("input[name='useRangesssready']:checked").each(function(indIR,elementIR) {
		var delm="++"+($(this).val())+",";
		xgsysearchIds=xgsysearchIds.replace(delm,'');//删除取消项
		$(this).parent().remove();
		var delids=$(this).val();
		 $("input[name='useRangesss']:checked").each(function(indIR,elementIR) {
			 if(delids===($(this).val())){
				 $(this).prop("checked", false);
			 }
			 });
	 });
	 $(".xgsysearchIds").val(xgsysearchIds);
})
$("#xgsyqcall").click(function(){
	 $(".xgsysearchIds").val("");
	var mterer=$("#xgsyready");
	mterer.empty();
	 $("input[name='useRangesss']:checked").each(function(indIR,elementIR) {
	$(this).prop("checked", false);
	});
})










