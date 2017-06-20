//商铺
var OBJ = "";
var XFS = "";
//判断该店铺下是否有商品
function xfStoreCenter(obj, xfStorecode) {
	OBJ = obj;
	// alert(xfStorecode);
	XFS = xfStorecode;
	if (obj.checked) {
		$.get("web/xfStore/getStoreCenter", function(data) {
			if (data.length > 0) {
				$("#form-field-select-2").empty();
				// 添加
				$("<option value=" + XFS + " selected>本店铺</option>").appendTo(
						"#form-field-select-2");
				for (var int = 0; int < data.length; int++) {
					var xfc = data[int].xfCenterstorecode.xfStorecode;
					if (xfStorecode != xfc) {
						$(
								"<option value=" + xfc + ">"
										+ data[int].xfCenterstorecode.xfName
										+ "</option>").appendTo(
								"#form-field-select-2");
					}
				}

				$('#dialog1').dialog('open');
			} else {
				// 添加本店铺
				addXfc(obj, xfStorecode, xfStorecode);

			}
		});
	} else {
		$.post("web/xfStore/addIsDeleXfStoreCenter", {
			xfStorecode : xfStorecode
		}, function(data) {
			if (data == true) {

			} else {
				alert("取消中央店铺失败！");
				$(obj).prop("checked", true);
			}
		});
	}
}

function addXfc(obj, xfStorecode, xfCenterStorecode, xfName) {
	$
			.post(
					"web/xfStore/addIsDeleXfStoreCenter",
					{
						xfStorecode : xfStorecode,
						xfCenterStorecode : xfCenterStorecode
					},
					function(data) {
						if (data == true) {
							if (xfStorecode != xfCenterStorecode) {
								$(obj).attr("disabled", "disabled");
								$(obj).prop("checked", false);
								// alert(xfCenterStorecode+" "+xfName);
								$("#xfN" + xfStorecode).html(xfName);
								var de = "<button class='btn btn-grey btn-xs' onclick=deleteXFSC(this,'"
										+ xfStorecode
										+ "','"
										+ xfCenterStorecode
										+ "');>"
										+ "<i class='icon-trash icon-2x icon-only'></i></button>";
								$(de).appendTo("#xfN" + xfStorecode);
							}
							$('#dialog1').dialog('close');
						} else {
							alert("设置中央店铺失败或者如果设置中央店铺则该店铺下必须无商品！");
							$(obj).prop("checked", false);
						}
					});
}
function addBtnXfc() {
	var val = $("#form-field-select-2 option:selected").val() + "";
	if (val != null) {
		addXfc(OBJ, XFS, val, $("#form-field-select-2 option:selected").text());
	}
}
function closeCheck() {
	$(OBJ).prop("checked", false);
}

function deleteXFSC(obj, xfStorecode, xfCenterStorecode) {
	if (confirm("确定取消关联吗？")) {
		$.post("web/xfStore/deleteXFSC", {
			xfStorecode : xfStorecode,
			xfCenterStorecode : xfCenterStorecode
		}, function(data) {
			if (data == true) {
				$(obj).remove();
				$("#xfN" + xfStorecode).html("");
				$("#chec" + xfStorecode).attr("disabled", false);
				$(OBJ).prop("checked", false);
			} else {
				alert("取消关联店铺失败！");
			}
		});
	}
}
function refresh() {
	if (confirm("确定更新商铺吗?")) {
		var z = "<div class='popWarp' id='zccRES' style='width: 1899px; "
				+ "height: 100%; left: 0px; top: 0px; z-index: 999;'><div style='position: absolute;top: 50%;left: 50%;margin: -100px00-150px;width: 300px;height: 200px;z-index: 99;'><h1><i class='icon-spinner icon-spin orange bigger-125'></i></h1></div></div>";
		$(z).appendTo("body");
		$.get("web/xfStore/refreshStore", function(data) {
			// alert(data.msg);
			$("#zccRES").remove();
			location.href = "web/xfStore/getStore";
		});

	}
}

function operation() {
	$("#signupForm2")[0].reset();
	$("#selectCenterStore").empty();
	// selectCenterStore
	$("<option value='no' selected>不设置</option>")
			.appendTo("#selectCenterStore");
	$("<option value='my'>本店铺</option>").appendTo("#selectCenterStore");
	$.get("web/xfStore/getStoreCenter", function(data) {
		if (data.length > 0) {
			for (var int = 0; int < data.length; int++) {
				$(
						"<option value='"
								+ data[int].xfCenterstorecode.xfStorecode
								+ "'>" + data[int].xfCenterstorecode.xfName
								+ "</option>").appendTo("#selectCenterStore");
			}
		}
	});
	$('#dialog2').dialog('open');
}
var CXS = true;
function chkForm2() {
	var xfStorecode = $("#xfStorecode").val().trim();
	var xfName = $("#xfName").val().trim();
	if (xfStorecode == "") {
		$("#xfStorecode").focus();
		$("#xfStorecode").attr("placeholder", "请输入商铺编号");
	} else if (xfStorecode.length > 30) {
		$("#xfStorecode").focus();
		$("#xfStorecode").val("");
		$("#xfStorecode").attr("placeholder", "商铺编号长度不能超过30");
	} else if (xfName == "") {
		$("#xfName").focus();
		$("#xfName").attr("placeholder", "请输入商铺名称");
	} else if (xfName.length > 30) {
		$("#xfName").focus();
		$("#xfName").val("");
		$("#xfName").attr("placeholder", "商铺名称长度不能超过30");
	} else if (CXS) {
		$("#xfStorecode").focus();
		$("#xfStorecode").val("");
		$("#xfStorecode").attr("placeholder", "商铺已存在");
	} else {
		return true;
	}
	return false;
}
function checkXfStorecode(obj) {
	$.get("web/xfStore/checkStoreByStoreCode", {
		storeCode : obj.value
	}, function(data) {
		if (data) {
			CXS = true;
		} else {
			CXS = false;
		}
	});
}
function del(obj, event) {
	var btnNum = event.button;
	if (btnNum == 2) {
		if (confirm("确定删除吗？")) {
			var val = $(obj).find(".xfStorecode").html();
			$.post("web/xfStore/deleteXfStoreByCode", {
				xfStorecode : val
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
var XFNAME = "";
function edit(obj, type) {
	XFNAME = $(obj).html();
	var inp = "<input value='" + XFNAME
			+ "' style='height:30px;' onblur='blu(this," + type + ");'/>";
	$(obj).html("");
	$(inp).appendTo(obj).focus();

}
function blu(obj, type) {
	var val = obj.value;
	if (type == 2) {
		if (val == "") {
			$(obj).attr("placeholder", "请输入店铺名称");
			$(obj).focus();
			return;
		} else if (val.length > 30) {
			$(obj).attr("placeholder", "店铺名称长度不能大于30");
			$(obj).val("");
			$(obj).focus();
			return;
		} else if (XFNAME == val) {
			var par = $(obj).parent();
			$(par).html(val);
			$(obj).remove();
		} else {
			// console.log(obj);
			var storeCode = $(obj).parent("td").prev(".xfStorecode").html();
			$.post("web/xfStore/editStoreCode", {
				xfStorecode : storeCode,
				xfName : val
			}, function(data) {
				if (data.flag) {
					var par = $(obj).parent();
					$(par).html(val);
					$(obj).remove();
				} else {
					$(obj).focus();
					alert(data.msg);
				}
			});
		}
	}
}
/* 店铺搜索 */
function serchXfStore() {
	var serchXfStoreCodeOrName = $("#serchXfStoreCodeOrName").val();
	if (serchXfStoreCodeOrName == "") {
		$("#selectCenterStore").empty();
		$("<option value='no' selected>不设置</option>").appendTo(
				"#selectCenterStore");
		$("<option value='my'>本店铺</option>").appendTo("#selectCenterStore");
		$.get("web/xfStore/getStoreCenter", function(data) {
			if (data.length > 0) {
				for (var int = 0; int < data.length; int++) {
					$(
							"<option value='"
									+ data[int].xfCenterstorecode.xfStorecode
									+ "'>" + data[int].xfCenterstorecode.xfName
									+ "</option>").appendTo(
							"#selectCenterStore");
				}
			}
		});
	} else {
		$
				.get(
						"web/xfStore/getStoreCenter",
						{
							codeOrName : serchXfStoreCodeOrName
						},
						function(data) {
							if (data.length > 0) {
								$("#selectCenterStore").empty();
								for (var int = 0; int < data.length; int++) {
									if (int == 0) {
										$(
												"<option selected value='"
														+ data[int].xfCenterstorecode.xfStorecode
														+ "'>"
														+ data[int].xfCenterstorecode.xfName
														+ "</option>")
												.appendTo("#selectCenterStore");
									} else {
										$(
												"<option value='"
														+ data[int].xfCenterstorecode.xfStorecode
														+ "'>"
														+ data[int].xfCenterstorecode.xfName
														+ "</option>")
												.appendTo("#selectCenterStore");
									}
								}
							}
						});
	}
}
/* 设置中央店铺或者关联店铺时店铺搜索 */
var serchXfStore2HTML = "";
function serchXfStore2() {
	var serchXfStoreCodeOrName = $("#serchXfStoreCodeOrName").val();
	if (serchXfStoreCodeOrName == "" && serchXfStore2HTML!="") {
		$("#form-field-select-2").empty();
		$(serchXfStore2HTML).appendTo(
		"#form-field-select-2");
	} else {
		$.get("web/xfStore/getXfStoreByCodeOrName", {
			codeOrName : serchXfStoreCodeOrName
		}, function(data) {
			if (data.length > 0) {
				//console.log(data);
				serchXfStore2HTML = $("#form-field-select-2").html();
				$("#form-field-select-2").empty();
				// 添加
				$("<option value=" + XFS + " selected>本店铺</option>").appendTo(
						"#form-field-select-2");
				for (var int = 0; int < data.length; int++) {
					var xfc = data[int].xfCenterstorecode.xfStorecode;
					$(
							"<option value=" + xfc + ">"
									+ data[int].xfCenterstorecode.xfName
									+ "</option>").appendTo(
							"#form-field-select-2");
				}
			}
		});
	}
}
var storeid;
function  configureCenterStore(storecode){
	storeid=storecode;
	initStoreInfo(storecode);
	$('#myModal').modal('show');
}
function initStoreInfo(storecode){
	$("#notUseStore").empty();
	$.post('web/getAllStoreCenterStore',{'storecode':storecode},function(data){
		var i=0;var tempContext="";
		$.each(data.data,function(index,item){
			if(item.ischildren==item.xfStorecode)
			tempContext=tempContext+"<td><input type='checkbox' name='selectStores' value='"+item.xfStorecode+"' checked='checked'/> "+item.xfName+"</td>";
			else tempContext=tempContext+"<td><input type='checkbox' name='selectStores'  value='"+item.xfStorecode+"' /> "+item.xfName+"</td>";
			i++;
			if(i==2){
				$("#notUseStore").append("<tr>'"+tempContext+"'</tr>");	
				i=0;tempContext="";
			}
		});
	});
}
function subStoreCenterStore(){
	var stores=new Array();
	$('input:checked').each(function() {
		stores.push($(this).val());
	});
	$.post('web/updateStoreCenterStore',{'stores':stores.join(','),'storeid':storeid},function(data){
		$('#myModal').modal('hide');
		if(data.status.code=="10000"){
	      bootbox.alert("成功添加为中央店铺添加子店铺！",function(){
	    	  location.reload();
	      });  
		}else{
	    	bootbox.alert("为中央店铺添加子店铺失败！");  
		}
});
	
}