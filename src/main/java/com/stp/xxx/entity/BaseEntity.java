package com.stp.xxx.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类，包含系统字段
 */
@Data
@EqualsAndHashCode
public abstract class BaseEntity implements Serializable {

    /**
     * 记录是否已删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT, value = "is_deleted", select = false)
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT, value = "create_time", select = false)
    private Date createTime;

    /**
     * 最后修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE, value = "update_time", select = false)
    private Date updateTime;
}
