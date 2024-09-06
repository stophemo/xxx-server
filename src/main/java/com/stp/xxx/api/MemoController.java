package com.stp.xxx.api;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.stp.xxx.dto.memo.MemoAddInputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.stp.xxx.service.MemoService;
import com.stp.xxx.entity.Memo;

/**
 * <p>
 * 备忘录表 前端控制器
 * </p>
 *
 * @author jackman
 * @since 2024-08-26
 */
@ApiSort(2)
@Tag(name = "备忘录")
@RestController
@RequestMapping("api/memo/")
public class MemoController {

    @Resource
    private MemoService memoService;

    @Operation(summary = "获取")
    @GetMapping(value = "get")
    public String getMemoJson(@RequestParam String username) {
        return memoService.getMemoJson(username);
    }

    @ApiOperationSupport(order = 1)
    @Operation(summary = "新建备忘")
    @PostMapping(value = "add")
    public String add(@Valid @RequestBody MemoAddInputDTO inputDTO) {
        return memoService.add(inputDTO);
    }

    @Operation(summary = "删除备忘")
    @PostMapping(value = "del/{id}")
    public void delete(
            @Parameter(description = "备忘ID", required = true) @PathVariable("id") String id) {
        memoService.removeById(id);
    }

    /**
     * 更新备忘
     *
     * @param params 备忘信息
     */
    @Operation(summary = "更新备忘")
    @PostMapping(value = "update")
    public void update(
            @Parameter(description = "备忘信息", required = true) @RequestBody Memo params) {
        memoService.updateById(params);
    }

}