package com.yamyamnavi.domain.city;

import com.yamyamnavi.api.converter.CityConverter;
import com.yamyamnavi.api.converter.CityConverterImpl;
import com.yamyamnavi.api.response.CityResponse;
import com.yamyamnavi.api.response.SggResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityResponseMockFinderTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityConverter cityConverter;

    @InjectMocks
    private CityFinder cityFinder;

    @Test
    void 도시_조회_후_그룹화하여_반환한다() {
        // given
        CitySgg 강진군 = new CitySgg("전라", "강진군", 126.7691972, 34.63891111);
        CitySgg 여수시 = new CitySgg("전라", "여수시", 127.6643861, 34.75731111);
        CitySgg 공주시 = new CitySgg("충청", "공주시", 127.1211194, 34.63891111);
        CitySgg 보령시 = new CitySgg("충청", "보령시", 126.6148861, 36.330575);
        List<CitySgg> expect = Arrays.asList(강진군, 여수시, 공주시, 보령시);

        when(cityRepository.findAll()).thenReturn(expect);

        SggResponse sggResponse1 = new SggResponse("강진군", 126.7691972, 34.63891111);
        SggResponse sggResponse2 = new SggResponse("여수시", 127.6643861, 34.75731111);
        SggResponse sggResponse3 = new SggResponse("공주시", 127.1211194, 34.63891111);
        SggResponse sggResponse4 = new SggResponse( "보령시", 126.6148861, 36.330575);

        when(cityConverter.convertToSggResponse(강진군)).thenReturn(sggResponse1);
        when(cityConverter.convertToSggResponse(여수시)).thenReturn(sggResponse2);
        when(cityConverter.convertToSggResponse(공주시)).thenReturn(sggResponse3);
        when(cityConverter.convertToSggResponse(보령시)).thenReturn(sggResponse4);

        // when
        List<CityResponse> cityResponses = cityFinder.findAll();

        // then
        assertEquals(2, cityResponses.size());

        verify(cityRepository, times(1)).findAll();
        verify(cityConverter, times(1)).convertToSggResponse(강진군);
        verify(cityConverter, times(1)).convertToSggResponse(여수시);
        verify(cityConverter, times(1)).convertToSggResponse(공주시);
        verify(cityConverter, times(1)).convertToSggResponse(보령시);
    }

}