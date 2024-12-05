package com.Feedback_service.Feedback_service.Feddback;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService service;

    @PostMapping
    public ResponseEntity<Integer> createOrder(
            @RequestBody @Valid FeedbackRequest request
    ) {
        return ResponseEntity.ok(this.service.createFeedback(request));
    }
    @GetMapping
    public ResponseEntity<List<FeedbackResponse>> findAll() {
        return ResponseEntity.ok(this.service.findAllfeedbacks());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFeedback(
            @PathVariable("id") Integer id,
            @RequestBody @Valid FeedbackRequest request
    ) {
        this.service.updateFeedback(id, request);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable("id") Integer id) {
        this.service.deleteFeedback(id);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/giver/{giverUserId}")
    public ResponseEntity<List<FeedbackResponse>> findFeedbacksByGiverUserId(@PathVariable("giverUserId") String giverUserId) {
        return ResponseEntity.ok(this.service.findFeedbacksByGiverUserId(giverUserId));
    }
    @GetMapping("/receiver/{receiverUserId}")
    public ResponseEntity<List<FeedbackResponse>> findFeedbacksByReceiverUserId(@PathVariable("receiverUserId") String receiverUserId) {
        return ResponseEntity.ok(this.service.findFeedbacksByReceiverUserId(receiverUserId));
    }
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test endpoint is working!");
    }

}
