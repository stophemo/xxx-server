package com.stp.xxx.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.stp.xxx.service.MemoService;
import com.stp.xxx.entity.Memo;
import org.springframework.beans.factory.annotation.Autowired;

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
@RequestMapping("/memo")
public class MemoController {


    @Autowired
    private MemoService memoService;



    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Memo params) {
        memoService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        memoService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody Memo params) {
        memoService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
