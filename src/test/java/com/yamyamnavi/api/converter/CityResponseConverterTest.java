package com.yamyamnavi.api.converter;

import com.yamyamnavi.api.response.SggResponse;
import com.yamyamnavi.domain.city.CitySgg;
import com.yamyamnavi.storage.city.CityEntity;
import com.yamyamnavi.support.utils.GeometryUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        CityConverterImpl.class
})
class CityResponseConverterTest {

    @Autowired
    private CityConverter cityConverter;

    @Test
    void CityEntity를_City로_변환한다() {
        // given
        GeometryFactory geometryFactory = new GeometryFactory();
        double x = 126.9784;
        double y = 37.5665;
        Point location = geometryFactory.createPoint(new Coordinate(x, y));

        CityEntity cityEntity = CityEntity.builder()
                .dosi("서울")
                .sgg("종로구")
                .location(location)
                .build();

        // when
        CitySgg city = cityConverter.convert(cityEntity);

        // then
        assertThat(city).isNotNull();
        assertEquals(city.getCity(),("서울"));
        assertEquals(city.getSgg(),("종로구"));
        assertEquals(city.getLongitude(), location.getX());
        assertEquals(city.getLatitude(), location.getY());
    }
    @Test
    void CityEntity_리스트를_City_리스트로_변환한다() {
        // given
        double longitude1 = 126.9784;
        double latitude1 = 37.5665;
        double longitude2 = 127.0286;
        double latitude2 = 37.4979;
        Point location1 = GeometryUtils.getPoint(longitude1, latitude1);
        Point location2 = GeometryUtils.getPoint(longitude2, latitude2);

        CityEntity cityEntity1 = CityEntity.builder()
                .dosi("서울")
                .sgg("종로구")
                .location(location1)
                .build();

        CityEntity cityEntity2 = CityEntity.builder()
                .dosi("서울")
                .sgg("강남구")
                .location(location2)
                .build();

        List<CityEntity> cityEntities = Arrays.asList(cityEntity1, cityEntity2);

        // when
        List<CitySgg> cities = cityConverter.convert(cityEntities);

        // then
        assertEquals(2, cities.size());

        assertEquals("서울", cities.get(0).getCity());
        assertEquals("종로구", cities.get(0).getSgg());
        assertEquals(latitude1, cities.get(0).getLatitude());
        assertEquals(longitude1, cities.get(0).getLongitude());

        assertEquals("서울", cities.get(1).getCity());
        assertEquals("강남구", cities.get(1).getSgg());
        assertEquals(latitude2, cities.get(1).getLatitude());
        assertEquals(longitude2, cities.get(1).getLongitude());
    }

    @Test
    void CitySgg를_SggResponse로_변환한다() {
        //given
        CitySgg source = new CitySgg("충청", "공주시", 127.1211194, 34.63891111);
        SggResponse expect = new SggResponse("공주시", 127.1211194, 34.63891111);

        //when
        SggResponse result = cityConverter.convertToSggResponse(source);

        //then
        assertEquals(expect.name(), result.name());
        assertEquals(expect.longitude(), result.longitude());
        assertEquals(expect.latitude(), result.latitude());
    }


}