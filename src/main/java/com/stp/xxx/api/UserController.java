package com.stp.xxx.api;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.stp.xxx.dto.core.ResultEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import com.stp.xxx.service.UserService;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author stophemo
 * @since 2024-08-20
 */
@Tag(name = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    private UserService userService;

    @Operation(summary = "登录")
    @Parameters({
            @Parameter(name = "username", description = "用户名", required = true),
            @Parameter(name = "password", description = "密码", required = true)})
    @PostMapping("/login")
    public ResultEntity<String> login(@RequestParam String username,
                                      @RequestParam String password) {
        if ("jackman".equals(username) && "666".equals(password)) {
            StpUtil.login(10001);
            return ResultEntity.ok("登录成功");
        }
        return ResultEntity.error("登录失败");
    }

    @PostMapping("isLogin")
    public ResultEntity<String> isLogin() {
        return ResultEntity.ok("当前会话是否登录：" + StpUtil.isLogin());
    }

    @PostMapping("tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    @PostMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }
}
