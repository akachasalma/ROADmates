package com.user_service.user_service.user;

import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<user, String> {

}
