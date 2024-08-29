package com.yamyamnavi.api.response;

import java.util.List;

public record CityResponse(String city, List<SggResponse> sgg) {
}