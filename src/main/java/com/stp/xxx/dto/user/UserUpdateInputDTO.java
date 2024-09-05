package com.stp.xxx.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 用户更新DTO
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateInputDTO {

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    @NotNull(message = "用户ID不能为空")
    private String id;

    /**
     * 电子邮箱
     */
    @Schema(description = "电子邮箱")
    @NotBlank(message = "电子邮箱不能为空")
    @Email(message = "电子邮箱格式不正确")
    private String email;

    /**
     * 手机号码
     */
    @Schema(description = "手机号码")
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号码格式不正确")
    private String phone;

    /**
     * 用户名称
     */
    @Schema(description = "用户名称")
    @NotBlank(message = "用户名称不能为空")
    private String name;

    /**
     * 用户性别
     */
    @Schema(description = "用户性别")
    @NotNull(message = "用户性别不能为空")
    @Pattern(regexp = "^[0-2]$", message = "用户性别必须是0、1或2")
    private Integer gender;

    /**
     * 用户头像
     */
    @Schema(description = "用户头像")
    private String avatar;

    /**
     * 用户状态
     */
    @Schema(description = "用户状态")
    private Boolean status;

    /**
     * 用户排序
     */
    @Schema(description = "用户排序")
    private Integer ordinal;
}
