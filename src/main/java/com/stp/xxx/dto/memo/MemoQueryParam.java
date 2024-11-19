package com.stp.xxx.dto.memo;

import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "查询备忘录参数")
public class MemoQueryParam {

    @Schema(description = "备忘录ID")
    private String id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户ID")
    private String userId;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "标签")
    private String tag;

    @Schema(description = "优先级")
    private Integer priority;

    @Schema(description = "创建开始日期", example = "2024-11-01")
    private LocalDate createStartDate;

    @Schema(description = "创建结束日期", example = "2024-11-30")
    private LocalDate createEndDate;

    @Schema(description = "更新开始日期", example = "2024-11-01")
    private LocalDate updateStartDate;

    @Schema(description = "更新结束日期", example = "2024-11-30")
    private LocalDate updateEndDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass()!= o.getClass()) {
            return false;
        }
        MemoQueryParam that = (MemoQueryParam) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(content, that.content) &&
                Objects.equals(tag, that.tag) &&
                Objects.equals(priority, that.priority) &&
                Objects.equals(createStartDate, that.createStartDate) &&
                Objects.equals(createEndDate, that.createEndDate) &&
                Objects.equals(updateStartDate, that.updateStartDate) &&
                Objects.equals(updateEndDate, that.updateEndDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, userId, title, content, tag, priority, createStartDate, createEndDate, updateStartDate, updateEndDate);
    }
}
