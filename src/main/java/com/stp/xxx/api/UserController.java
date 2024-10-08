package com.stp.xxx.api;

import cn.dev33.satoken.stp.StpUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.stp.xxx.dto.user.UserAddInputDTO;
import com.stp.xxx.dto.user.UserInfoGetOutputDTO;
import com.stp.xxx.dto.user.UserUpdateInputDTO;
import com.stp.xxx.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope
@Tag(name = "用户管理")
@ApiSort(1)
@RestController
@RequestMapping("api/user/")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperationSupport(order = 1)
    @Operation(summary = "注册")
    @PostMapping("register")
    public String register(@Valid @RequestBody UserAddInputDTO inputDTO) {
        return userService.register(inputDTO);
    }

    @ApiOperationSupport(order = 2)
    @Operation(summary = "修改用户信息")
    @PostMapping("updateUserInfo")
    public String updateUserInfo(@Valid @RequestBody UserUpdateInputDTO inputDTO) {
        return userService.updateUserInfo(inputDTO);
    }

    @ApiOperationSupport(order = 3)
    @Operation(summary = "登录")
    @Parameters({
            @Parameter(name = "username", description = "用户名", required = true),
            @Parameter(name = "password", description = "密码", required = true)})
    @PostMapping("login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        return userService.login(username, password);
    }

    @ApiOperationSupport(order = 4)
    @Operation(summary = "注销")
    @PostMapping("logout")
    public void logout() {
        StpUtil.logout();
    }

    @ApiOperationSupport(order = 5)
    @Operation(summary = "删除")
    @Parameter(name = "id", description = "用户id", required = true)
    @PostMapping("delete")
    public void delete(@RequestParam("id") String id) {
        userService.delete(id);
    }

    @ApiOperationSupport(order = 6)
    @Operation(summary = "获取当前用户信息")
    @PostMapping("getCurrentUserInfo")
    public UserInfoGetOutputDTO getCurrentUserInfo() {
        return userService.getCurrentUserInfo();
    }
}
