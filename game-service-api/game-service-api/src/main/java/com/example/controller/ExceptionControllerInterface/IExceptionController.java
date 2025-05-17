package com.example.controller.ExceptionControllerInterface;

import com.example.common.exceptions.NotFoundException;
import com.example.common.exceptions.UnauthorizedException;
import org.springframework.http.ResponseEntity;

public interface IExceptionController {
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e);
    public ResponseEntity<Object> handleGenericException(Exception e);
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException e);
}
