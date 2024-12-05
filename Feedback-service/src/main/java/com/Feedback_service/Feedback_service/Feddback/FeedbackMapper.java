package com.Feedback_service.Feedback_service.Feddback;

import org.springframework.stereotype.Service;

@Service
public class FeedbackMapper {
    public Feedback toFeedback(FeedbackRequest request) {
        return Feedback.builder()
               // .rideReference(request.rideReference())
                .rating(request.rating())
                .giverUserId(request.giverUserId())
                .receiverUserId(request.receiverUserId())
                .comment(request.comment())
                .build();
    }

    public FeedbackResponse toResponse(Feedback feedback) {
        return FeedbackResponse.builder()
                .id(feedback.getId())
                //.rideReference(feedback.getRideReference())
                .rating(feedback.getRating())
                .giverUserId(feedback.getGiverUserId())
                .receiverUserId(feedback.getReceiverUserId())
                .comment(feedback.getComment())
                .build();
    }
}
