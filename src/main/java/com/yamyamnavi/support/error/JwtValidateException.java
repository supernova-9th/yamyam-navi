package com.yamyamnavi.support.error;

import org.springframework.http.HttpStatus;

public class JwtValidateException extends YamYamException {

    public JwtValidateException(String message) {
        super(HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_TOKEN, message);
    }
}