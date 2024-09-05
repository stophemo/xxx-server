package com.stp.xxx.config.exception;

import lombok.Getter;

@Getter
public enum ErrorCodeEnum {

    UNKNOWN_ERROR(-1, "未知错误"),

    // 通用错误码
    SUCCESS(200, "成功"),
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源未找到"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),

    // 用户相关错误码
    USER_NOT_FOUND(1001, "用户不存在"),
    USER_ALREADY_EXISTS(1002, "用户已存在"),
    USER_AUTHENTICATION_FAILED(1003, "用户认证失败"),

    // 数据库相关错误码
    DATABASE_ERROR(2001, "数据库操作异常"),
    DATA_NOT_FOUND(2002, "数据未找到"),
    DATA_DUPLICATION(2003, "数据重复"),

    // 文件相关错误码
    FILE_NOT_FOUND(3001, "文件未找到"),
    FILE_UPLOAD_FAILED(3002, "文件上传失败"),
    FILE_DOWNLOAD_FAILED(3003, "文件下载失败"),

    // 业务相关错误码
    OPERATION_FAILED(4001, "操作失败"),
    OPERATION_NOT_ALLOWED(4002, "操作不允许"),
    INVALID_OPERATION(4003, "无效操作");

    private final int code;
    private final String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("ErrorCodeEnum{code=%d, message='%s'}", code, message);
    }
}
