package com.stp.xxx.dto;

public class MemoDTO {
    /**
     * 唯一标识符
     */
    private Long id;

    /**
     * 用户ID，关联到用户表
     */
    private Long userId;

    /**
     * 备忘录标题
     */
    private String title;

    /**
     * 备忘录内容
     */
    private String content;

    /**
     * 备忘录标签，使用逗号分隔
     */
    private String tags;

    /**
     * 优先级
     */
    private String priority;
}
