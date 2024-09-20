package com.stp.xxx.dto.memo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemoGetOutputDTO {

    @Schema(description = "标签")
    private String tags;

    @Schema(description = "内容集合")
    private List<Content> contentList;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Content {

        @Schema(description = "标题")
        private String title;

        @Schema(description = "内容")
        private String content;
    }
}
