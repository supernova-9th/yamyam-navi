package com.yamyamnavi.support.error;

import org.springframework.http.HttpStatus;

public class RestaurantNotFoundException extends YamYamException {

    public RestaurantNotFoundException() {
        super(HttpStatus.NOT_FOUND, ErrorCode.RESTAURANT_NOT_FOUND);
    }

    public RestaurantNotFoundException(HttpStatus httpStatus, ErrorCode errorCode) {
        super(httpStatus, errorCode);
    }

    public RestaurantNotFoundException(HttpStatus httpStatus, ErrorCode errorCode, String message) {
        super(httpStatus, errorCode, message);
    }

}
