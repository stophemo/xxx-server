-- liquibase formatted sql

-- ChangeSet ID: 1
-- Author: stophemo
-- Description: 创建用户表
CREATE TABLE t_sys_user
(
    id          VARCHAR(32) PRIMARY KEY COMMENT '主键ID',
    email       VARCHAR(64)  NOT NULL COMMENT '邮箱',
    phone       VARCHAR(15)  NOT NULL COMMENT '手机',
    name        VARCHAR(64)  NOT NULL COMMENT '用户名',
    nickname    VARCHAR(64)           DEFAULT '' COMMENT '昵称',
    password    VARCHAR(256) NOT NULL COMMENT '密码',
    gender      TINYINT(1)   NOT NULL DEFAULT 0 COMMENT '性别：1=男, 2=女, 0=未知',
    avatar      VARCHAR(256)          DEFAULT '' COMMENT '头像',
    status      TINYINT(1)   NOT NULL DEFAULT 1 COMMENT '状态：0=禁用, 1=启用',
    ordinal     INT          NOT NULL AUTO_INCREMENT COMMENT '序号',
    is_deleted  TINYINT(1)   NOT NULL COMMENT '逻辑删除标记',
    create_time DATETIME     NOT NULL COMMENT '创建时间',
    update_time DATETIME     NOT NULL COMMENT '更新时间',
    UNIQUE INDEX unique_email (email),
    UNIQUE INDEX unique_phone (phone),
    UNIQUE INDEX unique_name (name),
    UNIQUE INDEX unique_ordinal (ordinal)
) COMMENT '用户表';

-- ChangeSet ID: 2
-- Author: stophemo
-- Description: 创建备忘录表
CREATE TABLE t_feat_memo
(
    id          VARCHAR(32) PRIMARY KEY COMMENT '唯一标识符',
    user_id     BIGINT       NOT NULL COMMENT '用户ID，关联到用户表',
    title       VARCHAR(255) NOT NULL COMMENT '备忘录标题',
    content     TEXT COMMENT '备忘录内容',
    tags        VARCHAR(255) COMMENT '备忘录标签，使用逗号分隔',
    priority    ENUM ('LOW', 'MEDIUM', 'HIGH') DEFAULT 'MEDIUM' COMMENT '优先级',
    is_deleted  TINYINT(1)   NOT NULL COMMENT '逻辑删除标记',
    create_time DATETIME     NOT NULL COMMENT '创建时间',
    update_time DATETIME     NOT NULL COMMENT '更新时间',
    INDEX idx_user_id (user_id),
    INDEX idx_title (title),
    INDEX idx_tags (tags)
) COMMENT ='备忘录表';
