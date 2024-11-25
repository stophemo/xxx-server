package com.stp.xxx.dto.user;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

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

    @Schema(description = "用户名称")
    private String name;

    @Schema(description = "用户密码")
    @Size(min = 5, max = 20, message = "用户密码长度必须在5到20个字符之间")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,}$", message = "用户密码必须包含至少一个字母和一个数字")
    private String password;

    @Schema(description = "电子邮箱")
    @Email(message = "电子邮箱格式不正确")
    private String email;

    @Schema(description = "手机号码")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号码格式不正确")
    private String phone;


    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "用户性别")
    @Range(min = 0, max = 2, message = "用户性别必须是0、1或2")
    private Integer gender;

    @Schema(description = "用户头像")
    private String avatar;

    @Schema(description = "用户状态")
    @AssertTrue(message = "用户状态必须是0或1")
    private Boolean status;

    @Schema(description = "用户排序")
    @Positive(message = "用户排序必须为自然数")
    private Integer ordinal;

    @Schema(description = "角色", defaultValue = "普通用户")
    private String role;
}
