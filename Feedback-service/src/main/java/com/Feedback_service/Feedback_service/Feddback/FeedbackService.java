package com.Feedback_service.Feedback_service.Feddback;
import org.springframework.stereotype.Service;
import com.Feedback_service.Feedback_service.Exceptions.BusinessException;
import com.Feedback_service.Feedback_service.User.UserClient;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final UserClient userclient;
    private final FeedbackRepository repository;
    private final FeedbackMapper mapper;
    public Integer createFeedback(FeedbackRequest request) {
//check if the customer exists -> openFeign
// Check if both users (giver and receiver) exist
        var giverUser = this.userclient.findById(request.giverUserId())
                .orElseThrow(() -> new BusinessException("Cannot create feedback:: Giver user not found with ID: " + request.giverUserId()));

        var receiverUser = this.userclient.findById(request.receiverUserId())
                .orElseThrow(() -> new BusinessException("Cannot create feedback:: Receiver user not found with ID: " + request.receiverUserId()));

        var feedback = this.repository.save(mapper.toFeedback(request));
     return feedback.getId();}

    public List<FeedbackResponse> findAllfeedbacks() {
        return this.repository.findAll()
                .stream()
                .map(this.mapper::toResponse)
                .collect(Collectors.toList());
    }

    public void updateFeedback(Integer id, FeedbackRequest request) {
        var feedback = this.repository.findById(id)
                .orElseThrow(() -> new BusinessException("Feedback not found with ID: " + id));
        feedback.setComment(request.comment());
        feedback.setRating(request.rating());
        feedback.setGiverUserId(request.giverUserId());
        feedback.setReceiverUserId(request.receiverUserId());
        this.repository.save(feedback);
    }

    public void deleteFeedback(Integer id) {
        if (!this.repository.existsById(id)) {
            throw new BusinessException("Feedback not found with ID: " + id);
        }
        this.repository.deleteById(id);
    }

    public List<FeedbackResponse> findFeedbacksByReceiverUserId(String receiverUserId) {
        // Check if the receiver user exists
        var receiverUser = this.userclient.findById(receiverUserId)
                .orElseThrow(() -> new BusinessException("No user found with ID: " + receiverUserId));

        // Get feedbacks related to this receiver
        return this.repository.findByReceiverUserId(receiverUserId)
                .stream()
                .map(this.mapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<FeedbackResponse> findFeedbacksByGiverUserId(String giverUserId) {
        // Check if the giver user exists
        var giverUser = this.userclient.findById(giverUserId)
                .orElseThrow(() -> new BusinessException("No user found with ID: " + giverUserId));

        // Get feedbacks related to this giver
        return this.repository.findByGiverUserId(giverUserId)
                .stream()
                .map(this.mapper::toResponse)
                .collect(Collectors.toList());
    }
}


