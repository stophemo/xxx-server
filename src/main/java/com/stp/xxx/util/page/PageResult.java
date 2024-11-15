package com.stp.xxx.util.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "分页查询结果")
public class PageResult<T> {

    @Schema(description = "总页数", example = "5")
    private long totalPage;

    @Schema(description = "总记录数", example = "100")
    private long totalRow;

    @Schema(description = "数据列表")
    private List<T> list;
}
