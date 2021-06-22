package com.nile.apiservice.common.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import com.nile.apiservice.common.error.ErrorResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        // return super.handleMethodArgumentNotValid(ex, headers, status, request);
        Map<String, Object> body = new HashMap<>();
        List<String> errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(x -> x.getDefaultMessage())
        .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintValidationException(ConstraintViolationException ex){
        // todo: 아래의 return 이 ErrorResponse 인 메소드와 무엇이 다른지...
        return new ResponseEntity<>("Error:" + ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse unknownError(Throwable throwable) {
        // todo: 위의 return 이 ResponseEntity<String> 인 메소드와 무엇이 다른지...
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "0000", throwable.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
            
                Map<String, Object> body = new HashMap<>();
                body.put("error", ex.getMessage());

                return new ResponseEntity<>(body, headers, status);
    }
}
