package com.stp.xxx.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 通用Mapper
 */
public interface CommonMapper {

    @Select("""
            SELECT COLUMN_COMMENT
              FROM INFORMATION_SCHEMA.COLUMNS
             WHERE TABLE_NAME = #{tableName}
               AND COLUMN_NAME = #{fieldName}
               AND TABLE_SCHEMA = 'xxx';
            """)
    String getFieldCommentByName(@Param("tableName") String tableName, @Param("fieldName") String fieldName);

    @Select("""
            SELECT COLUMN_COMMENT
              FROM INFORMATION_SCHEMA.COLUMNS
             WHERE TABLE_NAME = #{tableName}
               AND COLUMN_KEY = 'PRI'
               AND TABLE_SCHEMA = 'xxx';
            """)
    String getPrimaryKeyComment(@Param("tableName") String tableName);
}
