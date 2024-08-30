package com.yamyamnavi.api.v1.response;

import java.util.List;

public record CityResponse(String city, List<SggResponse> sgg) {
}