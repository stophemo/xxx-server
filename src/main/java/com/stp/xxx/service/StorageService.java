package com.stp.xxx.service;

import com.stp.xxx.dto.alist.fs.FilesGetInputDTO;
import com.stp.xxx.dto.alist.fs.FilesGetOutputDTO;
import com.stp.xxx.dto.alist.fs.UploadResult;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    FilesGetOutputDTO listFiles(FilesGetInputDTO inputDTO);

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
}
