//添加到退货权限员工
function odbCAddXfStaff(obj) {
	$.post("web/returnGoodsAuthority/addReturnGoodsAuthority", {
		xfStaffCode : obj
	}, function(data) {
		if (data) {
			var a = $("#add" + obj).prop("outerHTML");
			var b = a.replace("odbCAddXfStaff('" + obj + "')",
					"odbCDelXfStaff('" + obj + "')");
			var c = b.replace("add" + obj, "del" + obj);
			$(c).appendTo("#yesSelectXfStaffs");
			$("#add" + obj).remove();
		} else {
			alert("设置退货权限失败！");
		}
	});
}
// 删除到退货权限员工
function odbCDelXfStaff(obj) {
	$.post("web/returnGoodsAuthority/delReturnGoodsAuthority", {
		xfStaffCode : obj
	}, function(data) {
		if (data) {
			var a = $("#del" + obj).prop("outerHTML");
			var b = a.replace("odbCDelXfStaff('" + obj + "')",
					"odbCAddXfStaff('" + obj + "')");
			var c = b.replace("del" + obj, "add" + obj);
			$(c).appendTo("#notSelectXfStaffs");
			$("#del" + obj).remove();
		} else {
			alert("设置退货权限失败！");
		}
	});
}

// 搜索员工
function searchXfStaff() {
	var searchXfCodeOrName = $("#searchXfCodeOrName").val();
	if (searchXfCodeOrName == "") {

	} else {
		var selectRGA = $("input[name='RGA']:checked").val();
		$.get("web/returnGoodsAuthority/searchXfStaff", {
			searchXfCodeOrName : searchXfCodeOrName,
			type : selectRGA
		}, function(data) {
			if (selectRGA == 0) {
				$("#notSelectXfStaffs").empty();
				$.each(data, function(index, obj) {
					var html = "<option id='add" + obj.xfStaffcode
							+ "' ondblclick=odbCAddXfStaff('" + obj.xfStaffcode
							+ "'); value='"+ obj.xfStaffcode+"'>"+obj.xfName+"("+(obj.xfIssuestore==null?"商场管理员":(obj.xfIssuestore.xfStorecode+":"+obj.xfIssuestore.xfName))+")</option>";
					$(html).appendTo("#notSelectXfStaffs");
				});
			} else {
				$("#yesSelectXfStaffs").empty();
				$.each(data, function(index, obj) {
					var html = "<option id='del" + obj.xfStaffcode
							+ "' ondblclick=odbCDelXfStaff('" + obj.xfStaffcode
							+ "'); value='"+ obj.xfStaffcode+"'>"+obj.xfName+"("+(obj.xfIssuestore==null?"商场管理员":(obj.xfIssuestore.xfStorecode+":"+obj.xfIssuestore.xfName))+")</option>";
					$(html).appendTo("#yesSelectXfStaffs");
				});
			}
		});
	}
}
//输入搜索
function onkUpSearchXfStaff(obj) {
	var searchXfCodeOrName = $("#searchXfCodeOrName").val();
	if(obj!=1){
		searchXfCodeOrName="";
	}
	//console.log(searchXfCodeOrName);
	if(searchXfCodeOrName==""){
		var selectRGA = $("input[name='RGA']:checked").val();
		if(obj!=1){
			if(selectRGA==1){
				selectRGA=0;
			}else{
				selectRGA=1;
			}
		}
		//alert(selectRGA);
		$.get("web/returnGoodsAuthority/searchXfStaff", {
			searchXfCodeOrName : searchXfCodeOrName,
			type : selectRGA
		}, function(data) {
			if (selectRGA == 0) {
				$("#notSelectXfStaffs").empty();
				$.each(data, function(index, obj) {
					var html = "<option id='add" + obj.xfStaffcode
							+ "' ondblclick=odbCAddXfStaff('" + obj.xfStaffcode
							+ "'); value='"+ obj.xfStaffcode+"'>"+obj.xfName+"("+(obj.xfIssuestore==null?"商场管理员":(obj.xfIssuestore.xfStorecode+":"+obj.xfIssuestore.xfName))+")</option>";
					$(html).appendTo("#notSelectXfStaffs");
				});
			} else {
				$("#yesSelectXfStaffs").empty();
				$.each(data, function(index, obj) {
					var html = "<option id='del" + obj.xfStaffcode
							+ "' ondblclick=odbCDelXfStaff('" + obj.xfStaffcode
							+ "'); value='"+ obj.xfStaffcode+"'>"+obj.xfName+"("+(obj.xfIssuestore==null?"商场管理员":(obj.xfIssuestore.xfStorecode+":"+obj.xfIssuestore.xfName))+")</option>";
					$(html).appendTo("#yesSelectXfStaffs");
				});
			}
		});
	}
}