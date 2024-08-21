package com.stp.xxx.dto.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * The type Result entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultEntity<T> {

    /**
     * The constant SUCCESS.
     */
    public static final int SUCCESS = 200;
    /**
     * The constant BUSINESS_ERROR_CODE.
     */
    public static final int BUSINESS_ERROR_CODE = 400;

    /**
     * The constant BUSINESS_ERROR_CODE_410.
     */
    public static final int BUSINESS_ERROR_CODE_410 = 410;

    /**
     * The constant MSG_SUCCESS.
     */
    public static final String MSG_SUCCESS = "SUCCESS";

    private int code;

    private String message;

    private T data;

    /**
     * Is success boolean.
     *
     * @return the boolean
     */
    public boolean isSuccess() {
        return Objects.equals(SUCCESS, this.getCode());
    }

    /**
     * Ok result entity.
     *
     * @param <T>  the type parameter
     * @param data the data
     * @return the result entity
     */
    public static <T> ResultEntity<T> ok(T data) {
        ResultEntity<T> result = new ResultEntity<>(SUCCESS, MSG_SUCCESS, data);
        return result;
    }

    /**
     * Success result entity.
     *
     * @param <T>  the type parameter
     * @param data the data
     * @return the result entity
     */
    public static <T> ResultEntity<T> success(T data) {
        ResultEntity<T> result = new ResultEntity<>(SUCCESS, MSG_SUCCESS, data);
        return result;
    }

    /**
     * Err result entity.
     *
     * @param <T>     the type parameter
     * @param message the message
     * @return the result entity
     */
    public static <T> ResultEntity<T> error(String message) {
        ResultEntity<T> result = new ResultEntity<>(BUSINESS_ERROR_CODE, message, null);
        return result;
    }

    /**
     * Err result entity.
     *
     * @param <T>       the type parameter
     * @param errorCode the error code
     * @param message   the message
     * @return the result entity
     */
    public static <T> ResultEntity<T> error(int errorCode, String message) {
        ResultEntity<T> result = new ResultEntity<>(errorCode, message, null);
        return result;
    }

    /**
     * 业务要求某些情况下允许数据部分保存并向前端返回错误信息.
     *
     * @param <T>       the type parameter
     * @param errorCode the error code
     * @param message   the message
     * @param data      the data
     * @return the result entity
     */
    public static <T> ResultEntity<T> error(int errorCode, String message, T data) {
        ResultEntity<T> result = new ResultEntity<>(errorCode, message, data);
        return result;
    }
}
