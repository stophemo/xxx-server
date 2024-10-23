package com.stp.xxx.dto.alist.fs;

import lombok.Data;

@Data
public class FileInfoGetOutputDTO {
    /**
     * 创建时间
     */
    private String created;
    private Object hashInfo;
    private String hashinfo;
    private String header;
    /**
     * 是否是文件夹
     */
    private boolean isDir;
    /**
     * 修改时间
     */
    private String modified;
    /**
     * 文件名
     */
    private String name;
    private String provider;
    /**
     * 原始url
     */
    private String raw_url;
    /**
     * 说明
     */
    private String readme;
    private Object related;
    /**
     * 签名
     */
    private String sign;
    /**
     * 大小
     */
    private long size;
    /**
     * 缩略图
     */
    private String thumb;
    /**
     * 类型
     */
    private long type;
}
