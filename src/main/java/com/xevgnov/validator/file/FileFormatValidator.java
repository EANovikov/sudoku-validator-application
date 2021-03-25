package com.xevgnov.validator.file;

import com.xevgnov.exception.SudokuFileFormatViolationException;

import java.util.List;
import java.util.Optional;

public class FileFormatValidator implements IFileFormatValidator {

    @Override
    public void validateRow(List<Integer> row, int rowIndex) {
        validateRowSize(row, rowIndex);
        validateAllowedIntegers(row, rowIndex);
    }

    @Override
    public void validateAll(List<List<Integer>> rows) {
        validateAmountOfRows(rows.size());
    }

    private void validateRowSize(List<Integer> row, int rowIndex) {
        if (row.size() != FIELD_SIZE) {
            throw new SudokuFileFormatViolationException(row.size() + " values found, but expected " + FIELD_SIZE + " values in row " + rowIndex);
        }
    }

    private void validateAllowedIntegers(List<Integer> row, int rowIndex) {
        Optional<Integer> invalidNumber = row.stream().filter(i -> (i < 1) || (i > FIELD_SIZE)).findFirst();
        if (invalidNumber.isPresent()) {
            throw new SudokuFileFormatViolationException("Invalid number " + invalidNumber.get() + " found in row " + rowIndex);
        }
    }

    private void validateAmountOfRows(int numberOfRows) {
        if (numberOfRows != FIELD_SIZE) {
            throw new SudokuFileFormatViolationException(numberOfRows + " rows in game, but expected " + FIELD_SIZE);
        }
    }
}
