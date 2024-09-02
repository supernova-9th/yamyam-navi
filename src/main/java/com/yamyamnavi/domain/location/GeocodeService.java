package com.yamyamnavi.domain.location;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.yamyamnavi.support.error.ErrorCode;
import com.yamyamnavi.support.error.YamYamException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class GeocodeService {

    private final GeoApiContext context;

    public GeocodeService(@Value("${google.maps.api-key}") String apiKey) {
        this.context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }

    public GeoPoint getGeoPointFromAddress(String address) {
        try {
            GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
            if (results.length > 0) {
                double lat = results[0].geometry.location.lat;
                double lng = results[0].geometry.location.lng;
                return new GeoPoint(lat, lng);
            }
            throw new YamYamException(HttpStatus.BAD_REQUEST, ErrorCode.BAD_REQUEST, "주소를 찾을 수 없습니다.");
        } catch (Exception e) {
            throw new YamYamException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EXTERNAL_API_ERROR, "주소 변환 중 오류가 발생했습니다.");
        }
    }
}