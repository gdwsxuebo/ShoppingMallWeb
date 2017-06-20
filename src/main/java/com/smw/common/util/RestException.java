package com.smw.common.util;


public class RestException extends Exception {
	private static final long serialVersionUID = -5606786463396141230L;

	private Integer code;

	private String message;

	public RestException() {
	}

	public RestException(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
