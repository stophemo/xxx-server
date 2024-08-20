package com.stp.xxx.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.stp.xxx.service.UserService;
import com.stp.xxx.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author stophemo
 * @since 2024-08-20
 */
@Tag(name = "User", description = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Operation(summary = "获取列表", description = "获取用户列表",
            parameters = {@Parameter(name = "current", description = "页码"),
                    @Parameter(name = "pageSize", description = "每页数量")})
    @PostMapping("/query")
    public ResponseEntity<Page<User>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<User> aPage = userService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }


    @Operation(summary = "新增", description = "新增用户")
    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody User params) {
        userService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        userService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody User params) {
        userService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
