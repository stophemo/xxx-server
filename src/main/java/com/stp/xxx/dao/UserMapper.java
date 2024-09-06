package com.stp.xxx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stp.xxx.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
