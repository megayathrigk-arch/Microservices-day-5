package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.demo.dto.UserDto;

@Service
public class UserClient {

    private final RestClient restClient;

    public UserClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public UserDto getUserById(Long id) {

        try {
            return restClient.get()
                    .uri("/api/users/{id}", id)
                    .retrieve()
                    .body(UserDto.class);

        } catch (Exception e) {
            throw new RuntimeException(
                    "User Service Unavailable", e
            );
        }
    }
}