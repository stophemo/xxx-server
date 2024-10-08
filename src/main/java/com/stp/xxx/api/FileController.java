package com.stp.xxx.api;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.stp.xxx.dto.alist.fs.FilesGetInputDTO;
import com.stp.xxx.dto.alist.fs.FilesGetOutputDTO;
import com.stp.xxx.dto.alist.fs.UploadResult;
import com.stp.xxx.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "文件")
@ApiSort(4)
@RestController
@RequestMapping("/api/file/")
public class FileController {
    @Resource
    private StorageService storageService;

    @ApiOperationSupport(order = 1)
    @Operation(summary = "查询文件目录")
    @PostMapping(value = "listFiles")
    public FilesGetOutputDTO listFiles(@RequestBody FilesGetInputDTO inputDTO) {
        return storageService.listFiles(inputDTO);
    }

    @ApiOperationSupport(order = 2)
    @Operation(summary = "通过表单上传文件")
    @PostMapping(value = "uploadFileByForm", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadResult uploadFileByForm(
            @RequestParam(name = "asTask", required = false) Boolean asTask,
            @RequestParam(name = "filePath") String filePath,
            @RequestPart(name = "file") MultipartFile file) {
        // 调用存储服务执行文件上传逻辑
        return storageService.uploadFileByForm(asTask, filePath, file);
    }

}
