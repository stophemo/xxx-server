package com.stp.xxx.dto.memo;

import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Accessors(chain = true)
public class MemoDTO {
    /**
     * 唯一标识符
     */
    @Schema(description = "唯一标识符", defaultValue = "")
    private String id;

    /**
     * 用户ID，关联到用户表
     */
    @Schema(description = "用户ID", defaultValue = "")
    private String userId;

    /**
     * 用户名
     */
    @Schema(description = "用户名", defaultValue = "")
    private String userName;

    /**
     * 备忘录标题
     */
    @Schema(description = "备忘录标题", defaultValue = "")
    private String title;

    /**
     * 备忘录内容
     */
    @Schema(description = "备忘录内容", defaultValue = "")
    private String content;

    /**
     * 备忘录标签，使用逗号分隔
     */
    @Schema(description = "备忘录标签", defaultValue = "defualt")
    private String tags;

    /**
     * 优先级
     */
    @Schema(description = "优先级", defaultValue = "0")
    private Integer priority;
}