package com.scrap.avatar.exception;

import org.springframework.http.HttpStatus;

/**
 * AvatarException.
 *
 * @author Luis Alonso Ballena Garcia
 */

public class AvatarException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String customMessage;

    public AvatarException(HttpStatus httpStatus, String customMessage) {
        this.httpStatus = httpStatus;
        this.customMessage = customMessage;
    }

    public AvatarException(String message, HttpStatus httpStatus, String customMessage) {
        super(message);
        this.httpStatus = httpStatus;
        this.customMessage = customMessage;
    }

    public AvatarException(String message, Throwable cause, HttpStatus httpStatus, String customMessage) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.customMessage = customMessage;
    }

    public AvatarException(Throwable cause, HttpStatus httpStatus, String customMessage) {
        super(cause);
        this.httpStatus = httpStatus;
        this.customMessage = customMessage;
    }

    public AvatarException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatus httpStatus, String customMessage) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.httpStatus = httpStatus;
        this.customMessage = customMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getCustomMessage() {
        return customMessage;
    }
}
