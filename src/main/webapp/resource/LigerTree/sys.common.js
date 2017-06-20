//定义系统安装路径
var baseUrl = "/";
var groupicon = "../../../lib/ligerUI/skins/icons/communication.png";
//全局变量
var plus = 0,
	rs_flag = true,	//防止多次提交
	
	//全局参数， 保存跳转的URL参数字符串
	url_param = {},
	
	//参数替换规则
	paramReplace = {};

//处理  ajax  session 过期
$(document).ajaxComplete(onSuccess); 
/**
 * function:ajax session 过期处理
 * res   设置全局ajax过期处理情况
 *       超时弹出登录框，登录
 */

function onSuccess(event, XMLHttpRequest, settings) {
	var resText=XMLHttpRequest.responseText; 
	if(resText!=null&&resText!=""){ 
	       var res=eval("(" + resText + ")"); 
	       if(res.status!=undefined){
	    	   if(res.status.code=="00000"){ 
	    		    $.ligerDialog.waitting("用户会话过期，需重新登录！");
	    		    setTimeout(function() {
	    				$.ligerDialog.closeWaitting();
	    				top.location.href="/";
	    			}, 500);
	    		   /* $.ligerDialog.alert("用户会话过期，需重新登录！", "提示信息", "warn", function(){
	    		    	//获取当前页面的地址	 
		    	      	var currentUrl=window.location.href;
		    	      	top.location.href="/"; 
	    		    });*/
	    	      	
	    	  } 
	       }   
	    } 
}

(function($){
	
	window.fsh = $.fsh = {
		
		//default setting
		options: {
			
			sysUrl : baseUrl,
			
			//设定系统数据显示条数
			page : function () {
				var winH = parseInt( ($(window).height()-185)/40 );
				if( winH > 40 ) winH=40;
				if( winH < 5 ) winH=5;
				if( typeof winH == "number" ) {
					return winH;
				}else{
					return 15;
				}
			}
			
		},
		
			
		/**
		创建表单验证提示框
		@return Null
		*/
		createFormPrompt: function(dom, con){
			var formPromptStr = "<div class=\""+dom.replace(".","")+"\"><div class=\"FormPromptPic\"></div><div class=\"FormPromptContent\">"+con+"</div></div>";
			$(document.body).append(formPromptStr);
		},
		
		/**
		点击提交按钮时验证表单
		@param fromId form的ID
		@return Boolean
		*/
		
		validateForSubmit : function(fromId){
			var $dom = $(fromId),
				_num_ = "1",
				_flag_ = true;
				
			$dom.find("input[validate][isSubValidate]").each(function(){
				var $this = $(this),
					validateStra = $this.attr("validate").split("||");
				if(!$.fsh._inputValidate($this.val(), validateStra[0])){
					$this.addClass("validateFailed");
					_num_ += "0";
				}
			});
			
			if(_num_.indexOf("0")>-1){_flag_ = false;}
			return _flag_;
		},
		/**
		 * 自动计算数字，金额 用逗号隔开
		 * number : 数字
		 * n:保留几位小数
		 */
		fmoney:function(s, n)   
		{   
		   n = n > 0 && n <= 20 ? n : 2;   
		   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
		   var l = s.split(".")[0].split("").reverse(),   
		   r = s.split(".")[1];   
		   t = "";   
		   for(i = 0; i < l.length; i ++ )   
		   {   
		      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
		   }   
		   return t.split("").reverse().join("") + "." + r;   
		},
		
		/**
		执行验证
		*/
		
		_inputValidate : function(str, rule){
			var validate_flag = true,
				ruleArr = rule.split(","),
				_num = "1";
				
			for(var vi=0, viLen=ruleArr.length; vi < viLen; vi++){
				var rules = ruleArr[vi];

				//EMAIL
				if(rules == "email") {
					var email_rules = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/i;
					if(!email_rules.test(str)) {_num += "0";}
				
				//日期
				}else if ( rules == "date"){
					var email_rules = /^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$/i;
					if(!email_rules.test(str)) { _num += "0";}
					
				//URL
				}else if ( rules == "url") {
					var email_rules = /^(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w \.-]*)*\/?$/i;
					if(!email_rules.test(str)) {_num += "0";}
				
				//IP
				}else if ( rules == "ip" ) {
					var email_rules = /^\S*(\.)?\S$/gi;
					if(!email_rules.test(str)){ _num += "0";}
		
				//身份证
				}else if (rules == "userId" ) {
					var email_rules = /\d{15}|\d{18}/i;
					if(!email_rules.test(str)) { _num += "0";}
				
				//密码
				}else if (rules == "password" ) {
					var email_rules = /^[a-z0-9_-]{6,18}$/i;
					if(!email_rules.test(str)) { _num += "0";}
					
				//不为空
				}else if (rules == "notempty" ) {
					var email_rules = /^.*$/;
					if(!email_rules.test(str)) { _num += "0";}
					
				//数字
				}else if (rules == "number" ) {
					var email_rules = /^\d*$/;
					if(!email_rules.test(str)) { _num += "0";}
				
				//整数或者小数
				}else if (rules == "numb" ) {
					var email_rules = /^-?[0-9]+(\.[0-9]+)?$/;
					if(!email_rules.test(str)) { _num += "0";}
				//经纬度
				}else if (rules == "jw" ) {
					var email_rules = /^-?(1[0-7]?[0-9]?|[0-9][0-9]|[0-9]|0)(\.\d{0,6})?$/;
					if(!email_rules.test(str)) { _num += "0";}
					
				//手机
				}else if (rules == "mobile" ) {
					var email_rules = /0?(13|14|15|18)[0-9]{9}/;
					if(!email_rules.test(str)) { _num += "0";}
					
				//座机传真
				}else if (rules == "phone" ) {
					var email_rules = /[0-9-()（）]{7,18}/;
					if(!email_rules.test(str)) { _num += "0";}
				
				//字母，数字，下划线
				}else if (rules == "english" ) {
					var email_rules = /^[a-zA-Z0-9_-]*$/;
					if(!email_rules.test(str)) { _num += "0";}
				
				//长度
				}else if(rules.substr(0,6) == "length"){
					var getLen = rules.replace("length","").split("_");
					if(str.length < parseInt(getLen[0]) || str.length > parseInt(getLen[1]) ){ _num += "0";}
					
				}else{
					if(!(new RegExp(rules ,"i")).test(str)) {_num += "0";}
				}
				
			}
			
			if(_num.indexOf("0")>-1){
				validate_flag = false;
			}
			return validate_flag;
		},
		
		
		/**
		输入验证
		@param fromId form的ID
		@return Null
		*/
		inputValidate : function(fromId){
			var $dom = $(fromId),
				titleInfo_prompt = "",	//提示信息
				titleInfo_validate = "",	//验证规则
				iv_flag = true,	//存放表单内容是否全部验证成功
				propmtClass = ".FormPrompt";
				
			$dom.find("input[validate]").each(function(){
				var $this = $(this);
				
				$this.hover(function(){
					titleInfo_prompt = $this.attr("validate").split("||")[1];
					if(titleInfo_prompt != "" ) $.fsh.createFormPrompt(propmtClass, titleInfo_prompt);
				},function(){
					$(propmtClass).remove();
					titleInfo_prompt = "";
				})
				
				.blur(function(){
					var thisValue = $this.val(),
						yyz = function(){
							iv_flag = $.fsh._inputValidate(thisValue, titleInfo_validate);
							if(!iv_flag){
								$this.addClass("validateFailed");
								$dom.find("#input_submit").attr("disabled", "disabled");
							}else{
								$this.removeClass("validateFailed");
								$dom.find("#input_submit").removeAttr("disabled");
							}	
						}
					
					if($this.attr("isSubValidate") == undefined && thisValue != ""){ yyz(); }
					//如果存在 isSubValidate 属性，当失去焦点时，内容为空也会验证
					if($this.attr("isSubValidate") != undefined){ yyz(); }
					
				})
				
				.focus(function(){
					titleInfo_validate = $this.attr("validate").split("||")[0];
					$this.removeClass("validateFailed");
					$dom.find("#input_submit").removeAttr("disabled");
				})
				
				.mousemove(function(e){
					$(propmtClass).css({top:e.pageY+20+"px",left:e.pageX-20+"px"});
				})
				
				.mouseout(function(){
					$(propmtClass).remove();
					titleInfo_prompt = "";
				});
			});
		},
		
		
		/**
		初始化表单
		@param formDom formID
		@param sucdialog	操作成功后，是否提示
		@return obj
		*/
		
		
		InitForm : function(formDom, sucdialog){
			var $dom = $(formDom),
				$urldom = $dom.find("input[name=gotoUrl]"),
				toUrl = $urldom.attr("value"),	//登陆成功后跳转地址
				target = $urldom.attr("target"),
				args = arguments[2],
				alertInfo = args!=undefined ? args : '数据提交中, 请稍候...';
				
			$.fsh.inputValidate(formDom);	//验证表单
			$dom.find("input[id=input_submit]").click(function(){
				if(toUrl=="_setting") { toUrl = $urldom.attr("value"); }  //此处仅用于添加用户跳转使用
				var _flag_ = $.fsh.validateForSubmit(formDom);
				if(_flag_){
					var manager = $.ligerDialog.waitting( alertInfo );
					$dom.ajaxSubmit(function(data){
						var d = typeof data=="object" ? data : $.parseJSON( data );
						if(typeof d.success == "boolean" && d.success==true){
							if(sucdialog){
								$.ligerDialog.success(d.message, "", function(){
									$.fsh.gotoUrl( !!d["return"] ? d["return"] : toUrl, target);
								});
							}else{
								$.fsh.gotoUrl( !!d["return"] ? d["return"] : toUrl, target);
							}
						}else{
							$.ligerDialog.error(d.message);
						}
						manager.close();
					});
				}
				return false;
			});
		},
		
		/**
		获取表单数据,返回url字符串形式
		@param formId
		@return string
		*/
		getFormData : function( formId ){
			var queryString = formId.formSerialize();
			return !!queryString ? queryString : "";
		},
		
		
		/**
		页面跳转
		@param url URL地址
		@return null
		*/
		gotoUrl : function(url, target){
			if(typeof url=='string'){
				if(!!target){
					if(target=="0") {
						top.location.href = url;
						return;	
					}
					top[target].location.href = url;
				}else{
					location.href=url;
				}
			}
		},
		
		/**
		ajax 获取数据
		@param url url请求地址
		*/
		getAjaxData : function( url ) {
			try{
				var _data = $.fsh.get_data( url ),
					con = null;
				if(_data) {
					con = typeof _data=="object" ? _data : $.parseJSON( _data );
				}
				return con;
			}catch(e) {
				return {};	
			}
		},
		
		
		/**
		获取原始数据
		@param url  url地址
		*/
		get_data : function( url ) {
			try{
				var con = null;
				$.ajax(
				{ 
					type: 'POST', url: url, dataType: "json",
					async: false,
					success: function (data) {
						if(data) con = data;
					} 
				});
			}catch(e){
				
			}
			return con || {};
			
		},
		
		
		/**
		*打印出时间
		*return null
		*/
		printTime : function() {
			var date = new Date(),
				pyear = date.getFullYear(),
				pmonth = date.getMonth() + 1,
				pdate = date.getDate(),
				pday = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")[date.getDay()];
			return "今天是:" + pyear + "年" + pmonth + "月" + pdate + "日 " + " " + pday;
		},
		
		/**
		Cookie操作
		@param name  cookie名称
		@param value	cookie值
		@param options	参数，设置过期时间,url地址等
		*/
		cookie : function(name, value, options){
			if (typeof value != 'undefined') {
				options = options || {};
				if (value === null) {
					value = '';
					options.expires = -1;
				}
				var expires = '';
				if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
					var date;
					if (typeof options.expires == 'number') {
						date = new Date();
						date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
					} else {
						date = options.expires;
					}
					expires = '; expires=' + date.toUTCString();
				}
				var path = options.path ? '; path=' + options.path : '';
				var domain = options.domain ? '; domain=' + options.domain : '';
				var secure = options.secure ? '; secure' : '';
				document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
			} else {
				var cookieValue = null;
				if (document.cookie && document.cookie != '') {
					var cookies = document.cookie.split(';');
					for (var i = 0; i < cookies.length; i++) {
						var cookie = $.trim(cookies[i]);
						if (cookie.substring(0, name.length + 1) == (name + '=')) {
							cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
							break;
						}
					}
				}
				return cookieValue;
			}
		},
		
		
		
		/**
		打印出头部信息
		@param options obj
		@return string
		*/
		topInfo : function ( options ) {
			var opt = options || {};
			var getBtnInfo = function(b){
				var btninfo = "";
				for(var i=0, len = b.button.length; i<len; i++){
					btninfo += "<li><a class=\""+ b.button[i].style +"\" href=\"javascript:;\" id="+ b.button[i].func +">"+ b.button[i].name +"</a></li>";
				}
				return btninfo;	
			};
			var	btn_info = getBtnInfo(opt),
				btn_show = "<div class=\"rbContents\">"+
							  "<div class=\"rbConInfo\">"+
								"<div class=\"opr_btn\">"+
								  "<ul>"+ btn_info +"</ul>"+
								"</div>"+
							  "</div>"+
							"</div>";
				/**
				_title = function(t) {
					var _tp = t.split("|"),
						_len = _tp.length,
						_i=0,
						_str="";
					for(; _i<_len; _i++) {
						var _bar = "";
						if(_i>0) {
							_bar = " &gt;&gt ";	
						}
						_str += "<li><a><span>"+_bar+_tp[_i]+"</span></a></li>"
					}
					return _str;
				},
				
				info = "<div class=\"rCtag\">" +
						  "<ul>" +
						    "<li><a><span>当前位置：</span></a></li>"+_title(options.title)+"</ul></div>";
				info += (btn_info=="") ? "" : btn_show;
				 */
			var info=btn_show;
			return info;
		},
		
		
		/**
		创建头部信息
		@param mode
		@param title
		@param discription
		@param btn obj
		@return null
		*/
		
		createHeader : function( mode, title, btn ){
			$("body").prepend("<div id='"+mode.replace("#","")+"'></div>");
			$(mode).html($.fsh.topInfo({
					title: title,
					button: btn
				})
			);
		},
		
		/**
		创建表格
		@param dom 容器
		@param columns 字段设置
		@param datafrom 数据来源
		@param pageSize 显示条数
		@param sortName 主键名称
		@param chckbox 是否显示checkbox
		@param multSelect 是否支持单选 默认为true
		@param _width  宽度
		@param _height 高度
		@return obj
		*/
		createTable : function(columns, datafrom, pageSize, sortName, checkbox, _multSelect, _width, _height, _dom, _container){
			var dom = !!_dom ? _dom : "userDataList",
				dataDom = $("div#"+dom);
			if(dataDom.length==0) {
				var container = !!_container ? _container: "body";
				$(container).append("<div id='"+dom+"' style='margin-left:4px; margin-top:10px; float:left;'></div>");
				dataDom = $("div#"+dom);
			}
			var multSelect = !!_multSelect ? _multSelect : false;
			multSelect=!multSelect;
			//初始化分页数组
			var showPageSize=[5,10,15,20,30,50];
			showPageSize=getShowPageSizeArr(showPageSize,pageSize);
			var grid = dataDom.ligerGrid({
				columns: columns,
				url: datafrom,
				pageSize: pageSize,
				sortName: sortName,
				sortOrder: "desc",
				width: (typeof _width == "undefined") ? '99%' : _width,
				height: (typeof _height == "undefined") ? '99%' : _height,
				checkbox: checkbox,
				isSingleCheck:multSelect,//是否支持单选，true-单选
				rowHeight: 40,
				root:'rows',
				record:'total',
				pageParmName:'currentPage',
				pagesizeParmName:'pageSize',
				sortnameParmName:'sortName',
				sortorderParmName:'sortOrder',
				rownumbers:true,
				headerRowHeight: 50,
				pageSizeOptions: showPageSize,
				enabledSort:true,
				onChangeSort:function(columnName, sortOrder){
					//grid.myChangeSort(columnName, sortOrder);
				},  
				onCheckRow: function(checked, rowdata, rowindex) {
					if( multSelect ) {
						for (var rowid in this.records){
							this.unselect(rowid);
						}
						checked ? this.select(rowindex) : this.unselect(rowindex);
					}
					//对选中数据进行参数组织
					url_param = $.fsh.dataFilter(paramReplace, rowdata);
				},
				
				//取消选择
				onUnSelectRow: function() {
					url_param = {};
				},
				
				onError : function() {
					$.ligerDialog.error('数据加载失败，请稍候再试！');
				},
				//如果总页数小于当前页数， 则跳转到第一页
				onAfterShowData : function() {
					var getPage = function() {
						var info = dataDom.liger(),
							infoAll = "";
						for(var d in info) {
							if(d=="options"){
								infoAll += info[d]["pageCount"] + "_";
								infoAll += info[d]["newPage"] + "_";
								break;
							}
						}
						return infoAll;
					},
					pn = getPage().split("_");
					if( parseInt(pn[0])< parseInt(pn[1]) ){
						var manager = dataDom.ligerGetGridManager();      
						manager.changePage("first");	
					}
				}
			});
			return grid;
		},
		
		
		/**
		* 处理过滤参数
		* @param reg 过滤参数对象
		* @param data 选中的数据对象
		* @return obj
		*/
		dataFilter: function(reg, data) {
			for(var r in reg) {
				url_param[r] = $.fsh.replaceStrByParam(reg[r], data);
			}
			return url_param;
		},
		
		/**
		* 替换字符串中指定的值
		* @param str 字符串
		* @obj 选中的行数据对象
		*/
		replaceStrByParam: function(str, obj) {
			var urlSplit = str.split("$"),
				len = urlSplit.length,
				arr = [], i=0;
			for( ; i<len; i++ ) {
				var ar = encodeURI(urlSplit[i]);
				arr.push( (i%2!=0) ? obj[ar] : ar );
			}
			return arr.join("");
		},
		
		
		/**
		 * URL问号后面的字符操作
		 * () 没有参数，直接返回保存search的对象
		 * ( str ) 直接返回查找值, 如果str中包含等号，直接返回对象
		 * ( str, url ) 返回在url中查询str的值
		 * ( 1 ) 如果参数为1 直接返回search字符串
		 */
		requestParam : function() {
			var obj = {}, o={},
				arg = arguments, fp=arg[0], sp=arg[1],
				len = arg.length,
				l = window.location,
				searchStr = (len==2 && typeof sp==="string") ?
						sp.substring(sp.indexOf("?")+1) : 
						l.search.substring(1);

			//返回search字符串
			if(len==1 && fp===1) return searchStr;
			
			//将search存到对象中
			var sArr = (( len==1 && typeof fp==="string" || len==0 ) ?
					searchStr :
					sp.substring(sp.indexOf("?")+1) || []).split("&");
			
			//将值保存在对象中[key=val]
			for(var i=0, _len=sArr.length; i<_len; i++){
				var items = sArr[i].split("="),
					name = (items[0]),
					val = (items[1]);
				obj[name] = encodeURI(val);
			}
			
			//返回查询值
			if(len==1 && typeof fp==="string" || len==2 && typeof fp==="string" && typeof sp==="string") {
				if(fp.indexOf("=")!=-1) {
					var _msp = fp.split("&"),
						jc, jclen, jcarr=[];
					for(jc=0, jclen=_msp.length; jc<jclen; jc++) {
						var jcc = _msp[jc].split("="),
							jco={};
						jco.name = decodeURIComponent(jcc[0]);
						jco.val = decodeURIComponent(jcc[1]);
						jcarr.push(jco);
					}
					return jcarr;
				} else {
					return obj[fp] || "";
				}
			}
			
			//添加对象的参数
			o.param = obj;
			o.len = i;
			o.realurl = l.href;
			o.protocol = l.protocol;
			o.host = l.host;
			o.hostname = l.hostname;
			o.port = l.port;
			o.pathname = l.pathname;
			o.search = searchStr;
			
			//返回对象
			return o;
			//*/
		},
 
 
		/**
		添加数据到表单
		@param form 表单id或者class
		@param data 数据
		*/
		addDataToForm : function ( form, data ) {
			$input = $(form);
			$input.each(function(){
				var $this = $(this),
					thisValue = $this.attr("id");
				$this.attr("value", data[0][thisValue]);
			});
		},
		
		
		/**
		判断系统是否登陆
		@return obj
		*/
		isLogin : function(){
			$.ajax({
				url:'/system/isLogin.do',
				method:'POST',
				dataType:'json',
				success:function(data){
					if(data.status.code!="10000") {
						top.location.href= baseUrl;
						return;
					}else{
						return data;
					}
				},
				error:function(data){
					alert("请求失败！");
				}
			});
			
		},
		/**
		 * ajax获取数据
		 * @param url
		 */
		getPostAjaxData:function(url){
			$.ajax({
				url:url,
				method:'POST',
				dataType:'json',
				success:function(data){
					return data;
				},
				error:function(data){
					alert("请求失败！");
				}
			});
		},
		
		/**
		btn Style
		@param
		*/
		btn_style : function () {
			$("div.btn_Mode input").hover(function(){$(this).addClass("btnsClass");},function(){$(this).removeClass("btnsClass");});
			$("input[readonly=readonly]").addClass("readonlyInput");
		},
		
		/**
		取消弹出框
		@param
		*/
		removeDialog : function () {
			var $top = $(top["rightContents"].document);
			$top.find("div[ligeruiid^=Dialog]:has(iframe)").remove();
			$top.find("div[class=l-window-mask]").hide();
		},
		
		/**
		添加或删除数组中的数据
		@param arr		数组
		@param arg		添加/删除的数据
		@param flag		判断是添加还是删除， false 删除
		@return Array
		*/
		mergeArr: function (arr, arg, flag) {
			var _arr = arr,
				_len = _arr.length,
				_flag = false;
			if(flag){
				_flag = $.fsh.isInArr(arr, arg);
				if(!_flag) {
					_arr.push( arg );
				}
			} else {
				//删除
				for(var i=0; i<_len; i++) {
					if(_arr[i] == arg) {
						_arr.splice(i, 1);
					}
				}
			}
			return _arr;
		},
		
		/**
		判断数据是否在数组中
		@param arr	数组
		@param d	数据
		@return Boolean
		*/
		isInArr: function(arr, d) {
			var _len = arr.length,
				_flag = false;
			if(_len == 0 || typeof arr != "array") {
				return _flag;
			}
			for(var i=0; i<_len; i++) {
				if(arr[i]==d) {
					_flag = true;
				}
				break;	
			}
			return _flag;
		},
		
		/**
		按钮事件判断
		@param obj 按钮数据对象
		*/
		btn_excute : function( obj ){
			var $dom = $("div#topInfo").find("a[id]");
			//console.log(obj);
			$dom.click(function(){
				var $this = $(this),
					this_id = this.id,
					_arr = obj || [];
				for(var i=0, len = _arr.length; i<len; i++){
					var o = _arr[i];
					if( this_id == o.func) {
						//if(o.type==0) {
							eval( o.func + "();" );	
						//} else {
						//	$.fsh.goToUrl_Param(o);
						//}
						break;
					}
				}
			});
			//*/
		},
		
		/**
		* 跳转到其它页面(包括参数)
		* @param obj 按钮的数据对象
		* @return null
		*/
		goToUrl_Param: function(o) {
			if($.isEmptyObject(url_param)) {
				$.ligerDialog.warn("<br/>请选中数据后，再操作 ！");
				return;
			}
			var _url = baseUrl;
			if(o.type==1) {
				_url += o.url + "?mid="+o.mid+"&pMid=" + o.nextValue + url_param[o.fun];
			} else if(o.type==2) {
				_url += o.url + url_param[o.fun];
			}
			$.fsh.gotoUrl(_url, "rightContents");
		},
		
		/**
		* 增删改除 执行文件
		* @param domId		表单的ID
		* @param list_url	数据显示地址
		* @param query_url	URL获取数据地址
		* @param query		url "?"后面的字段
		* @param dflag		判断是否为查询 0查询  1添加/修改
		* @param arrSearch	要查询的字段 Array
		//*/
		adud_excute: function(domId, list_url, query_url, query, dflag, arrSearch){
			
			//添加Action地址
			var _dom = $(domId);
			if(dflag==1) {
				_dom.attr("action", query_url + query);
			}
			$.fsh.inputValidate(domId);
			if(rs_flag) {
			//提交数据
			_dom.find("input[id=input_submit]").click(function(){
				var queryUrl = $.fsh.getFormData( _dom ),
					_flag = false;
				
				//添加验证
				_flag = $.fsh.validateForSubmit(domId);
				if(_flag) {
					dataUrl = query_url + query +"&" + encodeURI(queryUrl);
					
					//生成查询条件， 并显示数据
					var _query_search = "";
					for(var _qi=0, _qlen = arrSearch.length ; _qi < _qlen; _qi++){
						var _qmc = arrSearch[_qi] + "=" + $.fsh.requestParam(arrSearch[_qi], queryUrl);
						if(_qi < _qlen-1) _qmc += "&";
						_query_search += _qmc;
					}
					
					//传递表单数据
					if(dflag==1) {
						_dom.ajaxSubmit(function(data){
							var d = typeof data=="object" ? data : $.parseJSON( data );
							if(typeof d.success == "boolean" && d.success==true){
								$.fsh.gotoUrl(list_url + "&" + encodeURI(_query_search), "rightContents");
							}else{
								$.ligerDialog.error(d.message);	
							}
						});
					}else{
						grid = $.fsh.createTable( columns, 
												 query_url + "?method=search&" + encodeURI(_query_search), 
												 $.fsh.options.page(), "", true);
					}
					windialog.hidden();
				}
				return false;
			});
			rs_flag = false;
			}
			$.fsh.btn_style();
		},
		
		
		/**
		* 添加数据至弹出框
		* @param 对象
		*/
		addDataToPop: function( obj ) {
			$.fsh.addTagToBody(obj.dom);
			//create table
			return $.fsh.createTable(obj.columns, obj.url, obj.listNum, "", true, obj.isMult, obj.w, obj.h, "popWinCon"+plus);
		},
		
		/**
		* 移出弹出框
		*/
		removePopUp: function(dom) {
			$(dom).remove();
		},
		
		/**
		* 添加标签至body内
		*/
		addTagToBody: function(dom) {
			plus ++;
			var str= "<div class=\"popWin\">\n<div class=\"popWinContent\"><div id='popWinCon"+plus+"'></div>"+
					 "</div>\n<div class=\"popWinBtn\">\n<div class=\"btn_Mode\" style=\"margin:0;\">\n"+
					 "<input type=\"button\" class=\"btn_submitClass\" id=\"btnmode_close\" value=\" 关闭 \" />\n"+
					 "<input type=\"button\" class=\"btn_submitClass\" id=\"btnmode_add\" value=\" 添加 \" />\n"+
					 "<input type=\"button\" class=\"btn_submitClass\" id=\"btnmode_search\" value=\" 搜索 \" />\n</div>\n</div>\n</div>";
			$(dom).append(str);
		},
		
		/**
		 * 创建Title
		 * @param mid 菜单ID
		 * @param pMid 父菜单ID
		 * @param title 路径
		 * @param d		//传放数据
		 * return null
		*/
		createTitle: function( title, mid, pMid, d ) {
			var arg = arguments,
				len = arg.length;
			//data = (len==4) ? 
			//		arg[3] : 
			//		$.fsh.getAjaxData(baseUrl + "/gw_menu_tree/getMenuFuncsByMenuId.do?method=getRoleMethod&mid=" + mid +"&pMid="+pMid) || {};
			//$.fsh.createHeader("#topInfo", title,  data.data);
			//$.fsh.btn_excute( data.data );
		},
		/**
		 * 创建标题及菜单功能按钮
		 * @param menuId  菜单ID
		 * @param title   路径
		 * @param d		//传放数据
		 * return null
		*/
		createTitleAndFuncs: function( title, menuId, d ) {
			var arg = arguments,
				len = arg.length;
			data = (len==4) ? 
					arg[3] : 
					$.fsh.getAjaxData("/gw_menu_tree/getMenuFuncsByMenuId.do?menuId=" + menuId) || {};
			$.fsh.createHeader("#topInfo", title,  data.data);
			$.fsh.btn_excute( data.data );
		},
		
		/**
		* 判断是否选中
		* @param name 获取指定名称的数据
		@ return Object
		*/
		isSelected: function( name ){
			if(name){
				return 	grid.rowdata(name);
			}else{
				return grid.getSelectedRow();
			}
		},
		/**
		* 获取多选的值
		* @param name 获取指定名称的数据
		@ return Object
		*/
		getMuiltySelected: function(str){
			var idArr=new Array();
			var rows=grid.getSelectedRows();
			if(rows.length==0){
				$.ligerDialog.warn( str );
				return;
			}
			$.each(rows,function(i,n){
				idArr.push(n.id);
			});
			return idArr;
			
		},
		
		/**
		* 没有选中，操作按钮时的提示信息
		* @param str 提示字符串
		* return Object
		*/
		noteForNotSelected: function( str ){
			var _flag = $.fsh.isSelected();
			if(!_flag){
				$.ligerDialog.warn( str );
				return;
			}
			return _flag;
		},
		
		/**
		* 创建表单
		* @param p Object 表单参数
		* @return null
		*/
		createForm: function(p) {
			var str = "", tri, trlen, ii, ilen, dialog, o={},
				plugin=[];	//保存插件
			o.source = p;
			//设定外部DIV与FORM的外部样式与ID
			$.extend(p, {
				divClass: "create_div_dom"+plus,
				formid: "create_form_dom" + plus
			});
			//设置宽度
			p.width = (p.width != undefined) ? p.width : 260;
			plus ++;
			//创建外部DIV
			str += "<div class='"+p.divClass+"'>";
			//表单说明
			if(p.note != undefined) str += p.note;
			if(p.dataUrl==undefined) {
				//创建表单
				str += "<form ";
				if(p.formid != undefined) str += "id='"+p.formid+"' ";
				if(p.formaction != undefined) str += "action='"+p.formaction+"' ";
				if(p.formtarget != undefined) str += "target='"+p.formtarget+"' ";
				if(p.methodtype != undefined) str += "method='"+p.methodtype+"'";
				//创建表格
				str += ">\n<table ";
				str += "width='"+((p.tablewidth != undefined) ? p.tablewidth : "97.5%")+"'";
				str += " border='0' align='center' cellpadding='0' cellspacing='0'";
				str += " class='"+((p.tableClass != undefined) ? p.tableClass : "tableClass inputClass")+"'";
				str += ">";
				//创建TR
				for(tri=0, trlen=p.filds.length; tri<trlen; tri++) {
					var trItem = p.filds[tri];
					str += "<tr>";
					if(p.trWidth != 0) {
						str += "<td width='"+((p.trWidth!=undefined) ? p.trWidth : "25%")+"'>"+trItem.display+"</td>";
					}
					str += "<td style='padding-bottom:4px;'><ul class='component_styles' style='width:"+
					(p.trWidth==0 ? p.width+122 : p.width+22)+"px'>";
					//创建内容输入控件
					for(ii=0, ilen=trItem.input.length; ii<ilen; ii++) {
						var iItem = trItem.input[ii];
						//创建下拉框
						if(iItem.type=="select") {
							str += "<li style='padding-top:5px;'><select style='padding:3px;";
							str += "width:"+((iItem.wth != undefined) ? iItem.wth : (p.width+4))+"px; ";
							str += (iItem.h != undefined ? "height:"+iItem.h+"px;margin-top:5px; ": "");
							str += (iItem.p_class != undefined ? iItem.p_class: "")+"' ";
							if(iItem.name != undefined) str += "name='"+iItem.name+"' ";
							if(iItem.p_mult != undefined && iItem.p_mult) str += "multiple='multiple' ";
							if(iItem.id != undefined) str += "id='"+iItem.id+"' ";
							if(iItem.handler != undefined) str += "data-form-handler='"+iItem.handler+"' ";
							if(iItem.val != undefined) str += "value='"+iItem.val+"' ";
							if(iItem.funs != undefined) str += "data-form-funs='"+tri+"_"+ii+"' ";
							str += ">";
							for(var jm=0, jmlen=iItem.opt.length; jm<jmlen; jm++) {
								try{
									var optjm = iItem.opt[jm];
									str += "<option value='"+optjm.p_val+"'";
									if(optjm.p_select != undefined) str += " selected='selected'";
									str += ">"+optjm.display+"</option>";
								}catch(e){}
							}
							str += "</select></li>";
							if(iItem.tag!=undefined) str += "<span style='float:left;padding:4px 2px 0 3px;'>"+iItem.tag + "</span> ";
						//checkbox or radio
						} else if(iItem.type=="checkbox" || iItem.type=="radio") {
							str += "<li>";
							if( iItem.lable ) str += "<label id='"+iItem.name+"'style='float:left; padding-right:10px;'>";
							str += "<input "+(iItem.isSelected != undefined ? "checked": "")+" ";
							if(iItem.name != undefined) str += "name='"+iItem.name+"' ";
							if(iItem.id != undefined) str += "id='"+iItem.id+"' ";
							if(iItem.handler != undefined) str += "data-form-handler='"+iItem.handler+"' ";
							if(iItem.p_val != undefined) str += "value='"+iItem.p_val+"' ";
							if(iItem.funs != undefined) str += "data-form-funs='"+tri+"_"+ii+"' ";
							str += "type='"+iItem.type+"' name='"+iItem.name;
							str += "' style='float:left; margin-top:7px; margin-right:2px;'/>"+iItem.display;
							if( iItem.lable ) str += "</label>";
							str += "</li>";
						//textarea
						} else if(iItem.type=="textarea") {
							str += "<li><textarea ";
							if(iItem.id != undefined) str += "id='"+iItem.id+"' ";
							if(iItem.name != undefined) str += "name='"+iItem.name+"' ";
							if(iItem.handler != undefined) str += "data-form-handler='"+iItem.handler+"' ";
							if(iItem.funs != undefined) str += "data-form-funs='"+tri+"_"+ii+"' ";
							str += "style='width:"+p.width+"px; margin-top:5px; clear:both; padding:2px; height:"+
							(iItem.height != undefined ? iItem.height : 50)+"px;'></textarea></li>";
						//button
						} else if(iItem.type=="button") {
							str += "<li><input type='button' ";
							if(iItem.name != undefined) str += "name='"+iItem.name+"' ";
							if(iItem.id != undefined) str += "id='"+iItem.id+"' ";
							if(iItem.handler != undefined) str += "data-form-handler='"+iItem.handler+"' ";
							if(iItem.funs != undefined) str += "data-form-funs='"+tri+"_"+ii+"' ";
							str += "value='"+iItem.p_val+"' class='btn_submitClass' ";
							str += "style='margin-top:4px; font-size:12px; margin-right:6px;' /></li>";
						//input or other
						} else {
							str += "<li>";
							if(iItem.light) str += "<b class='red' style='float:right;'>　*</b>";
							str += "<div style='float:left; padding-top:5px;'><input style='width:"+p.width+"px;' type='"+iItem.type+"' ";
							if(iItem.name != undefined) str += "name='"+iItem.name+"' ";
							if(iItem.id != undefined) str += "id='"+iItem.id+"' ";
							if(iItem.validate != undefined) str += "validate='"+iItem.validate+"' ";
							if(iItem.handler != undefined) str += "data-form-handler='"+iItem.handler+"' ";
							if(iItem.funs != undefined) str += "data-form-funs='"+tri+"_"+ii+"' ";
							if(iItem.val != undefined) str += "value='"+iItem.val+"' ";
							if(iItem.isvalidate) str += "isSubValidate ";
							if(iItem.readOnly) str += "readonly='readonly' ";
							if(iItem.plugin) {
								str += "class='"+iItem.plugin+"_plugins_list' ";
								if(iItem.plugin=="data") {
									str += "width="+((p.width+2)/ilen)+" ";
								}
								plugin.push(iItem.plugin);
							}
							str += "/></div></li>";
							if(iItem.plugin == "data" && ii==0 && ilen>1 ) {
								str += "<span style='float:left;padding:0 4px 0 5px;'>至</span>";
							}
						}
					}
					str += "</ul></td></tr>";
				}
				str += "</table>";
			} else {
				p.dataContainer  = "data_grid_list_d_"+plus;
				str += "<div style='float:left;margin-left:2px;' id="+p.dataContainer+"></div>";
			}
			str += "<div class='btn_Mode'>";
			
			//添加按钮
			if(p.showCancel!=false) str += "<input type='button' class='btn_submitClass' value='关 闭' data-handler='click' data-funs='close_win'>";
			for(var bi=0, blen=p.button.length; bi<blen; bi++) {
				try{
					var pn = p.button[bi];
					str += "<input type='"+pn.btype+"' ";
					if(pn.name != undefined) str += "name='"+pn.name+"' ";
					str += "class='"+((pn.btnclass != undefined) ? pn.btnclass : "btn_submitClass")+"' ";
					if(pn.id != undefined) str += "id='"+pn.id+"' ";
					if(pn.val != undefined) str += "value='"+pn.val+"' ";
					if(pn.handler != undefined) str += "data-handler='"+pn.handler+"' ";
					if(pn.funs != undefined) str += "data-funs='"+((typeof pn.funs == "function") ? bi: pn.funs)+"' ";
					str += "/>";
				} catch(e){}
			}
			str += "</div>";
			if(p.dataUrl==undefined) str += "</form>";
			str += "</div>";
			$("body").append(str);
			
			//获取添加的容器
			var divDoms = $("div."+p.divClass);
			
			//显示边框
			p.showBorder = (p.showBorder==undefined) ? true : p.showBorder;
			if(p.showBorder) {
				try{
					dialog = $.ligerDialog.open({ target: divDoms, title: p.title, width:(p.width+160), 
						height:(p.dataUrl ? 486 : null), isHidden:false});
					o.dialog = dialog;
					o.gridBase = grid;
				} catch(e) {} 
			}

			//按钮绑定事件
			divDoms.find("input[data-handler]").each(function(){
				var t = $(this);
					handler = t.attr("data-handler"),
					funs = t.attr("data-funs"),
					type = t.attr("type");
					query = "";
				if(funs=="close_win" && type!="submit") {
					t.on(handler, function(){
						dialog.close();	
					});
				} else if(type!="submit") {
					t.on(handler, function(){
						return (function(t, o) {
							return p.button[parseInt(t.attr("data-funs"))].funs(o);
						})(t, o);
					});
				}
			});
			
			//此处为表单时，功能模块操作
			if(p.dataUrl!=undefined) {
				var _ms = (typeof p.multSelect != undefined) ? p.multSelect: false;
				o.grid = $.fsh.createTable( p.columns, p.dataUrl, 9, "", true, _ms, (p.width+140), 360, p.dataContainer);
			} else {

				//插件添加功能
				for(var plj=0, pljlen=plugin.length; plj<pljlen; plj++) {
					var pljItem  = plugin[plj];
					if(pljItem=="data"){
						var kdoml = $("input.data_plugins_list");
						kdoml.ligerDateEditor( { format: "yyyy-MM-dd", width: kdoml.attr("width")/*添加文字'至'*/-(pljlen>1 ? 10 : 0)});
					}
				}

				//输入框绑定事件
				divDoms.find("*[data-form-handler]").each(function() {
					var t = $(this),
						handler = t.attr("data-form-handler"),
						index = t.attr("data-form-funs").split("_");
					t.on(handler, function(e) {
						return p.filds[parseInt(index[0])].input[parseInt(index[1])].funs(e, o);
					});
				});
	
				//表单提交事件
				divDoms.find("form#"+p.formid).on("submit", function(e) {
					var dom_formk = $(this),
						formSerilize = $.fsh.getFormData(dom_formk);
					//设置表单数据
					o.fData = $.fsh.requestParam(formSerilize);
					//判断提交方式
					if(p.methodtype=="get") {
						p.button[parseInt(funs)].funs(o);
					} else if((p.methodtype=="post")) {
						var _flag_ = $.fsh.validateForSubmit("#"+p.formid),
							btnIndex = parseInt(dom_formk.find("input[type=submit]").attr("data-funs"));
						if(_flag_){
							dom_formk.ajaxSubmit(function(data){
								var d = typeof data=="object" ? data : $.parseJSON(data);
								if(typeof d.success == "boolean" && d.success==true){
									var toUrlDom = $.trim($("input[name=goToUrl]").val());
									if(toUrlDom!="") {
										$.fsh.gotoUrl(toUrlDom, "rightContents");
									}
									if($.type(btnIndex)=="number" && btnIndex>=0) {
										p.button[btnIndex].funs(o);
									}
									if(dialog) dialog.close();
								}else{
									$.ligerDialog.error(d.message);
								}
							});
						}
					}
					return false;
				});

				//添加表单验证
				$.fsh.inputValidate("#"+p.formid);
			}
			$.fsh.btn_style();
			return o;
		},
		
		
		/**
		* 删除数据
		* @param url String url字符串
		* @param grid Object 生成表格对象
		* @return Boolean 是否删除成功
		*/
		deleteSelectedRow: function(url, grid, msg) {
			var flag = true,
				re_info;
			$.ligerDialog.confirm("确定要删除选中数据？", function( r ){
				if(r){
					re_info = $.fsh.getAjaxData( url );
					if(re_info.status.code =="10000"){
						//grid.deleteSelectedRow();
						grid.reload();
						$.ligerDialog.success(re_info.status.msg+"!");
					}else{
						$.ligerDialog.error(re_info.status.msg+"!");
						flag = false;
					}
				}
			});
			return flag;
		},
		/**
		* 操作选择数据
		* @param url String url字符串
		* @param grid Object 生成表格对象
		* @return Boolean 是否删除成功
		*/
		oprSelectedRow: function(url, grid, msg) {
			var flag = true,
				re_info;
			$.ligerDialog.confirm(msg, function( r ){
				if(r){
					re_info = $.fsh.getAjaxData( url );
					if(re_info.status.code =="10000"){
						grid.reload();
						$.ligerDialog.success(re_info.status.msg+"!");
					}else{
						$.ligerDialog.error(re_info.status.msg+"!");
						flag = false;
					}
				}
			});
			return flag;
		},
		
		startSelectedRow: function(url, grid, msg) {
			var flag = true,
				re_info;
			$.ligerDialog.confirm("确定要启用选中数据？", function( r ){
				if(r){
					re_info = $.fsh.getAjaxData( url );
					if(re_info.status.code =="10000"){
						grid.reload();
						$.ligerDialog.success(re_info.status.msg+"!");
					}else{
						$.ligerDialog.error(re_info.status.msg+"!");
						flag = false;
					}
				}
			});
			return flag;
		},
		
		stopSelectedRow: function(url, grid, msg) {
			var flag = true,
				re_info;
			$.ligerDialog.confirm("确定要禁用选中数据？", function( r ){
				if(r){
					re_info = $.fsh.getAjaxData( url );
					if(re_info.status.code =="10000"){
						grid.reload();
						$.ligerDialog.success(re_info.status.msg+"!");
					}else{
						$.ligerDialog.error(re_info.status.msg+"!");
						flag = false;
					}
				}
			});
			return flag;
		},
		
		confirmSelectedRow: function(url, grid, msg) {
			var flag = true,
				re_info;
			$.ligerDialog.confirm("确定要确认选中数据？", function( r ){
				if(r){
					re_info = $.fsh.getAjaxData( url );
					if(re_info.status.code =="10000"){
						grid.reload();
						$.ligerDialog.success(re_info.status.msg+"!");
					}else{
						$.ligerDialog.error(re_info.status.msg+"!");
						flag = false;
					}
				}
			});
			return flag;
		},
		
		confirmSelectedRows: function(url, grid, rows) {
			var flag = true,
			re_info;
			var rowss=grid.getSelectedRows();
			$.ligerDialog.confirm("确定要确认选中数据？", function(r){
				if(r){
					re_info = $.fsh.getAjaxData( url );
					if(re_info.status.code =="10000"){
						$.ligerDialog.success(re_info.status.msg+"!");
						grid.reload();
					}else{
						$.ligerDialog.error(re_info.status.msg+"!");
						flag = false;
					}
				}
			});
			return flag;
		},
		
		failureSelectedRow: function(url, grid, msg) {
			var flag = true,
				re_info;
			$.ligerDialog.confirm("确定要失效选中单元？", function( r ){
				if(r){
					re_info = $.fsh.getAjaxData( url );
					if(re_info.status.code =="10000"){
						grid.reload();
						$.ligerDialog.success(re_info.status.msg+"!");
					}else{
						$.ligerDialog.error(re_info.status.msg+"!");
						flag = false;
					}
				}
			});
			return flag;
		},
		
		unConfirmSelectedRows: function(url, grid, rows) {
			var flag = true,
			re_info;
			var rowss=grid.getSelectedRows();
			$.ligerDialog.confirm("确定要取消确认选中数据？", function( r ){
				if(r){
					re_info = $.fsh.getAjaxData( url );
					if(re_info.status.code =="10000"){
						$.ligerDialog.success(re_info.status.msg+"!");
						grid.reload();
					}else{
						$.ligerDialog.error(re_info.status.msg+"!");
						flag = false;
					}
				}
			});
			return flag;
		},
		unConfirmSelectedRow: function(url, grid, msg) {
			var flag = true,
				re_info;
			$.ligerDialog.confirm("确定要取消确认选中数据？", function( r ){
				if(r){
					re_info = $.fsh.getAjaxData( url );
					if(re_info.status.code =="10000"){
						grid.reload();
						$.ligerDialog.success(re_info.status.msg+"!");
					}else{
						$.ligerDialog.error(re_info.status.msg+"!");
						flag = false;
					}
				}
			});
			return flag;
		},
		bohuiSuccSelectedRow: function(url, grid, msg) {
			var flag = true,
				re_info;
			$.ligerDialog.confirm("确定要驳回此合同吗？", function( r ){
				if(r){
					re_info = $.fsh.getAjaxData( url );
					if(re_info.status.code=="10000"){
						grid.reload();
						$.ligerDialog.success(re_info.status.msg+"!");
					}else{
						$.ligerDialog.error(re_info.status.msg+"!");
						flag = false;
					}
				}
			});
			return flag;
		},
		shenheSelectedRow: function(url, grid, msg) {
			var flag = true,
				re_info;
			$.ligerDialog.confirm("确定要确认此合同吗？", function( r ){
				if(r){
					re_info = $.fsh.getAjaxData( url );
					if(re_info.status.code=="10000"){
						grid.reload();;
						$.ligerDialog.success(re_info.status.msg+"!");
					}else{
						$.ligerDialog.error(re_info.status.msg+"!");
						flag = false;
					}
				}
			});
			return flag;
		},
		unfirmSelectedRow: function(url, grid, msg) {
			var flag = true,
				re_info;
			$.ligerDialog.confirm("确定要取消确认此合同吗？", function( r ){
				if(r){
					re_info = $.fsh.getAjaxData( url );
					if(re_info.status.code=="10000"){
						grid.reload();
						$.ligerDialog.success(re_info.status.msg+"!");
					}else{
						$.ligerDialog.error(re_info.status.msg+"!");
						flag = false;
					}
				}
			});
			return flag;
		},
		confirmSettlementPayment: function(url, grid, msg) {
			var flag = true,
				re_info;
			$.ligerDialog.confirm("确定要确认此结算付款信息吗？", function( r ){
				if(r){
					re_info = $.fsh.getAjaxData( url );
					if(re_info.status.code=="10000"){
						grid.reload();
						$.ligerDialog.success(re_info.status.msg+"!");
					}else{
						$.ligerDialog.error(re_info.status.msg+"!");
						flag = false;
					}
				}
			});
			return flag;
		},
		unconfirmSettlementPayment: function(url, grid, msg) {
			var flag = true,
				re_info;
			$.ligerDialog.confirm("确定要取消确认此结算付款信息吗？", function( r ){
				if(r){
					re_info = $.fsh.getAjaxData( url );
					if(re_info.status.code=="10000"){
						grid.reload();
						$.ligerDialog.success(re_info.status.msg+"!");
					}else{
						$.ligerDialog.error(re_info.status.msg+"!");
						flag = false;
					}
				}
			});
			return flag;
		},
		confirmSurchargeMoney: function(url, grid, msg) {
			var flag = true,
				re_info;
			$.ligerDialog.confirm("确定要确认此滞纳金吗？", function( r ){
				if(r){
					re_info = $.fsh.getAjaxData( url );
					if(re_info.status.code=="10000"){
						grid.reload();
						$.ligerDialog.success(re_info.status.msg+"!");
					}else{
						$.ligerDialog.error(re_info.status.msg+"!");
						flag = false;
					}
				}
			});
			return flag;
		},
		unconfirmSurchargeMoney: function(url, grid, msg) {
			var flag = true,
				re_info;
			$.ligerDialog.confirm("确定要取消确认此滞纳金吗？", function( r ){
				if(r){
					re_info = $.fsh.getAjaxData( url );
					if(re_info.status.code=="10000"){
						grid.reload();
						$.ligerDialog.success(re_info.status.msg+"!");
					}else{
						$.ligerDialog.error(re_info.status.msg+"!");
						flag = false;
					}
				}
			});
			return flag;
		},
		AuditSellentSelectedRow: function(url, grid, msg) {
			var flag = true,
				re_info;
			$.ligerDialog.confirm("确定要确认此结算单吗？", function( r ){
				if(r){
					re_info = $.fsh.getAjaxData( url );
					if(re_info.status.code=="10000"){
						grid.reload();
						$.ligerDialog.success(re_info.status.msg+"!");
					}else{
						$.ligerDialog.error(re_info.status.msg+"!");
						flag = false;
					}
				}
			});
			return flag;
		},
		unconfirmSettlementMain: function(url, grid, msg) {
			var flag = true,
				re_info;
			$.ligerDialog.confirm("确定要取消确认此结算单吗？", function( r ){
				if(r){
					re_info = $.fsh.getAjaxData( url );
					if(re_info.status.code=="10000"){
						grid.reload();
						$.ligerDialog.success(re_info.status.msg+"!");
					}else{
						$.ligerDialog.error(re_info.status.msg+"!");
						flag = false;
					}
				}
			});
			return flag;
		},
		
		/**
		* 删除数据
		* @param url String url字符串
		* @param grid Object 生成表格对象
		* @return Boolean 是否删除成功
		*/
		deleteSelectedRow2: function(url, grid, user) {
			var flag = true,
				re_info;
			$.ligerDialog.confirm("<br/>你确定要对 "+user+" 帐户销户吗？", function( r ){
				if(r){
					re_info = $.fsh.getAjaxData( url );
					if(re_info.success && typeof re_info.success=="boolean"){
						grid.deleteSelectedRow();
						$.ligerDialog.success("<br/>"+user+" 帐户已销户成功！");
					}else{
						$.ligerDialog.error("<br/>删除数据失败，请稍后再试....");
						flag = false;
					}
				}
			});
			return flag;
		},
		
		/**
		* 查询，显示数据
		* @param o Object 创建表单时返回对象
		* @param col Object 数据生成表格的列
		* @param url String URL字符串
		* @return 数据生成对象
		*/
		searchShow: function(o, col, url) {
			var sUrl = url + $.fsh.getFormData( $("form#"+o.source.formid) ),
				grid;
			grid = $.fsh.createTable(col, encodeURI(sUrl), $.fsh.options.page(), "", true);
			o.dialog.close();
			return grid;
		},
		/**
		* 判断对象是否为空
		* @param obj Object 传入对象
		* @return Boolean
		*/
		isEmpty: function(o) {
			var _item;
			for(_item in o) {
				return false;	
			}
			return true;
		},
		
		/**
		* 判断两个数组是否相等
		* @param arra Array
		* @param arrb Array
		* @return Boolean
		*/
		arrEqual: function(oa, ob) {
			var funs = function(o) {
				var arr = [], i, len;
				for(i=0,len=o.length; i<len; i++) {
					arr.push(o[i].id);	
				}
				return arr;
			}
			return (funs(oa).sort().toString()==funs(ob).sort().toString());
		},
		
		/**
		* 弹出框
		* @param Object 生成对话框的参数
		* @return Object 当前生成的对话框
		*/
		popWinD: function(o) {
			var dn = o.dval || 0,	//默认显示第几个标签
				tagBtnList = function() {
					//取得Tag的值
					var tl = "", i=0,
						len=o.tags.length;
					for(;i<len; i++) {
						var items = o.tags[i];
						tl += "<li><a href='javascript:;'";
						if(dn==i) {
							tl += " class='selected'";
						} else {
							if(items.handler!=undefined) {
								if($.type(items.funs)=="string") {
									tl += " on" + items.handler+"="+ items.funs;	
								} else {
									tl += " data-handler="+items.handler+" data-funs=tags_"+i;
								}
							}
						}
						if(items.id!=undefined) tl += " id='"+items.id+"'";
						tl += ">"+items.display+"</a></li>";
					}
					return tl;
				},
				inputList = function() {
					var tl = "", i=0,
						len = o.btnArr.length;
					for(; i<len; i++){
						var items = o.btnArr[i];
						tl += "<input class='btn_submitClass' type="+items.type;
						if (items.id!=undefined) tl += " id="+items.id;
						if(items.name!=undefined) tl += " name="+items.name;
						if(items.display!=undefined) tl += " value="+items.display;
						if(items.handler!=undefined) {
							if($.type(items.funs)=="string") {
								tl += (" on" + items.handler+"="+ items.funs);	
							} else {
								tl += " data-handler="+items.handler+" data-funs=btnArr_"+i;
							}
						}
						tl += " />";
					}
					return tl;
				},
				lbtn = function() {
					var tl = "", i=0,
						len = o.btn.length;
					for(;i<len; i++) {
						var items = o.btn[i];
						tl += "<li><a href='javascript:;'";
						if(items.id!=undefined) tl += " id="+items.id;
						if(items.handler!=undefined) {
							if($.type(items.funs)=="string") {
								tl += " on" + items.handler+"="+ items.funs;	
							} else {
								tl += " data-handler="+items.handler+" data-funs=btn_"+i;
							}
						}
						tl += ">"+items.display+"</a></li>";
					}
					return tl;
				},
				str="",	thisDom;
			
			str += "<div id='showUserPower'><div class='c_content'><div class='cc_title'><ul>"+ tagBtnList();
			str += "</ul><h2>"+o.powerTitle+"</h2></div><div class='cc_left'>";
			str += "<div class='ccl_tree'";
			if(o.btn.length==0) str += " style='height:328px;'";
			str += ">";
			str += (o.treeDom!=undefined) ? o.treeDom : "<ul class='ccl_tree_kmc'></ul>";
			str += "</div>";
			if(o.btn.length!=0) str += "<div class='ccl_btn'><ul>"+ lbtn() +"</ul></div>";
			str += "</div><div class='cc_right'><ul></ul></div></div><div class='btn_Mode' style='margin:0;'>";
			str += "<form method='post'";
			if(o.formaction!=undefined) str += " action="+o.formaction;
			str += " >" + inputList() + "</form></div></div>";
			
			thisDom = $(str);
			
			//添加事件
			thisDom.find("*[data-handler]").each(function() {
				var t = $(this),
					handler = t.attr("data-handler"),
					fs = t.attr("data-funs").split("_"),
					funs = o[fs[0]][fs[1]].funs;
				if(handler!="submit") {
					t.on(handler, funs);
				}
			});
			
			//表单提交事件
			var formDoms = thisDom.find("form:first"),
				submitBtn, spba, s, sflag=true;
			formDoms.on("submit", function(){
				
				//提交前事件
				submitBtn = formDoms.find("input[type=submit]");
				spba = submitBtn.attr("data-funs");
				if(spba!=undefined) {
					s = spba.split("_");
					sflag = o[s[0]][s[1]].funs();
				}
				
				if(sflag) {
					formDoms.ajaxSubmit(function(data){
						var d = typeof data=="object" ? data : $.parseJSON( data );
						if(d.success) {
							$.ligerDialog.success("数据保存成功！");
							if(windialog) windialog.close();
						} else {
							$.ligerDialog.error("数据保存失败，请稍后再试！");	
						}
					});	
				}
				return false;
			});
			
			//添加内容至Container
			thisDom.appendTo(o.to);
			
			//返回
			return thisDom;
		},
		
		/**
		* 关闭popWinD函数所创建的对话框
		* @param containerDom Object 对话框容器
		* @param windialog Object 弹出对话框对象
		* @param saveflag Boolean 是否需要保存
		* @return void
		*/
		closeDialogs: function(containerDom, windialog, saveflag) {
			if(saveflag) {
				$.ligerDialog.confirm("数据未保存，是否要保存？", function(r){
					if(r) {
						containerDom.find("form:first").ajaxSubmit(function(data){
							var d = typeof data=="object" ? data : $.parseJSON( data );
							if(d.success) {
								windialog.close();	
							} else {
								$.ligerDialog.error("数据保存失败，请稍后再试！");	
							}
						});
					} else {
						windialog.close();
					}
				});	
			} else {
				windialog.close();
			}
		},

		
		/**
		* 添加或者修改数据
		* @param sData	Object  选择的数据
		* @param grid	Object  生成表格返回对象
		* @param rData	Object  替换对象
		* @param fData	Object  表单数据
		* @param flagh  Boolean 添加(1), 修改(2)
		* @return void
		*/
		excData: function(sData, grid, rData, fData, flag) {
			var o={}, items, i, len,
				fDataArr = fData.fData;
			
			//从表单中获取数据， 并组织到JSON中
			for(items in rData) {
				for(i=0, len=fDataArr.length; i<len; i++) {
					var it = fDataArr[i];
					if(it.name == items) {
						o[rData[items]] = it.val;
					}
				}
			}
			
			//更新（添加）数据
			if(flag==2) {
				grid.updateRow(sData, o);
			}
		},
		gridCruidRows: function(infoMsg,url, grid, msg) {
			var flag = true,
				re_info;
			$.ligerDialog.confirm(infoMsg, function( r ){
				if(r){
					re_info = $.fsh.getAjaxData( url );
					if(re_info.status.code =="10000"){
						grid.loadData();
						$.ligerDialog.success(re_info.status.msg+"!");
					}else{
						$.ligerDialog.error(re_info.status.msg+"!");
						flag = false;
					}
				}
			});
			return flag;
		}
	};	
})(jQuery);
//获得分页显示的页面数组
function getShowPageSizeArr(showPageSize,pageSize){
	var newPageSizeArr=new Array();
	$.each(showPageSize,function(i,n){
		if(pageSize<n&&pageSize>showPageSize[i-1]){
			newPageSizeArr[newPageSizeArr.length]=pageSize;
			newPageSizeArr[newPageSizeArr.length]=n;
		}
		else if(pageSize<n){
			newPageSizeArr[newPageSizeArr.length]=n;
		}
	});
	return newPageSizeArr;
}