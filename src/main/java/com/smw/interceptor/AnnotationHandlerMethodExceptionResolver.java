package com.smw.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.smw.common.util.BaseResultVo;
import com.smw.common.util.ResponseCode;
import com.smw.common.util.Status;  
  
/** 
 * 不必在Controller中对异常进行处理，抛出即可，由此异常解析器统一控制。<br> 
 * ajax请求（有@ResponseBody的Controller）发生错误，输出JSON。<br> 
 * 页面请求（无@ResponseBody的Controller）发生错误，输出错误页面。<br> 
 * 需要与AnnotationMethodHandlerAdapter使用同一个messageConverters<br> 
 * Controller中需要有专门处理异常的方法。 
 * 
 * 
 * */  
public class AnnotationHandlerMethodExceptionResolver extends ExceptionHandlerExceptionResolver {  
	
	//日志
	//private Logger logger=LoggerFactory.getLogger(AnnotationHandlerMethodExceptionResolver.class);
	private Logger logger=LoggerFactory.getLogger("操作异常日志信息");
  
    private String defaultErrorView;  
  
    public String getDefaultErrorView() {  
        return defaultErrorView;  
    }  
  
    public void setDefaultErrorView(String defaultErrorView) {  
        this.defaultErrorView = defaultErrorView;  
    }  
  
    protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod, Exception exception) {  
  
        if (handlerMethod == null) {  
        	//可以自由处理各种异常逻辑  
            if (exception instanceof org.springframework.web.HttpRequestMethodNotSupportedException) {  
            	ModelAndView mv = new ModelAndView();
                mv.addObject("code", ResponseCode.METHOD_TYPE_ERROR);//异常代码
                mv.addObject("msg", "接口调用类型不匹配(post,get)");
                try {
					return handleResponseBody(mv, request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
            }
            return new ModelAndView(); 
        }  
  
        Method method = handlerMethod.getMethod();  
  
        if (method == null) {  
            return null;  
        }  
        //如果定义了ExceptionHandler则返回相应的Map中的数据  
        ModelAndView returnValue = super.doResolveHandlerMethodException(request, response, handlerMethod, exception);  
        ResponseBody responseBodyAnn = AnnotationUtils.findAnnotation(method, ResponseBody.class);  
        if (responseBodyAnn != null) {  
            try {  
                ResponseStatus responseStatusAnn = AnnotationUtils.findAnnotation(method, ResponseStatus.class);  
                if (responseStatusAnn != null) {  
                    HttpStatus responseStatus = responseStatusAnn.value();  
                    String reason = responseStatusAnn.reason();  
                    if (!StringUtils.hasText(reason)) {  
                        response.setStatus(responseStatus.value());  
                    } else {  
                        try {  
                            response.sendError(responseStatus.value(), reason);  
                        } catch (IOException e) { }  
                    }  
                }  
                //系统代码编号重复
                if(exception instanceof org.springframework.dao.DuplicateKeyException){
                	//异常信息类
                	response.setStatus(200); 
                    BaseResultVo res = new BaseResultVo();  
                    Status status=new Status();
                    status.setCode(ResponseCode.OPR_FAIL);
                    status.setMsg("系统编号重复，操作失败！");
                    res.setStatus(status);
                    //logger.info("系统异常："+exception.getMessage());
                    logger.info("系统异常："+exception.getLocalizedMessage());
                    handleResponseError(res, request, response);  
                    return new ModelAndView();  
                }
                //外键操作异常
                if(exception instanceof org.springframework.dao.DataIntegrityViolationException){
                	//异常信息类
                	response.setStatus(200); 
                	BaseResultVo res = new BaseResultVo();  
                	Status status=new Status();
                	status.setCode(ResponseCode.OPR_FAIL);
                	status.setMsg("数据库操作错误！");
                	res.setStatus(status);
                	//logger.info("系统异常："+exception.getMessage());
                	logger.info("系统异常："+exception.getLocalizedMessage());
                	handleResponseError(res, request, response);  
                	return new ModelAndView();  
                }
                /*if(exception instanceof LigerDataException){
                	//异常信息类
                	response.setStatus(200); 
                	BaseResultVo res = new BaseResultVo();  
                	Status status=new Status();
                	status.setCode(ResponseCode.OPR_FAIL);
                	status.setMsg(exception.getMessage());
                	res.setStatus(status);
                	//logger.info("系统异常："+exception.getMessage());
                	logger.info("系统异常："+exception.getLocalizedMessage());
                	handleResponseError(res, request, response);  
                	return new ModelAndView();  
                }*/
                // 如果没有ExceptionHandler注解那么returnValue就为空  
                if (returnValue == null) {  
                	//异常信息类
                	response.setStatus(200); 
                	//异常信息类
                    BaseResultVo res = new BaseResultVo();  
                    Status status=new Status();
                    status.setCode(ResponseCode.SYS_ERROR);
                    status.setMsg("系统异常，请稍后再试！");
                    logger.info("系统异常："+exception.getLocalizedMessage());
                    handleResponseError(res, request, response);  
                    return new ModelAndView();  
                }  
                return handleResponseBody(returnValue, request, response);  
            } catch (Exception e) {  
                return null;  
            }  
        }  
  
        if( null == returnValue) {  
            returnValue = new ModelAndView();  
            if (null == returnValue.getViewName()) {  
                returnValue.setViewName(defaultErrorView);  
            }  
        }  
        return returnValue;  
    }  
  
    /**
     * 处理ResponseBody返回的json数据
     * 
     * @param returnValue
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })  
    private ModelAndView handleResponseBody(ModelAndView returnValue, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        Map value = returnValue.getModelMap();  
        HttpInputMessage inputMessage = new ServletServerHttpRequest(request);  
        List<MediaType> acceptedMediaTypes = inputMessage.getHeaders().getAccept();  
        if (acceptedMediaTypes.isEmpty()) {  
            acceptedMediaTypes = Collections.singletonList(MediaType.ALL);  
        }  
        MediaType.sortByQualityValue(acceptedMediaTypes);  
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);  
        Class<?> returnValueType = value.getClass();  
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();  
        if (messageConverters != null) {  
            for (MediaType acceptedMediaType : acceptedMediaTypes) {  
                for (HttpMessageConverter messageConverter : messageConverters) {  
                    if (messageConverter.canWrite(returnValueType, acceptedMediaType)) {  
                        messageConverter.write(value, acceptedMediaType, outputMessage);  
                        return new ModelAndView();  
                    }  
                }  
            }  
        }  
        if (logger.isWarnEnabled()) {  
            logger.warn("Could not find HttpMessageConverter that supports return type [" + returnValueType + "] and " + acceptedMediaTypes);  
        }  
        return null;  
    }  
    @SuppressWarnings({ "unchecked", "rawtypes" })  
    private ModelAndView handleResponseError(BaseResultVo returnValue, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        HttpInputMessage inputMessage = new ServletServerHttpRequest(request);  
        List<MediaType> acceptedMediaTypes = inputMessage.getHeaders().getAccept();  
        if (acceptedMediaTypes.isEmpty()) {  
            acceptedMediaTypes = Collections.singletonList(MediaType.ALL);  
        }  
        MediaType.sortByQualityValue(acceptedMediaTypes);  
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);  
        Class<?> returnValueType = returnValue.getClass();  
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();  
        if (messageConverters != null) {  
            for (MediaType acceptedMediaType : acceptedMediaTypes) {  
                for (HttpMessageConverter messageConverter : messageConverters) {  
                    if (messageConverter.canWrite(returnValueType, acceptedMediaType)) {  
                        messageConverter.write(returnValue, acceptedMediaType, outputMessage);  
                        return new ModelAndView();  
                    }  
                }  
            }  
        }  
        if (logger.isWarnEnabled()) {  
            logger.warn("Could not find HttpMessageConverter that supports return type [" + returnValueType + "] and " + acceptedMediaTypes);  
        }  
        return null;  
    }  
  
}  