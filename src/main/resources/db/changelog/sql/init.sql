DROP TABLE IF EXISTS t_sys_user;
CREATE TABLE t_sys_user
(
    id          CHAR(32) PRIMARY KEY COMMENT '主键ID',
    email       VARCHAR(64) UNIQUE COMMENT '邮箱',
    phone       VARCHAR(15)  NOT NULL UNIQUE COMMENT '手机',
    name        VARCHAR(64)  NOT NULL UNIQUE COMMENT '用户名',
    nickname    VARCHAR(64)           DEFAULT '' COMMENT '昵称',
    password    VARCHAR(256) NOT NULL COMMENT '密码',
    gender      TINYINT(1)            DEFAULT 0 COMMENT '性别：1=男, 2=女, 0=未知',
    avatar      VARCHAR(1024)         DEFAULT '' COMMENT '头像',
    status      TINYINT(1)   NOT NULL DEFAULT 1 COMMENT '状态：0=禁用, 1=启用',
    role        VARCHAR(64)  NOT NULL DEFAULT '普通用户' COMMENT '命运',
    is_deleted  TINYINT(1)   NOT NULL DEFAULT 0 COMMENT '逻辑删除标记',
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '用户表';

DROP TABLE IF EXISTS t_feat_memo;
CREATE TABLE t_feat_memo
(
    id          CHAR(32) PRIMARY KEY COMMENT '唯一标识符',
    user_id     CHAR(32)     NOT NULL COMMENT '用户ID，关联到用户表',
    title       VARCHAR(255) NOT NULL COMMENT '备忘录标题',
    content     TEXT COMMENT '备忘录内容',
    tag         VARCHAR(255) DEFAULT 'DEFAULT' COMMENT '标签',
    priority    INT          NOT NULL DEFAULT 0 COMMENT '优先级',
    is_deleted  TINYINT(1)   NOT NULL DEFAULT 0 COMMENT '逻辑删除标记',
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (user_id) REFERENCES t_sys_user (id)
) COMMENT '备忘录表';

CREATE INDEX idx_user_id ON t_feat_memo (user_id);