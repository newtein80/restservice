package com.nile.apiservice.sample.exception;

import com.nile.apiservice.common.error.ErrorResponse;
import com.nile.apiservice.sample.exception.exceptions.SampleNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SampleExceptionHandler {
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse unknownError(Throwable throwable) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "0000", throwable.getMessage());
    }

    @ExceptionHandler(SampleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse sampleNotFound(SampleNotFoundException sampleNotFoundException) {
        return new ErrorResponse(HttpStatus.NOT_FOUND, "0001", sampleNotFoundException.getMessage());
    }
}
