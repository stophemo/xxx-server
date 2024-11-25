package com.stp.xxx.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.dromara.hutool.core.text.StrUtil;
import org.hibernate.validator.constraints.Range;

/**
 * <p>
 * 用户添加DTO
 * </p>
 */
@Data
public class UserAddInputDTO {

    @Schema(description = "电子邮箱")
    @Email(message = "电子邮箱格式不正确")
    private String email;

    @Schema(description = "手机号码")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号码格式不正确")
    private String phone;

    @Schema(description = "用户名称")
    @NotBlank(message = "用户名称不能为空")
    private String name;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "用户密码")
    @NotBlank(message = "用户密码不能为空")
    @Size(min = 5, max = 20, message = "用户密码长度必须在5到20个字符之间")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,}$", message = "用户密码必须包含至少一个字母和一个数字")
    private String password;

    @Schema(description = "用户性别")
    @Range(min = 0, max = 2, message = "用户性别必须是0、1或2")
    private Integer gender;

    @Schema(description = "用户头像")
    private String avatar;

    @Schema(description = "角色", defaultValue = "普通用户")
    @NotBlank(message = "角色不可为空")
    private String role = "普通用户";


    public void setEmail(String email) {
        if (email != "") {
            this.email = email;
        }
    }

    public void setPhone(String phone) {
        if (phone != "") {
            this.phone = phone;
        }
    }
}
