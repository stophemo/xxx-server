package com.stp.xxx.dto.user;

import lombok.Data;

@Data
public class UserInfoGetOutputDTO {

    private String tokenName;
    private String tokenValue;
    private Boolean isLogin;
    private Object loginId;
    private String loginType;
    private long tokenTimeout;
    private long sessionTimeout;
    private long tokenSessionTimeout;
    private long tokenActiveTimeout;
    private String loginDevice;
    private String tag;
    private String alistToken;

    private String id;
    private String email;
    private String phone;
    private String name;
    private String nickname;
    private Integer gender;
    private String avatar;
    private Boolean status;
    private Integer ordinal;
    private String role;
}
