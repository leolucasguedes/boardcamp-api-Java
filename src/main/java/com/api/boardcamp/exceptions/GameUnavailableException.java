package com.api.boardcamp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class GameUnavailableException extends RuntimeException {
    public GameUnavailableException(String message) {
        super(message);
    }
}
