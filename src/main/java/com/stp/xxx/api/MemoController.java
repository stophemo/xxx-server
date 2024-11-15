package com.stp.xxx.api;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.bean.BeanUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.stp.xxx.dto.memo.*;
import com.stp.xxx.util.page.PageParam;
import com.stp.xxx.util.page.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.stp.xxx.service.MemoService;
import com.stp.xxx.entity.Memo;

import java.util.List;

/**
 * <p>
 * 备忘录表 前端控制器
 * </p>
 *
 * @author jackman
 * @since 2024-08-26
 */
@SaCheckLogin
@Tag(name = "备忘录")
@ApiSort(2)
@RestController
@RequestMapping("api/memo/")
public class MemoController {

    @Resource
    private MemoService memoService;

    @SaIgnore
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取")
    @Parameter(name = "username", description = "用户名", required = true)
    @PostMapping(value = "getUserMemo")
    public List<MemoGetOutputDTO> getUserMemo(@RequestParam("username") String username) {
        return memoService.getUserMemo(username);
    }

    @ApiOperationSupport(order = 2)
    @Operation(summary = "新建备忘")
    @PostMapping(value = "addMemo")
    public String addMemo(@Valid @RequestBody MemoAddInputDTO inputDTO) {
        return memoService.add(inputDTO);
    }

    @ApiOperationSupport(order = 3)
    @Operation(summary = "更新备忘")
    @PostMapping(value = "updateMemo")
    public void updateMemo(@Valid @RequestBody MemoUpdateInputDTO inputDTO) {
        memoService.updateById(BeanUtil.copyProperties(inputDTO, Memo.class));
    }

    @ApiOperationSupport(order = 4)
    @Operation(summary = "删除备忘")
    @PostMapping(value = "deleteMemo")
    @Parameter(name = "id", description = "备忘ID", required = true)
    public void delete(@RequestParam("id") String id) {
        memoService.removeById(id);
    }

    @ApiOperationSupport(order = 5)
    @Operation(summary = "分页查询备忘")
    @PostMapping(value = "queryMemo")
    public PageResult<MemoQueryResult> queryMemo(@Valid @RequestBody PageParam<MemoQueryParam> param) {
        return memoService.queryMemo(param);
    }
}