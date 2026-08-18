package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserResponse;
import com.example.demo.entity.User;

@Service
public class UserService {

    private final List<User> users = List.of(
            new User(1001L, "Gayu", "gayu@example.com"),
            new User(1002L, "Priya", "priya@example.com"),
            new User(1003L, "Ravi", "ravi@example.com")
    );

    // Get one user
    public UserResponse getUser(Long id) {

        for (User user : users) {

            if (user.getId().equals(id)) {

                return new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                );
            }
        }

        return null;
    }

    // Get all users
    public List<UserResponse> getAllUsers() {

        List<UserResponse> responses = new ArrayList<>();

        for (User user : users) {

            responses.add(
                    new UserResponse(
                            user.getId(),
                            user.getName(),
                            user.getEmail()
                    )
            );
        }

        return responses;
    }
}