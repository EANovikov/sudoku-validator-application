package com.xevgnov.validator.file;

import java.util.List;

public interface IFileFormatValidator {

    int FIELD_SIZE = 9;

    void validateRow(List<Integer> row, int rowIndex);

    void validateAll(List<List<Integer>> rows);

}
