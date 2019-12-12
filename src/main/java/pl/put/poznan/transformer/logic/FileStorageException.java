package pl.put.poznan.transformer.logic;

import org.springframework.util.StringUtils;

public class FileStorageException extends RuntimeException {

    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
