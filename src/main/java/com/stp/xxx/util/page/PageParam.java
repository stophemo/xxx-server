package com.stp.xxx.util.page;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "分页查询输入参数")
public class PageParam<T> {

    @Schema(description = "当前页码", example = "1")
    private long pageNumber = 1;

    @Schema(description = "每页数据数量", example = "20")
    private long pageSize = 20;

    @Schema(description = "过滤Dto对象", required = true)
    @NotNull(message = "filter-不能为空")
    private T filter;
}
