package com.yamyamnavi.api.v1.converter;

import com.yamyamnavi.api.v1.request.UserCreateRequest;
import com.yamyamnavi.api.v1.response.UserResponse;
import com.yamyamnavi.domain.user.User;
import com.yamyamnavi.storage.user.UserEntity;
import com.yamyamnavi.support.utils.GeometryUtils;
import org.locationtech.jts.geom.Point;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring", imports = GeometryUtils.class)
public interface UserConverter {

    /**
     * UserCreateRequest를 User 도메인 객체로 변환합니다.
     */
    @Mapping(target = "location", expression = "java(GeometryUtils.getPoint(request.longitude(), request.latitude()))")
    User convertToUser(UserCreateRequest request);

    /**
     * User 도메인 객체를 UserResponse로 변환합니다.
     */
    @Mapping(target = "latitude", expression = "java(user.getLocation() != null ? user.getLocation().getY() : null)")
    @Mapping(target = "longitude", expression = "java(user.getLocation() != null ? user.getLocation().getX() : null)")
    UserResponse convertToUserResponse(User user);

    /**
     * User 도메인 객체를 UserEntity로 변환합니다.
     */
    @Mapping(target = "location", source = "location")
    UserEntity convertToEntity(User user);

    /**
     * UserEntity를 User 도메인 객체로 변환합니다.
     */
    User convertToDomain(UserEntity entity);

    /**
     * User 도메인 객체의 정보로 UserEntity를 업데이트합니다.
     */
    void updateEntityFromDomain(User user, @MappingTarget UserEntity entity);

    /**
     * 위도와 경도로 Point 객체를 생성합니다.
     */
    default Point createPoint(double latitude, double longitude) {
        return GeometryUtils.getPoint(longitude, latitude);
    }
}