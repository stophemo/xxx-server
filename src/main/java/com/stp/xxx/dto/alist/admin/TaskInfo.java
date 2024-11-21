package com.stp.xxx.dto.alist.admin;

import lombok.Data;

@Data
public class TaskInfo {
    /**
     * 错误信息
     */
    private String error;
    /**
     * id
     */
    private String id;
    /**
     * 任务名
     */
    private String name;
    /**
     * 进度
     */
    private Long progress;
    /**
     * 任务完成状态
     */
    private String state;
    private String status;
}
