package com.stp.xxx.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.stp.xxx.config.exception.BusinessException;
import com.stp.xxx.config.result.ResultEntity;
import com.stp.xxx.constant.SysContant;
import com.stp.xxx.dto.alist.admin.TaskInfo;
import com.stp.xxx.dto.alist.fs.*;
import com.stp.xxx.service.AlistService;
import com.stp.xxx.service.StorageService;
import com.stp.xxx.util.TokenUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.cache.impl.FIFOCache;
import org.dromara.hutool.core.io.file.FileUtil;
import org.dromara.hutool.core.regex.ReUtil;
import org.dromara.hutool.core.text.StrUtil;
import org.dromara.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    private AlistService alistService;
    @Value("${alist.username}")
    private String alistUsername;
    @Value("${alist.password}")
    private String alistPassword;
    @Value("${alist.linkImage-upload-timeout}")
    private int linkImageUploadTimeout;
    @Value("${alist.server.host}")
    private String alistHost;
    @Value("${alist.server.port}")
    private String alistPort;

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

    private String extractDirectory(String path) {
        String pattern = "(.+)/[^/]+$";
        String directory = ReUtil.get(pattern, path, 1);
        if (StrUtil.isNotBlank(directory)) {
            return directory;
        }
        return null;
    }

    @Override
    public FileInfoGetOutputDTO getFileInfo(FileInfoGetInputDTO inputDTO) {
        String directory = extractDirectory(inputDTO.getPath());
        // 获取前先刷新目录
        listFiles(new FilesGetInputDTO(1L, "", directory, 1000L, true));
        String token = (String) StpUtil.getSession().get(SysContant.ALIST_TOKEN);
        return alistService.getFileInfo(token, inputDTO).getData();
    }

    /**
     * @param asTask   是否添加为任务
     * @param filePath 经过URL编码的完整文件路径
     * @param file     文件
     */
    @Override
    public UploadResult uploadFileByForm(Boolean asTask, String filePath, MultipartFile file) {
        String token = (String) StpUtil.getSession().get(SysContant.ALIST_TOKEN);
        try {
            // 使用 URLEncoder.encode 进行 URL 编码，指定编码类型为 UTF-8
            String encodedFilePath = URLEncoder.encode(filePath, StandardCharsets.UTF_8);
            // 替换空格符，以保持URL路径中分隔符的正常格式
            filePath = encodedFilePath.replace("+", "%20");
        } catch (Exception e) {
            throw new BusinessException("文件路径编码失败");
        }
        String contentType = MediaType.MULTIPART_FORM_DATA_VALUE;

        ResultEntity<UploadResult> uploadResultResultEntity = alistService.uploadFileByform(
                token, contentType, filePath, String.valueOf(asTask), file);
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
    public UploadResult uploadFileByStream(Boolean asTask, String filePath, byte[] file) {
        String token = (String) StpUtil.getSession().get(SysContant.ALIST_TOKEN);
        try {
            // 使用 URLEncoder.encode 进行 URL 编码，指定编码类型为 UTF-8
            String encodedFilePath = URLEncoder.encode(filePath, StandardCharsets.UTF_8);
            // 替换空格符，以保持URL路径中分隔符的正常格式
            filePath = encodedFilePath.replace("+", "%20");
        } catch (Exception e) {
            throw new BusinessException("文件路径编码失败");
        }
        String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;

        ResultEntity<UploadResult> uploadResultResultEntity = alistService.uploadFileByStream(
                token, contentType, filePath, String.valueOf(asTask), file);

        return uploadResultResultEntity.getData();
    }


    @Override
    public String uploadImage(String filePath, MultipartFile file) {
        String token = (String) StpUtil.getSession().get(SysContant.ALIST_TOKEN);
        try {
            // 使用 URLEncoder.encode 进行 URL 编码，指定编码类型为 UTF-8
            String encodedFilePath = URLEncoder.encode(filePath, StandardCharsets.UTF_8);
            // 替换空格符，以保持URL路径中分隔符的正常格式
            filePath = encodedFilePath.replace("+", "%20");
        } catch (Exception e) {
            throw new BusinessException("文件路径编码失败");
        }

//        byte[] fileBytes;
//        try {
//            fileBytes = file.getBytes();
//        } catch (IOException e) {
//            log.error("读取文件内容失败: ", e);
//            throw new BusinessException("读取文件内容失败");
//        }

        // 确保 file.getSize() 返回的是一个 long 值
        long fileSize = file.getSize();
        log.info("File size: {}", fileSize); // 打印文件大小，确保它是正确的


        ResultEntity<UploadResult> uploadResultResultEntity = alistService.uploadFileByform(
                token,
                MediaType.MULTIPART_FORM_DATA_VALUE,
                filePath,
                String.valueOf(true),
                file);

        // 轮询上传任务状态
        int sleepTime = 1000;
        int maxRetries = linkImageUploadTimeout / sleepTime;
        for (int i = 0; i < maxRetries; i++) {
            // 获取任务状态
            ResultEntity<TaskInfo> taskInfoResultEntity = alistService.getTaskInfo(
                    token,
                    MediaType.APPLICATION_JSON_VALUE,
                    uploadResultResultEntity.getData().getTask().getId());
            TaskInfo taskInfo = taskInfoResultEntity.getData();
            log.info("taskInfo: {}", JSONUtil.toJsonPrettyStr(taskInfo));
            if (StrUtil.isBlank(taskInfo.getError())) {
                // 等待一段时间后再次轮询
                try {
                    log.info("等待上传任务完成, 剩余等待时间: {}s", (maxRetries - i));
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // 恢复中断状态
                    log.error("上传图片失败, 中断异常: ", e); // 记录更多详细信息
                    throw new BusinessException("上传图片失败");
                }
            } else {
                log.error("上传任务失败: {}", taskInfo.getError());
                throw new BusinessException("上传图片失败: " + taskInfo.getError());
            }

            if (StrUtil.equals(taskInfo.getStatus(), "succeeded")) {
                ResultEntity<FileInfoGetOutputDTO> fileInfo = alistService.getFileInfo(token,
                        new FileInfoGetInputDTO(1L, 0L, "", filePath, true));
                // 拼装图片访问地址
                return StrUtil.format("http://{}:{}/d/{}?sign={}",
                        alistHost,
                        alistPort,
                        filePath,
                        fileInfo.getData().getSign());
            }
        }

        String s = alistService.deleteTask(token, uploadResultResultEntity.getData().getTask().getId());
        log.info("上传图片超时, 删除任务: {}", s);
        throw new BusinessException("上传图片超时, 请检查图片大小后重试");
    }

}
