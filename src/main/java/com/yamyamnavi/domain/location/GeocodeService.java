package com.yamyamnavi.domain.location;

import org.springframework.stereotype.Service;

@Service
public class GeocodeService {

    public GeoPoint getGeoPointFromAddress(String address) {

        // 주소 > 위도/경도 변환 로직 구현

        return new GeoPoint(37.5116, 126.9272);
    }
}