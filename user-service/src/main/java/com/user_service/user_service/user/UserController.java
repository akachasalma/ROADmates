package com.user_service.user_service.user;

import com.user_service.user_service.exception.userNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody @Valid UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody @Valid UserRequest request) {
        userService.updateUser(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/exists/{userId}")
    public ResponseEntity<Boolean> existsById(@PathVariable String userId) {
        return ResponseEntity.ok(userService.existsById(userId));
    }

    @GetMapping("/details/{userId}")
    public ResponseEntity<UserResponse> findById(@PathVariable String userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody @Valid UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUp(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            String response = userService.login(loginRequest.email(), loginRequest.password());
            return ResponseEntity.ok(response);
        } catch (userNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test endpoint is working!");
    }
}
