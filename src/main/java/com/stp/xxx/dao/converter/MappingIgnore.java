package com.stp.xxx.dao.converter;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
@Mappings(value = {
        @Mapping(target = "isDeleted", ignore = true),
        @Mapping(target = "createTime", ignore = true),
        @Mapping(target = "updateTime", ignore = true)
})
public @interface MappingIgnore {}