package com.stp.xxx.dto.memo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Data
@Schema(description = "查询备忘录结果")
public class MemoQueryResult {
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建日期")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新日期")
    private LocalDateTime updateTime;
}
