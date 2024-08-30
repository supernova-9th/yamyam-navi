package com.yamyamnavi.support.error;

import org.springframework.http.HttpStatus;

public class UserRegistrationException extends YamYamException {

    public UserRegistrationException(String message) {
        super(HttpStatus.BAD_REQUEST, ErrorCode.USER_REGISTRATION_ERROR, message);
    }

    public UserRegistrationException(ErrorCode errorCode, String message) {
        super(HttpStatus.BAD_REQUEST, errorCode, message);
    }
}