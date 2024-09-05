package com.stp.xxx.util;

import cn.hutool.core.util.StrUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlExceptionUtil {

    public static String getDuplicateKeyMessage(String errorMessage) {
        String regex = "Duplicate entry '([^']*)' for key '([^']*)'";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(errorMessage);
        // 查找匹配的内容
        if (matcher.find()) {
            String entry = matcher.group(1);
            String key = matcher.group(2);
            return StrUtil.format("{}已存在：{}", key, entry);
        } else {
            System.out.println("No match found.");
        }
        return null;
    }
}
