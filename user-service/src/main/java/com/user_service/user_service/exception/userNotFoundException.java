package com.user_service.user_service.exception;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class userNotFoundException extends RuntimeException {

    private final String msg;
}