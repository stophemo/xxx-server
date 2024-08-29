package com.stp.xxx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.stp.xxx.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 备忘录表
 * </p>
 *
 * @author jackman
 * @since 2024-08-26
 */
@Getter
@Setter
@TableName("t_feat_memo")
public class Memo extends BaseEntity {

    /**
     * 唯一标识符
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID，关联到用户表
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 备忘录标题
     */
    @TableField("title")
    private String title;

    /**
     * 备忘录内容
     */
    @TableField("content")
    private String content;

    /**
     * 备忘录标签，使用逗号分隔
     */
    @TableField("tags")
    private String tags;

    /**
     * 优先级
     */
    @TableField("priority")
    private String priority;
}
