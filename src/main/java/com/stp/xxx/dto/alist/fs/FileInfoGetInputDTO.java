package com.stp.xxx.dto.alist.fs;

import lombok.Data;

@Data
public class FileInfoGetInputDTO {
    private Long page;
    private Long perPage;
    /**
     * 密码
     */
    private String password;
    /**
     * 路径
     */
    private String path;
    /**
     * 强制 刷新
     */
    private Boolean refresh;
}
