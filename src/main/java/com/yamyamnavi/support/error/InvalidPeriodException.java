package com.yamyamnavi.support.error;

import org.springframework.http.HttpStatus;

public class InvalidPeriodException extends YamYamException {
    public InvalidPeriodException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_TOKEN);
    }

    public InvalidPeriodException(ErrorCode errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }

    public InvalidPeriodException(HttpStatus httpStatus, ErrorCode errorCode) {
        super(httpStatus, errorCode);
    }
}