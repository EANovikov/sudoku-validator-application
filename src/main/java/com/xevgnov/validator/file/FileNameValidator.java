package com.xevgnov.validator.file;

import com.xevgnov.exception.MissingFileNameParameterException;
import lombok.NonNull;
import lombok.extern.java.Log;

import java.io.File;

@Log
public class FileNameValidator {

    private String fileName;

    public FileNameValidator(String[] args) {
        validateFileName(args);
        log.info("File name is valid");
    }

    public String getFileName() {
        return fileName;
    }

    private void validateFileName(String[] args) {
        if (args == null || args.length == 0) {
            throw new MissingFileNameParameterException("Filename has not been defined.\n " +
                    "Please pass the file name like below:\n" +
                    "validate.bat filename");
        }
        this.fileName = new File(args[0])
                .toPath()
                .toAbsolutePath()
                .normalize()
                .toFile()
                .toString();
    }
}
