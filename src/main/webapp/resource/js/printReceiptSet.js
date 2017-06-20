function uploadFileClick(obj) {
	$("#uploadFile").click();
}
/* 显示并判断上传的文件是否合法 */
function changeShowFile(file) { 
	var val = $('input:radio[name="fileType"]:checked').val();
	//logo或者广告图片
	if (val == 3 || val==4) {
		var strSrc = file.value;
		alert(strSrc);
		// 验证上传文件格式是否正确
		var pos = strSrc.lastIndexOf(".");
		var lastname = strSrc.substring(pos, strSrc.length);
		//var strRegex = "(.jpg|.png|.gif|.ps|.jpeg|.ico)$"; // 用于验证图片扩展名的正则表达式
		var strRegex = "(.jpg)$"; // 用于验证图片扩展名的正则表达式
		var re = new RegExp(strRegex);
		if (!re.test(lastname.toLowerCase())) {
			alert("您上传的文件类型为" + lastname + "，图片必须为 JPG类型");
			return false;
		}
		// 验证上传文件是否超出了大小6225750007404870
		if (file.files[0].size > 10485760) {
			alert("您上传的文件大小超出了10M限制！");
			return false;
		}
		var prevDiv = document.getElementById('preview');
		if (file.files && file.files[0]) {
			var reader = new FileReader();
			reader.onload = function(evt) {
				prevDiv.innerHTML = '<img src="' + evt.target.result + '" />';
			}
			reader.readAsDataURL(file.files[0]);
		} else {
			prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\''
					+ file.value + '\'"></div>';
			alert(file.value);
		}
		$("#submitFile").submit();
	}else {
		var strSrc = file.value;
		// 验证上传文件格式是否正确
		var pos = strSrc.lastIndexOf(".");
		var lastname = strSrc.substring(pos, strSrc.length)
		//var strRegex = "(.AVI|.wma|.rmvb|.rm|.flash|.mp4|.mid|.3GP|.wmv)$"; // 用于验证视频扩展名的正则表达式
		var strRegex = "(.mp4)$"; // 用于验证视频扩展名的正则表达式
		var re = new RegExp(strRegex);
		if (!re.test(lastname.toLowerCase())) {
			//alert("您上传的文件类型为" + lastname+ "，图片必须为 AVI、wma、rmvb、rm、flash、mp4、mid、3GP、wmv类型");
			alert("您上传的文件类型为" + lastname + "，图片必须为 mp4类型");
			return false;
		}
		// 验证上传文件是否超出了大小
		if (file.files[0].size > 1048576000) {
			alert("您上传的文件大小超出了100M限制！");
			return false;
		}
		$("#submitFile").submit();
	}

}

/* 显示相应的图片或者视频 */
function ShowFile(obj) {
	var wid = $("#dropzone").width();
	if (obj == 3) {
		$("#preview").find("#printLogo").show();
		$("#preview").find("#printEWM").hide();
	}else if(obj==4){
//		var widImg = $("#preview").find("img").width();
		$("#preview").find("#printLogo").hide();
		$("#preview").find("#printEWM").show();
	} 
}
/*$(document).ready(function() {
	ShowFile(3);
	$("#preview").find("#printLogo").hide();
});*/
//删除
function del(obj, event,type) {
	var btnNum = event.button;
	if (btnNum == 2) {
		if (confirm("确定删除吗？")) {
			$.post("web/setings/deleteFile", {
				fileType : type
			}, function(data) {
				if (data) {
					location.href="web/setings/getUploadGuestShowFile";
				} else {
					alert("删除失败！");
				}
			});
		}
	}

}

