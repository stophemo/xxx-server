package com.stp.xxx.dto.auth;

import lombok.Data;

/**
 * 用户认证请求对象.
 */
@Data
public class AuthRequest {

    private String username;

    private String password;

}
