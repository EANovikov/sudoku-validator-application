package com.xevgnov.parser;

import com.xevgnov.exception.SudokuFileFormatViolationException;
import com.xevgnov.validator.file.FileFormatValidator;
import com.xevgnov.validator.file.IFileFormatValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.xevgnov.TestUtilities.INVALID_FILE;
import static com.xevgnov.TestUtilities.VALID_FILE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SudokuReaderCsvTest {

    private ISudokuReader sudokuReader;
    private IFileFormatValidator fileFormatValidator;

    @BeforeEach
    public void setUp() {
        sudokuReader = new SudokuReaderCsv();
        fileFormatValidator = new FileFormatValidator();
    }

    @Test
    public void readFile_validFormat_successful() {
        //When
        List<List<Integer>> result = sudokuReader.readFile(VALID_FILE, fileFormatValidator);

        //Then
        assertThat(result.size()).isEqualTo(9);
        assertThat(result.get(8)).containsExactly(7, 8, 9, 1, 2, 3, 4, 5, 6);
    }

    @Test
    public void readFile_invalidSymbols_sudokuFileFormatViolationException() {

        assertThatThrownBy(
                () ->

                        //When
                        sudokuReader.readFile(INVALID_FILE, fileFormatValidator))

                //Then
                .isInstanceOf(SudokuFileFormatViolationException.class);
    }

}