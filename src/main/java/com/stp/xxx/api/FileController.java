package com.stp.xxx.api;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.stp.xxx.config.exception.BusinessException;
import com.stp.xxx.config.exception.ErrorCodeEnum;
import com.stp.xxx.dto.alist.fs.*;
import com.stp.xxx.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@SaCheckLogin
@Tag(name = "文件")
@ApiSort(4)
@RestController
@RequestMapping("/api/file/")
public class FileController {

    @Resource
    private StorageService storageService;

    @ApiOperationSupport(order = 1)
    @Operation(summary = "列出文件目录")
    @PostMapping(value = "list")
    public FilesGetOutputDTO listFiles(@RequestBody FilesGetInputDTO inputDTO) {
        return storageService.listFiles(inputDTO);
    }

    @ApiOperationSupport(order = 2)
    @Operation(summary = "获取某个文件/目录信息")
    @PostMapping(value = "get")
    public FileInfoGetOutputDTO getFileInfo(@RequestBody FileInfoGetInputDTO inputDTO) {
        return storageService.getFileInfo(inputDTO);
    }

    @ApiOperationSupport(order = 10)
    @Operation(summary = "表单上传文件")
    @PostMapping(value = "form", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadResult uploadFileByForm(
            @Parameter(description = "是否作为任务处理，可选参数") @RequestParam(name = "asTask", required = false) Boolean asTask,
            @Parameter(description = "文件保存路径") @RequestParam(name = "filePath") String filePath,
            @Parameter(description = "文件") @RequestPart(name = "file") MultipartFile file) {
        return storageService.uploadFileByForm(asTask, filePath, file);
    }

    @ApiOperationSupport(order = 11)
    @Operation(summary = "流式上传文件")
    @PostMapping(value = "put")
    public UploadResult uploadFileByStream(
            @Parameter(description = "是否作为任务处理，可选参数") @RequestParam(name = "asTask", required = false) Boolean asTask,
            @Parameter(description = "文件保存路径") @RequestParam(name = "filePath") String filePath,
            @Parameter(description = "文件") @RequestPart(name = "file") MultipartFile file) {
        byte[] fileContents;
        try {
            fileContents = file.getBytes();
        } catch (Exception e) {
            throw new BusinessException(e);
        }
        return storageService.uploadFileByStream(asTask, filePath, fileContents);
    }

    @ApiOperationSupport(order = 12)
    @Operation(summary = "上传图片并获取链接")
    @PostMapping(value = "uploadImage")
    public String uploadImage(@Parameter(description = "文件保存路径") @RequestParam(name = "filePath") String filePath,
                              @Parameter(description = "文件") @RequestPart(name = "file") MultipartFile file) {
        try {
            // 路径验证
            if (filePath == null || !filePath.matches("^[a-zA-Z0-9/._+-]+\\.(jpg|jpeg|png|gif|bmp|webp)$")) {
                throw new IllegalArgumentException("无效的文件路径");
            }

            // 文件类型验证
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                throw new IllegalArgumentException("只允许上传图片文件");
            }

            String result = storageService.uploadImage(filePath, file);
            log.info("文件上传成功，路径: {}，链接：{}", filePath, result);
            return result;
        } catch (IllegalArgumentException e) {
            throw new BusinessException(ErrorCodeEnum.INVALID_PARAMETER, e);
        } catch (Exception e) {
            log.error("上传失败: {}", e.getMessage(), e);
            return "上传失败: " + e.getMessage();
        }
    }
}
