package com.stp.xxx.dto.alist.fs;

import lombok.Data;

@Data
public class UploadResult {

    private Task task;

    @Data
    public static class Task {
        private String id;
        private String name;
        private long state;
        private long progress;
        private String status;
        private String error;
    }
}
