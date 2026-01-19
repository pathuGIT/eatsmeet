package com.wak.eatsmeet.exception;

import com.wak.eatsmeet.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    //handler for validation related exception
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> validationExceptionHandler(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        exception.getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage())
        );

        ApiErrorResponse response = new ApiErrorResponse(
                "validation error"
                , errors
                , HttpStatus.BAD_REQUEST.value()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //handler for ResourceAlreadyExistException
    @ExceptionHandler(value = ResourceAlreadyExistException.class)
    public ResponseEntity<ApiErrorResponse> duplicateResourceExceptionHandler(ResourceAlreadyExistException exception) {
        ApiErrorResponse response = new ApiErrorResponse(
                exception.getMessage()
                , new HashMap<>()
                , HttpStatus.BAD_REQUEST.value()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //handler for ResourceNotFoundException
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> resourceNotFoundHandler(ResourceNotFoundException exception) {
        ApiErrorResponse response = new ApiErrorResponse(
                exception.getMessage()
                , new HashMap<>()
                , HttpStatus.NOT_FOUND.value()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
