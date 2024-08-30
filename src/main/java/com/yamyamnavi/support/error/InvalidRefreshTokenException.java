package com.yamyamnavi.support.error;

import org.springframework.http.HttpStatus;

public class InvalidRefreshTokenException extends YamYamException {

  public InvalidRefreshTokenException() {
    super(HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_TOKEN);
  }

  public InvalidRefreshTokenException(String message) {
    super(HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_TOKEN, message);
  }
}