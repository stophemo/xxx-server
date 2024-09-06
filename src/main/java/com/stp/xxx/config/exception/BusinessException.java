package com.stp.xxx.config.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private Integer code;
    private String msg;

    // 默认构造函数
    public BusinessException() {
        super();
    }

    // 通过 ErrorCodeEnum 构造异常
    public BusinessException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMessage());
        this.code = errorCodeEnum.getCode();
        this.msg = errorCodeEnum.getMessage();
    }

    // 通过 ErrorCodeEnum 构造异常，并指定原因
    public BusinessException(ErrorCodeEnum errorCodeEnum, Throwable cause) {
        super(errorCodeEnum.getMessage(), cause);
        this.code = errorCodeEnum.getCode();
        this.msg = errorCodeEnum.getMessage();
    }

    // 自定义错误码和错误信息
    public BusinessException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    // 自定义错误码和错误信息，并指定原因
    public BusinessException(Integer code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(String msg) {
        super(msg);
        this.code = ErrorCodeEnum.UNKNOWN_ERROR.getCode(); // 默认错误码
        this.msg = msg;
    }

    public BusinessException(String msg, Throwable cause) {
        super(msg, cause);
        this.code = ErrorCodeEnum.UNKNOWN_ERROR.getCode(); // 默认错误码
    }

    public BusinessException(Exception e) {
        super(e.getMessage(), e.getCause());
        this.code = ErrorCodeEnum.UNKNOWN_ERROR.getCode(); // 默认错误码
    }
}

