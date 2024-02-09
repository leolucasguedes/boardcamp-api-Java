package com.api.boardcamp.exceptions;

public class GameAlreadyExistsException extends RuntimeException {

    public GameAlreadyExistsException(String message) {
        super(message);
    }
}
