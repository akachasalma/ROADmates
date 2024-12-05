package com.user_service.user_service.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record UserRequest(

        String id,
        @NotNull(message = "User firstname is required")
        String firstname,
        @NotNull(message = "User lastname is required")
        String lastname,
        @NotNull(message = "User Email is required")
        @Email(message = "User Email is not a valid email address")
        String email,
        @NotNull(message = "Password is required")
        String password, // Field for password
        Long phonenumber,
        LocalDate birthday,
        Long identityCard,
        Address address
) {
}
