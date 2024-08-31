package com.yamyamnavi.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class SeoulOpenApiConfig {

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .build();
    }

    @Bean
    public SeoulOpenApiClient seoulOpenApiClient(RestClient restClient) {
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient))
                .build();
        return factory.createClient(SeoulOpenApiClient.class);
    }
}