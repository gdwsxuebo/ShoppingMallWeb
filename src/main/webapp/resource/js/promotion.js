$(document).ready(function() {

})

/* 添加 */
function add() {
	$("#signupForm1")[0].reset();
	$("#issueRanges").empty();	$("#useRanges").empty();
	$("#showTitle1").html("添加促销规则  <font size=1px; color=#FFF8DC;>ps:使用有效天数与使用有效日期只能二选一</font>");
	$("#butName1").html("<i class='icon-ok bigger-110'></i> 添加");
	$("#useBeginDate").attr("disabled", false);
	$("#useEndDate").attr("disabled", false);
	$("#useValidNum").attr("disabled", false);
	$("#signupForm1").attr("action", "web/promotion/addPR");
	$("#chongzhi").show();
	$("#zccRES").remove();
	$(".PRType").remove();
	$("#zccRES").remove();
	$('#dialog1').dialog('open');
}

/* 添加或减少数量 */
function addOrSub(obj) {
	var number = parseInt($("#number").val());
	if (1 == obj) {
		number = 1 + number;
		if (number < 99999999) {
			$("#number").val(number);
		}
	} else {
		number = number - 1;
		if (number > 0) {
			$("#number").val(number);
		}
	}

}

/* 验证输入的数量是否合法 */
function checkNum(obj) {
	var val = parseInt(obj.value);
	var t = /^[0-9]*$/;
	if (!t.test(val)) {
		alert("请输入正整数");
		$("#number").focus();
		$("#number").val(1);
	} else {
		if (val < 0) {
			$("#number").val(1);
		} else if (val > 99999999) {
			$("#number").val(99999999);
		}
	}

}

/* 发放范围 */
function issueRanges(stcs) {
	var is = $("#issueRanges");
	is.empty();
	$.get("web/promotion/getStores",
					function(data) {
						var c = "";
						if (data != null && data.length > 0) {
							$(data).each(
							function(index, element) {
							if (stcs != null) {
							for (var int = 0, len = stcs.length; int < len; int++) {
							if (data[index].xfStorecode == stcs[int].xfStorecode) {
							c = "checked";
							break;
							} else if (int == len - 1) {
							c = "";
							}
							}
							} else {
							c = "";
							}
							var str = "<label><input name='issueRanges' class='ace ace-checkbox-2' type='checkbox' "
							+ c
							+ " value="
							+ data[index].xfStorecode
							+ "><span class='lbl'>"
							+ data[index].xfStorecode
							+ ":"
							+ data[index].xfName
							+ "</span></label><br/>";
							if(""!=c)	$(str).appendTo(is);
							});
						}
					});
}

/* 使用范围 */
function useRanges(stcs) {
	var us = $("#useRanges");
	us.empty();
	$
			.get(
					"web/promotion/getStores",
					function(data) {
						if (data != null && data.length > 0) {
							var c = "";
							$(data)
									.each(
											function(index, element) {
												if (stcs != null) {
													for (var int = 0, len = stcs.length; int < len; int++) {
														if (data[index].xfStorecode == stcs[int].xfStorecode) {
															c = "checked";
															break;
														} else if (int == len - 1) {
															c = "";
														}
													}
												} else {
													c = "";
												}
												var str = "<label><input name='useRanges' class='ace ace-checkbox-2' type='checkbox' "
														+ c
														+ " value="
														+ data[index].xfStorecode
														+ "><span class='lbl'>"
														+ data[index].xfStorecode
														+ ":"
														+ data[index].xfName
														+ "</span></label><br/>";
												if(""!=c)	$(str).appendTo(us);
											});
						}
					});
}

/* 隐藏有效天数或者有效日期 */
function useValidNumOrDate(obj) {
	if (1 == obj) {
		$("#useBeginDate").attr("disabled", true);
		$("#useEndDate").attr("disabled", true);
		$("#useValidNum").attr("disabled", false);
	} else {
		$("#useBeginDate").attr("disabled", false);
		$("#useEndDate").attr("disabled", false);
		$("#useValidNum").attr("disabled", true);
	}
}

/* 有效天数失去焦点如果为空则放开使用有效日期  */
function useValOnBlur(obj) {
	var val=$(obj).val();
	if(val==""){
		$("#useBeginDate").attr("disabled", false);
		$("#useEndDate").attr("disabled", false);
	}
}

/* 鼠标移除日期时检查日期是否为空，如果为空则放开使用有效天数  */
function useBAndEDate() {
	var useBeginDate=$("#useBeginDate").val();
	var useEndDate=$("#useEndDate").val();
	if(useBeginDate=="" && useEndDate==""){
		$("#useValidNum").attr("disabled", false);
	}
}

/* 验证输入框 */
function chkForm1() {
	var t = /^[0-9]*$/;
	var title = $("#title").val();
	var number = $("#number").val();
	var detailExplain = $("#detailExplain").val();
	var beginIssueDate = $("#beginIssueDate").val();
	var endIssueDate = $("#endIssueDate").val();
	var lowestConsumptionMoney = $("#lowestConsumptionMoney").val();
	var exp = /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
	var rank = $("#rank").val();
	// 获取到所有name为'issueRanges'并选中的checkbox(集合)
	var issueRanges = $("input[name='issueRanges']:checked");
	var issueBeginDate = $("#issueBeginDate").val();
	var issueEndDate = $("#issueEndDate").val();
	var useValidNum = $("#useValidNum").val();
	var useBeginDate = $("#useBeginDate").val();
	var useEndDate = $("#useEndDate").val();
	// 获取到所有name为'useRanges'并选中的checkbox(集合)
	var useRanges = $("input[name='useRanges']:checked");
	var useBeginPeriod = $("#useBeginPeriod").val();
	var useEndPeriod = $("#useEndPeriod").val();
	var offsetMoney = $("#offsetMoney").val();
	if (title == "") {
		$("#title").focus();
		$("#title").attr("placeholder", "请输入标题");
	} else if (title.length > 50) {
		$("#title").focus();
		$("#title").val("");
		$("#title").attr("placeholder", "标题长度不能超过50");
	} else if (number == "" || !t.test(number)) {
		$("#number").focus();
		$("#number").attr("placeholder", "请输入正整数");
	} else if (title.length > 99999999) {
		$("#number").focus();
		$("#number").val("");
		$("#number").attr("placeholder", "标题大小不能超过99999999");
	} else if (detailExplain == "") {
		$("#detailExplain").focus();
		$("#detailExplain").attr("placeholder", "请输入说明");
	} else if (detailExplain.length > 200) {
		$("#detailExplain").focus();
		$("#detailExplain").val("");
		$("#detailExplain").attr("placeholder", "说明长度不能超过200");
	} else if (beginIssueDate == "") {
		$("#beginIssueDate").focus();
		$("#beginIssueDate").attr("placeholder", "请选择有效时间");
	} else if (endIssueDate == "") {
		$("#endIssueDate").focus();
		$("#endIssueDate").attr("placeholder", "请选择有效时间");
	} else if (lowestConsumptionMoney == "") {
		$("#lowestConsumptionMoney").focus();
		$("#lowestConsumptionMoney").attr("placeholder", "请输入最低消费金额");
	} else if (!exp.test(lowestConsumptionMoney)) {
		$("#lowestConsumptionMoney").focus();
		$("#lowestConsumptionMoney").val("");
		$("#lowestConsumptionMoney").attr("placeholder", "请输入合法的金额");
	} else if (offsetMoney == "") {
		$("#offsetMoney").focus();
		$("#offsetMoney").attr("placeholder", "请输入最低消费金额");
	} else if (!exp.test(offsetMoney)) {
		$("#offsetMoney").focus();
		$("#offsetMoney").val("");
		$("#offsetMoney").attr("placeholder", "请输入合法的金额");
	} else if (!t.test(rank)) {
		$("#rank").focus();
		$("#rank").val("");
		$("#rank").attr("placeholder", "请输入正整数");
	} else if (issueRanges == null || issueRanges.length == 0) {
		alert("请选择发放范围");
	} else if (issueBeginDate == "") {
		$("#issueBeginDate").focus();
		$("#issueBeginDate").attr("placeholder", "请选择开始发放时间段");
	} else if (issueEndDate == "") {
		$("#issueEndDate").focus();
		$("#issueEndDate").attr("placeholder", "请选择开始发放时间段");
	} else if (!t.test(useValidNum) && useBeginDate == "" && useEndDate == "") {
		$("#useValidNum").focus();
		$("#useValidNum").val("");
		$("#useValidNum").attr("placeholder", "请输入整数有效天数");
	} else if (useBeginDate == "" && useValidNum == "") {
		$("#useBeginDate").focus();
		$("#useBeginDate").attr("placeholder", "请选择有效开始日期");
	} else if (useEndDate == "" && useValidNum == "") {
		$("#useEndDate").focus();
		$("#useEndDate").val("");
		$("#useEndDate").attr("placeholder", "请选择有效结束日期");
	} else if (useRanges == null || useRanges.length == 0) {
		alert("请选择使用范围");
	} else if (useBeginPeriod == "") {
		$("#useBeginPeriod").focus();
		$("#useBeginPeriod").attr("placeholder", "请选择使用开始时段");
	} else if (useEndPeriod == "") {
		$("#useEndPeriod").focus();
		$("#useEndPeriod").attr("placeholder", "请选择使用结束时段");
	} else {
		return true;
	}
	return false;
}

/* 编辑 */
function edit(obj) {
	$.get("web/promotion/getPR",{id : obj},function(data) {
						if (data != null) {
							$("#signupForm1")[0].reset();
							$("#issueRanges").empty();	$("#useRanges").empty();
							$("#showTitle1").html("修改促销规则  <font size=1px; color=#FFF8DC;>ps:有效天数与有效日期只能二选一</font>");
							$("#butName1").html("<i class='icon-ok bigger-110'></i> 修改");
							$("#useBeginDate").attr("disabled", false);
							$("#useEndDate").attr("disabled", false);
							$("#useValidNum").attr("disabled", false);
							$("#signupForm1").attr("action","web/promotion/updatePR");
							$("#chongzhi").hide();
							$("#zccRES").remove();
							/* 赋值 */
							$("#PRID").val(data.id);
							$("#title").val(data.title);
							$("#detailExplain").val(data.detailExplain);
							$("#number").val(data.number);
							$("#beginIssueDate").val(data.beginIssueDate);
							$("#endIssueDate").val(data.endIssueDate);
							$(".issueVipCondition").attr("checked",data.issueVipCondition);
							$("#lowestConsumptionMoney").val(	data.lowestConsumptionMoney);
							$("#issueBeginDate").val(data.issueBeginDate);
							$("#issueEndDate").val(data.issueEndDate);
							issueRanges(data.issueRanges);
							$("#rank").val(data.rank);
							if (data.useValidNum == null) {$("#useValidNum").attr("disabled", true);
							} else {$("#useBeginDate").attr("disabled", true);
								$("#useEndDate").attr("disabled", true);
							}
							$("#useValidNum").val(data.useValidNum);
							$("#useBeginDate").val(data.useBeginDate);
							$("#useEndDate").val(data.useEndDate);
							useRanges(data.useRanges);
							$("#useBeginPeriod").val(data.useBeginPeriod);
							$("#useEndPeriod").val(data.useEndPeriod);
							$("#offsetMoney").val(data.offsetMoney);
							$('#dialog1').dialog('open');
							if (data.type != 0) {
								$("#zccRES").remove();
								$(".PRType").remove();
								var prIsReade = $('#prIsReade');
								var top = prIsReade.position().top;
								var left = prIsReade.position().left;
								var z = "<div class='popWarp' id='zccRES' style='width: "
										+ prIsReade.width()
										+ "px; "
										+ "height: "
										+ prIsReade.height()
										+ "px; left: "
										+ left
										+ "px; top: "
										+ top + "px; z-index: 999;'></div>";
								$(z).appendTo(prIsReade);
								// 单选
								//alert(data.type);
								var lab1 = "<label class='PRType'><input name='type' class='ace ace-checkbox-2' type='radio' "
									+ (data.type == 1 ? "checked" : "")
									+ " value='1'><span class='lbl'>正常发放促销劵，使用促销劵</span>&nbsp;&nbsp;</label>";
								var lab2 = "<label class='PRType'><input name='type' class='ace ace-checkbox-2' type='radio' "
										+ (data.type == 2 ? "checked" : "")
										+ " value='2'><span class='lbl'>禁止发放该促销劵，但是可以用已产生的促销劵</span></label>";
								var lab3 = "<label class='PRType'><input name='type' class='ace ace-checkbox-2' type='radio'  "
										+ (data.type == 3 ? "checked" : "")
										+ " value='3'><span class='lbl'>禁止发放该促销劵和禁止使用已产生的促销劵</span></label>";
								$("#butName1").before(lab1);
								$("#butName1").before(lab2);
								$("#butName1").before(lab3);
								$("#butName1")
										.html(
												"<i class='icon-ok bigger-110'></i> 确定");
								$("#signupForm1").attr("action",
										"web/promotion/changePRType");
							} else {
								$("#zccRES").remove();
								$(".PRType").remove();
							}
						} else {
							alert("无该促销规则信息！");
						}
					});
}

/* 删除 */
function del(obj, event, id) {
	var btnNum = event.button;
	if (btnNum == 2) {
		if (confirm("确定删除吗？")) {
			$.post("web/promotion/deletePR", {
				id : id
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

/* 选择最多选择几个促销规则 */
function changePR(obj) {
	var value = obj.value;
	$.post("web/promotion/maxSelectPR", {
		"maxSelectPR" : value
	}, function(data) {
		if (!data) {
			alert("设置失败！");
		}
	});
}

/* 选择发放范围 */
function showIR() {
	$("#issueRanges").empty();
	var prIsReade = $('#prIsReade');
	var top = prIsReade.position().top;
	var left = prIsReade.position().left;
	var msg = "取消全选";
	if ($("#issueRanges").children("label").length == 0) {
		msg = "全选";
	}
	var serV="<button type='button' class='btn btn-purple btn-sm' style='height:28px;' onclick='onkeyUpPR(serarchShowIR,1);';>搜索 <i class='icon-search icon-on-right bigger-110'></i></button>";
	var ser = "<div style='float: left;'>"
			+ "<form class='form-search' action='javaScript:void(0);'>"
			+ "<span class='input-icon'> <input type='text' id='serarchShowIR'"
			+ " placeholder='编号或者名称' class='nav-search-input'"
			+ " autocomplete='off'> <i"
			+ " class='icon-search nav-search-icon'></i>"
			+ "</span></form></div>"+serV;
	var select = "<div style='width: 100%;height: 100%;'>"
			+ "<div style='width: 100%; height: 5%;background-color: #F0F8FF;'>"
			+ ser
			+ "<input type='button' style='margin-left: 15%;' class='btn btn-xs' onclick='quanxuan(this,1);' value="
			+ msg
			+ " />&nbsp;&nbsp;"
			+ "<lable style='float: right;'><input type='button' class='btn btn-xs btn-info' onclick='queren(1);' value='确认'/></lable>"
			+ "</div>"
			+ "<div style='width: 50%; height:95%; overflow: auto;background-color: #F0F8FF;float: left;' class='form-control' id='selectedISSRAN'></div>"
			+ "<div style='width: 50%; height: 95%; overflow: auto;background-color: #E6E6FA;float: right;' class='form-control' id='addISSRAN'></div>"

			+ "</div>";
	var z = "<div id='zccRES' style='width: "
			+ prIsReade.width()
			+ "px; "
			+ "height: "
			+ $("#signupForm1").height()
			+ "px; left: "
			+ left
			+ "px; top: "
			+ top
			+ "px; z-index: 999;background-color: #C0C0C0;-moz-opacity: 1;opacity:1;filter: alpha(opacity=50);position:absolute;z-index:1200;'>"
			+ select + "</div>";
	$(z).appendTo(prIsReade);
	selectedISSRAN();
}

/* 选择使用范围 */
function showUR() {
	$("#useRanges").empty();
	var prIsReade = $('#prIsReade');
	var top = prIsReade.position().top;
	var left = prIsReade.position().left;
	var msg = "取消全选";
	if ($("#useRanges").children("label").length == 0) {
		msg = "全选";
	}
	var serV="<button type='button' class='btn btn-purple btn-sm' style='height:28px;' onclick='onkeyUpPR(serarchShowUR,2);';>搜索 <i class='icon-search icon-on-right bigger-110'></i></button>";
	
	var ser = "<div style='float: left;'>"
			+ "<form class='form-search' action='javaScript:void(0);'>"
			+ "<span class='input-icon'> <input type='text' id='serarchShowUR'" 
			+ " placeholder='编号或者名称' class='nav-search-input'"
			+ " autocomplete='off'> <i"
			+ " class='icon-search nav-search-icon'></i>"
			+ "</span></form></div>"+serV;
	var select = "<div style='width: 100%;height: 100%;'>"
			+ "<div style='width: 100%; height: 5%;background-color: #F0F8FF;'>"
			+ ser
			+ "<input type='button' style='margin-left: 15%;' class='btn btn-xs' onclick='quanxuan(this,2);' value="
			+ msg
			+ " />&nbsp;&nbsp;"
			+ "<lable style='float: right;'><input type='button' class='btn btn-xs btn-info' onclick='queren(2);' value='确认'/></lable>"
			+ "</div>"
			+ "<div style='width: 50%; height:95%; overflow: auto;background-color: #F0F8FF;float: left;' class='form-control' id='selectedUR'></div>"
			+ "<div style='width: 50%; height: 95%; overflow: auto;background-color: #E6E6FA;float: right;' class='form-control' id='addUR'></div>"

			+ "</div>";
	var z = "<div id='zccRES' style='width: "
			+ prIsReade.width()
			+ "px; "
			+ "height: "
			+ $("#signupForm1").height()
			+ "px; left: "
			+ left
			+ "px; top: "
			+ top
			+ "px; z-index: 999;background-color: #C0C0C0;-moz-opacity: 1;opacity:1;filter: alpha(opacity=50);position:absolute;z-index:1200;'>"
			+ select + "</div>";
	$(z).appendTo(prIsReade);
	selectedUR();
}

function removeIR(obj) {
	// $("#zccRES").remove();
}

/* 搜索 */
function onkeyUpPR(obj, type) {
	//console.log(obj);
	var val = obj.value;
	if (type == 1) {
		if (val == "") {
			selectedISSRAN();
			return;
		}
		var charXfStore = $("#selectedISSRAN").children("label");
		// console.log(charXfStore);
		if (charXfStore != null && charXfStore.length > 0) {
			var array = [];
			for (var int = 0, len = charXfStore.length; int < len; int++) {
				var text = charXfStore[int].innerText;
				var con = text.indexOf(val) != -1;
				if (con) {
					// console.log(charXfStore[int]);
					array.push(charXfStore[int]);
				}
				if (int == len - 1) {
					if (array.length > 0) {
						$("#selectedISSRAN").empty();
						$(array).each(function(index, element) {
							// console.log(element);
							$(element).appendTo("#selectedISSRAN");
						});
					} else {
						selectedISSRAN();
					}
				}
			}
		}
	} else {
		if (val == "") {
			selectedUR();
			return;
		}
		var charXfStore = $("#selectedUR").children("label");
		// console.log(charXfStore);
		if (charXfStore != null && charXfStore.length > 0) {
			var array = [];
			for (var int = 0, len = charXfStore.length; int < len; int++) {
				var text = charXfStore[int].innerText;
				var con = text.indexOf(val) != -1;
				if (con) {
					// console.log(charXfStore[int]);
					array.push(charXfStore[int]);
				}
				if (int == len - 1) {
					if (array.length > 0) {
						$("#selectedUR").empty();
						$(array).each(function(index, element) {
							// console.log(element);
							$(element).appendTo("#selectedUR");
						});
					} else {
						selectedUR();
					}
				}
			}
		}
	}
}

/* 全选 */
function quanxuan(obj, type) {
	var val = obj.value;
	if (type == 1) {
		$("#issueRanges").empty();
		var charXfStore;
		if ("全选" == val) {
			charXfStore = $("#selectedISSRAN").children("label");
			$(obj).val("取消全选");
		} else {
			charXfStore = $("#addISSRAN").children("label");
			$(obj).val("全选");
		}

		$(charXfStore).each(function(index, element) {
			$(element).click();
		});
	} else {
		$("#useRanges").empty();
		var charXfStore;
		if ("全选" == val) {
			charXfStore = $("#selectedUR").children("label");
			$(obj).val("取消全选");
		} else {
			charXfStore = $("#addUR").children("label");
			$(obj).val("全选");
		}

		$(charXfStore).each(function(index, element) {
			$(element).click();
		});
	}
}

/* 确认选择 */
function queren(type) {
	if (type == 1) {
		$("#issueRanges").empty();
		var charXfStore = $("#addISSRAN").children("label");
		$(charXfStore).each(function(index, element) {
			$(element).appendTo("#issueRanges");
		});
		$("#zccRES").remove();
	} else {
		$("#useRanges").empty();
		var charXfStore = $("#addUR").children("label");
		$(charXfStore).each(function(index, element) {
			$(element).appendTo("#useRanges");
		});
		$("#zccRES").remove();
	}
}



/* 把选择的店铺添加到右边区域 发放 */
function issueRangesClick(obj) {
	var pr = $(obj).parent();
	if (obj.checked) {
		$(pr).appendTo("#addISSRAN");
	} else {
		$(pr).appendTo("#selectedISSRAN");
	}
}
/* 把选择的店铺添加到右边区域 使用 */
function useRangesClick(obj) {
	var pr = $(obj).parent();
	if (obj.checked) {
		$(pr).appendTo("#addUR");
	} else {
		$(pr).appendTo("#selectedUR");
	}
}

/* 发放范围 */
function selectedISSRAN() {
	
	var is = $("#selectedISSRAN");
	is.empty();
	$.get(
					"web/promotion/getStores",
					function(data) {
						var c = "";
						if (data != null && data.length > 0) {
							var charXfStore = $("#addISSRAN").children("label");
							//console.log(charXfStore);
							var issueRanges = $("#issueRanges").children(
									"label");
							// console.log(issueRanges);
							$(data).each(
							function(index, element) {
							var strNAMEXFS = (element.xfStorecode
							+ ":" + element.xfName);
							var bl = true;
							$(charXfStore)
							.each(
							function(ind,ele) {
							if (ele.innerText == strNAMEXFS) {
							bl = false;
							}
							});
							if (bl) {
								var str = "<label><input name='issueRanges' onclick='issueRangesClick(this);' class='ace ace-checkbox-2' type='checkbox' value="
										+ element.xfStorecode
										+ "><span class='lbl'>"
										+ strNAMEXFS
										+ "</span></label>";
								bl = false;
								$(issueRanges.find("input[name='issueRanges']:checked"))
										.each(
												function(
														indIR,
														elementIR) {
													//console.log($(elementIR).next().html());
													if ($(elementIR).next().html() == strNAMEXFS) {
														bl = true;
														return;
													}
												});
								if (bl) {
									$(str).click();
								} else {
									$(str).appendTo(is);
								}

							}
						});
						}
					});
}

/* 使用范围 */
function selectedUR() {
	var is = $("#selectedUR");
	is.empty();
	$
			.get(
					"web/promotion/getStores",
					function(data) {
						var c = "";
						if (data != null && data.length > 0) {
							var charXfStore = $("#addUR").children("label");
							var useRanges = $("#useRanges").children("label");
							// console.log(useRanges);
							$(data)
									.each(
											function(index, element) {
												var strNAMEXFS = (element.xfStorecode
														+ ":" + element.xfName);
												var bl = true;
												$(charXfStore)
														.each(
																function(ind,
																		ele) {
																	if (ele.innerText == strNAMEXFS) {
																		bl = false;
																	}
																});
												if (bl) {
													var str = "<label><input name='useRanges' onclick='useRangesClick(this);' class='ace ace-checkbox-2' type='checkbox' value="
															+ element.xfStorecode
															+ "><span class='lbl'>"
															+ strNAMEXFS
															+ "</span></label>";
													bl = false;
													$(useRanges.find("input[name='useRanges']:checked"))
															.each(
																	function(
																			indIR,
																			elementIR) {
																		if ($(elementIR).next().html() == strNAMEXFS) {
																			bl = true;
																			return;
																		}
																	});
													if (bl) {
														$(str).click();
													} else {
														$(str).appendTo(is);
													}

												}
											});
						}
					});
}