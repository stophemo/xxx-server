package com.stp.xxx.dto.memo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemoUpdateInputDTO {

    @Schema(description = "备忘id")
    @NotBlank(message = "备忘id 不可为空")
    private String id;

    @Schema(description = "备忘录标题", example = "会议提醒")
    @NotBlank(message = "标题不能为空")
    @Size(max = 100, message = "标题长度不能超过100个字符")
    private String title;

    @Schema(description = "备忘录内容", example = "参加下午3点的项目会议")
    @NotBlank(message = "内容不能为空")
    private String content;

    @Schema(description = "备忘录标签", example = "工作")
    private String tag = "default";

    @Schema(description = "优先级", example = "1", defaultValue = "0")
    @NotNull(message = "优先级不能为空")
    private Integer priority;
}
