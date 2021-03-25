package com.xevgnov.parser;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.xevgnov.exception.CsvFileParsingException;
import com.xevgnov.exception.SudokuFileFormatViolationException;
import com.xevgnov.util.PropertyReader;
import com.xevgnov.validator.file.IFileFormatValidator;
import lombok.Cleanup;
import lombok.NonNull;
import lombok.extern.java.Log;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.xevgnov.util.PropertyReader.FILE_SEPARATOR_PROPERTY;

@Log
public class SudokuReaderCsv implements ISudokuReader {

    public static final char FILE_SEPARATOR = PropertyReader.getInstance()
            .getProperty(FILE_SEPARATOR_PROPERTY, ",").charAt(0);

    @Override
    public List<List<Integer>> readFile(@NonNull String fileName, @NonNull IFileFormatValidator IFileFormatValidator) {
        log.info("Reading and parsing file " + fileName);

        try (FileReader reader = new FileReader(fileName, StandardCharsets.UTF_8)) {
            @Cleanup CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withCSVParser(getCsvParser())
                    .build();

            return readInts(csvReader, IFileFormatValidator);


        } catch (CsvException e) {
            throw new CsvFileParsingException("Invalid CSV format of file " + fileName, e);
        } catch (IOException e) {
            throw new CsvFileParsingException("Impossible to read file " + fileName, e);
        }
    }

    private CSVParser getCsvParser() {
        return new CSVParserBuilder()
                .withSeparator(FILE_SEPARATOR)
                .build();
    }

    private List<List<Integer>> readInts(@NonNull CSVReader csvReader, @NonNull IFileFormatValidator IFileFormatValidator)
            throws IOException, CsvException {

        List<List<Integer>> result = new ArrayList<>();
        String[] nextRecord;
        int rowIndex = 0;
        while ((nextRecord = csvReader.readNext()) != null) {
            List<Integer> currentRow = null;
            try {
                currentRow = Arrays.stream(nextRecord).map(Integer::parseInt).collect(Collectors.toUnmodifiableList());
            } catch (NumberFormatException e) {
                throw new SudokuFileFormatViolationException("Row " + rowIndex + " contains invalid character", e);
            }
            IFileFormatValidator.validateRow(currentRow, rowIndex);
            result.add(currentRow);
            rowIndex++;
        }
        IFileFormatValidator.validateAll(result);
        log.info(rowIndex + " rows have been read and validated");
        return result;
    }

}
