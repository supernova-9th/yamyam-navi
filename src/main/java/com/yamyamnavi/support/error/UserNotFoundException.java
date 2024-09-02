package com.yamyamnavi.support.error;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends YamYamException {

    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, ErrorCode.USER_NOT_FOUND);
    }

    public UserNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, ErrorCode.USER_NOT_FOUND, message);
    }
}