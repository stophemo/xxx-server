package com.stp.xxx.dto.alist.fs;

import lombok.Data;

@Data
public class UploadResult {

    private Task task;

    @Data
    public static class Task {
        private String error;
        private String id;
        private String name;
        private long progress;
        private long state;
        private String status;
    }
}
