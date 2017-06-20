//商品
function refresh() {
	if (confirm("确定更新商品信息吗?")) {
		var z = "<div class='popWarp' id='zccRES' style='width: 1899px; "
				+ "height: 100%; left: 0px; top: 0px; z-index: 999;'><div style='position: absolute;top: 50%;left: 50%;margin: -100px00-150px;width: 300px;height: 200px;z-index: 99;'><h1><i class='icon-spinner icon-spin orange bigger-125'></i></h1></div></div>";
		$(z).appendTo("body");
		$.get("web/xfItem/refreshItem", function(data) {
			//alert(data.msg);
			$("#zccRES").remove();
			location.href="web/xfItem/getItem";
		});
		
	}
}
function addItem() {
	$("#signupForm1")[0].reset();
	$("#selectStore").empty();
	$("#showTitle1").html("添加商品信息");
	$("#butName1").html("<i class='icon-ok bigger-110'></i>添加");
	var action = "web/xfItem/addItem";
	$("#signupForm1").attr("action", action);
	$("#xfPlu").attr("disabled", false);
	var role = '${sessionScope.XfStaff.staffRole.authority}';
	if ("ROLE_STORE_ADMIN" == role) {
		$(
				"<option value='"
						+ '${sessionScope.XfStaff.xfIssuestore.xfStorecode}'
						+ "' selected>本店</option>").appendTo(
				"#selectCenterStore");
	} else {
		$.get("web/xfItem/getStores",
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

function chkForm1() {
	// 正整数
	var r = /^\+?[1-9][0-9]*$/;
	// 小数
	var money = /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
	// console.log(money.test("32.01"));
	// return false;
	var xfPlu = $("#xfPlu").val();
	var xfDesci = $("#xfDesci").val();
	// var xfLongdesc = $("#xfLongdesc").val();
	var xfStkunit = $("#xfStkunit").val();
	var xfSalesunit = $("#xfSalesunit").val();
	var xfExstk2sales = $("#xfExstk2sales").val();
	var xfOrguprice = $("#xfOrguprice").val();
	var xfOrgwprice = $("#xfOrgwprice").val();
	var xfSeluprice = $("#xfSeluprice").val();
	var xfSelwprice = $("#xfSelwprice").val();
	if (xfPlu == "") {
		$("#xfPlu").focus();
		$("#xfPlu").attr("placeholder", "请输入货号");
	} else if (xfPlu.length > 36) {
		$("#xfPlu").focus();
		$("#xfPlu").val("");
		$("#xfPlu").attr("placeholder", "货号长度不能超过36");
	} else if (xfDesci == "") {
		$("#xfDesci").focus();
		$("#xfDesci").attr("placeholder", "请输入货品描述");
	} else if (xfDesci.length > 36) {
		$("#xfDesci").focus();
		$("#xfDesci").val("");
		$("#xfDesci").attr("placeholder", "货品描述长度不能超过36");
	} else if (xfStkunit == "") {
		$("#xfStkunit").focus();
		$("#xfStkunit").attr("placeholder", "请输入库存单位");
	} else if (xfStkunit.length > 36) {
		$("#xfStkunit").focus();
		$("#xfStkunit").val("");
		$("#xfStkunit").attr("placeholder", "库存单位长度不能超过36");
	} else if (xfSalesunit == "") {
		$("#xfSalesunit").focus();
		$("#xfSalesunit").attr("placeholder", "请输入销售单位");
	} else if (xfStkunit.length > 36) {
		$("#xfSalesunit").focus();
		$("#xfSalesunit").val("");
		$("#xfStkunit").attr("placeholder", "销售单位长度不能超过36");
	} else if (xfExstk2sales == "") {
		$("#xfExstk2sales").focus();
		$("#xfExstk2sales").attr("placeholder", "请输入实际销售数");
	} else if (!r.test(xfExstk2sales)) {
		$("#xfExstk2sales").focus();
		$("#xfExstk2sales").val("");
		$("#xfExstk2sales").attr("placeholder", "请输入正整数");
	} else if (xfOrguprice == "") {
		$("#xfOrguprice").focus();
		$("#xfOrguprice").attr("placeholder", "请输入最初零售价");
	} else if (!money.test(xfOrguprice)) {
		$("#xfOrguprice").focus();
		$("#xfOrguprice").val("");
		$("#xfOrguprice").attr("placeholder", "请输入数字");
	} else if (xfOrgwprice == "") {
		$("#xfOrgwprice").focus();
		$("#xfOrgwprice").attr("placeholder", "请输入最初批发价");
	} else if (!money.test(xfOrgwprice)) {
		$("#xfOrgwprice").focus();
		$("#xfOrgwprice").val("");
		$("#xfOrgwprice").attr("placeholder", "请输入数字");
	} else if (xfSeluprice == "") {
		$("#xfSeluprice").focus();
		$("#xfSeluprice").attr("placeholder", "请输入当前零售价");
	} else if (!money.test(xfSeluprice)) {
		$("#xfSeluprice").focus();
		$("#xfSeluprice").val("");
		$("#xfSeluprice").attr("placeholder", "请输入数字");
	} else if (xfSelwprice == "") {
		$("#xfSelwprice").focus();
		$("#xfSelwprice").attr("placeholder", "请输入当前批发价");
	} else if (!money.test(xfSelwprice)) {
		$("#xfSelwprice").focus();
		$("#xfSelwprice").val("");
		$("#xfSelwprice").attr("placeholder", "请输入数字");
	}else if(isUploadV61=='true'){
		var itemOrgId = $("#itemOrgId").val();
		if(itemOrgId==""){
			$("#itemOrgId").focus();
			$("#itemOrgId").val("");
			$("#itemOrgId").attr("placeholder", "请输入商品组织架构");
		}else if(itemOrgId.length>30){
			$("#itemOrgId").focus();
			$("#itemOrgId").val("");
			$("#itemOrgId").attr("placeholder", "长度不能超过30");
		}else{
			return true;
		}
	} else {
		return true;
	}
	return false;
}
function del(obj, event) {
	var btnNum = event.button;
	if (btnNum == 2) {
		if (confirm("确定删除吗？")) {
			var val = $(obj).find(".xfPlus").html();
			$.post("web/xfItem/deleteXfItemByXfPlu", {
				xfPlus : val
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
function checkXfPlu(obj) {
	$.get("web/xfItem/checkXfPlu", {
		xfPlus : obj.value
	}, function(data) {
		if (data) {
			$("#xfPlu").focus();
			$("#xfPlu").val("");
			$("#xfPlu").attr("placeholder", "商品号重复");
		}
	});
}

function edit(obj) {
	var val = $(obj).find(".xfPlus").html();
	$.post("web/xfItem/getXfItemByXfPlu",{xfPlus : val},function(item) {
		if (item != "") {
			$("#signupForm1")[0].reset();
			$("#selectStore").empty();
			$("#showTitle1").html("修改商品信息");
			$("#butName1").html("<i class='icon-ok bigger-110'></i>修改");
			var action = "web/xfItem/updateItem?xfPlu="+ val;
			$("#signupForm1").attr("action", action);
			// 显示商品信息 disabled="disabled"
			$("#xfPlu").val(item.xfPlu);
			$("#xfPlu").attr("disabled", true);
			$("#xfDesci").val(item.xfDesci);
			$(".xfLongdesc").val(item.xfLongdesc);
			$("#xfStkunit").val(item.xfStkunit);
			$("#xfSalesunit").val(item.xfSalesunit);
			$("#xfExstk2sales").val(item.xfExstk2sales);
			$("#xfOrguprice").val(item.xfOrguprice);
			$("#xfOrgwprice").val(item.xfOrgwprice);
			$("#xfSeluprice").val(item.xfSeluprice);
			$("#xfSelwprice").val(item.xfSelwprice);
			if(isUploadV61=='true'){
				$("#itemOrgId").val(item.itemOrgId);
			}
			var role = '${sessionScope.XfStaff.staffRole.authority}';
			if ("ROLE_STORE_ADMIN" == role) {
				$("<option value='"+ '${sessionScope.XfStaff.xfIssuestore.xfStorecode}'+ "' selected>本店</option>").appendTo("#selectCenterStore");
			} else {
				$.get("web/xfItem/getStores",function(data) {
									if (data.length > 0) {
										for (var int = 0; int < data.length; int++) {
											if (item.xfStorecode.xfStorecode == data[int].xfStorecode) {
												$("<option selected value='"+ data[int].xfStorecode+ "'>"+ data[int].xfName+ "</option>")
														.appendTo("#selectStore");
											} else {
												$("<option value='"+ data[int].xfStorecode+ "'>"+ data[int].xfName+ "</option>")
														.appendTo("#selectStore");
											}
										}
									}
								});
			}
			$('#dialog1').dialog('open');
		} else {
			alert("获取商品信息失败！");
		}
	});
}
/* 店铺搜索  */
function serchXfStore() {
	var serchXfStoreCodeOrName=$("#serchXfStoreCodeOrName").val();
	$.get("web/xfItem/getStores",{codeOrName:serchXfStoreCodeOrName},function(data){
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
