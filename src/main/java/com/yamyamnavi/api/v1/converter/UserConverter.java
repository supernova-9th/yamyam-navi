package com.yamyamnavi.api.v1.converter;

import com.yamyamnavi.api.v1.request.UserCreateRequest;
import com.yamyamnavi.api.v1.response.UserResponse;
import com.yamyamnavi.domain.user.User;
import com.yamyamnavi.storage.user.UserEntity;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",imports = {Coordinate.class, GeometryFactory.class, PrecisionModel.class, Point.class})
public interface UserConverter {

    GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "latitude", ignore = true)
    @Mapping(target = "longitude", ignore = true)
    User convertToUser(UserCreateRequest request);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "latitude", expression = "java(user.getLatitude())")
    @Mapping(target = "longitude", expression = "java(user.getLongitude())")
    UserResponse convertToUserResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "location", expression = "java(user.getLatitude() != null && user.getLongitude() != null ? geometryFactory.createPoint(new Coordinate(user.getLongitude(), user.getLatitude())) : null)")
    UserEntity convertToEntity(User user);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "latitude", expression = "java(entity.getLocation() != null ? entity.getLocation().getY() : null)")
    @Mapping(target = "longitude", expression = "java(entity.getLocation() != null ? entity.getLocation().getX() : null)")
    User convertToDomain(UserEntity entity);

    default void updateEntityFromDomain(User user, @MappingTarget UserEntity entity) {
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        if (user.getLatitude() != null && user.getLongitude() != null) {
            entity.setLocation(geometryFactory.createPoint(new Coordinate(user.getLongitude(), user.getLatitude())));
        }
    }
}