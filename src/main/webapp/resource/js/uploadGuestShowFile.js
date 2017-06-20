function uploadFileClick(obj) {
	$("#uploadFile").click();
}
/* 显示并判断上传的文件是否合法 */
function changeShowFile(file) {  
	var val = $('input:radio[name="fileType"]:checked').val();
	//logo或者广告图片
	if (val == 0 || val==2) {
		var strSrc = file.value;
		// 验证上传文件格式是否正确
		var pos = strSrc.lastIndexOf(".");
		var lastname = strSrc.substring(pos, strSrc.length);
		//var strRegex = "(.jpg|.png|.gif|.ps|.jpeg|.ico)$"; // 用于验证图片扩展名的正则表达式
		var strRegex = "(.jpg)$"; // 用于验证图片扩展名的正则表达式
		var re = new RegExp(strRegex);
		if (!re.test(lastname.toLowerCase())) {
			//alert("您上传的文件类型为" + lastname + "，图片必须为 JPG、PNG、GIF、PS、JPEG、ico类型");
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
			alert("您上传的文件类型为" + lastname
					+ "，图片必须为 mp4类型");
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
function changShowFile(obj) {
	var wid = $("#dropzone").width();
	if (obj == 0) {
		$("#preview").find("#imageUrl").show();
		$("#preview").find("div").hide();
		$("#preview").find("#logoUrl").hide();
	}else if(obj==2){
		$("#preview").find("#imageUrl").hide();
		$("#preview").find("div").hide();
		$("#preview").find("#logoUrl").show();
	} else {
		$("#preview").find("img").hide();
		$("#preview").find("div").show();
		$("#preview").find("#logoUrl").hide();
	}
}
$(document).ready(function() {
	changShowFile(0);
	$("#preview").find("div").hide();
	$("#preview").find("#logoUrl").hide();
});
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

/* 显示并判断上传的文件是否合法 */
function changeFile(file) {
	var val = $('input:radio[name="fileType"]:checked').val();
	//logo或者广告图片
	    var falg=false;
		var strSrc = file.value;
		// 验证上传文件格式是否正确
		var pos = strSrc.lastIndexOf(".");
		var lastname = strSrc.substring(pos, strSrc.length)
		//var strRegex = "(.jpg|.png|.gif|.ps|.jpeg|.ico)$"; // 用于验证图片扩展名的正则表达式
		var strRegex = "(.jpg)$"; // 用于验证图片扩展名的正则表达式
		var re = new RegExp(strRegex);
		if (!re.test(lastname.toLowerCase())) {
			//alert("您上传的文件类型为" + lastname + "，图片必须为 JPG、PNG、GIF、PS、JPEG、ico类型");
			alert("您上传的文件类型为" + lastname + "，图片必须为 JPG类型");
			return false;
		}
		// 验证上传文件是否超出了大小
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
			alert(evt.target.result);
		} else {
			prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\''
					+ file.value + '\'"></div>';
			alert(file.value);
		}
		$("#submitFile").submit();
//////////////////////////////////////////////////////////////////////////////////////////
		var strSrc = file.value;
		// 验证上传文件格式是否正确
		var pos = strSrc.lastIndexOf(".");
		var lastname = strSrc.substring(pos, strSrc.length)
		//var strRegex = "(.AVI|.wma|.rmvb|.rm|.flash|.mp4|.mid|.3GP|.wmv)$"; // 用于验证视频扩展名的正则表达式
		var strRegex = "(.mp4)$"; // 用于验证视频扩展名的正则表达式
		var strImage="(.jpg)$";   // 用于验证图片扩展名的正则表达式
		var re = new RegExp(strRegex);
		var img=new RegExp(strImage);
		//验证上传的是否为视频
		if (re.test(lastname.toLowerCase())) {
			//给文件类型设值
			$('input:radio[name="fileType"]:checked').setValue(1);
			// 验证上传文件是否超出了大小
			if(file.files[0].size > 1048576000){
				alert("您上传的视频文件大小超出了100M限制！");
				return false;
			}
		}
		//验证上传的是否为图片
		else if(img.test(lastname.toLowCase())){
			$('input:radio[name="fileType"]:checked').setValue(0);
			if (file.files[0].size > 10485760) {
				alert("您上传的文件大小超出了10M限制！");
				return false;
			}
		}else{
			alert("您上传文件类型为"+lastname+"，图片必须为JPG格式，视频必须为MP4格式");
		}
		$("#submitFile").submit();
	

}