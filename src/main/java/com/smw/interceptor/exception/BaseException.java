package com.smw.interceptor.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.smw.common.util.ResponseCode;
import com.smw.interceptor.exception.OperationException;
/**
 * 异常控制，这便是异常细节可控，将来可用于支持国际化（异常信息国际化）
 * @author yumaochun
 *
 */
public class BaseException {
	
	/**
	 * OperationException:系统异常
	 *  
	 * @param ex               异常类型
	 * @param request          request
	 * @return
	 */
	@ExceptionHandler(OperationException.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView operationException(OperationException ex, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
        mv.addObject("code", ResponseCode.SYS_ERROR);//异常代码
        mv.addObject("msg", ex.getMessage());
        return mv;
	}

	
	/**
	 * IllegalArgumentException:传递非法参数异常
	 * 
	 * @param ex             异常类型
	 * @param request        request
	 * @return
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView illegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
        mv.addObject("code", ResponseCode.PARAM_ERROR);//异常代码
        mv.addObject("msg", "传递非法参数异常");
        return mv;
	}
	/**
	 * NumberFormatException:数字格式异常
	 * 
	 * @param ex             异常类型
	 * @param request        request
	 * @return
	 */
	@ExceptionHandler(NumberFormatException.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView numberFormatException(NumberFormatException ex, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
        mv.addObject("code", ResponseCode.FORMAT_ERROR);//异常代码
        mv.addObject("msg", "数字格式异常");
        return mv;
	}
	/**
	 * NullPointerException:空指针引用异常
	 * 
	 * @param ex             异常类型
	 * @param request        request
	 * @return 
	 */
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView nullPointerException (NullPointerException  ex, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
        mv.addObject("code", ResponseCode.NULL_ERROR);//异常代码
        mv.addObject("msg", "空指针引用异常");
        return mv;
	}
	 
	/**
	 * ClassCastException:类型强制转换异常
	 * 
	 * @param ex             异常类型
	 * @param request        request
	 * @return
	 */
	@ExceptionHandler(ClassCastException.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView classCastException (ClassCastException  ex, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
        mv.addObject("code", ResponseCode.NULL_ERROR);//异常代码
        mv.addObject("msg", "类型强制转换异常");
        return mv;
	}
	/**
	 * IllegalStateException:参数类型不匹配
	 * 
	 * @param ex             异常类型
	 * @param request        request
	 * @return
	 */
	@ExceptionHandler(IllegalStateException.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView illegalStateException (IllegalStateException  ex, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
        mv.addObject("code", ResponseCode.PARAM_TYPE_ERROR);//异常代码
        mv.addObject("msg", "参数类型不匹配");
        return mv;
	}
	
}
