package com.stp.xxx.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <p>
 * 管理端用户表.
 * </p>
 *
 * @author lp
 * @since 2024-05-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminUserDTO {

    private Long id;
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
