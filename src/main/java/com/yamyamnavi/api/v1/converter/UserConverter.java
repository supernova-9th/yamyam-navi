package com.yamyamnavi.api.v1.converter;

import com.yamyamnavi.api.v1.request.UserCreateRequest;
import com.yamyamnavi.api.v1.response.UserResponse;
import com.yamyamnavi.domain.user.User;
import com.yamyamnavi.storage.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserConverter {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "active", constant = "false")
    @Mapping(target = "latitude", ignore = true)
    @Mapping(target = "longitude", ignore = true)
    User convertToUser(UserCreateRequest request);

    @Mapping(target = "id", source = "id")
    UserResponse convertToUserResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "latitude", source = "latitude")
    @Mapping(target = "longitude", source = "longitude")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserEntity convertToEntity(User user);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "latitude", source = "latitude")
    @Mapping(target = "longitude", source = "longitude")
    User convertToDomain(UserEntity entity);

    default void updateEntityFromDomain(User user, @MappingTarget UserEntity entity) {
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setActive(user.isActive());
        entity.setLatitude(user.getLatitude());
        entity.setLongitude(user.getLongitude());
    }
}