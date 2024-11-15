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
    USER_PERMISSION_DENIED(1004, "用户权限不足"),
    USER_ACCOUNT_LOCKED(1005, "用户账户已锁定"),
    USER_PASSWORD_INCORRECT(1006, "用户密码错误"),
    USER_EMAIL_ALREADY_REGISTERED(1007, "该邮箱已被注册"),
    USER_PHONE_ALREADY_REGISTERED(1008, "该手机号已被注册"),
    USER_SESSION_EXPIRED(1009, "用户会话已过期"),
    USER_ACCOUNT_DISABLED(1010, "用户账户已被禁用"),
    USER_VERIFICATION_CODE_INVALID(1011, "验证码无效"),
    USER_VERIFICATION_CODE_EXPIRED(1012, "验证码已过期"),
    USER_UNAUTHORIZED_OPERATION(1013, "用户未经授权的操作"),
    USER_PROFILE_INCOMPLETE(1014, "用户资料不完整"),
    USER_PASSWORD_TOO_WEAK(1015, "用户密码强度不足"),
    USER_PASSWORD_MISMATCH(1016, "用户密码不匹配"),
    USER_INVALID_TOKEN(1017, "无效的用户令牌"),
    USER_TOKEN_EXPIRED(1018, "用户令牌已过期"),
    USER_EMAIL_NOT_VERIFIED(1019, "用户邮箱未验证"),
    USER_PHONE_NOT_VERIFIED(1020, "用户手机未验证"),
    USER_SESSION_NOT_ACCESS(1021, "用户信息未获取到"),

    // 数据库相关错误码
    DATABASE_ERROR(2001, "数据库操作异常"),
    DATA_NOT_FOUND(2002, "数据未找到"),
    DATA_DUPLICATION(2003, "数据重复"),
    DATA_INSERT_FAILED(2004, "数据插入失败"),
    DATA_UPDATE_FAILED(2005, "数据更新失败"),
    DATA_DELETE_FAILED(2006, "数据删除失败"),
    DATA_CONFLICT(2007, "数据冲突"),
    DATA_INTEGRITY_VIOLATION(2008, "数据完整性违规"),
    DATA_ACCESS_DENIED(2009, "数据库访问被拒绝"),
    DATA_QUERY_TIMEOUT(2010, "数据库查询超时"),
    DATA_TRANSACTION_FAILED(2011, "数据库事务失败"),
    DATA_CONNECTION_LOST(2012, "数据库连接丢失"),
    DATA_CONSTRAINT_VIOLATION(2013, "数据库约束违规"),
    DATA_LOCK_ACQUISITION_FAILED(2014, "数据锁获取失败"),
    DATA_TYPE_MISMATCH(2015, "数据类型不匹配"),
    DATA_TOO_LARGE(2016, "数据量过大"),
    DATA_SCHEMA_MISMATCH(2017, "数据库模式不匹配"),
    DATA_FOREIGN_KEY_VIOLATION(2018, "外键约束违规"),
    DATA_BATCH_OPERATION_FAILED(2019, "批量操作失败"),
    DATA_INDEX_OUT_OF_BOUNDS(2020, "数据库索引越界"),


    // 文件相关错误码
    FILE_NOT_FOUND(3001, "文件未找到"),
    FILE_UPLOAD_FAILED(3002, "文件上传失败"),
    FILE_DOWNLOAD_FAILED(3003, "文件下载失败"),

    // 业务相关错误码
    OPERATION_FAILED(4001, "操作失败"),
    OPERATION_NOT_ALLOWED(4002, "操作不允许"),
    INVALID_OPERATION(4003, "无效操作"),
    MEMO_ALREADY_EXISTS(4004, "备忘录已存在");

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
