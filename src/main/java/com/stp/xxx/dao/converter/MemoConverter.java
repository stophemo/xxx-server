package com.stp.xxx.dao.converter;


import com.stp.xxx.dto.MemoDTO;
import com.stp.xxx.entity.Memo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

import com.stp.xxx.dto.MemoDTO;
import com.stp.xxx.entity.Memo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户表 Mapstruct转换接口.
 */
@Mapper(componentModel = "spring")
public interface MemoConverter {

    /**
     * The constant INSTANCE.
     */
    MemoConverter INSTANCE = Mappers.getMapper(MemoConverter.class);


    /**
     * To dto base station dto.
     *
     * @param entity the entity
     * @return the base station dto
     */
    MemoDTO toDto(Memo entity);

    /**
     * dto to entity.
     *
     * @param dto the entity
     * @return the base station brief dto
     */
    @MappingIgnore
    Memo toEntity(MemoDTO dto);

    /**
     * To dto list.
     *
     * @param entities the entities
     * @return the list
     */
    List<MemoDTO> toDtoList(List<Memo> entities);

    /**
     * To entity list.
     *
     * @param dtos the dtos
     * @return the list
     */
    @MappingIgnore
    List<Memo> toEntities(List<MemoDTO> dtos);
}

