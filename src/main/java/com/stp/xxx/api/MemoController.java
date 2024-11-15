package com.stp.xxx.api;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.core.bean.BeanUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import com.stp.xxx.dto.memo.MemoAddInputDTO;
import com.stp.xxx.dto.memo.MemoGetOutputDTO;
import com.stp.xxx.dto.memo.MemoUpdateInputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
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

    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取")
    @Parameter(name = "username", description = "用户名", required = true)
    @GetMapping(value = "get")
    public List<MemoGetOutputDTO> getMemo(@RequestParam("username") String username) {
        return memoService.getMemo(username);
    }

    @ApiOperationSupport(order = 2)
    @Operation(summary = "新建备忘")
    @PostMapping(value = "add")
    public String add(@Valid @RequestBody MemoAddInputDTO inputDTO) {
        return memoService.add(inputDTO);
    }

    @ApiOperationSupport(order = 3)
    @Operation(summary = "更新备忘")
    @PostMapping(value = "update")
    public void update(@Valid @RequestBody MemoUpdateInputDTO inputDTO) {
        memoService.updateById(BeanUtil.copyProperties(inputDTO, Memo.class));
    }

    @ApiOperationSupport(order = 4)
    @Operation(summary = "删除备忘")
    @PostMapping(value = "del")
    @Parameter(name = "id", description = "备忘ID", required = true)
    public void delete(@RequestParam("id") String id) {
        memoService.removeById(id);
    }

    @ApiOperationSupport(order = 5)
    @Operation(summary = "分页查询备忘")
    @PostMapping(value = "query")
    public Page<Memo> query(@RequestBody Page<Memo> param) {
        return memoService.page(param, QueryWrapper.create()
                .eq(Memo::getId, param.getRecords().get(0).getId())
                .eq(Memo::getUserId, param.getRecords().get(0).getUserId()));
    }
}