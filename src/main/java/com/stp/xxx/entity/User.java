package com.stp.xxx.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import com.stp.xxx.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author stophemo
 * @since 2024-08-20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table("t_sys_user")
public class User extends BaseEntity {

    @Id(keyType = KeyType.Generator, value = KeyGenerators.uuid)
    private String id;

    @Column(value = "email", comment = "邮箱")
    private String email;

    @Column(value = "phone", comment = "手机")
    private String phone;

    @Column(value = "name", comment = "用户名")
    private String name;

    @Column(value = "nickname", comment = "昵称")
    private String nickname;

    @Column(value = "password", comment = "密码")
    private String password;

    @Column(value = "gender", comment = "性别：1=男, 2=女, 0=未知")
    private Integer gender;

    @Column(value = "avatar", comment = "头像")
    private String avatar;

    @Column(value = "status", comment = "状态：0=禁用, 1=启用")
    private Boolean status;

    @Column(value = "ordinal", comment = "序号")
    private Integer ordinal;

    @Column(value = "role", comment = "角色")
    private String role;
}
