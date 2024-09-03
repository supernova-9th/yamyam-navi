package com.yamyamnavi.api;

import com.yamyamnavi.support.error.YamYamException;
import com.yamyamnavi.support.response.ResultResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(YamYamException.class)
    public ResponseEntity<ResultResponse<Void>> handleWantedException(YamYamException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(new ResultResponse<>(ex.getHttpStatus(), ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultResponse<Void>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder sb = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(x -> sb.append(x).append("\n"));

        return ResponseEntity.status(ex.getStatusCode()).body(new ResultResponse<>(HttpStatus.BAD_REQUEST, sb.toString().trim()));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ResultResponse<Void>> unhandledException(Exception e, HttpServletRequest request) {
        log.error("error occur {}", request.getRequestURI());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResultResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
    }

}