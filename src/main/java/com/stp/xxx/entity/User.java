package com.stp.xxx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.stp.xxx.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author stophemo
 * @since 2024-08-20
 */
@Getter
@Setter
@TableName("t_sys_user")
public class User extends BaseEntity {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 手机
     */
    @TableField("phone")
    private String phone;

    /**
     * 用户名
     */
    @TableField("name")
    private String name;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 性别：1=男, 2=女, 0=未知
     */
    @TableField("gender")
    private Integer gender;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 状态：0=禁用, 1=启用
     */
    @TableField("status")
    private Boolean status;

    /**
     * 序号
     */
    @TableField("ordinal")
    private Integer ordinal;
}
