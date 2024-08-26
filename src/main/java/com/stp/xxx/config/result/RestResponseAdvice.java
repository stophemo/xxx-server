package com.stp.xxx.config.result;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 对返回结果统一进行处理，包括返回结果格式统一包装，返回异常统一处理
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.stp.xxx")
public class RestResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(@NonNull MethodParameter returnType,
                            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        // 如果不需要进行封装的，可以添加一些校验手段，比如添加标记排除的注解
        return true;
    }

    /**
     * HttpMessageConverter转换之前进行的操作
     *
     * @param body                  要转换的body
     * @param returnType            返回类型
     * @param selectedContentType   根据请求头协商的ContentType
     * @param selectedConverterType 自动选择的转换器类型
     * @param request               当前请求
     * @param response              当前响应
     * @return 修改后的响应内容
     */
    @Override
    public Object beforeBodyWrite(Object body,
                                  @NonNull MethodParameter returnType,
                                  @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NonNull ServerHttpRequest request,
                                  @NonNull ServerHttpResponse response) {
        if (body instanceof ResultEntity<?> || !selectedContentType.equals(MediaType.APPLICATION_JSON)) {
            return body;
        }
        return ResultEntity.ok(body);
    }
}