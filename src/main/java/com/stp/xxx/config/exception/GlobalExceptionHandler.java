package com.stp.xxx.config.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.mybatisflex.annotation.Table;
import com.stp.xxx.config.result.ResultEntity;
import com.stp.xxx.dao.CommonMapper;
import com.stp.xxx.util.SqlExceptionUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.reflect.ClassUtil;
import org.dromara.hutool.core.text.StrUtil;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice(basePackages = "com.stp.xxx")
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public ResultEntity<?> handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("数据库条目重复异常：", e);
        String regex = "Duplicate entry '([^']*)' for key '([^']*)'";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(e.getMessage());

        String message = e.getMessage();
        // 查找匹配的内容
        if (matcher.find()) {
            String entry = matcher.group(1);
            String value = StrUtil.subAfter(entry, "-", false);
            String key = matcher.group(2);
            String tableName = StrUtil.subBefore(key, ".", false);
            String columnName = StrUtil.subAfter(key, ".", false);

            List<Field> fieldList = new ArrayList<>();
            // 获取指定包下的所有类的Class对象
            String packageName = "com.stp.xxx.entity";
            Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(packageName, Table.class);
            for (Class<?> clazz : classes) {
                // 检查类是否标注有@Table注解且值为t_sys_user
                if (clazz.getAnnotation(Table.class).value().equals(tableName)) {
                    // 通过反射获取类的所有字段
                    Field[] declaredFields = clazz.getDeclaredFields();
                    for (Field field : declaredFields) {
                        if (StrUtil.contains(columnName, field.getName())) {
                            fieldList.add(field);
                        }
                    }
                }
            }

            String columnComment = fieldList.stream().map(Field::getName).collect(Collectors.joining(","));
            message = StrUtil.format("{}已存在：[{}]", columnComment, value);
        }

        return ResultEntity.error(ErrorCodeEnum.DATA_DUPLICATION.getCode(), message);
    }


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
        return ResultEntity.error(ErrorCodeEnum.INVALID_PARAMETER, errorMessage);
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
        log.error("异常：", e);
        return ResultEntity.error(ErrorCodeEnum.UNKNOWN_ERROR.getCode(), e.getMessage(), e.getCause());
    }
}
