package com.user_service.user_service.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public user toUser(UserRequest request) {
        if (request == null) {
            return null;
        }

        return user.builder()
                .id(request.id())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .password(request.password())
                .phonenumber(request.phonenumber())
                .birthday(request.birthday())
                .identityCard(request.identityCard())
                .address(request.address())
                .build();
    }

    public UserResponse fromUser(user user) {
        if (user == null) {
            return null;
        }

        return new UserResponse(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPhonenumber(),
                user.getBirthday(),
                user.getIdentityCard(),
                user.getAddress()
        );
    }
}
