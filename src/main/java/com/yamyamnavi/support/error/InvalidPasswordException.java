package com.yamyamnavi.support.error;

import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends YamYamException {

    public InvalidPasswordException() {
        super(HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_PASSWORD);
    }

    public InvalidPasswordException(String message) {
        super(HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_PASSWORD, message);
    }
}