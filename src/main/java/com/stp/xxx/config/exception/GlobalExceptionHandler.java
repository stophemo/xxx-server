package com.stp.xxx.config.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.stp.xxx.config.result.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "com.stp.xxx")
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResultEntity<?> handleBusinessException(BusinessException e) {
        log.error("业务异常：", e);
        return ResultEntity.error(e);
    }

    @ExceptionHandler(NotLoginException.class)
    @ResponseBody
    public ResultEntity<?> handleNotLoginException(NotLoginException e) {
        log.error("未登录：", e);
        return ResultEntity.error(ErrorCodeEnum.USER_TOKEN_EXPIRED.getCode(), ErrorCodeEnum.USER_TOKEN_EXPIRED.getMessage(), e.getCause());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultEntity<?> handleUnknownException(Exception e) {
        log.error("未知异常：", e);
        return ResultEntity.error(ErrorCodeEnum.UNKNOWN_ERROR.getCode(), e.getMessage(), e.getCause());
    }
}
