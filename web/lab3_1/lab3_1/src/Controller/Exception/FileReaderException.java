package Controller.Exception;

public class FileReaderException extends Exception {
    public FileReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileReaderException(String message) {
        super(message);
    }
}