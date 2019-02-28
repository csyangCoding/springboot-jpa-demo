package com.csy.springboot.aop;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.csy.springboot.exception.NoPermissionException;
import com.csy.springboot.exception.UnloginException;

import net.sf.json.JSONObject;

/**
 * Created by jack on 2018/3/3. 统一异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class); 

	@ExceptionHandler(value = UnloginException.class)
	public JSONObject loginErrorHandler(HttpServletRequest req, UnloginException e) throws Exception {
		logger.error(">>>>>loginErrorHandler>>>>>>error log:" + e.getMessage());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", UnloginException.getErrorCode());
		jsonObject.put("error log", e.getMsg());
		return jsonObject;
	}

	@ExceptionHandler(value = NoPermissionException.class)
	public JSONObject noPermissionErrorHandler(HttpServletRequest req, NoPermissionException e) throws Exception {
		logger.error(">>>>>noPermissionErrorHandler>>>>>>error log:" + e.getMessage() + ";currentUser:" + req.getSession().getId());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", NoPermissionException.getErrorCode());
		jsonObject.put("error log", e.getMsg());
		return jsonObject;
	}

	@ExceptionHandler(value = Exception.class)
	public JSONObject defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		logger.error(">>>>>defaultErrorHandler>>>>>>error log:" + e.getMessage());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("error log", e.getMessage());
		return jsonObject;
	}

}