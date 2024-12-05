package com.Feedback_service.Feedback_service.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "user-service",
        url = "${application.config.user-url}"
)
public interface UserClient {
     @GetMapping("/details/{user-id}")
    Optional<UserResponse> findById(@PathVariable("user-id") String userId);
}
