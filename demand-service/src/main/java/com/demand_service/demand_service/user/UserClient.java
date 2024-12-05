package com.demand_service.demand_service.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "user-service",
        url = "${application.config.user-url:http://localhost:8090/api/v1/user}"
)
public interface UserClient {

    @GetMapping("{/{user-id}}")
    Optional<UserResponse> findUserById(@PathVariable("user-id") String userId);
}
