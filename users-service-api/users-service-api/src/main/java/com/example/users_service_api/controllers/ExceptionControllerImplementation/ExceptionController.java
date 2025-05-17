package com.example.users_service_api.controllers.ExceptionControllerImplementation;
import com.example.users_service_api.commons.exception.NotFoundException;
import com.example.users_service_api.controllers.ExceptionControllerInterface.IExceptionController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController implements IExceptionController {
    @Override
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @Override
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error ocurred:" + e.getMessage());
    }
}
