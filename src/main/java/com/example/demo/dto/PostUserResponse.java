package com.example.demo.dto;

import lombok.Getter;

@Getter

public class PostUserResponse {
    private long userId;
    private String jwt = null;

    public PostUserResponse(long userId) {
        this.userId = userId;
    }
}
