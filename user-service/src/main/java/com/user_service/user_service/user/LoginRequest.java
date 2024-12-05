package com.user_service.user_service.user;

public record LoginRequest(
        String email,
        String password
) {
}
