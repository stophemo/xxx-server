package com.stp.xxx.config.exception;

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
    public ResultEntity<?> businessException(BusinessException e) {
        log.error("业务异常：", e);
        return ResultEntity.error(e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultEntity<?> exception(Exception e) {
        log.error("未知异常：", e);
        return ResultEntity.error(ErrorCodeEnum.UNKNOWN_ERROR.getCode(), e.getMessage(), e.getCause());
    }
}