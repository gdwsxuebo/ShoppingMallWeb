//商场
function editOrAdd() {
	$('#dialog1').dialog('open');
}
function chkForm() {
	var xfMallid = $("#xfMallid").val().trim();
	var xfMallname = $("#xfMallname").val().trim();
	if (xfMallid == "") {
		$("#xfMallid").focus();
		$("#xfMallid").attr("placeholder", "请输入商场编号");
	} else if (xfMallid.length > 15) {
		$("#xfMallid").focus();
		$("#xfMallid").val("");
		$("#xfMallid").attr("placeholder", "商场编号长度不能超过15");
	} else if (xfMallname == "") {
		$("#xfMallname").focus();
		$("#xfMallname").attr("placeholder", "请输入商场名称");
	} else if (xfMallname.length > 30) {
		$("#xfMallname").focus();
		$("#xfMallname").val("");
		$("#xfMallname").attr("placeholder", "商场名称长度不能超过30");
	} else {
		return true;
	}
	return false;
}
