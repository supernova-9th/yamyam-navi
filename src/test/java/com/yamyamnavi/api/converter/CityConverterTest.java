package com.yamyamnavi.api.converter;

import com.yamyamnavi.domain.city.City;
import com.yamyamnavi.storage.city.CityEntity;
import org.junit.jupiter.api.DisplayName;
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
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        CityConverterImpl.class
})
class CityConverterTest {

    @Autowired
    private CityConverter cityConverter;

    @Test
    void CityEntity를_City로_변환한다() {
        // given
        GeometryFactory geometryFactory = new GeometryFactory();
        Point location = geometryFactory.createPoint(new Coordinate(126.9784, 37.5665));

        CityEntity cityEntity = CityEntity.builder()
                .dosi("서울")
                .sgg("종로구")
                .location(location)
                .build();

        // when
        City city = cityConverter.convert(cityEntity);

        // then
        assertThat(city).isNotNull();
        assertThat(city.getDosi()).isEqualTo("서울");
        assertThat(city.getSgg()).isEqualTo("종로구");
    }
    @Test
    void CityEntity_리스트를_City_리스트로_변환한다() {
        // given
        GeometryFactory geometryFactory = new GeometryFactory();
        Point location1 = geometryFactory.createPoint(new Coordinate(126.9784, 37.5665));
        Point location2 = geometryFactory.createPoint(new Coordinate(127.0286, 37.4979));

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
        List<City> cities = cityConverter.convert(cityEntities);

        // then
        assertThat(cities).isNotNull();
        assertThat(cities).hasSize(2);
        assertThat(cities.get(0).getDosi()).isEqualTo("서울");
        assertThat(cities.get(0).getSgg()).isEqualTo("종로구");
        assertThat(cities.get(1).getDosi()).isEqualTo("서울");
        assertThat(cities.get(1).getSgg()).isEqualTo("강남구");
    }

}