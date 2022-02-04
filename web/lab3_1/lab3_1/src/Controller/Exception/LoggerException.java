package Controller.Exception;

public class LoggerException extends Exception{
    public LoggerException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoggerException(String message) {
        super(message);
    }
}
