package com.xevgnov.parser;

import com.xevgnov.validator.file.IFileFormatValidator;

import java.util.List;

public interface ISudokuReader {

    List<List<Integer>> readFile(String fileName, IFileFormatValidator fileFormatValidatior);

}
