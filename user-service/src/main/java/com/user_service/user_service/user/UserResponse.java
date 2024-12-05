package com.user_service.user_service.user;

import java.time.LocalDate;

public record UserResponse(

        String id,
        String firstname,
        String lastname,
        String email,
        Long phonenumber,
        LocalDate birthday,
        Long identityCard,
        Address address
) {
}
