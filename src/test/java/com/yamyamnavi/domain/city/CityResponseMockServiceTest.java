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
class CityMockServiceTest {

    @Mock
    private CityFinder cityFinder;

    @InjectMocks
    private CityService cityService;

    @Test
    void 도시_목록_조회_테스트() {
        //given
        Sgg 강진군 = new Sgg("강진군", 126.7691972, 34.63891111);
        Sgg 여수시 = new Sgg("여수시", 127.6643861, 34.75731111);
        Sgg 공주시 = new Sgg("공주시", 127.1211194, 34.63891111);
        Sgg 보령시 = new Sgg("보령시", 126.6148861, 36.330575);

        City 전라 = new City("전라", Arrays.asList(강진군, 여수시));
        City 충청 = new City("충청", Arrays.asList(공주시, 보령시));
        List<City> expect = Arrays.asList(전라, 충청);

        given(cityFinder.findAll()).willReturn(expect);

        //when
        List<City> result = cityService.getCites();

        //then
        assertThat(result).isNotNull();
        assertEquals(2, result.size());
        assertEquals(전라, result.get(0));
        assertEquals(126.6148861, result.get(1).getSgg().get(1).getLongitude());
    }

}