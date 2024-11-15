package com.stp.xxx.api;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.stp.xxx.service.JuejinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@SaCheckLogin
@Tag(name = "掘金")
@ApiSort(3)
@RestController
@RequestMapping("api/juejin/")
public class JuejinController {

    @Resource
    private JuejinService juejinService;

    @ApiOperationSupport(order = 1)
    @Operation(summary = "签到")
    @PostMapping(value = "CheckIn")
    public String CheckIn() {
        return juejinService.checkIn();
    }


    @ApiOperationSupport(order = 2)
    @Operation(summary = "一键AllIN")
    @PostMapping(value = "oneClickAllIn")
    public String oneClickAllIn() {
        return juejinService.oneClickAllIn();
    }
}
