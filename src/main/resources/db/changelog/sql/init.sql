CREATE TABLE t_sys_user
(
    id         BIGINT AUTO_INCREMENT COMMENT '主键ID' PRIMARY KEY,
    email      VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '邮箱',
    phone      VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '手机',
    name       VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户名',
    nickname   VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '昵称',
    password   VARCHAR(256) NOT NULL DEFAULT '' COMMENT '密码',
    gender     TINYINT(1)   NOT NULL DEFAULT 0 COMMENT '性别：1=男, 2=女, 0=未知',
    avatar     VARCHAR(256) NOT NULL DEFAULT '' COMMENT '头像',
    status     TINYINT(1)   NOT NULL DEFAULT 1 COMMENT '状态：0=禁用, 1=启用',
    ordinal INT          NOT NULL DEFAULT 0 COMMENT '序号',
    is_deleted TINYINT(1)   NOT NULL DEFAULT 0 COMMENT '删除标记：1=已删除, 0=未删除',
    created_time DATETIME     NOT NULL COMMENT '创建时间',
    updated_time DATETIME     NOT NULL COMMENT '更新时间'
)
    COMMENT '用户表' ROW_FORMAT = DYNAMIC;
