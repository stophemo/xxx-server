package com.stp.xxx.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stp.xxx.config.exception.BusinessException;
import com.stp.xxx.config.exception.ErrorCodeEnum;
import com.stp.xxx.dao.UserMapper;
import com.stp.xxx.dto.user.UserAddInputDTO;
import com.stp.xxx.dto.user.UserUpdateInputDTO;
import com.stp.xxx.entity.User;
import com.stp.xxx.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stp.xxx.util.SqlExceptionUtil;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author stophemo
 * @since 2024-08-20
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public String register(UserAddInputDTO inputDTO) {

/*        User existUser = userMapper.selectOne(new QueryWrapper<User>().eq("name", inputDTO.getName()));
        if (ObjUtil.isNotEmpty(existUser)) {
            return "注册失败，用户名已存在";
        }*/

        User user = new User();
        BeanUtil.copyProperties(inputDTO, user);
        user.setId(IdUtil.fastSimpleUUID().toUpperCase());
        user.setStatus(true);

        try {
            int insert = userMapper.insert(user);
            if (insert == 1) {
                StpUtil.login(user.getName());
                return "注册成功";
            }
        } catch (Exception e) {
            // 获取异常信息
            String errorMessage = e.getMessage();
            // 判断是否违反唯一约束
            if (errorMessage != null && errorMessage.contains("Duplicate entry")) {
                throw new BusinessException(ErrorCodeEnum.DATA_DUPLICATION.getCode(),
                        SqlExceptionUtil.getDuplicateKeyMessage(errorMessage));
            } else {
                throw new BusinessException(e);
            }
        }
        return "注册失败";
    }

    @Override
    public String updateUserInfo(UserUpdateInputDTO inputDTO) {
        User user = BeanUtil.copyProperties(inputDTO, User.class);
        try {
            int i = baseMapper.updateById(user);
            if (i == 1) {
                return "修改成功";
            }
        } catch (Exception e) {
            // 获取异常信息
            String errorMessage = e.getMessage();

            // 判断是否违反唯一约束
            if (errorMessage != null && errorMessage.contains("Duplicate entry")) {
                // 获取违反唯一约束的列名
                String[] parts = errorMessage.split(" ");
                String duplicateValue = parts[2]; // 违约值
                String duplicateColumn = parts[5].replace("'", ""); // 违约的列名

                return duplicateColumn + " 已存在: " + duplicateValue;
            }
            return "注册失败：" + errorMessage;
        }
        return "注册失败";
    }

    @Override
    public String login(String username, String password) {

        User user = userMapper.selectOne(new QueryWrapper<User>().eq("name", username));

        if (ObjUtil.isEmpty(user)) {
            return "登录失败，用户名不存在";
        }

        boolean checkFlag = userMapper.exists(new QueryWrapper<User>().eq("name", username).eq("password", password));
        if (checkFlag) {
            StpUtil.login(user.getName());
            return "登录成功";
        }
        return "登录失败，密码错误";
    }
}
