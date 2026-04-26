package exception;

/**
 * Base exception for all spreadsheet application errors.
 */
public class TableException extends Exception {

    public TableException(String message) {
        super(message);
    }

    public TableException(String message, Throwable cause) {
        super(message, cause);
    }
}
