package com.xevgnov.validator.file;

import com.xevgnov.exception.MissingFileNameParameterException;
import org.junit.jupiter.api.Test;

import java.nio.file.InvalidPathException;

import static com.xevgnov.TestUtilities.VALID_FILE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class FileNameValidatorTest {

    private FileNameValidator fileNameValidator;

    @Test
    public void fileNameValidatorValidFileName_getFileName_validString() {
        //Given
        fileNameValidator = new FileNameValidator(new String[]{VALID_FILE});
        //When
        String fileName = fileNameValidator.getFileName();
        //Then
        assertThat(fileName).endsWith("validGame.txt");
    }

    @Test
    public void fileNameValidatorInvalidFileName_invalidPathException() {
        //Given
        String invalidPath = "invalid///path?/file.txt";
        assertThatThrownBy(
                () ->

                        //When
                        fileNameValidator = new FileNameValidator(new String[]{"invalid///path?/file.txt"}))
                //Then
                .isInstanceOf(InvalidPathException.class).hasMessageContaining("Illegal char");
    }

    @Test
    public void fileNameValidatorMissingFileName_missingFileNameParameterException() {
        assertThatThrownBy(
                () ->
                        //When
                        fileNameValidator = new FileNameValidator(new String[]{}))
                //Then
                .isInstanceOf(MissingFileNameParameterException.class);

    }

    @Test
    public void fileNameValidatorNullArguments_missingFileNameParameterException() {
        assertThatThrownBy(
                () ->
                        //When
                        fileNameValidator = new FileNameValidator(null))
                //Then
                .isInstanceOf(MissingFileNameParameterException.class);
    }

}