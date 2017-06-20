function changHight(obj, type) {
	if (type == 1) {
		$(obj).height("250");
	} else {
		$(obj).height("100");
	}
}
function search() {
	$.get("web/promotion/getPromotionRules",function(data) {
		if (data != null && data.length > 0) {
			var pss = $("#pss");
			$(pss).empty();
			$(data).each(function(index, element) {
				var str = "<label><input name='pss' class='ace ace-checkbox-2' type='checkbox' value="
						+ element.id
						+ "><span class='lbl'>"
						+ element.title
						+ "</span></label><lable>&nbsp;</lable>";
				$(str).appendTo(pss);
			});
		}
	});
	$('#dialog1').dialog('open');
}
/* 搜索 促销规则  */
function serchPromotionRules() {
	var serchPromotionRulesTitle=$("#serchPromotionRulesTitle").val();
	$.get("web/promotion/getPromotionRules",{title:serchPromotionRulesTitle},function(data) {
		if (data != null && data.length > 0) {
			var pss = $("#pss");
			$(pss).empty();
			$(data).each(function(index, element) {
				var str = "<label><input name='pss' class='ace ace-checkbox-2' type='checkbox' value="
						+ element.id
						+ "><span class='lbl'>"
						+ element.title
						+ "</span></label><lable>&nbsp;</lable>";
				$(str).appendTo(pss);
			});
		}
	});
}