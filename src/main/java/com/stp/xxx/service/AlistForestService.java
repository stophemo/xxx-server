package com.stp.xxx.service;

import cn.hutool.json.JSONObject;
import com.dtflys.forest.annotation.*;
import com.dtflys.forest.http.ForestResponse;
import com.stp.xxx.config.forest.AddressConfig;

@Address(source = AddressConfig.class)
public interface AlistForestService {

    /**
     * 获取token
     */
    @Post("/api/auth/login")
    ForestResponse<JSONObject> getToken(@JSONBody("username") String username, @JSONBody("password") String password);

    /**
     * 获取当前用户信息
     */
    @Get(url = "/api/me",
         headers = {
            "Authorization: ${token}"
         })
    ForestResponse<JSONObject> getCurrentUserInfo(@Var("token") String token);


}
