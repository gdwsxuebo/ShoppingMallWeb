//付款方式
function refresh() {
	if (confirm("确定更新付款方式信息吗?")) {
		var z = "<div class='popWarp' id='zccRES' style='width: 1899px; "
				+ "height: 100%; left: 0px; top: 0px; z-index: 999;'><div style='position: absolute;top: 50%;left: 50%;margin: -100px00-150px;width: 300px;height: 200px;z-index: 99;'><h1><i class='icon-spinner icon-spin orange bigger-125'></i></h1></div></div>";
		$(z).appendTo("body");
		$.get("web/xfTender/refreshTender", function(data) {
			//alert(data.msg);
			$("#zccRES").remove();
			location.href="web/xfTender/getTender";
		});
		
	}
}

function addTender() {
	$("#signupForm")[0].reset();
	$("#showTitle").html("添加付款方式");
	$("#butName").html("<i class='icon-ok bigger-110'></i>添加");
	$("#xfTendercode").attr("disabled", false);
	$("#id-button-borders").attr("checked", false);
	$("#dialog1").dialog("open");
}
function chkForm() {
	var xfTendercode = $("#xfTendercode").val().trim();
	var xfTenderdesc = $("#xfTenderdesc").val().trim();
	if (xfTendercode == "") {
		$("#xfTendercode").focus();
		$("#xfTendercode").attr("placeholder", "请输入付款方式编码");
	} else if (xfTendercode.length > 15) {
		$("#xfTendercode").focus();
		$("#xfTendercode").val("");
		$("#xfTendercode").attr("placeholder", "付款方式编码长度不能超过15");
	} else if (xfTenderdesc == "") {
		$("#xfTenderdesc").focus();
		$("#xfTenderdesc").attr("placeholder", "请输入付款方式描述");
	} else if (xfTenderdesc.length > 50) {
		$("#xfTenderdesc").focus();
		$("#xfTenderdesc").val("");
		$("#xfTenderdesc").attr("placeholder", "付款方式描述长度不能超过50");
	} else {
		return true;
	}
	return false;
}
function del(obj, event) {
	var btnNum = event.button;
	if (btnNum == 2) {
		if (confirm("确定删除吗？")) {
			var val = $(obj).find(".xfTenderCode").html();
			$.post("web/xfTender/deleteXfTenderByXfTendercode", {
				xfTendercode : val
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
function edit(obj) {
	var val = $(obj).find(".xfTenderCode").html();
	$.get("web/xfTender/getXfTender", {
		xfTenderCode : val
	}, function(xfTender) {
		if (xfTender != "") {
			$("#signupForm")[0].reset();
			$("#showTitle").html("修改付款方式");
			$("#butName").html("<i class='icon-ok bigger-110'></i>修改");
			$("#xfTendercode").attr("disabled", true);
			$("#xfTendercode").val(xfTender.xfTendercode);
			$("#xfTenderdesc").val(xfTender.xfTenderdesc);
			$("#id-button-borders").attr("checked", xfTender.xfRefund);
			$("#xfCode").val(xfTender.xfTendercode);
			$("#dialog1").dialog("open");
		} else {
			alert("获取支付方式信息失败！");
		}
	});
}
function checkXfTender(obj) {
	$.get("web/xfTender/getXfTender", {
		xfTenderCode : obj.value
	}, function(xfTender) {
		if (xfTender != "") {
			$("#xfTendercode").focus();
			$("#xfTendercode").val("");
			$("#xfTendercode").attr("placeholder", "付款方式编码重复");
		}
	});
}
