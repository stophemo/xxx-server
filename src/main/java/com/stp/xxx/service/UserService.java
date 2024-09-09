package com.stp.xxx.service;

import com.mybatisflex.core.service.IService;
import com.stp.xxx.dto.user.UserAddInputDTO;
import com.stp.xxx.dto.user.UserUpdateInputDTO;
import com.stp.xxx.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 */
public interface UserService extends IService<User> {

    String register(UserAddInputDTO inputDTO);

    String updateUserInfo(UserUpdateInputDTO inputDTO);

    String login(String username, String password);

    void delete(String id);
}
