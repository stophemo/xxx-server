package com.stp.xxx.util;

import cn.hutool.core.util.StrUtil;
import com.stp.xxx.dao.CommonMapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlExceptionUtil {

    public static String getDuplicateKeyMessage(String errorMessage, CommonMapper mapper) {
        String regex = "Duplicate entry '([^']*)' for key '([^']*)'";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(errorMessage);
        // 查找匹配的内容
        if (matcher.find()) {
            // stophemo
            String entry = matcher.group(1);
            // t_sys_user.unique_name
            String key = matcher.group(2);
            String tableName = StrUtil.subBefore(key, ".", false);
            if (StrUtil.endWith(key, "PRIMARY")) {
                return StrUtil.format("{}已存在：{}", mapper.getPrimaryKeyComment(tableName), entry);
            } else {
                String fieldName = StrUtil.subAfter(key, "_", true);
                return StrUtil.format("{}已存在：{}", mapper.getFieldCommentByName(tableName, fieldName), entry);
            }


        } else {
            System.out.println("No match found.");
        }
        return null;
    }
}
