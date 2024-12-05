package com.Feedback_service.Feedback_service.User;

import java.time.LocalDate;

public record UserResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Long phonenumber
) {
}
