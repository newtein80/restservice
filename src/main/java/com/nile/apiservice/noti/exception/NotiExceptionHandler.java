package com.nile.apiservice.noti.exception;

import com.nile.apiservice.common.error.ErrorResponse;
import com.nile.apiservice.noti.exception.exceptions.NotiNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotiExceptionHandler {
    @ExceptionHandler(NotiNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse sampleNotFound(NotiNotFoundException notiNotFoundException) {
        return new ErrorResponse(HttpStatus.NOT_FOUND, "0001", notiNotFoundException.getMessage());
    }
}
