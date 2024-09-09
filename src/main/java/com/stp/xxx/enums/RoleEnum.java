package com.stp.xxx.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    ADMIN(1, "管理员"),
    USER(2, "普通用户"),
    VISITOR(3, "游客")
    ;

    private final Integer code;
    private final String name;

    RoleEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
