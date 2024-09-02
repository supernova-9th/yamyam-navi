//package com.yamyamnavi.api;
//
//import com.yamyamnavi.api.v1.response.CityResponse;
//import com.yamyamnavi.api.v1.controller.CityController;
//import com.yamyamnavi.domain.city.CityService;
//import com.yamyamnavi.api.v1.response.SggResponse;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(CityController.class)
//class CityResponseControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    CityService cityService;
//
//    @Test
//    void 도시_목록_조회_테스트() throws Exception {
//        // given
//        SggResponse 강진군 = new SggResponse("강진군", 126.7691972, 34.63891111);
//        SggResponse 여수시 = new SggResponse("여수시", 127.6643861, 34.75731111);
//        SggResponse 공주시 = new SggResponse("공주시", 127.1211194, 34.63891111);
//        SggResponse 보령시 = new SggResponse("보령시", 126.6148861, 36.330575);
//
//        CityResponse 전라 = new CityResponse("전라", Arrays.asList(강진군, 여수시));
//        CityResponse 충청 = new CityResponse("충청", Arrays.asList(공주시, 보령시));
//        List<CityResponse> expect = Arrays.asList(전라, 충청);
//
//        given(cityService.getCites()).willReturn(expect);
//
//        //when
//
//        // when & then
//        mockMvc.perform(get("/v1/apis/cities")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.result[0].city").value("전라"))
//                .andExpect(jsonPath("$.result[0].sgg[0].name").value("강진군"))
//                .andExpect(jsonPath("$.result[0].sgg[1].name").value("여수시"))
//                .andExpect(jsonPath("$.result[1].city").value("충청"))
//                .andExpect(jsonPath("$.result[1].sgg[0].name").value("공주시"))
//                .andExpect(jsonPath("$.result[1].sgg[1].name").value("보령시"));
//    }
//}