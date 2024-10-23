package com.stp.xxx.service;

import com.stp.xxx.dto.alist.fs.*;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    /**
     * 列出文件目录
     */
    FilesGetOutputDTO listFiles(FilesGetInputDTO inputDTO);

    /**
     * 获取某个文件/目录信息
     */
    FileInfoGetOutputDTO getFileInfo(FileInfoGetInputDTO inputDTO);

    /**
     * 通过表单上传文件
     *
     * @param asTask 是否添加为任务
     * @param filePath 经过URL编码的完整文件路径
     * @param file 文件
     */
    UploadResult uploadFileByForm(
            Boolean asTask,
            String filePath,
            MultipartFile file);

    /**
     * 通过流上传文件
     *
     * @param asTask 是否添加为任务
     * @param filePath 经过URL编码的完整文件路径
     * @param file 文件
     */
    UploadResult uploadFileByForm(
            Boolean asTask,
            String filePath,
            byte[] file
    );


}
