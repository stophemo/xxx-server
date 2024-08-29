package com.stp.xxx.entity.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础实体类，包含系统字段
 */
@Data
@EqualsAndHashCode
public abstract class BaseEntity implements Serializable {

    /**
     * 记录是否已删除
     */
    @TableLogic(value = "0", delval = "1")
    @TableField(fill = FieldFill.INSERT, value = "is_deleted", select = false)
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT, value = "create_time", select = false)
    private LocalDateTime createTime;

    /**
     * 最后修改时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE, value = "update_time", select = false)
    private LocalDateTime updateTime;
}