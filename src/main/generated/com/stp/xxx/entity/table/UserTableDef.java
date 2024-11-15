package com.stp.xxx.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class UserTableDef extends TableDef {

    /**
     * <p>
 用户表
 </p>

 @author stophemo
 @since 2024-08-20
     */
    public static final UserTableDef USER = new UserTableDef();

    public final QueryColumn ID = new QueryColumn(this, "id");

    public final QueryColumn NAME = new QueryColumn(this, "name");

    public final QueryColumn ROLE = new QueryColumn(this, "role");

    public final QueryColumn EMAIL = new QueryColumn(this, "email");

    public final QueryColumn PHONE = new QueryColumn(this, "phone");

    public final QueryColumn AVATAR = new QueryColumn(this, "avatar");

    public final QueryColumn GENDER = new QueryColumn(this, "gender");

    public final QueryColumn STATUS = new QueryColumn(this, "status");

    public final QueryColumn ORDINAL = new QueryColumn(this, "ordinal");

    public final QueryColumn NICKNAME = new QueryColumn(this, "nickname");

    public final QueryColumn PASSWORD = new QueryColumn(this, "password");

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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, NAME, ROLE, EMAIL, PHONE, AVATAR, GENDER, STATUS, ORDINAL, NICKNAME, PASSWORD, CREATE_TIME, UPDATE_TIME};

    public UserTableDef() {
        super("", "t_sys_user");
    }

    private UserTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public UserTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new UserTableDef("", "t_sys_user", alias));
    }

}
