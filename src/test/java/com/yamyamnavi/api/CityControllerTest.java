package com.yamyamnavi.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yamyamnavi.domain.city.CityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CityController.class)
class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CityService cityService;

    @Test
    void 도시_조회_테스트() throws Exception {
        // given
        Map<String, List<String>> result = new HashMap<>();
        result.put("서울시", Arrays.asList("동작구", "종로구"));
        result.put("강원도", Arrays.asList("강릉시", "고양시"));

        given(cityService.getCites()).willReturn(result);

        // when & then
        mockMvc.perform(get("/v1/apis/cities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.서울시").isArray())
                .andExpect(jsonPath("$.result.서울시[0]").value("동작구"))
                .andExpect(jsonPath("$.result.강원도").isArray())
                .andExpect(jsonPath("$.result.강원도[1]").value("고양시"));
    }

}