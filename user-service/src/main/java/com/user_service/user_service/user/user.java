package com.user_service.user_service.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Document // Specifies that this class will be stored in a MongoDB collection
public class user {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String password; // Field for the password
    private boolean isLoggedIn; // Indicates if the user is logged in
    private Long phonenumber;
    private LocalDate birthday;
    private Long identityCard;
    private Address address;
}
