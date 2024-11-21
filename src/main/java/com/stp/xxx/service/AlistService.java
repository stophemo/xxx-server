package com.stp.xxx.service;

import com.stp.xxx.config.result.ResultEntity;
import com.stp.xxx.dto.alist.admin.TaskInfo;
import com.stp.xxx.dto.alist.auth.LoginResult;
import com.stp.xxx.dto.alist.fs.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@FeignClient(name = "alist", url = "${alist.server.host}:${alist.server.port}")
public interface AlistService {

    // ----------------------------  auth  -----------------------------
    @PostMapping("/api/auth/login")
    ResultEntity<LoginResult> getToken(@RequestBody Map<String, String> map);

    default String getTokenValue(String username, String password) {
        Map<String, String> map = Map.of("username", username, "password", password);
        ResultEntity<LoginResult> result = getToken(map);
        return result.getData().getToken();
    }


    // ----------------------------  fs  -----------------------------
    @PostMapping("/api/fs/list")
    ResultEntity<FilesGetOutputDTO> listFiles(
            @RequestHeader("Authorization") String token,
            @RequestBody FilesGetInputDTO inputDTO
    );

    @PostMapping("/api/fs/get")
    ResultEntity<FileInfoGetOutputDTO> getFileInfo(
            @RequestHeader("Authorization") String token,
            @RequestBody FileInfoGetInputDTO inputDTO
    );

    @PutMapping(value = "/api/fs/form", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResultEntity<UploadResult> uploadFileByform(
            @RequestHeader("Authorization") String token,
            @RequestHeader("Content-Type") String contentType,
            @RequestHeader("File-Path") String filePath,
            @RequestHeader(value = "As-Task", required = false) String asTask,
            @RequestPart("file") MultipartFile file
    );

    @PutMapping(value = "/api/fs/put", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    ResultEntity<UploadResult> uploadFileByStream(
            @RequestHeader("Authorization") String token,
            @RequestHeader("Content-Type") String contentType,
            @RequestHeader("File-Path") String filePath,
            @RequestHeader(value = "As-Task", required = false) String asTask,
            @RequestBody byte[] fileContents
    );

//    @PostMapping(value = "/api/fs/remove", consumes = MediaType.APPLICATION_JSON_VALUE)
//    void removeFilesOrFolders(
//            @RequestHeader("Authorization") String authorization,
//            @RequestBody RemoveFilesOrFoldersRequest request);
//
//    @Data
//    class RemoveFilesOrFoldersRequest {
//        private List<String> names;
//        private String dir;
//    }


    // ----------------------------  admin  -----------------------------
    // 获取任务信息
    @PostMapping("/api/admin/task/upload/info")
    ResultEntity<TaskInfo> getTaskInfo(
            @RequestHeader("Authorization") String token,
            @RequestHeader("Content-Type") String contentType,
            @RequestParam("tid") String taskId
    );
    // 删除任务
    @DeleteMapping("/api/admin/task/upload/delete")
    String deleteTask(
            @RequestHeader("Authorization") String token,
            @RequestParam("tid") String taskId
    );
}
