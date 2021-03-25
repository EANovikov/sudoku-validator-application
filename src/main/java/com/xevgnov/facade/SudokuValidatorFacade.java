package com.xevgnov.facade;

import com.xevgnov.parser.ISudokuReader;
import com.xevgnov.parser.SudokuReaderCsv;
import com.xevgnov.validator.file.FileFormatValidator;
import com.xevgnov.validator.file.FileNameValidator;
import com.xevgnov.validator.game.IRulesValidator;
import com.xevgnov.validator.game.RulesValidator;

import java.util.List;

public class SudokuValidatorFacade {

    public static void validate(String[] args) {
        // (1) validate filename
        FileNameValidator fileNameValidator = new FileNameValidator(args);
        String validatedFileName = fileNameValidator.getFileName();
        // (2) read file in desired format
        ISudokuReader ISudokuReader = new SudokuReaderCsv();
        List<List<Integer>> inputRows = ISudokuReader.readFile(validatedFileName, new FileFormatValidator());
        // (3) validate the game
        IRulesValidator IRulesValidator = new RulesValidator();
        IRulesValidator.validate(inputRows);
    }

}
