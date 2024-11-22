package com.stp.xxx.dto.alist.fs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileGetInputDTO {
    private Long page;
    private Long perPage;
    private String password;
    private String path;
    private Boolean refresh;
}
