package exception;

/**
 * Thrown when a formula references a cell that does not exist in the table.
 */
public class InvalidCellReferenceException extends TableException {

    public InvalidCellReferenceException(int row, int column) {
        super("Invalid cell reference: R" + row + "C" + column);
    }
}
