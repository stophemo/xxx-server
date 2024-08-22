package com.stp.xxx.service;

import com.stp.xxx.dto.UserDTO;
import com.stp.xxx.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 */
public interface UserService extends IService<User> {

    String login(String username,
                               String password);

    String register(UserDTO user);
}
