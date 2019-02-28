package com.csy.springboot.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.MDC;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Aspect
@Component
public class LogAspect {
	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class); 
	private static final List<Long> timeTreadLocal = new ArrayList<Long>(1);

    @Pointcut("execution(* com.csy.springboot.controller..*(..))")
    public void log() {  
    }  
    
    @Before("log()")  
    public void before(JoinPoint joinPoint) {  
    	MDC.put("requestId", "csy111");
    	timeTreadLocal.add(System.currentTimeMillis());  
        // 接收到请求，记录请求内容  
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();  
        //获取请求的request  
        HttpServletRequest request = attributes.getRequest();  
        //获取所有请求的参数，封装为map对象  
        // Map<String,Object> parameterMap = getParameterMap(request);  
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();  
        //获取被拦截的方法  
        Method method = methodSignature.getMethod();  
        //获取被拦截的方法名  
        String methodName = method.getName();  
        logger.info("AOP begin ,请求开始方法  :{}", method.getDeclaringClass() + "." + methodName + "()");
        //获取所有请求参数key和value  
        String keyValue = getReqParameter(request);  
        logger.info("请求url = {}" , request.getRequestURL().toString());  
        logger.info("请求方法requestMethod = {}" , request.getMethod());  
        logger.info("请求资源uri = {}" , request.getRequestURI());  
        logger.info("所有的请求参数 key：value = {}" , keyValue);  
    }  
  
  
    @After("log()")  
    public void after() {  
        logger.info("aop的after()方法");  
    }  
  
    //controller请求结束返回时调用  
    @AfterReturning(returning = "result", pointcut = "log()")  
    public Object afterReturn(Object result) {  
        logger.info("AOP afterReturn,返回值result = {}" , result.toString());  
        // System.out.println("返回值="+result.toString());  
        long startTime = timeTreadLocal.get(0);  
        double callTime = (System.currentTimeMillis() - startTime) / 1000.0;  
        logger.info("调用controller花费时间time = {}s" , callTime);  
        timeTreadLocal.remove(0);
        MDC.remove("requestId");
        return result;  
    }  
  
    /** 
     * 获取所有请求参数，封装为map对象 
     * 
     * @return 
     */  
    public Map<String, Object> getParameterMap(HttpServletRequest request) {  
        if (request == null) {  
            return null;  
        }  
        Enumeration<String> enumeration = request.getParameterNames();  
        Map<String, Object> parameterMap = new HashMap<String, Object>();  
        StringBuilder stringBuilder = new StringBuilder();  
        while (enumeration.hasMoreElements()) {  
            String key = enumeration.nextElement();  
            String value = request.getParameter(key);  
            String keyValue = key + " : " + value + " ; ";  
            stringBuilder.append(keyValue);  
            parameterMap.put(key, value);  
        }  
        return parameterMap;  
    }  
  
    public String getReqParameter(HttpServletRequest request) {  
        if (request == null) {  
            return null;  
        }  
        Enumeration<String> enumeration = request.getParameterNames();  
        //StringBuilder stringBuilder = new StringBuilder();  
        JSONArray jsonArray = new JSONArray();  
        while (enumeration.hasMoreElements()) {  
            String key = enumeration.nextElement();  
            String value = request.getParameter(key);  
            JSONObject json = new JSONObject();  
            //String keyValue = key+" : " +value+" ; ";  
            json.put(key, value);  
            //stringBuilder.append(keyValue);  
            jsonArray.add(json);  
        }  
        //JSONObject jsonObject = new JSONObject();  
        //jsonObject.put("请求参数为：",jsonArray.toString());  
        return jsonArray.toString();  
    }  
}
