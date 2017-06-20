//员工
function addStaff() {
	$(".selectStoreTr").show();
	$("#signupForm1")[0].reset();
	$("#selectStore").empty();
	$.get("web/xfStaff/getRoles", function(data) {
				if (data.length > 0) {
					for (var int = 0; int < data.length; int++) {
						if (int == 0) {
							$( "<option selected value='"
											+ data[int].id + "'>"
											+ data[int].cnName
											+ "</option>").appendTo(
									"#roleGroup");
						} else {
							$( "<option value='"
											+ data[int].id + "'>"
											+ data[int].cnName
											+ "</option>").appendTo(
									"#roleGroup");
						}
					}
				}
			});
	
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
function selectRoleChange(obj) {
	if (3 == obj.value) {
		$(".selectStoreTr").hide();
	} else {
		$(".selectStoreTr").show();
	}
}

function chkForm1() {
	var xfStaffcode = $("#xfStaffcode").val().trim();
	var xfName = $("#xfName").val().trim();
	var xfPassword = $("#xfPassword").val().trim();
	if (xfStaffcode == "") {
		$("#xfStaffcode").focus();
		$("#xfStaffcode").attr("placeholder", "请输入员工编号");
	} else if (xfStaffcode.length > 36) {
		$("#xfStaffcode").focus();
		$("#xfStaffcode").val("");
		$("#xfStaffcode").attr("placeholder", "员工编号长度不能超过36");
	} else if (xfName.length > 36) {
		$("#xfName").focus();
		$("#xfName").val("");
		$("#xfName").attr("placeholder", "员工名称长度不能超过36");
	} else if (xfPassword == "") {
		$("#xfPassword").focus();
		$("#xfPassword").attr("placeholder", "请输入员工密码");
	} else if (xfPassword.length > 32) {
		$("#xfPassword").focus();
		$("#xfPassword").val("");
		$("#xfPassword").attr("placeholder", "员工密码长度不能超过32");
	} else {
		return true;
	}
	return false;
}

function checkXfStaffcode(obj) {
	$.get("web/xfStaff/checkXfStaffcode", {
		xfStaffcode : obj.value
	}, function(data) {
		if (data) {
			$("#xfStaffcode").focus();
			$("#xfStaffcode").val("");
			$("#xfStaffcode").attr("placeholder", "员工编号重复");
		}
	});
}

function del(obj, event) {
	var btnNum = event.button;
	if (btnNum == 2) {
		if (confirm("确定删除吗？")) {
			var val = $(obj).find(".staffCode").html();
			$.post("web/xfStaff/deleteXfStaffByCode", {
				xfStaffCode : val
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
function edit(obj, xfStaffCode) {
	// console.log(xfStaffCode);
	$(".selectStoreTr").show();
	$("#signupForm2")[0].reset();
	$("#selectStore2").empty();
	$
			.get(
					"web/xfStaff/getXfStaff",
					{
						xfStaffCode : xfStaffCode
					},
					function(xfStaff) {
						if (xfStaff != "") {
							$(".xfStaffcode2").val(xfStaff.xfStaffcode);
							$("#xfName2").val(xfStaff.xfName);
							$("#xfPassword2").val(xfStaff.xfPassword);
							$(".updateIsUse").attr("checked", xfStaff.enabled);
							$("#roleSel").val(xfStaff.staffRoleInt);
							if (xfStaff.staffRoleInt == 3) {
								$(".selectStoreTr").hide();
							}
							
							$.get("web/xfStaff/getRoles",
									function(data) {
										if (data.length > 0) {
											for (var int = 0; int < data.length; int++) {
												if (xfStaff.gwRoleId == data[int].id) {
													$(
															"<option selected value='"
																	+ data[int].id + "'>"
																	+ data[int].cnName
																	+ "</option>").appendTo(
															"#roleGroup2");
												} else {
													$(
															"<option value='"
																	+ data[int].id + "'>"
																	+ data[int].cnName
																	+ "</option>").appendTo(
															"#roleGroup2");
												}
											}
										}
									});
							
							// console.log(xfStaff);
							var role = '${sessionScope.XfStaff.staffRole.authority}';
							if ("ROLE_STORE_ADMIN" == role) {
								$(
										"<option value='"
												+ '${sessionScope.XfStaff.xfIssuestore.xfStorecode}'
												+ "' selected>本店</option>")
										.appendTo("#selectCenterStore");
							} else {
								$
										.get(
												"web/xfStaff/getStores",
												function(data) {
													if (data.length > 0) {
														for (var int = 0; int < data.length; int++) {
															if (xfStaff.xfStorecode == data[int].xfStorecode) {
																$(
																		"<option selected value='"
																				+ data[int].xfStorecode
																				+ "'>"
																				+ data[int].xfName
																				+ "</option>")
																		.appendTo(
																				"#selectStore2");
															} else {
																$(
																		"<option value='"
																				+ data[int].xfStorecode
																				+ "'>"
																				+ data[int].xfName
																				+ "</option>")
																		.appendTo(
																				"#selectStore2");
															}
														}
													}
												});
							}
							$('#dialog2').dialog('open');
						} else {
							alert("获取员工信息失败！");
						}
					});
}
function chkForm2() {
	var xfName = $("#xfName2").val().trim();
	var xfPassword = $("#xfPassword2").val().trim();
	if (xfName.length > 36) {
		$("#xfName2").focus();
		$("#xfName2").val("");
		$("#xfName2").attr("placeholder", "员工名称长度不能超过36");
	} else if (xfPassword == "") {
		$("#xfPassword2").focus();
		$("#xfPassword2").attr("placeholder", "请输入员工密码");
	} else if (xfPassword.length > 32) {
		$("#xfPassword2").focus();
		$("#xfPassword2").val("");
		$("#xfPassword2").attr("placeholder", "员工密码长度不能超过32");
	} else if ($("#roleSel option:selected").val() != "3") {
		if ($("#selectStore2 option:selected").val() == null) {
			alert("请选择关联的商场");
			return false;
		}else {
			return true;
		}
	} else {
		return true;
	}
	return false;
}

/* 店铺搜索  */
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
