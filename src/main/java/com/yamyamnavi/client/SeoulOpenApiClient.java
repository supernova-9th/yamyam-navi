package com.yamyamnavi.client;

import com.yamyamnavi.client.response.SeoulApiResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("http://openapi.seoul.go.kr:8088")
public interface SeoulOpenApiClient {

    @GetExchange("/{apiKey}/json/LOCALDATA_072404/{start}/{end}")
    SeoulApiResponse fetchData(@PathVariable String apiKey,
                               @PathVariable int start,
                               @PathVariable int end);
}