package com.stp.xxx.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.stp.xxx.dto.user.UserInfoGetOutputDTO;
import com.stp.xxx.service.AlistService;
import com.stp.xxx.util.TokenUtil;
import com.stp.xxx.config.exception.BusinessException;
import com.stp.xxx.config.exception.ErrorCodeEnum;
import com.stp.xxx.constant.SysContant;
import com.stp.xxx.dao.CommonMapper;
import com.stp.xxx.dao.UserMapper;
import com.stp.xxx.dto.user.UserAddInputDTO;
import com.stp.xxx.dto.user.UserUpdateInputDTO;
import com.stp.xxx.entity.User;
import com.stp.xxx.enums.RoleEnum;
import com.stp.xxx.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.bean.BeanUtil;
import org.dromara.hutool.core.data.id.IdUtil;
import org.dromara.hutool.core.util.ObjUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

    @Autowired
    private AlistService alistService;
    @Value("${alist.username}")
    private String alistUsername;
    @Value("${alist.password}")
    private String alistPassword;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String register(UserAddInputDTO inputDTO) {
        if (exists(QueryWrapper.create().eq("name", inputDTO.getName()))) {
            throw new BusinessException(ErrorCodeEnum.USER_ALREADY_EXISTS);
        }

        User user = BeanUtil.copyProperties(inputDTO, User.class);
        user.setId(IdUtil.fastSimpleUUID().toUpperCase());
        user.setStatus(true);
        user.setPassword(passwordEncoder.encode(inputDTO.getPassword()));
        if (save(user)) {
            StpUtil.login(user.getName());
            StpUtil.getSession().set(SysContant.USER_INFO, user);
            // 调用Alist的登录接口
            try {
                String alistToken = alistService.getTokenValue(alistUsername, alistPassword);
                StpUtil.getSession().set(SysContant.ALIST_TOKEN, alistToken);
            } catch (Exception e) {
                log.error("Alist 登录失败", e);
            }
            return StpUtil.getTokenValue();
        } else {
            throw new BusinessException("注册失败");
        }
    }

    @Override
    public String updateUserInfo(UserUpdateInputDTO inputDTO) {
        User user = BeanUtil.copyProperties(inputDTO, User.class);

        if (updateById(user, true)) {
            User newUser = getById(user.getId());
            StpUtil.getSession().set(SysContant.USER_INFO, newUser);
            return "修改成功";
        } else {
            throw new BusinessException("修改失败");
        }
    }

    @Override
    public String login(String username, String password) {
        User user = getOne(QueryWrapper.create().eq("name", username));

        if (ObjUtil.isEmpty(user)) {
            throw new BusinessException(ErrorCodeEnum.USER_NOT_FOUND);
        }

        if (passwordEncoder.matches(password, user.getPassword())) {
            StpUtil.login(user.getId());
            StpUtil.getSession().set(SysContant.USER_INFO, user);
            // 调用Alist的登录接口
            try {
                String alistToken = alistService.getTokenValue(alistUsername, alistPassword);
                StpUtil.getSession().set(SysContant.ALIST_TOKEN, alistToken);
            } catch (Exception e) {
                log.error("Alist 登录失败", e);
            }
            return StpUtil.getTokenValue();
        } else {
            throw new BusinessException(ErrorCodeEnum.USER_AUTHENTICATION_FAILED);
        }
    }

    @Override
    public void delete(String id) {
        // 校验权限
        User user = TokenUtil.getTokenInfo();
        if (user != null && !RoleEnum.ADMIN.getName().equals(user.getRole())) {
            throw new BusinessException(ErrorCodeEnum.USER_PERMISSION_DENIED);
        }

        if (!removeById(id)) {
            throw new BusinessException(ErrorCodeEnum.DATA_DELETE_FAILED);
        }
    }

    @Override
    public UserInfoGetOutputDTO getCurrentUserInfo() {
        UserInfoGetOutputDTO res = BeanUtil.copyProperties(StpUtil.getTokenInfo(), UserInfoGetOutputDTO.class);
        User user = (User) StpUtil.getSession().get(SysContant.USER_INFO);
        BeanUtil.copyProperties(user, res);
        res.setAlistToken(TokenUtil.getAlistToken());
        return res;
    }
}
