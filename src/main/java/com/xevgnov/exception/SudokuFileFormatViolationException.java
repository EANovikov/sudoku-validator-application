package com.xevgnov.exception;

public class SudokuFileFormatViolationException extends SudokuValidationException {
    public SudokuFileFormatViolationException(String message) {
        super(message);
    }

    public SudokuFileFormatViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
