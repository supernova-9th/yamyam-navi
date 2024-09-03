package com.yamyamnavi.support.error;

import org.springframework.http.HttpStatus;

public class FileUploadException extends YamYamException {

    public FileUploadException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.FILE_UPLOAD_FAILED);
    }

    public FileUploadException(HttpStatus httpStatus, ErrorCode errorCode) {
        super(httpStatus, errorCode);
    }

    public FileUploadException(HttpStatus httpStatus, ErrorCode errorCode, String message) {
        super(httpStatus, errorCode, message);
    }
}
