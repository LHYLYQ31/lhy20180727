/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.ums.common.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import net.sf.json.JSONObject;

/**
 * <B>系统名称：ums</B><BR>
 * <B>模块名称：aop 统一打印日志请求</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年7月10日
 */
@Aspect
@Component
public class HttpAspect {
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(* com.sinosoft.ums.modules.*.controller.*.*(..))")
    public void log() {
    }

    /**
     * 
     * <B>方法名称：beforeLog</B><BR>
     * <B>概要说明：所有controller的方法执行前执行</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年7月10日 上午11:02:32 void
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attribute = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest requerst = attribute.getRequest();
        //url
        logger.info("url: {}", requerst.getRequestURL());
        //method
        logger.info("method: {}", requerst.getMethod());
        //类+方法
        logger.info("class_method: {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature()
                .getName());
        //argue
        logger.info("class_method: {}", new JSONObject().put("param", joinPoint.getArgs()).toString());
    }

    /**
     * 
     * <B>方法名称：afterLog</B><BR>
     * <B>概要说明：所有controller的方法执行后执行</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年7月10日 上午11:04:27 void
     */
    @AfterReturning(value = "log()", returning = "result")
    public void doAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("this method " + methodName + " end.");
    }

    @AfterReturning(pointcut = "log()", returning = "obj")
    public void doAfterReturning(Object obj) {
        logger.info("resposes: {}", obj.toString());
    }

    /**
     * 异常通知（方法发生异常执行的代码）
     * 可以访问到异常对象；且可以指定在出现特定异常时执行的代码
     * 
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "log()", throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        logger.error("this method " + methodName + " end.ex message<" + ex.getStackTrace() + ">");
    }
}
