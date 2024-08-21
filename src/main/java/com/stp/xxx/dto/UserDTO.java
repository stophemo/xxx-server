package com.stp.xxx.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 用户表DTO
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private String id;
    private String email;
    private String phone;
    private String name;
    private String nickname;
    private String password;
    private Boolean gender;
    private String avatar;
    private Boolean status;
    private Integer ordinal;
}
