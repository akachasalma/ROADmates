package com.Feedback_service.Feedback_service.Feddback;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record FeedbackRequest(
        Integer id,
       // @NotBlank(message = "Ride reference must not be blank")
        //String rideReference,
        @NotNull(message = "Rating is required")
        @Min(value = 1, message = "Rating must be at least 1")
        @Max(value = 5, message = "Rating must be at most 5")
        Integer rating,
        @NotBlank(message = "User ID must not be blank")
        String giverUserId,
        @NotBlank(message = "User ID must not be blank")
        String receiverUserId,
        String comment
) {
}
