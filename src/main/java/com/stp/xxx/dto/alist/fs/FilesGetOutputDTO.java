package com.stp.xxx.dto.alist.fs;

import lombok.Data;

import java.util.List;

@Data
public class FilesGetOutputDTO {

    /**
     * 内容
     */
    private Content[] content;
    private String header;
    private String provider;
    /**
     * 说明
     */
    private String readme;
    /**
     * 总数
     */
    private long total;
    /**
     * 是否可写入
     */
    private boolean write;

    @Data
    public static class Content {
        /**
         * 创建时间
         */
        private String created;
        private Object hashInfo;
        private String hashinfo;
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
}
