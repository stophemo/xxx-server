package com.stp.xxx.util;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.FIFOCache;
import cn.hutool.core.util.ObjUtil;
import com.stp.xxx.config.exception.BusinessException;
import com.stp.xxx.config.exception.ErrorCodeEnum;
import com.stp.xxx.constant.SysContant;
import com.stp.xxx.entity.User;
import lombok.Getter;

public class TokenUtil {

    public static User getTokenInfo() {
        User user = (User) StpUtil.getSession().get(SysContant.USER_INFO);
        if (ObjUtil.isEmpty(user)) {
            throw new BusinessException(ErrorCodeEnum.USER_SESSION_NOT_ACCESS);
        }
        return user;
    }

    public static String getAlistToken() {
        return (String) StpUtil.getSession().get(SysContant.ALIST_TOKEN);
    }

    @Getter
    private static final FIFOCache<String, String> tokenCache = CacheUtil.newFIFOCache(10,  60 * 1000);
}
