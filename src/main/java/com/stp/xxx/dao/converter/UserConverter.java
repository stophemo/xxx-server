package com.stp.xxx.dao.converter;

import com.stp.xxx.dto.UserDTO;
import com.stp.xxx.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户表 Mapstruct转换接口.
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    /**
     * The constant INSTANCE.
     */
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);


    /**
     * To dto base station dto.
     *
     * @param entity the entity
     * @return the base station dto
     */
    UserDTO toDto(User entity);

    /**
     * dto to entity.
     *
     * @param dto the entity
     * @return the base station brief dto
     */
    User toEntity(UserDTO dto);

    /**
     * To dto list.
     *
     * @param entities the entities
     * @return the list
     */
    List<UserDTO> toDtoList(List<User> entities);

    /**
     * To entity list.
     *
     * @param dtos the dtos
     * @return the list
     */
    List<User> toEntities(List<UserDTO> dtos);
}

