package com.smw.common.util;

import java.io.Serializable;

/**
 * 
 * Status:请求返回状态信息类
 *
 * @author yumaochun
 * @date 2016年3月3日 下午4:25:02
 */
public class Status implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2133800753538506666L;
	/**
	 * 状态编码
	 */
	private String code;
	/**
	 * 提示信息
	 */
	private String msg;
	/**
	 * 调试信息
	 */
	private String debugMsg;
	
	/**
	 * 当前您所调用的接口数据信息
	 */
	private String url;

	public String getCode() {
		return code;
	}

	public Status setCode(String code) {
		this.code = code; return this;
	}

	public String getMsg() {
		return msg;
	}

	public Status setMsg(String msg) {
		this.msg = msg; return this;
	}

	public String getDebugMsg() {
		return debugMsg;
	}

	public void setDebugMsg(String debugMsg) {
		this.debugMsg = debugMsg;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
