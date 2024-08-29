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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {

    @Mock
    private CityFinder cityFinder;

    @InjectMocks
    private CityService cityService;

    @Test
    void 도시_목록_조회_테스트() {
        //given
        Map<String, List<String>> expect = new HashMap<>();
        expect.put("전라", Arrays.asList("강진군", "여수시"));
        expect.put("충청", Arrays.asList("공주시", "보령시"));

        given(cityFinder.findAll()).willReturn(expect);

        //when
        Map<String, List<String>> result = cityService.getCites();

        //then
        assertThat(result).isNotNull();
        assertEquals(expect.size(), result.size());
        assertThat(result.get("전라")).containsExactly("강진군", "여수시");
        assertThat(result.get("충청")).containsExactly("공주시", "보령시");
    }

}