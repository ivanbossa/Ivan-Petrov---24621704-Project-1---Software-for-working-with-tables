package exception;

/**
 * Thrown when the user tries to open a file while another file is already open.
 */
public class FileAlreadyOpenException extends TableException {

    public FileAlreadyOpenException(String currentFile) {
        super("A file is already open: " + currentFile + ". Please close it first.");
    }
}
