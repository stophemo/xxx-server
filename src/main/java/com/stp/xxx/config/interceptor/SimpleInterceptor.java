package com.stp.xxx.config.interceptor;

import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.http.ForestResponse;
import com.dtflys.forest.interceptor.Interceptor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.json.JSONObject;
import org.dromara.hutool.json.JSONUtil;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleInterceptor<T> implements Interceptor<T> {

    @Override
    public void onSuccess(T data, ForestRequest req, ForestResponse res) {
        String content = res.getContent();
        JSONObject jsonObject = JSONUtil.parseObj(content);
        JSONObject entries = jsonObject.get("data", JSONObject.class);
        res.setContent(JSONUtil.toJsonStr(entries));
    }
}

