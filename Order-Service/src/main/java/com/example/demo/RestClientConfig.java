package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${user.service.url}")
    private String userServiceUrl;

    @Bean
    public RestClient restClient() {

        return RestClient.builder()
                .baseUrl(userServiceUrl)
                .build();
    }
}