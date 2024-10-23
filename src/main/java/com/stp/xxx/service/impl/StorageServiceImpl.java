package com.stp.xxx.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.cache.impl.FIFOCache;
import com.stp.xxx.config.exception.BusinessException;
import com.stp.xxx.config.result.ResultEntity;
import com.stp.xxx.constant.SysContant;
import com.stp.xxx.dto.alist.fs.*;
import com.stp.xxx.service.AlistService;
import com.stp.xxx.service.StorageService;
import com.stp.xxx.util.TokenUtil;
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
    private String alistUsername;
    @Value("${alist.password}")
    private String alistPassword;

    public String getAlistCacheToken() {
        FIFOCache<String, String> tokenCache = TokenUtil.getTokenCache();
        String token = tokenCache.get(SysContant.ALIST_TOKEN);
        if (token == null || token.isEmpty()) {
            String tokenValue = alistService.getTokenValue(alistUsername, alistPassword);
            tokenCache.put(SysContant.ALIST_TOKEN, tokenValue);
        }
        return tokenCache.get(SysContant.ALIST_TOKEN);
    }



    @Override
    public FilesGetOutputDTO listFiles(FilesGetInputDTO inputDTO) {
        String token = (String) StpUtil.getSession().get(SysContant.ALIST_TOKEN);

        return alistService.listFiles(token, inputDTO).getData();
    }

    @Override
    public FileInfoGetOutputDTO getFileInfo(FileInfoGetInputDTO inputDTO) {
        String token = getAlistCacheToken();
        return alistService.getFileInfo(token, inputDTO).getData();
    }

    /**
     * @param asTask   是否添加为任务
     * @param filePath 经过URL编码的完整文件路径
     * @param file     文件
     */
    @Override
    public UploadResult uploadFileByForm(Boolean asTask, String filePath, MultipartFile file) {
        String token = getAlistCacheToken();
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
        String token = getAlistCacheToken();
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
