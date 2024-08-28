package com.yamyamnavi.support.error;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class YamYamException extends RuntimeException {

    private HttpStatus httpStatus;
    private String message;

    public YamYamException(HttpStatus httpStatus, ErrorCode errorCode) {
        super(errorCode.getDefaultMessage());
        this.httpStatus = httpStatus;
        this.message = errorCode.getDefaultMessage();
    }

    public YamYamException(HttpStatus httpStatus, ErrorCode errorCode, String message) {
        super(errorCode.getDefaultMessage());
        this.httpStatus = httpStatus;
        this.message = errorCode.getDefaultMessage();
    }

}