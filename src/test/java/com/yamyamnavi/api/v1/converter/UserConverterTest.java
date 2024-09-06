package com.yamyamnavi.api.v1.converter;

import com.yamyamnavi.api.v1.request.UserCreateRequest;
import com.yamyamnavi.api.v1.response.UserResponse;
import com.yamyamnavi.domain.user.User;
import com.yamyamnavi.storage.user.UserEntity;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserConverterTest {

    @Autowired
    private UserConverter userConverter;

    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Test
    public void UserCreateRequest를_User로_변환한다() {
        UserCreateRequest request = new UserCreateRequest("test@example.com", "password123", "서울특별시 동작구 여의대방로54길 18");

        User user = userConverter.convertToUser(request);

        assertNotNull(user);
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertNull(user.getLatitude());
        assertNull(user.getLongitude());
    }

    @Test
    public void User를_UserResponse로_변환한다() {
        User user = User.builder()
                .id(1L)
                .email("test@example.com")
                .latitude(37.5116)
                .longitude(126.9272)
                .build();

        UserResponse response = userConverter.convertToUserResponse(user);

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("test@example.com", response.email());
        assertEquals(37.5116, response.latitude());
        assertEquals(126.9272, response.longitude());
    }

    @Test
    public void User를_UserEntity로_변환한다() {
        User user = User.builder()
                .id(1L)
                .email("test@example.com")
                .password("password123")
                .latitude(37.5116)
                .longitude(126.9272)
                .build();

        UserEntity entity = userConverter.convertToEntity(user);

        assertNotNull(entity);
        assertEquals("test@example.com", entity.getEmail());
        assertEquals("password123", entity.getPassword());
        assertNotNull(entity.getLocation());
        assertEquals(37.5116, entity.getLocation().getY(), 0.0001);
        assertEquals(126.9272, entity.getLocation().getX(), 0.0001);
    }

    @Test
    public void UserEntity를_User로_변환한다() {
        UserEntity entity = new UserEntity("test@example.com", "password123", createPoint(126.9272, 37.5116));

        User user = userConverter.convertToDomain(entity);

        assertNotNull(user);
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals(37.5116, user.getLatitude());
        assertEquals(126.9272, user.getLongitude());
    }

    private Point createPoint(double longitude, double latitude) {
        return geometryFactory.createPoint(new Coordinate(longitude, latitude));
    }
}