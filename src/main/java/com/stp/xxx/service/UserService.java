package com.stp.xxx.service;

import com.stp.xxx.dto.user.UserAddInputDTO;
import com.stp.xxx.dto.user.UserUpdateInputDTO;
import com.stp.xxx.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 */
public interface UserService extends IService<User> {

    String register(UserAddInputDTO inputDTO);

    String updateUserInfo(UserUpdateInputDTO inputDTO);

    String login(String username, String password);
}
