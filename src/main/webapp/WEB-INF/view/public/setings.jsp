<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="ace-settings-container" id="ace-settings-container">
	<div
		class="btn btn-app btn-xs btn-warning ace-settings-btn ${sessionScope.setingIsShow=='show'?'open':''}"
		id="ace-settings-btn" onclick="setingIsShow(this);">
		<i class="icon-cog bigger-150"></i>
	</div>

	<div
		class="ace-settings-box ${sessionScope.setingIsShow=='show'?'open':''}"
		id="ace-settings-box">
		<div>
			<div class="pull-left">
				<select id="skin-colorpicker" class="hide">
					<option data-skin="default" value="#438EB9">#438EB9</option>
					<option data-skin="skin-1" value="#222A2D">#222A2D</option>
					<option data-skin="skin-2" value="#C6487E">#C6487E</option>
					<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
				</select>
			</div>
			<span>&nbsp; 选择皮肤</span>
		</div>

		<div>
			<input type="checkbox" class="ace ace-checkbox-2"
				id="ace-settings-navbar" onclick="gdtht(this);" /> <label
				class="lbl" for="ace-settings-navbar"> 固定导航条</label>
		</div>

		<div>
			<input type="checkbox" class="ace ace-checkbox-2"
				id="ace-settings-sidebar" onclick="gdhdt(this);" /> <label
				class="lbl" for="ace-settings-sidebar"> 固定滑动条</label>
		</div>

		<div>
			<input type="checkbox" class="ace ace-checkbox-2"
				id="ace-settings-breadcrumbs" onclick="gdmbx(this);" /> <label
				class="lbl" for="ace-settings-breadcrumbs">固定面包屑</label>
		</div>

		<div>
			<input type="checkbox" class="ace ace-checkbox-2"
				id="ace-settings-rtl" onclick="qhdzb(this);" /> <label class="lbl"
				for="ace-settings-rtl">切换到左边</label>
		</div>

		<div>
			<input type="checkbox" class="ace ace-checkbox-2"
				id="ace-settings-add-container" onclick="qhzp(this);" /> <label
				class="lbl" for="ace-settings-add-container"> 切换窄屏 <b></b>
			</label>
		</div>
	</div>
</div>

