package com.stp.xxx.api;

import com.stp.xxx.dto.memo.MemoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
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
@Tag(name = "备忘录")
@RestController
@RequestMapping("api/memo/")
public class MemoController {

    @Resource
    private MemoService memoService;

    /**
     * 新建备忘
     * @return 备忘ID
     */
    @Operation(summary = "新建备忘")
    @PostMapping(value = "add")
    public String add(@RequestBody MemoDTO memoDTO) {
        return memoService.add(memoDTO);
    }

    /**
     * 删除备忘
     *
     * @param id 备忘ID
     */
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