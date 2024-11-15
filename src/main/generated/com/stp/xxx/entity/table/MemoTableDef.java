package com.stp.xxx.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class MemoTableDef extends TableDef {

    /**
     * <p>
 备忘录表
 </p>

 @author jackman
 @since 2024-08-26
     */
    public static final MemoTableDef MEMO = new MemoTableDef();

    public final QueryColumn ID = new QueryColumn(this, "id");

    public final QueryColumn TAG = new QueryColumn(this, "tag");

    public final QueryColumn TITLE = new QueryColumn(this, "title");

    public final QueryColumn USER_ID = new QueryColumn(this, "user_id");

    public final QueryColumn CONTENT = new QueryColumn(this, "content");

    public final QueryColumn PRIORITY = new QueryColumn(this, "priority");

    /**
     * 记录是否已删除
     */
    public final QueryColumn IS_DELETED = new QueryColumn(this, "is_deleted");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 最后修改时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, TAG, TITLE, USER_ID, CONTENT, PRIORITY, CREATE_TIME, UPDATE_TIME};

    public MemoTableDef() {
        super("", "t_feat_memo");
    }

    private MemoTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public MemoTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new MemoTableDef("", "t_feat_memo", alias));
    }

}
