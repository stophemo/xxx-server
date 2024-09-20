package com.stp.xxx.config.aop;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.StopWatch;
import cn.hutool.core.util.StrUtil;
import com.stp.xxx.constant.SysContant;
import com.stp.xxx.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ApiLoggingAspect {

    private final HttpServletRequest request;

    public ApiLoggingAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Around("execution(* com.stp.xxx.api..*(..)) || execution(* org.springdoc.webmvc.api..*(..))")
    public Object logRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        String classMethod = getClassMethod(joinPoint);
        String requestUrl = request.getRequestURL().toString();
        String method = request.getMethod();
        String userIp = request.getRemoteAddr();
        Object[] args = joinPoint.getArgs();
        String params = Arrays.toString(args);
        String userInfo = StrUtil.format("{}({})", "unknown", userIp);
        User user = null;
        try {
            // 执行目标方法
            Object result = joinPoint.proceed();
            stopWatch.stop();
            long duration = stopWatch.getLastTaskTimeMillis();

            if (StpUtil.isLogin()) {
                user = (User) StpUtil.getSession().get(SysContant.USER_INFO);
            }
            userInfo = StrUtil.format("{}({})", user == null ? "unknown" : user.getName(), userIp);

            // 记录成功请求日志
            log.info(StrUtil.format(
                    """
                            Request Details:
                            URL            : {}
                            Method         : {}
                            User           : {}
                            Class.Method   : {}
                            Parameters     : {}
                            Duration       : {} ms""",
                    requestUrl, method, userInfo, classMethod, params, duration
            ));
            return result;
        } catch (Exception e) {
            stopWatch.stop();
            long duration = stopWatch.getLastTaskTimeMillis();

            // 捕获异常并记录
            log.error(StrUtil.format(
                    """
                            Request Error :
                            URL           : {}
                            Method        : {}
                            User          : {}
                            Class.Method  : {}
                            Parameters    : {}
                            Duration      : {} ms
                            Exception     : {}""",
                    requestUrl, method, userInfo, classMethod, params, duration, e.getMessage()
            ), e);
            throw e;
        }
    }

    private String getClassMethod(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        return className + "." + methodName;
    }
}