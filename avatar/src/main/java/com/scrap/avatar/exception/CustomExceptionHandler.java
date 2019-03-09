package com.scrap.avatar.exception;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ExceptionHandler.
 *
 * @author Luis Alonso Ballena Garcia
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(AvatarException.class)
    @ResponseBody
    public ResponseEntity<ExceptionResponse> handleAvatarException(final AvatarException ex) {
        logger.info("handleAvatarException : {}",ex.getCustomMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setError_message(ex.getCustomMessage());
        return new ResponseEntity(exceptionResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleArgumentNotValid(HttpServletRequest req, MethodArgumentNotValidException ex) {
        logger.info("handleArgumentNotValid : {}",ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        FieldError fieldError = ex.getBindingResult().getFieldErrors().stream().findFirst().orElse(null);
        if (fieldError != null) {
            exceptionResponse.setError_message(fieldError.getDefaultMessage());
        }
        return exceptionResponse;
    }

}
