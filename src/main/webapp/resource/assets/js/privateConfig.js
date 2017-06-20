/**
 * 
 */
 var id;
var ckv61tillid;
var cktillid;
function addConfig(){
	$("#tillidText").hide();
	$("#Vtillid").hide();
	$("#pos1").hide();
	$("#pos2").hide();
	$("#pos3").hide();
	$("#pos4").hide();
	$("#pos5").hide();
	$(".selectStoreTr").show();
	$("#signupForm1")[0].reset();
	$("#selectStore").empty();
	var role = '${sessionScope.XfStaff.staffRole.authority}';
	if ("ROLE_STORE_ADMIN" == role) {
		$(
				"<option value='"
						+ '${sessionScope.XfStaff.xfIssuestore.xfStorecode}'
						+ "' selected>本店</option>").appendTo("#selectStore");
	} else {
		$.get("web/xfStaff/getStores",
				function(data) {
			        if (data.length > 0) {
						for (var int = 0; int < data.length; int++) {
							if (int == 0) {
								$(
										"<option selected value='"
												+ data[int].xfStorecode + "'>"
												+ data[int].xfName
												+ "</option>").appendTo(
										"#selectStore");
							} else {
								$(
										"<option value='"
												+ data[int].xfStorecode + "'>"
												+ data[int].xfName
												+ "</option>").appendTo(
										"#selectStore");
							}
						}
					}
				});
	}
	$('#dialog1').dialog('open');
}
//删除
function del(obj, event) {
	var btnNum = event.button;
	if (btnNum == 2) {
		if (confirm("确定删除吗？")) {
			var val = $(obj).find(".staffCode").html();
			$.post("web/deleteposConfigByTillid", {
				tillid : val
			}, function(data) {
				if (data) {
					$(obj).remove();
				} else {
					alert("删除失败！");
				}
			});
		}
	}

}
//选择店铺显示收银机信息
function selectStoreChange(obj){
	$.ajax({
		url:'web/getStoreTill?storeid='+obj.value,
		method:'post',
		type:'json',
		success:function(data){
			var data=JSON.parse(data);
			if (data.length > 0) {
				if($("#selectTillid").is(":hidden")){
					$("#selectTillid").show();
				};
				if($("#tillidText").is(":hidden")){
					
				}else{
					$("#tillidText").hide();
				};
				if($("#Vtillid").is(":hidden")){
					
				}else{
					$("#Vtillid").hide();
				}
				$("#selectTillValue").empty();
				if(data.length==1){
					$(
							"<option selected value='"
									+ data[0].tillid + "'>"
									+ data[0].tillid
									+ "</option>").appendTo(
							"#selectTillValue");
					$("#Vtillid").show();
					$("#v61tillid").val(data[0].v61tillid);
					$("#pos1").show();
					$("#pos2").show();
					$("#pos3").show();
					$("#pos4").show();
					$("#pos5").show();
					if(data[0].posid!=null){
						$("#posid").val(data[0].posid);
						$("#mchtId").val(data[0].mchtId);
						$("#termId").val(data[0].termId);
						$("#authSN").val(data[0].authSN);
						$("#outlinepay").val(data[0].outlinepay);
					}
				}else{
				for (var int = 0; int < data.length; int++) {
					if (int == 0) {
						$(
								"<option selected value='"
										+ data[int].tillid + "'>"
										+ data[int].tillid
										+ "</option>").appendTo(
								"#selectTillValue");
						$("#Vtillid").show();
						$("#v61tillid").val(data[0].v61tillid);
						$("#pos1").show();
						$("#pos2").show();
						$("#pos3").show();
						$("#pos4").show();
						$("#pos5").show();
						if(data[0].posid!=null){
							$("#posid").val(data[0].posid);
							$("#mchtId").val(data[0].mchtId);
							$("#termId").val(data[0].termId);
							$("#authSN").val(data[0].authSN);
							$("#outlinepay").val(data[0].outlinepay);
						}
					} else {
						$(
								"<option value='"
										+ data[int].tillid + "'>"
										+ data[int].tillid
										+ "</option>").appendTo(
								"#selectTillValue");
					}
				}
				}
			}
    //店铺下没有收银机
	else{
		if($("#selectTillid").is(":hidden")){
			$("#tillidText").show();
			$("#Vtillid").show();
			$("#v61tillid").val('');
			$("#posid").val('');
			$("#mchtId").val('');
			$("#termId").val('');
			$("#authSN").val('')
			$("#outlinepay").val('');
			$("#pos1").show();
			$("#pos2").show();
			$("#pos3").show();
			$("#pos4").show();
			$("#pos5").show(); 
		}else{
			$("#tillidText").show();
			$("#Vtillid").show();
			$("#v61tillid").val('');
			$("#selectTillid").hide();
			$("#posid").val('');
			$("#mchtId").val('');
			$("#termId").val('');
			$("#authSN").val('');
			$("#outlinepay").val('');
		    $("#pos1").show();
			$("#pos2").show();
			$("#pos3").show();
			$("#pos4").show();
			$("#pos5").show(); 
		};
		
		
		//显示收银机编号和V61收银机编号输入框
		$("#selectTillid").hide();
		$("#tillidText").show();
		$("#Vtillid").show();
		bootbox.alert('该店铺没有收银机，请先添加收银机');
	} 
		}
	});
}
//点击选择收银机
function selectTillChange(obj){
	$.ajax({
			url:'web/getTillInfo?tillid='+obj.value,
			method:'post',
			async: false,
			success : function(data){
				var data=JSON.parse(data);
				//获取选中的id做增加或修改判断
				id=data.id;
				cktillid=data.tillid;
				ckv61tillid=data.v61tillid;
				$("#Vtillid").show();
				$("#v61tillid").val(data.v61tillid);
				$("#pos1").show();
				$("#pos2").show();
				$("#pos3").show();
				$("#pos4").show();
				$("#pos5").show();
				$("#posid").val(data.posid);
				$("#mchtId").val(data.mchtId);
				$("#termId").val(data.termId);
				$("#authSN").val(data.authSN);
				$("#outlinepay").val(data.outlinepay);
				//当收银机配置了pos机私有配置时在页面展示可修改
				 if(data.posid!=null&&data.mchid!=null&&data.termid!=null&&data.authSN!=null){
					$("#pos1").show();
					$("#pos2").show();
					$("#pos3").show();
					$("#pos4").show();
					$("#pos5").show();
					$("#posid").val(data.posid);
					$("#mchtId").val(data.mchtId);
					$("#termId").val(data.termId);
					$("#authSN").val(data.authSN);
					$("#outlinepay").val(data.outlinepay);
					
				} 
			}
		})
		/* var data=JSON.parse(data);
		//获取选中的id做增加或修改判断
		id=data.id;
		cktillid=data.tillid;
		ckv61tillid=data.v61tillid;
		alert(ckv61tillid);
		$("#Vtillid").show();
		$("#v61tillid").val(data.v61tillid);
		$("#pos1").show();
		$("#pos2").show();
		$("#pos3").show();
		$("#pos4").show();
		$("#pos5").show();
		$("#posid").val(data.posid);
		$("#mchtId").val(data.mchtId);
		$("#termId").val(data.termId);
		$("#authSN").val(data.authSN);
		$("#outlinepay").val(data.outlinepay);
		//当收银机配置了pos机私有配置时在页面展示可修改
		 if(data.posid!=null&&data.mchid!=null&&data.termid!=null&&data.authSN!=null){
			$("#pos1").show();
			$("#pos2").show();
			$("#pos3").show();
			$("#pos4").show();
			$("#pos5").show();
			$("#posid").val(data.posid);
			$("#mchtId").val(data.mchtId);
			$("#termId").val(data.termId);
			$("#authSN").val(data.authSN);
			$("#outlinepay").val(data.outlinepay);
			//$("#outlinepay").value();
		} 
	}) */
}
//验证收银机编号是否重复
function checkTillid(obj){
	if(obj.value==null||obj.value==cktillid){
		return;
	}else{
		$.get("web/checktillid", {
			tillid : obj.value
		}, function(data) {
			if(!data){
			$("#tillid").focus();
			$("#tillid").val("");
			$("#tillid").attr("placeholder", "收银机编号重复");
			}
		})
	}
	
}
//验证V61收银机编号是否重复
function checkv61tillid(obj){
	if(obj.value==null||obj.value==ckv61tillid){
		return;
	}else{
		$.get("web/checkv61tillid", {
			V61tillid : obj.value
		}, function(data) {
			if(!data){
			$("#v61tillid").focus();
			$("#v61tillid").val("");
			$("#v61tillid").attr("placeholder", "V61收银机编号重复");
			}
		})
	}
}
//搜索商铺
function serchXfStore() {
	var serchXfStoreCodeOrName=$("#serchXfStoreCodeOrName").val();
	$.get("web/xfStaff/getXfStoreByCodeOrName",{codeOrName:serchXfStoreCodeOrName},function(data){
		if (data.length > 0) {
			$("#selectStore").empty();
			for (var int = 0; int < data.length; int++) {
				if (int == 0) {
					$(
							"<option selected value='"
									+ data[int].xfStorecode + "'>"
									+ data[int].xfName
									+ "</option>").appendTo(
							"#selectStore");
				} else {
					$(
							"<option value='"
									+ data[int].xfStorecode + "'>"
									+ data[int].xfName
									+ "</option>").appendTo(
							"#selectStore");
				}
			}
		}
	});
}
//添加其他收银机
function addTill(){
	$("#selectTillid").hide();
	$("#tillidText").show();
	$("#Vtillid").show();
	$("#pos1").show();
	$("#pos2").show();
	$("#pos3").show();
	$("#pos4").show();
	$("#pos5").show();
	$("#posid").val("");
	$("#mchtId").val("");
	$("#termId").val("");
	$("#authSN").val("");
	$("#outlinepay").val("");
	$("#v61tillid").val("");
}
//添加或修改
function addorupdate(){
	   //验证
		if(validateForm()){
			var flag=false;
			var posid=$("#posid").val().trim();
			var mchtId=$("#mchtId").val().trim();
			var termId=$("#termId").val().trim();
			var authSN=$("#authSN").val().trim();
			var outlinepay=$("#outlinepay").val().trim();
			var data=$("#signupForm1").serializeArray(); 
			var tillid;
	        var storeid=$("#selectStore option:selected").val().trim();
			if($("#selectTillid").is(":hidden")){
				tillid=$("#tillid").val();
			}else{
				tillid=$("#selectTillValue option:selected").val().trim();
			}
			if(posid==""&&mchtId==""&&termId==""&&authSN==""&&outlinepay==""){
				if (confirm("确定不添加pos信息吗？")){
					flag=true;
				}else{
					flag=false;
				} 
			}else{
				flag=true;
			}
			if(flag){
				$.ajax({
					url:'web/addorupdateposConfig?tilladdid='+tillid+'&storeid='+storeid+'&outlinepay='+outlinepay,
					type:'POST',
					dataType:'json',
					data:data,
					success : function(data){
						if(data.status.code="10000"){
							bootbox.alert("你已经成功添加或修改收银机私有配置了", function() {  
								window.location.href="web/getCommonConfig";
							} );
						}else{
							bootbox.alert("添加或修改收银机私有配置失败", function() {  
								window.location.href="web/getCommonConfig";
							});
						}
					}  
				})  
			}
			}
	}

 
//验证
function validateForm() {
	if($("#selectTillid").is(":hidden")){
		var tillid=$("#tillid").val().trim();
	}else{
		var tillid=$("#selectTillValue option:selected").val().trim();
	}
	var v61tillid = $("#v61tillid").val().trim();
	var posid=$("#posid").val().trim();
	var mchtId=$("#mchtId").val().trim();
	var termId=$("#termId").val().trim();
	var authSN=$("#authSN").val().trim();
	var outlinepay=$("#outlinepay").val().trim();
	if($("#selectStore option:selected").val() == ""){
		bootbox.alert("请选择店铺");
		return false;
	}else if(tillid==""){
		bootbox.alert("请选择或输入收银机编号");
		return false;
	}else if(v61tillid==""){
		bootbox.alert("请输入v61收银机编号");
		return false;
	} 
	if(posid==""&&mchtId==""&&termId==""&&authSN==""&&outlinepay==""){
		
	}else if(posid!=""&&mchtId!=""&&termId!=""&&authSN!=""&&outlinepay!=""){
		
	}else{
		bootbox.alert("请把pos机号、商户号、终端号、密文、pos机离线是否可用输入完整");
		return false;
	}
	return true;
}