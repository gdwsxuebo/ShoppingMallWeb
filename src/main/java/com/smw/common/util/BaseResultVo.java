package com.smw.common.util;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;
/**
 * Description:json返回数据基本格式
 * 
 * @author yumaochun
 *
 */
public class BaseResultVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 657131477209360917L;
	
	/**
	 * 返回数据
	 */
	private Object data;
	/**
	 * 提示信息
	 */
	private Status status;

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	/**
	 * 
	 * ResponseInfo:ajax返回提示信息
	 *
	 * @date 2016年3月7日
	 * @param code         提示代码
	 * @param msg          提示信息
	 */
	public static BaseResultVo responseInfo(String code,String msg){
		BaseResultVo baseResultVo=new BaseResultVo();
		Status status=new Status();
		//提示代码
		status.setCode(code);
		//提示信息
		status.setMsg(msg);
		baseResultVo.setStatus(status);
		return baseResultVo;
	}
	/**
	 * 
	 * responseSuccess:返回操作成功的提示
	 *
	 * @date 2016年3月7日
	 * @param msg                 提示信息
	 */
	public static BaseResultVo responseSuccess(String msg){
		BaseResultVo baseResultVo=new BaseResultVo();
		Status status=new Status();
		//提示代码
		status.setCode(ResponseCode.OPR_SUCCESS);
		//提示信息
		status.setMsg(msg);
		baseResultVo.setStatus(status);
		return baseResultVo;
	}
	/**
	 * 
	 * responseFail:返回操作失败的提示
	 *
	 * @date 2016年3月7日 
	 * @param msg               提示信息
	 */
	public static BaseResultVo responseFail(String msg){
		BaseResultVo baseResultVo=new BaseResultVo();
		Status status=new Status();
		//提示代码
		status.setCode(ResponseCode.OPR_FAIL);
		//提示信息
		status.setMsg(msg);
		baseResultVo.setStatus(status);
		return baseResultVo;
	}
	
}
