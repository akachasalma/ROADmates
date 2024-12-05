package com.user_service.user_service.user;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
@Setter
@Getter
//permet d'appliquer des contraintes de validation définies sur les champs d'une classe à l'aide de la bibliothèque Bean Validation
@Validated
public class Address {
    private String street;
    private String houseNumber;
    private String zipcode;
}
