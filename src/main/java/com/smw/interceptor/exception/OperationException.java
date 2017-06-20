package com.smw.interceptor.exception;


/**
 * 操作异常
 * @author yumaochun
 */
public class OperationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 返回异常说明，操作失败异常
	 * @param message
	 */
	public OperationException(String message) {
		super(message);
	}
}
