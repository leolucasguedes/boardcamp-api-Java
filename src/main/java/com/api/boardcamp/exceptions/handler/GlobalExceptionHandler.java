package com.api.boardcamp.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.api.boardcamp.exceptions.ExceptionResponse;
import com.api.boardcamp.exceptions.NotFoundException;
import com.api.boardcamp.exceptions.CustomerAlreadyExistsException;
import com.api.boardcamp.exceptions.GameAlreadyExistsException;
import com.api.boardcamp.exceptions.GameUnavailableException;
import com.api.boardcamp.exceptions.NegativeValueException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(GameAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleGameAlreadyExistsException(GameAlreadyExistsException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(GameUnavailableException.class)
    public ResponseEntity<ExceptionResponse> handleGameUnavailableException(GameUnavailableException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    @ExceptionHandler(NegativeValueException.class)
    public ResponseEntity<ExceptionResponse> handleNegativeValueException(NegativeValueException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}