package com.stp.xxx.service.impl;

import cn.hutool.core.util.IdUtil;
import com.stp.xxx.config.exception.BusinessException;
import com.stp.xxx.config.result.ResultEntity;
import com.stp.xxx.dto.alist.fs.FilesGetInputDTO;
import com.stp.xxx.dto.alist.fs.FilesGetOutputDTO;
import com.stp.xxx.dto.alist.fs.UploadResult;
import com.stp.xxx.service.AlistService;
import com.stp.xxx.service.StorageService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    private AlistService alistService;
    @Value("${alist.username}")
    private String username;
    @Value("${alist.password}")
    private String password;

    @Override
    public FilesGetOutputDTO listFiles(FilesGetInputDTO inputDTO) {
        String token = alistService.getTokenValue(username, password);
        return alistService.listFiles(token, inputDTO).getData();
    }

    /**
     * @param asTask   是否添加为任务
     * @param filePath 经过URL编码的完整文件路径
     * @param file     文件
     */
    @Override
    public UploadResult uploadFileByForm(Boolean asTask, String filePath, MultipartFile file) {
        String token = alistService.getTokenValue(username, password);
        try {
            // 使用 URLEncoder.encode 进行 URL 编码，指定编码类型为 UTF-8
            String encodedFilePath = URLEncoder.encode(filePath, StandardCharsets.UTF_8);
            // 替换空格符，以保持URL路径中分隔符的正常格式
            filePath = encodedFilePath.replace("+", "%20");
        } catch (Exception e) {
            throw new BusinessException("文件路径编码失败");
        }
        String contentType = MediaType.MULTIPART_FORM_DATA_VALUE;

        ResultEntity<UploadResult> uploadResultResultEntity = alistService.uploadFile(
                token, contentType, String.valueOf(file.getSize()), filePath, String.valueOf(asTask), file);
        return uploadResultResultEntity.getData();
    }

    /**
     * 通过流上传文件
     *
     * @param asTask   是否添加为任务
     * @param filePath 经过URL编码的完整文件路径
     * @param file     文件
     */
    @Override
    public UploadResult uploadFileByForm(Boolean asTask, String filePath, byte[] file) {
        String token = alistService.getTokenValue(username, password);
        try {
            // 使用 URLEncoder.encode 进行 URL 编码，指定编码类型为 UTF-8
            String encodedFilePath = URLEncoder.encode(filePath, StandardCharsets.UTF_8);
            // 替换空格符，以保持URL路径中分隔符的正常格式
            filePath = encodedFilePath.replace("+", "%20");
        } catch (Exception e) {
            throw new BusinessException("文件路径编码失败");
        }
        String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;

        ResultEntity<UploadResult> uploadResultResultEntity = alistService.uploadFile(
                token, contentType,  filePath, String.valueOf(asTask), file);

        return uploadResultResultEntity.getData();
    }
}
