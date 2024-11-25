package com.stp.xxx.config.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.stp.xxx.config.result.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.text.StrUtil;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice(basePackages = "com.stp.xxx")
public class GlobalExceptionHandler {

@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultEntity<?> handleValidationException(MethodArgumentNotValidException e) {
        log.error("参数校验异常：", e);

        // 提取所有字段错误信息
        List<String> errorMessages = e.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        // 拼接错误信息为字符串，或直接返回错误列表
        String errorMessage = String.join("; ", errorMessages);
        return ResultEntity.error(ErrorCodeEnum.INVALID_PARAMETER.getCode(), errorMessage);
    }

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
