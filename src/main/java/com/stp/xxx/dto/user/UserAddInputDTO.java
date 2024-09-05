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
 * 用户添加DTO
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAddInputDTO {

    @Schema(description = "电子邮箱")
    @NotBlank(message = "电子邮箱不能为空")
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
    @NotBlank(message = "昵称不可为空")
    private String nickname;

    @Schema(description = "用户密码")
    @NotBlank(message = "用户密码不能为空")
    private String password;

    @Schema(description = "用户性别")
    @NotNull(message = "用户性别不能为空")
    @Pattern(regexp = "^[0-2]$", message = "用户性别必须是0、1或2")
    private Integer gender;

    @Schema(description = "用户头像")
    private String avatar;
}