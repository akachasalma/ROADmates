package com.offer_service.offer_service.offer;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record OfferRequest(
    Integer id,
    @NotBlank(message = "User ID must not be blank")
    String userId,
    @NotNull(message = "Departure location is required")
    String dep_location,
    @NotNull(message = "Arrival location is required")
    String arr_location,
    @NotNull(message = "Time is required")
    String time,
    @Positive(message = "Price should be positive")
    BigDecimal price,
    @Positive(message = "Available should be positive")
    @Column(name = "available_seats", nullable = false)
    @NotNull(message = "availableSeats" +
            " is required")
    Integer availableSeats,
    @NotNull(message = "Vehicle is required")
    String matriculationNumber,
    @NotNull(message = "Vehicle is required")
    String brand,
    @NotNull(message = "Vehicle is required")
    String color
    )
{
}
