package com.stp.xxx.service.impl;

import com.stp.xxx.entity.User;
import com.stp.xxx.dao.UserMapper;
import com.stp.xxx.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author stophemo
 * @since 2024-08-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
