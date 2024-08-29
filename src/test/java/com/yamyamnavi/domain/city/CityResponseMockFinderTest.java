package com.yamyamnavi.domain.city;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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
        CitySgg 강진군 = new CitySgg("전라", "강진군", 126.7691972, 34.63891111);
        CitySgg 여수시 = new CitySgg("전라", "여수시", 127.6643861, 34.75731111);
        CitySgg 공주시 = new CitySgg("충청", "공주시", 127.1211194, 34.63891111);
        CitySgg 보령시 = new CitySgg("충청", "보령시", 126.6148861, 36.330575);
        List<CitySgg> expect = Arrays.asList(강진군, 여수시, 공주시, 보령시);

        given(cityRepository.findAll()).willReturn(expect);

        // when
        List<City> result = cityFinder.findAll();

        // then
        assertThat(result).isNotNull();
        assertEquals(2, result.size());
        assertEquals("전라", result.get(0).getCity());
        assertEquals("공주시", result.get(1).getSgg().get(0).getName());
    }

}