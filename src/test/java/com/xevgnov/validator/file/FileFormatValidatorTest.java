package com.xevgnov.validator.file;

import com.xevgnov.exception.SudokuFileFormatViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.xevgnov.TestUtilities.getStubRow;
import static com.xevgnov.TestUtilities.getStubRows;
import static com.xevgnov.validator.file.IFileFormatValidator.FIELD_SIZE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FileFormatValidatorTest {

    private IFileFormatValidator fileFormatValidator;

    @BeforeEach
    public void setUp() {
        fileFormatValidator = new FileFormatValidator();
    }

    @Test
    public void validateRow_correctFormat_successful() {
        //Given
        List<Integer> validRow = getStubRow(FIELD_SIZE);

        //When
        fileFormatValidator.validateRow(validRow, 0);

        //Then OK
    }

    @Test
    public void validateRow_tooShort_sudokuFileFormatViolationException() {
        //Given
        List<Integer> shortRow = getStubRow(FIELD_SIZE - 1);

        assertThatThrownBy(
                () ->
                        //When
                        fileFormatValidator.validateRow(shortRow, 0))

                //Then
                .isInstanceOf(SudokuFileFormatViolationException.class);
    }

    @Test
    public void validateRow_tooLong_sudokuFileFormatViolationException() {
        //Given
        List<Integer> longRow = getStubRow(FIELD_SIZE + 1);

        assertThatThrownBy(
                () ->
                        //When
                        fileFormatValidator.validateRow(longRow, 0))

                //Then
                .isInstanceOf(SudokuFileFormatViolationException.class);
    }

    @Test
    public void validateRow_validSizeAndTooSmallInteger_sudokuFileFormatViolationException() {
        //Given
        List<Integer> row = getStubRow(FIELD_SIZE - 1);
        row.add(0);

        assertThatThrownBy(
                () ->
                        //When
                        fileFormatValidator.validateRow(row, 0))

                //Then
                .isInstanceOf(SudokuFileFormatViolationException.class);
    }

    @Test
    public void validateRow_validSizeAndTooBigInteger_sudokuFileFormatViolationException() {
        //Given
        List<Integer> row = getStubRow(FIELD_SIZE - 1);
        row.add(10);

        assertThatThrownBy(
                () ->
                        //When
                        fileFormatValidator.validateRow(row, 0))

                //Then
                .isInstanceOf(SudokuFileFormatViolationException.class);
    }

    @Test
    public void validateAll_validSize_successful() {
        //Given
        List<List<Integer>> validRows = getStubRows(FIELD_SIZE);

        //When
        fileFormatValidator.validateAll(validRows);

        //Then OK
    }

    @Test
    public void validateAll_tooFewRows_successful() {
        //Given
        List<List<Integer>> tooFewRows = getStubRows(FIELD_SIZE - 1);

        //When
        assertThatThrownBy(
                () ->
                        //When
                        fileFormatValidator.validateAll(tooFewRows))

                //Then
                .isInstanceOf(SudokuFileFormatViolationException.class);
    }

    @Test
    public void validateAll_tooMuchRows_successful() {
        //Given
        List<List<Integer>> tooMuchRows = getStubRows(FIELD_SIZE + 1);

        //When
        assertThatThrownBy(
                () ->
                        //When
                        fileFormatValidator.validateAll(tooMuchRows))

                //Then
                .isInstanceOf(SudokuFileFormatViolationException.class);
    }

}