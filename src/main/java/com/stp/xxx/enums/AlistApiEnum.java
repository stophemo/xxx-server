package com.stp.xxx.enums;

import lombok.Getter;
import org.dromara.hutool.core.text.StrUtil;
import org.springframework.beans.factory.annotation.Value;

/**
 * Aist API 枚举
 * 文档地址：<a href="https://alist.nn.ci/zh/guide/api/auth.html">...</a>
 */
public enum AlistApiEnum {
    // auth 相关 API
    AUTH_TOKEN_GET("/api/auth/login", "token获取"),
    AUTH_TOKEN_GET_HASH("/api/auth/login/hash", "token获取hash"),
    AUTH_GENERATE_2FA_KEY("/api/auth/2fa/generate", "生成2FA密钥"),
    AUTH_VERIFY_2FA_CODE("/api/auth/2fa/verify", "验证2FA code"),
    AUTH_GET_CURRENT_USER_INFO("/api/me", "获取当前用户信息"),

    // fs 相关 API
    FS_LIST_FILES("/api/fs/list", "列出文件目录"),
    FS_GET_FILE_INFO("/api/fs/get", "获取某个文件/目录信息"),
    FS_GET_DIRS("/api/fs/dirs", "获取目录"),
    FS_SEARCH_FILES("/api/fs/search", "搜索文件或文件夹"),
    FS_CREATE_DIR("/api/fs/mkdir", "新建文件夹"),
    FS_RENAME_FILE("/api/fs/rename", "重命名文件"),
    FS_BATCH_RENAME("/api/fs/batch_rename", "批量重命名"),
    FS_REGEX_RENAME("/api/fs/regex_rename", "正则重命名"),
    FS_FORM_UPLOAD_FILE("/api/fs/form", "表单上传文件"),
    FS_MOVE_FILE("/api/fs/move", "移动文件"),
    FS_COPY_FILE("/api/fs/copy", "复制文件"),
    FS_REMOVE_FILE_OR_DIR("/api/fs/remove", "删除文件或文件夹"),
    FS_REMOVE_EMPTY_DIR("/api/fs/remove_empty_directory", "删除空文件夹"),
    FS_RECURSIVE_MOVE("/api/fs/recursive_move", "聚合移动"),
    FS_STREAMING_UPLOAD_FILE("/api/fs/put", "流式上传文件"),
    FS_ADD_OFFLINE_DOWNLOAD("/api/fs/add_offline_download", "添加离线下载"),

    // public 相关 API
    PUBLIC_GET_SITE_SETTINGS("/api/public/settings", "获取站点设置"),
    PUBLIC_PING_TEST("/ping", "ping检测"),

    // admin 相关 API
    // admin/meta
    ADMIN_META_LIST("/api/admin/meta/list", "列出元信息"),
    ADMIN_META_GET("/api/admin/meta/get", "获取元信息"),
    ADMIN_META_CREATE("/api/admin/meta/create", "新增元信息"),
    ADMIN_META_UPDATE("/api/admin/meta/update", "更新元信息"),
    ADMIN_META_DELETE("/api/admin/meta/delete", "删除元信息"),

    // admin/user
    ADMIN_USER_LIST("/api/admin/user/list", "列出所有用户"),
    ADMIN_USER_GET("/api/admin/user/get", "列出某个用户"),
    ADMIN_USER_CREATE("/api/admin/user/create", "新建用户"),
    ADMIN_USER_UPDATE("/api/admin/user/update", "更新用户信息"),
    ADMIN_USER_CANCEL_2FA("/api/admin/user/cancel_2fa", "取消某个用户的两步验证"),
    ADMIN_USER_DELETE("/api/admin/user/delete", "删除用户"),
    ADMIN_USER_DEL_CACHE("/api/admin/user/del_cache", "删除用户缓存"),

    // admin/storage
    ADMIN_STORAGE_LIST("/api/admin/storage/list", "列出存储列表"),
    ADMIN_STORAGE_ENABLE("/api/admin/storage/enable", "启用存储"),
    ADMIN_STORAGE_DISABLE("/api/admin/storage/disable", "禁用存储"),
    ADMIN_STORAGE_CREATE("/api/admin/storage/create", "创建存储"),
    ADMIN_STORAGE_UPDATE("/api/admin/storage/update", "更新存储"),
    ADMIN_STORAGE_GET("/api/admin/storage/get", "查询指定存储信息"),
    ADMIN_STORAGE_DELETE("/api/admin/storage/delete", "删除指定存储"),
    ADMIN_STORAGE_LOAD_ALL("/api/admin/storage/load_all", "重新加载所有存储"),

    // admin/driver
    ADMIN_DRIVER_LIST("/api/admin/driver/list", "查询所有驱动配置模板列表"),
    ADMIN_DRIVER_NAMES("/api/admin/driver/names", "列出驱动名列表"),
    ADMIN_DRIVER_INFO("/api/admin/driver/info", "列出特定驱动信息"),

    // admin/setting
    ADMIN_SETTING_LIST("/api/admin/setting/list", "列出设置"),
    ADMIN_SETTING_GET("/api/admin/setting/get", "获取某项设置"),
    ADMIN_SETTING_SAVE("/api/admin/setting/save", "保存设置"),
    ADMIN_SETTING_DELETE("/api/admin/setting/delete", "删除设置"),
    ADMIN_SETTING_RESET_TOKEN("/api/admin/setting/reset_token", "重置令牌"),
    ADMIN_SETTING_SET_ARIA2("/api/admin/setting/set_aria2", "设置aria2"),
    ADMIN_SETTING_SET_QBIT("/api/admin/setting/set_qbit", "设置qBittorrent"),

    // admin/task/upload
    ADMIN_TASK_UPLOAD_DONE("/api/admin/task/upload/done", "获取已完成任务"),
    ADMIN_TASK_UPLOAD_INFO("/api/admin/task/upload/info", "获取任务信息"),
    ADMIN_TASK_UPLOAD_UNDONE("/api/admin/task/upload/undone", "获取未完成任务"),
    ADMIN_TASK_UPLOAD_DELETE("/api/admin/task/upload/delete", "删除任务"),
    ADMIN_TASK_UPLOAD_CANCEL("/api/admin/task/upload/cancel", "取消任务"),
    ADMIN_TASK_UPLOAD_RETRY("/api/admin/task/upload/retry", "重试任务"),
    ADMIN_TASK_UPLOAD_CLEAR_DONE("/api/admin/task/upload/clear_done", "清除已完成任务"),
    ADMIN_TASK_UPLOAD_CLEAR_SUCCEEDED("/api/admin/task/upload/clear_succeeded", "清除已成功任务");

    @Value("${alist.server-url}")
    private String serverUrl;

    private final String url;

    @Getter
    private final String comment;

    AlistApiEnum(String url, String comment) {
        this.url = url;
        this.comment = comment;
    }

    public String getUrl() {
        return StrUtil.format("http://{}{}", serverUrl, url);
    }

}