package com.yamyamnavi.support.error;

import org.springframework.http.HttpStatus;

public class RestaurantUpdateExcpetion extends YamYamException {

    public RestaurantUpdateExcpetion() {
        super(HttpStatus.NOT_FOUND, ErrorCode.RESTAURANT_NOT_FOUND);
    }

    public RestaurantUpdateExcpetion(HttpStatus httpStatus, ErrorCode errorCode) {
        super(httpStatus, errorCode);
    }

    public RestaurantUpdateExcpetion(HttpStatus httpStatus, ErrorCode errorCode, String message) {
        super(httpStatus, errorCode, message);
    }
}
