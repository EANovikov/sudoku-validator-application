package com.xevgnov.validator.game;

import com.xevgnov.exception.SudokuRuleViolationException;
import com.xevgnov.parser.ISudokuReader;
import com.xevgnov.parser.SudokuReaderCsv;
import com.xevgnov.validator.file.FileFormatValidator;
import com.xevgnov.validator.file.IFileFormatValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.xevgnov.TestUtilities.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RulesValidatorTest {

    private IRulesValidator rulesValidator;
    private ISudokuReader sudokuReader;
    private IFileFormatValidator fileFormatValidator;

    @BeforeEach
    void setUp() {
        rulesValidator = new RulesValidator();
        sudokuReader = new SudokuReaderCsv();
        fileFormatValidator = new FileFormatValidator();
    }

    @Test
    void validate_allRulesFit_successful() {
        //Given
        List<List<Integer>> validGameSolution = sudokuReader.readFile(VALID_FILE, fileFormatValidator);

        //When
        rulesValidator.validate(validGameSolution);

        //Then OK
    }

    @Test
    void validate_notUniqueValuesInRow_sudokuRuleViolationException() {
        //Given
        List<List<Integer>> invalidGameSolution = sudokuReader.readFile(RULE_VIOLATION_ROW_UNIQ, fileFormatValidator);

        //When
        assertThatThrownBy(
                () ->
                        //When
                        rulesValidator.validate(invalidGameSolution))

                //Then
                .isInstanceOf(SudokuRuleViolationException.class).hasMessageContaining("Failed the game!");
    }

    @Test
    void validate_notUniqueValuesIn3On3Square_sudokuRuleViolationException() {
        //Given
        List<List<Integer>> invalidGameSolution = sudokuReader.readFile(RULE_VIOLATION_SQUARE_DIGITS_UNIQ, fileFormatValidator);

        //When
        assertThatThrownBy(
                () ->
                        //When
                        rulesValidator.validate(invalidGameSolution))

                //Then
                .isInstanceOf(SudokuRuleViolationException.class).hasMessageContaining("Failed the game!");
    }
}