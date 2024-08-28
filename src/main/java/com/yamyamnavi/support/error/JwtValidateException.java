package com.yamyamnavi.support.error;

public class JwtValidateException extends RuntimeException {
    public JwtValidateException(String message) {
        super(message);
    }
}
