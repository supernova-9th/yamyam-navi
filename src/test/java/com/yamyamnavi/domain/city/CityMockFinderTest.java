package com.yamyamnavi.domain.city;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CityMockFinderTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityFinder cityFinder;

    @Test
    void 도시_조회_후_그룹화하여_반환한다() {
        //given
        List<City> cities = Arrays.asList(
                new City("광주시", "남구"),
                new City("광주시", "동구"),
                new City("대구시", "중구"),
                new City("대구시", "수성구")
        );

        given(cityRepository.findAll()).willReturn(cities);

        // when
        Map<String, List<String>> result = cityFinder.findAll();

        // then
        Map<String, List<String>> expected = new HashMap<>();
        expected.put("광주시", Arrays.asList("남구", "동구"));
        expected.put("대구시", Arrays.asList("중구", "수성구"));

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expected);
        assertThat(result.get("광주시")).containsExactly("남구", "동구");
        assertThat(result.get("대구시")).containsExactly("중구", "수성구");

    }

}