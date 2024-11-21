package com.stp.xxx.dto.alist.fs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
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
