package com.stp.xxx.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 用户更新DTO
 * </p>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateInputDTO {

    @Schema(description = "用户ID")
    @NotNull(message = "用户ID不能为空")
    private String id;

    @Schema(description = "电子邮箱")
    @Email(message = "电子邮箱格式不正确")
    private String email;

    @Schema(description = "手机号码")
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号码格式不正确")
    private String phone;

    @Schema(description = "用户名称")
    @NotBlank(message = "用户名称不能为空")
    private String name;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "用户性别")
    @Pattern(regexp = "^[0-2]$", message = "用户性别必须是0、1或2")
    private Integer gender;

    @Schema(description = "用户头像")
    private String avatar;

    @Schema(description = "用户状态")
    @Pattern(regexp = "^[01]$", message = "用户状态必须是0或1")
    private Boolean status;

    @Schema(description = "用户排序")
    @Positive(message = "用户排序必须为自然数")
    private Integer ordinal;

    @Schema(description = "角色", defaultValue = "普通用户")
    private String role;
}
