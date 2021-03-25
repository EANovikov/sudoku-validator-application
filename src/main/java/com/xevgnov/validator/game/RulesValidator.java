package com.xevgnov.validator.game;

import com.xevgnov.exception.SudokuRuleViolationException;
import lombok.NonNull;
import lombok.extern.java.Log;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log
public class RulesValidator implements IRulesValidator {

    private List<List<Integer>> inputRows;

    @Override
    public void validate(@NonNull List<List<Integer>> inputRows) {
        rowsContainUniqueNumbers(inputRows);
        eachTreeOnTreeSquareContainsUniqueNumbers(inputRows);
        log.info("Validation done, data fit to the rules!");
    }

    private void rowsContainUniqueNumbers(List<List<Integer>> inputRows) {
        int countRow = 0;
        for (List<Integer> list : inputRows) {
            Set<Integer> uniqueValue = new HashSet<>(list);
            if (uniqueValue.size() != list.size()) {
                throw new SudokuRuleViolationException("There where found only " + uniqueValue.size()
                        + " unique values, but expected " + inputRows.size() + " unique values in row " + countRow);
            }
            countRow++;
        }
    }

    private void eachTreeOnTreeSquareContainsUniqueNumbers(List<List<Integer>> inputRows) {

        List<Set<Integer>> squares = List.of(new HashSet<>(9), new HashSet<>(9), new HashSet<>(9));

        for (int i = 0; i < inputRows.size(); i++) {
            if (i != 0 && i % 3 == 0) {
                squares.forEach(Set::clear);
            }
            List<Integer> row = inputRows.get(i);
            int currentSquare = 0;

            for (int j = 0; j < row.size(); j++) {
                if (j != 0 && j % 3 == 0) {
                    currentSquare++;
                }
                Integer currentValue = row.get(j);
                boolean uniqueValue = squares.get(currentSquare).add(currentValue);
                if (!uniqueValue) {
                    throw new SudokuRuleViolationException("The value " + currentValue + " is not unique in its 3x3 square. " +
                            "Row: " + i + ", cell: " + j);
                }
            }
        }
    }

}
