package com.stp.xxx.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mybatisflex.annotation.Column;
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

    @Column(value = "is_deleted", isLogicDelete = true)
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(value = "create_time", onInsertValue = "now()")
    private LocalDateTime createTime;

    /**
     * 最后修改时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column( value = "update_time", onInsertValue = "now()", onUpdateValue = "now()")
    private LocalDateTime updateTime;
}