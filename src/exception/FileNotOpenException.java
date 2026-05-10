package exception;

/**
 * Thrown when a command requires an open file but none is currently loaded.
 */
public class FileNotOpenException extends TableException {

    public FileNotOpenException() {
        super("No file is currently open.");
    }
}
