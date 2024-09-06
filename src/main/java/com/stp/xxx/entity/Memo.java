package com.stp.xxx.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import com.stp.xxx.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 备忘录表
 * </p>
 *
 * @author jackman
 * @since 2024-08-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table("t_feat_memo")
public class Memo extends BaseEntity {

    @Id(keyType = KeyType.Generator, value = KeyGenerators.uuid)
    private String id;

    @Column(value = "user_id", comment = "用户ID")
    private String userId;

    @Column(value = "title", comment = "标题")
    private String title;

    @Column(value = "content", comment = "内容")
    private String content;

    @Column(value = "tags", comment = "标签")
    private String tags;

    @Column(value = "priority", comment = "优先级")
    private Integer priority;
}
