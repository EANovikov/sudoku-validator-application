package com.xevgnov.exception;

public class SudokuValidationException extends RuntimeException {

    public SudokuValidationException(String message) {
        super(message);
    }

    public SudokuValidationException(String message, Throwable cause) {
        super(message, cause);
    }

}
