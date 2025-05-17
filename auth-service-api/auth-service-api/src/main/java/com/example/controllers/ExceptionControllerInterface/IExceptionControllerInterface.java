package com.example.controllers.ExceptionControllerInterface;

import org.springframework.http.ResponseEntity;

public interface IExceptionControllerInterface {
    public ResponseEntity<Object> handleGenericException(Exception e);
}
