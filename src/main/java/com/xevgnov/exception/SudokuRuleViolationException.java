package com.xevgnov.exception;

public class SudokuRuleViolationException extends SudokuValidationException {

    private static final String FAIL_GAME_MESSAGE = "Failed the game!\n";

    public SudokuRuleViolationException(String message) {
        super(FAIL_GAME_MESSAGE + message);
    }

}
