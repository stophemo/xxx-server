CREATE TABLE t_sys_user
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    email        VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '邮箱',
    phone        VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '手机',
    name         VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户名',
    nickname     VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '昵称',
    password     VARCHAR(256) NOT NULL DEFAULT '' COMMENT '密码',
    gender       TINYINT(1)   NOT NULL DEFAULT 0 COMMENT '性别：1=男, 2=女, 0=未知',
    avatar       VARCHAR(256) NOT NULL DEFAULT '' COMMENT '头像',
    status       TINYINT(1)   NOT NULL DEFAULT 1 COMMENT '状态：0=禁用, 1=启用',
    ordinal      INT          NOT NULL DEFAULT 0 COMMENT '序号',
    is_deleted   BOOLEAN               DEFAULT FALSE COMMENT '逻辑删除标记',
    created_time DATETIME     NOT NULL COMMENT '创建时间',
    updated_time DATETIME     NOT NULL COMMENT '更新时间'
) COMMENT '用户表';


CREATE TABLE t_feat_memo
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '唯一标识符',
    user_id     BIGINT COMMENT '用户ID，关联到用户表',
    title       VARCHAR(255) NOT NULL COMMENT '备忘录标题',
    content     TEXT COMMENT '备忘录内容',
    tags        VARCHAR(255) COMMENT '备忘录标签，使用逗号分隔',
    priority    ENUM ('LOW', 'MEDIUM', 'HIGH') DEFAULT 'MEDIUM' COMMENT '优先级',
    is_deleted  BOOLEAN                        DEFAULT FALSE COMMENT '逻辑删除标记',
    create_time TIMESTAMP                      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP                      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT ='备忘录表';

