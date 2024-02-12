package com.api.boardcamp.exceptions;

public class NegativeValueException extends RuntimeException {

    public NegativeValueException(String message) {
        super(message);
    }
}