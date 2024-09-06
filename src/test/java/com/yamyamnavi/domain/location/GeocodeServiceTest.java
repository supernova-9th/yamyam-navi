package com.yamyamnavi.domain.location;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import com.google.maps.model.LatLng;
import com.yamyamnavi.support.error.YamYamException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GeocodeServiceTest {

    @Autowired
    private GeocodeService geocodeService;

    @Test
    public void 올바른_주소로_좌표_변환한다() {
        String address = "서울특별시 동작구 여의대방로54길 18";

        assertDoesNotThrow(() -> {
            GeoPoint geoPoint = geocodeService.getGeoPointFromAddress(address);
            assertNotNull(geoPoint);
            assertTrue(geoPoint.latitude() > 0);
            assertTrue(geoPoint.longitude() > 0);
        });
    }

    @Test
    public void 잘못된_주소로_좌표_변환시_예외_발생_테스트() {
        String invalidAddress = "존재하지 않는 주소 1234567890";

        assertThrows(YamYamException.class, () -> geocodeService.getGeoPointFromAddress(invalidAddress));
    }
}