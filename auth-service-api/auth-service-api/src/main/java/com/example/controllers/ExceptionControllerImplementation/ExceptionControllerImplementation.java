package com.example.controllers.ExceptionControllerImplementation;

import com.example.controllers.ExceptionControllerInterface.IExceptionControllerInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionControllerImplementation implements IExceptionControllerInterface {
    @Override
    public ResponseEntity<Object> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred: " + e.getMessage());
    }
}
