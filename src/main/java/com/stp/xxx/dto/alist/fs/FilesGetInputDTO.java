package com.stp.xxx.dto.alist.fs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FilesGetInputDTO {
    /**
     * 页数
     */
    private Long page;
    /**
     * 密码
     */
    private String password;
    /**
     * 路径
     */
    private String path;
    /**
     * 每页数目
     */
    private Long perPage;
    /**
     * 是否强制刷新
     */
    private Boolean refresh;
}
