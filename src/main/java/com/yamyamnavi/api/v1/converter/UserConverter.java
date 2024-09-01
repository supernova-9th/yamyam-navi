package com.yamyamnavi.api.v1.converter;

import com.yamyamnavi.api.v1.request.UserCreateRequest;
import com.yamyamnavi.api.v1.response.UserResponse;
import com.yamyamnavi.domain.user.User;
import com.yamyamnavi.storage.user.UserEntity;
import com.yamyamnavi.support.utils.GeometryUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", imports = GeometryUtils.class)
public interface UserConverter {

    /**
     * UserCreateRequest를 User 도메인 객체로 변환합니다.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "false")
    User convertToUser(UserCreateRequest request);

    /**
     * User 도메인 객체를 UserResponse로 변환합니다.
     */
    UserResponse convertToUserResponse(User user);

    /**
     * User 도메인 객체를 UserEntity로 변환합니다.
     */
    @Mapping(target = "latitude", source = "latitude")
    @Mapping(target = "longitude", source = "longitude")
    UserEntity convertToEntity(User user);

    /**
     * UserEntity를 User 도메인 객체로 변환합니다.
     */
    @Mapping(target = "latitude", source = "latitude")
    @Mapping(target = "longitude", source = "longitude")
    User convertToDomain(UserEntity entity);

    /**
     * User 도메인 객체의 정보로 UserEntity를 업데이트합니다.
     */
    void updateEntityFromDomain(User user, @MappingTarget UserEntity entity);

}