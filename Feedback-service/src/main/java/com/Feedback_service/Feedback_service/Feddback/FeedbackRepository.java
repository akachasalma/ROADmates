package com.Feedback_service.Feedback_service.Feddback;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findByReceiverUserId(String receiverUserId); // For feedback received by a user
    List<Feedback> findByGiverUserId(String giverUserId); // Fo

}
