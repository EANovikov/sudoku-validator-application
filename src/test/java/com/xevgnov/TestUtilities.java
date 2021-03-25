package com.xevgnov;

import java.util.ArrayList;
import java.util.List;

public final class TestUtilities {

    public static final String VALID_FILE = "src/test/resources/files/validGame.txt";
    public static final String INVALID_FILE = "src/test/resources/files/invalidFileFormat.txt";
    public static final String RULE_VIOLATION_ROW_UNIQ = "src/test/resources/files/ruleViolationRowValuesUniqness.txt";
    public static final String RULE_VIOLATION_SQUARE_DIGITS_UNIQ = "src/test/resources/files/ruleViolationSquareDigitsUniqness.txt";

    public static List<Integer> getStubRow(int numberOfValues) {
        List<Integer> row = new ArrayList<>(numberOfValues);
        for (int i = 0; i < numberOfValues; i++) {
            row.add(i + 1);
        }
        return row;
    }

    public static List<List<Integer>> getStubRows(int numberOfRows) {
        List<List<Integer>> rows = new ArrayList<>(numberOfRows);
        for (int i = 0; i < numberOfRows; i++) {
            rows.add(getStubRow(9));
        }
        return rows;
    }
}