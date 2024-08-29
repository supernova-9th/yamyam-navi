package com.yamyamnavi.domain.city;

import com.yamyamnavi.api.response.CityResponse;
import com.yamyamnavi.api.response.SggResponse;
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
class CityResponseMockServiceTest {

    @Mock
    private CityFinder cityFinder;

    @InjectMocks
    private CityService cityService;

    @Test
    void 도시_목록_조회_테스트() {
        //given
        SggResponse 강진군 = new SggResponse("강진군", 126.7691972, 34.63891111);
        SggResponse 여수시 = new SggResponse("여수시", 127.6643861, 34.75731111);
        SggResponse 공주시 = new SggResponse("공주시", 127.1211194, 34.63891111);
        SggResponse 보령시 = new SggResponse("보령시", 126.6148861, 36.330575);

        CityResponse 전라 = new CityResponse("전라", Arrays.asList(강진군, 여수시));
        CityResponse 충청 = new CityResponse("충청", Arrays.asList(공주시, 보령시));
        List<CityResponse> expect = Arrays.asList(전라, 충청);

        given(cityFinder.findAll()).willReturn(expect);

        //when
        List<CityResponse> result = cityService.getCites();

        //then
        assertThat(result).isNotNull();
        assertEquals(2, result.size());
        assertEquals(전라, result.get(0));
        assertEquals(126.6148861, result.get(1).sgg().get(1).longitude());
    }

}