package com.Feedback_service.Feedback_service.Feddback;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record FeedbackResponse(
        Integer id,
        Integer rating,
        String giverUserId, // ID of the user making the feedback
        String receiverUserId, // ID of the user receiving the feedback
        String comment
) {
}
