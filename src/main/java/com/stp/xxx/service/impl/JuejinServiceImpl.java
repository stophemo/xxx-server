package com.stp.xxx.service.impl;

import com.stp.xxx.service.JuejinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JuejinServiceImpl implements JuejinService {

    private final String drawUrl = "https://api.juejin.cn/growth_api/v1/lottery/ten_draw";

    @Override
    public String checkIn() {
        return "";
    }

    @Override
    public String oneClickAllIn() {
        return "";
    }
}
