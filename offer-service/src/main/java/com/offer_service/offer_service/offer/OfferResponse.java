package com.offer_service.offer_service.offer;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record OfferResponse(
        Integer id,
        String userId,
        String dep_location,
        String arr_location,
        String time,
        BigDecimal price,
        Integer availableSeats,
        String matriculationNumber,
        String brand,
        String color

) {

}
