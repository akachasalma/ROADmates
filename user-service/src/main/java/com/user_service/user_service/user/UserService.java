package com.user_service.user_service.user;

import com.user_service.user_service.exception.userNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public String createUser(UserRequest request) {
        var user = this.repository.save(mapper.toUser(request));
        return user.getId();
    }

    public void updateUser(UserRequest request) {
        var user = this.repository.findById(request.id())
                .orElseThrow(() -> new userNotFoundException("Cannot update user: No user found with the provided ID"));

        mergeUser(user, request);
        this.repository.save(user);
    }

    private void mergeUser(user user, UserRequest request) {
        if (StringUtils.isNotBlank(request.firstname())) {
            user.setFirstname(request.firstname());
        }
        if (StringUtils.isNotBlank(request.lastname())) {
            user.setLastname(request.lastname());
        }
        if (StringUtils.isNotBlank(request.email())) {
            user.setEmail(request.email());
        }
        if (request.phonenumber() != null) {
            user.setPhonenumber(request.phonenumber());
        }
        if (request.birthday() != null) {
            user.setBirthday(request.birthday());
        }
        if (request.identityCard() != null) {
            user.setIdentityCard(request.identityCard());
        }
        if (request.address() != null) {
            user.setAddress(request.address());
        }
    }

    public List<UserResponse> findAllUsers() {
        return this.repository.findAll().stream()
                .map(mapper::fromUser)
                .collect(Collectors.toList());
    }

    public Boolean existsById(String userId) {
        return this.repository.existsById(userId);
    }

    public UserResponse findById(String userId) {
        return this.repository.findById(userId)
                .map(mapper::fromUser)
                .orElseThrow(() -> new userNotFoundException("No user found with this ID"));
    }

    public void deleteUser(String userId) {
        this.repository.deleteById(userId);
    }

    public String signUp(UserRequest request) {
        if (StringUtils.isBlank(request.email()) || StringUtils.isBlank(request.password())) {
            throw new IllegalArgumentException("Email and Password are required for signing up.");
        }
        /*if (this.repository.existsById(request.id())) {
            throw new IllegalArgumentException("User already exists with this ID.");
        }*/
        var user = this.mapper.toUser(request);
        //user.setId(null); // Let MongoDB generate the ID.
        this.repository.save(user);
        return user.getId();
    }

    public String login(String email, String password) {
        var user = repository.findAll().stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new userNotFoundException("Invalid email or password!"));

        user.setLoggedIn(true);
        repository.save(user);
        return "Login successful!";
    }

    public String logout(String userId) {
        var user = repository.findById(userId)
                .orElseThrow(() -> new userNotFoundException("No user found with this ID!"));

        user.setLoggedIn(false);
        repository.save(user);
        return "Logout successful!";
    }
}
