package com.example.users_service_api.controllers.ExceptionControllerInterface;
import com.example.users_service_api.commons.exception.NotFoundException;
import org.springframework.http.ResponseEntity;

public interface IExceptionController {
    public ResponseEntity<Object> handleUserNotFoundException(NotFoundException e);
    public ResponseEntity<Object> handleGenericException(Exception e);
}
